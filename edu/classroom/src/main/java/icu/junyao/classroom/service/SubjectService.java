package icu.junyao.classroom.service;

import icu.junyao.classroom.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.res.SubjectOneRes;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 获取树形学科列表
     *
     * @return 一级学科树形节点
     */
    List<SubjectOneRes> gainSubject();
}
