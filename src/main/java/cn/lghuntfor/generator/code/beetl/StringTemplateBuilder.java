package cn.lghuntfor.generator.code.beetl;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;

/**
 * 字符串模版单例类
 * @author liaogang
 * @date 2020/11/3 09:56
 */
public class StringTemplateBuilder {

    private static GroupTemplate STRING_GROUP_TEMPLATE = null;

    public static GroupTemplate getStringTemplate() {
        if (STRING_GROUP_TEMPLATE == null) {
            synchronized (StringTemplateBuilder.class) {
                if (STRING_GROUP_TEMPLATE == null) {
                    STRING_GROUP_TEMPLATE = createStringGroupTemplate();
                }
            }
        }
        return STRING_GROUP_TEMPLATE;
    }

    /** 创建字符串的解析模版 */
    private static GroupTemplate createStringGroupTemplate() {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        return gt;
    }
}
