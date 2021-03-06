package com.supermax.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.LayoutRes;

import com.supermax.base.common.dialog.QsProgressDialog;
import com.supermax.base.common.http.HttpBuilder;
import com.supermax.base.common.log.L;
import com.supermax.base.common.utils.ImageHelper;
import com.supermax.base.common.utils.QsHelper;

import okhttp3.Response;

/*
 * @Author yinzh
 * @Date   2018/10/12 16:32
 * @Description
 */
public abstract class QsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (isLogOpen()) L.init(true);
        QsHelper.getInstance().init(this);
    }

    public abstract boolean isLogOpen();

    public abstract void initHttpAdapter(HttpBuilder builder);

    public void onActivityCreate(Activity activity) {
    }

    public void onActivityStart(Activity activity) {
    }

    public void onActivityResume(Activity activity) {
    }

    public void onActivityPause(Activity activity) {
    }

    public void onActivityStop(Activity activity) {
    }

    public void onActivityDestroy(Activity activity) {
    }

    public boolean isMainProcess() {
        return getPackageName().equals(getCurrentProcessName());
    }

    /**
     * 获取当前进程名
     */
    public String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) return processName;
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
                break;
            }
        }
        return processName;
    }

    /**
     * 公共progressDialog
     */
    public QsProgressDialog getLoadingDialog() {
        return null;
    }

    public @LayoutRes int loadingLayoutId() {
        return 0;
    }

    public @LayoutRes int emptyLayoutId() {
        return 0;
    }

    public @LayoutRes int errorLayoutId() {
        return 0;
    }

    public void onCommonLoadImage(ImageHelper.Builder builder) {
    }

    public void onCommonHttpResponse(Response response) {
    }

}
