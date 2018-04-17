<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.mapper;


import java.util.List;

<#include "/java_imports.include">

public interface ${className}Mapper {

    /**
     * 新增信息
     */
    public Integer add${className}(${className} entity);

    /**
     * 修改信息
     */
    public Integer update${className}(${className} entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer delete${className}(String id);
    /**
     * 批量删除
     */
    public Integer delete${className}(String[] id); 

    /**
     * 查询列表
     */
    public List<${className}> find${className}(${className} entity);

    /**
     * 查询列表分页  
     */
    public List<${className}> find${className}ByPage(${className} entity);

}
