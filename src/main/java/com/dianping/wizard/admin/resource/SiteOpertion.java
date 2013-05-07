package com.dianping.wizard.admin.resource;

import com.dianping.wizard.repo.GenericRepo;
import com.dianping.wizard.repo.RepoFactory;
import com.dianping.wizard.site.Site;
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

@Path("/site")
public class SiteOpertion {

    private GenericRepo<Site> repo= RepoFactory.getRepo(Site.class);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Site loadSite(@PathParam("name") String name){
        Site site= repo.loadByName(name);
        if(site==null){
            site=new Site();
            site.name=name;
        }
        return site;
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveSite(Site site){

        repo.save(site);
    }



}
