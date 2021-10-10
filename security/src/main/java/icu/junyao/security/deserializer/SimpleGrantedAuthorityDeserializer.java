package icu.junyao.security.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

/**
 * SimpleGrantedAuthority没有无参构造, json反序列化会报异常, 自定义反序列化器可解决
 *
 * @author johnson
 * @date 2021-10-02
 */
public class SimpleGrantedAuthorityDeserializer extends StdDeserializer<SimpleGrantedAuthority> {

    public SimpleGrantedAuthorityDeserializer() {
        super(SimpleGrantedAuthority.class);
    }
    @Override
    public SimpleGrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode tree = jsonParser.getCodec().readTree(jsonParser);
        return new SimpleGrantedAuthority(tree.get("authority").textValue());
    }
}
