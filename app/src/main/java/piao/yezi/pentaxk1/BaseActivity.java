package piao.yezi.pentaxk1;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import piao.yezi.pentaxk1.util.CommonUtil;
import piao.yezi.pentaxk1.util.EventUtils;

/**

 * 应用中所有activity的基类
 */
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {


    private FrameLayout containerLeft, containerRight;

    private TextView title;

    protected LayoutInflater inflater;

    protected final int DEFAULT = 0;

    //RefWatcher refWatcher;

    private boolean useEventBus = false;

    protected float mDensity;
    protected int mDensityDpi;
    protected int mAvatarSize;
    protected int mWidth;
    protected int mHeight;
    protected float mRatio;
    protected Context mContext;
    protected Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mDensityDpi = dm.densityDpi;
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
        mRatio = Math.min((float) mWidth / 720, (float) mHeight / 1280);
        mAvatarSize = (int) (50 * mDensity);


        inflater = getLayoutInflater();
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);//强制竖屏 
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//开启沉浸式状态栏

        mContext = this;
        AppManager.getAppManager().addActivity(this);// 添加Activity到堆栈  
//        setBackgroundColor(R.color.white);
        setContentView(getContentView());
        onSetContentViewNext(savedInstanceState);
        if (!isTitleActivity()) {
            unbinder  =  ButterKnife.bind(this);
        }

        initView();
        setListener();
        initData();

        if (useEventBus()) {
            EventUtils.register(this);//eventbus注册
        }

        //refWatcher = PentaxK1.getRefWatcher(this);//leakcanary工具，在ondestory中检测内存泄漏
    }

    /**
     * 某些第三方sdk需要在这做操作
     *
     * @param savedInstanceState
     */
    protected void onSetContentViewNext(Bundle savedInstanceState) {

    }


    protected abstract int getContentView();


    /**
     * 设置整个activity的背景图片,在setContentView方法后调用才有效果
     *
     * @param resId
     */
    protected void setBackground(int resId) {
        View childView;
        ViewGroup contentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        if (contentView.getChildCount() > 0) {
            childView = contentView.getChildAt(0);
            childView.setBackgroundResource(resId);
        }
    }

    /**
     * 设置背景颜色
     *
     * @param color
     */
    protected void setBackgroundColor(int color) {
        View childView;
        ViewGroup contentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        if (contentView.getChildCount() > 0) {
            childView = contentView.getChildAt(0);
            childView.setBackgroundColor(getResources().getColor(color));
        }
    }

    public void showToast(String msg) {
        if (!isFinishing()) {
            CommonUtil.showToast(msg);
        }
    }

    public void showToast(int msgid) {
        if (!isFinishing()) {
            CommonUtil.showToast(mContext.getString(msgid));
        }
    }

    public RxPermissions getRxPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(ConstantKey.IS_DEBUG);
        return rxPermissions;
    }

    /**
     * 是否有标题栏，如果有，需要在子类中执行butterknife的绑定
     *
     * @return
     */
    protected boolean isTitleActivity() {
        return false;
    }

    public boolean isUseEventBus() {
        return useEventBus;
    }

    public void setUseEventBus(boolean useEventBus) {
        this.useEventBus = useEventBus;
    }

    protected boolean useEventBus() {
        return useEventBus;
    }//是否使用eventbus

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (useEventBus()) {
            EventUtils.unregister(this);
        }
        if(unbinder!= null){
            unbinder.unbind();

        }

        AppManager.getAppManager().finishActivity(this); // 结束Activity&从堆栈中移除
        //leakcanary检查内存泄漏问题
//        if (refWatcher != null) {
//            refWatcher.watch(this);
//        }
    }

    protected abstract void initView();

    protected abstract void setListener();

    protected abstract void initData();

    public void setOnClicks(View.OnClickListener listener, View... views) {
        CommonUtil.setOnClickListeners(listener, views);
    }

    public void setOnClicks(View... views) {
        setOnClicks(this, views);
    }

//    public void showLog(String log) {
//        Logger.e(log);
//    }


    @SuppressWarnings("RestrictedApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> fragments = fm.getFragments();
        if (fragments == null)
            return;

        for (Fragment frag : fragments)
            if (frag != null)
                handleResult(frag, requestCode, resultCode, data);

        return;

    }

    /**
     * 递归调用，对所有子Fragement生效
     *
     * @param frag
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @SuppressWarnings("RestrictedApi")
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }
}
