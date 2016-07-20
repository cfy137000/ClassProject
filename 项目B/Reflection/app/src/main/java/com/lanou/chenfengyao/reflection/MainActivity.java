package com.lanou.chenfengyao.reflection;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lanou.chenfengyao.reflection.db.DBUtil;
import com.lanou.chenfengyao.reflection.db.TestBean;
import com.lanou.chenfengyao.reflection.reflection.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBUtil dbUtil = new DBUtil();
        TestBean testBean = new TestBean();
        testBean.setAge(10);
        testBean.setName("aaa");

        dbUtil.createBean(this,testBean);

//        viewPager = (ViewPager) findViewById(R.id.mainVp);
//        assert viewPager != null;
//        Class aClass = viewPager.getClass();
//        try {
//            Field mOffscreenPageLimit = aClass.getDeclaredField("mOffscreenPageLimit");
//            mOffscreenPageLimit.setAccessible(true);
//            mOffscreenPageLimit.set(viewPager,-1);
//            Method populate = aClass.getDeclaredMethod("populate");
//            populate.setAccessible(true);
//            populate.invoke(viewPager,null);
//            ViewAdapter viewAdapter = new ViewAdapter(getSupportFragmentManager());
//            viewPager.setAdapter(viewAdapter);
//            Object o = mOffscreenPageLimit.get(viewPager);
//            Log.d("MainActivity", "o:" + o);
//            Log.d("MainActivity", "mOffscreenPageLimit:" + mOffscreenPageLimit);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

    }
}
