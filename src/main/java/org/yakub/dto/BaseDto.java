package org.yakub.dto;

import java.io.Serializable;
import java.util.Date;

public class BaseDto implements Serializable {
   //serileştirme
    public static  final  long serialVersionUID= 1L;

    //Variable
    protected  long id;
    protected Date systemCreatedDate;
    //parametresiz construactor
    public BaseDto(){
        systemCreatedDate=new Date(System.currentTimeMillis());
    }
    // parametreli constructor
    public BaseDto(Long id, Date systemCreatedDate) {
        this.id = id;
        this.systemCreatedDate = systemCreatedDate;
    }
    // Getter And Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setSystemCreatedDate(Date systemCreatedDate) {
        this.systemCreatedDate = systemCreatedDate;
    }

    public long getId() {
        return id;
    }

    public Date getSystemCreatedDate() {
        return systemCreatedDate;
    }
}
