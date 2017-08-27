package piao.yezi.pentaxk1;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.TextView;

import piao.yezi.pentaxk1.view.WindowProgress;

/**
 * Created by yezi on 05/08/2017.
 */

public class PentaxK1 extends Application {
    private WindowProgress root;
    @SuppressLint("StaticFieldLeak")
    public  static   Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }
    public static Context getInstance() {
        return context;
    }
    public synchronized void createWindowView(String msg){
        if (root != null){
            TextView tipTextView = (TextView) root.findViewById(R.id.tipTextView);// 提示文字
            tipTextView.setText(msg);// 设置加载信息
            return;
        }
        final WindowManager wm = (WindowManager) PentaxK1.getInstance().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams para = new WindowManager.LayoutParams();
        para.height = -2;//WRAP_CONTENT
        para.width = -2;//WRAP_CONTENT
        para.format = 1;

        para.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        para.gravity = Gravity.CENTER;

        para.type = WindowManager.LayoutParams.TYPE_TOAST;

        LayoutInflater inflater = LayoutInflater.from(context);
        root = (WindowProgress) inflater.inflate(R.layout.dialog_loading_two, null);// 得到加载view
        TextView tipTextView = (TextView) root.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        wm.addView(root, para);
        //监听返回键
        root.setDispatchKeyEventListener(mDispatchKeyEventListener);
    }

    public void closeWindowView(){
        if (root == null){
            return ;
        }
        WindowManager wm = (WindowManager) PentaxK1.getInstance().getSystemService(Context.WINDOW_SERVICE);
        wm.removeView(root);
        root = null;
    }
    /**
     * 返回鍵监听
     */
    private WindowProgress.DispatchKeyEventListener mDispatchKeyEventListener = new WindowProgress.DispatchKeyEventListener() {

        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                closeWindowView();
                return true;
            }
            return false;
        }
    };
}
