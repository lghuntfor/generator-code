@DIRECTIVE SAFE_OUTPUT_OPEN;
package ${packageName}.api.bo;

import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;

/**
 * ${data.tableComment}BO业务实体类
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */
\@Data
public class ${data.className}BO implements Serializable {

@for(c in data.columnList){
    @if(isNotEmpty(c.columnComment)){
    /**${c.columnComment}*/
    @}
    private ${c.javaType} ${c.propertyName};

@}
}
