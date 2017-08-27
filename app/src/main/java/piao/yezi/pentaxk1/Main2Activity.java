package piao.yezi.pentaxk1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.facebook.stetho.common.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import piao.yezi.pentaxk1.bean.ChangeParamsResBean;
import piao.yezi.pentaxk1.bean.GetDetailParamsBean;
import piao.yezi.pentaxk1.bean.GetLiveParamsBean;
import piao.yezi.pentaxk1.http.ApiImp;
import piao.yezi.pentaxk1.http.ProgressSubscriber;

@RequiresApi(api = Build.VERSION_CODES.M)
public class Main2Activity extends BaseTitleActivity implements View.OnTouchListener, View.OnScrollChangeListener {


    WheelView mTv11;
    WheelView mTv12;
    WheelView mTv13;
    WheelView mTv21;
    WheelView mTv22;
    WheelView mTv23;
    TextView mTv5;
    TextView mTv6;
    private boolean flag = true;

    @SuppressLint("HandlerLeak")
    private final Handler myHandler = new MyHandler() {

        @Override
        public void handleMessage(Message msg) {
            getLiveParams();
            //initData();
        }
    };

    @Override
    protected int getChildView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mTv11 = findViewById(R.id.tv11);
        mTv12 = findViewById(R.id.tv12);
        mTv13 = findViewById(R.id.tv13);
        mTv21 = findViewById(R.id.tv21);
        mTv22 = findViewById(R.id.tv22);
        mTv23 = findViewById(R.id.tv23);
        mTv5 = findViewById(R.id.tv5);
        mTv6 = findViewById(R.id.tv6);
        //setRightMenuColorTexts("刷新", "相册");
        setRightMenuTexts("刷新", "绿键", "相册");
        titleTv.setTextColor(Color.WHITE);
        titleTv.setText("Pentax K1");
        toolbar.setBackgroundColor(Color.parseColor("#990000"));

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setListener() {

        mTv11.setOnTouchListener(this);
        mTv12.setOnTouchListener(this);
        mTv13.setOnTouchListener(this);
        mTv21.setOnTouchListener(this);
        mTv22.setOnTouchListener(this);
        mTv23.setOnTouchListener(this);
        mTv11.setOnScrollChangeListener(this);

        mTv11.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                GetParamsBean bean = new GetParamsBean();
                bean.setAv(item);
                ApiImp.getInstance().changeAvParams(Main2Activity.this, item, new ProgressSubscriber<ChangeParamsResBean>(Main2Activity.this) {
                    @Override
                    public void onNext(ChangeParamsResBean changeParamsResBean) {
                        super.onNext(changeParamsResBean);
                        flag = true;
                        initParams2(changeParamsResBean);
                    }
                });
                LogUtil.e(item);
            }
        });
        mTv12.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                ApiImp.getInstance().changeTvParams(Main2Activity.this, item, new ProgressSubscriber<ChangeParamsResBean>(Main2Activity.this) {
                    @Override
                    public void onNext(ChangeParamsResBean changeParamsResBean) {
                        super.onNext(changeParamsResBean);
                        flag = true;
                        initParams2(changeParamsResBean);
                    }
                });
                LogUtil.e(item);
            }
        });
        mTv13.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                ApiImp.getInstance().changeSvParams(Main2Activity.this, item, new ProgressSubscriber<ChangeParamsResBean>(Main2Activity.this) {
                    @Override
                    public void onNext(ChangeParamsResBean changeParamsResBean) {
                        super.onNext(changeParamsResBean);
                        flag = true;
                        initParams2(changeParamsResBean);
                    }
                });
                LogUtil.e(item);
            }
        });
        // 更改曝光补偿
        mTv21.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
//                String newPram;
//                if(!item.startsWith("-") || !item.startsWith("0")){
//                    newPram =  item.replace("+"," ");
//                }else {
//                    newPram = item;
//                }
//                LogUtil.e(newPram);
                ApiImp.getInstance().changeXvParams(Main2Activity.this, item, new ProgressSubscriber<ChangeParamsResBean>(Main2Activity.this) {
                    @Override
                    public void onNext(ChangeParamsResBean changeParamsResBean) {
                        super.onNext(changeParamsResBean);
                        flag = true;
                        initParams2(changeParamsResBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        LogUtil.e("曝光补偿 " + e.getMessage());
                    }
                });
               // LogUtil.e(item);
            }
        });
        mTv22.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                ApiImp.getInstance().changeWbParams(Main2Activity.this, item, new ProgressSubscriber<ChangeParamsResBean>(Main2Activity.this) {
                    @Override
                    public void onNext(ChangeParamsResBean changeParamsResBean) {
                        super.onNext(changeParamsResBean);
                        flag = true;
                        initParams2(changeParamsResBean);
                    }
                });
                LogUtil.e(item);
            }
        });
        mTv23.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                ApiImp.getInstance().changeEfParams(Main2Activity.this, item, new ProgressSubscriber<ChangeParamsResBean>(Main2Activity.this) {
                    @Override
                    public void onNext(ChangeParamsResBean changeParamsResBean) {
                        super.onNext(changeParamsResBean);
                        flag = true;
                        initParams2(changeParamsResBean);
                    }
                });
                LogUtil.e(item);
            }
        });
    }

    @Override
    protected void initData() {
        if (flag) {
            ApiImp.getInstance()
                    .getParamsList(this, new ProgressSubscriber<GetDetailParamsBean>(this) {
                        @Override
                        public void onNext(GetDetailParamsBean bean) {
                            super.onNext(bean);
                            //  LogUtil.e(new Gson().toJson(bean));
                            //  initNetData(bean);
                            initWheelView(bean);
                            initDataSet(bean);

                            mTv5.setText(
                                    "曝光模式:" + bean.getExposureMode().toUpperCase() +
                                            "  剩余电量:" + bean.getBattery() + "\n" +

                                            "SD1 格式:" + bean.getStorages().get(0).getFormat()

                                            + "  剩余张数:" + bean.getStorages().get(0).getRemain()
                                            + "\n"
                                            + "SD2 格式:" + bean.getStorages().get(1).getFormat()

                                            + "  剩余张数:" + bean.getStorages().get(1).getRemain() +
                                            "\n" +
                                            "是否过热:" + bean.isHot() +
                                            "  是否合焦:" + bean.isFocused() + ""
                            );
                        }
                    });
        }

    }

    List<String> avList = new ArrayList<>();
    List<String> tvList = new ArrayList<>();
    List<String> svList = new ArrayList<>();
    List<String> exposureList = new ArrayList<>();
    List<String> wbList = new ArrayList<>();
    List<String> effectList = new ArrayList<>();
    List<String> xvList = new ArrayList<>();
    List<String> sizeList = new ArrayList<>();

    private void initNetData(GetDetailParamsBean bean) {

        getLiveParams();


    }

    private void initNetData2(GetDetailParamsBean bean) {

    }

    private void initWheelView(GetDetailParamsBean bean) {

        mTv11.setOffset(2);
        mTv12.setOffset(2);
        mTv13.setOffset(2);
        mTv21.setOffset(2);
        mTv22.setOffset(2);
        mTv23.setOffset(2);
        mTv11.setItems(bean.getAvList());
        mTv12.setItems(bean.getTvList());
        mTv13.setItems(bean.getSvList());
        mTv21.setItems(bean.getXvList());
        mTv22.setItems(bean.getWBModeList());
        mTv23.setItems(bean.getEffectList());
        initDataSet(bean);
    }

    private void initDataSet(GetDetailParamsBean bean) {
        avList.clear();
        avList.addAll(bean.getAvList());
        tvList.clear();
        tvList.addAll(bean.getTvList());
        svList.clear();
        svList.addAll(bean.getSvList());
        xvList.clear();
        xvList.addAll(bean.getXvList());
        exposureList.clear();
        exposureList.addAll(bean.getExposureModeList());
        wbList.clear();
        wbList.addAll(bean.getWBModeList());
        effectList.clear();
        effectList.addAll(bean.getEffectList());
        initParam3(bean);

        myHandler.postDelayed(null, 1500);
//        getLiveParams();

    }

    private void initDataSet2(ChangeParamsResBean bean) {
        avList.clear();
        avList.addAll(bean.getAvList());
        tvList.clear();
        tvList.addAll(bean.getTvList());
        svList.clear();
        svList.addAll(bean.getSvList());
        xvList.clear();
        xvList.addAll(bean.getXvList());
//        exposureList.clear();
//        exposureList.addAll(bean.ge());
        wbList.clear();
        // wbList.addAll(bean.getWBMode());
        effectList.clear();
        // effectList.addAll(bean.getEffectList());
        initParams2(bean);
    }

    private void getLiveParams() {
        ApiImp.getInstance()
                .getLiveParams(this, new ProgressSubscriber<GetLiveParamsBean>(this) {
                    @Override
                    public void onNext(GetLiveParamsBean bean1) {
                        super.onNext(bean1);
                        // LogUtil.e(new Gson().toJson(bean1));
                        initParams(bean1);
                        myHandler.postDelayed(null, 1500);
                    }
                });
    }

    private void initParams(GetLiveParamsBean bean1) {
        if (flag) {
            mTv11.setSeletion(avList.indexOf(bean1.getAv()));
            mTv12.setSeletion(tvList.indexOf(bean1.getTv()));
            mTv13.setSeletion(svList.indexOf(bean1.getSv()));
            mTv21.setSeletion(xvList.indexOf(bean1.getXv()));
            mTv22.setSeletion(wbList.indexOf(bean1.getWBMode()));
            mTv23.setSeletion(effectList.indexOf(bean1.getEffect()));
        }

    }

    private void initParam3(GetDetailParamsBean bean1) {
        if (flag) {
            mTv11.setSeletion(avList.indexOf(bean1.getAv()));
            mTv12.setSeletion(tvList.indexOf(bean1.getTv()));
            mTv13.setSeletion(svList.indexOf(bean1.getSv()));
            mTv21.setSeletion(xvList.indexOf(bean1.getXv()));
            mTv22.setSeletion(wbList.indexOf(bean1.getWBMode()));
            mTv23.setSeletion(effectList.indexOf(bean1.getEffect()));
        }

    }

    private void initParams2(ChangeParamsResBean bean1) {
        if (flag) {
            mTv11.setSeletion(avList.indexOf(bean1.getAv()));
            mTv12.setSeletion(tvList.indexOf(bean1.getTv()));
            mTv13.setSeletion(svList.indexOf(bean1.getSv()));
            mTv21.setSeletion(xvList.indexOf(bean1.getXv()));
            mTv22.setSeletion(wbList.indexOf(bean1.getWBMode()));
            mTv23.setSeletion(effectList.indexOf(bean1.getEffect()));
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onRightMenuClick(int itemId) {
        switch (itemId) {
            case 0:
                initData();
                break;
            case 1:
                greenButton();
                break;
            case 2:
                startActivity(new Intent(this, PhotoActivity.class));
                break;
            default:

                break;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flag = true;
                    }
                }, 3500);

                break;
            case MotionEvent.ACTION_DOWN:
                flag = false;
                break;
            case MotionEvent.ACTION_MOVE:
                flag = false;
                break;


        }
        return false;
    }

    public void greenButton() {
        ApiImp.getInstance().greenButton(this, new ProgressSubscriber<ChangeParamsResBean>(this) {
            @Override
            public void onNext(ChangeParamsResBean changeParamsResBean) {
                super.onNext(changeParamsResBean);

            }
        });
    }

    @Override
    public void onScrollChange(View view, int i, int i1, int i2, int i3) {

    }

    class MyHandler extends Handler {


    }

}
