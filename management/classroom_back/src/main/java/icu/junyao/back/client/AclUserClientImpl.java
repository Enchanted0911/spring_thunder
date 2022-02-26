package icu.junyao.back.client;

import icu.junyao.back.res.AclUserDetailRes;
import icu.junyao.common.entity.R;

/**
 * @author johnson
 * @date 2022-02-26
 */
public class AclUserClientImpl implements AclUserClient {

    @Override
    public R<AclUserDetailRes> userDetails(String id) {
        return R.fail("获取用户信息异常");
    }
}
