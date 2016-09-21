package com.lanou.chenfengyao.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
//http://www.tuicool.com/articles/63I3EfB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBManager dbManager = DBManager.getInstance(this);
        dbManager.deleteAll();
        Person person0 = new Person();
        person0.setName("ceshi");
        person0.setAge(2);
        dbManager.insert(person0 );
//        dbManager.insert(this);
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setName("名字" + i);
            person.setAge(i);
//            person.setId(i);
            list.add(person);
        }
        dbManager.insert(list);
        for (Person person1 : dbManager.getAllPerson()) {
            Log.d("MainActivity", "person1:" + person1);
        }
//        dbManager.deleteAll();
//
//        dbManager.insertUserList(list);
//
//        List<Person> allPerson = dbManager.getAllPerson();
//        Log.d("MainActivity", "allPerson.size():" + allPerson.size());
//        for (Person person : allPerson) {
//            Log.d("MainActivity", "person:" + person);
//            person.setName(person.getName() + "改");
//            dbManager.upData(person);
//        }
//
//
//        List<Person> allPerson2 = dbManager.getAllPerson();
//        Log.d("MainActivity", "allPerson2.size():" + allPerson2.size());
//        for (Person person : allPerson2) {
//            Log.d("MainActivity", "person:" + person);
//        }
    }
}
