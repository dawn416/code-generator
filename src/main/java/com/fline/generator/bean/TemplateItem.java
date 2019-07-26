package com.fline.generator.bean;

/**
 * @since 2019年7月25日 下午6:49:53
 *
 */
public class TemplateItem {
	private String templatePackage;
	private String templateFile;

	/**
	 * @param templatePackage
	 * @param templateFile
	 */
	public TemplateItem(String templatePackage, String templateFile) {
		super();
		this.templatePackage = templatePackage;
		this.templateFile = templateFile;
	}

	public String getTemplatePackage() {
		return templatePackage;
	}

	public void setTemplatePackage(String templatePackage) {
		this.templatePackage = templatePackage;
	}

	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

}
