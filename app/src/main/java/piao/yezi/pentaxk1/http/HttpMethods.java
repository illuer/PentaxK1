package piao.yezi.pentaxk1.http;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import piao.yezi.pentaxk1.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Func1;



public class
HttpMethods {


    private static String TOKEN = "";
    private static long COMPANYID;
    private static long USERID;
    private static long EMPLOYEEID;
    private static int CLIENT_FLAG = 1;//0：其他1：Android客户端2：IOS客户端3:Windows客户端4：Mac客户端
    private static String CLIENT_VERSION = "1.0";
    private static String CLIENT_ID = "1111222333";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    ApiService mApiService;



    //构造方法私有
    private HttpMethods() {

    }

    private static HttpMethods instance;

    public static synchronized HttpMethods getInstance() {
        if (instance == null) {
            instance = new HttpMethods();
        }
        return instance;
    }

    public void init(final Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //手动创建一个OkHttpClient并设置超时时间

//        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //自定义拦截器，负责请求日志打印，请求头统一处理，请求结果统一处理等
//        builder.addNetworkInterceptor(new RequestIntercept(globeHttpHandler));

        //Stetho增加的网络拦截器
        //builder.addNetworkInterceptor(new StethoInterceptor());

//        CookieJarImpl cookieJar = new CookieJarImpl(new MemoryCookieStore());
//        builder.cookieJar(cookieJar);//coolie持久化

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();

        mApiService = retrofit.create(ApiService.class);

    }


    public void init(final Context context, boolean noHeader) {

        if (noHeader) {

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build();
//            tmApiService = retrofit.create(TeamMessageApiService.class);
        } else {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            //自定义拦截器，负责请求日志打印，请求头统一处理，请求结果统一处理等
//            builder.addNetworkInterceptor(new RequestIntercept(globeHttpHandler));
            //Stetho增加的网络拦截器
            //builder.addNetworkInterceptor(new StethoInterceptor());

//        CookieJarImpl cookieJar = new CookieJarImpl(new MemoryCookieStore());
//        builder.cookieJar(cookieJar);//coolie持久化

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .serializeNulls()
                    .create();


        }

    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResultBak<T>, T> {

        @Override
        public T call(HttpResultBak<T> httpResultBak) {

            if (!"SUCCESS".equals(httpResultBak.getResultCode())) {
               // throw new ApiException(1,"1");

            }

            return httpResultBak.getResultData();
        }
    }


    public ApiService getApiService() {
        return mApiService;
    }

    public void setApiService(ApiService apiService) {
        mApiService = apiService;
    }

    GlobeHttpHandler globeHttpHandler = new GlobeHttpHandler() {
        @Override
        public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
            return response;
        }

        @Override
        public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
            //加上统一的请求头
            request = request.newBuilder()
                    .header("EMPLOYEEID", "" + EMPLOYEEID)
                    .header("USERID", "" + USERID)
                    .header("COMPANYID", "" + COMPANYID)
                    .header("TOKEN", TOKEN)
                    .method(request.method(), request.body())
                    .build();
            return request;
        }

    };
//    public final String MD5Encoder(String s) {
//        char hexDigits[] = {'0', '1', '2', '3', '4',
//                '5', '6', '7', '8', '9',
//                'A', 'B', 'C', 'D', 'E', 'F'};
//        try {
//            byte[] btInput = s.getBytes();
//            //获得MD5摘要算法的 MessageDigest 对象
//            MessageDigest mdInst = MessageDigest.getInstance("MD5");
//            //使用指定的字节更新摘要
//            mdInst.update(btInput);
//            //获得密文
//            byte[] md = mdInst.digest();
//            //把密文转换成十六进制的字符串形式
//            int j = md.length;
//            char str[] = new char[j * 2];
//            int k = 0;
//            for (int i = 0; i < j; i++) {
//                byte byte0 = md[i];
//                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                str[k++] = hexDigits[byte0 & 0xf];
//            }
//            return new String(str).toLowerCase();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        HttpMethods.TOKEN = TOKEN;
    }

    public static long getCOMPANYID() {
        return COMPANYID;
    }

    public static void setCOMPANYID(long COMPANYID) {
        HttpMethods.COMPANYID = COMPANYID;
    }

    public static long getUSERID() {
        return USERID;
    }

    public static void setUSERID(long USERID) {
        HttpMethods.USERID = USERID;
    }

    public static long getEMPLOYEEID() {
        return EMPLOYEEID;
    }

    public static void setEMPLOYEEID(long EMPLOYEEID) {
        HttpMethods.EMPLOYEEID = EMPLOYEEID;
    }

    public static int getClientFlag() {
        return CLIENT_FLAG;
    }

    public static void setClientFlag(int clientFlag) {
        CLIENT_FLAG = clientFlag;
    }

    public static String getClientVersion() {
        return CLIENT_VERSION;
    }

    public static void setClientVersion(String clientVersion) {
        CLIENT_VERSION = clientVersion;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }

    public static void setClientId(String clientId) {
        CLIENT_ID = clientId;
    }
}
