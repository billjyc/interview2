/**
 * Created by billjyc on 2016/10/23.
 */
public class BubbleSort {
    public void bubbleSort(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        for(int i = nums.length - 1; i >= 0; i--) {
            for(int j = 0; j < i; j++) {
                if(nums[j] > nums[j + 1]) {
                    swap(nums, j, j+1);
                }
            }
        }
        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        new BubbleSort().bubbleSort(new int[]{1,1,3,2,5,6});
    }
}
