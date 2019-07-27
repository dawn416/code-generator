package com.fline.generator.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @since 2019年7月26日 下午5:18:37
 *
 */
@XStreamAlias("generator")
public class GeneratorConfig {
    private List<TemplateItem> templateList;
    private JdbcInfo jdbcInfo;
    private List<TypeConvert> typeConvertList;

    @Override
    public String toString() {
        return "GeneratorConfig [templateList=" + templateList + ", jdbcInfo=" + jdbcInfo + ", typeConvertList="
                + typeConvertList + "]";
    }

    public List<TypeConvert> getTypeConvertList() {
        return typeConvertList;
    }

    public void setTypeConvertList(List<TypeConvert> typeConvertList) {
        this.typeConvertList = typeConvertList;
    }

    public List<TemplateItem> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateItem> templateList) {
        this.templateList = templateList;
    }

    public JdbcInfo getJdbcInfo() {
        return jdbcInfo;
    }

    public void setJdbcInfo(JdbcInfo jdbcInfo) {
        this.jdbcInfo = jdbcInfo;
    }

}
