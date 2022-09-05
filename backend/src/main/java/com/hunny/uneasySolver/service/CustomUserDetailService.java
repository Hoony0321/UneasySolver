package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findOne = memberRepository.findByEmail(username);

        if(findOne.isEmpty()){
            throw new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다.");
        }
        else{
            return createUserDetails(findOne.get());
        }

    }

    // DB에 USER 값이 존재한다면 UserDetails 객체를 만들어서 반환
    private UserDetails createUserDetails(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
