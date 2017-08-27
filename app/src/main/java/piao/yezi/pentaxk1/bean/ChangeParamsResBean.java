package piao.yezi.pentaxk1.bean;

import java.util.List;

/**
 * Created by yezi on 06/08/2017.
 */

public class ChangeParamsResBean extends BaseResponseBean {

    /**
     * avList : ["1.4","1.6","1.8","2.0","2.2","2.5","2.8","3.2","3.5","4.0","4.5","5.0","5.6","6.3","7.1","8.0","9.0","10","11","13","14","16"]
     * tvList : ["30.1","25.1","20.1","15.1","13.1","10.1","8.1","6.1","5.1","4.1","3.1","25.10","2.1","16.10","13.10","1.1","8.10","6.10","5.10","4.10","3.10","1.4","1.5","1.6","1.8","1.10","1.13","1.15","1.20","1.25","1.30","1.40","1.50","1.60","1.80","1.100","1.125","1.160","1.200","1.250","1.320","1.400","1.500","1.640","1.800","1.1000","1.1250","1.1600","1.2000","1.2500","1.3200","1.4000","1.5000","1.6400","1.8000"]
     * svList : ["100","125","160","200","250","320","400","500","640","800","1000","1250","1600","2000","2500","3200","4000","5000","6400","8000","10000","12800","16000","20000","25600","32000","40000","51200","64000","80000","102400","128000","160000","204800"]
     * xvList : ["+5.0","+4.7","+4.3","+4.0","+3.7","+3.3","+3.0","+2.7","+2.3","+2.0","+1.7","+1.3","+1.0","+0.7","+0.3","0.0","-0.3","-0.7","-1.0","-1.3","-1.7","-2.0","-2.3","-2.7","-3.0","-3.3","-3.7","-4.0","-4.3","-4.7","-5.0"]
     * exposureModeOption :
     * state : idle
     * av : 1.4
     * tv : 1.4
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

    private String exposureModeOption;
    private String state;
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
    private List<String> avList;
    private List<String> tvList;
    private List<String> svList;
    private List<String> xvList;

    public String getExposureModeOption() {
        return exposureModeOption;
    }

    public void setExposureModeOption(String exposureModeOption) {
        this.exposureModeOption = exposureModeOption;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public List<String> getAvList() {
        return avList;
    }

    public void setAvList(List<String> avList) {
        this.avList = avList;
    }

    public List<String> getTvList() {
        return tvList;
    }

    public void setTvList(List<String> tvList) {
        this.tvList = tvList;
    }

    public List<String> getSvList() {
        return svList;
    }

    public void setSvList(List<String> svList) {
        this.svList = svList;
    }

    public List<String> getXvList() {
        return xvList;
    }

    public void setXvList(List<String> xvList) {
        this.xvList = xvList;
    }
}
