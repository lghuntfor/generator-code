package cn.lghuntfor.generator.code.utils;

import cn.hutool.core.util.URLUtil;

import java.io.File;

/**
 * jar相关工具类
 * @author lghuntfor
 * @date 2020/11/14
 */
public class JarUtils {

    /**
     * 获取jar绝对路径
     * @return
     */
    public static String getJarPath() {
        File file = getFile();
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取jar目录
     * @return
     */
    public static String getJarDir() {
        File file = getFile();
        if (file != null) {
            return file.getParent();
        }
        return null;
    }

    /**
     * 获取jar包名
     * @return
     */
    public static String getJarName() {
        File file = getFile();
        if (file != null) {
            return file.getName();
        }
        return null;
    }

    /**
     * 获取当前Jar文件
     * @return
     */
    private static File getFile() {
        String path = JarUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        path = URLUtil.encode(path);
        return new File(path);
    }
}
