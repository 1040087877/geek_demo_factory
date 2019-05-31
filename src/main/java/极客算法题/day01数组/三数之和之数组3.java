package 极客算法题.day01数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和之数组3 {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();
        for(int i=0;i< nums.length;i++)
        {
            if(i==0 || (i>0  && nums[i]!=nums[i-1])){
                int l=i+1;
                int r=nums.length-1;
                int sum=0-nums[i];

                while (l<r){
                    if (sum==nums[l]+nums[r]){
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l<r && nums[l]==nums[l+1]){ l++;}
                        while (l<r && nums[r]==nums[r-1]){r--;}
                        l++;
                        r--;
                    }else if(nums[l]+nums[r]<sum){
                        while (l<r && nums[l]==nums[l+1]){ l++;}
                        l++;
                    }else{
                        while (l<r && nums[r]==nums[r-1]){ r--;}
                        r--;
                    }
                }
            }
        }

        return ls;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{-1,-12,14,-6,4,-11,2,-7,13,-15,-1,11,-15,-15,13,-9,-11,-10,-7,-13,7,9,-13,-3,10,1,-5,12,11,-9,2,-4,-6,-7,5,5,-2,14,13,-12,14,-3,14,14,-11,5,12,-6,10,-9,-4,-6,-15,-9,-1,2,-1,9,-9,-5,-15,8,-2,-6,9,10,7,14,9,5,-13,9,-12,8,8,-8,-2,-2,1,-15,-4,5,-13,-4,3,1,5,-13,-13,11,-11,10,5,3,11,-9,-2,3,-10,-7,-6,14,9,-11,7,2,-4,13,7,5,13,-12,-13,7,-9,5,-7,11,-1,-12,8,3,13,-10,13,1,-4};
        //int[] nums=new int[]{-1, 0, 1, 2, -1, -4};
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
