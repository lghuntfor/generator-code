### 代码生成器使用说明

* 使用前先配置generator.properties
* 到template目录可修改对应文件的模版
* 配置好后, 直接双击start.bat/start.sh即可
* 生成好的代码默认当前文件夹code目录下


## 模版与配置变量说明

- 关于模版的语法请参考[beetl文档](http://ibeetl.com/guide/#/beetl/), 项目中定界符已设置为@, 可通过beetl.properties修改
- generator.properties文件中所有的配置内容, 都可以直接在模版文件中使用(其中包括文件内容,文件名,目录), 使用${key}的方式获取
```
示例: ${author}, ${version}, ${packageName}
```
- 要基于数据库的表生成相关代码则需要正确配置以下内容, 相关的模版可以参考示例文件
```
tableNames = rbac_user, rbac_role
dbUrl = jdbc:mysql://10.6.6.3:3306/euaa_devdb
dbName = euaa_devdb
dbUser = root
dbPass = 123456
```
- 基于数据库生成常用代码的一些变量说明, 都需要以data开头, 如${data.xxx}, 以下以sys_user表为例

变量名 | 使用示例 |示例结果| 说明|
---|---|---|---|
tableName| ${data.tableName} | sys_user | 表名 |
lowerTableName| ${data.lowerTableName} | sys_user | 表名, 全小写, 有些表名可能会使用全大写 |
tableComment| ${data.tableComment} | 用户表 | 表注释 |
className| ${data.className} | SysUser | 表对应的实体类, 驼峰规则, 可通过prefix去掉前缘 |
lowerClassName| ${data.lowerClassName} | sysUser | 实体类名的首字母小写 |
urlPath| ${data.urlPath} | sys-user | 实体类名对应的http路径名, 如SysUser对应sys-user |
columnList| ${data.columnList} | - | 数据表中的所有列数据, 通常需要使用循环语句才可使用 |

```
beetl循环示例:

@for(c in data.columnList){
    @if(isNotEmpty(c.columnComment)){
    /** ${c.columnComment} */
    @}
    private ${c.javaType} ${c.propertyName};

@}
```

集合columnList中的属性说明, 基于上一示例, 其中循环变量为c

变量名 | 使用示例 |示例结果| 说明|
---|---|---|---|
tableName| ${c.tableName} | sys_user | 表名 |
columnName| ${c.columnName} | user_name | 列名 |
dataType| ${c.dataType} | varchar | 列在数据库中的类型 |
fullDataType| ${c.fullDataType} | varchar(10) | 列在数据库中的类型 |
columnComment| ${c.columnComment} | 用户账号 | 列的注释 |
defaultValue| ${c.defaultValue} | '' | 列的默认值 |
nullable| ${c.nullable} | true | 是否可以为空 |
primary| ${c.primary} | true | 是否是主健 |
propertyName| ${c.propertyName} | userName | 列对应的实体属性名, 驼峰规则, 首字母小写 |
upperPropertyName| ${c.upperPropertyName} | UserName | 列对应的实体属性名, 驼峰规则, 首字母大写 |
javaType| ${c.javaType} | String | 属性的java类型 |
baseJavaType| ${c.baseJavaType} | - | 属性的java类型对应的基本类型, 只有基本类型才会存在 |
fullJavaType| ${c.fullJavaType} | java.lang.String | 属性的java类型的全类名 |
