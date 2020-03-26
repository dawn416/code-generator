package ${templateItem.controller_template.targetPackage};

//import org.springframework.stereotype.Controller;

import ${templateItem.entity_template.targetPackage}.${tableItem.beanName};
<#list tableItem.columnList as pk>
<#if pk.id>
    <#assign primaryKey=pk>
<#break>
</#if>
</#list>

//@Controller
public class ${tableItem.beanName}Controller {

}