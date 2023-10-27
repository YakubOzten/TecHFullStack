package org.yakub.files;


import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.Date;
import java.util.UUID;
@Data
@Log4j2

public class    FilePathData {
   //variable
    private String id;
    private String pathFileName;
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
                // dosyaya default 4 hak verildi.
                fileWriterRemaingNumber(4);
                System.out.println("Kalan Hak:"+fileReaderRemainingNumber());
            }else {
                System.out.println(path + " Böyle bir dosya adı zaten var tekrardan oluşturulmadı !!!");
            }


        }catch (Exception e){
           e.printStackTrace();
        }
    }
    //// METOTLAR //////////////////////////////////
    // fileReader
    public Integer fileReaderRemainingNumber() {
        String rows; // okunan satır
        Integer numberOfRights = null; //kalan hak sayısı
        String readRows;
        StringBuilder stringBuilder=new StringBuilder();
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(this.path))) {
        while ((rows=bufferedReader.readLine())!=null){
            stringBuilder.append(rows);
        }
        readRows=stringBuilder.toString();
        numberOfRights=Integer.valueOf(readRows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  numberOfRights;
    }
    // FileWriter
    public void fileWriterRemaingNumber(int counter) {
        try (BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(this.path,false))){
            // Dikkat: File Yazarken Stirnf olarak yazmalıyız yoksa EOT olarak
            bufferedWriter.write(String.valueOf(counter));
            bufferedWriter.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }

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



}
// Primitive Type ile Wrapper Type arasındaki farklar ?
// Heap memory ile Stack memory nedir arasındaki farklar ?
// String neden primitive Type'dır ?
// Compiler Interpreter nedir ?
// Java 8 ile gelen stream seri ve paralel nedir ?