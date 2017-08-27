package piao.yezi.pentaxk1.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class BaseSubscriber<T> extends Subscriber<T>  {

    protected Context context;
    
    public BaseSubscriber(Context context) {
        this.context = context;
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {

    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (!(e instanceof ApiException)){
            e.printStackTrace();
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {

    }
    
    protected  void showErrorToast(Throwable e){
        if (e instanceof SocketTimeoutException) {
            //CommonUtil.showToast(context.getString(R.string.network_disconnect));
        } else if (e instanceof ConnectException) {
            //CommonUtil.showToast(context.getString(R.string.connect_server_timeout));
        } else if (e instanceof ApiException
                //&& ((ApiException) e).getMessage()==ApiException.SERVER_ERROR
                ){
           // CommonUtil.showToast(e.getMessage());
        } else if (e instanceof ApiException
                &&
               // ((ApiException) e).getMessage()==ApiException.SERVER_ERROR &&

                TextUtils.isEmpty(e.getMessage())){
           // CommonUtil.showToast(context.getString(R.string.unknown_error));
        }else {

            Toast.makeText(context,  e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}