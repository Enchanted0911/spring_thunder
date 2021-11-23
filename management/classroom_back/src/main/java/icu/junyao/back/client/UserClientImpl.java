package icu.junyao.back.client;

import icu.junyao.common.entity.R;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

/**
 * @author johnson
 * @date 2021-11-23
 */
public class UserClientImpl implements UserClient {
    @Override
    public R<Integer> statisticRegister(String date) {
        return R.fail("统计异常");
    }
}
