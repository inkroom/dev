package cn.inkroom.base.review.queue;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/4
 * @Time 9:57
 * @Descorption 优先级队列，默认小的先出队
 */
public class PriorityArrayQueue<T> extends AbstractArrayQueue<T> {
    //队头
    private int front;

    //排序方式，默认小的先出队
    private boolean isAsc;

    public PriorityArrayQueue(int capacity) {
        this(capacity, true);
    }

    /**
     * @param capacity 容量
     * @param order    排序方式，true则大的优先级更高
     */
    public PriorityArrayQueue(int capacity, boolean order) {
        super(capacity);
        isAsc = order;
        front = -1;
    }

    @Override
    public T append(T value) {
        super.append(value);
        if (size() > 0) {
            for (int i = front; i >= 0; i--) {
                if (compare(element(i), value)) {//置换
                    values[i + 1] = value;
                    front++;
//                    T temp = element(i);
//                    values[i] = value;
//                    values[++front] = temp;
                    return value;
                } else {//前移
                    values[i + 1] = element(i);
                }
                if (i == 0) {
                    values[0] = value;
                    front++;
                }
            }
        } else {
            values[++front] = value;
            System.out.println("从0开始=" + value);
            return value;
        }

        return null;
    }

    @Override
    public T remove() {
        T temp = element(front);
        values[front--] = null;
        return temp;
    }

    @Override
    public T front() {
        try {
            return element(front);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public T rear() {
        try {
            return element(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int size() {
        return front + 1;
    }

    /**
     * 比较
     *
     * @param already 队列里的元素
     * @param stay    待入队的元素
     * @return true 则stay在already之前，小的先出队
     */
    private boolean compare(T already, T stay) {
        if (already instanceof Comparable) {
            return ((Comparable) already).compareTo(stay) > 0 ^ isAsc;
        }
//        if (already instanceof Integer) {
//            return ((Integer) already) > ((Integer) stay) && isAsc;
//        }
//        if (already instanceof Long) {
//            return ((Long) already) > ((Long) stay) && isAsc;
//        }
//        if (already instanceof Float) {
//            return ((Float) already) > ((Float) stay) && isAsc;
//        }
//        if (already instanceof Double) {
//            return ((Double) already) > ((Double) stay) && isAsc;
//        }
        return false;
    }
}
