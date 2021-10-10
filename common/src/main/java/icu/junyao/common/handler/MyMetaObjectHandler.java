package icu.junyao.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author johnson
 * @date 2021-10-02
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * MP功能 自动插入创建时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * MP 功能 自动插入修改时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime::now, LocalDateTime.class);
    }
}
