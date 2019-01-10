package cn.inkroom.base.review.use;

import cn.inkroom.base.review.stack.ArrayStack;
import cn.inkroom.base.review.stack.Stack;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/4
 * @Time 15:52
 * @Descorption 中缀表达式转换成后缀表达式
 */
public class ConvertRPN {
    public static void main(String[] args) {

//        System.out.println(((int) '+') + "   " + ((int) '-') + "   " + ((int) '/') + "   " + ((int) '*'));
//
//        String op = "A+B-C+D";
//        String op = "1+2-3+4";
//        String op = "A+B*C+D";
//        String op = "1+2*3+4";
//        String op = "A+(B-C)+D";
//        String op = "A+(B-C)*D";
//        String op = "A+(B-C)*D";
        String op = "1+(5-3)*2";
        StringBuilder builder = new StringBuilder();
        //存放运算符
        Stack<Character> stack = new ArrayStack<>(3);
        for (int i = 0; i < op.length(); i++) {
            Character c = op.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.empty()) {
                    Character opc = stack.peek();
                    if (opc != '(') {
                        builder.append(stack.pop());
                    } else {
                        stack.pop();
                        break;
                    }
                }
            } else if (c == '+' || c == '-' || c == '/' || c == '*') {
                if (stack.empty())
                    stack.push(c);
                else {//栈内不为空，此时需要处理栈内所有运算符
                    while (!stack.empty()) {
                        Character opc = stack.peek();
                        if (opc == '(') {
                            break;
                        }
                        if (priority(c, opc) <= 0) {//栈内的优先级更高(1)，或相等(0)
                            builder.append(stack.pop());
                        } else {//当前的运算符优先级更高（-1）
                            break;
                        }
                    }
                    stack.push(c);
                }
            } else {
                builder.append(c);
            }
        }
        while (!stack.empty())
            builder.append(stack.pop());
        System.out.println(builder.toString());
        System.out.println("计算结果= " + calculate(builder.toString()));
    }

    public static int calculate(String RPN) {
        Stack<Integer> numbers = new ArrayStack<>(4);
        for (int i = 0; i < RPN.length(); i++) {
            Character c = RPN.charAt(i);
            if (c == '+' || c == '-' || c == '/' || c == '*') {
                int first = numbers.pop();
                int second = numbers.pop();
                if (c == '+') {
                    numbers.push(second + first);
                } else if (c == '-') {
                    numbers.push(second - first);
                } else if (c == '*') {
                    numbers.push(second * first);
                } else {
                    numbers.push(second / first);
                }
            } else {
                numbers.push(Integer.parseInt(RPN.substring(i, i + 1)));
            }
        }
        return numbers.pop();
    }

    /**
     * @param a
     * @param b
     * @return 1优先级相同，-1b的优先级更高，1a的优先级更高
     */
    public static int priority(char a, char b) {
        if (a == '+' || a == '-') {//优先级相同
            if (b == '+' || b == '-')
                return 0;
            else if (b == '/' || b == '*')
                return -1;
        } else if (a == '/' || a == '*') {
            if (b == '+' || b == '-')
                return 1;
            else if (b == '/' || b == '*')
                return 0;
        }
        return -1;
    }
}
