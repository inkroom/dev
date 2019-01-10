package cn.inkroom.base.review.sort;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/29
 * @Time 17:28
 * @Descorption
 */
public class SortEntry {
    public static void main(String[] args) {
//        int values[] = createValues(true);
////        pupSoft(values);
////        out(values);
////        values = createValues(true);
////        selectSort(values);
////        out(values);
//        values = createValues(false);
//        insertSort(values, 4);
//        out(values);


        int a[] = {4, 1, 7, 6, 5, 3, 8, 2};
        MergeSort sort = new MergeSort();
        sort.sort(a);
        out(a);

    }

    public static int[] createValues(boolean flag) {
        if (flag)
            return new int[]{1, 423, 3, 534, 213, 33, 64};
        else
            return new int[]{1, 22, 123, 444, 12, 125, 445, 74};
    }


    /**
     * 冒泡排序
     *
     * @param values
     */
    public static void pupSoft(int values[]) {
        int count = 0;
        int exchange = 0;
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i; j < values.length - 1; j++) {
                count++;
                System.out.println("比较下标= " + j + "  " + (j + 1));
                if (values[j] > values[j + 1]) {
                    exchange++;
                    int temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序比较次数=" + count + "  交换顺序=" + exchange);
    }

    /**
     * 选择排序
     *
     * @param values
     */
    public static void selectSort(int values[]) {
        int count = 0;
        int exchange = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                System.out.println("比较下标= " + i + "  " + j);
                count++;
                if (values[i] > values[j]) {
                    exchange++;
                    int temp = values[i];
                    values[i] = values[j];
                    values[j] = temp;
                }
            }
        }
        System.out.println("选择排序比较次数=" + count + "  交换顺序=" + exchange);
    }

    /**
     * 插入排序
     *
     * @param values
     * @param flag   初始标记
     */
    public static void insertSort(int[] values, int flag) {
        int count = 0;
        int exchange = 0;
        for (int i = flag; i < values.length; i++) {
            int temp = values[i];
            int j = i - 1;
            while (j > 0 && values[j - 1] > temp) {
                values[j] = values[j - 1];
                j--;
            }
            values[j] = temp;
//            for (int j = i - 1; j >= 0; j--) {
//                count++;
//                exchange++;
//                if (values[j] > temp) {
//                    values[j + 1] = values[j];
//                } else {
//                    values[j + 1] = temp;
//                    break;
//                }
//            }
        }
        System.out.println("插入排序比较次数=" + count + "  交换顺序=" + exchange);

    }


    /**
     * 输出
     *
     * @param values
     */
    public static void out(int values[]) {
        for (int i = 0; i < values.length; i++) {
            System.out.printf(values[i] + "  ");
        }
        System.out.println();
    }

    protected static class MergeSort {

        private int layer = 0;

        public void sort(int[] s) {
            int[] buffer = new int[s.length];
            mergeHelper(s, buffer, 0, s.length - 1);
            layer = 0;
        }

        private void mergeHelper(int[] a, int buffer[], int low, int high) {
            if (low < high) {

                layer++;
//                System.out.println("这是第" + (++layer) + "层");


                int middle = (low + high) / 2;

                System.out.println("low = " + low + "  middle=" + middle + "  high=" + high);


                mergeHelper(a, buffer, low, middle);
                mergeHelper(a, buffer, middle + 1, high);
                merge(a, buffer, low, middle, high);

            }
        }


        private void merge(int[] a, int[] copy, int low, int middle, int high) {
            if (low == 4&&high==7) {
                low = low;
            }


            int i1 = low, i2 = middle + 1;


            System.out.println("i1=" + i1 + "  i2=" + i2 + "  low=" + low + "  middle=" + middle + "  high=" + high);

            for (int i = low; i <= high; i++) {
                if (i1 > middle)
                    copy[i] = a[i2++];
                else if (i2 > high) {
                    copy[i] = a[i1++];
                } else if (a[i1] < a[i2]) {
                    copy[i] = a[i1++];

                } else
                    copy[i] = a[i2++];
            }

            for (int i = low; i <= high; i++) {
                a[i] = copy[i];
            }

        }


    }


}
