package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MsgDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public enum MsgDAO {
    INSTANCE;
    //DAO 는 DB 용어를 쓴다

    private static final String SQL_INSERT = "insert into tbl_msg (who,whom,content) values(?,?,?)";

    private static final String SQL_LIST = "select mno, who, whom, if(whom = ?, 'R', 'S') kind, content, regdate, opendate\n" +
            "from\n" +
            "tbl_msg \n" +
            "where whom=? or who=? \n" +
            "order by kind asc, mno desc";

    public void insert(MsgDTO msgDTO) throws RuntimeException {

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception { //jdbc 인덱스 번호는 1부터시작
                //who,whom,content
                int idx = 1;
                preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setString(idx++,msgDTO.getWho());
                preparedStatement.setString(idx++,msgDTO.getWhom());
                preparedStatement.setString(idx++,msgDTO.getContent());

                int count = preparedStatement.executeUpdate();
                log.info("count :" + count); // 연결확인 값이 들어가면 1, 아니면 0
            }
        }.makeAll();



    }

    // who 인지 whom 인지 string으로 구분해서 MsgDTO의 lIst를 뒤지려고 만든다. 여러개의 값을 검색하려고 hashMap을 사용.Arraylist는  불가.
    // 어레이 리스트 하나의 한번 어레이 리스트를 두개로
    public Map<String, List<MsgDTO>> selectList(String user) throws RuntimeException {
        Map<String, List<MsgDTO>> listMap =  new HashMap<>(); // 틀만 만든것.
        //ListMap 에 MsgDTO의 어레이 리스트를 push, R,s 로 구분했던 것.
        listMap.put("R", new ArrayList<>()); // listMap에 있는 메소드는 put()을 사용
        listMap.put("S", new ArrayList<>()); //
        //put() 값을 넣어주는 기능 -> R을 가진 new ArrayList 를 listMap 으로 넣어준다.

        new JdbcTemplate() { //익명클래스 : 이름이 없고 조상의 이름또는 인터페이스의 이름을 가져와서 생성과 정의를 한 번에 하는 일회적인 클래스
            @Override
            protected void execute() throws Exception {

                preparedStatement = connection.prepareStatement(SQL_LIST);
                preparedStatement.setString(1,user);
                preparedStatement.setString(2,user);
                preparedStatement.setString(3,user);

                resultSet = preparedStatement.executeQuery();
                //resultSet. DB -> 자바로 가져오는는
                //Statement 자바에 쓸 명령어를 DB에 입력.

                log.info(resultSet); // 결과집합

                while(resultSet.next()){ // 다음 ㄷㅔ이터를 받아오려고 next

                    String kind = resultSet.getString(4); // 같은 문장 log.info(resultSet.getString(4));

                    List<MsgDTO> targetList = listMap.get(kind); //getXXX() 조회
                    //mno, who, whom, if(whom = ?, 'R', 'S') kind, content,
                    // regdate, opendate


                    targetList.add(MsgDTO.builder()
                            .mno(resultSet.getLong(1))
                            .who(resultSet.getString(2))
                            .whom(resultSet.getString(3))
                            .content(resultSet.getString(5))
                            .regdate(resultSet.getTimestamp(6))
                            .opendate(resultSet.getTimestamp(7))
                            .build());
                }
            }
        }.makeAll();

                         return listMap;


    }

}
