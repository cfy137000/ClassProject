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
    public void createBean(Context context, TestBean testBean) {
        MyDBHelper myDBHelper = new MyDBHelper(context, "aa.db", null, 1);
        SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
        Class clazz = testBean.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        List<String> columnType = new ArrayList<>();
        List<String> columnName = new ArrayList<>();
        List<String> columnVal = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.getType().getSimpleName().equals("String")) {
                columnType.add("text");
                columnName.add(declaredField.getName());
                try {
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

        String sql = "create table if not exists "
                + clazz.getSimpleName() +
                "(id integer primary key autoincrement";
        for (int i = 0; i < columnType.size(); i++) {
            sql = sql + "," + columnName.get(i) + " " + columnType.get(i);
        }
        sql += ")";
        Log.d("DBUtil", sql);
        writableDatabase.execSQL(sql);
        //插入数据
        String insert = "insert into " + clazz.getSimpleName() + "(";
        for (String s : columnName) {
            insert = insert + s + ",";
        }
        insert = insert.substring(0,insert.length()-1);
        insert += ")values(";
        for (String s : columnName) {
            insert +="?,";
        }
        insert = insert.substring(0,insert.length()-1);
        insert += ")";
        Log.d("DBUtil", insert);
        String[] array = new String[columnVal.size()];
        writableDatabase.execSQL(insert,columnVal.toArray(array));

        Cursor cursor = writableDatabase.rawQuery("select * from "+clazz.getSimpleName(),null);
        Log.d("DBUtil", "cursor.getCount():" + cursor.getCount());
        cursor.close();
    }

}
