package piao.yezi.pentaxk1.http;

import okhttp3.ResponseBody;
import piao.yezi.pentaxk1.GetParamsBean;
import piao.yezi.pentaxk1.bean.BaseResponseBean;
import piao.yezi.pentaxk1.bean.ChangeParamsResBean;
import piao.yezi.pentaxk1.bean.GetDetailParamsBean;
import piao.yezi.pentaxk1.bean.GetDeviceInfoBean;
import piao.yezi.pentaxk1.bean.GetLastPhotoInfoBean;
import piao.yezi.pentaxk1.bean.GetLiveParamsBean;
import piao.yezi.pentaxk1.bean.GetPhotosBean;
import piao.yezi.pentaxk1.bean.GetWifiInfoBean;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yezi on 05/08/2017.
 */

public interface ApiService {
    /**
     * 获取相机参数
     *
     * @return
     */
    @GET("params/camera")
    Observable<GetParamsBean> getCameraParams();

    /**
     * 拍照
     *
     * @return
     */
    @POST("camera/shoot")
    Observable<BaseResponseBean> takePhoto();

    /**
     * 获取相机详细参数
     */
    @GET("props")
    Observable<GetDetailParamsBean> getParamsList();

    /**
     * 获取最后拍摄的照片信息
     *
     * @return
     */
    @GET("photos/latest/info")
    Observable<GetLastPhotoInfoBean> getLastPhotoInfo();

    /**
     * 获取相机硬件相关信息（mac地址、相机序号、相机型号名称）
     *
     * @return
     */
    @GET("constants/device")
    Observable<GetDeviceInfoBean> getDeviceInfo();

    /**
     * 获取相机SD卡中的照片列表
     *
     * @param storage
     * @return
     */
    @GET("photos")
    Observable<GetPhotosBean> getPhotoList(@Query("storage") String storage);

    /**
     * 获取相机WIFI信息
     *
     * @return
     */
    @GET("params/device")
    Observable<GetWifiInfoBean> getWifiInfo();

    /**
     * @return
     */
    @GET("params")
    Observable<GetLiveParamsBean> getLiveParams();

    /**
     * 绿键
     *
     * @return
     */
    @PUT("params/camera")
    Observable<ChangeParamsResBean> greenButton();

    /**
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeParams(@Body GetParamsBean bean);

    /**
     * 绿键
     *
     * @return
     */
    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeParams();

    /**
     * 更改快门
     *
     * @param param
     * @return
     */
    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeAvParams(@Field("av") String param);

    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeTvParams(@Field("tv") String param);

    /**
     * 更改ISO
     *
     * @param param
     * @return
     */
    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeSvParams(@Field("sv") String param);

    /**
     * 曝光补偿
     *
     * @param param
     * @return
     */
    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeXvParams(@Field("xv") String param);

    /**
     * 更改白平衡
     *
     * @param param
     * @return
     */
    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeWbParams(@Field("WBMode") String param);

    /**
     * 更改照片尺寸
     *
     * @param param
     * @return
     */
    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeSizeParams(@Field("stillSize") String param);

    /**
     * 更改色彩模式/效果
     *
     * @param param
     * @return
     */
    @FormUrlEncoded
    @PUT("params/camera")
    Observable<ChangeParamsResBean> changeEfParams(@Field("effect") String param);

    /**
     * 下载照片到相机
     *
     * @param folderName
     * @param fileName
     * @return
     */
    @GET("photos/{folderName}/{fileName}")
    Observable<ResponseBody> downloadPhoto(@Path("folderName") String folderName,
                                           @Path("fileName") String fileName);
    //boolean writtenToDisk = writeResponseBodyToDisk(response.body());
}
