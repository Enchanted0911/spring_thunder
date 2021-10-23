package icu.junyao.classroom.service.impl;

import icu.junyao.classroom.entity.CourseComment;
import icu.junyao.classroom.mapper.CourseCommentMapper;
import icu.junyao.classroom.service.CourseCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class CourseCommentServiceImpl extends ServiceImpl<CourseCommentMapper, CourseComment> implements CourseCommentService {

}
