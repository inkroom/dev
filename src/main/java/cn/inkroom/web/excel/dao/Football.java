package cn.inkroom.web.excel.dao;

import cn.inkroom.web.excel.bean.Coach;
import cn.inkroom.web.excel.bean.Player;
import cn.inkroom.web.excel.bean.Team;
import org.apache.ibatis.annotations.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/13
 * @Time 15:46
 * @Descorption
 */
@Mapper
public interface Football {

    @Insert("insert into ACCOUNT (A_NAME,A_USERNAME,A_PASSWORD,A_TYPE,A_STATUS) values(#{player.name},#{player.username},#{player.password},1,1)")
    @Options(useGeneratedKeys = true, keyProperty = "player.id", keyColumn = "A_ID")
    int insertAccount(@Param("player") Player player);

    @Insert("insert into ACCOUNT (A_NAME,A_USERNAME,A_PASSWORD,A_TYPE,A_STATUS) values(#{player.name},#{player.username},#{player.password},2,1)")
    @Options(useGeneratedKeys = true, keyProperty = "player.id", keyColumn = "A_ID")
    int insertAccountC(@Param("player") Coach coach);

    @Insert("insert into ACCOUNT (A_NAME,A_USERNAME,A_PASSWORD,A_TYPE,A_STATUS) values(#{player.name},#{player.username},#{player.password},4,1)")
    @Options(useGeneratedKeys = true, keyProperty = "player.id", keyColumn = "A_ID")
    int insertAccountT(@Param("player") Team coach);

    @Insert("insert into TEAM (A_ID) values(#{t.id})")
    int insertTeam(@Param("t") Team team);

    @Insert("insert into PLAYER (A_ID) values(#{p.id})")
    int insertPlayer(@Param("p") Player player);

    @Insert("insert into COACH (A_ID) values(#{c.id})")
    int insertCoach(@Param("c") Coach player);

    @Select("select A_ID from ACCOUNT where A_USERNAME=#{username} and A_TYPE=#{type} limit 1")
    Long selectUsername(@Param("username") String username, @Param("type") int type);

    @Select("select count(*) FROM  ACCOUNT WHERE A_NAME=#{coach.name} and A_USERNAME=#{coach.username}")
    int selectCoach(@Param("coach") Coach coach);
}
