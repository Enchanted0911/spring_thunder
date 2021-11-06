package icu.junyao.classroom.client;

import icu.junyao.common.entity.R;

/**
 * @author wu
 */
public class OrderClientImpl implements OrderClient {
    @Override
    public R<Boolean> ifBought(String courseId, String memberId) {
        return R.data(false);
    }
}
