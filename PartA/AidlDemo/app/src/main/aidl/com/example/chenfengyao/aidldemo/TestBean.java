package com.example.chenfengyao.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ChenFengYao on 15/11/8.
 */
public class TestBean implements Parcelable{
    String testString;
    int testInt;
    public TestBean(){}

    protected TestBean(Parcel in) {
        testString = in.readString();
        testInt = in.readInt();
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel in) {
            return new TestBean(in);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testString);
        dest.writeInt(testInt);
    }
}
