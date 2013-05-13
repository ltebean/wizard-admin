package com.dianping.wizard.admin.resource;

import com.dianping.wizard.repo.GenericRepo;
import com.dianping.wizard.repo.RepoFactory;
import com.dianping.wizard.widget.Layout;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: ltebean
 * Date: 13-4-25
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */

@Path("/layout")
public class LayoutOpertion {

    private GenericRepo<Layout> repo= RepoFactory.getRepo(Layout.class);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Layout loadLayout(@PathParam("name") String name){
        Layout layout= repo.loadByName(name);
        if (layout == null) {
            layout=new Layout();
            layout.name=name;
            repo.save(layout);
        }
        return layout;
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveLayout(Layout layout){

        repo.save(layout);
    }



}
