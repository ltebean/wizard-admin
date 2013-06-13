package com.dianping.wizard.admin.repo;

import com.dianping.wizard.admin.domain.WidgetHistory;
import com.dianping.wizard.repo.GenericRepo;

/**
 * @author ltebean
 */
public interface WidgetHistoryRepo extends GenericRepo<WidgetHistory>{

    public Iterable<WidgetHistory> loadHistory(String widgetName, int skip,int limit);

}
