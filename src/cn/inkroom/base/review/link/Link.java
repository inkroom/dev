package cn.inkroom.base.review.link;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/5
 * @Time 16:06
 * @Descorption 链表
 */
public interface Link<T> {
    /**
     * 从尾部追加数据
     *
     * @param value
     * @return
     */
    T append(T value);

    /**
     * 第一个数据
     *
     * @return
     */
    T first();

    /**
     * 从头部追加
     *
     * @param value
     * @return
     */
    T insert(T value);

    /**
     * 删除第一个节点
     */
    void deleteFirst();

    /**
     * 删除最后一个节点
     */
    void deleteLast();

    /**
     * 获取数据
     *
     * @return
     */
    T getData();

    /**
     * 设置数据
     *
     * @param data
     */
    void setData(T data);

    /**
     * 最后一个节点
     *
     * @return
     */
    T last();

    /**
     * 清空
     */
    int clear();

    /**
     * 个数
     */
    int size();

    /**
     * 链表是否为空
     *
     * @return
     */
    boolean empty();
}
