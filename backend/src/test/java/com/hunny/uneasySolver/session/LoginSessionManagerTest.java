package com.hunny.uneasySolver.session;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginSessionManagerTest {

    @Autowired private LoginSessionManager sessionManager;

    @Test
    public void 세션테스트() throws Exception {

        Member member = Member.createDummyMember(1);
        MemberDTO memberInfo = new MemberDTO(member);

        //response에 쿠기 담기
        MockHttpServletResponse response = new MockHttpServletResponse();
        sessionManager.createSession(memberInfo, response);

        //request에 쿠기 담기
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회
        MemberDTO session = sessionManager.getSession(request);
        assertThat(session).isEqualTo(memberInfo);

        //세션 만료
        sessionManager.expire(request);
        MemberDTO session1 = sessionManager.getSession(request);
        assertThat(session1).isEqualTo(null);
    }

}