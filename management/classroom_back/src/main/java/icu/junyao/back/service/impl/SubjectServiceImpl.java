package icu.junyao.back.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.back.constant.ClassroomConstants;
import icu.junyao.back.entity.Subject;
import icu.junyao.back.inter.SubjectInter;
import icu.junyao.back.listener.SubjectExcelListener;
import icu.junyao.back.mapper.SubjectMapper;
import icu.junyao.back.res.SubjectOneRes;
import icu.junyao.back.res.SubjectTwoRes;
import icu.junyao.back.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubjectByExcel(MultipartFile file, SubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectInter.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

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
