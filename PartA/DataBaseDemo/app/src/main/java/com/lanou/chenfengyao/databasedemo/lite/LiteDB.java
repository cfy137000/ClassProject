package com.lanou.chenfengyao.databasedemo.lite;

import android.os.AsyncTask;
import android.util.Log;

import com.lanou.chenfengyao.databasedemo.MyApp;
import com.lanou.chenfengyao.databasedemo.greendao.Person;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ChenFengYao on 16/8/23.
 */
public class LiteDB {
    private static LiteDB ourInstance;
    private LiteOrm mLiteOrm;
    ExecutorService mExecutorService = Executors.newFixedThreadPool(5);

    public static LiteDB getInstance() {
        if (ourInstance == null) {
            synchronized (LiteDB.class) {
                if (ourInstance == null) {
                    ourInstance = new LiteDB();
                }
            }
        }

        return ourInstance;
    }

    private LiteDB() {
        mLiteOrm = LiteOrm.newSingleInstance(MyApp.getContext(), "MyDB_Lite");
    }

    public void insertPerson(final Student student) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                mLiteOrm.insert(student);
            }
        };
        mExecutorService.execute(runnable);
    }

    public void insertPerson(List<Student> students) {
        mLiteOrm.insert(students);
    }

    public void queryGoodStudent(OnQueryListener<List<Student>> onQueryListener) {
        MyAsync<List<Student>> myAsync = new MyAsync<List<Student>>(onQueryListener) {
            @Override
            protected List<Student> doInBackground(Void... params) {
                return queryGoodStudentSync();
            }
        };
        myAsync.execute();


    }

    abstract class MyAsync<T> extends AsyncTask<Void,Void,T>{
        OnQueryListener<T> mTOnQueryListener;

        public MyAsync(OnQueryListener<T> TOnQueryListener) {
            mTOnQueryListener = TOnQueryListener;
        }

        @Override
        protected void onPostExecute(T t) {
            super.onPostExecute(t);
            mTOnQueryListener.onQuery(t);
        }
    }

    private List<Student> queryGoodStudentSync(){
        Log.d("LiteDB", Thread.currentThread().getName());
        QueryBuilder<Student> studentQueryBuilder = new QueryBuilder<>(Student.class);
        studentQueryBuilder.where("score > 60", null);
        ArrayList<Student> query = mLiteOrm.query(studentQueryBuilder);

        return query;
    }


    public interface OnQueryListener<T> {
        void onQuery(T t);
    }
}
