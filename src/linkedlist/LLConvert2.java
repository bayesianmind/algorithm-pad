package linkedlist;

public class LLConvert2 {
    private static class IterState {
        TNode first = null;
        TNode prev = null;
    }

    // In-order traversal storing first and prev states
    public static TNode convertBTtoCDLL2(TNode root) {
        IterState state = new IterState();

        if (root == null) { return null; }
        recurse(root, state);

        // make the whole list circular
        join(state.prev, state.first);

        return state.first;
    }

    private static void recurse(TNode root, IterState state) {
        if (root == null) { return; }
        recurse(root.left, state);
        visit(root, state);
        recurse(root.right, state);
    }

    private static void visit(TNode root, IterState state) {
        if (state.first == null) {
            state.first = root;
            state.prev = root;
        }
        else {
            join(state.prev, root);
            state.prev = root;
        }
    }

    // create two way connection between nodes
    private static void join(TNode a, TNode b) {
        a.right = b;
        b.left = a;
    }
}
