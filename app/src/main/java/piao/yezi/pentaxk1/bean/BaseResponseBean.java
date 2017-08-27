package piao.yezi.pentaxk1.bean;

/**
 * Created by yezi on 05/08/2017.
 */

public class BaseResponseBean {

    /**
     * errCode : 412
     * errMsg : Precondition Failed
     */

    private int errCode;
    private String errMsg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
