package com.company.jmix.app;

import com.company.jmix.entity.Project;
import com.company.jmix.entity.ProjectStats;
import com.company.jmix.entity.ProjectTask;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectStatService {
    @Autowired
    private DataManager dataManager;

    public List<ProjectStats> fetchProjectStatistics(){

        List<Project> projects = dataManager.load(Project.class).all().fetchPlan("project-with-task").list();

        List<ProjectStats> projectStats = projects.stream().map(
                project -> {
                    ProjectStats stats = dataManager.create(ProjectStats.class);
                    stats.setId(project.getId());
                    stats.setProjectName(project.getName());

                    List<ProjectTask> tasks = project.getTasks();
                    stats.setTaskCount(tasks.size());

                    Integer estimatedEfforts = tasks.stream().map(ProjectTask::getEstimateEfforts).reduce(0, Integer::sum);

                    stats.setPlannedEfforts(estimatedEfforts);

                    stats.setActualEfforts( getActualEfforts(project.getId()) );

                    return stats;
                }).collect(Collectors.toList());

        return projectStats;
    };

    private  Integer getActualEfforts(UUID projectId){

        //внимание
        // если запрос не чего не вернет, то вылезет исключение

        Integer actualEfforts = dataManager.loadValue("select sum(te.timeSpend) from TimeEntity te "  +
                        "where te.task.project.id = :projectId", Integer.class)
                .parameter("projectId", projectId)
                .one();

        return actualEfforts;
    }


}