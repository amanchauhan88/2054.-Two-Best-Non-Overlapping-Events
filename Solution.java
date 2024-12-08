import java.util.*;

class Solution {
    public int maxTwoEvents(int[][] events) {
        // Sort events by end time
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        
        // Array to store the maximum value up to each event
        int n = events.length;
        int[] maxValues = new int[n];
        maxValues[0] = events[0][2];
        
        // Fill maxValues array
        for (int i = 1; i < n; i++) {
            maxValues[i] = Math.max(maxValues[i - 1], events[i][2]);
        }
        
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            // Single event case
            result = Math.max(result, events[i][2]);
            
            // Binary search for the latest non-overlapping event
            int left = 0, right = i - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (events[mid][1] < events[i][0]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            // If a valid event is found
            if (right >= 0) {
                result = Math.max(result, events[i][2] + maxValues[right]);
            }
        }
        
        return result;
    }
}
