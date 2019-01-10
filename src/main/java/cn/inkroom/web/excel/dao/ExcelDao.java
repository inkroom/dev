package cn.inkroom.web.excel.dao;


import cn.inkroom.web.excel.bean.Question;
import org.apache.ibatis.annotations.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/24
 * @Time 10:27
 * @Descorption
 */
@Mapper
public interface ExcelDao {

    @Insert("insert into stu_question_bank (question_title,answer,difficult,type,subject,grade,term," +
            "analysis,choice) values(#{map.title},#{map.answer},#{map.difficult},#{map.type},#{map.subject}," +
            "#{map.grade},#{map.term},#{map.analysis},#{map.choice})")
    @Options(useGeneratedKeys = true, keyColumn = "question_id", keyProperty = "map.id")
    int insertQuestion(@Param("map") Question question);

    @Update("update stu_question_bank set resource_path= #{question.resource} where question_id = #{question.id}")
    int updateResource(@Param("question") Question question);

}
