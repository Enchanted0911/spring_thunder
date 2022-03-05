package icu.junyao.classroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.entity.ArticleContent;
import icu.junyao.classroom.res.ArticleContentRes;

/**
 * <p>
 * 文章内容 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface ArticleContentService extends IService<ArticleContent> {

    /**
     * 获取文章内容详情
     *
     * @param id 文章内容id
     * @return {@link ArticleContentRes}
     */
    ArticleContentRes articleContentDetails(String id);
}
