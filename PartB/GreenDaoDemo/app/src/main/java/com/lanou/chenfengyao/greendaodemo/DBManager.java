package com.lanou.chenfengyao.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by ChenFengYao on 16/9/17.
 * 数据库管理者单例
 */
public class DBManager {
    private final static String dbName = "test.db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    public void upData(Person person){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PersonDao personDao = daoSession.getPersonDao();
        personDao.update(person);
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(Person user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PersonDao userDao = daoSession.getPersonDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param persons
     */
    public void insertUserList(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PersonDao userDao = daoSession.getPersonDao();
//        userDao.insertOrReplaceInTx(persons);
        userDao.insertInTx(persons);
    }

    public List<Person> getAllPerson(){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PersonDao personDao = daoSession.getPersonDao();
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder();
        List<Person> list = queryBuilder.list();
        return list;
    }

    public void deleteAll(){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PersonDao personDao = daoSession.getPersonDao();
        personDao.deleteAll();
    }
}
