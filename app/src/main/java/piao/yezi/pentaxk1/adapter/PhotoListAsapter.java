package piao.yezi.pentaxk1.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import piao.yezi.pentaxk1.R;

/**
 * Created by yezi on 09/08/2017.
 */

public class PhotoListAsapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PhotoListAsapter(List<String> data) {
        super(R.layout.item_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(((ImageView) helper.getView(R.id.image)).getContext()).load(item+"size=view&storage=sd1").into(((ImageView) helper.getView(R.id.image)));
    }


}
