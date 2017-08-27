package piao.yezi.pentaxk1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import piao.yezi.pentaxk1.adapter.FragmentAdapter;
import piao.yezi.pentaxk1.util.DeviceUtils;

/**
 * Created by yezi on 07/08/2017.
 */

public class PhotoActivity extends BaseActivity {
    @BindView(R.id.indicator_container)
    MagicIndicator mIndicator;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private List<Fragment> fragments = new ArrayList<>(4);
    private FragmentAdapter mAdapter;
    private PhotoListFragment sd1;
    private PhotoListFragment sd2;
    private String[] PROJECT_TITLES = {"SD1","SD2"};


    @Override
    protected int getContentView() {
        return R.layout.activity_photos;
    }

    @Override
    protected void initView() {
        mViewpager= findViewById(R.id.viewpager);
        mIndicator = findViewById(R.id.indicator_container);
        sd1 = PhotoListFragment.newInstance(PhotoListFragment.TAG1);
        sd2 = PhotoListFragment.newInstance(PhotoListFragment.TAG2);
        fragments.add(sd1);
        fragments.add(sd2);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(5);
//      mViewPager.addOnPageChangeListener(this);

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(commonNavigatorAdapter);
        commonNavigator.setAdjustMode(true);
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, mViewpager);
    }

    CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter() {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public IPagerTitleView getTitleView(Context context, final int index) {
            ScaleTransitionPagerTitleView scaleTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
            scaleTransitionPagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.white));
//            colorTransitionPagerTitleView.setWidth((int)(DeviceUtils.getScreenWidth(mContext)/ PROJECT_TITLES.length));
            scaleTransitionPagerTitleView.setGravity(Gravity.CENTER);
            scaleTransitionPagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.white));
//                colorTransitionPagerTitleView.set
            scaleTransitionPagerTitleView.setTextSize(18);
            scaleTransitionPagerTitleView.setText(PROJECT_TITLES[index]);
            scaleTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewpager.setCurrentItem(index);
                }
            });
            return scaleTransitionPagerTitleView;
        }

        @Override
        public IPagerIndicator getIndicator(Context context) {
            LinePagerIndicator indicator = new LinePagerIndicator(context);
            indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
            indicator.setLineWidth(DeviceUtils.dpToPixel(mContext, 90));
            indicator.setLineHeight(DeviceUtils.dpToPixel(mContext, 3));
            indicator.setYOffset(DeviceUtils.dpToPixel(mContext, 3));
            indicator.setColors(ContextCompat.getColor(mContext, R.color.green));
            return indicator;
        }

    };

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
