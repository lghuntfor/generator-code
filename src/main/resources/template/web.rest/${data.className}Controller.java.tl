package ${packageName}.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import com.ndccloud.commons.common.result.ReturnData;
import com.ndccloud.edu.base.common.utils.StatusUtils;
import com.ndccloud.commons.common.convert.ConvertUtils;
import ${packageName}.api.dto.${data.className}DTO;
import com.ndccloud.commons.common.constants.ReturnMsg;
import com.ndccloud.commons.common.page.PageInfo;
import ${packageName}.api.vo.${data.className}VO;
import ${packageName}.api.vo.${data.className}AddVO;
import ${packageName}.api.vo.${data.className}EditVO;
import ${packageName}.api.vo.${data.className}ListVO;
import ${packageName}.api.query.${data.className}ListQuery;
import org.springframework.web.bind.annotation.*;
import com.ndccloud.commons.webmvc.controller.BaseController;
import ${packageName}.service.${data.className}Service;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import java.util.List;


/**
 * ${data.tableComment}请求接口处理类
 * \@author ${author}
 * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
 * \@version ${version}
 */
\@RestController
\@RequestMapping(value = "/api")
\@Api(value = "/api", tags = "${data.tableComment}")
public class ${data.className}Controller extends BaseController {


	\@Autowired
	private ${data.className}Service ${data.lowerClassName}Service;


	/**
     * ${data.tableComment}
	 * 新增接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
    \@PostMapping("/${data.lowerClassName}")
	\@ApiOperation(value = "新增接口", tags = "${data.tableComment}")
    public ReturnData<${data.className}VO> add(\@RequestBody \@Validated ${data.className}AddVO addVO) {
        return ${data.lowerClassName}Service.save(addVO);
    }
	
	
	/**
     * ${data.tableComment}
	 * 编辑接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
    \@PutMapping("/${data.lowerClassName}")
	\@ApiOperation(value = "编辑接口", tags = "${data.tableComment}")
    public ReturnData<${data.className}VO> edit(\@RequestBody \@Validated ${data.className}EditVO editVO) {
        return ${data.lowerClassName}Service.update(editVO);
    }


	/**
     * ${data.tableComment}
	 * 列表分页查询接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
    \@GetMapping("/${data.lowerClassName}")
	\@ApiOperation(value = "列表分页接口", tags = "${data.tableComment}")
    public ReturnData<PageInfo<${data.className}ListVO>> page(${data.className}ListQuery query) {
		PageInfo<${data.className}ListVO> page = ${data.lowerClassName}Service.page(query);
        return new ReturnData<>(page);
    }


	/**
     * ${data.tableComment}
	 * 详情接口
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
    \@GetMapping("/${data.lowerClassName}/{id}")	
	\@ApiOperation(value = "详情接口", tags = "${data.tableComment}")
	\@ApiImplicitParams({
		\@ApiImplicitParam(dataType = "Long", name = "id", value = "记录id", required = true , example = "1")
    })
    public ReturnData<${data.className}VO> detail(\@PathVariable Long id) {
		${data.className}DTO dto = ${data.lowerClassName}Service.getDTOById(id);
		if (dto == null) {
			return new ReturnData<>(ReturnMsg.NOT_FOUND);
		}
        return new ReturnData<>(ConvertUtils.convert(dto, ${data.className}VO.class));
    }
	
	
	/**
     * ${data.tableComment}
	 * 批量删除接口(逻辑删除)
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
    \@DeleteMapping("/${data.lowerClassName}")
	\@ApiOperation(value = "批量删除接口", tags = "${data.tableComment}")
	\@ApiImplicitParams({
		\@ApiImplicitParam(dataType = "array", name = "ids", value = "记录id数组", required = true , example = "1")
    })
    public ReturnData<Object> delete(\@RequestBody List<Long> ids) {		
        return ${data.lowerClassName}Service.deleteByIds(ids);
    }


	/**
     * ${data.tableComment}
	 * 批量更新操作接口
	 * pending-挂起, recover-恢复
     * \@author ${author}
     * \@date ${date(),"yyyy-MM-dd HH:mm:ss"}
     * \@return com.ndccloud.commons.common.result.ReturnData
     **/
	\@PutMapping("/${data.lowerClassName}/{operation}")
	\@ApiOperation(value = "批量更新操作接口", tags = "${data.tableComment}")
	\@ApiImplicitParams({
		\@ApiImplicitParam(dataTypeClass = List.class, name = "ids", value = "记录id数组", required = true , example = "1"),
		\@ApiImplicitParam(dataTypeClass = String.class, name = "operation", value = "操作名称: pending-挂起, recover-恢复", required = true , example = "pending")
    })
    public ReturnData<Object> batchOperation(\@RequestBody List<Long> ids, \@PathVariable String operation) {
        Byte status = StatusUtils.getStatus(operation);
        if (status == null) {
            return new ReturnData<>(ReturnMsg.BAD_REQUEST.getCode(), "不支持此操作");
        }
        /** 这里可以再扩展其他操作 */
        return ${data.lowerClassName}Service.updateStatusByIds(ids, status);
    }
}
