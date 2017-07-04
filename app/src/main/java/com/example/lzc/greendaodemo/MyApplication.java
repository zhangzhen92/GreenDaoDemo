package com.example.lzc.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.lzc.greendaodemo.dao.userdao.DaoMaster;
import com.example.lzc.greendaodemo.dao.userdao.DaoSession;

/**
 * 类描述：Application类
 * 创建人：zz
 * 创建时间： 2017/2/15 15:00
 */


public class MyApplication extends Application{
    private static MyApplication instance;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        setDataBase();
    }


    private  void setDataBase() {
        //默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 在正式的项目中，需要做一层封装，来实现升级维护。
        mHelper = new DaoMaster.DevOpenHelper(this,"user_db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }


    public SQLiteDatabase getDb() {
        return db;
    }


    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

}
