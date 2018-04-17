<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ${basepackage}.mapper.${className}Mapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

<#include "/java_imports.include">
@Transactional
@Service("${className}Service")
public class ${className}ServiceImpl implements ${className}Service{

    private final static Logger log=Logger.getLogger(${className}ServiceImpl.class);
    
    @Autowired
    private ${className}Mapper ${className}Mapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer add${className}(${className} entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ${className}Mapper.add${className}(entity);
            }

        } catch (Exception e) {

            new RuntimeException();
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer update${className}(${className} entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ${className}Mapper.update${className}(entity);
            }
        } catch (Exception e) {
            
            log.error("${className}信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer delete${className}(String id){
        
        
        return ${className}Mapper.delete${className}(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<${className}> find${className}(${className} entity){
        
        
        return ${className}Mapper.find${className}(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<${className}> find${className}ByPage(${className} entity){
        
        
        return ${className}Mapper.find${className}ByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ${className} find${className}ById(${className} entity){
        
        List<${className}> list=${className}Mapper.find${className}(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
