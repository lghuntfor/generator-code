@DIRECTIVE SAFE_OUTPUT_OPEN;
* **接口版本：**

|修改人员|版本|说明|日期|
|:---- |:---|----- |---|
|${author} |${version}  |创建接口 |${date(),"yyyy-MM-dd"}|

* **描述：**

```
${data.tableComment}
获取指定id详情接口
```

* **请求URL：**

```
服务器地址: http://xx.xxx.com
接口地址: /api/${data.urlPath}
```

* **请求方式：**

```
- Request-Method: GET
```

* **请求示例：**

```
GET: http://xx.xxx.com/api/${data.urlPath}/10
```

**请求参数说明：**

|参数名|必选|类型|说明|
|:---- |:---|:----- |:----- |
|id|是| integer | id主键 |

* **返回示例: **

```
{
    "code": 200,
    "msg": "成功",
    "data": {
	@for(c in data.columnList){
	@if(cLP.last){
		@if(isNotEmpty(c.defaultValue)){
		"${c.propertyName}": "${c.defaultValue}"
		@}else{
		"${c.propertyName}": null
		@}
	@}else{
		@if(isNotEmpty(c.defaultValue)){
		"${c.propertyName}": "${c.defaultValue}",
		@}else{
		"${c.propertyName}": null,
		@}
	@}
	@}	
	}
}
```

* **返回字段说明: **

|参数名|类型|说明|
|:---- |:----- |:----- |
@for(c in data.columnList){
@if(c.nullable){
|${c.propertyName}| ${c.jsonType} |${c.columnComment}|
@}else{
|${c.propertyName}| ${c.jsonType} |${c.columnComment}|
@}
@}

* **备注: **