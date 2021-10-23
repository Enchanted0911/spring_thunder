package icu.junyao.back;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MyTest {
    @Test
    void codeGenerate() {
// 1、全局配置
        //构建全局配置对象
        GlobalConfig globalConfig = new GlobalConfig();
        // 获取当前用户的目录
        String projectPath = "D:/IdeaProjects/";
        globalConfig
                // 输出文件路径
                .setOutputDir(projectPath + "springThunder/management/classroom_back/src/main/java")
                // 设置作者名字
                .setAuthor("johnson")
                // 是否打开资源管理器
                .setOpen(false)
                // 是否覆盖原来生成的
                .setFileOverride(false)
                // 主键策略
                .setIdType(IdType.ASSIGN_ID)
                // 生成resultMap
                .setBaseResultMap(true)
                // XML中生成基础列
                .setBaseColumnList(true)
                // 生成的service接口名字首字母是否为I，这样设置就没有I
                .setServiceName("%sService")
                //定义生成的实体类中日期类型
//                .setDateType(DateType.ONLY_DATE)
                //开启Swagger2模式
                .setSwagger2(true);

        // 2、数据源配置
        // 创建数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setUrl("jdbc:mysql://localhost:3306/springthunder?serverTimezone=Asia/Shanghai")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("Vanessa1020Ives")
                .setDbType(DbType.MYSQL);

        // 3、包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig
                .setParent("icu.junyao.back")
                .setEntity("entity")
                .setController("controller")
                .setService("service")
                .setMapper("mapper");

        // 4、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                // 开启全局大写命名
                .setCapitalMode(true)
                // 设置要映射的表
                .setInclude("t_banner", "t_order", "t_pay_log", "t_statistics_daily", "t_subject", "t_course", "t_course_chapter", "t_course_collect", "t_course_comment", "t_course_description", "t_course_subsection", "t_teacher", "t_user")
                // 下划线到驼峰的命名方式
                .setNaming(NamingStrategy.underline_to_camel)
                // 下划线到驼峰的命名方式
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //生成实体时去掉表前缀
                .setTablePrefix("t_")
                // 是否使用lombok
                .setEntityLombokModel(true)
                // 是否开启rest风格
                .setRestControllerStyle(true)
                // url驼峰转连字符
                .setControllerMappingHyphenStyle(true);


        // 5、自定义配置（配置输出xml文件到resources下）
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/springThunder/management/classroom_back/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        // 6、整合配置
        // 构建代码自动生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator
                // 将全局配置放到代码生成器对象中
                .setGlobalConfig(globalConfig)
                // 将数据源配置放到代码生成器对象中
                .setDataSource(dataSourceConfig)
                // 将包配置放到代码生成器对象中
                .setPackageInfo(packageConfig)
                // 将策略配置放到代码生成器对象中
                .setStrategy(strategyConfig)
//                // 将自定义配置放到代码生成器对象中
                .setCfg(cfg)
                // 执行！
                .execute();
    }
}
