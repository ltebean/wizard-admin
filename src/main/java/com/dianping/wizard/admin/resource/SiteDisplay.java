package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.wrapper.SiteRenderingRequest;
import com.dianping.wizard.site.SiteRenderer;
import com.dianping.wizard.site.SiteRendererFactory;
import com.dianping.wizard.widget.Widget;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: ltebean
 * Date: 13-4-25
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */

@Path("/site-output")
public class SiteDisplay {

    private SiteRenderer renderer= SiteRendererFactory.getRenderer("default");

    @POST
    @Path("/display")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String renderSiteDisplay(SiteRenderingRequest request){
        return renderer.renderSite(request.site, Widget.ModeType.Display.value, request.param);
    }

    @POST
    @Path("/config")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String renderSiteConfig(SiteRenderingRequest request){
        return renderer.renderSite(request.site, Widget.ModeType.Config.value, request.param);
    }

}
