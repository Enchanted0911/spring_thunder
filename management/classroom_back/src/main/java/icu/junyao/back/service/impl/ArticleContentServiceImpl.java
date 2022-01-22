package icu.junyao.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.back.entity.ArticleContent;
import icu.junyao.back.mapper.ArticleContentMapper;
import icu.junyao.back.res.ArticleContentRes;
import icu.junyao.back.service.ArticleContentService;
import org.springframework.beans.BeanUtils;
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
        BeanUtils.copyProperties(articleContent, articleContentRes);

        return articleContentRes;
    }
}
