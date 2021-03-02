package cn.lghuntfor.generator.code.common;

/**
 * 常量
 * @author lghuntfor
 * @date 2020/11/14
 */
public interface Const {

    /** 必要的一些配置key, 缺少将无法正常运行 */
    String KEY_DB_URL = "dbUrl";
    String KEY_DB_USER = "dbUser";
    String KEY_DB_PASS = "dbPass";
    String KEY_TABLE_NAMES = "tableNames";

    /** 其他的一些配置key */
    String KEY_DB_NAME = "dbName";
    String KEY_BASE_PROPS = "baseProps";
    String KEY_PREFIX = "prefix";
    String KEY_TEMPLATE_DIR = "templateDir";
    String KEY_OUTPUT_DIR = "outputDir";

    /** 默认的配置文件名称 */
    String DEFAULT_CONFIG_NAME = "generator.properties";
    String QUERY_TABLE_TEMPLATE_FILE = "sql/query_table_info.tl";
    String QUERY_COLUMN_TEMPLATE_FILE = "sql/query_column_info.tl";

    String DEFAULT_TEMPLATE_DIR = "template";
    String DEFAULT_OUTPUT_DIR = "code";
    String TEMPLATE_SYMBOL = "$";

    String TEMPLATE_SUFFIX_TL = ".tl";

    /** 文件分隔符 */
    String WIN_FILE_SEPARATOR = "\\";
    String WIN_FILE_SEPARATOR_REGEX = "\\\\";
    String WIN_FILE_SEPARATOR_ESCAPE = "%5C";
    String UNIX_FILE_SEPARATOR = "/";
}
