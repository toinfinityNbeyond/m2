package org.zerock.m2.dao;

import org.zerock.m2.dto.MemberDTO;

public enum MemberDAO {
    INSTANCE;

    private static final String SQL_LOGIN = "select mid,mpw,mname,nickname,joindate,moddate \n" +
            "from tbl_member\n" +
            "where mid = ? and mpw =?";

    public MemberDTO selectByForLogin(String mid, String mpw) throws RuntimeException {
        MemberDTO dto = MemberDTO.builder().build();
        //mid,mpw,mname,nickname,joindate,
        // moddate

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception {
                preparedStatement = connection.prepareStatement(SQL_LOGIN);
                preparedStatement.setString(1, mid);     //1번째 물음표에 값을 던져준다.
                preparedStatement.setString(2, mpw);   //2번째 물음표에 값을 던져준다.

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next() == false) {    //만약에 레코드가 없으면 강제로 예외를 발생시키는것. 그럼 밑에 코드들은 실행x
                    throw new Exception("NOT EXIST");  //throw new 내 생각에 이거 잘못된거같아. 강제로
                }
                //예외가 없으면 아래에 문장 실행
                dto.setMid(resultSet.getString(1));
                dto.setMpw(resultSet.getString(2));
                dto.setMname(resultSet.getString(3));
                dto.setNickname(resultSet.getString(4));
                dto.setJoindate(resultSet.getTimestamp(5));

                dto.setModdate(resultSet.getTimestamp(6));
            }
            //makeAll 도 예외를 던지게 설계되어있다. 예외는 의사소통 수단


        }.makeAll();

        return dto;

    }
}