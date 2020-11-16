package ${packageName}.dubbo;


import org.apache.dubbo.config.annotation.DubboService;
import ${packageName}.api.dubbo.${data.className}DubboService;


/**
 * ${data.tableComment}业务dubbo接口实现类
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */ 
\@DubboService(interfaceClass = ${data.className}DubboService.class)
public class ${data.className}DubboServiceImpl implements ${data.className}DubboService {

}
