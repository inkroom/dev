package cn.inkroom.base.review.queue;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/4
 * @Time 10:08
 * @Descorption
 */
public abstract class AbstractArrayQueue<T> implements Queue<T> {
    //扩展容量
    private int extend = 10;

    protected Object values[];

    protected AbstractArrayQueue(int capacity) {
        values = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    protected T element(int index) {
        return (T) values[index];
    }

    @Override
    public T append(T value) {
        if (full()) {
            extend(values.length + getExtend());
        }
        return value;
    }

    @Override
    public int clear() {
        int size = size();
        while (!empty())
            remove();
        return size;
    }

    @Override
    public boolean full() {
        return size() == values.length;
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    protected void extend(int capacity) {
        Object temps[] = new Object[capacity];
        System.arraycopy(values, 0, temps, 0, size());
//        for (int i = 0; i < size(); i++) {
//            temps[i]=values[i];
//        }
        values = temps;
    }

    public int getExtend() {
        return extend;
    }

    public void setExtend(int extend) {
        this.extend = extend;
    }
}
