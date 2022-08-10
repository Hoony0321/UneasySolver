package com.hunny.uneasySolver.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Job {

    @Id @GeneratedValue
    @Column(name = "job_id")
    private Long id;

    private String Name;
}
