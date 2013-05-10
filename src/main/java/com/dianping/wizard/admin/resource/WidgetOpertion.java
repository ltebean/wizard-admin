package com.dianping.wizard.admin.resource;

import com.dianping.wizard.repo.GenericRepo;
import com.dianping.wizard.repo.RepoFactory;
import com.dianping.wizard.widget.Widget;
import org.json.simple.JSONObject;

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

    private GenericRepo<Widget> repo= RepoFactory.getRepo(Widget.class);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Widget loadWidget(@PathParam("name") String name){
        Widget widget= repo.loadByName(name);
        if(widget==null){
            widget=new Widget();
            widget.name=name;
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
