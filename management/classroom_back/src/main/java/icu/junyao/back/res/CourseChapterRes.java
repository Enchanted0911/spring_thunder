package icu.junyao.back.res;

import lombok.Data;

import java.util.List;

/**
 * @author johnson
 * @date 2021-10-24
 */
@Data
public class CourseChapterRes {
    private String id;
    private String title;
    private List<CourseSubsectionRes> courseSubsectionResList;
}
