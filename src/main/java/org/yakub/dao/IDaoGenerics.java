package org.yakub.dao;
import org.yakub.database.DataBaseConnection;
import org.yakub.dto.RegisterDto;

import java.sql.Connection;
import java.util.ArrayList;

public interface IDaoGenerics <T>{
    public String speedData(Long id);
    public String allDelete();
    ////////////////////////////////////////

    //C R U D
    // CREATE
    public T create(T t);

    // FIND BY ID
    public T findById(Long id);
    public T findByEmail(String email);

    // LIST
        public ArrayList<T> list();

    // UPDATE
    public T update(Long id,T t);
    public RegisterDto updateRemaing(Long id, T t);

    // DELETE
    public T deleteById(RegisterDto registerDto);

    ////////////////////////////////////////
    // interface Gövdeli yapmak istiyorsak; default yazacağız.
    default Connection getInterfaceConnection(){ //java.sql.Connection

        return DataBaseConnection.getInstance().getConnection();
    }

}
