/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.basu.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by suhrit on 16/11/18.
 */

@Entity(nameInDb = "user")
public class User {

    @Id(autoincrement = true)
    private Long id;

    @Property
    private int member_table_id;

    @Property(nameInDb = "memberId")
    private String memberId;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "email")
    private String email;

    @Property(nameInDb = "country_code")
    private String country_code;

    @Property(nameInDb = "phone_no")
    private String phone_no;

    @Property(nameInDb = "address_1")
    private String address_1;

    @Property(nameInDb = "address_2")
    private String address_2;

    @Property(nameInDb = "state")
    private String state;

    @Property(nameInDb = "city")
    private String city;

    @Property(nameInDb = "zip")
    private String zip;

    @Property(nameInDb = "country")
    private String country;

    @Property(nameInDb = "pin")
    private String pin;

    @Property(nameInDb = "car_make")
    private String car_make;

    @Property(nameInDb = "car_model")
    private String car_model;

    @Property(nameInDb = "license_plate_number")
    private String license_plate_number;

    public int getMember_table_id() {
        return member_table_id;
    }

    public void setMember_table_id(int member_table_id) {
        this.member_table_id = member_table_id;
    }

    @Property(nameInDb = "encodedImage")
    private String encodedImage;

    @Generated(hash = 1093098650)
    public User(Long id, int member_table_id, String memberId, String name,
            String email, String country_code, String phone_no, String address_1,
            String address_2, String state, String city, String zip, String country,
            String pin, String car_make, String car_model,
            String license_plate_number, String encodedImage) {
        this.id = id;
        this.member_table_id = member_table_id;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.country_code = country_code;
        this.phone_no = phone_no;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.pin = pin;
        this.car_make = car_make;
        this.car_model = car_model;
        this.license_plate_number = license_plate_number;
        this.encodedImage = encodedImage;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getState() {
        return state;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCar_make() {
        return car_make;
    }

    public void setCar_make(String car_make) {
        this.car_make = car_make;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getLicense_plate_number() {
        return license_plate_number;
    }

    public void setLicense_plate_number(String license_plate_number) {
        this.license_plate_number = license_plate_number;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}