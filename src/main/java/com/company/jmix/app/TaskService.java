package com.company.jmix.app;



import com.company.jmix.entity.Project;
import com.company.jmix.entity.ProjectTask;
import com.company.jmix.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {


    @Autowired
    private DataManager dataManager;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    public User findLeastBusyUser() {

        User user = dataManager.loadValues("select u,count(t.id) from User u left outer join ProjectTask t " +
                        "on u= t.assignee " +
                        "group by u order by count(t.id)")
                .properties("user", "task")
                .list().stream()
                .map(e -> e.<User>getValue("user"))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);

        return user;
    }

    public ProjectTask creatNewTask(Project project, String taskName, LocalDateTime starDate) {

        ProjectTask projectTask = dataManager.create(ProjectTask.class);
        projectTask.setProject(project);
        projectTask.setName(taskName);
        projectTask.setStartDate(starDate);
        projectTask.setEstimateEfforts(4);
        projectTask.setAssignee((User) currentAuthentication.getUser());

        return dataManager.save(projectTask);
    }

}