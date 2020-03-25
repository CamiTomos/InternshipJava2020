package com.arobs.project.dtos;

import javax.validation.constraints.NotNull;

//@JsonIgnoreProperties(value = "id", allowGetters = true)
public class TagDTO {
    private int id;
    @NotNull
    private String tagDescription;

    public TagDTO() {
    }

    public TagDTO(int id, String tagDescription) {
        this.id = id;
        this.tagDescription = tagDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", tagDescription='" + tagDescription + '\'' +
                '}';
    }
}
