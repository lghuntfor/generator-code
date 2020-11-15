package cn.lghuntfor.generator.code.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;
import cn.lghuntfor.generator.code.common.Const;
import cn.lghuntfor.generator.code.model.CodeInfo;
import cn.lghuntfor.generator.code.model.ConfigInfo;
import cn.lghuntfor.generator.code.model.TableInfo;
import cn.lghuntfor.generator.code.model.TemplateInfo;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器工具类
 * @author lghuntfor
 * @date 2020/11/14
 */
public class GeneratorUtils {

    /**
     * 开始生成代码
     * @param configPath
     * @throws SQLException
     */
    public static void startGenerator(String configPath) throws SQLException {
        System.out.println("configPath = " + configPath);
        configPath = ConfigUtils.getConfigPath(configPath);

        /** 获取基础配置 */
        Props props = PropsUtil.get(configPath);
        ConfigInfo config = ConfigUtils.getConfigInfo(props);

        /** 获取所有要生成的表与对应列的信息 */
        List<TableInfo> tableList = DbUtils.getTableInfo(config);

        /** 获取所有模版信息 */
        List<TemplateInfo> templateList = TemplateUtils.getTemplateInfo(config.getTemplateDir());
        if (CollUtil.isEmpty(templateList)) {
            return;
        }

        /** 根据模版, 配置以及数据库信息, 生成代码并输出结果文件 */
        if (CollUtil.isEmpty(tableList)) {
            /** 没有数据库相关代码生成时 */
            output(null, config, templateList);
        } else {
            tableList.forEach(table -> output(table, config, templateList));
        }
    }


    private static void output(TableInfo table, ConfigInfo config, List<TemplateInfo> templateList) {
        String outputDir = config.getOutputDir();

        Map params = new HashMap();
        /** 数据库表与列的信息 */
        if (table != null) {
            params.put("data", table);
        }
        params.putAll(config.getProps());

        /** 解析模版文件(文件名, 文件目录) */
        parseTemplateFile(config, templateList, params);

        List<CodeInfo> codeList = new ArrayList<>(templateList.size());
        templateList.forEach(template -> {
            /** 解析模版文件内容 */
            String content = TemplateUtils.parseFile(config.getTemplateDir(), template.getTemplateRelativePath(), params);

            CodeInfo codeInfo = new CodeInfo();
            codeInfo.setContent(content);
            codeInfo.setPath(outputDir + template.getParseRelativePath());
            codeList.add(codeInfo);
        });

        /** 输出结果文件 */
        codeList.forEach(code -> {
            FileUtil.writeUtf8String(code.getContent(), code.getPath());
        });
    }

    /**
     * 解析模版文件(文件名, 文件目录)
     * @param config
     * @param templateList
     * @param params
     */
    private static void parseTemplateFile(ConfigInfo config, List<TemplateInfo> templateList, Map params) {
        templateList.forEach(template -> {
            File file = template.getTemplateFile();
            /** 目录或文件名称解析后的结果 */
            String dir = file.getParent();
            String fileName = file.getName();

            fileName = TemplateUtils.parseText(fileName, params);
            if (fileName.endsWith(Const.TEMPLATE_SUFFIX_TL)) {
                fileName = fileName.substring(0, fileName.lastIndexOf(Const.TEMPLATE_SUFFIX_TL));
            }
            dir = TemplateUtils.parseText(dir, params);

            String parsePath = dir.replaceAll("\\.", FileUtils.getFileSeparator())
                    + FileUtils.getFileSeparator() + fileName;
            template.setParseRelativePath(parsePath.replace(config.getTemplateDir(), ""));
        });
    }

}
