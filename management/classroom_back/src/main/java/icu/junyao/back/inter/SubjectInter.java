package icu.junyao.back.inter;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author johnson
 * @date 2021-11-03
 */
@Data
public class SubjectInter {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
