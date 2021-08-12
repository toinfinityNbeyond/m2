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

    private static final String SQL_SELECT = "select mno, who, whom, content, regdate, opendate from tbl_msg where mno =?";
    //"select * from tbl_msg where mno =?";  여기서 *로 하면 알아보기 힘들어서 컬럼명으로 바꾼다.
    // mno 값을 받아서 -> 해당 mno의 다른 값들(who,whom 등)을 불러오는 구문

    private static final String SQL_UPDATE_OPEN = "update tbl_msg set opendate = now() where mno =?";

    private static final String SQL_DELETE =  "delete from tbl_msg where mno=? and who=?";

    public void delete(Long mno, String who) throws RuntimeException {

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception { //jdbc 인덱스 번호는 1부터시작
                //who,whom,content
                preparedStatement = connection.prepareStatement(SQL_DELETE);
                preparedStatement.setLong(1,mno);
                preparedStatement.setString(2,who);
                preparedStatement.executeUpdate();

            }
        }.makeAll();

    }

    public MsgDTO select(Long mno) throws RuntimeException{

        MsgDTO msgDTO = MsgDTO.builder().build();

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception { //jdbc 인덱스 번호는 1부터시작
                //who,whom,content

                preparedStatement = connection.prepareStatement(SQL_UPDATE_OPEN);
                preparedStatement.setLong(1,mno);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                preparedStatement = null;

                preparedStatement = connection.prepareStatement(SQL_SELECT);
                preparedStatement.setLong(1,mno); 
                resultSet = preparedStatement.executeQuery();

                resultSet.next();

                msgDTO.setMno(resultSet.getLong(1));
                msgDTO.setWho(resultSet.getString(2));
                msgDTO.setWhom(resultSet.getString(3));
                msgDTO.setContent(resultSet.getString(4));
                msgDTO.setContent(resultSet.getString(5));

                msgDTO.setContent(resultSet.getString(6));


            }
        }.makeAll();
        return msgDTO;
    }






    public void insert(MsgDTO msgDTO) throws RuntimeException { //자신없으면 void

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

                //데이터의 갯수가 몇개인지 모름. 커서를 아래로 몇개로 이동해야하는지 모름. 그래서 사용함.
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
