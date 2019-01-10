package cn.inkroom.base.review.stack;


import cn.inkroom.base.review.link.Link;
import cn.inkroom.base.review.link.SingleLink;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/5
 * @Time 15:46
 * @Descorption 单链表实现的栈，不会栈满
 */
public class SingleLinkStack<T> implements Stack<T> {
    protected Link<T> link;

    public SingleLinkStack() {
        link = new SingleLink<>();
    }

    @Override
    public T push(T value) {
        return link.insert(value);
    }

    @Override
    public T pop() {
        T value = link.first();
        link.deleteFirst();
        return value;
    }

    @Override
    public T peek() {
        return link.last();
    }

    @Override
    public int size() {
        return link.size();
    }

    @Override
    public int clear() {
        return link.clear();
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return link.empty();
    }
}
