package com.dao;

import com.entity.Member;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    /**
     * 
     * @param name
     * @return
     */
    public List<Member> query(String name){


        List<Member> list = new ArrayList<Member>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from t_member where 1=1 ");
            if(name != null && !"".equals(name)){
                sb.append("and Name = ? ");
                params.add(name);
            }
           // sb.append("order by create_time desc");
            pstmt = con.prepareStatement(sb.toString());
//            if(params != null && params.size()>0){
//                for(int i=0; i<params.size(); i++){
//                    pstmt.setObject(i, params.get(i));
//                }
//            };
            if(params != null && params.size()>0){
                pstmt.setString(1,name);
            }

            rs = pstmt.executeQuery();
            while(rs.next()){

                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setFirstName(rs.getString("FirstName"));
                member.setSecondName(rs.getString("LastName"));
               // member.setName();
                member.setState(rs.getString("State"));
                member.setCity(rs.getString("City"));
                member.setStreet(rs.getString("Street"));
                member.setPhoneNumber(rs.getString("PhoneNumber"));
                member.setZipCode(rs.getString("ZipCode"));
                member.setSalary(rs.getDouble("Salary"));
                member.setWorkSchedule(rs.getString("WorkSchedule"));
                list.add(member);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return list;

    }

    /**
     * 
     * @param member
     * @return
     */
    public boolean save(Member member){

        Connection con = null;
        String sql = "insert into t_member(FirstName,LastName,Name,City,Street,PhoneNumber,ZipCode,Salary,WorkSchedule,State) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, member.getFirstName());
            pstmt.setString(2,member.getSecondName());
            pstmt.setString(3, member.getName());
//            Date date = new Date();
//            pstmt.setTimestamp(4, new Timestamp(date.getTime()));
//            pstmt.setTimestamp(5, new Timestamp(date.getTime()));
            pstmt.setString(4, member.getCity());
            pstmt.setString(5,member.getStreet());
            pstmt.setString(6, member.getPhoneNumber());
            pstmt.setString(7, member.getZipCode());
            pstmt.setDouble(8,member.getSalary());
            pstmt.setString(9, member.getWorkSchedule());
            pstmt.setString(10, member.getState());
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }

    /**
     * 
     * @param member
     * @return
     */
    public boolean update(Member member){

        Connection con = null;
        String sql = "update t_member set FirstName = ?,LastName = ?,City = ?,Street = ?,PhoneNumber = ?,ZipCode = ?,Salary = ?,WorkSchedule = ?,State = ?,Name = ? where id=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getFirstName());
            pstmt.setString(2,member.getSecondName());
           // pstmt.setString(3, member.getName());
//            Date date = new Date();
//            pstmt.setTimestamp(4, new Timestamp(date.getTime()));
//            pstmt.setTimestamp(5, new Timestamp(date.getTime()));
            pstmt.setString(3, member.getCity());
            pstmt.setString(4,member.getStreet());
            pstmt.setString(5, member.getPhoneNumber());
            pstmt.setString(6, member.getZipCode());
            pstmt.setDouble(7,member.getSalary());
            pstmt.setString(8, member.getWorkSchedule());
            pstmt.setString(9, member.getState());
            pstmt.setString(10, member.getName());
            pstmt.setInt(11,member.getId());
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }

    /**
     * 
     * @param id
     * @return
     */
    public boolean delete(int id){

        Connection con = null;
        String sql = "delete from t_member where id = ?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }




}
