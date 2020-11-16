package ${packageName}.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}.domain.${data.className};

/**
 * ${data.tableComment}数据处理Mapper接口
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */
\@Mapper
public interface ${data.className}Mapper extends BaseMapper<${data.className}> {

}
