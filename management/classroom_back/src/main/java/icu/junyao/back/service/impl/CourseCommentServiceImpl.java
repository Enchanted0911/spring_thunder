package icu.junyao.back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.back.entity.CourseComment;
import icu.junyao.back.mapper.CourseCommentMapper;
import icu.junyao.back.req.PageCourseCommentReq;
import icu.junyao.back.res.CourseCommentRes;
import icu.junyao.back.service.CourseCommentService;
import icu.junyao.common.entity.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
public class CourseCommentServiceImpl extends ServiceImpl<CourseCommentMapper, CourseComment> implements CourseCommentService {

    @Override
    public PageResult<CourseCommentRes> pageComment(PageCourseCommentReq pageCourseCommentReq) {
        // 构建分页条件, 按时间降序查找当前课程的评论
        IPage<CourseComment> courseCommentPage = new Page<>(pageCourseCommentReq.getPage()
                , pageCourseCommentReq.getPageSize());
        LambdaQueryWrapper<CourseComment> courseCommentLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseCommentLambdaQueryWrapper.eq(CourseComment::getCourseId, pageCourseCommentReq.getCourseId())
                .orderByDesc(CourseComment::getCreatedTime);

        super.page(courseCommentPage, courseCommentLambdaQueryWrapper);

        // 属性转移
        List<CourseCommentRes> courseCommentResList = BeanUtil
                .copyToList(courseCommentPage.getRecords(), CourseCommentRes.class, CopyOptions.create());

        return new PageResult<>(courseCommentPage.getTotal(), courseCommentResList);
    }
}
