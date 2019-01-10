package cn.inkroom.web.money.gate.handler.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/10/23
 * @Time 18:19
 * @Descorption
 */
public class DateHandler extends BaseTypeHandler<Date> {
    Log log = LogFactory.getLog(getClass());

    @Override
    public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
//        super.setParameter(ps, i, parameter, jdbcType);
        log.info("插入的时间   ");

        ps.setTimestamp(i, new Timestamp(parameter.getTime()), Calendar.getInstance());
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
//        log.info("setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType)  " + date);
        log.info("插入使得时间   =   " + date + "    " + jdbcType);
        preparedStatement.setString(i, date.toString());
//        preparedStatement.setDate(i,date);
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
//        log.info("getNullableResult(ResultSet resultSet, String s) " + s);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-8"));
//        String temp = resultSet.getTimestamp(s,Calendar.getInstance()).toString();
//        log.info("时间 = " + resultSet.getTimestamp(s).toString() + "    "
//                + resultSet.getTimestamp(s).toLocalDateTime().toString() + "    "
////                + resultSet.getTimestamp(s, Calendar.getInstance()).toGMTString()
//        +"  "+temp);
        return resultSet.getTimestamp(s, Calendar.getInstance());
//        try {
//            return dateFormat.parse(s);
//        } catch (ParseException e) {
////            e.printStackTrace();
//            return null;
//        }
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
//        log.info("getNullableResult(ResultSet resultSet, int i)  " + resultSet.getDate(i).toString());
        return resultSet.getTimestamp(i, Calendar.getInstance());
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
//        log.info("getNullableResult(CallableStatement callableStatement, int i)  " + i);
//        log.info("getNullableResult(CallableStatement callableStatement, int i)  = " + callableStatement.getTimestamp(i));
        return callableStatement.getTime(i, Calendar.getInstance());
    }
}
