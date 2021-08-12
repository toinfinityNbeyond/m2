package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "RemoveController", value = "/msg/remove")
public class RemoveController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("do Post......................");

        Long mno = Long.parseLong(request.getParameter("mno"));
        String who = request.getParameter("who");

        log.info("mno  :" + mno);
        log.info("who : " + who);

        //삭제 쿼리
        MsgService.INSTANCE.remove(mno,who);

        //리다이렉트
        response.sendRedirect("/msg/list?whom=" + who);


    }
}
