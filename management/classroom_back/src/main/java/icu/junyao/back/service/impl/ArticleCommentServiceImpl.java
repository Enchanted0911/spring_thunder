package icu.junyao.back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.back.entity.ArticleComment;
import icu.junyao.back.mapper.ArticleCommentMapper;
import icu.junyao.back.req.PageArticleCommentReq;
import icu.junyao.back.res.ArticleCommentRes;
import icu.junyao.back.res.BannerRes;
import icu.junyao.back.service.ArticleCommentService;
import icu.junyao.common.entity.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章评论表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Override
    public PageResult<ArticleCommentRes> pageArticleComment(PageArticleCommentReq pageArticleCommentReq) {

        IPage<ArticleComment> articleCommentPage = new Page<>(pageArticleCommentReq.getPage(), pageArticleCommentReq.getPageSize());
        LambdaQueryWrapper<ArticleComment> articleCommentLambdaQueryWrapper = Wrappers.lambdaQuery();
        articleCommentLambdaQueryWrapper.eq(ArticleComment::getArticleId, pageArticleCommentReq.getArticleId());
        super.page(articleCommentPage, articleCommentLambdaQueryWrapper);

        List<ArticleCommentRes> articleCommentResList = BeanUtil
                .copyToList(articleCommentPage.getRecords(), ArticleCommentRes.class, CopyOptions.create());

        return new PageResult<>(articleCommentPage.getTotal(), articleCommentResList);
    }
}
