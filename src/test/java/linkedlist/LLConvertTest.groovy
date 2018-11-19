package linkedlist

import static LLConvert.*
import static LLConvert2.*
import static TNode.*

/**
 * Created by Ben on 5/11/2016.
 */
class LLConvertTest extends groovy.util.GroovyTestCase {
    void testBST() {
        def root = givenBST();

        def ret = convertBTtoCDLL(root);
        def (seen1, arr1) = CDLLtoArr(ret, false)
        def (seen2, arr2) = CDLLtoArr(ret.left, true)
        assertEquals(seen1,seen2);
        println "Arr1" + (arr1)
        println "Arr2:" + arr2;

    }

    void testBST2() {
        def root = givenBST();

        def ret = convertBTtoCDLL2(root);
        def (seen1, arr1) = CDLLtoArr(ret, false)
        def (seen2, arr2) = CDLLtoArr(ret.left, true)
        assertEquals(seen1,seen2);
        println "Arr1" + (arr1)
        println "Arr2:" + arr2;

    }

    static def givenBST() {
        TNode root = of(4);
        root.left = of(2);
        root.right = of(8);
        root.right.left = of(7);
        root.left.left = of(1);
        root.left.right = of(3);
        return root;
    }

    static def CDLLtoArr(TNode root, boolean backwards) {
        def seen = new HashSet<TNode>();
        def arr = [];
        while (! seen.contains(root)) {
            arr += root.value;
            seen.add(root);
            if (backwards) {
                root = root.left;
            }
            else {
                root = root.right;
            }
        }
        return [seen, arr];
    }
}
