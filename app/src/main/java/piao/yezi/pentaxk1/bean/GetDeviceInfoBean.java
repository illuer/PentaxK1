package piao.yezi.pentaxk1.bean;

import java.util.List;

/**
 * Created by yezi on 05/08/2017.
 */

public class GetDeviceInfoBean extends BaseResponseBean {

    /**
     * model : PENTAX K-1
     * firmwareVersion : 01.41
     * macAddress : AC:3F:A4:65:DB:B6
     * serialNo : 6356762
     * channelList : [0,1,2,3,4,5,6,7,8,9,10,11]
     */

    private String model;
    private String firmwareVersion;
    private String macAddress;
    private String serialNo;
    private List<Integer> channelList;

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

    public List<Integer> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Integer> channelList) {
        this.channelList = channelList;
    }
}
