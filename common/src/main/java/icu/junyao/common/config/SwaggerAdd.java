package icu.junyao.common.config;

import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

/**
 * 手动添加swagger接口，如登录接口等
 *
 * @author johnson
 * @date 2021-10-02
 */
@Component
public class SwaggerAdd implements ApiListingScannerPlugin {
    /**
     * Implement this method to manually add ApiDescriptions
     * 实现此方法可手动添加ApiDescriptions
     *
     * @param context - Documentation context that can be used infer documentation context
     * @return List of {@link ApiDescription}
     * @see ApiDescription
     */
    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        Collection<RequestParameter> parameters = new ArrayList<>();


        parameters.add(new RequestParameterBuilder().name("username").in(ParameterType.BODY).required(true).build());
        parameters.add(new RequestParameterBuilder().name("password").in(ParameterType.BODY).required(true).build());
        Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("用户名密码登录")
                .notes("username/password登录")
                // 接收参数格式
                .consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                // 返回参数格式
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                .tags(Sets.newHashSet("登录"))
                .requestParameters(parameters)
                .build();
        List<Operation> operations = Collections.singletonList(usernamePasswordOperation);
        ApiDescription loginApiDescription = new ApiDescription("login", "/login", "登录接口", "",
                operations, false);

        return Collections.singletonList(loginApiDescription);
    }

    /**
     * 是否使用此插件
     *
     * @param documentationType swagger文档类型
     * @return true 启用
     */
    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}