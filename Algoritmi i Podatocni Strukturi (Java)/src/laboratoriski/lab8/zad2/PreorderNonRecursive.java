package laboratoriski.lab8.zad2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

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

class BTree<E> {

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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    public void PreorderNonRecursive() {
        // vasiot kod ovde
        ArrayStack<BNode<E>> list = new ArrayStack<BNode<E>>(100);
        BNode<E> p = root;

        while(true){
            while(p!=null){
                list.push(p);
                System.out.print(p.info + " ");
                p=p.left;
            }
            if(list.isEmpty()){
                break;
            }

            p = list.peek();
            list.pop();
            p = p.right;

        }
    }

}

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    @SuppressWarnings("unchecked")
    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    @SuppressWarnings("unchecked")
    public E peek ();
    // Go vrakja elementot na vrvot od stekot.


    // Metodi za transformacija:

    @SuppressWarnings("unchecked")
    public void clear ();
    // Go prazni stekot.

    @SuppressWarnings("unchecked")
    public void push (E x);
    // Go dodava x na vrvot na stekot.
    @SuppressWarnings("unchecked")
    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    @SuppressWarnings("unchecked")
    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }

    @SuppressWarnings("unchecked")
    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }

    @SuppressWarnings("unchecked")
    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }

    @SuppressWarnings("unchecked")
    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }

    @SuppressWarnings("unchecked")
    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PreorderNonRecursive {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode[] nodes = new BNode[N];
        BTree<String> tree = new BTree<String>();

        for (i=0;i<N;i++)
            nodes[i] = new BNode<>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = st.nextToken();
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

        br.close();

        tree.PreorderNonRecursive();

    }
}

