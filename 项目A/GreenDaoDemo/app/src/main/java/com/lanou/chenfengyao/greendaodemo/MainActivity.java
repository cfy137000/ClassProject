package com.lanou.chenfengyao.greendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lanou3g.greendaodemo.entity.greendao.DaoMaster;
import com.lanou3g.greendaodemo.entity.greendao.DaoSession;
import com.lanou3g.greendaodemo.entity.greendao.PersonEntity;
import com.lanou3g.greendaodemo.entity.greendao.PersonEntityDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 数据库
    private SQLiteDatabase db;
    // 管理者
    private DaoMaster mDaoMaster;
    // 会话
    private DaoSession mDaoSession;
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    private PersonEntityDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatabase();
    }

    private void initDatabase() {
        // 初始化就这个顺序,记着吧 ^_^
        // 此DevOpenHelper类继承自SQLiteOpenHelper,第一个参数Context,第二个参数数据库名字,第三个参数CursorFactory
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"daodemo.db",null);
        db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        PersonEntityDao personDao = mDaoSession.getPersonEntityDao();

        // 删除表内所有数据(我为什么要写这个方法...太简单了吧也),写入前先清空一下,防止id冲突导致报错
        personDao.deleteAll();

        // 下面对数据库内的表进行操作
        // 首先模拟一些数据
        List<PersonEntity> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PersonEntity person = new PersonEntity((long) i,"孙悟空"+i,20+i,"男");
            // 使用这个方法可以一条一条的写入数据库
            personDao.insert(person);
            // 插入或者替换单条数据,就是id重复也可以使用
//            personDao.insertOrReplace(person);
            personList.add(person);
        }
        // 这个方法可以将一个集合直接写入数据库,很方便
//        personDao.insertInTx(personList);
        // 看名字也知道是干嘛的了吧,把数据写入,或者替换
//        personDao.insertOrReplaceInTx(personList);
        // 根据id删除数据 - -! 我真的一定要写出来么
        personDao.deleteByKey(3l);
        // 更新表内元素
        personDao.update(new PersonEntity(5l,"贝吉塔",100,"男"));
        // 查表,结果直接返回到链表中,下面的三种方法都可以进行查表操作,将所有信息都查出来
        List<PersonEntity> queryList = personDao.queryBuilder().list();
//        queryList = personDao.queryBuilder().listLazy();
//        queryList = personDao.loadAll();
        // 输出结果,看一下
        for (PersonEntity personEntity : queryList) {
            Log.d("TAGGG", personEntity.getName());
        }

    }
    private void initView() {
        /** 初始化控件的,没什么用,省略了 */
    }
}
