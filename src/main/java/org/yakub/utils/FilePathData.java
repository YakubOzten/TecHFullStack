package org.yakub.utils;


import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.Date;
import java.util.UUID;
@Data
@Log4j2

public class FilePathData {
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
            }else {
                System.out.println(path + " Böyle bir dosya adı zaten var tekrardan oluşturulmadı !!!");
            }
            // dosyaya default 4 hak verildi.
            fileWriterRemaingNumber();
            System.out.println("Kalan Hak:"+fileReaderRemainingNumber());

        }catch (Exception e){
           e.printStackTrace();
        }
    }

    private Integer fileReaderRemainingNumber() {
        String rows;
        Integer numberOfRights = null;
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
    private void fileWriterRemaingNumber() {
        try (BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(this.path,false))){
            bufferedWriter.write("4");
            bufferedWriter.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        FilePathData filePathData = new FilePathData();
        System.out.println(filePathData);
    }

}
