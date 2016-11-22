package com.lanou.chenfengyao.reflection.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenFengYao on 16/7/20.
 */
public class DBUtil {

    public void createBean(Context context, Object testBean) {
        // 打开数据库
        MyDBHelper myDBHelper = new MyDBHelper(context, "aa.db", null, 1);
        SQLiteDatabase writableDatabase
                = myDBHelper.getWritableDatabase();
       //获得 数据类的类类型
        Class clazz = testBean.getClass();

        //获得所有属性
        Field[] declaredFields = clazz.getDeclaredFields();

        //存放列的 类型
        List<String> columnType = new ArrayList<>();
        //存放 列名
        List<String> columnName = new ArrayList<>();
        //存放 值
        List<String> columnVal = new ArrayList<>();

        //遍历 所有属性
        for (Field declaredField : declaredFields) {
            //让属性可以访问
            declaredField.setAccessible(true);
            //如果 属性是String
            if (declaredField.getType().getSimpleName()
                    .equals("String")) {
                //列类型 添加一个text
                columnType.add("text");
                //列名 添加 属性名
                columnName.add(declaredField.getName());
                try {
                    //把值 添加的 值的集合
                    columnVal.add(declaredField.get(testBean).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (declaredField.getType().getSimpleName().equals("int")) {
                columnType.add("integer");
                columnName.add(declaredField.getName());
                try {
                    columnVal.add(declaredField.get(testBean).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //建表语句
        String sql = "create table if not exists "
                + clazz.getSimpleName() +
                "(id integer primary key autoincrement";
        for (int i = 0; i < columnType.size(); i++) {
            sql = sql + "," + columnName.get(i) + " " + columnType.get(i);
        }
        sql += ")";
        Log.d("DBUtil", sql);
        //执行SQL语句
        writableDatabase.execSQL(sql);

        //插入数据
        String insert = "insert into " + clazz.getSimpleName() + "(";
        for (String s : columnName) {
            insert = insert + s + ",";
        }
        //执行完上面的循环,会在最后 多加一个逗号
        insert = insert.substring(0,insert.length()-1);
        insert += ")values(";
        for (String s : columnName) {
            insert +="?,";
        }
        insert = insert.substring(0,insert.length()-1);
        insert += ")";
        //插入的sql语句完成
        Log.d("DBUtil", insert);

        String[] array = new String[columnVal.size()];
        writableDatabase.execSQL(insert,columnVal.toArray(array));

        Cursor cursor = writableDatabase.rawQuery("select * from "+clazz.getSimpleName(),null);
        Log.d("DBUtil", "cursor.getCount():" + cursor.getCount());
        cursor.close();
    }

}
