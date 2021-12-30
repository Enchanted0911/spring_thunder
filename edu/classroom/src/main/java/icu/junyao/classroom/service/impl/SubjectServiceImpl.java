package icu.junyao.classroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.classroom.constant.ClassroomConstants;
import icu.junyao.classroom.entity.Subject;
import icu.junyao.classroom.mapper.SubjectMapper;
import icu.junyao.classroom.res.SubjectOneRes;
import icu.junyao.classroom.res.SubjectTwoRes;
import icu.junyao.classroom.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Override
    public List<SubjectOneRes> gainSubject() {

        // 获取所有一级学科
        LambdaQueryWrapper<Subject> subjectLambdaQueryWrapper = Wrappers.lambdaQuery();
        subjectLambdaQueryWrapper.eq(Subject::getParentId, ClassroomConstants.ONE_SUBJECT_CODE);
        List<Subject> oneSubjectList = super.list(subjectLambdaQueryWrapper);

        // 获取所有二级学科
        subjectLambdaQueryWrapper = Wrappers.lambdaQuery();
        subjectLambdaQueryWrapper.ne(Subject::getParentId, ClassroomConstants.ONE_SUBJECT_CODE);
        List<Subject> twoSubjectList = super.list(subjectLambdaQueryWrapper);

        // 创建最终集合
        List<SubjectOneRes> subjectOneResList = new ArrayList<>();

        // 构建二级树形结构
        oneSubjectList.forEach(one -> {
            // 把一级学科加入返回列表
            SubjectOneRes subjectOneRes = new SubjectOneRes();
            BeanUtils.copyProperties(one, subjectOneRes);
            subjectOneResList.add(subjectOneRes);

            // 为所有一级学科添加二级学科
            List<SubjectTwoRes> subjectTwoResList = new ArrayList<>();
            twoSubjectList.stream().filter(two -> two.getParentId().equals(one.getId())).forEach(two -> {
                SubjectTwoRes subjectTwoRes = new SubjectTwoRes();
                BeanUtils.copyProperties(two, subjectTwoRes);
                subjectTwoResList.add(subjectTwoRes);
            });

            // 设置一级学科的二级学科集合
            subjectOneRes.setSubjectTwoResList(subjectTwoResList);
        });
        return subjectOneResList;
    }
}
