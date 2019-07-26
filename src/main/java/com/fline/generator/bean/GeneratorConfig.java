package com.fline.generator.bean;

import java.util.List;

/**
 * @since 2019年7月26日 下午5:18:37
 *
 */
public class GeneratorConfig {
	List<TemplateItem> templateList;

	private JdbcInfo jdbcInfo;

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
