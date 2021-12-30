package icu.junyao.classroom.res;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author johnson
 * @date 2021-11-03
 */
@Data
public class SubjectOneRes {
    private String id;
    private String title;
    List<SubjectTwoRes> subjectTwoResList = new ArrayList<>();
}
