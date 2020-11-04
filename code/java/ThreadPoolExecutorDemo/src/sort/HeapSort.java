package sort;

/**
 * @title: HeapSort
 * @Author wenzhenzhen
 * @Date: 2020/9/24 10:01 上午
 */
public class HeapSort {
    public static void adjustHead(int[] nums,int length,int i){
        int left = i*2+1;
        int right = i*2+2;
        int largeIndex = i;
        if (left<length && nums[left]>nums[largeIndex]){
            largeIndex = left;
        }
        if (right<length && nums[right]>nums[largeIndex]){
            largeIndex = right;
        }
        if (largeIndex!=i){
            swap(nums,largeIndex,i);
            adjustHead(nums,length,largeIndex);
        }
    }
    //交换数组中第i个节点和第j个节点的值
    public static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j]=temp;
    }

    public static void heapSort(int [] nums){
        int length = nums.length;
        //建堆
        for (int i= length/2-1;i>=0;i--){
            adjustHead(nums,length,i);
        }
        for (int i=length-1;i>0;i--){
            //交换堆顶元素和最后一个待排序子节点（最大元素下沉）
            swap(nums,i,0);
            adjustHead(nums,i,0);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,7,5,4};
        heapSort(nums);
        for (int i: nums ) {
            System.out.print(i+",");
        }

    }
}
