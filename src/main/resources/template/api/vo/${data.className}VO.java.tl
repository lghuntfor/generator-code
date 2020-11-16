@DIRECTIVE SAFE_OUTPUT_OPEN;
package ${packageName}.api.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ${data.tableComment}VO实体类
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */ 
\@Data
\@ApiModel(value="${data.className}VO实体类", description="${data.tableComment}")
public class ${data.className}VO implements Serializable {

@for(c in data.columnList){
    @if(isNotEmpty(c.columnComment)){
    /**${c.columnComment}*/
	\@ApiModelProperty(value = "${c.columnComment}")
    @}
    private ${c.javaType} ${c.propertyName};

@}
}
