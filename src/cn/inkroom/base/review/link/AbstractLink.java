package cn.inkroom.base.review.link;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/5
 * @Time 16:25
 * @Descorption
 */
public abstract class AbstractLink<T> implements Link<T> {

    protected int size;
    private AbstractLink<T> next;
    private T data;

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    protected AbstractLink<T> getNext() {
        return next;
    }

    protected void setNext(AbstractLink<T> next) {
        this.next = next;
    }

    @Override
    public int clear() {
        int temp = size();
        setNext(null);
        this.size = 0;
        return temp;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }
}
