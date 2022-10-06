package com.hunny.uneasySolver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Target {

    @Id
    @GeneratedValue
    @Column(name = "target_id")
    private Long id;

    @NotNull
    private String name;

    public static Target createTarget(String name){
        Target target = new Target();
        target.name = name;

        return target;
    }
}
