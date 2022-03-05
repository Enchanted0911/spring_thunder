package icu.junyao.classroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.classroom.entity.Course;
import icu.junyao.classroom.entity.Teacher;
import icu.junyao.classroom.mapper.TeacherMapper;
import icu.junyao.classroom.req.PageTeacherReq;
import icu.junyao.classroom.res.CourseRes;
import icu.junyao.classroom.res.TeacherRes;
import icu.junyao.classroom.service.TeacherService;
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

    @Override
    public List<TeacherRes> list4HotTeacher() {
        LambdaQueryWrapper<Teacher> courseLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseLambdaQueryWrapper.orderByAsc(Teacher::getSort).last("limit 4");
        List<Teacher> teacherList = super.list(courseLambdaQueryWrapper);
        return BeanUtil.copyToList(teacherList, TeacherRes.class, CopyOptions.create());
    }

    @Override
    public PageResult<TeacherRes> pageTeacher(PageTeacherReq pageTeacherReq) {
        // 构建分页
        IPage<Teacher> teacherPage = new Page<>(pageTeacherReq.getPage(), pageTeacherReq.getPageSize());
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = Wrappers.lambdaQuery();
        teacherLambdaQueryWrapper
                .like(StrUtil.isNotEmpty(pageTeacherReq.getTeacherName()), Teacher::getName, pageTeacherReq.getTeacherName());
        super.page(teacherPage, teacherLambdaQueryWrapper);

        // 属性转移
        List<TeacherRes> teacherResList = BeanUtil
                .copyToList(teacherPage.getRecords(), TeacherRes.class, CopyOptions.create());

        return new PageResult<>(teacherPage.getTotal(), teacherResList);
    }

    @Override
    public TeacherRes teacherDetails(String id) {
        Teacher teacher = super.getById(id);
        if (teacher == null) {
            throw BusinessResponseEnum.TEACHER_NOT_EXISTS.newException();
        }
        TeacherRes teacherRes = new TeacherRes();
        BeanUtils.copyProperties(teacher, teacherRes);
        return teacherRes;
    }

    @Override
    public List<TeacherRes> listTeacher() {
        return BeanUtil
                .copyToList(super.list(), TeacherRes.class, CopyOptions.create());
    }
}
