package com.dianping.wizard.admin.repo;

import com.dianping.wizard.admin.domain.Group;
import com.dianping.wizard.repo.db.GenericDBRepo;

/**
 * @author ltebean
 */
public class GroupRepoImpl extends GenericDBRepo<Group> implements GroupRepo {


    public GroupRepoImpl() {
        super(Group.class);
    }

    @Override
    public Iterable<Group> loadAll() {
        return col.find().as(Group.class);
    }
}
