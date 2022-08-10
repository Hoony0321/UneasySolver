package com.hunny.uneasySolver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class File {

    @Id @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    @NotNull
    private PostContent postContent;

    @NotNull
    private String path;

    @NotNull
    private Long height;

    @NotNull
    private  Long width;

    // 생성 메서드 //
    public static File createFile(String path, Long height, Long width){
        File file = new File();
        file.path = path;
        file.height = height;
        file.width = width;

        return file;
    }


    // set 메서드 //
    public void setPostContent(PostContent postContent){
        this.postContent = postContent;
    }

}
