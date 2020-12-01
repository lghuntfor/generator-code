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

    /** 表名全小写 */
    private String lowerTableName;

    /** 表注释 */
    private String tableComment;

    /** 表中的列表信息 */
    private List<ColumnInfo> columnList;

    /** 表中的所有列名 */
    private List<String> columnNames;

    /** 表中的所有字段名 */
    private List<String> propertyNames;

    /** 表对应的实体类名 */
    private String className;

    /** 表对应的实体类名, 首字母小写 */
    private String lowerClassName;

    /** 实体类名对应的http路径名, 如SysUser对应sys-user */
    private String urlPath;

    /** mybatis要排除的属性名 */
    private List<String> excludeProps;

}
