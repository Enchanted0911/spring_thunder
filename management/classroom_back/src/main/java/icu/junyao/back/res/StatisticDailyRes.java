package icu.junyao.back.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author johnson
 * @date 2021-11-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDailyRes {
    List<String> dateCalculatedList;
    List<Integer> numDataList;
}
