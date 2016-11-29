package com.lanou.chenfengyao.examdemo.internet;

import java.util.concurrent.atomic.AtomicReference;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/22,
 * otherwise, I do not know who create it either.
 */
public class Test {
    private static final AtomicReference<Test> INSTANCE = new AtomicReference<Test>();

    public static Test getInstance() {
        for (; ; ) {
            Test current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new Test();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private Test() {
    }
}
