package cn.inkroom.base.review.queue;

import java.util.Random;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 11:02
 * @Descorption
 */
public class QueueEntry {
    public static void main(String[] args) {
        int capacity = 10;
        Queue<Integer> queue = new CycleArrayArrayQueue<>(capacity);
        Random random = new Random();
        for (int i = 0; i < capacity; i++) {
            int temp = random.nextInt();
            System.out.println("入队= " + temp);
            queue.append(temp);
        }
//        System.out.println("入队玩= " + queue.size());
//        System.out.println("队头= " + queue.front());
//        System.out.println("队尾= " + queue.rear());
//        queue.out();
        System.out.println();
        while (!queue.empty()) {
//            System.out.println("队头= " + queue.front());
//            System.out.println("队尾= " + queue.rear());
            System.out.println("出队="+queue.remove());
//            System.out.println("size = " + queue.size() + "  队头= " + queue.front());
//            System.out.println("size = " + queue.size() + "  队尾= " + queue.rear());
        }
//        queue.append(12);
//        queue.append(125);
//        System.out.println();
//        System.out.println(queue.front());
//        System.out.println(queue.rear());
//        System.out.println("清空队 = " + queue.clear());
//        System.out.println("个数= " + queue.size());
    }
}
