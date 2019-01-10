package cn.inkroom.base.review.queue;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 10:47
 * @Descorption 单向队列，
 */
public class SingleArrayArrayQueue<T> extends AbstractArrayQueue<T> {


    private int index;

    public SingleArrayArrayQueue(int capacity) {
        super(capacity);
        index = -1;
    }


    @Override
    public T append(T value) {
        super.append(value);
        try {
            values[++index] = value;
//            System.out.println("size = " + size() + "  index = " + index);
            return value;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new ArrayIndexOutOfBoundsException("the queue is full");
        }

    }


    @Override
    public T remove() {
        try {
            T temp = element(0);
            for (int i = 0; i < size() - 1; i++) {
                values[i] = values[i + 1];
            }
            values[index] = null;
            index--;
//            System.out.println("容量" + size());
            return temp;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public T front() {
        return element(0);
    }

    @Override
    public T rear() {
        return element(index);
    }

    @Override
    public int clear() {
        int size = size();
        while (!empty())
            remove();
        return size;
    }


    @Override
    public int size() {
        return index + 1;
    }


}
