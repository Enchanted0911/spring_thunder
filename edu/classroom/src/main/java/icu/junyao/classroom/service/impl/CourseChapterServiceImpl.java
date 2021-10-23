package icu.junyao.classroom.service.impl;

import icu.junyao.classroom.entity.CourseChapter;
import icu.junyao.classroom.mapper.CourseChapterMapper;
import icu.junyao.classroom.service.CourseChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class CourseChapterServiceImpl extends ServiceImpl<CourseChapterMapper, CourseChapter> implements CourseChapterService {

}
