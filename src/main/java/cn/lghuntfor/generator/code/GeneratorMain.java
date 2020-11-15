package cn.lghuntfor.generator.code;

import cn.lghuntfor.generator.code.common.Const;
import cn.lghuntfor.generator.code.utils.FileUtils;
import cn.lghuntfor.generator.code.utils.GeneratorUtils;
import cn.lghuntfor.generator.code.utils.JarUtils;

/**
 * 启动类
 * @author lghuntfor
 * @date 2020/11/14
 */
public class GeneratorMain {

    public static void main(String[] args) throws Exception {
        System.out.println("==================== start ====================");
        String configPath = JarUtils.getJarDir() + FileUtils.getFileSeparator() + Const.DEFAULT_CONFIG_NAME;
        GeneratorUtils.startGenerator(configPath);
        System.out.println("==================== end ====================");
    }

}
