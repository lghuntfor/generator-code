package cn.lghuntfor.generator.code.utils;

import cn.lghuntfor.generator.code.beetl.ClasspathTemplateBuilder;
import cn.lghuntfor.generator.code.beetl.FileTemplateBuilder;
import cn.lghuntfor.generator.code.beetl.StringTemplateBuilder;
import cn.lghuntfor.generator.code.common.Const;
import cn.lghuntfor.generator.code.model.ConfigInfo;
import cn.lghuntfor.generator.code.model.TableInfo;
import cn.lghuntfor.generator.code.model.TemplateInfo;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模版解析工具类
 * @author liaogang
 * @date 2020/11/2 15:47
 */
public class TemplateUtils {

    /**
     * 字符串模版解析工具
     * @param templateText 模版内容
     * @param params 解析参数
     * @return java.lang.String
     */
    public static String parseText(String templateText, Map params) {
        GroupTemplate groupTemplate = StringTemplateBuilder.getStringTemplate();
        Template template = groupTemplate.getTemplate(templateText);
        if (params != null) {
            template.binding(params);
        }
        return template.render();
    }

    /**
     * 文件模版解析工具
     * @param rootDir 指定目录
     * @param path 基于目录的文件相对路径
     * @param params 解析参数
     * @return java.lang.String
     */
    public static String parseFile(String rootDir, String path, Map params) {
        GroupTemplate groupTemplate = FileTemplateBuilder.getFileTemplate(rootDir);
        Template template = groupTemplate.getTemplate(path);
        if (params != null) {
            template.binding(params);
        }
        return template.render();
    }

    /**
     * classpath文件模版解析工具
     * @param classpath 基于classpath下的文件相对路径
     * @param params 解析参数
     * @return java.lang.String
     */
    public static String parseClasspathFile(String classpath, Map params) {
        GroupTemplate groupTemplate = ClasspathTemplateBuilder.getClasspathTemplate();
        Template template = groupTemplate.getTemplate(classpath);
        if (params != null) {
            template.binding(params);
        }
        return template.render();
    }


    /**
     * 获取指定目录下的所有模版文件信息
     * @return
     */
    public static List<TemplateInfo> getTemplateInfo(String templateDir) {
        List<TemplateInfo> templateList = new ArrayList<>();
        FileUtils.recursionFile(templateDir, (file) -> {
            if (file.isDirectory()) {
                return;
            }
            TemplateInfo template = new TemplateInfo();
            template.setTemplateFile(file);
            String path = file.getAbsolutePath();
            template.setTemplatePath(path);
            template.setTemplateRelativePath(path.replace(templateDir, ""));
            templateList.add(template);
        });
        return templateList;
    }

}
