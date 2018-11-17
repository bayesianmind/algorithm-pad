package linkedlist;

/**
 * Created by Ben on 5/11/2016.
 */
public class TNode {
    TNode left;
    TNode right;
    int value;

    public static TNode of(int value) {
        TNode ret = new TNode();
        ret.value = value;
        return ret;
    }

    @Override
    public String toString() {
        return "linkedlist.TNode{"+value+"}";
    }
}
