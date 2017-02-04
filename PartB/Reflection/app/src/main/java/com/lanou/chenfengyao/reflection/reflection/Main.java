package com.lanou.chenfengyao.reflection.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ChenFengYao on 16/7/18.
 */
public class Main {
    public static void main(String args[]){
        try {
            Class<?> aClass = Class.forName("com.lanou.chenfengyao.reflection.reflection.Person");
            Object o = aClass.newInstance();
            System.out.println(o.getClass().getSimpleName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Person person = new Person();
        System.out.println(person.getFlag());
        Class personClass = person.getClass();
        try {
            Field flag = personClass.getDeclaredField("flag");
            flag.setAccessible(true);
            flag.set(person,true);
            System.out.println(person.getFlag());
            System.out.println("----------");
            Method setFlag = personClass.getDeclaredMethod("setFlag", boolean.class);
            setFlag.setAccessible(true);
            setFlag.invoke(person,true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
