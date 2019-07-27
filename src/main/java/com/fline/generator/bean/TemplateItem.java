package com.fline.generator.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @since 2019年7月25日 下午6:49:53
 *
 */
@XStreamAlias("templateItem")
public class TemplateItem {
    private String templatePackage;
    private String templateFile;
    private String fileName;

    @Override
    public String toString() {
        return "TemplateItem [templatePackage=" + templatePackage + ", templateFile=" + templateFile + ", fileName="
                + fileName + "]";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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
