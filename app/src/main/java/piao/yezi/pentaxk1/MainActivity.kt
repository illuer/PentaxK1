package piao.yezi.pentaxk1

import android.graphics.Color
import android.view.View
import piao.yezi.pentaxk1.bean.GetDetailParamsBean
import piao.yezi.pentaxk1.http.ApiImp
import piao.yezi.pentaxk1.http.HttpMethods
import piao.yezi.pentaxk1.http.ProgressSubscriber


class MainActivity : BaseTitleActivity() {
    override fun getChildView(): Int {
        return R.layout.activity_main
    }

    override fun setListener() {

    }

    override fun onClick(view: View?) {

    }

    override fun initData() {
        HttpMethods.getInstance().init(this)
       var s =  ProgressSubscriber<GetDetailParamsBean>(this)
        s.onCompleted()

        ApiImp.getInstance().getParamsList(this, s);
    }

    override fun initView() {
        super.initView()
        //setRightMenuTexts("OK")
        //setActivityTitle("Pentax K1")
        //setRightMenuIcons(R.mipmap.synchronous,R.mipmap.icon_menu)
//        setRightMenuColorTexts("刷新","相册")
        setRightMenuColorTexts("刷新", "相册")
        titleTv.setTextColor(Color.WHITE);
        titleTv.setText("Pentax K1")
        toolbar.setBackgroundColor(Color.parseColor("#990000"));


        var lists = ArrayList<String>();
        for (i in 0..19) {
            lists.add("test:" + i)
        }
//        tv11.lists(lists).fontSize(55).showCount(7).selectTip("").select(0).listener { index -> Log.e("cc", "current select:" + tv11.selectItem + " index :" + index + ",result=" + lists.get(index)) }.build()
//        tv12.lists(lists).fontSize(55).showCount(7).selectTip("").select(0).listener { index -> Log.e("cc", "current select:" + tv11.selectItem + " index :" + index + ",result=" + lists.get(index)) }.build()
//        tv13.lists(lists).fontSize(55).showCount(7).selectTip("").select(0).listener { index -> Log.e("cc", "current select:" + tv11.selectItem + " index :" + index + ",result=" + lists.get(index)) }.build()
//        tv21.lists(lists).fontSize(55).showCount(7).selectTip("").select(0).listener { index -> Log.e("cc", "current select:" + tv11.selectItem + " index :" + index + ",result=" + lists.get(index)) }.build()
//        tv22.lists(lists).fontSize(55).showCount(7).selectTip("").select(0).listener { index -> Log.e("cc", "current select:" + tv11.selectItem + " index :" + index + ",result=" + lists.get(index)) }.build()
//        tv23.lists(lists).fontSize(55).showCount(7).selectTip("").select(0).listener { index -> Log.e("cc", "current select:" + tv11.selectItem + " index :" + index + ",result=" + lists.get(index)) }.build()

    }


}
