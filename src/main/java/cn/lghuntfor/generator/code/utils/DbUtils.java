package cn.lghuntfor.generator.code.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.pooled.PooledDSFactory;
import cn.hutool.setting.Setting;
import cn.lghuntfor.generator.code.common.Const;
import cn.lghuntfor.generator.code.model.ColumnInfo;
import cn.lghuntfor.generator.code.model.ConfigInfo;
import cn.lghuntfor.generator.code.model.TableInfo;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * db工具类
 * @author lghuntfor
 * @date 2020/11/14
 */
public class DbUtils {


    /**
     * 从配置信息中获取表与列的信息
     * @param configInfo
     * @return
     * @throws SQLException
     */
    public static List<TableInfo> getTableInfo(ConfigInfo configInfo) {
        try {
            DataSource dataSource = getDataSource(configInfo);
            Map<String, Object> params = new HashMap<>();
            if (StrUtil.isNotBlank(configInfo.getDbName())) {
                params.put("dbName", configInfo.getDbName());
            }
            Db db = Db.use(dataSource);

            List<TableInfo> tableInfoList = new ArrayList<>(configInfo.getTableNames().size());
            params.put("tableNames", "'" + StrUtil.join("','", configInfo.getTableNames()) + "'");
            String queryTableSql = TemplateUtils.parseClasspathFile(Const.QUERY_TABLE_TEMPLATE_FILE, params);
            System.out.println("查询数据表SQL: " + queryTableSql);
            List<Entity> tableList = db.query(queryTableSql);
            if (CollUtil.isEmpty(tableList)) {
                System.out.println("数据表未找到");
                return null;
            }

            String queryColumnSql = TemplateUtils.parseClasspathFile(Const.QUERY_COLUMN_TEMPLATE_FILE, params);
            System.out.println("查询数据列SQL: " + queryColumnSql);
            List<Entity> columnList = db.query(queryColumnSql);
            if (CollUtil.isEmpty(columnList)) {
                throw new RuntimeException("数据列未找到");
            }
            List<ColumnInfo> columnInfoList = new ArrayList<>(columnList.size());
            columnList.forEach(c -> {
                ColumnInfo columnInfo = c.toBean(ColumnInfo.class);
                putColumnInfo(columnInfo);
                columnInfoList.add(columnInfo);
            });

            Map<String, List<ColumnInfo>> columnMap = columnInfoList.stream().collect(Collectors.groupingBy(ColumnInfo::getTableName));
            for (Entity table : tableList) {
                TableInfo tableInfo = table.toBean(TableInfo.class);
                putTableInfo(tableInfo, configInfo.getPrefix());
                tableInfo.setColumnList(columnMap.get(tableInfo.getTableName()));
                tableInfoList.add(tableInfo);
            }
            return tableInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置列与对象属性相关的信息
     * @param columnInfo
     */
    private static void putColumnInfo(ColumnInfo columnInfo) {
        String columnName = columnInfo.getColumnName();
        String propertyName = StrUtil.toCamelCase(columnName);
        columnInfo.setPropertyName(propertyName);
        columnInfo.setUpperPropertyName(StrUtil.upperFirst(propertyName));
        putJavaType(columnInfo);
    }

    /**
     * 设置表与实体相关的信息
     * @param tableInfo
     * @param prefix
     */
    private static void putTableInfo(TableInfo tableInfo, String prefix) {
        String tableName = tableInfo.getTableName();
        String tempTableName = tableName.replace(prefix, "");

        String lowerClassName = StrUtil.toCamelCase(tempTableName);
        tableInfo.setLowerClassName(lowerClassName);
        tableInfo.setClassName(StrUtil.upperFirst(lowerClassName));
    }

    /**
     * 获取dataSource
     * @param configInfo
     * @return
     */
    public static DataSource getDataSource(ConfigInfo configInfo) {
        Setting setting = new Setting();
        setting.set("url", configInfo.getDbUrl());
        setting.set("user", configInfo.getDbUser());
        setting.set("pass", configInfo.getDbPass());
        return PooledDSFactory.create(setting).getDataSource();
    }

    /**
     * 设置javaType
     * @param columnInfo
     */
    public static void putJavaType(ColumnInfo columnInfo) {
        String dbType = columnInfo.getDataType();
        if (StrUtil.equalsAnyIgnoreCase(dbType, "int", "integer")) {
            columnInfo.setFullJavaType("java.lang.Integer");
            columnInfo.setBaseJavaType("int");
            columnInfo.setJavaType("Integer");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "tinyint")) {
            columnInfo.setFullJavaType("java.lang.Byte");
            columnInfo.setBaseJavaType("byte");
            columnInfo.setJavaType("Byte");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "bigint")) {
            columnInfo.setFullJavaType("java.lang.Long");
            columnInfo.setBaseJavaType("long");
            columnInfo.setJavaType("Long");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "varchar", "text", "longtext", "char")) {
            columnInfo.setFullJavaType("java.lang.String");
            columnInfo.setBaseJavaType("String");
            columnInfo.setJavaType("String");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "numeric", "double")) {
            columnInfo.setFullJavaType("java.lang.Double");
            columnInfo.setBaseJavaType("double");
            columnInfo.setJavaType("Double");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "float")) {
            columnInfo.setFullJavaType("java.lang.Float");
            columnInfo.setBaseJavaType("float");
            columnInfo.setJavaType("Float");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "decimal")) {
            columnInfo.setFullJavaType("java.math.BigDecimal");
            columnInfo.setBaseJavaType("BigDecimal");
            columnInfo.setJavaType("BigDecimal");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "datetime", "timestamp", "date", "time")) {
            columnInfo.setFullJavaType("java.util.Date");
            columnInfo.setBaseJavaType("Date");
            columnInfo.setJavaType("Date");
        } else if (StrUtil.equalsAnyIgnoreCase(dbType, "boolean", "bit")) {
            columnInfo.setFullJavaType("java.lang.Boolean");
            columnInfo.setBaseJavaType("boolean");
            columnInfo.setJavaType("Boolean");
        } else {
            columnInfo.setFullJavaType("java.lang.Object");
            columnInfo.setBaseJavaType("Object");
            columnInfo.setJavaType("Object");
        }
    }
}
