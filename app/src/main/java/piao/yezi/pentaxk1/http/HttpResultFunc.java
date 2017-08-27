package piao.yezi.pentaxk1.http;

import piao.yezi.pentaxk1.bean.BaseResponseBean;
import piao.yezi.pentaxk1.util.CommonUtil;
import rx.functions.Func1;

/**
 * Created by lx on 2017/3/14.
 */

public class HttpResultFunc<T extends BaseResponseBean> implements Func1<T, T> {

    @Override
    public T call(T baseBean) {

        if (baseBean == null) {
//            throw new ApiException(0,
//                    "返回结果为空");
        }
        // TODO: 2017/4/14 新的接口修改为1001为操作成功，旧的接口还是200 最后会全部修改为1001
        // if (1001 != response.getCode() && 200 != response.getCode()) {
        if (200 != baseBean.getErrCode()) {
            CommonUtil.showToast(""+baseBean.getErrMsg()+":"+baseBean.getErrCode());
            //throw new ApiException(baseBean.getErrMsg());
        }
//TODO
        return baseBean;
    }
}
