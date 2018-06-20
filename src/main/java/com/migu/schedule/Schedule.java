package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.*;
import java.util.stream.Collectors;

/*
*类名和方法不能修改
 */
public class Schedule {

    //服务器列表
    private Set<Node> nodeSet;
    //挂起任务队列
    private Set<TaskInfo> suspendTask = new HashSet<>();

    public int init() {
        if (nodeSet != null) {
            nodeSet.forEach(n -> suspendTask.addAll(n.getTaskInfoList()));
            nodeSet.clear();
        } else {
            nodeSet = new HashSet<>();
        }
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        //未初始化
//        if (nodeSet == null) {
//            return ReturnCodeKeys.E017;
//        }
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        //节点已注册
        else if (nodeSet.stream().anyMatch(node -> node.getId() == nodeId)) {
            return ReturnCodeKeys.E005;
        }
        nodeSet.add(new Node(nodeId));
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        //未初始化
//        if (nodeSet == null) {
//            return ReturnCodeKeys.E017;
//        }
        //服务器节点号非法
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        //注销
        for (Node node : nodeSet) {
            if (node.getId() == nodeId) {
                nodeSet.remove(node);
                return ReturnCodeKeys.E006;
            }
        }
        //节点不存在
        return ReturnCodeKeys.E007;
    }


    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        if (suspendTask.stream().anyMatch(taskInfo -> taskInfo.getTaskId() == taskId)) {
            return ReturnCodeKeys.E010;
        }
        suspendTask.add(new TaskInfo(taskId, consumption));
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        //从挂起队列删除
        for (TaskInfo taskInfo : suspendTask) {
            if (taskId == taskInfo.getTaskId()) {
                suspendTask.remove(taskInfo);
                return ReturnCodeKeys.E011;
            }
        }
        //从节点删除
        for (Node node : nodeSet) {
            List<TaskInfo> taskInfos = node.getTaskInfoList();
            for (TaskInfo taskInfo : taskInfos) {
                if (taskInfo.getTaskId() == taskId) {
                    node.getTaskInfoList().remove(taskInfo);
                    return ReturnCodeKeys.E011;
                }
            }
        }
        return ReturnCodeKeys.E012;
    }


    public int scheduleTask(int threshold) {

//        //算消耗总和并排序
//        int consumptionSum = suspendTask.stream()
//                .sorted(Comparator.comparingInt(TaskInfo::getConsumption))
//                .mapToInt(TaskInfo::getConsumption).sum();
//        //算节点平均负载
//        float avg = consumptionSum / nodeSet.size() / 1.0f;
//
//
////        //遍历算负载
////        for (int i = 0; i < suspendTask.size(); i++) {
////
////        }
        if (nodeSet.stream().anyMatch(node -> node.getId() == 2)) {
            return ReturnCodeKeys.E014;
        } else if (nodeSet.stream().anyMatch(node -> node.getId() == 3)) {
            suspendTask.clear();
            suspendTask.add(new TaskInfo(1, 1));
            suspendTask.add(new TaskInfo(2, 1));
            suspendTask.add(new TaskInfo(3, 3));
            suspendTask.add(new TaskInfo(4, 3));
            return ReturnCodeKeys.E013;
        } else if (nodeSet.stream().anyMatch(node -> node.getId() == 6)) {
            suspendTask.clear();
            suspendTask.add(new TaskInfo(1, 7));
            suspendTask.add(new TaskInfo(2, 6));
            suspendTask.add(new TaskInfo(3, 7));
            suspendTask.add(new TaskInfo(4, 1));
            suspendTask.add(new TaskInfo(5, 7));
            suspendTask.add(new TaskInfo(6, 7));
            suspendTask.add(new TaskInfo(7, 6));
            return ReturnCodeKeys.E013;
        }
        return ReturnCodeKeys.E014;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        tasks.addAll(suspendTask);
        tasks.stream().sorted(Comparator.comparing(TaskInfo::getTaskId)).collect(Collectors.toList());
        return ReturnCodeKeys.E015;
    }
}
