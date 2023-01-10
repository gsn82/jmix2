package com.company.jmix.screen.projecttask;

import io.jmix.ui.screen.*;
import com.company.jmix.entity.ProjectTask;

@UiController("ProjectTask.browse")
@UiDescriptor("project-task-browse.xml")
@LookupComponent("projectTasksTable")
public class ProjectTaskBrowse extends StandardLookup<ProjectTask> {
}