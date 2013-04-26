package com.dianping.wizard.admin.resource;

import com.dianping.wizard.widget.Widget;
import com.dianping.wizard.widget.WidgetRepo;
import com.dianping.wizard.widget.WidgetRepoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: ltebean
 * Date: 13-4-25
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */

@Path("/widget")
public class WidgetOpertion {

    private WidgetRepo widgetRepo=new WidgetRepoImpl();

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Widget loadWidget(@PathParam("name") String name){

        return widgetRepo.loadWidgetByName(name);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateWidget(Widget widget){

         widgetRepo.updateWidget(widget);
    }



}
