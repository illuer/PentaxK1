package piao.yezi.pentaxk1.util;
import org.greenrobot.eventbus.EventBus;

/**
 * 
 * Created by lx on 2017/3/14.
 */

public class EventUtils{
        public static void register(Object context){
            if (!EventBus.getDefault().isRegistered(context)){
                EventBus.getDefault().register(context);
            }
        }
        public static void unregister(Object context){
            if (EventBus.getDefault().isRegistered(context)) {
                EventBus.getDefault().unregister(context);
            }
        }
        public static void sendEvent(Object object){
            EventBus.getDefault().post(object);
        }
        public static void sendStickyEvent(Object object){
            EventBus.getDefault().postSticky(object);
        }
        public static void removeStickyEvent(Object object){
            EventBus.getDefault().removeStickyEvent(object);
        }
         public static void removeAllStickyEvents(){
            EventBus.getDefault().removeAllStickyEvents();
        }
        
    
}
