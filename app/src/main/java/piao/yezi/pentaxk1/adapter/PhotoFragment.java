package piao.yezi.pentaxk1.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import piao.yezi.pentaxk1.BaseFragment;
import piao.yezi.pentaxk1.PhotoListFragment;
import piao.yezi.pentaxk1.R;

/**
 * Created by yezi on 09/08/2017.
 */

public class PhotoFragment extends BaseFragment {


    @BindView(R.id.indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;
    private List<Fragment> fragments = new ArrayList<>(4);
    private FragmentAdapter mAdapter;
    private PhotoListFragment sd1;
    private PhotoListFragment sd2;

    @Override
    protected int getContentView() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initView(View view) {
        sd1 = PhotoListFragment.newInstance(PhotoListFragment.TAG1);
        sd2 = PhotoListFragment.newInstance(PhotoListFragment.TAG2);
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
