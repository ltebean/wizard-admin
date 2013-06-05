package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.wrapper.RenderingRequest;
import com.dianping.wizard.widget.WidgetRenderer;
import com.dianping.wizard.widget.WidgetRendererFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: ltebean
 * Date: 13-4-25
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */

@Path("/widget-output")
public class WidgetDisplay {

    private WidgetRenderer renderer= WidgetRendererFactory.getRenderer("default");

    @POST
    @Path("/{mode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String renderWidget(@PathParam("mode") String mode,RenderingRequest request){
        return renderer.render(request.widget, mode, request.param).output;
    }


}
