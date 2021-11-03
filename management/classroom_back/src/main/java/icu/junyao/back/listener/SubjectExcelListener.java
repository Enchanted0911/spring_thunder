package icu.junyao.back.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.back.constant.ClassroomConstants;
import icu.junyao.back.entity.Subject;
import icu.junyao.back.inter.SubjectInter;
import icu.junyao.back.service.SubjectService;
import icu.junyao.common.enums.BusinessResponseEnum;

/**
 * @author johnson
 * @date 2021-11-03
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectInter> {


    /**
     * 因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
     * 不能实现数据库操作
     */
    public SubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    /**
     * 读取excel内容，一行一行进行读取
     *
     * @param subjectInter    {@link SubjectInter}
     * @param analysisContext {@link AnalysisContext}
     */
    @Override
    public void invoke(SubjectInter subjectInter, AnalysisContext analysisContext) {
        if (subjectInter == null) {
            throw BusinessResponseEnum.FILE_IS_EMPTY.newException();
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        Subject existOneSubject = this.existOneSubject(subjectService, subjectInter.getOneSubjectName());
        if (existOneSubject == null) {
            //没有相同一级分类，进行添加
            existOneSubject = new Subject();
            existOneSubject.setParentId(ClassroomConstants.ONE_SUBJECT_CODE);
            //一级分类名称
            existOneSubject.setTitle(subjectInter.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pId = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        Subject existTwoSubject = this.existTwoSubject(subjectService, subjectInter.getTwoSubjectName(), pId);
        if (existTwoSubject == null) {
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(pId);
            //二级分类名称
            existTwoSubject.setTitle(subjectInter.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }


    /**
     * 判断一级学科不能重复添加
     *
     * @param subjectService {@link SubjectService}
     * @param name           一级学科名
     * @return 查找到的一级学科
     */
    private Subject existOneSubject(SubjectService subjectService, String name) {
        LambdaQueryWrapper<Subject> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Subject::getTitle, name);
        wrapper.eq(Subject::getParentId, ClassroomConstants.ONE_SUBJECT_CODE);
        return subjectService.getOne(wrapper);
    }

    /**
     * 判断二级学科不能重复添加
     *
     * @param subjectService {@link SubjectService}
     * @param name           二级学科名
     * @param pId            父级学科名
     * @return 查找到的二级学科
     */
    private Subject existTwoSubject(SubjectService subjectService, String name, String pId) {
        LambdaQueryWrapper<Subject> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Subject::getTitle, name);
        wrapper.eq(Subject::getParentId, pId);
        return subjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
