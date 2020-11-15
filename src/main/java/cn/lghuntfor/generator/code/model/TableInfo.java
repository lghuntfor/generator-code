package cn.lghuntfor.generator.code.model;

import lombok.Data;

import java.util.List;

/**
 * 表信息封装
 * @author lghuntfor
 * @date 2020/11/14
 */
@Data
public class TableInfo {

    /** 表名 */
    private String tableName;

    /** 表注释 */
    private String tableComment;

    /** 表中的列表信息 */
    private List<ColumnInfo> columnList;

    /** 表对应的实体类名 */
    private String className;

    /** 表对应的实体类名, 首字母小写 */
    private String lowerClassName;
}
