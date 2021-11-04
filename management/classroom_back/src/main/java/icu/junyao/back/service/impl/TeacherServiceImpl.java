package icu.junyao.back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.back.constant.ClassroomConstants;
import icu.junyao.back.entity.Course;
import icu.junyao.back.entity.Teacher;
import icu.junyao.back.mapper.CourseMapper;
import icu.junyao.back.mapper.TeacherMapper;
import icu.junyao.back.req.PageTeacherReq;
import icu.junyao.back.req.TeacherEditReq;
import icu.junyao.back.req.TeacherReq;
import icu.junyao.back.res.TeacherRes;
import icu.junyao.back.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    private final CourseMapper courseMapper;

    @Override
    public List<TeacherRes> listTeacher() {
        List<Teacher> teacherList = super.list();
        return BeanUtil.copyToList(teacherList, TeacherRes.class, CopyOptions.create());
    }

    @Override
    public void removeTeacher(String id) {

        // 检查该教师是否存在所讲课程
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseLambdaQueryWrapper.eq(Course::getTeacherId, id);
        List<Course> courseList = courseMapper.selectList(courseLambdaQueryWrapper);
        if (CollUtil.isNotEmpty(courseList)) {
            throw BusinessResponseEnum.TEACHER_CANNOT_DELETE.newException();
        }

        super.removeById(id);
    }

    @Override
    public PageResult<TeacherRes> pageTeacher(PageTeacherReq pageTeacherReq) {
        // 构建分页条件
        IPage<Teacher> teacherPage = new Page<>(pageTeacherReq.getPage(), pageTeacherReq.getPageSize());
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = Wrappers.lambdaQuery();
        teacherLambdaQueryWrapper
                .eq(pageTeacherReq.getLevel() != null, Teacher::getLevel, pageTeacherReq.getLevel())
                .like(StrUtil.isNotEmpty(pageTeacherReq.getName()), Teacher::getName, pageTeacherReq.getName())
                .le(StrUtil.isNotEmpty(pageTeacherReq.getEnd()), Teacher::getCreatedTime, pageTeacherReq.getEnd())
                .ge(StrUtil.isNotEmpty(pageTeacherReq.getEnd()), Teacher::getCreatedTime, pageTeacherReq.getBegin())
                .orderByDesc(Teacher::getCreatedTime);

        super.page(teacherPage, teacherLambdaQueryWrapper);

        // 属性转移
        List<TeacherRes> teacherResList = BeanUtil
                .copyToList(teacherPage.getRecords(), TeacherRes.class, CopyOptions.create());

        return new PageResult<>(teacherPage.getTotal(), teacherResList);
    }

    @Override
    public void saveTeacher(TeacherReq teacherReq) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherReq, teacher);
        // 设置默认头像
        teacher.setAvatar(StrUtil.isNotEmpty(teacher.getAvatar()) ? teacher.getAvatar() :ClassroomConstants.DEFAULT_TEACHER_COVER);
        super.save(teacher);
    }

    @Override
    public void updateTeacher(TeacherEditReq teacherEditReq) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherEditReq, teacher);
        super.updateById(teacher);
    }

    @Override
    public TeacherRes teacherDetails(String id) {
        Teacher teacher = super.getById(id);
        TeacherRes teacherRes = new TeacherRes();
        BeanUtils.copyProperties(teacher, teacherRes);
        return teacherRes;
    }
}
