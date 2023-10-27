package org.yakub.controller;

import org.yakub.dao.IDaoGenerics;
import org.yakub.dao.RegisterDao;
import org.yakub.dto.RegisterDto;
import org.yakub.files.FilePathData;

import java.util.ArrayList;
import java.util.Scanner;

public class RegisterController implements IDaoGenerics<RegisterDto> {
    private FilePathData filePathData=new FilePathData();

    private RegisterDao registerDao=new RegisterDao();

    private Integer counter=filePathData.fileReaderRemainingNumber();
    // SPEED DATA
    @Override
    public String speedData(Long id) {
        return  registerDao.speedData(id);
    }
//ALL DELETE
    @Override
    public String allDelete() {
        return registerDao.allDelete();
    }
    ////////////////////////////////////////////////////
    // CREATE
    @Override
    public RegisterDto create(RegisterDto registerDto) {
           return registerDao.create(registerDto);
    }
    // FIND BY ID
    @Override
    public RegisterDto findById(Long id) {
        return registerDao.findById(id);
    }
    // LIST
    @Override
    public ArrayList<RegisterDto> list() {
        return registerDao.list();
    }
    // UPDATE
    @Override
    public RegisterDto update(Long id, RegisterDto registerDto) {
        return registerDao.update(id,registerDto);
    }
    // DELETE
    @Override
    public RegisterDto delete(RegisterDto registerDto) {
        return registerDao.delete(registerDto);
    }
    ///////////////////////////////////////////////////////////////////////////////////

    //IS LOGIN
    private boolean isLogin(){
        String userEmail,userPassword;
        Scanner klavye=new Scanner(System.in);
        System.out.println("Email Adresinizi Giriniz");
        userEmail=klavye.nextLine();
        System.out.println("Password Giriniz");
        userPassword=klavye.nextLine();
        //is Login
        if(userEmail.equals("root")&&userPassword.equals(("root")))
            return true;
        else {
            System.out.println("\nKalan Hakkınız: " + (counter - 1));
            System.out.println("Sifre veya email yanlış girdiniz.");
            counter--;
            // kalan hakkı dosyaya yaz
            filePathData.fileWriterRemaingNumber(counter);
        }
        return  false;
    }//end login
    public  void adminPage(){
        System.out.println("Admin Sayfasına Hış geldiniz.");
        System.out.println("1-)Kitapları Listele\n2-)Kitap Ekle\n3-)Kitap Sil\n4-)Kitap Güncelle\5-)Çıkış");
    }
    // REDIRECT
    private  void isPageRedirect(){
        Integer counter= filePathData.fileReaderRemainingNumber();
        // kalan :0 ise sistemden çıkış yapsın
        if(counter==0){
            System.out.println("Giriş hakkınız kalmadı Hesabınını Bloke oldu");
            System.out.println("Admin'e başvuru yapınız. Çıkış yapıldı");
            System.exit(0);
        }else if (counter >= 1){
         Boolean result=isLogin();
         if(result) adminPage();
         else isLogin();
        }
    }//end isPageRedirect
    public  void  commonMethodLibraries(){
        isPageRedirect();
    }
}//end class
