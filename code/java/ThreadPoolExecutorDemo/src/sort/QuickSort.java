package sort;

/**
 * @title: QuickSort
 * @Author wenzhenzhen
 * @Date: 2020/9/24 10:21 上午
 */
public class QuickSort {

    public static void quickSort(int[] nums,int low,int high){
        if (low > high) {
            return;
        }
        int left = low,right=high,temp=nums[low];
        while(left<right){
            while (left<right&&nums[right]>=temp){
                right--;
            }
            nums[left]=nums[right];
            while (left<right&&nums[left]<=temp){
                left++;
            }
            nums[right]=nums[left];
        }
        nums[left] = temp;
        quickSort(nums,low,left-1);
        quickSort(nums,left+1,high);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,7,5,4};
        quickSort(nums,0,nums.length-1);

        sort(nums, 0, nums.length - 1);
        for (int i: nums ) {
            System.out.print(i+",");
        }
    }


    public static void sort(int a[], int low, int hight) {
        if (low > hight) {
            return;
        }
        // 用子表的第一个记录做基准
        int i=low, j=hight, index=a[i];

        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && a[j] >= index)
                j--;
            if (i < j)
                a[i++] = a[j];// 用比基准小的记录替换低位记录
            while (i < j && a[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
                a[j--] = a[i];
        }
        a[i] = index;// 将基准数值替换回 a[i]
        sort(a, low, i - 1); // 对低子表进行递归排序
        sort(a, i + 1, hight); // 对高子表进行递归排序

    }
}
