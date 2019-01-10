package cn.inkroom.base.review.stack;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 9:56
 * @Descorption 栈
 */
public interface Stack<T> {
    /**
     * 入栈
     *
     * @param value
     * @return
     */
    T push(T value);

    /**
     * 出栈
     *
     * @return
     */
    T pop();

    /**
     * 取栈顶
     *
     * @return
     */
    T peek();

    /**
     * 栈内元素数量
     *
     * @return
     */
    int size();

    /**
     * 清空栈内元素
     * @return 清空的数量
     */
    int clear();

    /**
     * 是否栈满
     *
     * @return
     */
    boolean full();

    /**
     * 是否栈空
     *
     * @return
     */
    boolean empty();
}
