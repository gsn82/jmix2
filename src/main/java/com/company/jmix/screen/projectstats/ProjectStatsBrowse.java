package com.company.jmix.screen.projectstats;

import com.company.jmix.app.ProjectStatService;
import io.jmix.core.LoadContext;
import io.jmix.ui.action.Action;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.jmix.entity.ProjectStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("ProjectStats.browse")
@UiDescriptor("project-stats-browse.xml")
@LookupComponent("projectStatsesTable")
public class ProjectStatsBrowse extends StandardLookup<ProjectStats> {

    @Autowired
    private ProjectStatService projectStatService;
    @Autowired
    private CollectionLoader<ProjectStats> statsDL;

    @Install(to = "statsDL", target = Target.DATA_LOADER)
    private List<ProjectStats> statsDLLoadDelegate(LoadContext<ProjectStats> loadContext) {
        return projectStatService.fetchProjectStatistics();
    }

    @Subscribe("projectStatsesTable.refreshAction")
    public void onProjectStatsesTableRefreshAction(Action.ActionPerformedEvent event) {
        statsDL.load();
    }


}