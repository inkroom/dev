package cn.inkroom.base.review.stack;

import java.io.File;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 10:11
 * @Descorption
 */
public class StackEntry {
    public static void main(String[] args) {

        Stack<Character> stack = new CycleLinkStack<>();
//        System.out.println("入栈结果="+stack.push(new File("D:\\")));
//        System.out.println("入栈结果="+stack.push(new File("E:\\")));
//        System.out.println("栈内数量="+stack.size());
//        System.out.println("取栈顶="+stack.peek());
//        System.out.println("出栈="+stack.pop());
//        System.out.println("栈内数量="+stack.size());
//        System.out.println("出栈="+stack.pop());

        String word = "Entry";
        for (int i = 0; i < word.length(); i++) {
            stack.push(word.charAt(i));
        }
        while (stack.peek() != null) {
            System.out.printf(stack.pop() + "");
        }
        System.out.println();
        /*
        40 (
        41 )
        91 [
        93 ]
        123 {
        125 }
         */
        String value = "a(232)90[{]sd(的)ds";
        boolean result = true;
        for (int i = 0; i < value.length(); i++) {
            Character c = value.charAt(i);
//            System.out.println((int) c + "   " + c + "   " + i);
            if (c == 40 || c == 91 || c == 123) {
                stack.push(c);
//                System.out.println("入栈 = " + c + "  " + i);
            } else if (c == 41 || c == 93 || c == 125) {
                Character temp = stack.pop();
                if (!((c == 41 && temp == 40) || (c == 93 && temp == 91) || (c == 125 && temp == 123))) {
                    System.out.println("不匹配" + c + "  " + temp + "  " + i);
                    result = false;
                    break;
                }
//                    if (c == 41 && temp != 40) {
//                        System.out.println("不匹配" + c + "  " + temp + "  " + i);
//                        break;
//                    } else if (temp != 40) {
//                        if (temp - 2 != c) {
//                            System.out.println("不匹配" + c + "  " + temp + "  " + i);
//                            break;
//                        }
//                    }

            }
        }
        if (result)
            System.out.println("匹配成功");

    }
}
