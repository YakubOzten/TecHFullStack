package org.yakub.database;

abstract public class DataBaseInformation {

    protected  String url;
    protected String user;
    protected  String password;
    protected String fornameData;
    protected  String platform;

    abstract public  void databaseInfo();

    public DataBaseInformation() {
        //default Postgresql
        this.url="jdbc:postgresql://localhost:5432/sample";
        this.user="postgres";
        this.password="Sirdas912";
        this.platform="postgres";
        this.fornameData="org.postgresql.Driver";
    }

    public DataBaseInformation(String url, String user, String password, String fornameData, String platform) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.fornameData = fornameData;
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFornameData() {
        return fornameData;
    }

    public void setFornameData(String fornameData) {
        this.fornameData = fornameData;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
