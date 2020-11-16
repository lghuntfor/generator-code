@DIRECTIVE SAFE_OUTPUT_OPEN;
* **接口版本：**

|修改人员|版本|说明|日期|
|:---- |:---|----- |---|
|${author} |${version}  |创建接口 |${date(),"yyyy-MM-dd"}|

* **描述：**

```
${data.tableComment}
列表分页接口
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
GET: http://xx.xxx.com/api/${data.urlPath}?page=1&size=10&sort=name-desc&sort=age-desc
```

**请求参数说明：**

|参数名|必选|类型|说明|
|:---- |:---|:----- |:----- |
|page|否|integer|页码, 默认第1页|
|size|否|integer|每页显示数, 默认10条|
|pageShowFlag|否|boolean|是否需要查询总记录数|
|sort|否|array|排序信息:property-asc 或 property-desc,ASC,DESC不区分大小写|
@for(c in data.columnList){
|${c.propertyName}|是| ${c.jsonType} |${c.columnComment}|
@}

* **返回示例: **

```
{
	"code": 1,
	"msg": "成功",
	"data": {
		"page": "1",
		"size": "10",
		"sort": [],
		"pages": "1",
		"total": "5",
		"content": [
			{
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
		]
		"pageShowFlag": true
	}
}
```

* **返回字段说明: **

|参数名|类型|说明|
|:---- |:----- |:----- |
|page|integer|页码, 默认第1页|
|size|integer|每页显示数, 默认10条|
|pageShowFlag|boolean|是否需要查询总记录数|
|sort|array|排序信息:property-asc 或 property-desc,ASC,DESC不区分大小写|
|pages|integer|总页数|
|total|integer|总记录数|
@for(c in data.columnList){
@if(c.nullable){
|${c.propertyName}| ${c.jsonType} |${c.columnComment}|
@}else{
|${c.propertyName}| ${c.jsonType} |${c.columnComment}|
@}
@}

* **备注: **
