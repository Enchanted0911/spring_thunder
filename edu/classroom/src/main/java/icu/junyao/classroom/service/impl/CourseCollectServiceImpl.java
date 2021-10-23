package icu.junyao.classroom.service.impl;

import icu.junyao.classroom.entity.CourseCollect;
import icu.junyao.classroom.mapper.CourseCollectMapper;
import icu.junyao.classroom.service.CourseCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class CourseCollectServiceImpl extends ServiceImpl<CourseCollectMapper, CourseCollect> implements CourseCollectService {

}
