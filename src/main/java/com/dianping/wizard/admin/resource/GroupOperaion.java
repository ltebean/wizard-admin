package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.domain.Group;
import com.dianping.wizard.repo.GenericRepo;
import com.dianping.wizard.repo.GenericRepoFactory;
import com.dianping.wizard.widget.Widget;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author ltebean
 */
@Path("/group")

public class GroupOperaion {

    private GenericRepo<Group> groupRepo= GenericRepoFactory.getGenericRepo(Group.class);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Group loadGroup(@PathParam("name") String name){
        Group group=groupRepo.loadByName(name);
        return group;
    }

}
