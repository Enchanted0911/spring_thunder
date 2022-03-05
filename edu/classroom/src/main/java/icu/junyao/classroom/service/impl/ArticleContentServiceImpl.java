package icu.junyao.classroom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.classroom.entity.ArticleContent;
import icu.junyao.classroom.mapper.ArticleContentMapper;
import icu.junyao.classroom.res.ArticleContentRes;
import icu.junyao.classroom.service.ArticleContentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章内容表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements ArticleContentService {
    @Override
    public ArticleContentRes articleContentDetails(String id) {
        ArticleContent articleContent = super.getById(id);
        ArticleContentRes articleContentRes = new ArticleContentRes();
        articleContentRes.setContent(articleContent.getContent());
        return articleContentRes;
    }
}
