package icu.junyao.back.service;

import icu.junyao.back.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.res.SubjectOneRes;
import org.springframework.web.multipart.MultipartFile;

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
     * 通过excel添加学科
     *
     * @param file excel文件
     * @param subjectService excel监听器无法让spring管理, 传入service操作数据库
     */
    void saveSubjectByExcel(MultipartFile file, SubjectService subjectService);

    /**
     * 获取树形学科列表
     *
     * @return 一级学科树形节点
     */
    List<SubjectOneRes> gainSubject();
}
