package com.migu.schedule;

import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyao on 18-6-20.
 */
public class Node {
    private int id;
    private List<TaskInfo> taskInfoList;

    public Node(int id) {
        this.id = id;
        this.taskInfoList = new ArrayList<TaskInfo>();
    }

    public Node(int id, List<TaskInfo> taskInfoList) {
        this.id = id;
        this.taskInfoList = taskInfoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TaskInfo> getTaskInfoList() {
        return taskInfoList;
    }

    public void setTaskInfoList(List<TaskInfo> taskInfoList) {
        this.taskInfoList = taskInfoList;
    }
}
