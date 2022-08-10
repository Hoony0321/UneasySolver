package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    private final EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll(){
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        return members;
    }


}
