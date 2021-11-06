package icu.junyao.classroom.res;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author johnson
 * @date 2021-11-06
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CourseDetailRes extends CourseRes {
    private String teacherName;
    private String teacherIntro;
    private String teacherAvatar;
    private String subjectName;
    private String subjectParentName;
}
