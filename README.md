# Sorting Algorithm Comparison

## Description

This project analyzes and compares the execution time efficiency between **Bubble Sort** and **Merge Sort** algorithms implemented on singly linked lists. The comparison is performed across various dataset sizes to demonstrate the performance differences between O(n²) and O(n log n) time complexities.

## Features

- **Algorithm Implementation**: Custom implementation of Bubble Sort and Merge Sort for singly linked lists
- **Performance Benchmarking**: Measures execution times for different data sizes (100 to 10,000 elements)
- **Fair Comparison**: Uses identical random data for both algorithms to ensure accurate comparison
- **Data Visualization**: Jupyter notebook with matplotlib plots (linear and logarithmic scales)
- **Verification**: Includes sorting verification to ensure correctness

## Project Structure

```
📦Sorting-Algorithm-Comparison
 ┣ 📂ADT_Lists
 ┃ ┣ 📜Circular_Linked_List.java
 ┃ ┣ 📜Doubly_Linked_List.java
 ┃ ┣ 📜Linked_List.java
 ┃ ┗ 📜Singly_Linked_List.java
 ┣ 📂ADT_Nodes
 ┃ ┣ 📜Doubly_Node.java
 ┃ ┣ 📜Node.java
 ┃ ┗ 📜Singly_Node.java
 ┣ 📜Chart.ipynb
 ┣ 📜Main.java
 ┗ 📜README.md
```

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Jupyter Notebook (for visualization)
- Python with matplotlib (for Chart.ipynb)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Sorting-Algorithm-Comparison.git
   cd Sorting-Algorithm-Comparison
   ```

2. Ensure Java is installed:
   ```bash
   java -version
   javac -version
   ```

3. For visualization (optional):
   ```bash
   pip install matplotlib jupyter
   ```

## Usage

### Running the Benchmark

Compile and run the main program:
```bash
javac -d bin Main.java ADT_Lists/*.java ADT_Nodes/*.java
java -cp bin Main
```

This will output a comparison table showing execution times for both algorithms across different data sizes.

### Visualization

Open the Jupyter notebook to view the performance charts:
```bash
jupyter notebook Chart.ipynb
```

## Results

The benchmark compares Bubble Sort and Merge Sort on singly linked lists with data sizes ranging from 100 to 10,000 elements.

### Key Findings

- **Bubble Sort**: O(n²) complexity - performance degrades significantly with larger datasets
- **Merge Sort**: O(n log n) complexity - maintains efficiency even with large data
- **Speedup**: Merge Sort shows increasing performance advantage as data size grows

### Sample Output

```
==========================================================================================
                  BUBBLE SORT vs MERGE SORT - LINKED LIST COMPARISON
==========================================================================================

Data Size        Bubble Sort (ms)        Merge Sort (ms)          Speedup
------------------------------------------------------------------------------------------
100              1.401                    0.238                    5.89x
500              9.557                    0.447                    21.39x
1000             2.619                    0.531                    4.93x
2500             14.290                   0.720                    19.85x
5000             71.389                   1.032                    69.17x
7500             193.664                  1.212                    159.79x
10000            387.227                  2.445                    158.29x
------------------------------------------------------------------------------------------
```

### Performance Charts

#### Linear Scale
![Linear Scale Chart](https://github.com/user-attachments/assets/504e030b-c8d1-4a03-858d-d684e5052f4b)

#### Logarithmic Scale
![Log Scale Chart](https://github.com/user-attachments/assets/71427649-6cb8-4cd6-8738-a1a88dce4d65)

## Implementation Details

- **Data Generation**: Random integers (0-9999) generated once per data size and reused for both algorithms
- **Time Measurement**: High-precision timing using `System.nanoTime()` converted to milliseconds
- **Sorting Verification**: Each sort is verified to ensure correctness
- **Linked List ADT**: Custom implementation with abstract base classes and concrete implementations

## Contributors

1. I Nyoman Widiyasa Jayananda (F1D02410053)
2. I Kadek Mahesa Permana Putra (F1D02410052)
3. Reza Muthahhari Purnomo (F1D02410088)

## License

This project is for educational purposes as part of the Algorithm and Data Structure course. Licensed under the [MIT License](https://github.com/Vuxyn/Sorting-Algorithm-Comparison/blob/main/LICENSE).
