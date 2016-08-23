package com.lanou.chenfengyao.databasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lanou.chenfengyao.databasedemo.lite.LiteDB;
import com.lanou.chenfengyao.databasedemo.lite.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LiteDB liteDB = LiteDB.getInstance();
//        Student student = new Student();
//        student.setName("aa");
//        student.setScore(10);
//        liteDB.insertPerson(student);
//
//        List<Student> students = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Student student1 = new Student();
//            student1.setName("aa-"+i);
//            student1.setScore(i);
//            students.add(student1);
//        }
//        liteDB.insertPerson(students);

        liteDB.queryGoodStudent(new LiteDB.OnQueryListener<List<Student>>() {
            @Override
            public void onQuery(List<Student> students) {
                for (Student student : students) {
                    Log.d("MainActivity", "student:" + student);
                }
            }
        });
    }

}
