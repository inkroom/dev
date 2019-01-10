package cn.inkroom.base.review.link;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/5
 * @Time 14:25
 * @Descorption 单链表，当前节点数据域应该为空
 */
public class SingleLink<T> extends AbstractLink<T> {

    public SingleLink() {

    }

    public SingleLink(T data) {
        this.setData(data);
//        SingleLink<T> next = new SingleLink<>();
//        next.setData(data);
//        this.next = next;
    }

    @Override
    public T append(T value) {
        SingleLink<T> last = lastLink();
        last.setNext(new SingleLink<>(value));
        size++;
        return value;
    }

    public SingleLink(T data, SingleLink<T> next) {
        setData(data);
        setNext(next);
    }

    @Override
    public T first() {
        if (getNext() != null)
            return getNext().getData();
        return null;
    }

    @Override
    public T insert(T value) {
        size++;
        setNext(new SingleLink<T>(value, (SingleLink<T>) getNext()));
        return value;
    }

    @Override
    public void deleteFirst() {
        SingleLink<T> temp = (SingleLink<T>) getNext();
        if (temp != null) {
            setNext(temp.getNext());
            size--;
        }

    }

    @Override
    public void deleteLast() {
        SingleLink<T> temp = this;
        //找到倒数第二个节点
        while (temp.getNext() != null && ((SingleLink) temp.getNext()).getNext() != null) {
            temp = (SingleLink<T>) temp.getNext();
        }
        temp.setNext(null);
    }

    /**
     * 倒数第二个节点
     *
     * @return
     */
//    private SingleLink<T> lastSecondLink() {
//        SingleLink<T> temp = this;
//        //找到倒数第二个节点
//        while (temp.next != null && temp.next.next != null) {
//            temp = temp.next;
//        }
//        return temp;
//    }
    @Override
    public T last() {
        Link<T> last = lastLink();
        return last == null ? null : last.getData();
    }

    protected SingleLink<T> lastLink() {
        SingleLink<T> temp = this;
        while (temp.getNext() != null) {
            temp = (SingleLink<T>) temp.getNext();
        }
        return temp;
    }


}
