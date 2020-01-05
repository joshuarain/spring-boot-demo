package com.zhl.springboot.camunda.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhl.springboot.camunda.constant.ProjectProcessConstant;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class ProcessCtrl {
	
	@Autowired private ProcessEngine processEngine;
	
	@Autowired private TaskService taskService;
	
	@Autowired private RepositoryService repositoryService; 
	
	@Autowired private RuntimeService runtimeService;
	


    private static class ProjectParticipateRequestRecord {
        Long studentId;

        Long projectParticipateId;

        String taskId;

        public Long getProjectParticipateId() {
            return projectParticipateId;
        }

        public Long getStudentId() {
            return studentId;
        }

        public void setProjectParticipateId(Long projectParticipateId) {
            this.projectParticipateId = projectParticipateId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }
    }

    private static class UploadExtraInfoRecord {
        private String taskId;

        private String theUploadUrlOfExtraInfo;

        public String getTaskId() {
            return taskId;
        }

        public String getTheUploadUrlOfExtraInfo() {
            return theUploadUrlOfExtraInfo;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public void setTheUploadUrlOfExtraInfo(String theUploadUrlOfExtraInfo) {
            this.theUploadUrlOfExtraInfo = theUploadUrlOfExtraInfo;
        }
    }

    public ProcessCtrl(TaskService taskService, RuntimeService runtimeService) {
        this.taskService = taskService;
        this.runtimeService = runtimeService;
    }

    @RequestMapping(value = "start")
    public boolean ParticipatingProject() {
        //ignore argument verify

        //save the record to db

//        Long savedRecordId = 3L;
        //start a new instance of the process
//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put(ProjectProcessConstant.VAR_NAME_SCHOOL, "上海交通大学");
//        variables.put(ProjectProcessConstant.VAR_NAME_STUDENT, String.valueOf(userId));
//        variables.put(ProjectProcessConstant.FORM_RECORD_ID, savedRecordId);

        ProcessInstance instance = runtimeService.
                startProcessInstanceByKey("Process_Post");
        if (instance == null) {
            return false;
        }else {
            System.out.println(instance.getProcessDefinitionId());
            return true;
        }
    }





    @RequestMapping(value = "list")
    public  List<ProjectParticipateRequestRecord> getAllProjectParticipateRequest() {

        //get the taskList
        List<Task> tasks;
             tasks = taskService.createTaskQuery().
                            list();

        List<ProjectParticipateRequestRecord> records = new ArrayList<ProjectParticipateRequestRecord>(tasks.size());
        tasks.forEach( task -> {
            ProjectParticipateRequestRecord record = new ProjectParticipateRequestRecord();
            String taskId = task.getId();
            Map<String, Object> variables = taskService.getVariables(taskId);

            record.setTaskId(taskId);

            records.add(record);
        });

        return records;
    }



    @RequestMapping(value = "approve")
    public boolean approveProjectParticipateRequest( String taskId, boolean approve) {
        Task task = taskService.createTaskQuery().
               taskId(taskId).singleResult();
        if (task == null) {
            System.out.println("The task not found, task id is {}"+ taskId);
            return false;
        }else {
            //business logic here

            //Into next step
            System.out.println("The taskId is {}"+taskId);
            Map<String, Object> variables = new HashMap<>();
            variables.put("approve",  approve);
            taskService.complete(task.getId(), variables);
            return true;
        }
    }

  
}
