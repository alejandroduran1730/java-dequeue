import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

    /*
     * Description:
     * In this problem, you are given N integers. You need to find the maximum number 
     * of unique integers among all the possible contiguous subarrays (windows) of size M.
     * Note: Time limit is 3 second for this problem.
     * 
     * Input Format:
     * The first line contains two integers N and M: representing the total number 
     *   of integers and the size of the subarray, respectively.
     * The next line contains N space-separated integers.
     * 
     * Constraints:
     * 1 <= N <= 100000
     * 1 <= M <= 100000
     * M <= N
     *
     * Output Format:
     * Print the maximum number of unique integers among all possible contiguous subarrays of size M.
     *
     * Sample Output:
     * 6 3
     * 5 3 5 2 3 2
     *
     * Sample Output:
     * 3
     */
     
public class Solution {
    public static void main(String[] args) {
    
        // Create a Scanner object to read input from standard input
        Scanner in = new Scanner(System.in);
        
        // Use an ArrayDeque to keep track of the elements in the current sliding window
        Deque<Integer> deque = new ArrayDeque<>();
        
        // Use a HashMap to store the frequency of each element present in the current window
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Read the total number of elements (n) and the subarray size (m)
        int n = in.nextInt();
        int m = in.nextInt();
        
        // Initialize a variable to track the maximum number of unique elements found
        int maxUnique = 0;
        
        // Loop through all n elements from the input stream
        for (int i = 0; i < n; i++) {
            // Read the current integer
            int num = in.nextInt();
            
            // Add the new integer to the back (tail) of the deque (expanding the window)
            deque.addLast(num);
            
            // Update its frequency count in the frequency map
            map.put(num, map.getOrDefault(num, 0) + 1);
            
            // If the window size exceeds m, remove the oldest element from the front (head)
            if (deque.size() > m) {
                int removed = deque.removeFirst();
                int count = map.get(removed);
                
                // If the removed element's count drops to 0, remove it completely from the map
                if (count == 1) {
                    map.remove(removed);
                } else {
                    // Otherwise, decrement its frequency count
                    map.put(removed, count - 1);
                }
            }
            
            // Once the window size matches m, check and update the maximum unique elements count
            if (deque.size() == m) {
                maxUnique = Math.max(maxUnique, map.size());
            }
        }
        
        // Print the final maximum number of unique integers found in any window of size m
        System.out.println(maxUnique);
        
        // Close the scanner resource
        in.close();
        
        // Solution's algorithm analysis:
        // Time Complexity: O(n)
        // Space Complexity: O(m)
    }
}