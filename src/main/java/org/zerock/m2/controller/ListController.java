package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MemberDTO;
import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
@WebServlet(name = "ListController", value = "/msg/list")
public class ListController extends HttpServlet {


//    private MsgService msgService;  //정석




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("list controller doGet....");

        //로그인 체크 로직
        HttpSession session = request.getSession();
        Object memeberObj = session.getAttribute("member");

        //로그인 관련 정보 없음 - 로그인 안한 사용자 & 로그인 실패
        if(memeberObj == null) {
            response.sendRedirect("/login");
            return; //실행의 제어권를 반납. 끝내는 문장
        }
        MemberDTO memberDTO = (MemberDTO)memeberObj;

        String user =memberDTO.getMid();

        Map<String, List<MsgDTO>> result = MsgService.INSTANCE.getList(user); //() 안에 사용자가 입력한 값이 와야함.


        //jsp (view) 로 택배 전달 . request.setAttribute();
        request.setAttribute("Receive", result.get("R"));
        request.setAttribute("Send", result.get("S"));    // 목록을 따로따로 분리해서.


        request.getRequestDispatcher("/WEB-INF/msg/list.jsp").forward(request,response); // 택배 전달~!


    }
}
