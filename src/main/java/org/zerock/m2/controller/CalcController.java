package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalcController", value = "/calc")
@Log4j2
public class CalcController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("CalcController doGet.............");
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/calcInput.jsp");   //리퀘스트를 어디로 보낼건지 지정. 거쳐서 jsp로 간다. 경로지정. 서버 내부의 지정
        dispatcher.forward(request,response);
    } //get방식

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doPost.............");

        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        String oper = request.getParameter("oper");

        log.info("num1Str: " + num1Str);
        log.info("num2Str: " + num1Str);
        log.info("oper: " + oper);     //post 방식 브라우저에서 호출 불가.

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/calcResult.jsp");   //리퀘스트를 어디로 보낼건지 지정. 거쳐서 jsp로 간다. 경로지정. 서버 내부의 지정
        dispatcher.forward(request,response);
    } //post방식
}
