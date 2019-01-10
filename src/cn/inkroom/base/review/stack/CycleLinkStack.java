package cn.inkroom.base.review.stack;

import cn.inkroom.base.review.link.CycleLink;
import cn.inkroom.base.review.link.Link;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/5
 * @Time 16:55
 * @Descorption 循环链表的栈，不会栈满
 */
public class CycleLinkStack<T> extends SingleLinkStack<T> {

    public CycleLinkStack() {
        link = new CycleLink<>();
    }

    @Override
    public T pop() {
        T temp = super.pop();
        System.out.println("temp " + temp+"   "+link+"  "+link.last());
        return temp;
    }
}
