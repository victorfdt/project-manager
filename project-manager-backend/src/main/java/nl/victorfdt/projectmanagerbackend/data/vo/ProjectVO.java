package nl.victorfdt.projectmanagerbackend.data.vo;

import java.time.LocalDate;
import java.util.Objects;

public class ProjectVO {

    private Long id;
    private String name;
    private String identifier;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectVO projectVO = (ProjectVO) o;
        return Objects.equals(id, projectVO.id) && Objects.equals(name, projectVO.name) && Objects.equals(identifier, projectVO.identifier) && Objects.equals(description, projectVO.description) && Objects.equals(startDate, projectVO.startDate) && Objects.equals(endDate, projectVO.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, identifier, description, startDate, endDate);
    }
}
