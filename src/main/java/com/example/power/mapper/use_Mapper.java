package com.example.power.mapper;
// ^^ @author ZJH111
// ^^ @date 2023/4/2

import com.example.power.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface use_Mapper {

    @Insert("insert into user (PWID,username,password) values (#{PWID},#{username},#{password})")
    void JOIN_PW(@Param("PWID") String PWID,@Param("username") String username,@Param("password") String password);

    @Select("select count(*) from user")
    int PW_MAXID();

    @Update("update user set username=#{changename} where PWID=#{PWID}")
    void Uname(@Param("changename") String changname,@Param("PWID") String PWID);

    @Update("update user set benchPress=#{benchPress} where PWID=#{PWID}")
    void updata_A(@Param("benchPress") int benchPress,@Param("PWID") String PWID);

    @Update("update user set pullHard=#{pullHard} where PWID=#{PWID}")
    void updata_B(@Param("pullHard") int benchPress,@Param("PWID") String PWID);

    @Update("update user set deepSquat=#{deepSquat} where PWID=#{PWID}")
    void updata_C(@Param("deepSquat") int deepSquat,@Param("PWID") String PWID);

    @Update("update user set total=benchPress+pullHard+deepSquat where PWID=#{PWID}")
    void updata_total(String PWID);

    @Select("SELECT * from user where PWID=#{PWID} and password=#{password}")
    User select_login(User user);

    @Select("SELECT PWID,username,benchPress,pullHard,deepSquat,total from user where PWID=#{PWID}")
    User select_By_pwid(String PWID);

    @Select("SELECT PWID,username,benchPress,pullHard,deepSquat,total from user")
    List<User> select_all();

    @Select("SELECT PWID,username,benchPress,pullHard,deepSquat,total from user ORDER BY total DESC LIMIT #{number}")
    List<User> selectTOP(int number);

    @Select("SELECT PWID,username,benchPress,pullHard,deepSquat,total from user ORDER BY benchPress DESC LIMIT #{number}")
    List<User> selectTOPA(int number);

    @Select("SELECT PWID,username,benchPress,pullHard,deepSquat,total from user ORDER BY pullHard DESC LIMIT #{number}")
    List<User> selectTOPB(int number);

    @Select("SELECT PWID,username,benchPress,pullHard,deepSquat,total from user ORDER BY deepSquat DESC LIMIT #{number}")
    List<User> selectTOPC(int number);

}
