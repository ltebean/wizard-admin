package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.wrapper.RenderingRequest;
import com.dianping.wizard.widget.DefaultWidgetRenderer;
import com.dianping.wizard.widget.Widget;
import com.dianping.wizard.widget.WidgetRenderer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ltebean
 * Date: 13-4-25
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */

@Path("/widget-output")
public class WidgetDisplay {

    private WidgetRenderer renderer=new DefaultWidgetRenderer();

    @POST
    @Path("/display")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String renderWidgetDisplay(RenderingRequest request){
        return renderer.renderWidget(request.getWidget(), Widget.ModeType.Display,request.getParam());
    }

    @POST
    @Path("/config")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String renderWidgetConfig(RenderingRequest request){
        return renderer.renderWidget(request.getWidget(), Widget.ModeType.Config,request.getParam());
    }

}
