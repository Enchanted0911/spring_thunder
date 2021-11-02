package icu.junyao.back.res;

import lombok.Data;

/**
 * @author johnson
 * @date 2021-11-02
 */
@Data
public class CoursePublishInfoRes {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
