package ${packageName}.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import com.ndccloud.commons.common.result.ReturnData;
import cn.hutool.core.collection.CollUtil;

import com.ndccloud.edu.base.common.domain.BaseDomainUtils;
import com.ndccloud.commons.common.page.PageInfo;
import ${packageName}.domain.${data.className};
import ${packageName}.service.${data.className}Service;
import ${packageName}.manager.${data.className}Manager;
import ${packageName}.api.vo.${data.className}VO;
import ${packageName}.api.vo.${data.className}AddVO;
import ${packageName}.api.vo.${data.className}EditVO;
import ${packageName}.api.vo.${data.className}ListVO;
import com.ndccloud.commons.common.convert.ConvertUtils;
import ${packageName}.api.dto.${data.className}DTO;
import ${packageName}.api.query.${data.className}ListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import com.ndccloud.commons.mybatis.plus.common.PageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ${data.tableComment}
 * 业务逻辑实现类
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */ 
\@Service
public class ${data.className}ServiceImpl implements ${data.className}Service {

	\@Autowired
	private ${data.className}Manager ${data.lowerClassName}Manager;
		
	\@Override
	public ReturnData<${data.className}VO> save(${data.className}AddVO addVO) {
		${data.className} ${data.lowerClassName} = new ${data.className}();
		BeanUtils.copyProperties(addVO, ${data.lowerClassName});
		ReturnData returnData = initAndCheck(${data.lowerClassName}, true);
		if (returnData.getCode() != ReturnData.SUCCESS_CODE) {
			return returnData;
		}
		${data.lowerClassName}Manager.save(${data.lowerClassName});
		
		${data.className}VO vo = new ${data.className}VO();
		BeanUtils.copyProperties(${data.lowerClassName}, vo);
		return new ReturnData(vo);
	}
	
	/** 数据初始化以及数据有效性检验 */
	private ReturnData initAndCheck(${data.className} domain, boolean isAdd) {		
		return BaseDomainUtils.initAndCheck(domain, isAdd, ${data.lowerClassName}Manager);
	}
	
	\@Override
	public ReturnData<${data.className}VO> update(${data.className}EditVO editVO) {
		${data.className} ${data.lowerClassName} = new ${data.className}();
		BeanUtils.copyProperties(editVO, ${data.lowerClassName});
		ReturnData returnData = initAndCheck(${data.lowerClassName}, false);
		if (returnData.getCode() != ReturnData.SUCCESS_CODE) {
			return returnData;
		}
		${data.lowerClassName}Manager.updateById(${data.lowerClassName});
		
		${data.className}VO vo = new ${data.className}VO();
		BeanUtils.copyProperties(${data.lowerClassName}, vo);
		return new ReturnData(vo);
	}

	\@Override
	public PageInfo<${data.className}ListVO> page(${data.className}ListQuery query) {
		PageInfo page = PageUtil.buildPageInfo(query);		
		${data.className} ${data.lowerClassName} = new ${data.className}();
		BeanUtils.copyProperties(query, ${data.lowerClassName});
		PageInfo<${data.className}> pageResult = ${data.lowerClassName}Manager.page(page, ${data.lowerClassName});
		
		PageInfo<${data.className}ListVO> pageReturn = PageInfo.copyPageInfo(pageResult);
		List<${data.className}ListVO> resultList = ConvertUtils.convertList(pageResult.getContent(), ${data.className}ListVO.class);
		pageReturn.setContent(resultList);		 
		
		return pageReturn;
	}
	
	\@Override
	public ${data.className}DTO getDTOById(Long id) {
		${data.className} ${data.lowerClassName} = ${data.lowerClassName}Manager.getById(id);
		return ConvertUtils.convert(${data.lowerClassName}, ${data.className}DTO.class);
	}

	\@Override
	public ReturnData<Object> deleteByIds(List<Long> ids) {
		List<${data.className}> domainList = BaseDomainUtils.checkAndDeleteInfo(ids, ${data.lowerClassName}Manager);
		${data.lowerClassName}Manager.updateBatch(domainList);		
		return new ReturnData();
	}
	
	\@Override
	public ReturnData<Object> updateStatusByIds(List<Long> ids, Byte status) {
		boolean isDel = (status == 0);
		List<${data.className}> domainList = BaseDomainUtils.checkAndUpdateInfo(ids, isDel, ${data.lowerClassName}Manager, (t) -> t.setStatus(status));
		${data.lowerClassName}Manager.updateBatch(domainList);
		return new ReturnData();
	}

}
