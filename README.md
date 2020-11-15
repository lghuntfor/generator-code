## Generator-Code
<p align="center">
	<a target="_blank" href="https://license.coscl.org.cn/MulanPSL2/">
		<img src="https://img.shields.io/:license-MulanPSL2-blue.svg" />
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
	<a target="_blank" href="https://github.com/lghuntfor/generator-code">
		<img src="https://img.shields.io/badge/Github-welcome-brightgreen.svg" />
	</a>
	<a target="_blank" href="https://gitee.com/lghuntfor/generator-code">
		<img src="https://img.shields.io/badge/Gitee-welcome-brightgreen.svg" />
	</a>
</p>

一套基于模版的代码生成器, 可以通过模版文件, 生成任意代码

## 获取代码
```
Github: git clone https://github.com/lghuntfor/generator-code.git
Gitee: git clone https://gitee.com/lghuntfor/generator-code.git
```

## 快速开始
执行命令   
```
cd generator-code
mvn package -Dmaven.test.skip=true
```

在target/output目录下就是最终可执行文件   
```
cd target/output
```

修改配置文件
```
vim generator.properties
```

运行代码生成器
```
执行 start.sh 或 start.bat
```

## 模版与配置变量说明

- 关于模版的语法请参考[beetl文档](http://ibeetl.com/guide/#/beetl/), 项目中定界符已设置为@, 可通过beetl.properties修改
- generator.properties文件中所有的配置内容, 都可以直接在模版文件中使用(其中包括文件内容,文件名,目录), 使用${key}的方式获取
```
示例: ${author}, ${version}
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
tableComment| ${data.tableComment} | 用户表 | 表注释 |
className| ${data.className} | SysUser | 表对应的实体类, 驼峰规则, 可通过prefix去掉前缘 |
lowerClassName| ${data.lowerClassName} | sysUser | 实体类名的首字母小写 |
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
tableName| ${.tableName} | sys_user | 表名 |
columnName| ${c.columnName} | user_name | 列名 |
dataType| ${c.dataType} | varchar | 列在数据库中的类型 |
columnComment| ${c.columnComment} | 用户账号 | 列的注释 |
propertyName| ${c.propertyName} | userName | 列对应的实体属性名, 驼峰规则, 首字母小写 |
upperPropertyName| ${c.upperPropertyName} | UserName | 列对应的实体属性名, 驼峰规则, 首字母大写 |
javaType| ${c.javaType} | String | 属性的java类型 |
baseJavaType| ${c.baseJavaType} | - | 属性的java类型对应的基本类型, 只有基本类型才会存在 |
fullJavaType| ${c.fullJavaType} | java.lang.String | 属性的java类型的全类名 |


