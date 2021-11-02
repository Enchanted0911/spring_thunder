package icu.junyao.back.res;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author johnson
 * @date 2021-11-02
 */
@Data
public class CourseBaseInfoRes {
    private String id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;
}
