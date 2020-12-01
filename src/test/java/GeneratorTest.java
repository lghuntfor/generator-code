import cn.hutool.core.util.StrUtil;
import cn.lghuntfor.generator.code.common.Const;
import cn.lghuntfor.generator.code.utils.FileUtils;
import cn.lghuntfor.generator.code.utils.GeneratorUtils;
import cn.lghuntfor.generator.code.utils.JarUtils;
import cn.lghuntfor.generator.code.utils.TemplateUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 * @author lghuntfor
 * @date 2020/11/14
 */
public class GeneratorTest {

    @Test
    public void testJarUtils() {
        System.out.println(JarUtils.getJarName());
        System.out.println(JarUtils.getJarPath());
        System.out.println(JarUtils.getJarDir());
    }

    @Test
    public void testTemplate() {
        Map<String, Object> params = new HashMap<>();
        params.put("dbName", "euaa_devdb");
        params.put("tableName", "rbac_user");
        String[] tableNames = {"rbac_user", "rbac_role"};
        params.put("tableNames", "'" + StrUtil.join("','", tableNames) + "'");
        params.put("tableNames", tableNames);
        String sql = TemplateUtils.parseClasspathFile(Const.QUERY_TABLE_TEMPLATE_FILE, params);
        System.out.println(sql);

        String columnSql = TemplateUtils.parseClasspathFile(Const.QUERY_COLUMN_TEMPLATE_FILE, params);
        System.out.println(columnSql);
    }


    /**
     * 代码生成器测试
     * @throws Exception
     */
    @Test
    public void testGenerator() throws Exception {
        GeneratorUtils.startGenerator(JarUtils.getJarDir() + FileUtils.getFileSeparator() + Const.DEFAULT_CONFIG_NAME);
    }
}
