package auditoriski.aud2;



public class ChangeArrays<E> {
    public void compareAndChangeArrays(Array<E> niza1, Array<E> niza2){
        if(niza1.getSize() != niza2.getSize()){
            System.out.println("Nizite se od razlicna golemina");
            return;
        }
        int size = niza1.getSize();
        int i=0;

        while(i<size){
            if(niza1.get(i).equals(niza2.get(i))){
                niza1.delete(i);
                niza2.delete(i);
                size--;
            }else{
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Array<String> niza1= new Array<String>(4);
        niza1.insertLast("nb11");
        niza1.insertLast("b1");
        niza1.insertLast("b2");
        niza1.insertLast("nb12");

        Array<String> niza2= new Array<String>(4);
        niza1.insertLast("nb21");
        niza1.insertLast("b1");
        niza1.insertLast("b2");
        niza1.insertLast("nb13");

        System.out.println("Nizi pred:");
        System.out.println(niza1.toString());
        System.out.println(niza2.toString());

        ChangeArrays<String> pom = new ChangeArrays<String>();
        pom.compareAndChangeArrays(niza1,niza2);

        System.out.println("Nizi po:");
        System.out.println(niza1.toString());
        System.out.println(niza2.toString());

    }
}
