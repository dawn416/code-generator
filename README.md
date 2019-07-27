
# 通用JDBC代码生成器

通过自定义freemarker模板生成代码

```xml
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
		<!-- 数据库表名 -->
		<table>big_match</table>
		<!-- 表对应的实体类 -->
		<entity></entity>
		<!-- 字段名是否转换成驼峰命名法 -->
		<camel>false</camel>
	</jdbcInfo>
	<typeConvertList>
		<!-- jdbc转换为java类型对应 -->
		<typeConvert java="String" jdbc="VARCHAR"/>
		<typeConvert java="String" jdbc="CHAR"/>
		<typeConvert java="String" jdbc="TEXT"/>
		<typeConvert java="Integer" jdbc="INT"/>
		<typeConvert java="Integer" jdbc="TINYINT"/>
		<typeConvert java="Integer" jdbc="SMALLINT"/>
		<typeConvert java="Integer" jdbc="TINYINT"/>
		<typeConvert java="Integer" jdbc="INTEGER"/>
		<typeConvert java="Long" jdbc="BIGINT"/>
		<typeConvert java="Double" jdbc="DOUBLE"/>
		<typeConvert java="Double" jdbc="FLOAT"/>
		<typeConvert java="Date" jdbc="DATE"/>
		<typeConvert java="Date" jdbc="DATETIME"/>
		<typeConvert java="Date" jdbc="TIME"/>
		<typeConvert java="Date" jdbc="TIMESTAMP"/>
		<typeConvert java="Decimal" jdbc="DECIMAL"/>
	</typeConvertList>
	<templateList>
		<templateItem>
			<!-- jdbc转换为java类型对应 -->
			<templateFile>test/mysql_template.xml</templateFile>
			<templatePackage>src.main.java.com.fline.dao</templatePackage>
			<fileName>${entity}Mapper.xml</fileName>
		</templateItem>
		<templateItem>
			<templateFile>test/service_template.java</templateFile>
			<templatePackage>src.main.java.com.fline.service</templatePackage>
			<fileName>${entity}Service.java</fileName>
		</templateItem>
		<templateItem>
			<templateFile>test/controller_template.java</templateFile>
			<templatePackage>src.main.java.com.fline.controller</templatePackage>
			<fileName>${entity}Controller.java</fileName>
		</templateItem>
	</templateList>
</generator>
```