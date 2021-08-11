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
