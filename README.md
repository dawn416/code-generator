
# 通用JDBC代码生成器

通过自定义freemarker模板生成代码,支持自定义参数

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
        <!-- 指定jdbc转换为java类型的对应关系 -->
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
        <!-- 模板参数,可指定多个,${entity}、${table}参数可以使用,id指定了该templateItem的唯一标识,用于freemarker的参数填充 -->
        <templateItem id="mysql_template">
            <!-- 模板文件路径 -->
            <templateFile>codeGenerator/mysql_template.xml</templateFile>
            <!-- 文件的生成项目 -->
            <targetProject>src.main.java</targetProject>
            <!-- 文件的生成路径 -->
            <targetPackage>src.main.java.com.fline.dao.${table}</targetPackage>
            <!-- 生成的文件名 -->
            <targetFileName>${entity}Mapper.xml</targetFileName>
        </templateItem>
        <templateItem>
            <templateFile>codeGenerator/service_template.java</templateFile>
            <targetProject>src.main.java</targetProject>
            <targetPackage>src.main.java.com.fline.service.${table}</targetPackage>
            <targetFileName>${entity}Service.java</targetFileName>
        </templateItem>
        <templateItem>
        <templateFile>codeGenerator/controller_template.java</templateFile>
            <targetProject>src.main.java</targetProject>
            <targetPackage>com.fline.controller.${table}</targetPackage>
            <targetFileName>${entity}Controller.java</targetFileName>
        </templateItem>
    </templateList>
</generator>
```