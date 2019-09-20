package com.basu.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "usercontacts")
public class UserContacts {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "memberId")
    private String memberId;

    @Property(nameInDb = "contactNo")
    private String contactNo;


    /******* CONTACT NAMES **********/
    @Property(nameInDb = "contactName")
    private String contactName;

    /******* CONTACT COUNTRY CODE **********/
    @Property(nameInDb = "contactPin")
    private String contactPin;

    @Generated(hash = 966219151)
    public UserContacts(Long id, String memberId, String contactNo,
            String contactName, String contactPin) {
        this.id = id;
        this.memberId = memberId;
        this.contactNo = contactNo;
        this.contactName = contactName;
        this.contactPin = contactPin;
    }

    @Generated(hash = 1671479911)
    public UserContacts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPin() {
        return contactPin;
    }

    public void setContactPin(String contactPin) {
        this.contactPin = contactPin;
    }
}
