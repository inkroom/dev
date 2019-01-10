package cn.inkroom.base.review.queue;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 10:44
 * @Descorption 队列
 */
public interface Queue<T> {
    /**
     * 入队
     *
     * @param value
     * @return
     */
    T append(T value);

    /**
     * 出队
     *
     * @return
     */
    T remove();

    /**
     * 队头
     *
     * @return
     */
    T front();

    /**
     * 队尾
     *
     * @return
     */
    T rear();

    /**
     * 清空队内元素
     * @return 清空的数量
     */
    int clear();

    /**
     * 队列内元素数量
     *
     * @return
     */
    int size();

    /**
     * 是否队满
     *
     * @return
     */
    boolean full();

    /**
     * 是否队空
     *
     * @return
     */
    boolean empty();


}
