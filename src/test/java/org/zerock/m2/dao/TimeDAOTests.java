package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
//import org.graalvm.compiler.debug.Assertions;
import org.junit.jupiter.api.Test;

@Log4j2
public class TimeDAOTests {

    @Test
    public void testGetTime() {
        log.info("test get time..................");

        String timeStr = TimeDAO.INSTANCE.getTime();

        Assertions.assertNotNull(timeStr);
        //Assert 확신하다 . 이 테스트 결과는 null이 아닐것을 확신해야하다.
        //TDD(Test Driven Development) 테스트 주도 개발- 반복 테스트를 이용한 소프트웨어 방법론으로,
        // 작은 단위의 테스트 케이스를 작성하고 통과하는 코드를 추가하는 단계를 반복하여 구현한다.
        //실패하는 코드를 짜고 그 코드를 고쳐서 성공하게 만들고 다음 단계로 넘어간다.

    }
}




//package org.zerock.m2.dao;
//
//import lombok.extern.log4j.Log4j2;
//import org.graalvm.compiler.debug.Assertions;
//import org.junit.Test;
//import org.junit.api.Test;
//
//
//import java.sql.Time;
//
////로그4j 안들어간다 -> 사용하고 싶으면 빌드그레이들에서 롬복 컴파일
//// testCompileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.20' 적용
//@Log4j2
//public class TimeDAOTests {
//
//    @Test
//    public void testGetTime() {
//        log.info("test get time....");
//
//        String timeStr = TimeDAO.INSTANCE.getTime();
//
//        Assertions.assertNotNull(timeStr); // TDD
//
//
//    }
//
//
//}
