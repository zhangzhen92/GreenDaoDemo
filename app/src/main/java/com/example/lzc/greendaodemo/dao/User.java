package com.example.lzc.greendaodemo.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 类描述：greendao 实体类
 * 创建人：zz
 * 创建时间： 2017/2/15 14:42
 */

@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;                                                  //大写的Long为AUTOINCREMENT，小写的为AUTOINCREMENT NOTNULL
    private String name;
    private String passWord;
    @Generated(hash = 569598642)
    public User(Long id, String name, String passWord) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
