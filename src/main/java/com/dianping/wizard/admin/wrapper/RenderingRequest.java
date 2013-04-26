package com.dianping.wizard.admin.wrapper;

import com.dianping.wizard.widget.Widget;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ltebean
 * Date: 13-4-26
 * Time: 上午9:23
 * To change this template use File | Settings | File Templates.
 */
public class RenderingRequest {

    private Widget widget;

    private Map<String,Object> param;

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
