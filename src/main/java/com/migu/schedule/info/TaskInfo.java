package com.migu.schedule.info;

/**
 * 任务状态信息类，请勿修改。
 *
 * @author
 */
public class TaskInfo {
    private int taskId;
    private int nodeId;
    //消耗
    private int consumption;
    //状态
    private int state; //0:挂起  1:执行

    public TaskInfo(int taskId, int consumption) {
        this.taskId = taskId;
        this.consumption = consumption;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TaskInfo [taskId=" + taskId + ", nodeId=" + nodeId + ", consumption=" + consumption + "]";
    }
}
