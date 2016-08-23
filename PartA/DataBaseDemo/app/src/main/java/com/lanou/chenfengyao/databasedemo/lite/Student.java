package com.lanou.chenfengyao.databasedemo.lite;

import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by ChenFengYao on 16/8/23.
 */
public class Student {
    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String name;
    @Default("100")
    private int score;

    @Override
    public String toString() {
        return name + "---" + score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
