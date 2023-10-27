package org.yakub.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 public class DataBaseConnection extends DataBaseInformation{

    //field
    private  final String url=super.getUrl();
    private String user=super.getUser();
    private String password=super.getPassword();
    private String platform=super.getPlatform();
    private String forNameData=super.getFornameData();

    // Gövdesiz metot


    private Connection connection;

    // Singleton design Pattern (Class)
    private  static DataBaseConnection instance;

    // Singleton design Pattern (Constructor)
private  DataBaseConnection(){
    try {
        Class.forName(this.forNameData);
        System.out.println("Database Class yüklendi");
        connection= DriverManager.getConnection(url,user,password);
        System.out.println("Database bağlantısı başarıılı");

    }catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
    } catch (SQLException e){
throw new RuntimeException(e);
    }
}

public  static  DataBaseConnection getInstance(){
    try {
        // Eğer connection null veya kapalı ise
        // yeni instance oluştur
        if (instance == null || instance.connection.isClosed()) {
            instance = new DataBaseConnection() {

            };
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return instance;
}


     public Connection getConnection() {
         return connection;
     }

     public void setConnection(Connection connection) {
         this.connection = connection;
     }

     @Override
    public void databaseInfo() {
        System.out.println("Database Information: " + super.getUrl() + " " + super.getFornameData() + " " + super.getPassword() + " " + super.getUser());
    }

     public static void main(String[] args) {
         //DataBaseConnection databaseConnection = new DataBaseConnection();
     }




}
