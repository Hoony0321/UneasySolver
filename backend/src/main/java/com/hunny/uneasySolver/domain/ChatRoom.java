package com.hunny.uneasySolver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class ChatRoom extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @ManyToOne
    private Post post; //게시글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problemer_id")
    private Member problemer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solver_id")
    private Member solver;

    // 생성 메서드 //
    public static ChatRoom createRoom(Post post, Member solver ){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.post = post;
        chatRoom.setProblemer(post.getAuthor());
        chatRoom.setSolver(solver);

        return chatRoom;
    }

    // 연관 관계 편의 메서드 //
    public void setProblemer(Member problemer) {
        this.problemer = problemer;
        problemer.setUneasyRoom(this);
    }

    public void setSolver(Member solver){
        this.solver = solver;
        solver.setSolveRoom(this);
    }

}
