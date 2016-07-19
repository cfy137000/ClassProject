package com.lanou.chenfengyao.reflection.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ChenFengYao on 16/7/18.
 */
public class ActivityControl {
    public static LinkedList<BaseAty> atyLinkedList = new LinkedList<>();

    public static void addAty(BaseAty baseAty){
        atyLinkedList.add(baseAty);
    }

    public static void removeAty(BaseAty baseAty){
        atyLinkedList.remove(baseAty);
    }
}
