package cn.antigenmhc.otaku.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/2 21:21
 * @Version: 1.0
 **/
public class GeneratorCode {

    @Test
    public void genCode() {

        String moduleName = "trade";
        String prefix = "trade";

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("antigenmhc");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(true); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");    //去掉Service接口的首字母I,默认为I%sService
        gc.setIdType(IdType.ASSIGN_ID); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启 Swagger2 注解添加
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/er_ci_yuan_trade?" +
                "serverTimezone=GMT%2B8&useUnicode=true&" +
                "characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.antigenmhc.otaku.service");
        pc.setModuleName(moduleName); //模块名
        pc.setController("controller");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略为驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //设置表前缀不生成
        //比如 otaku_aka，会去掉 otaku_，将 _ 后的首字母大写，只生成 Aka 的实体类
        strategy.setTablePrefix(prefix + "_");

        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok 模型 @Accessors(chain = true) setter 链式编程
        strategy.setEntityLombokModel(true);

        //逻辑删除字段名
        strategy.setLogicDeleteFieldName("is_deleted");
        //去掉布尔值的is_前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);

        //自动填充
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        //乐观锁
        strategy.setVersionFieldName("version");
        //restful api风格控制器，设置为 @RestController
        strategy.setRestControllerStyle(true);
        //url 中驼峰转连字符：adminCollector -> admin-collector
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        //设置BaseEntity
        strategy.setSuperEntityClass("cn.antigenmhc.otaku.service.base.pojo.BasePojo");
        // 填写BaseEntity中的公共字段
        strategy.setSuperEntityColumns("id", "gmt_create", "gmt_modified");

        // 6、执行
        mpg.execute();
    }
}
