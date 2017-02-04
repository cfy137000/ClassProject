package xyz.chenfy.rxjavademo.fordb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.chenfy.rxjavademo.R;

/**
 * Created by ChenFengYao on 16/8/12.
 */
public class DBAty extends AppCompatActivity implements View.OnClickListener {
    private Button addBtn, queryBtn;
    private SQLiteDatabase mWritableDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        addBtn = (Button) findViewById(R.id.add_btn);
        queryBtn = (Button) findViewById(R.id.query_btn);

        addBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);

        MyOpenHelper myOpenHelper = new MyOpenHelper(this, "test.db", null, 1);
        mWritableDatabase = myOpenHelper.getWritableDatabase();
//        for (int i = 0; i < 10; i++) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("name","张" + i);
//            contentValues.put("age",i);
//            mWritableDatabase.insert("person",null,contentValues);
//
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                break;
            case R.id.query_btn:
                query("张3", new Action1<Person>() {
                    @Override
                    public void call(Person person) {
                        printName(person);
                    }
                });
                break;
        }
    }

    public void query(String name,Action1<Person> action1){
        Observable.just(name)
                .flatMap(new Func1<String, Observable<Person>>() {
                    @Override
                    public Observable<Person> call(String s) {
                        String sql = "select * from person where name = ?";
                        String args[]  = new String[1];
                        args[0] = s;
                        Cursor cursor = mWritableDatabase.rawQuery(sql, args);
                        Person person = new Person();
                        while (cursor.moveToNext()){
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            int age = cursor.getInt(cursor.getColumnIndex("age"));
                            person.setName(name);
                            person.setAge(age);
                        }
                        cursor.close();
                        Log.d("DBAty-query", Thread.currentThread().getName());
                        return Observable.just(person);
                    }
                })
                .subscribeOn(Schedulers.io())//查询数据库是在IO线程
                .observeOn(AndroidSchedulers.mainThread())//返回结果是在主线程,l
                .subscribe(action1);
    }

    public void printName(Person person){
        System.out.println(person);
        Log.d("DBAty-print", Thread.currentThread().getName());
    }
}
