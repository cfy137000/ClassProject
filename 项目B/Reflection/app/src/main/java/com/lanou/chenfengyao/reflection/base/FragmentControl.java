package com.lanou.chenfengyao.reflection.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

/**
 * Created by ChenFengYao on 16/7/18.
 */
public class FragmentControl {
    private FragmentManager fragmentManager;
    private Stack<BaseFragment> fragmentStack;

    public FragmentControl(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        fragmentStack = new Stack<>();
    }

    /**
     *
     * @return 是否有Fragment可以返回
     */
    public boolean backFragment() {
        if (fragmentStack.size() > 2) {
            return false;
        }
        BaseFragment fragment = fragmentStack.pop();
        BaseFragment preFragment = fragmentStack.peek();
        fragmentManager.beginTransaction()
                .show(fragment)
                .remove(preFragment)
                .commit();
        return true;
    }

    public void addFragment(BaseFragment baseFragment, int frameId
            ,boolean hideBefore) {
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction
                .add(frameId,baseFragment);
        if(hideBefore){
            BaseFragment before = fragmentStack.peek();
            fragmentTransaction.hide(before);
        }
        fragmentTransaction.commit();
        fragmentStack.push(baseFragment);

    }
}
