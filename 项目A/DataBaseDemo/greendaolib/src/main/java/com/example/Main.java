package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {
    public static void main(String[] args){
        Schema schema = new Schema(1, "com.lanou.chenfengyao.databasedemo.database");
        Entity note= schema.addEntity("TestBean");
        note.addIdProperty();
        note.addStringProperty("name").notNull();
        note.addIntProperty("score");
        note.addDateProperty("date");

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
