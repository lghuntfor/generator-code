@DIRECTIVE SAFE_OUTPUT_OPEN;
package ${packageName}.api.query;

import com.ndccloud.commons.common.query.Query;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ${data.tableComment}查询信息封装类
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */
\@Data
\@ApiModel(value="${data.className}ListQuery", description="${data.tableComment}")
public class ${data.className}ListQuery extends Query {

@for(c in data.columnList){
    @if(isNotEmpty(c.columnComment)){
    /**${c.columnComment}*/
    \@ApiModelProperty(value = "${c.columnComment}")
    @}
    private ${c.javaType} ${c.propertyName};

@}
}
