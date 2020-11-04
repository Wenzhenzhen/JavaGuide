package binaryTree;

/**
 * @title: BinarySearchArray
 * @Author wenzhenzhen
 * @Date: 2020/10/16 3:57 下午
 */
public class BinarySearchArray {
    /**
     * @Description 数组边界查找
     * @Param
     * @return
     */
    public static Integer findValNotLessThan(int[] nums , Integer target){
        int low = 0,high = nums.length-1;
        while (low<high){
            int mid =low + (high-low)/2;
            if (nums[mid]==target){
                return nums[mid];
            }
            if (nums[mid]<target){
                low=mid+1;
            }else {
                high=mid;
            }
        }
        return nums[low]>target?nums[low-1]:nums[low];
    }

    public static void main(String[] args) {
        int shuzu []= {3,1,2,5,0,7,9,8};
        quiteSort(shuzu,0,shuzu.length-1);
        System.out.println(findValNotLessThan(shuzu,6));
    }

    public static void quiteSort(int[] nums,int low,int high){
        if (low > high) {
            return;
        }
        int left=low,right=high,temp=nums[low];
        while (left<right){
            while (left<right&&nums[right]>temp){
                right--;
            }
            nums[left]=nums[right];
            while (left<right&&nums[left]<temp){
                left++;
            }
            nums[right]=nums[left];
        }
        nums[left] = temp;
        quiteSort(nums,low,left-1);
        quiteSort(nums,left+1,high);
    }

}
