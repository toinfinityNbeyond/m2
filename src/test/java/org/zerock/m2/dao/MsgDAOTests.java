package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.m2.dto.MsgDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Log4j2
public class MsgDAOTests {

    @Test
    public void testDelete() {
        Long mno = 1L;
        String who = "user1";

        MsgDAO.INSTANCE.delete(mno,who);
        
        log.info("성공");

    }


    @Test
    public void testSelect() {

        Long mno = 118L;   //118번에 있는 값을 조회

        log.info(MsgDAO.INSTANCE.select(mno));

    }

    @Test
    public void testInsertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            String who = "user" + (int) (Math.random() * 10);
            String whom = "user" + (int) (Math.random() * 10);
            String content = "sample ..." + i;

            MsgDTO dto = MsgDTO.builder().who(who).whom(whom).content(content).build();

            MsgDAO.INSTANCE.insert(dto);
        });
    }

    @Test
    public void testList() {

        Map<String, List<MsgDTO>> result = MsgDAO.INSTANCE.selectList("user5");

        log.info("받은 목록...........");

        List<MsgDTO> receiveList = result.get("R");

        receiveList.forEach(msgDTO -> log.info(msgDTO)); //stream 생략

        log.info("보낸 목록...........");

        List<MsgDTO> sendList = result.get("S");

        sendList.forEach(msgDTO -> log.info(msgDTO)); //stream 생략
    }


}

