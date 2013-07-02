package com.dianping.wizard.admin.repo;

import com.dianping.wizard.admin.domain.Group;
import com.dianping.wizard.repo.GenericRepo;

/**
 * @author ltebean
 */
public interface GroupRepo extends GenericRepo<Group> {

    Iterable <Group> loadAll();

}
