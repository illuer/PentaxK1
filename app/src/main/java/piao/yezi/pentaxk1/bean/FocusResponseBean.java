package piao.yezi.pentaxk1.bean;

import java.util.List;

/**
 * Created by yezi on 05/08/2017.
 */

public class FocusResponseBean extends  BaseResponseBean{

    /**
     * focused : true
     * focusCenters : []
     * focusEffectiveArea : [70,64]
     */

    private boolean focused;
    private List<?> focusCenters;
    private List<Integer> focusEffectiveArea;

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public List<?> getFocusCenters() {
        return focusCenters;
    }

    public void setFocusCenters(List<?> focusCenters) {
        this.focusCenters = focusCenters;
    }

    public List<Integer> getFocusEffectiveArea() {
        return focusEffectiveArea;
    }

    public void setFocusEffectiveArea(List<Integer> focusEffectiveArea) {
        this.focusEffectiveArea = focusEffectiveArea;
    }
}
