package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MemberDTO;
import org.zerock.m2.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


//GET 일때는 아이디와 패스워드 입력
//POST 일때는 로그인 처리-> /msg/list
@Log4j2
@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET login");

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //파라미터 수집 mid mpw
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

//        //사용자 정보 정보 구한다. -------> 사용자의 정보가 없다. ----> 다시 GET 방식으로 로그인페이지 값
//        MemberDTO memberDTO = MemberDTO.builder()
//                .mid(mid)
//                .mpw(mpw)
//                .mname("사용자" + mid)
//                .nickname("사용자" + mid)
//                .build();
 //
//    위에 없애고 아래 문장 적음

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);
            //세션에 setAttribute("member",사용자 정보)
            HttpSession session = request.getSession();
            session.setAttribute("member", memberDTO);

            // /msg/list 로 리다이렉트 시킨다.
            response.sendRedirect("/msg/list");


            }catch (Exception e) {
                log.error("Login Fail..." +e.getMessage());
                response.sendRedirect("/login?result-fail");
        }


        // 아래 정상적으로 가는 코드를 위에 코드로 적는다.
//        //세션에 setAttribute("member",사용자 정보)
//        HttpSession session = request.getSession();
//        session.setAttribute("member", memberDTO);
//
//        // /msg/list 로 리다이렉트 시킨다.
//        response.sendRedirect("/msg/list");



    }
}
