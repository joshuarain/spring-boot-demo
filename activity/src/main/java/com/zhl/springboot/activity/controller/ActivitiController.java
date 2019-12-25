package com.zhl.springboot.activity.controller;

import com.zhl.springboot.activity.utils.ActivitiUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lenovo
 * @title: ActivityController
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/23 15:45
 */
@Controller
@RequestMapping("activiti")
@Slf4j
public class ActivitiController {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping("deploy")
    public Deployment deploy() {
        Deployment deployment = repositoryService.createDeployment()
                .name("请假流程")
                .addClasspathResource("processes/ask_for_leave.bpmn20.xml")
                .addClasspathResource("processes/ask_for_leave.png")
                .deploy();

        System.out.println("" + deployment);
        System.out.println("" + deployment.getId());
        System.out.println("" + deployment.getCategory());
        System.out.println("" + deployment.getKey());
        System.out.println("" + deployment.getName());
        System.out.println("" + deployment.getProjectReleaseVersion());
        System.out.println("" + deployment.getTenantId());
        System.out.println("" + deployment.getDeploymentTime());
        System.out.println("" + deployment.getVersion());

        return deployment;
    }


    @RequestMapping("startProcessInstance")
    public void startProcessInstance(String userId) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("applyUserId", userId);
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("ask_for_leave", vars);

        System.out.println("" + processInstance.getProcessDefinitionId());
        System.out.println("" + processInstance.getProcessDefinitionKey());
        System.out.println("" + processInstance.getProcessDefinitionName());
        System.out.println("" + processInstance.getProcessDefinitionVersion());

        System.out.println("" + processInstance.getId());
        System.out.println("" + processInstance.getParentId());
        System.out.println("" + processInstance.getProcessInstanceId());
        System.out.println("" + processInstance.getParentProcessInstanceId());
        System.out.println("" + processInstance.getRootProcessInstanceId());

        System.out.println("" + processInstance.getName());
        System.out.println("" + processInstance.getLocalizedName());
        System.out.println("" + processInstance.getDescription());
        System.out.println("" + processInstance.getLocalizedDescription());

        System.out.println("" + processInstance.getBusinessKey());
        System.out.println("" + processInstance.getDeploymentId());

        System.out.println("" + processInstance.getActivityId());
        System.out.println("" + processInstance.getStartUserId());
        System.out.println("" + processInstance.getTenantId());


        System.out.println("" + processInstance.getSuperExecutionId());

        System.out.println("" + processInstance.getStartTime());

        System.out.println("" + processInstance.getProcessVariables());
        Map<String, Object> processVariables = processInstance.getProcessVariables();
        processVariables.forEach((key, value) -> {
            System.out.println(key + "->" + value);
        });


    }


    @RequestMapping("apply")
    public String apply(String taskId, String applyUserId) {
        taskService.setVariableLocal(taskId, "请假天数", 5);
        taskService.setVariable(taskId, "请假日期", new Date());
        taskService.setVariable(taskId, "请假原因", "回家探亲，一起吃个饭");
        taskService.setVariable(taskId, "applyUserId", applyUserId);
        return "success";
    }

    @RequestMapping("userTask")
    @ResponseBody
    public List<Map<String, String>> userTask(String userId) {
        List<Task> userTasks = taskService.createTaskQuery()
                .taskAssignee(userId)
                .list();
        List<Map<String, String>> resultList = new ArrayList<>();
        userTasks.forEach(task -> {
            Map<String, String> map = new HashMap<>();
            map.put("taskId", task.getId());
            map.put("name", task.getName());
            map.put("createTime", task.getCreateTime().toString());
            map.put("assignee", task.getAssignee());
            map.put("instanceId", task.getProcessInstanceId());
            map.put("executionId", task.getExecutionId());
            map.put("definitionId", task.getProcessDefinitionId());
            resultList.add(map);
        });
        return resultList;
    }

    @GetMapping("groupTask")
    public void groupTask(String groupId) {
        List<Task> groupTasks = taskService.createTaskQuery()
                .taskCandidateGroup(groupId)
                .list();
        iteratorTasks(groupTasks);
        return;
    }

    private void iteratorTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println("id=" + task.getId());
            System.out.println("name=" + task.getName());
            System.out.println("assignee=" + task.getAssignee());
            System.out.println("createTime=" + task.getCreateTime());
            System.out.println("executionId=" + task.getExecutionId());
            System.out.println(task.getDelegationState());
            System.out.println(task.getAssignee());
            System.out.println(task.getCategory());
            System.out.println(task.getProcessDefinitionId());
            System.out.println(task.getProcessInstanceId());
            System.out.println(task.getProcessVariables());
        }
    }

    /**
     * 查看任务成员列表
     *
     * @param taskId
     */
    @GetMapping("identityLinksForTask")
    public void identityLinksForTask(String taskId) {
        List<IdentityLink> identityLinks = taskService.getIdentityLinksForTask(taskId);
        iteratorIdentityLink(identityLinks);
    }

    /**
     * 查看任务历史成员列表
     *
     * @param taskId
     */
    @GetMapping("historicIdentityLinksForTask")
    public void historicIdentityLinksForTask(String taskId) {
        List<HistoricIdentityLink> historicIdentityLinks = historyService.getHistoricIdentityLinksForTask(taskId);
        iteratorHistoricIdentityLink(historicIdentityLinks);
    }


    private void iteratorIdentityLink(List<IdentityLink> identityLinks) {
        for (IdentityLink identityLink : identityLinks) {
            System.out.println("userId=" + identityLink.getUserId());
            System.out.println("taskId=" + identityLink.getTaskId());
            System.out.println("piId=" + identityLink.getProcessInstanceId());
            System.out.println(identityLink.getGroupId());
            System.out.println(identityLink.getProcessDefinitionId());
            System.out.println(identityLink.getType());
        }
    }

    private void iteratorHistoricIdentityLink(List<HistoricIdentityLink> historicIdentityLinks) {
        for (HistoricIdentityLink historicIdentityLink : historicIdentityLinks) {
            System.out.println("userId=" + historicIdentityLink.getUserId());
            System.out.println("taskId=" + historicIdentityLink.getTaskId());
            System.out.println("piId=" + historicIdentityLink.getProcessInstanceId());
            System.out.println(historicIdentityLink.getGroupId());
            System.out.println(historicIdentityLink.getType());
        }
    }

    @RequestMapping("completeTask")
    public void completeTask(String taskId) {
        taskService.complete(taskId);
    }

    @RequestMapping("claimTask")
    public void claimTask(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    @PostMapping("addCandidateUser")
    public void addCandidateUser(String taskId, String userId) {
        taskService.addCandidateUser(taskId, userId);
    }

    @DeleteMapping("deleteCandidateUser")
    public void deleteCandidateUser(String taskId, String userId) {
        taskService.deleteCandidateUser(taskId, userId);
    }

    @GetMapping("processDefinitionImage")
    public void processDefinitionImage(String processDefinitionId, HttpServletResponse response) throws IOException {


        InputStream inputStream = this.getDefinitionImage(processDefinitionId);
        OutputStream outputStream = response.getOutputStream();
        copyImageStream(inputStream, outputStream);
    }

    public static void copyImageStream(InputStream inputStream, OutputStream outputStream) {
        try {
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取流程定义图片
     *
     * @param processDefinitionId
     * @return
     */
    public InputStream getDefinitionImage(String processDefinitionId) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        System.out.println("processDefinition:" + processDefinition);
        System.out.println(processDefinition.getDeploymentId());
        System.out.println(processDefinition.getDiagramResourceName());
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        return inputStream;
    }


    @ResponseBody
    @RequestMapping(value = "showImg")
    public void showImg(String instanceId, HttpServletResponse response) {
        /*
         * 参数校验
         */
        log.info("查看完整流程图！流程实例ID:{}", instanceId);
        if (StringUtils.isBlank(instanceId)) {
            return;
        }


        /*
         *  获取流程实例
         */
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        if (processInstance == null) {
            log.error("流程实例ID:{}没查询到流程实例！", instanceId);
            return;
        }

        // 根据流程对象获取流程对象模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());


        /*
         *  查看已执行的节点集合
         *  获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
         */
        // 构造历史流程查询
        HistoricActivityInstanceQuery historyInstanceQuery = historyService.createHistoricActivityInstanceQuery().processInstanceId(instanceId);
        // 查询历史节点
        List<HistoricActivityInstance> historicActivityInstanceList = historyInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();
        if (historicActivityInstanceList == null || historicActivityInstanceList.size() == 0) {
            log.info("流程实例ID:{}没有历史节点信息！", instanceId);
            outputImg(response, bpmnModel, null, null);
            return;
        }
        // 已执行的节点ID集合(将historicActivityInstanceList中元素的activityId字段取出封装到executedActivityIdList)
        List<String> executedActivityIdList = historicActivityInstanceList.stream().map(item -> item.getActivityId()).collect(Collectors.toList());

        /*
         *  获取流程走过的线
         */
        // 获取流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
        List<String> flowIds = ActivitiUtils.getHighLightedFlows(bpmnModel, processDefinition, historicActivityInstanceList);


        /*
         * 输出图像，并设置高亮
         */
        outputImg(response, bpmnModel, flowIds, executedActivityIdList);
    }

    /**
     * <p>输出图像</p>
     *
     * @param response               响应实体
     * @param bpmnModel              图像对象
     * @param flowIds                已执行的线集合
     * @param executedActivityIdList void 已执行的节点ID集合
     * @author FRH
     * @time 2018年12月10日上午11:23:01
     * @version 1.0
     */
    private void outputImg(HttpServletResponse response, BpmnModel bpmnModel, List<String> flowIds, List<String> executedActivityIdList) {
        InputStream imageStream = null;
        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
        try {
            imageStream = processDiagramGenerator.generateDiagram(bpmnModel, executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", true, "png");
            // 输出资源内容到相应对象
            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("流程图输出异常！", e);
        } finally { // 流关闭
            try {
                imageStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
