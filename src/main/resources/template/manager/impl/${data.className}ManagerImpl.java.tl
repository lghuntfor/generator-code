package ${packageName}.manager.impl;


import org.springframework.stereotype.Service;
import com.ndccloud.commons.mybatis.plus.manager.impl.BaseManagerImpl;
import ${packageName}.domain.${data.className};
import ${packageName}.mapper.${data.className}Mapper;
import ${packageName}.manager.${data.className}Manager;

/**
 * ${data.tableComment}通用业务接口实现类
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */ 
\@Service
public class ${data.className}ManagerImpl extends BaseManagerImpl<${data.className}, ${data.className}Mapper> implements ${data.className}Manager {

}
