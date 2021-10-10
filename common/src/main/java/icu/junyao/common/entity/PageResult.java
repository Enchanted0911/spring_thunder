package icu.junyao.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author johnson
 * @date 2021-10-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "总页数")
    private Long totalPage = 0L;

    /**
     * 当前页码展示的记录数
     */
    @ApiModelProperty(value = "当前页码展示的记录数")
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
