package piao.yezi.pentaxk1;

import piao.yezi.pentaxk1.bean.BaseResponseBean;

/**
 * Created by yezi on 05/08/2017.
 */

public class GetParamsBean extends BaseResponseBean{

    /**
     * errCode : 200
     * errMsg : OK
     * av : 2.2
     * tv : 3.10
     * sv : 100
     * xv : -0.7
     * WBMode : auto
     * shootMode : single
     * exposureMode : M
     * stillSize : L3
     * movieSize : FHD30p
     * effect : cim_auto
     * filter : off
     * timerExposure : false
     */


    private String av;
    private String tv;
    private String sv;
    private String xv;
    private String WBMode;
    private String shootMode;
    private String exposureMode;
    private String stillSize;
    private String movieSize;
    private String effect;
    private String filter;
    private boolean timerExposure;



    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getXv() {
        return xv;
    }

    public void setXv(String xv) {
        this.xv = xv;
    }

    public String getWBMode() {
        return WBMode;
    }

    public void setWBMode(String WBMode) {
        this.WBMode = WBMode;
    }

    public String getShootMode() {
        return shootMode;
    }

    public void setShootMode(String shootMode) {
        this.shootMode = shootMode;
    }

    public String getExposureMode() {
        return exposureMode;
    }

    public void setExposureMode(String exposureMode) {
        this.exposureMode = exposureMode;
    }

    public String getStillSize() {
        return stillSize;
    }

    public void setStillSize(String stillSize) {
        this.stillSize = stillSize;
    }

    public String getMovieSize() {
        return movieSize;
    }

    public void setMovieSize(String movieSize) {
        this.movieSize = movieSize;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isTimerExposure() {
        return timerExposure;
    }

    public void setTimerExposure(boolean timerExposure) {
        this.timerExposure = timerExposure;
    }
}
