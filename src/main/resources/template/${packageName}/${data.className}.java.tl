package ${packageName}.model

import lombok.Data;

/**
 * 模版文件示例
 * ${data.tableComment}
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 */
public class ${data.className} {

@for(c in data.columnList){
    @if(isNotEmpty(c.columnComment)){
    /** ${c.columnComment} */
    @}
    private ${c.javaType} ${c.propertyName};

@}
}