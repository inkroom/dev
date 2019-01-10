package cn.inkroom.web.socket.typeHandler;

import cn.inkroom.web.socket.enums.UserStatus;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 墨盒 on 14:35.
 */
public class UserStatusHandler extends EnumOrdinalTypeHandler<UserStatus> {
    public UserStatusHandler(Class<UserStatus> type) {
        super(type);
//        System.out.println(this.getClass() + "  UserStatusHandler");
    }

    @Override
    public UserStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        System.out.println(this.getClass() + "  getNullableResult(ResultSet rs, String columnName) " + rs + "    " + columnName);
//        if (rs.wasNull())
//            return null;
        int temp = rs.getInt(columnName);

        return locateEnum(temp);
    }

    private UserStatus locateEnum(int value) {
        UserStatus status[] = UserStatus.values();
        for (UserStatus state :
                status) {
            if (state.ordinal() == value)
                return state;
        }
        return null;
    }

    @Override
    public UserStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        System.out.println(this.getClass() + "  getNullableResult(CallableStatement cs, int columnIndex) ");
//        if (cs.wasNull())
//            return null;

        int temp = cs.getInt(columnIndex);
        return locateEnum(temp);
    }

    @Override
    public UserStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        System.out.println(this.getClass() + "  getNullableResult(ResultSet rs, int columnIndex) ");
//        if (rs.wasNull())
//            return null;
        int temp = rs.getInt(columnIndex);

        return locateEnum(temp);
    }
}
