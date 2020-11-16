package ${packageName}.service;


import com.ndccloud.commons.common.result.ReturnData;
import com.ndccloud.commons.common.page.PageInfo;
import ${packageName}.api.vo.${data.className}VO;
import ${packageName}.api.vo.${data.className}AddVO;
import ${packageName}.api.vo.${data.className}EditVO;
import ${packageName}.api.vo.${data.className}ListVO;
import com.ndccloud.commons.common.convert.ConvertUtils;
import ${packageName}.api.dto.${data.className}DTO;
import ${packageName}.api.query.${data.className}ListQuery;

import java.util.List;

/**
 * ${data.tableComment}业务接口
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */
public interface ${data.className}Service {

	/**
     * ${data.tableComment}
	 * 新增接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
	ReturnData<${data.className}VO> save(${data.className}AddVO addVO);
	
	/**
     * ${data.tableComment}
	 * 编辑接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
	ReturnData<${data.className}VO> update(${data.className}EditVO editVO);

	/**
     * ${data.tableComment}
	 * 列表分页查询接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.page.PageInfo
     **/
	PageInfo<${data.className}ListVO> page(${data.className}ListQuery query);
	
	/**
     * ${data.tableComment}
	 * 详情接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return ${packageName}.api.dto.${data.className}DTO
     **/
	${data.className}DTO getDTOById(Long id);

	/**
     * ${data.tableComment}
	 * 批量删除接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
	ReturnData<Object> deleteByIds(List<Long> ids);
	
	/**
     * ${data.tableComment}
	 * 批量更新操作接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
	 * \@param ids
	 * \@param status
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
	ReturnData<Object> updateStatusByIds(List<Long> ids, Byte status);
}