# 通用JDBC代码生成器

### 通过xml配置文件和用户自定义的freemarker模板生成代码,支持自定义参数

#####适用于任何使用java连接关系型数据库的项目，不论是mybatis,ibatis,hibernate或是jpa，mybatis-puls等，不论前端后端,每个项目组都可以自定义模板快速生成代码,节约编写基础增删改查的时间

------------
###quick start

运行代码生成器

```java
Map<String,Object> map = new HashMap<>();
map.put("key", "value");
Generator.generate("codeGenerator/config.xml", map);
```
第一个参数是xml配置文件名,第二个参数为一个Map,在freemarker中此Map名为customItem,如果需要map中的value,则需要在freemarker中使用`${customItem.key}`来获取,可以用于忽略某些字段等需求

------------

### config.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- dtd验证文件 -->
<!DOCTYPE generator SYSTEM "codeGenerator.dtd" >
<generator>
    <jdbcInfo>
        <!-- 数据库驱动类 -->
        <driver>com.mysql.jdbc.Driver</driver>
        <!-- 数据库地址 -->
        <url>jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&amp;characterEncoding=UTF8&amp;useSSL=false</url>
        <!-- 数据库用户名 -->
        <username>root</username>
        <!-- 数据库密码 -->
        <password>123456</password>
        <!-- 数据库scheme -->
        <scheme>saizhi</scheme>
        <!-- 数据库catalog -->
        <catalog></catalog>
        <!-- 数据库表名 -->
        <table>big_match</table>
        <!-- 表对应的实体类 -->
        <entity>BigMatch</entity>
        <!-- 属性名是否根据字段转换成驼峰命名法,为true时subject_id将转换为subjectId作为属性(fieldName) -->
        <camel>true</camel>
    </jdbcInfo>
    <typeConvertList>
        <!-- 指定jdbc转换为java类型的对应关系 -->
        <typeConvert java="String" jdbc="VARCHAR"/>
        <typeConvert java="String" jdbc="CHAR"/>
        <typeConvert java="String" jdbc="TEXT"/>
        <typeConvert java="Integer" jdbc="INT"/>
    </typeConvertList>
    <templateList>
        <!-- 模板参数,可指定多个,id指定了该templateItem的唯一标识,变量${entity}、${table}将被替换为jdbcInfo节点中的entity、table -->
        <templateItem id="mysql_template">
            <!-- freemarker模板文件路径 -->
            <templateFile>codeGenerator/entity_template.xml</templateFile>
            <!-- 文件的生成项目 -->
            <targetProject>src.main.java</targetProject>
            <!-- 文件的生成路径 -->
            <targetPackage>com.fline.entity</targetPackage>
            <!-- 生成的文件名 -->
            <targetFileName>${entity}.java</targetFileName>
        </templateItem>
        <templateItem>
            <templateFile>codeGenerator/service_template.java</templateFile>
            <targetProject>src.main.java</targetProject>
            <targetPackage>com.fline.service</targetPackage>
            <targetFileName>${entity}Service.java</targetFileName>
        </templateItem>
    </templateList>
</generator>
```

------------

###freemarker模板中的参数
例如在freemarker文件中使用`${tableItem.beanName}`即可获取表名
```json
{
	"templateItem":{ //对应xml配置文件中的templateList
		"dao_template": { //配置文件中的id
			"id": "entity_template", //配置文件中的id
			"targetPackage": "com.fline.entity", //配置文件中的targetPackage,生成的文件包名,没有该路径将自动生成
			"templateFile": "codeGenerator/entity_template.java", //配置文件中的templateFile,freemarker模板文件名
			"targetFileName": "BigMatch.java", //配置文件中的templateFile,生成的文件名
			"targetProject": "src.main.java" //配置文件中的targetProject
		},
		"service_template": {
			"id": "service_template",
			"targetPackage": "com.fline.service",
			"templateFile": "codeGenerator/service_template.java",
			"targetFileName": "BigMatchService.java",
			"targetProject": "src.main.java"
		}
	},
	"tableItem":{
		"beanName": "BigMatch", //类名
		"tableName": "big_match", //表名
		"remark": "",
		"alias": "bigMatch", //别名
		"columnList": [{ //列的属性列表
			"columnName": "id", //数据库列名
			"fieldName": "id", //字段名
			"jdbcType": "INT", //jdbc类型
			"javaType": "Integer", //java类型
			"remarks": "主键", //注释
			"id": true //是否为主键
		}, {
			"columnName": "name",
			"fieldName": "name",
			"jdbcType": "VARCHAR",
			"javaType": "String",
			"remarks": "名称",
			"id": false
		}]
	}
}
```
------------
### 使用示例

```java
	Map<String, Object> map = new HashMap<>();
	// 排除id和name字段
	map.put("excludeFields", Arrays.asList("id", "name"));
	Generator.generate("codeGenerator/config.xml", map);
```

以下为一份模板样例entity_template.java
```
package ${templateItem.entity_template.targetPackage};

import java.io.Serializable;

<#list tableItem.columnList as pk>
<#if pk.javaType=='Date'><#assign utildate=true></#if>
<#if pk.javaType=='BigDecimal'><#assign bigdecimal=true></#if>
</#list>
<#if utildate??>import java.util.Date;</#if>
<#if bigdecimal??>import java.math.BigDecimal;</#if>
import lombok.Data;

@Data
public class ${tableItem.beanName} implements Serializable {

    private static final long serialVersionUID = 1L;
<#-- field -->
<#list tableItem.columnList as pk>
    <#assign exist=false>
	<#-- 忽略字段 -->
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
}
```
生成的文件BigMatch.java
```java
package com.fline.entity;

import java.io.Serializable;

import java.util.Date;

import lombok.Data;

@Data
public class BigMatch implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 注释
     */
    private Integer areaId;
    /**
     * 注释
     */
    private String areaName;
    /**
     * 注释
     */
    private Integer subject_Id;
    /**
     * 注释
     */
    private String subjectName;
    /**
     * 注释
     */
    private String banner;
    /**
     * 注释
     */
    private Date starttime;
    /**
     * 注释
     */
    private Date endtime;
    /**
     * 注释
     */
    private String memo;

}
```
------------


###帮我改进
#####欢迎issue和pull request
