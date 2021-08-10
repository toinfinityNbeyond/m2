package org.zerock.m2.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HelloController", value = "/hello")  //value 값은 소문자.
public class HelloController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Hello Controller doGet");

        String data = "생성된 데이터";
        String[] arr = {"AAA","BBB","CCC","DDD","EEE","FFF"};

        request.setAttribute("msg", data); //화면 쪽은 hello2. jsp는 출력용도로 바뀌게됌
        request.setAttribute("arr",arr);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/hello2.jsp");   //리퀘스트를 어디로 보낼건지 지정. 거쳐서 jsp로 간다. 경로지정. 서버 내부의 지정

        dispatcher.forward(request,response);
        // 컨트롤러는 값을 받아서 디스패쳐를 이용

    }

}
