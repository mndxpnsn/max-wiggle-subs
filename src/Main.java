public class Main {
    public static void main(String[] args) {

        // Declare and initialize input array
        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};

        // Compute max wiggle subsequence
        Solution sol_obj = new Solution();

        int max_wiggle_subs = sol_obj.wiggle_max_length(nums);

        // Print results
        System.out.println("max wiggle subsequqnce: " + max_wiggle_subs);
    }
}