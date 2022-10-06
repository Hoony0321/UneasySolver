package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.RefreshToken;
import com.hunny.uneasySolver.dto.MemberLoginRequest;
import com.hunny.uneasySolver.dto.MemberRequestDto;
import com.hunny.uneasySolver.dto.MemberResponseDto;
import com.hunny.uneasySolver.dto.TokenRequestDto;
import com.hunny.uneasySolver.repository.MemberRepository;
import com.hunny.uneasySolver.repository.RefreshTokenRepository;
import com.hunny.uneasySolver.security.JwtProvider;
import com.hunny.uneasySolver.security.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toMember(passwordEncoder);

        Long saveId = memberRepository.save(member);
        return MemberResponseDto.of(member);
    }

    @Transactional
    public TokenDto login(MemberLoginRequest request){
        //멤버 정보 가져오기
        Optional<Member> findOne = memberRepository.findByEmail(request.getEmail());
        if(findOne.isEmpty()){
            throw new RuntimeException("해당 이메일 계정은 존재하지 않습니다.");
        }
        Member member = findOne.get();


        // Login id/pw 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        // 실제 검증이 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomeUserDetailService에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtProvider.generateToken(member);

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissueToken(TokenRequestDto tokenRequestDto){

        // RefreshToken 적용
        if(!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())){
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // AccessToken에서 Member ID 가져오기
        Authentication authentication = jwtProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 저장소에서 Member ID 기반으로 Refresh Token 값 가져오기
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // Refresh 토큰 일치하는지 검사
        if(!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())){
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        Member member = memberRepository.findByEmail(authentication.getName()).get();
        TokenDto tokenDto = jwtProvider.generateToken(member);

        // 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
