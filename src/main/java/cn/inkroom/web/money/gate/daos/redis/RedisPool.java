package cn.inkroom.web.money.gate.daos.redis;

public interface RedisPool {

    /**
     * 设定该Key持有指定的字符串Value，如果该Key已经存在，则覆盖其原有值。
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);


    String set(String key, int seconds, String value);


    /**
     * 获取指定Key的Value。
     * 如果与该Key关联的Value不是string类型，Redis将返回错误信息，因为GET命令只能用于获取string Value。
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 判断该键是否存在，存在返回1，否则返回0。
     * @param key
     * @return
     */
    Boolean exists(String key);

    /**
     * 设置过期时间为，单位为秒。
     * @param key
     * @param seconds
     * @return
     */
    Long expire(String key, int seconds);

    /**
     * 查看指定Key的剩余存活时间(秒数)，0表示已经过期，-1表示永不过期
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 将指定Key的Value原子性的递增1。
     * 如果该Key不存在，其初始值为0，在incr之后其值为1。
     * 如果Value的值不能转换为整型值，如Hello，该操作将执行失败并返回相应的错误信息。
     * 注意：该操作的取值范围是64位有符号整型。
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     * 存储的关键值的散列设置字段。如果键不存在，新的key由哈希创建。
     * 如果字段已经存在于哈希值那么将被覆盖。
     * @param key
     * @param field
     * @param value
     * @return 1:如果字段是哈希值和一个新字段被设置。
     * 		   0 如果字段已经存在于哈希并且值被更新。
     */
    Long hset(String key, String field, String value);

    /**
     * Redis HGET命令用于获取与字段中存储的键哈希相关联的值。
     * @param key
     * @param field
     * @return 回复字符串值关联字段，或nil当字段时不存在哈希或键不存在值。
     */
    String hget(String key, String field);

    /**
     * 命令用于从存储在键散列删除指定的字段。
     * 如果没有这个哈希中存在指定的字段将被忽略。如果键不存在，它将被视为一个空的哈希与此命令将返回0。
     * @param key
     * @param field
     * @return 回复整数，从散列中删除的字段的数量，不包括指定的但不是现有字段。
     */
    Long hdel(String key, String... field);

    /**
     * 删除指定的字段
     * @param key
     * @return
     */
    Boolean del(String key);


    void close();
}
