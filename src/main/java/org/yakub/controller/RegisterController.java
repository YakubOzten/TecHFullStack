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

    @Override
    public RegisterDto findByEmail(String email) {
        return null;
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

    @Override
    public RegisterDto updateRemaing(Long id, RegisterDto registerDto) {
        return null;
    }
    // DELETE
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {
        return null;
    }


    ///////////////////////////////////////////////////////////////////////////////////

    //IS LOGIN

}//end class
