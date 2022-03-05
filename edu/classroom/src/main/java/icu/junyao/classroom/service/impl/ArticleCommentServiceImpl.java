package icu.junyao.classroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.classroom.entity.ArticleComment;
import icu.junyao.classroom.entity.CourseComment;
import icu.junyao.classroom.entity.User;
import icu.junyao.classroom.mapper.ArticleCommentMapper;
import icu.junyao.classroom.mapper.UserMapper;
import icu.junyao.classroom.req.ArticleCommentReq;
import icu.junyao.classroom.req.PageArticleCommentReq;
import icu.junyao.classroom.res.ArticleCommentRes;
import icu.junyao.classroom.res.CourseCommentRes;
import icu.junyao.classroom.service.ArticleCommentService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章评论表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2022-01-18
 */
@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    private final UserMapper userMapper;

    @Override
    public PageResult<ArticleCommentRes> pageArticleComment(PageArticleCommentReq pageArticleCommentReq) {
        IPage<ArticleComment> articleCommentPage = new Page<>(pageArticleCommentReq.getPage(), pageArticleCommentReq.getPageSize());
        LambdaQueryWrapper<ArticleComment> articleCommentLambdaQueryWrapper = Wrappers.lambdaQuery();
        articleCommentLambdaQueryWrapper.eq(ArticleComment::getArticleId, pageArticleCommentReq.getArticleId())
                .orderByDesc(ArticleComment::getCreatedTime);
        super.page(articleCommentPage, articleCommentLambdaQueryWrapper);

        List<ArticleCommentRes> articleCommentResList = BeanUtil
                .copyToList(articleCommentPage.getRecords(), ArticleCommentRes.class, CopyOptions.create());

        // 没有评论直接返回空
        if (CollUtil.isEmpty(articleCommentResList)) {
            return new PageResult<>(0L, new ArrayList<>());
        }
        // 加入用户信息
        List<String> userIdList = articleCommentResList.stream().map(ArticleCommentRes::getUserId)
                .collect(Collectors.toList());
        List<User> userList = userMapper.selectBatchIds(userIdList);
        Map<String, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, u -> u));
        articleCommentResList.forEach(c -> {
            User user = userMap.get(c.getUserId());
            c.setNickname(user.getNickname());
            c.setAvatar(user.getAvatar());
        });
        return new PageResult<>(articleCommentPage.getTotal(), articleCommentResList);
    }

    @Override
    public void saveComment(ArticleCommentReq articleCommentReq, DecodedJWT decodedJwt) {
        // 校验解析jwt
        if (decodedJwt == null) {
            throw BusinessResponseEnum.TOKEN_ERROR.newException();
        }

        String id = decodedJwt.getClaim("id").asString();

        ArticleComment articleComment = new ArticleComment();
        BeanUtils.copyProperties(articleCommentReq, articleComment);
        articleComment.setUserId(id);

        super.save(articleComment);
    }
}
