package icu.junyao.classroom.service.impl;

import icu.junyao.classroom.entity.Teacher;
import icu.junyao.classroom.mapper.TeacherMapper;
import icu.junyao.classroom.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
