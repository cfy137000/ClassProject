package com.lanou.chenfengyao.databasedemo.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ChenFengYao on 16/8/23.
 * GreenDao的数据类
 * 数据类需要用Entity来修饰
 */
@Entity
public class Person {
    @Id
    private long id;
    private String name;
    private int score;

    @Generated(hash = 844292295)
    public Person(long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
