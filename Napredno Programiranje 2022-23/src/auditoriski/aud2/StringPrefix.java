package auditoriski.aud2;

public class StringPrefix{

    public static boolean isPrefix(String first, String second){
        if(first.length() > second.length()){
            return false;
        }

        for(int i=0;i<first.length();i++){
              if(first.charAt(i) != first.charAt(i)){
                  return false;
              }
        }
        return true;

    }




    public static void main(String[] args) {
        System.out.println(isPrefix("zdrav", "zdravstvo"));
        System.out.println(isPrefix("zdrav", "zdrastvo"));
        System.out.println(isPrefix("zdrav", "zdrav"));
        System.out.println(isPrefix("zdraaaaaaaaav", "zdravstvo"));
        System.out.println(isPrefix("zdravstvoj", "zdravstvo"));
        System.out.println("zdravstvo".startsWith("zdrav"));
    }
}
