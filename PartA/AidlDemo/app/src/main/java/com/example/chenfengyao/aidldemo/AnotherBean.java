package com.example.chenfengyao.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ChenFengYao on 16/7/31.
 */
public class AnotherBean implements Parcelable{
    String name;

    public AnotherBean() {
    }

    protected AnotherBean(Parcel in) {
        name = in.readString();
    }

    public static final Creator<AnotherBean> CREATOR = new Creator<AnotherBean>() {
        @Override
        public AnotherBean createFromParcel(Parcel in) {
            return new AnotherBean(in);
        }

        @Override
        public AnotherBean[] newArray(int size) {
            return new AnotherBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
