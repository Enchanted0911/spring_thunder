package icu.junyao.back.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2022-01-20
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ArticleEditReq extends ArticleReq {
    private String id;
}
