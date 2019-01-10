package cn.inkroom.web.socket.typeHandler;

import cn.inkroom.web.socket.enums.BoardStatus;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 墨盒 on 14:30.
 */
public class BoardStatusHanlder extends EnumOrdinalTypeHandler<BoardStatus> {
    public BoardStatusHanlder(Class<BoardStatus> type) {
        super(type);
    }

    @Override
    public BoardStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        System.out.println(this.getClass() + "  getNullableResult(ResultSet rs, String columnName) " + rs + "    " + columnName);
//        if (rs.wasNull())
//            return null;
        int temp = rs.getInt(columnName);

        return locateEnum(temp);
    }

    private BoardStatus locateEnum(int value) {
        BoardStatus status[] = BoardStatus.values();
        for (BoardStatus state :
                status) {
            if (state.ordinal() == value)
                return state;
        }
        return null;
    }

    @Override
    public BoardStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        System.out.println(this.getClass() + "  getNullableResult(CallableStatement cs, int columnIndex) ");
//        if (cs.wasNull())
//            return null;

        int temp = cs.getInt(columnIndex);
        return locateEnum(temp);
    }

    @Override
    public BoardStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        System.out.println(this.getClass() + "  getNullableResult(ResultSet rs, int columnIndex) ");
//        if (rs.wasNull())
//            return null;
        int temp = rs.getInt(columnIndex);

        return locateEnum(temp);
    }
}
