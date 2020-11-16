@DIRECTIVE SAFE_OUTPUT_OPEN;
* **接口版本：**

|修改人员|版本|说明|日期|
|:---- |:---|----- |---|
|${author} |${version}  |创建接口 |${date(),"yyyy-MM-dd"}|

* **描述：**

```
- ${data.tableComment}
删除接口
```

* **请求URL：**

```
服务器地址: http://xx.xxx.com
接口地址: /api/${data.urlPath}/{id}
```

* **请求方式：**

```
- Request-Method: DELETE
```

* **请求示例：**

```
DELETE: http://xx.xxx.com/api/${data.urlPath}/10
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
    "data": {}
}
```

* **返回字段说明: **

|参数名|类型|文案|说明|
|:---- |:----- |:----- |------   |
|code| int | \ |响应状态码，200表示正常 |
|msg| String|  \ | 返回的消息提示 |

* **备注: **