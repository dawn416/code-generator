package ${templateItem.entity_template.targetPackage};

import java.io.Serializable;

<#list tableItem.columnList as pk>
<#if pk.javaType=='Date'><#assign utildate=true></#if>
<#if pk.javaType=='BigDecimal'><#assign bigdecimal=true></#if>
</#list>
<#if utildate??>import java.util.Date;</#if>
<#if bigdecimal??>import java.math.BigDecimal;</#if>
//import lombok.Data;

//@Data
public class ${tableItem.beanName} implements Serializable {

    private static final long serialVersionUID = 1L;
<#-- field -->
<#list tableItem.columnList as pk>
    <#assign exist=false>
    <#if customItem?? && customItem.excludeFields??>
    <#list customItem.excludeFields as fi>
        <#if fi == pk.fieldName>
            <#assign exist=true><#break>
        </#if>
    </#list>
    </#if>
    <#if !exist>
    /**
     * ${pk.remarks}
     */
    private ${pk.javaType} ${pk.fieldName};
    </#if>
</#list>

<#-- getter and setter method -->
<#-- 
<#list tableItem.columnList as pk>
    <#assign exist=false>
    <#if customItem??>
    <#list customItem.excludeFields as fi>
        <#if fi == pk.fieldName>
            <#assign exist=true><#break>
        </#if>
    </#list>
    </#if>
    <#if !exist>
    public void set${pk.fieldName?cap_first} (${pk.javaType} ${pk.fieldName}) {
        this.${pk.fieldName} = ${pk.fieldName};
    }
    public ${pk.javaType} get${pk.fieldName?cap_first} () {
        return ${pk.fieldName};
    }
    </#if>
</#list>
-->
}