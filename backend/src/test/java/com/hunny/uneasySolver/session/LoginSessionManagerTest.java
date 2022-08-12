package com.hunny.uneasySolver.session;

import com.hunny.uneasySolver.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginSessionManagerTest {

    @Autowired private LoginSessionManager sessionManager;

    @Test
    public void 세션테스트() throws Exception {

        Member member = Member.createDummyMember(1);
        MemberInfo_simple memberInfo = MemberInfo_simple.createMemberCookie(member);

        //response에 쿠기 담기
        MockHttpServletResponse response = new MockHttpServletResponse();
        Cookie cookie = sessionManager.createSession(memberInfo);

        //request에 쿠기 담기
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회
        MemberInfo_simple session = sessionManager.getSession(request);
        assertThat(session).isEqualTo(memberInfo);

        //세션 만료
        sessionManager.expire(request);
        MemberInfo_simple session1 = sessionManager.getSession(request);
        assertThat(session1).isEqualTo(null);
    }

}