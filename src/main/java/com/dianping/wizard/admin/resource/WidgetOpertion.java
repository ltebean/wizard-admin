package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.domain.WidgetHistory;
import com.dianping.wizard.admin.repo.WidgetHistoryRepo;
import com.dianping.wizard.admin.repo.WidgetHistoryRepoImpl;
import com.dianping.wizard.admin.wrapper.LoadingLayoutRequest;
import com.dianping.wizard.admin.wrapper.WidgetSubmitRequest;
import com.dianping.wizard.config.Configuration;
import com.dianping.wizard.exception.WidgetException;
import com.dianping.wizard.repo.LayoutRepo;
import com.dianping.wizard.repo.LayoutRepoFactory;
import com.dianping.wizard.repo.WidgetRepo;
import com.dianping.wizard.repo.WidgetRepoFactory;
import com.dianping.wizard.script.ScriptEngine;
import com.dianping.wizard.script.ScriptEngineFactory;
import com.dianping.wizard.widget.Layout;
import com.dianping.wizard.widget.Widget;
import com.dianping.wizard.widget.extensions.ServiceLocator;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ltebean
 * Date: 13-4-25
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */

@Path("/widget")
public class WidgetOpertion {

    private static final int PAGE_SIZE = 10;

    private WidgetRepo widgetRepo = WidgetRepoFactory.getRepo("default");

    private LayoutRepo layoutRepo = LayoutRepoFactory.getRepo("default");

    private WidgetHistoryRepo historyRepo = new WidgetHistoryRepoImpl();

    private ScriptEngine scriptEngine = ScriptEngineFactory.getEngine("default");

    private Map<String, Object> injections;

    public WidgetOpertion() {
        this.injections = new HashMap<String, Object>();
        String locatorClassName = Configuration.get("extensions.serviceLocator", "", String.class);
        if (StringUtils.isNotEmpty(locatorClassName)) {
            try {
                ServiceLocator locator = (ServiceLocator) Class.forName(locatorClassName).newInstance();
                injections.put("service", locator);
            } catch (Exception e) {
                throw new WidgetException("extensions locator initialization failed", e);
            }
        }
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Widget loadWidget(@PathParam("name") String name) {
        Widget widget = widgetRepo.loadByName(name);
        return widget;
    }

    @POST
    @Path("/{name}/layout")
    @Produces(MediaType.APPLICATION_JSON)
    public Layout loadWidgetLayout(LoadingLayoutRequest request) {
        Widget widget = widgetRepo.loadByName(request.widgetName);
        if (StringUtils.isNotEmpty(widget.layoutName)) {
            return layoutRepo.loadByName(widget.layoutName);
        }
        //get param context
        Map<String, Object> context = new HashMap<String, Object>();
        context.putAll(injections);
        Map<String, Object> param = (Map<String, Object>) scriptEngine.eval(request.paramScript, context);
        //evaluate layout rule under the context
        context.putAll(param);
        String layoutName = (String) scriptEngine.eval(widget.layoutRule, context);
        return layoutRepo.loadByName(layoutName);
    }

    @GET
    @Path("/{name}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<WidgetHistory> loadWidgetHistory(@PathParam("name") String name, @QueryParam("page") int page) {
        return historyRepo.loadHistory(name, page * PAGE_SIZE, PAGE_SIZE);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveWidget(Widget widget) {
        widgetRepo.save(widget);
    }

    @POST
    @Path("/commit")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveWidget(WidgetSubmitRequest request) {
        //save history
        WidgetHistory widgetHistory = new WidgetHistory();
        widgetHistory.widget = request.widget;
        widgetHistory.comment = request.comment;
        widgetHistory.author = request.author;
        historyRepo.save(widgetHistory);
        //save data
        widgetRepo.save(request.widget);
    }
}
