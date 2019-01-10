package cn.inkroom.base.review.queue;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 11:28
 * @Descorption 循环队列
 */
public class CycleArrayArrayQueue<T> extends AbstractArrayQueue<T> {

    private int rear;//队尾
    private int front;//队头
    private int count;


    public CycleArrayArrayQueue(int capacity) {
        super(capacity);
        front = -1;
        rear = -1;
        count = 0;
    }

    /**
     * 入队，如果队满，则入队失败
     *
     * @param value
     * @return
     */
    @Override
    public T append(T value) {
//        super.append(value);
        try {
            if (!full()) {
                int next = (rear + 1) % values.length;
                values[next] = value;
                rear = next;
                count++;
                return value;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T remove() {
        int next = (front + 1) % values.length;
        T temp = element(next);
        values[next] = null;
        count--;
        front = next;
        return temp;
    }

    @Override
    public T front() {
        return element((front + 1) % values.length);
    }

    @Override
    public T rear() {
        return element((rear) % values.length);
    }

    @Override
    public int size() {
        return count;
    }

}
