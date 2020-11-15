package cn.lghuntfor.generator.code.model;

import lombok.Data;

import java.io.File;

/**
 * 模版文件信息封装
 * @author lghuntfor
 * @date 2020/11/14
 */
@Data
public class TemplateInfo {

    /** 模版文件绝对路径 */
    private String templatePath;

    /** 模版文件的相对路径 */
    private String templateRelativePath;

    /** 模版文件解析后的相对路径 */
    private String parseRelativePath;

    /** 模版文件 */
    private File templateFile;

}
