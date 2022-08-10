package com.hunny.uneasySolver.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
public class PostContent {

    @Id @GeneratedValue
    @Column(name = "content_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "postContent", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<File>();

    // 생성 메서드 //
    public static PostContent createContent(String content, File ...files){
        PostContent postContent = new PostContent();
        postContent.content = content;
        postContent.setFiles(files);

        return postContent;
    }


    // 연관 관계 편의 메서드 //
    public  void setFiles(File ...files){
        Arrays.stream(files).forEach((file) -> {
            this.files.add(file);
            file.setPostContent(this);
        });
    }

    public static PostContent createPostContent(String content, File ...files){
        PostContent postContent = new PostContent();
        postContent.content = content;
        postContent.setFiles(files);

        return postContent;
    }

}
