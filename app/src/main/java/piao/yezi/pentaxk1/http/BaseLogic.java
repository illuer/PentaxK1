package piao.yezi.pentaxk1.http;


import piao.yezi.pentaxk1.SingleInstance;
import piao.yezi.pentaxk1.util.CommonUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lx on 2017/3/14.
 */

public class BaseLogic extends SingleInstance {

    protected static <T> void  toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
    
    protected void showToast(String text){
        CommonUtil.showToast(text);
    }
    
    
}
