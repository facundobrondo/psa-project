package proyectos.model;

import java.time.LocalDate;
import proyectos.exceptions.*;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_sequence", allocationSize = 1)
    private Long taskCode;

    private Long projectCode;

    private String name;

    private TaskStatus status;

    private String description;

    private Long employeeCode;

    private LocalDate startDate;

    private LocalDate endDate;

    private TaskPriority priority;



    public Task() {
        this.status = TaskStatus.NEW;
    }

    public Task(String name, String status, String description, Long employeeCode, String priority, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        if(status == null || status.isEmpty()){
            this.status = TaskStatus.NEW;
        } else {
            this.status = TaskStatus.valueOf(status.toUpperCase());
        }
        this.description = description;
        this.employeeCode = employeeCode;
        this.startDate = startDate;
        this.endDate = endDate;
        if(priority == null || priority.isEmpty()){
            this.priority = TaskPriority.LOW;
        } else {
            this.priority = TaskPriority.valueOf(priority.toUpperCase());
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
