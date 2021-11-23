package icu.junyao.back.res;

import lombok.Data;

import java.util.List;

/**
 * @author johnson
 * @date 2021-11-23
 */
@Data
public class StatisticDailyRes {
    List<String> dateCalculatedList;
    List<Integer> numDataList;
}
