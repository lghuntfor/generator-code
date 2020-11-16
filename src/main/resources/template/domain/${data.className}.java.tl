@DIRECTIVE SAFE_OUTPUT_OPEN;
package ${packageName}.domain;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

import com.ndccloud.edu.base.common.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * ${data.tableComment}实体类,与数据表相对应
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */
\@Data
\@TableName(value = "${data.tableName}", excludeProperty = {})
public class ${data.className} extends BaseDomain implements Serializable {

@for(c in data.columnList){
    @if(isNotEmpty(c.columnComment)){
    /**${c.columnComment}*/
    @}
	@if(c.primary){
    \@TableId(type = IdType.AUTO)
    @}
    private ${c.javaType} ${c.propertyName};

@}
}
