package com.mbti.MbtiProJect.service;

import com.mbti.MbtiProJect.entity.Member;
import com.mbti.MbtiProJect.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void singup(Member member){
        memberRepository.save(member);
    }
    public List<Member> MemberListByName(String name){
        return memberRepository.findBymemberid(name);
    }
    public int login(String memberid,String memberpassword){
        int insertCount=0;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mariadb://localhost:3306/mbtidb","root","kkjjss103@");
            String SQL="SELECT memberPassword FROM member WHERE memberid=?";
            ps=conn.prepareStatement(SQL);
            ps.setString(1,memberid);
            rs=ps.executeQuery();
            if(rs.next()){
                if (rs.getString(1).equals(memberpassword)) {
                    return 1;//로그인성공
                }
                else {
                    return insertCount;
                }
            }
            return -1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -2;
    }
}