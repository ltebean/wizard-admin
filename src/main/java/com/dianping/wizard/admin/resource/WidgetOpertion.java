package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.domain.WidgetHistory;
import com.dianping.wizard.admin.repo.WidgetHistoryRepo;
import com.dianping.wizard.admin.repo.WidgetHistoryRepoImpl;
import com.dianping.wizard.admin.wrapper.WidgetSubmitRequest;
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

    private static final int PAGE_SIZE=10;

    private WidgetRepo repo= WidgetRepoFactory.getRepo("default");

    private WidgetHistoryRepo historyRepo=new WidgetHistoryRepoImpl();

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Widget loadWidget(@PathParam("name") String name){
        Widget widget= repo.loadByName(name);
        return widget;
    }

    @GET
    @Path("/{name}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<WidgetHistory> loadWidgetHistory(@PathParam("name") String name,@QueryParam("page")int page){
        return historyRepo.loadHistory(name,page*PAGE_SIZE,PAGE_SIZE);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveWidget(WidgetSubmitRequest request){
        WidgetHistory widgetHistory=new WidgetHistory();

        widgetHistory.widget=request.widget;
        widgetHistory.comment=request.comment;
        widgetHistory.author=request.author;
        historyRepo.save(widgetHistory);
        repo.save(request.widget);

    }



}
