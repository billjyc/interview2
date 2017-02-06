package facebook;

/**
 * Created by billjyc on 2016/10/6.
 */
public class PreviousPermutaion {
    public void previousPermutation(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        //find the longest increasing sequence in the end
        int index = -1;
        for(int i = nums.length - 2; i >= 0; i--) {
            if(nums[i] > nums[i + 1]) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            reverse(nums, 0, nums.length - 1);
            //return;
        } else {
            //find the first element that is smaller than nums[index]
            int index2 = nums.length - 1;
            for(int i = nums.length - 1; i > index; i--) {
                if(nums[i] < nums[index]) {
                    index2 = i;
                    break;
                }
            }
            swap(nums, index, index2);
            reverse(nums, index + 1, nums.length - 1);
        }

        for(int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        PreviousPermutaion previousPermutaion = new PreviousPermutaion();
        previousPermutaion.previousPermutation(new int[]{1,2,3,4});
        previousPermutaion.previousPermutation(new int[]{1,1,3,4});
        previousPermutaion.previousPermutation(new int[]{1,3,2,4});
        previousPermutaion.previousPermutation(new int[]{4,3,2,1});
        previousPermutaion.previousPermutation(new int[]{1,1});
    }
}
