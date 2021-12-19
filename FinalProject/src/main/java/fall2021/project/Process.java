package fall2021.project;

/**
 * POJO class to store process
 */
public class Process {

    private Integer priority;
    private Integer processId;
    private Integer duration;
    private Integer arrivalTime;
    private Integer waitTime;
    private Integer completion;

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}