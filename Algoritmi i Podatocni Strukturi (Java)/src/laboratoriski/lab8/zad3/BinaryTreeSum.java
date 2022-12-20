package laboratoriski.lab8.zad3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    @SuppressWarnings("unchecked")
    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    @SuppressWarnings("unchecked")
    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    @SuppressWarnings("unchecked")
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BTree<E extends Comparable<E>> {

    public BNode<E> root;

    @SuppressWarnings("unchecked")
    public BTree() {
        root = null;
    }

    @SuppressWarnings("unchecked")
    public BTree(E info) {
        root = new BNode<E>(info);
    }
    @SuppressWarnings("unchecked")
    public void makeRoot(E elem) {
        root = new BNode<E>(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }
    @SuppressWarnings("unchecked")
    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }
    @SuppressWarnings("unchecked")
    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public BNode<E> find(BNode<E> n,Integer e) {

        BNode<E> found = null;

        if (n == null)
            return null;

        if (n.info.equals(e))
            return n;

        if (n.left != null)
            found = find(n.left,e);

        if (found == null)
            found = find(n.right,e);

        return found;
    }

    public int sumLeft(BNode<Integer> n, int e){
        if(n==null){
            return 0;
        }else{
            if(n.info < e){
                return sumLeft(n.left,e) + sumLeft(n.right,e)+ n.info;
            }
            return sumLeft(n.left, e)+sumLeft(n.right, e);
        }
    }

    public int sumRight(BNode<Integer> n, int e){
        if(n==null){
            return 0;
        }else{
            if(n.info>e){
                return sumRight(n.left, e) + sumRight(n.right, e) + n.info;
            }
            return sumRight(n.left, e) + sumRight(n.right, e);
        }
    }


}

public class BinaryTreeSum {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i=0;i<N;i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        int baranaVrednost=Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod ovde
        BNode<Integer> baranNode = tree.find(tree.root, baranaVrednost);
        System.out.println(tree.sumLeft(baranNode.left, baranaVrednost) + " " + tree.sumRight(baranNode.right, baranaVrednost));

    }
}
