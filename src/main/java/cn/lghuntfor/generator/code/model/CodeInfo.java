package cn.lghuntfor.generator.code.model;

import lombok.Data;

/**
 * 代码生成的信息封装
 * @author lghuntfor
 * @date 2020/11/15
 */
@Data
public class CodeInfo {

    /** 代码生成后的绝对路径 */
    private String path;

    /** 代码生成的内容 */
    private String content;

}
