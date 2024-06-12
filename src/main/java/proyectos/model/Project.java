package proyectos.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_sequence", allocationSize = 1)
    private Long projectCode;

    private Long leaderCode;

    private Long productCode;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private StatusProject status;

    public Project() {
        this.status = StatusProject.INITIATED;
    }

    public Long getProjectCode() {
        return projectCode;
    }

    public Long getLeaderCode() {
        return leaderCode;
    }

    public void setLeaderCode(Long leaderCode) {
        this.leaderCode = leaderCode;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
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

    public StatusProject getStatus() {
        return status;
    }

    public void setStatus(StatusProject status) {
        this.status = status;
    }
}