package cn.lghuntfor.generator.code.model;

import lombok.Data;

/**
 * 列信息封装
 * @author lghuntfor
 * @date 2020/11/14
 */
@Data
public class ColumnInfo {

    /** 表名, 从数据库中获取 */
    private String tableName;

    /** 列名, 从数据库中获取 */
    private String columnName;

    /** 列对应的数据类型, 如varchar, 从数据库中获取 */
    private String dataType;

    /** 列对应的数据类型, 包括长度描述, 如varchar(10), 从数据库中获取 */
    private String fullDataType;

    /** 列注释, 从数据库中获取 */
    private String columnComment;

    /** 列的默认值 */
    private String defaultValue;

    /** 是否可以为空 */
    private Boolean nullable;

    /** 是否是主键 */
    private Boolean primary;

    /** 列对应的驼峰格式的实体属性名 */
    private String propertyName;

    /** 列对应的驼峰格式的实体属性名, 首字母大写 */
    private String upperPropertyName;

    /** java类型 */
    private String javaType;

    /** java基础类型 */
    private String baseJavaType;

    /** java类型的全类名 */
    private String fullJavaType;

    /** 接口返回的类型 */
    private String jsonType;

}
