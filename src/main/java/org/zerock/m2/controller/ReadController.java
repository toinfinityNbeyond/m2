package org.zerock.m2.controller;

import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ReadController", value = "/msg/read")
public class ReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object memeberObj = session.getAttribute("member");

        //로그인 관련 정보 없음 - 로그인 안한 사용자 & 로그인 실패
        if(memeberObj == null) {
            response.sendRedirect("/login");
            return; //실행의 제어권를 반납. 끝내는 문장
        }


        Long mno = Long.parseLong(request.getParameter("mno"));

        MsgDTO msgDTO = MsgService.INSTANCE.read(mno);

        request.setAttribute("dto", msgDTO); //택배 포장

        request.getRequestDispatcher("/WEB-INF/msg/read.jsp").forward(request,response); //송장 붙여~~!, 보내~
    }

}
