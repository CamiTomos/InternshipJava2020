package com.arobs.project.dtos;


public class TagDTO {
    private int id;
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
