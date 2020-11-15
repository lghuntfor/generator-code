package cn.lghuntfor.generator.code.beetl;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;

/**
 * classpath模版单例类
 * @author liaogang
 * @date 2020/11/3 09:56
 */
public class ClasspathTemplateBuilder {

    private static GroupTemplate GROUP_TEMPLATE = null;

    public static GroupTemplate getClasspathTemplate() {
        if (GROUP_TEMPLATE == null) {
            synchronized (ClasspathTemplateBuilder.class) {
                if (GROUP_TEMPLATE == null) {
                    GROUP_TEMPLATE = createClasspathGroupTemplate();
                }
            }
        }
        return GROUP_TEMPLATE;
    }

    /** 创建字符串的解析模版 */
    private static GroupTemplate createClasspathGroupTemplate() {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
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
