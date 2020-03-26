package ${templateItem.service_template.targetPackage};

import ${templateItem.entity_template.targetPackage}.${tableItem.beanName};
<#list tableItem.columnList as pk>
<#if pk.id>
    <#assign primaryKey=pk>
<#break>
</#if>
</#list>

public interface ${tableItem.beanName}Service {
    int deleteByPrimaryKey(${primaryKey.javaType} ${primaryKey.fieldName});
    
    int updateByPrimaryKey(${tableItem.beanName} ${tableItem.beanName?uncap_first});
    
    int insert(${tableItem.beanName} ${tableItem.beanName?uncap_first});
}