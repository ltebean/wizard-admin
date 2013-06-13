package com.dianping.wizard.admin.repo;

import com.dianping.wizard.admin.domain.WidgetHistory;
import com.dianping.wizard.repo.db.GenericDBRepo;

/**
 * @author ltebean
 */
public class WidgetHistoryRepoImpl extends GenericDBRepo<WidgetHistory> implements WidgetHistoryRepo {


    public WidgetHistoryRepoImpl() {
        super(WidgetHistory.class);
    }

    @Override
    public Iterable<WidgetHistory> loadHistory(String widgetName, int skip,int limit) {
        return col.find("{widget.name:#}",widgetName).sort("{_id:-1}").skip(skip).limit(limit).as(WidgetHistory.class);
    }
}
