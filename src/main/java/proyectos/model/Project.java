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

    private ProjectStatus status;

    public Project() {
        this.status = ProjectStatus.INITIATED;
    }

    public Project(Long leaderCode, Long productCode, String name, LocalDate startDate, LocalDate endDate) {
        this.leaderCode = leaderCode;
        this.productCode = productCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ProjectStatus.INITIATED;
    }

    public Project(Long productCode, String name, LocalDate startDate, LocalDate endDate) {
        this.productCode = productCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ProjectStatus.INITIATED;
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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}