package com.lanou.chenfengyao.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ChenFengYao on 16/9/20.
 */
@Entity
public class Car {
    @Id(autoincrement = true)
    Long id;

    private String name;
    private int price;
    @Generated(hash = 1409633413)
    public Car(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Generated(hash = 625572433)
    public Car() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
