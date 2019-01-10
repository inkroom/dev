package cn.inkroom.base.review.link;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/5
 * @Time 15:49
 * @Descorption
 */
public class LinkEntry {
    public static void main(String[] args) {
        Link<Integer> singleLink = new CycleLink<>();
        for (int i = 0; i < 10; i++) {
            singleLink.insert(i);
        }
//        singleLink.display();
        System.out.println();
        System.out.println("size = "+singleLink.size());
//        System.out.println(singleLink.lastLink().getData());
        while (!singleLink.empty()){
            System.out.println("第一个= "+ singleLink.last());
            singleLink.deleteLast();
        }
        System.out.println(singleLink.clear());
        System.out.println(singleLink.size());
    }
}
