package com.dianping.wizard.admin.resource;

import com.dianping.wizard.site.Layout;
import com.dianping.wizard.site.Site;
import com.dianping.wizard.site.repo.LayoutRepo;
import com.dianping.wizard.site.repo.LayoutRepoFactory;

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

    private LayoutRepo repo= LayoutRepoFactory.getRepo("default");

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Layout loadLayout(@PathParam("name") String name){

        return repo.loadLayoutByName(name);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveLayout(Layout layout){

        repo.saveLayout(layout);
    }



}
