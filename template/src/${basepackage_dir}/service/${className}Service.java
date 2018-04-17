<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service;

import java.util.List;

<#include "/java_imports.include">

public interface ${className}Service{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer add${className}(${className} entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer update${className}(${className} entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer delete${className}(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<${className}> find${className}(${className} entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<${className}> find${className}ByPage(${className} entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ${className} find${className}ById(${className} entity);
}
