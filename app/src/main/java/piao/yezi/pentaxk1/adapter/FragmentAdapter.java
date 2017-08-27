package piao.yezi.pentaxk1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lx on 2017/3/23.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;

    public FragmentAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return (data == null) ? 0 : data.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (data == null) {
            return null;
        }
        return data.get(position);
    }

}
