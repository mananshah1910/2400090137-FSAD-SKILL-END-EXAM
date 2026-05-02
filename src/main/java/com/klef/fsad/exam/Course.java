package com.klef.fsad.exam;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course_table")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_name", nullable = false, length = 100)
    private String name;

    @Column(name = "course_description", length = 255)
    private String description;

    @Column(name = "course_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "course_status", length = 50)
    private String status;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", status="
                + status + "]";
    }
}
