import java.util.*;

class HuffmanNode {
    char symbol;
    int frequency;
    HuffmanNode left, right;

    HuffmanNode(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }
}

public class TextCompressionTool {
    private static HuffmanNode root;
    private static Map<Character, String> codeTable = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Text Compression Tool ===");
            System.out.println("1. Compress Text");
            System.out.println("2. Decode Bit String");
            System.out.println("3. Show Code Table");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter text to compress: ");
                String text = sc.nextLine();
                compress(text);
            } else if (choice.equals("2")) {
                System.out.print("Enter bit string to decode: ");
                String bits = sc.nextLine().trim();
                decode(bits);
            } else if (choice.equals("3")) {
                if (codeTable.isEmpty()) {
                    System.out.println("Code table is empty.");
                } else {
                    System.out.println("Code Table: " + codeTable);
                }
            } else if (choice.equals("4")) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    private static void compress(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Input is empty. Nothing to compress.");
            return;
        }

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        root = pq.poll();
        codeTable.clear();
        buildCodes(root, "");

        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codeTable.get(c));
        }

        int originalBits = text.length() * 8;
        int encodedBits = encoded.length();
        double saved = ((double) (originalBits - encodedBits) / originalBits) * 100;

        System.out.println("Code Table: " + codeTable);
        System.out.println("Encoded Bit String: " + encoded + " (" + encodedBits + " bits)");
        System.out.printf("Original Bits: %d bits | Space Saved: %.2f%%\n", originalBits, saved);
    }

    private static void buildCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            codeTable.put(node.symbol, code.isEmpty() ? "0" : code);
            return;
        }
        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }

    private static void decode(String bits) {
        if (root == null) {
            System.out.println("Compress text first to build the Huffman tree.");
            return;
        }
        if (bits.isEmpty()) {
            System.out.println("Bit string is empty.");
            return;
        }

        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        if (root.left == null && root.right == null) {
            for (char b : bits.toCharArray()) {
                if (b != '0') {
                    System.out.println("Error: Invalid bit string.");
                    return;
                }
                decoded.append(root.symbol);
            }
            System.out.println("Decoded Text: " + decoded);
            return;
        }

        for (char b : bits.toCharArray()) {
            if (b == '0') {
                current = current.left;
            } else if (b == '1') {
                current = current.right;
            } else {
                System.out.println("Error: Bit string must contain only 0 and 1.");
                return;
            }

            if (current.left == null && current.right == null) {
                decoded.append(current.symbol);
                current = root;
            }
        }

        if (current != root) {
            System.out.println("Error: Incomplete bit string.");
        } else {
            System.out.println("Decoded Text: " + decoded);
        }
    }
}
