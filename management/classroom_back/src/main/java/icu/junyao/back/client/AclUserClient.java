package icu.junyao.back.client;

import icu.junyao.back.res.AclUserDetailRes;
import icu.junyao.common.entity.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author johnson
 * @date 2022-02-26
 */
@Component
@FeignClient("service-acl")
public interface AclUserClient {

    /**
     * 通过管理用户id获取用户信息
     *
     * @param id 管理用户id
     * @return {@link AclUserDetailRes}
     */
    @ApiOperation("获取单个用户信息")
    @GetMapping("rabbit/acl/acl-user/{id}")
    R<AclUserDetailRes> userDetails(@PathVariable String id);
}
