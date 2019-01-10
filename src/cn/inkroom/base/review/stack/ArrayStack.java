package cn.inkroom.base.review.stack;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 9:58
 * @Descorption
 */
public class ArrayStack<T> implements Stack<T> {

    private Object values[];
    private int count;//栈内元素数量
    private int index;//栈指针


    public ArrayStack(int capacity) {
        values = new Object[capacity];
        count = 0;
        index = -1;
    }

    @Override
    public T push(T value) {
        if (count >= values.length)
            return null;
        values[index + 1] = value;
        index++;
        count++;
        return value;
    }


    @Override
    public T pop() {
        if (index == -1)
            return null;
        T p = element(index);
        index--;
        count--;
        return p;
    }

    @Override
    public T peek() {
        if (index == -1)
            return null;
        return element(index);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int clear() {
        int size = size();
        while (!empty())
            pop();
        return size;
    }

    @Override
    public boolean full() {
        return index == count - 1;
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @SuppressWarnings("unchecked")
    private T element(int index) {
        return (T) values[index];
    }
}
