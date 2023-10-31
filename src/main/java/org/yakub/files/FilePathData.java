package org.yakub.files;


import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
@Data
@Log4j2

public class    FilePathData {
   //variable
    private String id;
    private String pathFileName;
    private String pathDirectoryName;
    private String url;
    private String path;
    private File file;
    private Date systemCreatedDate;
    // parametresiz constructor
    // URL URI
    // Relative path absolute path
    public  FilePathData(){
        id = UUID.randomUUID().toString();
        systemCreatedDate=new Date(System.currentTimeMillis());
        url="C:\\io\\techcareer\\full_4";
        pathFileName = "\\MyRemainingRight.txt";
        //path="C:\\io\\techcareer\\full_4\\MyRemainingRight.txt";
        path=url.concat(pathFileName);
        file =new File(path);
        try {
            // Böyle bir dosya var mı ? eğer varsa tekrar oluşturma
            if(file.createNewFile()){
              System.out.println("Boyle Bir Dosya yoktur"+path+"adında dosya oluşturuldu.");
              System.out.println("Permission:Çalışabilir mi?"+ file.canExecute()+
                      "okunabilir mi?"+ file.canRead()+"Yazılabilir mi"+ file.canWrite());
                System.out.println("ID: " + this.id + "URL: " + this.path + " " + file.getName() + " PATH: " + file.getPath());
                // toString
                System.out.println("ID: " + this.id + " URL: " + this.url + " Hash Code: " + file.hashCode());
                // logFileWriter();// Writer
                // logFileReader();// Reader
                // fileIsDelete(); // Delete
            }else {
                System.out.println(path + " Böyle bir dosya adı zaten var tekrardan oluşturulmadı !!!");
            }
        }catch (Exception e){
           e.printStackTrace();
        }
    }
    // file Date Locale
    private String localeDateTime(){
        Locale locale= new Locale("tr","TR");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMMM-yyyy",locale);
        Date date=new Date();
        String changeDate=simpleDateFormat.format(date);
        return changeDate;
    }
    //// METOTLAR //////////////////////////////////
    // fileReader
    public void logFileReader() {
        String rows; // okunan satır
        String builderToString;
        StringBuilder stringBuilder=new StringBuilder();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(this.url))) {
        while ((rows=bufferedReader.readLine())!=null){
            stringBuilder.append(rows).append("\n");
        }
            builderToString=stringBuilder.toString();
            System.out.println("LOGLAMA:\n" +builderToString);
        }catch (Exception e){
            e.printStackTrace();
        }

    }//end Reader
    // FileWriter
    public void logFileWriter(String email,String password) {
        try (BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(this.url,true))){
            // Dikkat: File Yazarken Stirnf olarak yazmalıyız yoksa EOT olarak
            String data="[ "+ localeDateTime()+" ] "+email+" "+password;
            bufferedWriter.write(data+"\n");
            bufferedWriter.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }//end Writer


    public String specialFileCreate(String fileName){
        this.id=UUID.randomUUID().toString();
        this.systemCreatedDate=new Date(System.currentTimeMillis());
        pathFileName = "\\"+fileName.concat(".txt");
        pathDirectoryName=FilePathUrl.MY_FILE_PATH;
        url=pathDirectoryName.concat(pathFileName);
        this.file=new File(url);
        try {// Böyle bir dosya var mı?
            if(file.createNewFile()){
                System.out.println(pathFileName+"Böyle bir dosya yok ama oluşturuldu.");
            }else {
                String fileNameData=pathFileName+"Böyle bir dosya var tekrardan oluşturulmadı.";
                System.out.println(fileNameData);
                return  fileNameData;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return url+"olusturuldu";
    }
    // Dosya Listele
    public  void fileList(){
        File filelist=new File(FilePathUrl.MY_FILE_PATH);
        for (File temp:filelist.listFiles()){
            System.out.println(temp.getName());
        }
    }
    // File Delete
    public  void fileIsDelete(){
        Scanner klavye=new Scanner(System.in);
        //dosya isimleri göster
        fileList();
        System.out.println("Silmek istediğniiz dosya adını yazınız");
        String fileName=klavye.nextLine().concat(".txt");
        pathDirectoryName=FilePathUrl.MY_FILE_PATH;
        url=pathDirectoryName.concat("\\").concat(fileName);
        System.out.println("Dosya uzantısı"+url);
        char chooise;
        System.out.println(fileName+"bu dosyayı silmek istermisiniz?E/H");
        chooise=klavye.nextLine().charAt(0);
        if (chooise=='E'|| chooise=='e'){
            try {
                File fileDelete=new File(url);
                if(fileDelete.exists()){
                    fileDelete.delete();
                    System.out.println("Dosyanız silindi");
                }else{
                    System.out.println("Olmayan dosyayı silememem!!!");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            System.out.println(pathFileName+ "Silinmedi");
        }
    }
    // Informartion
    public void fileProperties(){
        System.out.println("Dosya Özellikler");
        Scanner klavye=new Scanner(System.in);
        // dosya isimleri göster
        fileList();
        System.out.println("Özelliklerine bakmak istediğiniz dosya adınızı yazınız");
        String fileName=klavye.nextLine().concat(".txt");
        pathDirectoryName=FilePathUrl.MY_FILE_PATH;
        url=pathDirectoryName.concat("\\").concat(fileName);
        System.out.println("Dosya uzantısı"+url);
    }

    public String getPathDirectoryName() {
        return pathDirectoryName;
    }

    public void setPathDirectoryName(String pathDirectoryName) {
        this.pathDirectoryName = pathDirectoryName;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GETTER AND SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPathFileName() {
        return pathFileName;
    }

    public void setPathFileName(String pathFileName) {
        this.pathFileName = pathFileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getSystemCreatedDate() {
        return systemCreatedDate;
    }

    public void setSystemCreatedDate(Date systemCreatedDate) {
        this.systemCreatedDate = systemCreatedDate;
    }



}// end class FilePathData
