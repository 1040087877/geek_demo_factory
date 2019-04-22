package 极客算法题.day01数组;

import java.util.ArrayList;
import java.util.List;

public class 三数之和数组 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list =new ArrayList<List<Integer>>();
        int count=0;
        for (int i=0;i<nums.length;i++) {
            for (int j=0;j<nums.length;j++) {
                for (int z=0;z<nums.length;z++) {
                    if(i==j || j==z || i==z ){
                        continue;
                    }

                    int value=nums[i]+nums[j]+nums[z];
                    /*if(nums[i]==nums[j] && nums[j]==nums[z] && count<1){
                        count=1;
                    }*/
                    if(value==0){
                        boolean falg=false;
                        for (List<Integer> sets:list) {
                           /* if(nums[i]==0 && nums[j]==0 && nums[z]==0){
                                System.out.println("测试");
                                System.out.println(i+"__"+j+"__"+z);
                                nums[i]!=nums[j] && nums[i]!=nums[z] && nums[j]!=nums[z]
                            }*/

                            if(sets.contains(nums[i]) && sets.contains(nums[j])
                                    && sets.contains(nums[z]) ){
                                    falg=true;
                            }

                        }
                        if(nums[i]==nums[j] && nums[j]==nums[z] && count<1){
                            count++;
                            falg=false;
                        }
                        if(falg){

                            continue;
                        }else{

                            List li=new ArrayList<Integer>();
                            li.add(nums[i]);
                            li.add(nums[j]);
                            li.add(nums[z]);
                            list.add(li);
                        }

                    }else{

                    }

                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{-1,-12,14,-6,4,-11,2,-7,13,-15,-1,11,-15,-15,13,-9,-11,-10,-7,-13,7,9,-13,-3,10,1,-5,12,11,-9,2,-4,-6,-7,5,5,-2,14,13,-12,14,-3,14,14,-11,5,12,-6,10,-9,-4,-6,-15,-9,-1,2,-1,9,-9,-5,-15,8,-2,-6,9,10,7,14,9,5,-13,9,-12,8,8,-8,-2,-2,1,-15,-4,5,-13,-4,3,1,5,-13,-13,11,-11,10,5,3,11,-9,-2,3,-10,-7,-6,14,9,-11,7,2,-4,13,7,5,13,-12,-13,7,-9,5,-7,11,-1,-12,8,3,13,-10,13,1,-4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists.size());
        for (List<Integer> list:lists) {
            for(Integer l:list){
                System.out.print(l+" ");
            }
            System.out.println();
        }

    }
}
