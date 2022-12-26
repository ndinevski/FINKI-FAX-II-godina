package laboratoriski.lab9.zad2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class BNode<E extends Comparable<E>>{
    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}
class BinarySearchTree<E extends Comparable<E>>{
    private BNode<E> root;
    @SuppressWarnings("unchecked")
    public BinarySearchTree() {
        root = null;
    }
    @SuppressWarnings("unchecked")
    public BNode<E> getRoot(){
        return root;
    }
    @SuppressWarnings("unchecked")
    private BNode<E> insert(E x, BNode<E>t){

        if(t == null)
        {
            t = new BNode<E>(x,null,null);
        }else if(x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        }else if(x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        }else;
        return t;
    }
    @SuppressWarnings("unchecked")
    public void insert(E x) {
        root = insert(x, root);
    }
    @SuppressWarnings("unchecked")
    private BNode<E>findMin(BNode<E> t){
        if(t == null) {
            return null;
        }else if(t.left == null) {
            return t;
        }
        return findMin(t.left);
    }
    @SuppressWarnings("unchecked")
    private BNode<E> findMax(BNode<E> t){
        if(t == null)
            return null;
        else if(t.right == null)
            return t;
        return findMax(t.right);
    }
    @SuppressWarnings("unchecked")
    private BNode<E> find(E x, BNode<E> t){
        if(t == null)
            return null;
        if(x.compareTo(t.info) < 0)
            return find(x, t.left);
        else if(x.compareTo(t.info) > 0)
        {
            return find(x, t.right);
        }
        else
            return t;
    }
    @SuppressWarnings("unchecked")
    public BNode<E> find(E x)
    {
        return find(x, root);
    }
    @SuppressWarnings("unchecked")
    private BNode<E> remove(Comparable x, BNode<E> t)
    {
        if(t == null)
            return t;
        if(x.compareTo(t.info) < 0)
            t.left = remove(x, t.left);
        else if(x.compareTo(t.info) > 0)
        {
            t.right = remove(x, t.right);
        }
        else if(t.left != null&&t.right != null)
        {
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        }
        else
        if(t.left != null)
            return t.left;
        else
            return t.right;
        return t;
    }
    @SuppressWarnings("unchecked")
    public void remove(E x) {
        root = remove(x, root);
    }
    @SuppressWarnings("unchecked")
    public int height(E elem) {
        BNode node = find(elem, root);
        return findH(node);
    }
    @SuppressWarnings("unchecked")
    public int findDepth(int l)
    {
        BNode<Integer>tmp = (BNode<Integer>)this.root;
        return Depth(tmp,0,l);
    }
    @SuppressWarnings("unchecked")
    public int Depth(BNode<Integer> node,int curr, int e)
    {
        if(node==null )
        {
            return 0;
        }
        if(curr==e)
            return 1;
        return Depth(node.left, curr + 1, e) + Depth(node.right, curr + 1, e);

    }
    @SuppressWarnings("unchecked")
    public int findH(BNode<Integer>node)
    {
        if(node==null)
        {
            return 0;
        }
        else {
            int leftH = findH(node.left);
            int rightH  = findH(node.right);
            if(leftH > rightH)
                return(leftH + 1);
            else
                return (rightH + 1);
        }

    }
    @SuppressWarnings("unchecked")
    public void preOrderR(BNode<Integer> node) {
        if (node==null)
            return;
        System.out.print(node.info + " ");
        preOrderR(node.left);
        preOrderR(node.right);
    }
}

public class TreeHeight {
    @SuppressWarnings("unchecked")
    public static void main(String[] args)throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        int n = Integer.parseInt(bf.readLine());

        int array[] = new int[n];
        for(int i = 0; i<n; i++)
        {
            int a = Integer.parseInt(bf.readLine());
            array[i] = a;
            tree.insert(a);
        }
        int b = Integer.parseInt(bf.readLine());
        int l = tree.height(b);
        System.out.println(l);
        System.out.println(tree.findDepth(l));

    }
}