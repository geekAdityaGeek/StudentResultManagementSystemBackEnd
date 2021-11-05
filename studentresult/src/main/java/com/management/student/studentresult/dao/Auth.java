package com.management.student.studentresult.dao;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Auth")
public class Auth {

    @Id
    @GeneratedValue
    @Column(name = "auth_id")
    private int authId;
    private String email;
    private String password;
    private String status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_at")
    private Date modifiedAt;

    @PrePersist
    void created_at() {
        this.createdAt = this.modifiedAt = new Date();
    }

    @PreUpdate
    void modified_at() {
        this.modifiedAt = new Date();
    }

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
        this.status = "ACTIVE";
    }

}