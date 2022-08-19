class Solution {

    int max(int a, int b) {
        int res = 0;

        if(a > b) res = a;
        else res = b;

        return res;
    }

    int max_wiggle_rec(int[] nums, boolean bound_up, int prev, int i, int[] dp) {

        int res = 0;

        int n = nums.length;
        int num = bound_up ? 1 : 0;
        if(dp[i] != 0) {
            return dp[i];
        }

        if(i > n - 1) {
            return 0;
        }

        if(i == n - 1) {
            if(bound_up && nums[i] < prev) {
                return 1;
            }
            if(!bound_up && nums[i] > prev) {
                return 1;
            }
        }

        if(i < n - 1) {
            if(bound_up) {
                if(nums[i] < prev) {
                    int val1 = 1 + max_wiggle_rec(nums, false, nums[i], i + 1, dp);
                    int val2 = max_wiggle_rec(nums, true, prev, i + 1, dp);
                    res = max(val1, val2);
                }
            }
            if(!bound_up) {
                if(nums[i] > prev) {
                    int val1 = 1 + max_wiggle_rec(nums, true, nums[i], i + 1, dp);
                    int val2 = max_wiggle_rec(nums, false, prev, i + 1, dp);
                    res = max(val1, val2);
                }
            }
        }

        dp[i] = res;

        return res;
    }

    boolean all_same(int[] nums) {
        boolean all_the_same = true;

        int n = nums.length;

        for(int i = 1; i < n; ++i) {
            if(nums[i] != nums[i - 1]) {
                all_the_same = false;
            }
        }

        return all_the_same;
    }

    int max_wiggle_api(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n + 1];

        int start_low = 0;
        int start_high = 0;

        if(all_same(nums)) {
            return 1;
        }

        if(n == 1) {
            return 1;
        }

        if(n == 2 && nums[0] == nums[1]) {
            return 1;
        }

        if(n == 2 && nums[0] != nums[1]) {
            return 2;
        }

        for(int i = 0; i < n; ++i) {
            start_high = i;
            if(nums[i] > nums[0]) {
                break;
            }
        }

        while(start_high + 1 < n && nums[start_high + 1] >= nums[start_high]) {
            start_high++;
        }

        for(int i = 0; i < n; ++i) {
            start_low = i;
            if(nums[i] < nums[0]) {
                break;
            }
        }

        while(start_low + 1 < n && nums[start_low + 1] <= nums[start_low]) {
            start_low++;
        }

        int val1 = 2 + max_wiggle_rec(nums, true, nums[start_high], start_high + 1, dp);
        int val2 = 2 + max_wiggle_rec(nums, false, nums[start_low], start_low + 1, dp);

        return max(val1, val2);
    }

    public int wiggle_max_length(int[] nums) {

        return max_wiggle_api(nums);
    }
}