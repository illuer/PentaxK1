package piao.yezi.pentaxk1.bean;

import java.util.List;

/**
 * Created by yezi on 05/08/2017.
 */

public class GetDetailParamsBean extends BaseResponseBean {

    /**
     * resoList : ["1080x720","720x480"]
     * movieResoList : ["1280x720","720x404"]
     * WBModeList : ["auto","multiAuto","daylight","shade","cloud","daylightFluorescent","dayWhiteFluorescent","coolWhiteFluorescent","warmWhiteFluorescent","tungsten","cte","manual1","manual2","manual3","colorTemp1","colorTemp2","colorTemp3"]
     * stillSizeList : ["L3","L2","L1","M3","M2","M1","S3","S2","S1","XS3","XS2","XS1"]
     * movieSizeList : ["FHD30p","FHD25p","FHD24p","HD60p","HD50p","FHD60i","FHD50i"]
     * shootModeList : ["single","continuousH","continuousM","continuousL","self12s","self2s","selfCotinuous","remocon","remocon3s","remoconContinous","bracket","bracketSelf","bracketRemocon","multiExp","multiExpContinuous","multiExpSelf","multiExpRemocon","interval","intervalSelf","intervalRemocon","intervalComp","intervalCompSelf","intervalCompRemocon","intervalMovie","intervalMovieSelf","intervalMovieRemocon","starstream","starstreamSelf","starstreamRemocon","mirrorUp","mirrorUpRemcon"]
     * effectList : ["cim_natural","cim_bright","cim_portrait","cim_landscape","cim_vibrant","cim_radiant","cim_muted","cim_bleachBypass","cim_reversal","cim_monochrome","cim_crossProcess","cim_auto","cim_flat"]
     * filterList : ["off","dfl_extractColor","dfl_replaceColor","dfl_toyCamera","dfl_retro","dfl_highContrast","dfl_shading","dfl_negaPosi","dfl_solidMonoColor","dfl_hardMonochrome","hdr_auto","hdr_mode1","hdr_mode2","hdr_mode3","hdr_modeA"]
     * exposureModeList : ["P","SV","TV","AV","TAV","M","B","X","U1","U2","U3","U4","U5","auto","gps","movie"]
     * avList : ["1.4","1.6","1.8","2.0","2.2","2.5","2.8","3.2","3.5","4.0","4.5","5.0","5.6","6.3","7.1","8.0","9.0","10","11","13","14","16"]
     * tvList : ["30.1","25.1","20.1","15.1","13.1","10.1","8.1","6.1","5.1","4.1","3.1","25.10","2.1","16.10","13.10","1.1","8.10","6.10","5.10","4.10","3.10","1.4","1.5","1.6","1.8","1.10","1.13","1.15","1.20","1.25","1.30","1.40","1.50","1.60","1.80","1.100","1.125","1.160","1.200","1.250","1.320","1.400","1.500","1.640","1.800","1.1000","1.1250","1.1600","1.2000","1.2500","1.3200","1.4000","1.5000","1.6400","1.8000"]
     * svList : ["100","125","160","200","250","320","400","500","640","800","1000","1250","1600","2000","2500","3200","4000","5000","6400","8000","10000","12800","16000","20000","25600","32000","40000","51200","64000","80000","102400","128000","160000","204800"]
     * xvList : ["+5.0","+4.7","+4.3","+4.0","+3.7","+3.3","+3.0","+2.7","+2.3","+2.0","+1.7","+1.3","+1.0","+0.7","+0.3","0.0","-0.3","-0.7","-1.0","-1.3","-1.7","-2.0","-2.3","-2.7","-3.0","-3.3","-3.7","-4.0","-4.3","-4.7","-5.0"]
     * exposureModeOption :
     * state : idle
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
     * focused : false
     * focusCenters : []
     * focusEffectiveArea : [70,64]
     * focusMode : af
     * model : PENTAX K-1
     * firmwareVersion : 01.41
     * macAddress : AC:3F:A4:65:DB:B6
     * serialNo : 6356762
     * channelList : [0,1,2,3,4,5,6,7,8,9,10,11]
     * hot : false
     * battery : 66
     * stillFormatList : ["jpeg","dng","rawdng"]
     * storages : [{"name":"sd1","equipped":false,"writable":false,"available":false,"active":false,"format":"dng","reservePriority":"none","remain":0},{"name":"sd2","equipped":false,"writable":false,"available":false,"active":false,"format":"jpeg","reservePriority":"none","remain":0}]
     * ssid : PENTAX_65DBB6
     * key : A465DBB6
     * channel : 6
     * slotMode : separate
     * liveState : idle
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
    private boolean focused;
    private String focusMode;
    private String model;
    private String firmwareVersion;
    private String macAddress;
    private String serialNo;
    private boolean hot;
    private int battery;
    private String ssid;
    private String key;
    private String channel;
    private String slotMode;
    private String liveState;
    private List<String> resoList;
    private List<String> movieResoList;
    private List<String> WBModeList;
    private List<String> stillSizeList;
    private List<String> movieSizeList;
    private List<String> shootModeList;
    private List<String> effectList;
    private List<String> filterList;
    private List<String> exposureModeList;
    private List<String> avList;
    private List<String> tvList;
    private List<String> svList;
    private List<String> xvList;
    private List<?> focusCenters;
    private List<Integer> focusEffectiveArea;
    private List<Integer> channelList;
    private List<String> stillFormatList;
    private List<StoragesBean> storages;

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

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public String getFocusMode() {
        return focusMode;
    }

    public void setFocusMode(String focusMode) {
        this.focusMode = focusMode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSlotMode() {
        return slotMode;
    }

    public void setSlotMode(String slotMode) {
        this.slotMode = slotMode;
    }

    public String getLiveState() {
        return liveState;
    }

    public void setLiveState(String liveState) {
        this.liveState = liveState;
    }

    public List<String> getResoList() {
        return resoList;
    }

    public void setResoList(List<String> resoList) {
        this.resoList = resoList;
    }

    public List<String> getMovieResoList() {
        return movieResoList;
    }

    public void setMovieResoList(List<String> movieResoList) {
        this.movieResoList = movieResoList;
    }

    public List<String> getWBModeList() {
        return WBModeList;
    }

    public void setWBModeList(List<String> WBModeList) {
        this.WBModeList = WBModeList;
    }

    public List<String> getStillSizeList() {
        return stillSizeList;
    }

    public void setStillSizeList(List<String> stillSizeList) {
        this.stillSizeList = stillSizeList;
    }

    public List<String> getMovieSizeList() {
        return movieSizeList;
    }

    public void setMovieSizeList(List<String> movieSizeList) {
        this.movieSizeList = movieSizeList;
    }

    public List<String> getShootModeList() {
        return shootModeList;
    }

    public void setShootModeList(List<String> shootModeList) {
        this.shootModeList = shootModeList;
    }

    public List<String> getEffectList() {
        return effectList;
    }

    public void setEffectList(List<String> effectList) {
        this.effectList = effectList;
    }

    public List<String> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<String> filterList) {
        this.filterList = filterList;
    }

    public List<String> getExposureModeList() {
        return exposureModeList;
    }

    public void setExposureModeList(List<String> exposureModeList) {
        this.exposureModeList = exposureModeList;
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

    public List<?> getFocusCenters() {
        return focusCenters;
    }

    public void setFocusCenters(List<?> focusCenters) {
        this.focusCenters = focusCenters;
    }

    public List<Integer> getFocusEffectiveArea() {
        return focusEffectiveArea;
    }

    public void setFocusEffectiveArea(List<Integer> focusEffectiveArea) {
        this.focusEffectiveArea = focusEffectiveArea;
    }

    public List<Integer> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Integer> channelList) {
        this.channelList = channelList;
    }

    public List<String> getStillFormatList() {
        return stillFormatList;
    }

    public void setStillFormatList(List<String> stillFormatList) {
        this.stillFormatList = stillFormatList;
    }

    public List<StoragesBean> getStorages() {
        return storages;
    }

    public void setStorages(List<StoragesBean> storages) {
        this.storages = storages;
    }

    public static class StoragesBean {
        /**
         * name : sd1
         * equipped : false
         * writable : false
         * available : false
         * active : false
         * format : dng
         * reservePriority : none
         * remain : 0
         */

        private String name;
        private boolean equipped;
        private boolean writable;
        private boolean available;
        private boolean active;
        private String format;
        private String reservePriority;
        private int remain;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isEquipped() {
            return equipped;
        }

        public void setEquipped(boolean equipped) {
            this.equipped = equipped;
        }

        public boolean isWritable() {
            return writable;
        }

        public void setWritable(boolean writable) {
            this.writable = writable;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getReservePriority() {
            return reservePriority;
        }

        public void setReservePriority(String reservePriority) {
            this.reservePriority = reservePriority;
        }

        public int getRemain() {
            return remain;
        }

        public void setRemain(int remain) {
            this.remain = remain;
        }
    }
}
