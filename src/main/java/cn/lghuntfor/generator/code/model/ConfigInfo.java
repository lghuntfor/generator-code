package cn.lghuntfor.generator.code.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置信息封装
 * 来源于配置文件
 * @author lghuntfor
 * @date 2020/11/14
 */
@Data
public class ConfigInfo {

    /** 数据库链接 */
    private String dbUrl;

    /** 数据库连接账号 */
    private String dbUser;

    /** 数据库密码 */
    private String dbPass;

    /** 数据库 */
    private String dbName;

    /** 表名转实体类名时, 需要去掉的表前缀 */
    private String prefix;

    /** 需要生成的表名 */
    private List<String> tableNames;

    /** 模版文件的根目录, 可配置, 默认为当前jar所在的template目录 */
    private String templateDir;

    /** 代码生成文件后存放的根目录, 可配置, 默认为当前jar所在的code目录 */
    private String outputDir;

    /** 配置文件中的全部内容 */
    private Map props;

}
