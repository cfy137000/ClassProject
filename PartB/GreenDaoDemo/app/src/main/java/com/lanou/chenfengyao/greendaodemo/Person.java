package com.lanou.chenfengyao.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ChenFengYao on 16/9/17.
 * 实体类
 */
@Entity
public class Person {
    @Id(autoincrement = true)
    Long id;

    private String name;
    private int age;



 

    @Generated(hash = 1024547259)
    public Person() {
    }






    @Generated(hash = 1145075130)
    public Person(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }






    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }






    public Long getId() {
        return this.id;
    }






    public void setId(Long id) {
        this.id = id;
    }



 


}
