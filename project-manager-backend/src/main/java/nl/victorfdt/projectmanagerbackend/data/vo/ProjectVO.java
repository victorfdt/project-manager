package nl.victorfdt.projectmanagerbackend.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@JsonPropertyOrder({"identifier", "name", "description", "startDate", "endDate"})
public class ProjectVO extends RepresentationModel<ProjectVO> {

    private String name;
    private String identifier;
    private String description;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

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

        if (!name.equals(projectVO.name)) return false;
        if (!identifier.equals(projectVO.identifier)) return false;
        if (!description.equals(projectVO.description)) return false;
        if (!startDate.equals(projectVO.startDate)) return false;
        return endDate.equals(projectVO.endDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + identifier.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
