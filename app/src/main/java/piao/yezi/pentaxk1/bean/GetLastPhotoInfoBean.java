package piao.yezi.pentaxk1.bean;

/**
 * Created by yezi on 05/08/2017.
 */

public class GetLastPhotoInfoBean extends  BaseResponseBean {

    /**
     * captured : true
     * dir : 260_0805
     * file : 17087842.JPG
     * av : 2.2
     * sv : 100
     * xv : -0.7
     * tv : 1.100
     * orientation : 1
     * cameraModel : PENTAX K-1
     * latlng :
     * datetime : 2017-08-05T01:01:26
     */

    private boolean captured;
    private String dir;
    private String file;
    private String av;
    private String sv;
    private String xv;
    private String tv;
    private int orientation;
    private String cameraModel;
    private String latlng;
    private String datetime;

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
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

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
