package com.example.demo.generate;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class MysqlGenerator {
	
	// 数据库的连接
	private static final String URL = "jdbc:mysql://172.17.0.2:3306/db1?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
	
	// 数据库的用户名
	private static final String USER = "root";
	
	// 数据库的密码
	private static final String PASSWORD = "123456";
	
	// 数据库的驱动
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	// 需要生成代码的bean的表名（多个使用 , 分割）
	private static final String TABLES = "stu";
	
	// base包名
	private static final String BASE_PACKAGE = "com.example.demo";
	
	// 实体类包名
	private static final String ENTITY_PACKAGE = "pojo";
	
	// service类包名
	private static final String SERVICE_PACKAGE = "service";
	
	// service实现类包名
	private static final String SERVICE_IMP_PACKAGE = "service.impl";
	
	// 使用人
	private static final String AUTHOR = "chengwei";
	
	// 是否使用lombok
	private static final boolean USE_LOMBOK = false;
	
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        setGlobalConfig(mpg);

        // 数据源配置
        setDataSource(mpg);
        
        // 包配置
        setPackage(mpg);
        

//        // TODO: 自定义需要填充的字段
//        List<TableFill> tableFillList = new ArrayList<>();
//        //如 每张表都有一个创建时间、修改时间
//        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
//        //修改时，修改时间会修改，
//        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
//        //使用公共字段填充功能，就可以实现，自动按场景更新了。
//        //如下是配置
//        TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
//        TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
//        tableFillList.add(createField);
//        tableFillList.add(modifiedField);
        
        // 自定义配置
        setInjectionConfig(mpg);

        // 策略配置
        setStrategyConfig(mpg);
        
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
    
    // 自定义配置 
    private static void setInjectionConfig(AutoGenerator mpg) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        
        List<FileOutConfig> focList = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
	}

	private static void setStrategyConfig(AutoGenerator mpg) {
    	StrategyConfig strategy = new StrategyConfig();
        
    	// 数据库表映射到实体的命名策略，驼峰原则
        strategy.setNaming(NamingStrategy.underline_to_camel);
       
        // 字数据库表字段映射到实体的命名策略，驼峰原则
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
       
        // 实体是否生成 serialVersionUID
        strategy.setEntitySerialVersionUID(false);
        
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        
        // 使用lombok
        strategy.setEntityLombokModel(USE_LOMBOK);
        
        // 指定生成的bean的数据库表名
        strategy.setInclude(TABLES.split(","));
        
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);

//      // 设置逻辑删除键
//      strategy.setLogicDeleteFieldName("del_flag");
        
        mpg.setStrategy(strategy);
	}

	private static void setPackage(AutoGenerator mpg) {
    	PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(BASE_PACKAGE);
        pc.setEntity(ENTITY_PACKAGE);
        pc.setService(SERVICE_PACKAGE);
        pc.setServiceImpl(SERVICE_IMP_PACKAGE);
        mpg.setPackageInfo(pc);
	}

	private static void setDataSource(AutoGenerator mpg) {
    	DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER);
        dsc.setUsername(USER);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);
	}

	private static void setGlobalConfig(AutoGenerator mpg) {
    	GlobalConfig gc = new GlobalConfig();
    	
    	// 当前项目位置
    	String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");

        // 注意 %s 会自动填充表实体属性
        // 设置使用人
        gc.setAuthor(AUTHOR);
        gc.setOpen(true);
        
        // service 命名方式
        gc.setServiceName("%sService");
        
        // service impl 命名方式
        gc.setServiceImplName("%sServiceImpl");
        
        // 自定义dao 命名方式
        gc.setMapperName("%sMapper");
        
        // 自定义xmlMapper 命名方式
        gc.setXmlName("%sMapper");

        // 是否覆盖文件
        gc.setFileOverride(true);
        
        // 
        gc.setActiveRecord(true);
        
        // XML 二级缓存
        gc.setEnableCache(false);
        
        // XML ResultMap
        gc.setBaseResultMap(true);
       
        // XML columList
        gc.setBaseColumnList(true);
        
        mpg.setGlobalConfig(gc);
    }
    
}
