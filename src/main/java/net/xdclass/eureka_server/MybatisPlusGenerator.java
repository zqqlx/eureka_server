package net.xdclass.eureka_server;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MybatisPlusGenerator
 * @Description TODO
 * @Author Administrator
 * @dATE 2019/6/30 9:48
 * @Version 1.0
 **/
public class MybatisPlusGenerator {
    /**
     * <p>
     * MySQL 生成演示
     * </p >
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        File file = new File("");
        String path = file.getAbsolutePath();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path + File.separator + "src" + File.separator + "main" + File.separator + "java");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("01368991");// TODO 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("I%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);

        // TODO
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/ruserver");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });//
        // 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "red_packet_info","red_packet_record"}); // TODO 需要生成的表
        strategy.setEntityLombokModel(true);
        mpg.setStrategy(strategy);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 调整 问题同样IService生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            public String outputFile(TableInfo tableInfo) {
                return path + File.separator + "src" + File.separator + "main" + File.separator + "resources"   + File.separator + "mapper"+ tableInfo.getEntityName()
                        + "Mapper.xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("net.xdclass.eureka_server");
        pc.setModuleName("");
        pc.setEntity("domain.entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("domain.mapper");
        mpg.setPackageInfo(pc);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        tc.setController(null);
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }
}
