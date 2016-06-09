
public class LLConvert {
    public static TNode convertBTtoCDLL(TNode root) {
        if (root == null) { return null; }
        if (root.left == null && root.right == null) {
            root.left = root;
            root.right = root;
            return root;
        }
        if (root.right != null) {
            root.right = convertBTtoCDLL(root.right);
        }
        if (root.left != null) {
            root.left = convertBTtoCDLL(root.left);
        }
        return join(root, root.left, root.right);
    }

    private static TNode join(final TNode middle, final TNode n1, final TNode n2) {
        TNode listBegin, listEnd;
        listBegin = listEnd = middle;

        if (n1 != null) {
            listBegin = n1;
            n1.left.right = middle;
            middle.left = n1.left;
        }

        if (n2 != null) {
            listEnd = n2.left;
            n2.left = middle;
            middle.right = n2;
        }

        listBegin.left = listEnd;
        listEnd.right = listBegin;

        return listBegin;
    }


}
