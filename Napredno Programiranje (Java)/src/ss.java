import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static int[] twoSum(int [] numss, int target){
        Map<Integer,Integer> numbers = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        List<Integer> nums = Arrays.stream(numss).boxed().collect(Collectors.toList());

        for (int i=0; i<nums.size();i++) {
            int secondNum = target-nums.get(i);
            if(numbers.containsKey(secondNum)){
                result.add(numbers.get(secondNum));
                result.add(i);
                break;
            }else {
                numbers.put(nums.get(i), i);
            }
        }
        int [] ret = {result.get(0), result.get(1)};
        ret.length

        return ret;
    }

    // public static void main(String[] args) throws IOException {
    //     BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    //     String [] line = bf.readLine().split("\\s+");
    //     String [] nums = line[2].substring(1,line[2].length()-2).split(",");
    //     List<Integer> numbers = new ArrayList<>();
    //     for (String num : nums) {
    //         numbers.add(Integer.parseInt(num));
    //     }
    //     int target = Integer.parseInt(line[5]);
    //     System.out.println(twoSum(numbers, target));
    // }
}
