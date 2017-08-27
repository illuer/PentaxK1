package piao.yezi.pentaxk1;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.stetho.common.LogUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import piao.yezi.pentaxk1.adapter.PhotoListAsapter;
import piao.yezi.pentaxk1.bean.GetPhotosBean;
import piao.yezi.pentaxk1.http.ApiImp;
import piao.yezi.pentaxk1.http.ProgressSubscriber;


/**
 * 随手记 2/3/4 界面
 * Created by lx on 2017/5/15.
 */

public class PhotoListFragment extends BaseFragment {

    public static final int TAG1 = 0;
    public static final int TAG2 = 1;
    RecyclerView mRecyclerview;
    SwipeRefreshLayout mSwipeRefreshWidget;
    Unbinder unbinder;

    private LayoutInflater inflater;
    List<String> photoList = new ArrayList<>();
    PhotoListAsapter mPhotoListAsapter;


    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void initData() {
        //getNetData();
        photoList.add(Constants.BASE_URL+"205_0204/"+"17016413.JPG");
        photoList.add(Constants.BASE_URL+"photos/"+"205_0204/"+"17016414.JPG");
        mPhotoListAsapter.notifyDataSetChanged();
    }

    private void getNetData() {
        String path = "";
        if (index1 == TAG1) {
            path = "sd1";
        } else if (index1 == TAG2) {
            path = "sd2";
        }
        ApiImp.getInstance().getPhotoList(((BaseActivity) getActivity()), path, new ProgressSubscriber<GetPhotosBean>(getActivity()) {
            @Override
            public void onNext(GetPhotosBean getPhotosBean) {
                super.onNext(getPhotosBean);
                LogUtil.e("照片列表  " + new Gson().toJson(getPhotosBean));
                for (int i = 0; i < getPhotosBean.getDirs().size(); i++) {
                    String dirName = getPhotosBean.getDirs().get(i).getName();
                    for (int i1 = 0; i1 < getPhotosBean.getDirs().get(i).getFiles().size(); i1++) {
                        if (getPhotosBean.getDirs().get(i).getFiles().get(i1).toLowerCase().endsWith("jpg")
                                || getPhotosBean.getDirs().get(i).getFiles().get(i1).toLowerCase().endsWith("jpeg")
                                ) {
                           // photoList.add(Constants.BASE_URL + "photos/" + dirName + "/" + getPhotosBean.getDirs().get(i).getFiles().get(i1));
                        }


                    }
                }
                photoList.add(Constants.BASE_URL+"205_0204/"+"17016413.JPG");
                mPhotoListAsapter.notifyDataSetChanged();
            }
        });
    }

    static int index1 = 0;

    public static PhotoListFragment newInstance(int index) {
        PhotoListFragment myFragment = new PhotoListFragment();
        Bundle bundle = new Bundle();
        index1 = index;
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_photo_list;
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = getArguments();
        mRecyclerview = getActivity().findViewById(R.id.recyclerview);
        mSwipeRefreshWidget =getActivity().findViewById(R.id.swipe_refresh_widget);
        mPhotoListAsapter = new PhotoListAsapter(photoList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(manager);
        mRecyclerview.setAdapter(mPhotoListAsapter);
    }

    @Override
    protected void setListener() {
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                mSwipeRefreshWidget.setRefreshing(false);
            }
        });
    }


    @Override
    public void onClick(View v) {

    }


    // 当FRagmen被加载到activity的时候会被回调
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//        if (activity instanceof FragmentInteraction) {
//            listterner = (FragmentInteraction) activity; // 2.2 获取到宿主activity并赋值
//        } else {
//            throw new IllegalArgumentException("activity must implements FragmentInteraction");
//        }
//    }

    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private FragmentInteraction listterner;

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


    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void edit(int index);

        void finish(int index);
    }


}
