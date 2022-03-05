package icu.junyao.classroom.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.classroom.entity.AclUserTeacher;
import icu.junyao.classroom.entity.Article;
import icu.junyao.classroom.entity.Subject;
import icu.junyao.classroom.mapper.*;
import icu.junyao.classroom.req.PageArticleReq;
import icu.junyao.classroom.res.ArticleRes;
import icu.junyao.classroom.service.ArticleService;
import icu.junyao.common.entity.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    private final SubjectMapper subjectMapper;
    private final TeacherMapper teacherMapper;
    private final AclUserTeacherMapper aclUserTeacherMapper;

    @Override
    public PageResult<ArticleRes> pageArticle(PageArticleReq pageArticleReq) {

        IPage<Article> articlePage = new Page<>(pageArticleReq.getPage(), pageArticleReq.getPageSize());

        if (StrUtil.isNotBlank(pageArticleReq.getTeacherId())){
            String createdBy = aclUserTeacherMapper.selectOne(Wrappers.lambdaQuery(AclUserTeacher.class)
                    .eq(AclUserTeacher::getTeacherId, pageArticleReq.getTeacherId())).getAclUserId();
            pageArticleReq.setTeacherId(createdBy);
        }
        // 按其他条件查找文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = Wrappers.lambdaQuery();
        articleLambdaQueryWrapper.eq(StrUtil.isNotBlank(pageArticleReq.getSubjectId()), Article::getSubjectId, pageArticleReq.getSubjectId())
                .eq(StrUtil.isNotBlank(pageArticleReq.getSubjectParentId()), Article::getSubjectParentId, pageArticleReq.getSubjectParentId())
                .eq(StrUtil.isNotBlank(pageArticleReq.getTeacherId()), Article::getCreatedBy, pageArticleReq.getTeacherId())
                .like(StrUtil.isNotBlank(pageArticleReq.getTitle()), Article::getTitle, pageArticleReq.getTitle());

        super.page(articlePage, articleLambdaQueryWrapper);

        // 属性转移 添加学科名称
        List<ArticleRes> articleResList = new ArrayList<>();
        articlePage.getRecords().forEach(a -> {
            ArticleRes articleRes = new ArticleRes();
            BeanUtils.copyProperties(a, articleRes);
            articleRes.setSubjectName(subjectMapper.selectOne(Wrappers.lambdaQuery(Subject.class)
                    .eq(Subject::getId, a.getSubjectId())).getTitle());
            articleRes.setSubjectParentName(subjectMapper.selectOne(Wrappers.lambdaQuery(Subject.class)
                    .eq(Subject::getId, a.getSubjectParentId())).getTitle());

            // 管理员id转成教师id再将id转成name
            String teacherId = aclUserTeacherMapper.selectOne(Wrappers.lambdaQuery(AclUserTeacher.class)
                    .eq(AclUserTeacher::getAclUserId, a.getCreatedBy())).getTeacherId();
            articleRes.setTeacherName(teacherMapper.selectById(teacherId).getName());

            articleResList.add(articleRes);
        });

        return new PageResult<>(articlePage.getTotal(), articleResList);
    }

    @Override
    public ArticleRes articleDetails(String id) {
        Article article = super.getById(id);
        ArticleRes articleRes = new ArticleRes();
        BeanUtils.copyProperties(article, articleRes);

        articleRes.setSubjectName(subjectMapper.selectOne(Wrappers.lambdaQuery(Subject.class)
                .eq(Subject::getId, article.getSubjectId())).getTitle());
        articleRes.setSubjectParentName(subjectMapper.selectOne(Wrappers.lambdaQuery(Subject.class)
                .eq(Subject::getId, article.getSubjectParentId())).getTitle());

        // 管理员id转成教师id再将id转成name
        String teacherId = aclUserTeacherMapper.selectOne(Wrappers.lambdaQuery(AclUserTeacher.class)
                .eq(AclUserTeacher::getAclUserId, article.getCreatedBy())).getTeacherId();
        articleRes.setTeacherName(teacherMapper.selectById(teacherId).getName());
        return articleRes;
    }
}
