package com.lanou.chenfengyao.databasedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lanou.chenfengyao.databasedemo.database.DaoMaster;
import com.lanou.chenfengyao.databasedemo.database.DaoSession;
import com.lanou.chenfengyao.databasedemo.database.TestBean;
import com.lanou.chenfengyao.databasedemo.database.TestBeanDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

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

        testBeanDao.deleteAll();

        TestBean testBean = new TestBean();
        testBean.setDate(new Date());
        testBean.setName("张三");
        testBean.setScore(100);
        //插入单挑数据
        testBeanDao.insert(testBean);
        List<TestBean> testBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestBean bean = new TestBean();
            bean.setName("李四" + i);
            bean.setScore(i);
            bean.setDate(new Date());
            testBeanList.add(bean);

        }
        for (int i = 0; i < 10; i++) {
            TestBean bean = new TestBean();
            bean.setName("李四" + i);
            bean.setScore(i*10);
            bean.setDate(new Date());
            testBeanList.add(bean);

        }
        //添加数据集合
        testBeanDao.insertInTx(testBeanList);

        //查询指定表里的所有数据
        for (TestBean bean : testBeanDao.queryBuilder().list()) {
            Log.d("MainActivity", bean.getName());
        }

        QueryBuilder<TestBean> queueBuilder = testBeanDao.queryBuilder();
        queueBuilder.where(TestBeanDao.Properties.Name.eq("李四1")
               ,TestBeanDao.Properties.Score.eq(10) );
        for (TestBean bean : queueBuilder.build().list()) {
            Log.d("MainActivity", bean.toString());
            //通过查询删除
            testBeanDao.delete(bean);

        }
        for (TestBean bean : testBeanDao.queryBuilder().list()) {
            Log.d("MainActivity", bean.toString());
        }

    }
}
