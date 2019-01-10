package cn.inkroom.base.review.link;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/5
 * @Time 16:11
 * @Descorption 循环链表
 */
// TODO: 2017/12/18 不可用
public class CycleLink<T> extends AbstractLink<T> {

    private CycleLink<T> prev = null;


    public CycleLink() {

    }

    public CycleLink(T data) {
        setData(data);
    }

    public CycleLink(T data, CycleLink<T> prev) {
        setData(data);
        this.prev = prev;
    }

    public CycleLink(T data, CycleLink<T> next, CycleLink<T> prev) {
        setData(data);
        setNext(next);
        this.prev = prev;
    }

    @Override
    public T append(T value) {
        CycleLink<T> last = this.prev;
        if (last != null) {
            CycleLink<T> temp = new CycleLink<>(value);
            temp.prev = last;
            last.setNext(temp);
            this.prev = temp;
        } else {//此时链表为空
            CycleLink<T> temp = new CycleLink<>(value);
            temp.prev = this;
            setNext(temp);
            this.prev = temp;
        }
        size++;
        return value;
    }

    @Override
    public T first() {
        return getNext() == null ? null : getNext().getData();
    }

    @Override
    public T insert(T value) {
        CycleLink<T> temp = new CycleLink<>(value);
        temp.prev = this;
        temp.setNext(this.getNext());
        if (this.getNext() != null) {
            ((CycleLink) this.getNext()).prev = temp;
        }
        this.setNext(temp);
        size++;
        if (this.prev == null) {//第一次添加
            this.prev = temp;
            temp.setNext(this);
        }
        return value;
    }

    @Override
    public void deleteFirst() {

        CycleLink<T> temp = (CycleLink<T>) this.getNext();
        if (temp != null) {
            size--;
            temp.prev = this;
            this.setNext(temp.getNext());
        }
    }

    @Override
    public void deleteLast() {
        //获取最后一个节点
        CycleLink<T> last = this.prev;
        if (last != null) {
            size--;
            if (last.prev == last.getNext()) {//只有一个节点
                this.prev = null;
                this.setNext(null);
            } else {
                this.prev = (CycleLink<T>) last.getNext();
//                ((CycleLink<T>) last.getNext()).prev = this;
//                last.prev.setNext(this);
                ((CycleLink<T>) last.getNext()).setNext(this);
            }

        }

    }

    @Override
    public T last() {
        return this.prev == null ? null : this.prev.getData();
    }
}
