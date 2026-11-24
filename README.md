# 🗜️ Text Compression Tool (Huffman Coding in Java)

![Java](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk)
![Algorithm](https://img.shields.io/badge/Algorithm-Huffman%20Coding%20(Greedy)-blue?style=for-the-badge)
![Data Structures](https://img.shields.io/badge/Data%20Structures-PriorityQueue%20%7C%20HashMap%20%7C%20Binary%20Tree-success?style=for-the-badge)
![Problem Statement](https://img.shields.io/badge/Problem%20Statement-No.%2099-purple?style=for-the-badge)

> **Course:** B.Tech – Computer Science & Engineering (Data Science)  
> **Submitted By:** Shivam Kumar (`ERP ID: RU-25-11368`)  
> **Guided By:** Mr. Abhijeet Dutta  
> **Institution:** Rungta International Skills University, Bhilai, Chhattisgarh  

---

## 📌 Overview
A console-based **lossless text compression and decompression** tool implemented in Java using **Huffman Coding**. By leveraging character frequencies, a **Min-Heap (`PriorityQueue`)**, and a **Greedy Algorithm**, it assigns shorter variable-length prefix-free binary codes to frequent characters and longer codes to rare characters—reducing storage size without losing a single bit of original information.

---

## ✨ Key Features
- **Frequency Analysis (`HashMap`):** Dynamically computes character frequencies in $O(n)$ time.
- **Greedy Huffman Tree (`PriorityQueue`):** Repeatedly merges the two lowest-frequency nodes (`left child = 0`, `right child = 1`).
- **Prefix-Free Recursive Code Generation:** Traverses the binary tree recursively to build an unambiguous code table.
- **Lossless Encoding & Decoding:** Compresses plain text into a binary bit string and reconstructs the exact original text.
- **Compression Analytics:** Displays original bit count (`8 bits/char`), encoded bit count, and percentage of space saved.
- **Complete Edge-Case Handling:**
  - Empty input validation (`"Input is empty. Nothing to compress."`)
  - Single-distinct-character input (explicitly assigned code `0`)
  - Rejects invalid bit strings (characters other than `0`/`1`) and incomplete bit paths.

---

## 📊 Verified Test Cases (PS No. 99)

| Test Case | Input / Action | Generated Codes / Output | Space Saved |
| :--- | :--- | :--- | :--- |
| **1. Normal Input** | Compress `aaaabbc` | `a=1, b=01, c=00` → `1111010100` | **82.14%** |
| **2. Minimum Input** | Compress `aaaa` | `a=0` → `0000` | **87.50%** |
| **3. Empty Input** | Compress `""` | `Input is empty. Nothing to compress.` | — |
| **4. Decode Bit String** | Decode `0100111` | `Decoded text: bcaaa` | — |

---

## ⚡ Time & Space Complexity

| Operation | Complexity | Data Structure Used |
| :--- | :--- | :--- |
| Frequency Counting | $O(n)$ | `HashMap<Character, Integer>` |
| Huffman Tree Construction | $O(k \log k)$ | `PriorityQueue<HuffmanNode>` |
| Code Table Generation | $O(k)$ | Recursion |
| Encoding / Decoding | $O(n + L)$ / $O(L)$ | `StringBuilder` & Binary Tree |
| Auxiliary Space | $O(k)$ | Tree & Maps ($k$ = unique chars) |

---

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/shivamkumar6-a11y/Text-Compression-Tool-Huffman-Java.git](https://github.com/shivamkumar6-a11y/Text-Compression-Tool-Huffman-Java.git)
   cd Text-Compression-Tool-Huffman-Java
