package com.lanou.chenfengyao.databasedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lanou.chenfengyao.databasedemo.database.DaoMaster;
import com.lanou.chenfengyao.databasedemo.database.DaoSession;
import com.lanou.chenfengyao.databasedemo.database.TestBean;
import com.lanou.chenfengyao.databasedemo.database.TestBeanDao;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        DaoSession daoSession = daoMaster.newSession();
        TestBeanDao testBeanDao = daoSession.getTestBeanDao();

        TestBean testBean = new TestBean();
        testBean.setDate(new Date());
        testBean.setName("张三");
        testBean.setScore(100);

        testBeanDao.insert(testBean);
    }
}
