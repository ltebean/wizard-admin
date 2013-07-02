package com.dianping.wizard.admin.resource;

import com.dianping.wizard.admin.domain.Group;
import com.dianping.wizard.admin.repo.GroupRepo;
import com.dianping.wizard.admin.repo.GroupRepoImpl;
import com.dianping.wizard.repo.GenericRepo;
import com.dianping.wizard.repo.GenericRepoFactory;
import com.dianping.wizard.widget.Widget;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author ltebean
 */
@Path("/group")
public class GroupOperaion {

    private GroupRepo groupRepo= new GroupRepoImpl();

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Group loadGroup(@PathParam("name") String name){
        Group group=groupRepo.loadByName(name);
        return group;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Group> loadAllGroup(){
        Iterable<Group> groups=groupRepo.loadAll();
        return groups;
    }


    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Group saveGroup(Group group) {
        groupRepo.save(group);
        return group;
    }


}
