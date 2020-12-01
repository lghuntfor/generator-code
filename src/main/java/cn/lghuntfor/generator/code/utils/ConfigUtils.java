package cn.lghuntfor.generator.code.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.lghuntfor.generator.code.common.Const;
import cn.lghuntfor.generator.code.model.ConfigInfo;

import java.io.File;
import java.util.Optional;

/**
 * 配置信息工具类
 * @author lghuntfor
 * @date 2020/11/14
 */
public class ConfigUtils {

    /**
     * 从properties中获取config信息
     * @param props
     * @return
     */
    public static ConfigInfo getConfigInfo(Props props) {
        ConfigInfo configInfo = new ConfigInfo();

        String tableNames = props.getStr(Const.KEY_TABLE_NAMES);
        if (StrUtil.isBlank(tableNames)) {
            throw new RuntimeException("缺少必要的配置: tableNames");
        }
        tableNames = tableNames.trim();
        configInfo.setTableNames(StrUtil.split(tableNames, ',', true, true));

        String baseProps = props.getStr(Const.KEY_BASE_PROPS);
        configInfo.setBaseProps(StrUtil.split(baseProps, ',', true, true));

        String dbUrl = props.getStr(Const.KEY_DB_URL);
        String dbUser = props.getStr(Const.KEY_DB_USER);
        String dbPass = props.getStr(Const.KEY_DB_PASS);
        String dbName = props.getStr(Const.KEY_DB_NAME);

        configInfo.setDbUrl(dbUrl);
        configInfo.setDbUser(dbUser);
        configInfo.setDbPass(dbPass);
        configInfo.setDbName(dbName);

        String templateDir = props.getStr(Const.KEY_TEMPLATE_DIR);
        if (StrUtil.isBlank(templateDir)) {
            templateDir = JarUtils.getJarDir() + FileUtils.getFileSeparator() + Const.DEFAULT_TEMPLATE_DIR;
        } else {
            if (!templateDir.startsWith(FileUtils.getFileSeparator())) {
                /** 相对路径 */
                templateDir = JarUtils.getJarDir() + FileUtils.getFileSeparator() + templateDir;
            }
        }
        if (!templateDir.endsWith(FileUtils.getFileSeparator())) {
            templateDir = templateDir + FileUtils.getFileSeparator();
        }
        configInfo.setTemplateDir(templateDir);

        String outputDir = props.getStr(Const.KEY_OUTPUT_DIR);
        if (StrUtil.isBlank(outputDir)) {
            outputDir = JarUtils.getJarDir() + FileUtils.getFileSeparator() + Const.DEFAULT_OUTPUT_DIR;
        } else {
            if (!outputDir.startsWith(FileUtils.getFileSeparator())) {
                /** 相对路径 */
                outputDir = JarUtils.getJarDir() + FileUtils.getFileSeparator() + outputDir;
            }
        }
        if (!outputDir.endsWith(FileUtils.getFileSeparator())) {
            outputDir = outputDir + FileUtils.getFileSeparator();
        }
        configInfo.setOutputDir(outputDir);

        String prefix = props.getStr(Const.KEY_PREFIX);
        configInfo.setPrefix(Optional.ofNullable(prefix).orElse(""));

        configInfo.setProps(props);

        return configInfo;
    }

    /**
     * 获取配置文件路径, 并检测是否存在
     */
    public static String getConfigPath(String configPath) {
        if (StrUtil.isBlank(configPath)) {
            configPath = JarUtils.getJarDir() + "/" + Const.DEFAULT_CONFIG_NAME;
        }
        File file = new File(configPath);
        if (!file.exists()) {
            throw new RuntimeException("配置文件不存在");
        }
        return configPath;
    }
}
