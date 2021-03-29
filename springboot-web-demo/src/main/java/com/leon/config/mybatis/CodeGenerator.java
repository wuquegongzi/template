package com.leon.config.mybatis;

import cn.hutool.core.util.StrUtil;
import com.leon.common.utils.GsonUtils;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author minglei.chen
 * @date 2020/1/2 8:27 下午
 * @description 代码生成工具
 * 用法：执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {

        String driver = null;
        String url = null;
        String user = null;
        String pass = null;
        String author = null;
        String packageParent = null;
        String mapperDir = null;
        String entityClass = null;
        String controllerClass = null;
        String entityColumnsStr = null;


//        FileSystemResource fileSystemResource =new FileSystemResource("classpath:generator/generator.properties");
        ClassPathResource classPathResource = new ClassPathResource("generator/generator.properties");
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(classPathResource);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(null == properties){
            System.out.println("请检查generator.properties路径，未找到Properties！");
            return;
        }

        driver = properties.getProperty("database.driver", "com.mysql.cj.jdbc.Driver");
        url = properties.getProperty("database.url", "jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        user = properties.getProperty("database.user", "root");
        pass = properties.getProperty("database.pass", "root");
        author = properties.getProperty("generator.author", "admin");
        packageParent = properties.getProperty("generator.package.parent", "com.leon.biz");
        mapperDir  = properties.getProperty("generator.package.mapper.dir", "/src/main/resources/mapper/");
        entityClass = properties.getProperty("generator.package.super.entityclass", "com.leon.base.model.BaseEntity");
        controllerClass = properties.getProperty("generator.package.super.controllerclass", "");
        entityColumnsStr = properties.getProperty("generator.package.super.entitycolumns", "");
        String[] entityColumns = StrUtil.isEmpty(entityColumnsStr)?new String[0]:entityColumnsStr.split(",");

        System.out.println(driver+"\n"+url+"\n"+user+"\n"+pass+"\n"+author
                +"\n"+packageParent+"\n"+mapperDir+"\n"+entityClass+"\n"+controllerClass+"\n"+ GsonUtils.gsonString(entityColumns));

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driver);
        dsc.setUsername(user);
        dsc.setPassword(pass);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(packageParent);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        String finalMapperDir = mapperDir;
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + finalMapperDir + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        if(StrUtil.isNotEmpty(entityClass)){
            strategy.setSuperEntityClass(entityClass);
        }
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        if(StrUtil.isNotEmpty(controllerClass)){
            strategy.setSuperControllerClass(controllerClass);
        }
        if(entityColumns.length > 0){
            // 写于父类中的公共字段
            strategy.setSuperEntityColumns(entityColumns);
        }

        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
