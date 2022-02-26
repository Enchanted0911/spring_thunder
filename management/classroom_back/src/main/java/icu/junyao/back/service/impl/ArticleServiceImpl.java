package icu.junyao.back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.back.client.AclUserClient;
import icu.junyao.back.entity.Article;
import icu.junyao.back.entity.ArticleComment;
import icu.junyao.back.entity.ArticleContent;
import icu.junyao.back.entity.Subject;
import icu.junyao.back.mapper.ArticleCommentMapper;
import icu.junyao.back.mapper.ArticleContentMapper;
import icu.junyao.back.mapper.ArticleMapper;
import icu.junyao.back.mapper.SubjectMapper;
import icu.junyao.back.req.ArticleEditReq;
import icu.junyao.back.req.ArticleReq;
import icu.junyao.back.req.PageArticleReq;
import icu.junyao.back.res.ArticleRes;
import icu.junyao.back.service.ArticleService;
import icu.junyao.common.entity.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private final ArticleContentMapper articleContentMapper;
    private final SubjectMapper subjectMapper;
    private final ArticleCommentMapper articleCommentMapper;
    private final AclUserClient aclUserClient;

    @Override
    public PageResult<ArticleRes> pageArticle(PageArticleReq pageArticleReq) {

        IPage<Article> articlePage = new Page<>(pageArticleReq.getPage(), pageArticleReq.getPageSize());

        // 按其他条件查找文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = Wrappers.lambdaQuery();
        articleLambdaQueryWrapper.eq(StrUtil.isNotBlank(pageArticleReq.getSubjectId()), Article::getSubjectId, pageArticleReq.getSubjectId())
                .eq(StrUtil.isNotBlank(pageArticleReq.getSubjectParentId()), Article::getSubjectParentId, pageArticleReq.getSubjectParentId())
                .eq(StrUtil.isNotBlank(pageArticleReq.getCreatedBy()), Article::getCreatedBy, pageArticleReq.getCreatedBy())
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
            articleRes.setCreatedBy(aclUserClient.userDetails(a.getCreatedBy()).getData().getUsername());
            articleResList.add(articleRes);
        });

        return new PageResult<>(articlePage.getTotal(), articleResList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleReq articleReq) {
        Article article = new Article();
        ArticleContent articleContent = new ArticleContent();

        BeanUtils.copyProperties(articleReq, article);
        BeanUtils.copyProperties(articleReq, articleContent);

        super.save(article);

        articleContent.setId(article.getId());
        articleContentMapper.insert(articleContent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(ArticleEditReq articleEditReq) {
        Article article = new Article();
        ArticleContent articleContent = new ArticleContent();

        BeanUtils.copyProperties(articleEditReq, article);
        BeanUtils.copyProperties(articleEditReq, articleContent);

        super.updateById(article);
        articleContentMapper.updateById(articleContent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeArticle(String id) {
        super.removeById(id);
        articleContentMapper.deleteById(id);
        articleCommentMapper.delete(Wrappers.lambdaQuery(ArticleComment.class).eq(ArticleComment::getArticleId, id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeArticle(List<String> idList) {
        super.removeByIds(idList);
        articleContentMapper.deleteBatchIds(idList);
        articleCommentMapper.delete(Wrappers.lambdaQuery(ArticleComment.class).in(ArticleComment::getArticleId, idList));
    }

    @Override
    public ArticleRes articleDetails(String id) {
        Article article = super.getById(id);
        ArticleRes articleRes = new ArticleRes();
        BeanUtils.copyProperties(article, articleRes);
        articleRes.setCreatedBy(aclUserClient.userDetails(article.getCreatedBy()).getData().getUsername());
        return articleRes;
    }
}
