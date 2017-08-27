package piao.yezi.pentaxk1.http;

import android.app.Activity;

import com.facebook.stetho.common.LogUtil;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import piao.yezi.pentaxk1.BaseActivity;
import piao.yezi.pentaxk1.GetParamsBean;
import piao.yezi.pentaxk1.PentaxK1;
import piao.yezi.pentaxk1.SingleInstance;
import piao.yezi.pentaxk1.bean.ChangeParamsResBean;
import piao.yezi.pentaxk1.bean.GetDetailParamsBean;
import piao.yezi.pentaxk1.bean.GetLiveParamsBean;
import piao.yezi.pentaxk1.bean.GetPhotosBean;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by yezi on 05/08/2017.
 */

public class ApiImp extends BaseLogic {
    static ApiService apiService;
    Activity mActivity;

    public static ApiImp getInstance() {

        return (ApiImp) SingleInstance.getInstance(ApiImp.class.getName());
    }

    ApiImp() {
        HttpMethods.getInstance().init(PentaxK1.getInstance());
        apiService = HttpMethods.getInstance().getApiService();


    }

    public void getParamsList(BaseActivity mActivity, Subscriber<GetDetailParamsBean> s) {
        Observable observable = apiService.getParamsList()
                .map(new HttpResultFunc<GetDetailParamsBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    /**
     * 获取实时参数
     *
     * @param mActivity
     * @param s
     */
    public void getLiveParams(BaseActivity mActivity, Subscriber<GetLiveParamsBean> s) {
        Observable observable = apiService.getLiveParams()
                .map(new HttpResultFunc<GetLiveParamsBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeParams(BaseActivity mActivity, GetParamsBean bean, Subscriber<ChangeParamsResBean> s) {
        Observable observable = apiService.changeParams(bean)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    /**
     * 绿键
     *
     * @param mActivity
     * @param s
     */
    public void greenButton(BaseActivity mActivity, Subscriber<ChangeParamsResBean> s) {
        Observable observable = apiService.greenButton()
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeAvParams(BaseActivity mActivity, String param, Subscriber<ChangeParamsResBean> s) {
        Observable observable = apiService.changeAvParams(param)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeTvParams(BaseActivity mActivity, String param, Subscriber<ChangeParamsResBean> s) {

        Observable observable = apiService.changeTvParams(param)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeSvParams(BaseActivity mActivity, String param, Subscriber<ChangeParamsResBean> s) {
        Observable observable = apiService.changeSvParams(param)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeXvParams(BaseActivity mActivity, String param, Subscriber<ChangeParamsResBean> s) {
        String newparam = "";

        try {
            newparam = URLEncoder.encode(param, "utf-8").replaceAll("\\+", " ");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LogUtil.e(newparam);
        Observable observable = apiService.changeXvParams(newparam)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeWbParams(BaseActivity mActivity, String param, Subscriber<ChangeParamsResBean> s) {
        Observable observable = apiService.changeWbParams(param)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeEfParams(BaseActivity mActivity, String param, Subscriber<ChangeParamsResBean> s) {
        Observable observable = apiService.changeEfParams(param)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void changeSizeParams(BaseActivity mActivity, String param, Subscriber<ChangeParamsResBean> s) {
        Observable observable = apiService.changeSizeParams(param)
                .map(new HttpResultFunc<ChangeParamsResBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }

    public void getPhotoList(BaseActivity mActivity, String param, Subscriber<GetPhotosBean> s) {
        Observable observable = apiService.getPhotoList(param)
                .map(new HttpResultFunc<GetPhotosBean>())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY));
        toSubscribe(observable, s);

    }
}
