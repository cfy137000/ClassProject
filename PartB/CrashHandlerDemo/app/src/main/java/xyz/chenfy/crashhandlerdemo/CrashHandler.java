package xyz.chenfy.crashhandlerdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hasee on 2016/7/27.
 * 需要单例
 */
public class CrashHandler implements UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";


    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashTest/log";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();
    private UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    /**
     * 这个是最关键的函数,当程序中有未被捕获的异常,系统将会自动调用该方法
     *
     * @param thread    为出现未捕获异常的线程
     * @param throwable 为尾部获得异常,有了这个throwable,我们就可以得到异常信息
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //导出异常信息到sd卡中
        dumpExceptionToSDCard(throwable);
    }

    private void dumpExceptionToSDCard(Throwable ex) {
        //如果SD卡不存在或无法使用,则无法把异常信息写入SD卡中
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.w(TAG,"sd卡不存在,跳过导出异常信息");
            return;
        }

        File dir = new File(PATH);
        if(!dir.exists()){
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time +FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);//写入时间
            //导出手机基本信息
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);//写入错误日志
            pw.close();
        } catch (Exception e){
            Log.e(TAG,"导出错误信息失败");
        }
    }

    //导出手机的基本信息
    private void dumpPhoneInfo(PrintWriter printWriter) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
        //版本号信息
        printWriter.print("应用版本: ");
        printWriter.println(pi.versionName + "-" + pi.versionCode);

        //手机制造商
        printWriter.println("制造商: " + Build.MANUFACTURER);

        //手机型号
        printWriter.println("型号:" + Build.MODEL);

        //cpu架构
        printWriter.println("CPU ABI :" + Build.CPU_ABI);


    }
}
