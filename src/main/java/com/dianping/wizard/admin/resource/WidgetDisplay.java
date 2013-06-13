package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.wrapper.RenderingRequest;
import com.dianping.wizard.config.Configuration;
import com.dianping.wizard.exception.WidgetException;
import com.dianping.wizard.script.ScriptEngine;
import com.dianping.wizard.script.ScriptEngineFactory;
import com.dianping.wizard.widget.WidgetRenderer;
import com.dianping.wizard.widget.WidgetRendererFactory;
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
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */

@Path("/widget-output")
public class WidgetDisplay {

    private WidgetRenderer renderer= WidgetRendererFactory.getRenderer("default");

    private ScriptEngine scriptEngine=ScriptEngineFactory.getEngine("default");

    private Map<String,Object> bindings;

    public WidgetDisplay() {
        this.bindings=new HashMap<String, Object>();
        String locatorClassName= Configuration.get("extensions.serviceLocator", "", String.class);
        if(StringUtils.isNotEmpty(locatorClassName)){
            try{
                ServiceLocator locator=(ServiceLocator)Class.forName(locatorClassName).newInstance();
                bindings.put("service",locator);
            }catch (Exception e){
                throw new WidgetException("extensions locator initialization failed",e);
            }
        }
    }

    @POST
    @Path("/{mode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String renderWidget(@PathParam("mode") String mode,RenderingRequest request){
        Map<String,Object> param=(Map<String,Object>)scriptEngine.eval(request.paramScript,bindings);
        return renderer.render(request.widget, mode, param).output;
    }


}
