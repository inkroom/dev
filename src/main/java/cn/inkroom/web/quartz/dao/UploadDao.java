package cn.inkroom.web.quartz.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/1
 * @Time 20:08
 * @Descorption
 */
public interface UploadDao {
    @Insert("insert into upload (path,owner,create_time) values(#{path},#{owner},now())")
    int insertFile(@Param("path") String path, @Param("owner") long albumId) throws Exception;

    @Select("select path from upload where id=#{id}")
    String getFile(@Param("id") long id);
}
