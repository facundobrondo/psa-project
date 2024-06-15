package proyectos.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_sequence", allocationSize = 1)
    private Long taskCode;

    private Long projectCode;

    private Long employeeCode;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private TaskStatus status;

    private TaskPriority priority;

    public Task() {
        this.status = TaskStatus.NEW;
    }

    public Task(Long projectCode, Long employeeCode, String name, LocalDate startDate, LocalDate endDate, TaskPriority priority) {
        this.projectCode = projectCode;
        this.employeeCode = employeeCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = TaskStatus.NEW;
        this.priority = priority;
    }

    public Long getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(Long projectCode) {
        this.projectCode = projectCode;
    }

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}
