package com.dianping.wizard.admin.resource;

import com.dianping.wizard.repo.GenericRepo;
import com.dianping.wizard.repo.GenericRepoFactory;
import com.dianping.wizard.repo.WidgetRepo;
import com.dianping.wizard.repo.WidgetRepoFactory;
import com.dianping.wizard.widget.Widget;

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

    private WidgetRepo repo= WidgetRepoFactory.getRepo("default");

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Widget loadWidget(@PathParam("name") String name){
        Widget widget= repo.loadByName(name);
        if(widget==null){
            widget=new Widget();
            widget.name=name;
            repo.save(widget);
        }
        return widget;
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveWidget(Widget widget){

         repo.save(widget);
    }



}
