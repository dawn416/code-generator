package com.fline.generator.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @since 2019年7月26日 下午5:19:56
 *
 */
@XStreamAlias("jdbcInfo")
public class JdbcInfo {
    private String driver;
    private String url;
    private String username;
    private String password;
    private String catalog;
    private String scheme;
    private String table;
    private String entity;
    private boolean camel;

    @Override
    public String toString() {
        return "JdbcInfo [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
                + ", catalog=" + catalog + ", scheme=" + scheme + ", table=" + table + ", entity=" + entity + ", camel="
                + camel + "]";
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public boolean isCamel() {
        return camel;
    }

    public void setCamel(boolean camel) {
        this.camel = camel;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
