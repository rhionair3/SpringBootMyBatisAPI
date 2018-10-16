package com.rwds.erecruitment.user.mappers;

import com.rwds.erecruitment.user.entities.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user ( namadepan, namabelakang, surel, katasandi, usia, telepon) VALUES ( #{user.namadepan}, #{user.namabelakang},#{user.surel},#{user.katasandi}, #{user.usia},#{user.telepon})")
    Integer insertPengguna(@Param("user") User user) throws Exception;

    @Select("select * from user where surel = #{surel}")
    User findBySurel(@Param("surel") String surel);

    @Select("select * from user where surel = #{surel} AND id!=#{id}")
    User findBySurelNotPengguna(@Param("surel") String surel, @Param("id") Integer id);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where surel = #{surel} AND katasandi = #{katasandi}")
    User login(@Param("surel") String surel, @Param("katasandi") String katasandi);

    @Select("select * from user")
    List<User> getPenggunas();

    @Update("UPDATE USER SET namadepan = #{user.namadepan}, namabelakang = #{user.namabelakang}, email = #{user.email}, telepon = #{user.telepon},usia = #{user.usia} WHERE id = #{user.id}")
    Integer updatePengguna(@Param("user") User user) throws Exception;

    @Delete("DELETE from user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);
}