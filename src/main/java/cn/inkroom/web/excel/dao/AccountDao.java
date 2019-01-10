package cn.inkroom.web.excel.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 15:47
 * @Descorption
 */
@Mapper
public interface AccountDao {

    @Insert("insert into stu_file (stu_id,stu_name,birthday,sex,stu_password," +
            "attended_school,current_school,stu_class,create_time) " +
            "values(#{stuId},#{name},#{birthday},#{sex}," +
            "'e10adc3949ba59abbe56e057f20f883e'," +
            "#{attend},#{current},#{class},#{create,typeHandler=cn.inkroom.web.excel.DateHandler})")
    int insert(@Param("stuId") String stuId, @Param("name") String name, @Param("birthday") Date birthday,
               @Param("sex") String sex, @Param("attend") String attend, @Param("current") String current,
               @Param("class") int group, @Param("create") Date create);

    @Insert("insert into stu_file (stu_id,stu_name,sex,stu_password," +
            "attended_school,current_school,stu_class,create_time) " +
            "values(#{stuId},#{name},#{sex}," +
            "'09512df7d5d253b5b4dd0a20c5a47bfb'," +
            "#{attend},#{current},#{class},#{create,typeHandler=cn.inkroom.web.excel.DateHandler})")
    int insertSec(@Param("stuId") String stuId, @Param("name") String name,
                  @Param("sex") String sex, @Param("attend") String attend, @Param("current") String current,
                  @Param("class") int group, @Param("create") Date create);

}
