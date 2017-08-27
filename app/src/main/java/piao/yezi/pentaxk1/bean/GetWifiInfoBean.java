package piao.yezi.pentaxk1.bean;

/**
 * Created by yezi on 05/08/2017.
 */

public class GetWifiInfoBean extends BaseResponseBean {

    /**
     * ssid : PENTAX_65DBB6
     * key : A465DBB6
     * channel : 6
     * slotMode : separate
     */

    private String ssid;
    private String key;
    private String channel;
    private String slotMode;

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
}
