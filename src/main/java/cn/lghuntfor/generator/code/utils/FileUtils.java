package cn.lghuntfor.generator.code.utils;

import java.io.File;
import java.util.function.Consumer;

/**
 * 文件工具类
 * @author lghuntfor
 * @date 2020/11/14
 */
public class FileUtils {

    /**
     * 递归目录或文件, 要操作的内容在consumer中执行
     * @param file
     * @param consumer
     */
    public static void recursionFile(File file, Consumer<File> consumer) {
        if (!file.exists()) {
            throw new RuntimeException("文件不存在, dir = " + file.getAbsolutePath());
        }
        consumer.accept(file);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    recursionFile(f, consumer);
                }
            }
        }
    }

    /**
     * 递归目录或文件, 要操作的内容在consumer中执行
     * @param filePath
     * @param consumer
     */
    public static void recursionFile(String filePath, Consumer<File> consumer) {
        recursionFile(new File(filePath), consumer);
    }

    /**
     * 获取文件目录分隔符
     * @return
     */
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }
}
