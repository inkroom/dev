package cn.inkroom.base.review.queue;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/4
 * @Time 10:04
 * @Descorption 优先级队列比较方法
 */
public interface PriorityQueueCompareAble<T> {
    /**
     * 比较方法
     *
     * @param value 需要比较的对象
     * @return 如果为true则this排在队尾，否则this排在队头
     */
    boolean compare(T value);
}
