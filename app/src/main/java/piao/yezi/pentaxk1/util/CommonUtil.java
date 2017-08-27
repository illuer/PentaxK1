package piao.yezi.pentaxk1.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import piao.yezi.pentaxk1.PentaxK1;
import piao.yezi.pentaxk1.R;

/**
 * Created by Administrator on 2016/7/6.
 */
public class CommonUtil {

    private static Toast toast = null;

    /**
     * 思路是先将statusbar设置成半透明，然后在contentView中添加一个和statusbar一样的高度的view作为statusbar
     * 的背景颜色
     *
     * @param activity
     */
    public static View customStatusbar(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
//        View view = new View(activity);
//        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
//        layoutParams.height = getStatusBarHeight(activity);
//        layoutParams.width = CommonUtil.getScreenWidth(activity);
//        view.setLayoutParams(layoutParams);
//        view.setBackgroundColor(Color.RED);
//        mContentView.addView(view, 0);
        //First translucent status bar.
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int statusBarHeight = getStatusBarHeight(activity);

        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
            if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                //不预留系统空间
                ViewCompat.setFitsSystemWindows(mChildView, false);
                lp.topMargin += statusBarHeight;
                mChildView.setLayoutParams(lp);
            }
        }
        // 已经添加过假背景view时，不用再次添加，只需要设置颜色即ok

        View statusBarView = mContentView.getChildAt(0);
        if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
            //避免重复调用时多次添加 View
            statusBarView.setBackgroundColor(activity.getResources().getColor(R.color.abc_btn_colored_text_material));
//            statusBarView.setBackgroundResource(R.mipmap.ic_launcher);
            statusBarView.setVisibility(View.VISIBLE);
            return statusBarView;
        }
        statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusBarView.setBackgroundColor(activity.getResources().getColor(R.color.abc_search_url_text));
//        statusBarView.setBackgroundResource(R.mipmap.ic_launcher);
        //向 ContentView 中添加假 View
        mContentView.addView(statusBarView, 0, lp);
        statusBarView.setVisibility(View.VISIBLE);
        return statusBarView;
    }


    /**
     * 全屏显示
     *
     * @param activity
     */
    public static void fullScreen(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        //首先使 ChildView 不预留空间
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }

        int statusBarHeight = getStatusBarHeight(activity);
        //需要设置这个 flag 才能设置状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //避免多次调用该方法时,多次移除了 View
        if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height == statusBarHeight) {
            //移除假的 View.
            mContentView.removeView(mChildView);
            mChildView = mContentView.getChildAt(0);
        }
        if (mChildView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            //清除 ChildView 的 marginTop 属性
            if (lp != null && lp.topMargin >= statusBarHeight) {
//                lp.topMargin -= statusBarHeight;
                mChildView.setLayoutParams(lp);
            }
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int resId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return activity.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 判断手机是否有导航栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static boolean hasNavigationBar(Context context) {
        //是否在屏幕外有菜单键
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        //屏幕外是否有返回键
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        return !hasBackKey && !hasMenuKey;

    }

    public static void startActivtiy(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void startActivtiy(Context context, Class cls, Bundle bundle) {
        if (bundle == null) {
            startActivtiy(context, cls);
            return;
        }
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivtiyForResult(Context context, Class cls, int requestCode) {
        Intent intent = new Intent(context, cls);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            throw new IllegalThreadStateException();
        }
    }

    public static void startActivtiyForResult(Context context, Class cls, int requestCode, Bundle bundle) {
        if (bundle == null) {
            startActivtiyForResult(context, cls, requestCode);
            return;
        }
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            throw new IllegalThreadStateException();
        }
    }


    /**
     * 判断是否是电话号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
            return false;
        }
        return phoneNum.matches("[1][3578][0-9]{9}");
    }

    public static int px2dip(Context context, float pxValue) {
        final int scale = (int) context.getResources().getDisplayMetrics().density;
        int value = 0;
        switch (scale) {
            case 2:
                value = (int) (pxValue / scale + 0.5f);
                break;
            case 3:
                value = (int) (pxValue / scale + 0.5f);
                break;
            case 4:
                value = (int) (pxValue / 2 + 0.5f);
                break;
        }
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float pxValue) {
        final int scale = (int) context.getResources().getDisplayMetrics().density;
        return (int) ((pxValue - .05f) * scale);
    }

    /**
     * 初始化一组信息控件
     */
//    public static void initInfoItemView(View view, int layoutId, int iconId, String text) {
//        RelativeLayout rl = (RelativeLayout) view.findViewById(layoutId);
//        ((ImageView) rl.findViewById(R.id.item_info_icon)).setImageResource(iconId);
//        ((TextView) rl.findViewById(R.id.item_info_key)).setText(text);
//
//    }

    /**
     * 为一组信心空组赋值
     */
//    public static void assignmentInfoItem(View view, int layoutId, String text, boolean enabled) {
//        RelativeLayout rl = (RelativeLayout) view.findViewById(layoutId);
//        EditText et = (EditText) view.findViewById(R.id.item_info_value);
//        et.setText(text);
//        et.setEnabled(enabled);
//    }
    public static String formatDate(long mill) {
        if (mill == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(new Date(mill));
    }


    public static String formatDate2Min(long mill) {
        if (mill == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return sdf.format(new Date(mill));
    }

    public static String formatDate2Mill(long mill) {
        if (mill == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return sdf.format(new Date(mill));
    }

    public static long formatToMill(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(date);
            return parse.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String convertList2String(Set<Integer> set) {
        StringBuilder sb = new StringBuilder();
        if (set == null || set.size() == 0) {
            return "";
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next() + "、");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }

//    public static void showDatePick(Context context, final OnDateSelectedListener listener) {
//
//        final DatePicker datePicker = new DatePicker(context);
//        AlertDialog.Builder ab = new AlertDialog.Builder(context);
//        ab.setView(datePicker);
//        ab.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                int year = datePicker.getYear();
//                int month = datePicker.getMonth() + 1;
//                int dayOfMonth = datePicker.getDayOfMonth();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    Date date = sdf.parse(year + "-" + month + "-" + dayOfMonth);
//                    if (listener != null) {
//                        listener.onDateSelected(date.getTime());
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        ab.setPositiveButton(R.string.cancel, null);
//        ab.create().show();
//    }

    /**
     * Methods: setListViewHeight Description: 动态获取listview的高度
     *
     * @param listView void
     * @throws null
     */

    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 动态设置gridView的高度
     *
     * @param gridView
     * @param columns  列数
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void setGridViewHeight(GridView gridView, int columns) {
        ListAdapter adapter = gridView.getAdapter();
        int count = adapter.getCount();
        int row = count / columns;
        row = (count % columns) == 0 ? row : (row + 1);
        int totalHeight = 0;
        for (int i = 0; i < row; i++) {
            View view = adapter.getView(i, null, gridView);
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = totalHeight + (gridView.getVerticalSpacing() * (row - 1));
        gridView.setLayoutParams(layoutParams);
    }

    public final static String MD5Encoder(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        return defaultDisplay.getWidth();
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        return defaultDisplay.getHeight();
    }


    public static int getMatchTitleHeight(Context context) {
        int d = (int) context.getResources().getDisplayMetrics().density;
        int screenHeight = getScreenHeight(context);
        int myheight = 0;
        switch (screenHeight) {
            case 720:
                myheight = 146;
                break;
            case 1080:
                myheight = 300;
                break;
            case 1440:
                myheight = 500;
                break;
            default:
                myheight = 146;

        }
        return myheight;
    }

    /**
     * 判断网络
     *
     * @param context
     * @return
     */
    public static boolean isInterNetWork(Context context) {
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] allNetworkInfo = manager.getAllNetworkInfo();

        for (int i = 0; i < allNetworkInfo.length; i++) {
            if (allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 身份证验证（严格）
     *
     * @param str
     * @return
     */
    public static boolean checkID(String str) {
        boolean flag = false;
        if (str == null || str.length() < 18) {
            return flag;
        }
        String address = str.substring(0, 6);//6位，地区代码

        String birthday = str.substring(6, 14);//8位，出生日期

        String sequenceCode = str.substring(14, 17);//3位，顺序码：奇为男，偶为女

        String checkCode = str.substring(17);//1位，校验码：检验位
        String[] provinceArray = {"11:北京", "12:天津", "13:河北", "14:山西", "15:内蒙古", "21:辽宁", "22:吉林", "23:黑龙江", "31:上海", "32:江苏", "33:浙江", "34:安徽", "35:福建", "36:江西", "37:山东", "41:河南", "42:湖北 ", "43:湖南", "44:广东", "45:广西", "46:海南", "50:重庆", "51:四川", "52:贵州", "53:云南", "54:西藏 ", "61:陕西", "62:甘肃", "63:青海", "64:宁夏", "65:新疆", "71:台湾", "81:香港", "82:澳门", "91:国外"};

        boolean valideAddress = false;

        for (int i = 0; i < provinceArray.length; i++) {

            String provinceKey = provinceArray[i].split(":")[0];

            if (provinceKey.equals(address.substring(0, 2))) {

                valideAddress = true;
                break;

            }

        }
        //无效地区
        if (!valideAddress) {
            flag = false;
            return flag;
        }

        /***********验证出生日期*************/

        String year = birthday.substring(0, 4);

        String month = birthday.substring(4, 6);

        String day = birthday.substring(6);

        Date tempDate = new Date(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        if ((tempDate.getYear() != Integer.parseInt(year) ||
                tempDate.getMonth() != Integer.parseInt(month) - 1 ||
                tempDate.getDate() != Integer.parseInt(day))) {//
            flag = false;
        } else {

//            int[] weightedFactors = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};//加权因子
//
//            int[] valideCode = {1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};//身份证验证位值，其中10代表X
//
//            int sum = 0;//声明加权求和变量
//
//            String[] certificateNoArray = new String[str.length()];
//
//            for (int i = 0; i < str.length(); i++) {
//
//                certificateNoArray[i] = String.valueOf(str.charAt(i));
//
//            }
//
//            if ("x".equals(certificateNoArray[17].toLowerCase())) {
//
//                certificateNoArray[17] = "10";//将最后位为x的验证码替换为10
//
//            }
//            for (int i = 0; i < 17; i++) {
//
//                sum += weightedFactors[i] * Integer.parseInt(certificateNoArray[i]);//加权求和
//
//            }
//            int valCodePosition = sum % 11;//得到验证码所在位置
//
//            if (Integer.parseInt(certificateNoArray[17]) == valideCode[valCodePosition]) {
//                String sex = "男";
//                if (Integer.parseInt(sequenceCode) % 2 == 0) {
//                    sex = "女";
//                }
//                flag = true;
//
//            }
            flag = true;
        }
        return flag;
    }

    /**
     * 从身份证上号码中获取生日
     *
     * @param id
     * @return
     */
    public static long getBirthdayFromID(String id) {
        long birthday = 0;
        if (TextUtils.isEmpty(id) || id.length() < 18) {
            birthday = 0;
        }
        String substring = id.substring(6, 15);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sdf.parse(substring);
            birthday = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthday;
    }


    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @param activity
     * @return 1:MIUUI 2:Flyme 3:android6.0
     */
    public static int StatusBarLightMode(Activity activity) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                result = 1;
            } else if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                result = 2;
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                result = 3;
            }
        }
        return result;
    }

    /**
     * 已知系统类型时，设置状态栏黑色字体图标。
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @param activity
     * @param type     1:MIUUI 2:Flyme 3:android6.0
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void StatusBarLightMode(Activity activity, int type) {
        if (type == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), true);
        } else if (type == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), true);
        } else if (type == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    /**
     * 清除MIUI或flyme或6.0以上版本状态栏黑色字体
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void StatusBarDarkMode(Activity activity, int type) {
        if (type == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), false);
        } else if (type == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), false);
        } else if (type == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }

    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    public static void showWarnDailog(Context context, int resId) {
//        SweetAlertDialog dialog = new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
//        dialog.setContentText(context.getString(resId));
//        dialog.show();
    }

    public static void showToast(String text) {
        Looper.prepare();
        if (toast == null) {
            toast = Toast.makeText(PentaxK1.getInstance()
                    .getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }

        toast.show();
        Looper.loop();
    }

    /**
     * 批量设置onclicklistener
     *
     * @param listener
     * @param views
     */
    public static void setOnClickListeners(View.OnClickListener listener, View[] views) {
        if (views != null) {
            for (int i = 0; i < views.length; i++) {
                views[i].setOnClickListener(listener);
            }
        }
    }

    /**
     * 判断是不是wifi
     *
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 控制键盘显示
     *
     * @param editText
     */
    public static void showKeyBorad(final EditText editText) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void run() {
                InputMethodManager m = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 300);
    }

    /**
     * 拍照获取图片
     *
     * @param requestCode
     */
//    public static File getImageFromCamera(Activity act, int requestCode) {
//        Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
//        //Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        File photoFile = new File(JYFileHelper.getFileDir(Constants.PATH_IMAGE), String.valueOf(System.currentTimeMillis()) + ".jpg");
//        LogUtil.e("photoFile==" + photoFile.getAbsolutePath());
//        Uri imageUri = Uri.fromFile(photoFile);
//        getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        act.startActivityForResult(getImageByCamera, requestCode);
//        return photoFile;
//    }

//    public static Uri getImageFromCamera2(Activity act, int requestCode) {
//        //拍照
//        String SDState = Environment.getExternalStorageState();
//
//        //if (SDState.equals(Environment.MEDIA_MOUNTED)) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//"android.media.action.IMAGE_CAPTURE"
//            /***
//             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
//             * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
//             * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
//             */
//            ContentValues values = new ContentValues();
//            Uri photoUri = act.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//            act.startActivityForResult(intent, Constants.TAKE_PHOTO_UPDATE_REQUEST_CODE);
//        //}
//
//
//        //File photoFile = new File(JYFileHelper.getFileDir(Constants.PATH_IMAGE), String.valueOf(System.currentTimeMillis()) + ".jpg");
//        LogUtil.e("onActivityResult--拍照新建图片"+photoUri.getEncodedPath());
//        return photoUri;
//    }

    /**
     * 相册获取图片
     *
     * @param requestCode
     */
    public static void getImageFromAlbum(Activity act, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");//相片类型
        act.startActivityForResult(intent, requestCode);

    }

    /**
     * 图片多选
     *
     * @param activity
     */
//    public static void getImageFromAlbumByMultiple(Activity activity) {
//        getImageFromAlbumByMultiple(activity, 9);
//    }

    /**
     * 图片多选
     *
     * @param activity
     * @param count
     */
//    public static void getImageFromAlbumByMultiple(Activity activity, int count) {
//        PhotoPicker.builder()
//                .setPhotoCount(count)
//                .setShowCamera(false)
//                .setShowGif(false)
//                .setPreviewEnabled(false)
//                .start(activity, PhotoPicker.REQUEST_CODE);
//    }

    /**
     * 通过路径截取名字
     *
     * @param picturePath
     * @return
     */
    public static String getPicNameFromPath(String picturePath) {
        String temp[] = picturePath.replaceAll("\\\\", "/").split("/");
        String fileName = "";
        if (temp.length > 1) {
            fileName = temp[temp.length - 1];
        }
        return fileName;
    }
}
