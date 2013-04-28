package com.dianping.wizard.admin.resource;

import com.dianping.wizard.site.Site;
import com.dianping.wizard.site.repo.SiteRepo;
import com.dianping.wizard.site.repo.SiteRepoFactory;
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

    private SiteRepo siteRepo= SiteRepoFactory.getRepo("default");

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Widget loadSite(@PathParam("name") String name){

        return siteRepo.loadSiteByName(name);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveSite(Site site){

        siteRepo.saveSite(site);
    }



}
