package icu.junyao.classroom.client;

import icu.junyao.common.entity.R;

/**
 * @author johnson
 * @date 2021-11-06
 */
public class OrderClientImpl implements OrderClient {
    @Override
    public R<Boolean> ifBought(String courseId, String memberId) {
        return R.fail("查询失败!");
    }
}
