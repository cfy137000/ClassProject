package com.lanou.chenfengyao.databasedemo.greendao;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by ChenFengYao on 16/8/23.
 * GreenDao操作的单例类
 */
public class GreenDB {
    private static final AtomicReference<GreenDB> INSTANCE = new AtomicReference<GreenDB>();

    public static GreenDB getInstance() {
        for (; ; ) {
            GreenDB current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new GreenDB();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private GreenDB() {

    }
}
