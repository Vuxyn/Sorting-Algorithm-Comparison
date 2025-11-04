import ADT_Lists.*;
import ADT_Nodes.*;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        // Ukuran data yang akan ditest
        int[] dataSizes = {100, 500, 1000, 2500, 5000, 7500, 10000};
        
        System.out.println("=".repeat(90));
        System.out.println("                  BUBBLE SORT vs MERGE SORT - LINKED LIST COMPARISON");
        System.out.println("=".repeat(90));
        System.out.println();
        
        System.out.printf("%-15s %-25s %-25s %-20s\n", 
            "Data Size", "Bubble Sort (ms)", "Merge Sort (ms)", "Speedup");
        System.out.println("-".repeat(90));
        
        for (int size : dataSizes) {
            // Generate random data SEKALI - data yang sama untuk kedua algoritma
            Integer[] randomData = generateRandomData(size);
            
            // ===== Test Bubble Sort =====
            Singly_Linked_List<Integer> listBubble = new Singly_Linked_List<>();
            for (Integer num : randomData) {
                listBubble.add(num);
            }
            
            long startTime = System.nanoTime();
            listBubble.sort(Linked_List.Sort_Type.BUBBLE);
            long endTime = System.nanoTime();
            double bubbleTime = (endTime - startTime) / 1_000_000.0; // Convert to ms dengan presisi
            
            // Verify sorted
            boolean bubbleSorted = listBubble.is_sorted();
            
            // ===== Test Merge Sort dengan DATA YANG SAMA =====
            Singly_Linked_List<Integer> listMerge = new Singly_Linked_List<>();
            for (Integer num : randomData) {
                listMerge.add(num);
            }
            
            startTime = System.nanoTime();
            listMerge.sort(Linked_List.Sort_Type.MERGE);
            endTime = System.nanoTime();
            double mergeTime = (endTime - startTime) / 1_000_000.0; // Convert to ms dengan presisi
            
            // Verify sorted
            boolean mergeSorted = listMerge.is_sorted();
            
            // Calculate speedup
            double speedup = mergeTime > 0 ? bubbleTime / mergeTime : 0;
            
            System.out.printf("%-15d %-25.3f %-25.3f ", size, bubbleTime, mergeTime);
            if (speedup > 0) {
                System.out.printf("%-20.2fx\n", speedup);
            } else {
                System.out.printf("%-20s\n", "N/A");
            }
            
            if (!bubbleSorted || !mergeSorted) {
                System.err.println("ERROR: Sorting failed for size " + size);
            }
        }
        
        System.out.println("-".repeat(90));
        System.out.println("\nConclusion:");
        System.out.println("- Bubble Sort: O(n²) - Performance degrades significantly with large data");
        System.out.println("- Merge Sort: O(n log n) - Maintains efficiency even with large data");
        System.out.println("- Same random data used for both algorithms to ensure fair comparison");
        System.out.println("\nNote: Bubble Sort becomes extremely slow for large datasets (>10000)");
        System.out.println("      For datasets >30000, Bubble Sort may take several minutes!");
    }
    
    /**
     * Generate random Integer array
     * @param size Number of elements
     * @return Array of random integers
     */
    private static Integer[] generateRandomData(int size) {
        Random random = new Random();
        Integer[] data = new Integer[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(10000); // Random numbers 0-9999
        }
        return data;
    }
    
    /**
     * Print linked list (optional, untuk debugging)
     * @param list The linked list to print
     * @param maxItems Maximum items to display
     */
    public static void printList(Singly_Linked_List<Integer> list, int maxItems) {
        Node<Integer> current = list.get_head();
        int count = 0;
        
        while (current != null && count < maxItems) {
            System.out.print(current.get_data() + " ");
            current = current.get_next();
            count++;
        }
        
        if (list.get_size() > maxItems) {
            System.out.print("... (+" + (list.get_size() - maxItems) + " more)");
        }
        System.out.println();
    }
}