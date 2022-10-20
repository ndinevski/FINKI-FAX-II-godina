package auditoriski.aud2;

public class Lock {
    private int combination;
    private boolean isOpen;
    private static int DEFAULT_COMBINATION = 100;
    private static int MIN_COMBINATION = 100;
    private static int MAX_COMBINATION = 999;

    public Lock(int combination) {
        if(isValid(combination)) {
            this.combination = combination;
        }
        else this.combination = DEFAULT_COMBINATION;
        this.isOpen = false;
    }

    private boolean isValid(int combination){
        return combination >= MIN_COMBINATION && MAX_COMBINATION <= 999;
    }

    public boolean isOpen(){
        return isOpen;
    }

    public boolean open(int combinationTry){
        this.isOpen = (this.combination==combinationTry);
        return this.isOpen;
    }

    public void lock(){
        this.isOpen = false;
    }

    public boolean changeCombination(int oldCombination, int newCombination){
        if(open(oldCombination) && isValid(newCombination)){
            this.combination = newCombination;
            this.lock();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Lock l1 = new Lock (482);

        System.out.println("TESTING isOpen().........");
        System.out.println(l1.isOpen());

        System.out.println("TESTING open().........");
        System.out.println(l1.open(233));
        System.out.println(l1.open(999));
        System.out.println(l1.open(482));

        System.out.println("TESTING lock().........");
        l1.lock();
        System.out.println(l1.isOpen());

        System.out.println("TESTING changeCombination().........");
        System.out.println(l1.changeCombination(233, 234));
        System.out.println(l1.changeCombination(999, 234));
        System.out.println(l1.changeCombination(482, 234));
        System.out.println(l1.open(233));
        System.out.println(l1.open(999));
        System.out.println(l1.open(482));
        System.out.println(l1.open(234));



        Lock l2 = new Lock (123123);
        System.out.println("TESTING isOpen().........");
        System.out.println(l2.isOpen());

        System.out.println("TESTING open().........");
        System.out.println(l2.open(233));
        System.out.println(l2.open(999));
        System.out.println(l2.open(482));

        System.out.println("TESTING lock().........");
        l2.lock();
        System.out.println(l2.isOpen());

        System.out.println("TESTING changeCombination().........");
        System.out.println(l2.changeCombination(233, 234));
        System.out.println(l2.changeCombination(999, 234));
        System.out.println(l2.changeCombination(482, 234));


    }
}
