// MyTestAIDL.aidl
package com.example.chenfengyao.aidldemo;

// Declare any non-default types here with import statements
import com.example.chenfengyao.aidldemo.TestBean;
import com.example.chenfengyao.aidldemo.AnotherBean;
interface MyTestAIDL {
    void controlService(int flag);
    void changeData(in TestBean testBean);
    void changeAnother(in AnotherBean anotherbean);
}
