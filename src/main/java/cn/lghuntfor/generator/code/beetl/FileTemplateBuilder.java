package cn.lghuntfor.generator.code.beetl;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.FileResourceLoader;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件模版单例类
 * @author liaogang
 * @date 2020/11/3 09:56
 */
public class FileTemplateBuilder {

    private static Map<String, GroupTemplate> FILE_GROUP_TEMPLATE_MAP = new ConcurrentHashMap<>();

    public static GroupTemplate getFileTemplate(String rootDir) {
        if (!FILE_GROUP_TEMPLATE_MAP.containsKey(rootDir)) {
            synchronized (FileTemplateBuilder.class) {
                if (!FILE_GROUP_TEMPLATE_MAP.containsKey(rootDir)) {
                    FILE_GROUP_TEMPLATE_MAP.put(rootDir, createFileGroupTemplate(rootDir));
                }
            }
        }
        return FILE_GROUP_TEMPLATE_MAP.get(rootDir);
    }

    /** 创建字符串的解析模版 */
    private static GroupTemplate createFileGroupTemplate(String rootDir) {
        FileResourceLoader fileResourceLoader = new FileResourceLoader(rootDir);
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GroupTemplate gt = new GroupTemplate(fileResourceLoader, cfg);
        return gt;
    }
}
