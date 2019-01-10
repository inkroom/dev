package cn.inkroom.base.review.tree;

/**
 * @author 墨盒
 */
public class BinaryTree {


    private String value;
    private BinaryTree left;
    private BinaryTree right;


    public BinaryTree(String value) {
        this.value = value;
    }

    public BinaryTree(String value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTree() {

//        BinaryTree left = new BinaryTree("A");
//        BinaryTree right = new BinaryTree("C");
//
//
//        BinaryTree temp = new BinaryTree("B");
//        temp.setLeft(left);
//        temp.setRight(right);
//
//        this.setLeft(temp);
//
//        left = new BinaryTree("E");
//        right = new BinaryTree("G");
//
//        temp = new BinaryTree("F");
//        temp.setLeft(left);
//        temp.setRight(right);
//
//        this.setRight(temp);
//
//        this.setValue("D");


    }

    public String getValue() {
        return value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }



    public static void layer(BinaryTree tree) {
//        if (tree != null) {
//            BinaryTree temp = tree;
//
//            BinaryTree left = tree;
//            BinaryTree right = null;
////            System.out.println(left.getValue());
//
//            while (temp != null) {
//                System.out.println(temp.getValue());
//
//                left = tree.getLeft();
//                right = tree.getRight();
//                System.out.println(left.getValue());
//                System.out.println(right.getValue());
//
//                temp
//            }
//
//        }
    }


    public String InorderString() {
        StringBuilder builder = new StringBuilder();
        preInorder(this, builder);
        return builder.toString();
    }

    private void preInorder(BinaryTree tree, StringBuilder builder) {

        if (tree != null) {


            builder.append(tree.getValue()).append(" ");

            preInorder(tree.getLeft(), builder);
            preInorder(tree.getRight(), builder);


        }

    }


}
