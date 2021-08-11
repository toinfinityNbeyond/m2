package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum TimeDAO {   //enum하면 인스턴스를몇개 만들지 지정. 하나면 INSTANCE;

    INSTANCE;

    private static  final  String SQL = "select now()";  //쿼리문이 변하지 않으면 파이널 선언.  현재시간 보여주는 sql

    public String getTime() throws RuntimeException { //자신 없으면 런타임

        //익명클래스를 외부에서 가져올 때 사용
        StringBuffer buffer = new StringBuffer();

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception {

                //익명클래스 사용
                preparedStatement = connection.prepareStatement(SQL);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();  // 필수 한칸 띄우기
                buffer.append(resultSet.getString(1));
            }
        }.makeAll();   //makeall. RuntimeException을 던지게 설계 되어있다.

        return buffer.toString();
    }  // 스레드 세이프하게 설계
}


