package piao.yezi.pentaxk1.http;

import android.content.Context;
import android.text.TextUtils;

import piao.yezi.pentaxk1.PentaxK1;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressSubscriber<T> extends BaseSubscriber<T> implements ProgressCancelListener {

    private String content = "请稍等...";
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;


    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        super(context);
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
//        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    public ProgressSubscriber(Context context) {
        super(context);
//        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    public ProgressSubscriber(Context context,String content) {
        super(context);
        if (!TextUtils.isEmpty(content))
            this.content = content;
//        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }


    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    private void showWindowView() {
        ((PentaxK1)PentaxK1.getInstance()).createWindowView(content);
    }
    private void dismissWindowView() {
        ((PentaxK1)PentaxK1.getInstance()).closeWindowView();
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
//        showProgressDialog();
       // showWindowView();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
//        dismissProgressDialog();
        dismissWindowView();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        super.onError(e);
        showErrorToast(e);
//        dismissProgressDialog();
        dismissWindowView();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}