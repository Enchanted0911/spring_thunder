package icu.junyao.classroom.service.impl;

import icu.junyao.classroom.entity.Subject;
import icu.junyao.classroom.mapper.SubjectMapper;
import icu.junyao.classroom.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
