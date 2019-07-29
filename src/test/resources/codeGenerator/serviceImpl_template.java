package ${templateItem.serviceImpl_template.targetPackage};

import ${templateItem.dao_template.targetPackage}.${tableItem.beanName}Mapper;
import ${templateItem.entity_template.targetPackage}.${tableItem.beanName};
import ${templateItem.service_template.targetPackage}.${tableItem.beanName}Service;
<#list tableItem.columnList as pk>
<#if pk.id>
    <#assign primaryKey=pk>
<#break>
</#if>
</#list>
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//@Service
public class ${tableItem.beanName}ServiceImpl implements ${tableItem.beanName}Service {

//    @Autowired
    private ${tableItem.beanName}Mapper ${tableItem.beanName?uncap_first}Mapper;

    @Override
    public int deleteByPrimaryKey(${primaryKey.javaType} ${primaryKey.fieldName}) {
        return ${tableItem.beanName?uncap_first}Mapper.deleteByPrimaryKey(${primaryKey.fieldName});
    }
    
    @Override
    public int updateByPrimaryKey(${tableItem.beanName} ${tableItem.beanName?uncap_first}) {
        return ${tableItem.beanName?uncap_first}Mapper.updateByPrimaryKey(${tableItem.beanName?uncap_first});
    }
    
    @Override
    public int insert(${tableItem.beanName} ${tableItem.beanName?uncap_first}) {
        return ${tableItem.beanName?uncap_first}Mapper.insert(${tableItem.beanName?uncap_first});
    }
}