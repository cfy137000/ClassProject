package com.lanou.chenfengyao.reflection.reflection;

/**
 * Created by ChenFengYao on 16/7/19.
 */
public class Person {
    private boolean flag;
    public String name;

    public Person() {
        flag = false;
    }

    private void setFlag(boolean flag){
        System.out.print("我不能被调用");
    }
    public boolean getFlag(){
        return flag;
    }
}
