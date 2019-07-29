package com.fline.generator.bean;

import javax.xml.bind.annotation.XmlType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @since 2019年7月25日 下午6:49:53
 *
 */
@XStreamAlias("templateItem")
@XmlType(name = "templateItem")
public class TemplateItem {
    @XStreamAsAttribute
    private String id;
    private String targetPackage;
    private String templateFile;
    private String targetFileName;
    private String targetProject;

    @Override
    public String toString() {
        return "TemplateItem [id=" + id + ", targetPackage=" + targetPackage + ", templateFile=" + templateFile
                + ", targetFileName=" + targetFileName + ", targetProject=" + targetProject + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

}
