package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterController" , value = "/msg/register")
@Log4j2
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       log.info("등록 화면 조회");

        HttpSession session = request.getSession();
        Object memeberObj = session.getAttribute("member");

        //로그인 관련 정보 없음 - 로그인 안한 사용자 & 로그인 실패
        if(memeberObj == null) {
            response.sendRedirect("/login");
            return; //실행의 제어권를 반납. 끝내는 문장
        }

       request.getRequestDispatcher("/WEB-INF/msg/register.jsp")
               .forward(request,response);

    }   // 입력하는 화면 보기


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //한글 처리
        request.setCharacterEncoding("UTF-8");

        //who, whom, content
        //파라미터를 수집 - > MsgDTO를 생성
        String who = request.getParameter("who");
        String whom = request.getParameter("whom");
        String content = request.getParameter("content");

        log.info("who : " + who);
        log.info("whom : " + whom);
        log.info("content : " + content);


        MsgDTO msgDTO = MsgDTO.builder().who(who).whom(whom).content(content).build();

        // MSgService 의 register() 호출
        MsgService.INSTANCE.register(msgDTO);

        //리다이렉트 response.sendRedirect();
        response.sendRedirect("/msg/list?whom=" + who);   //브라우저로 보냄.

        
        
        
    }
}
