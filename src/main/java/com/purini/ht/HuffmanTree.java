package com.purini.ht;

import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

import static com.purini.ht.GenerateCodeFile.CHAR_MAX;

public class HuffmanTree {

    private final HuffmanNode root;

    public HuffmanTree(int[] frequencies) {
        root = generateAndGetTree(frequencies);
    }

    public HuffmanTree(Scanner codeInput) {
        root = generateAndGetTree(codeInput);
        codeInput.close();
    }

    private HuffmanNode generateAndGetTree(Scanner codeInput) {
        HuffmanNode rootNode = new HuffmanNode(0, -1);
        while (codeInput.hasNextLine()) {

            int n = Integer.parseInt(codeInput.nextLine());
            String code = codeInput.nextLine();

            HuffmanNode currNode = rootNode;
            for (char c : code.toCharArray()) {
                if (c == '0') {
                    if (currNode.getLeft() == null) {
                        HuffmanNode newNode = new HuffmanNode(0, -1);
                        currNode.setLeft(newNode);
                        currNode = newNode;
                    } else {
                        currNode = currNode.getLeft();
                    }
                } else if (c == '1') {
                    if (currNode.getRight() == null) {
                        HuffmanNode newNode = new HuffmanNode(0, -1);
                        currNode.setRight(newNode);
                        currNode = newNode;
                    } else {
                        currNode = currNode.getRight();
                    }
                }
            }
            currNode.setCharacter(n);

        }
        return rootNode;
    }

    private HuffmanNode generateAndGetTree(int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        populateQueue(queue, frequencies);
        while (queue.size() > 1) {
            HuffmanNode left = queue.remove();
            HuffmanNode right = queue.remove();
            HuffmanNode parentNode = new HuffmanNode(left.getFrequency() + right.getFrequency(), -1);
            parentNode.setLeft(left);
            parentNode.setRight(right);
            queue.offer(parentNode);
        }
        return queue.remove();
    }

    private void populateQueue(PriorityQueue<HuffmanNode> queue, int[] frequencies) {
        for (int i = 0; i < frequencies.length; i++) {
            final int frequency = frequencies[i];
            if (frequency > 0) {
                HuffmanNode node = new HuffmanNode(frequency, i);
                queue.offer(node);
            }
        }
        //add pseudo-eof character, index is size
        queue.offer(new HuffmanNode(1, frequencies.length));
    }

    public void write(PrintStream output) {
        Stack<Integer> bitSeq = new Stack<>();
        traverse(root, output, bitSeq);

    }

    private void traverse(HuffmanNode node, PrintStream output, Stack<Integer> bitSeq) {
        final int character = node.getCharacter();
        if (character != -1) {
            output.println(character);
            final String x = bitSeq.stream().map(String::valueOf).collect(Collectors.joining());
            output.println(x);
        }
        if (node.getLeft() != null) {
            bitSeq.push(0);
            traverse(node.getLeft(), output, bitSeq);
        }
        if (node.getRight() != null) {
            bitSeq.push(1);
            traverse(node.getRight(), output, bitSeq);
        }
        if (!bitSeq.isEmpty()) {
            bitSeq.pop();
        }
    }

    public void decode(BitInputStream encoded, PrintStream output) {
        int b;
        HuffmanNode currentNode = root;
        while ((b = encoded.readBit()) != -1) {
            if (b == 0) {
                currentNode = currentNode.getLeft();
            } else if (b == 1) {
                currentNode = currentNode.getRight();
            }
            final int character = currentNode.getCharacter();
            if (character != -1) {
                if (character != CHAR_MAX) {
                    output.print((char) character);
                }
                currentNode = root;
            }
        }
    }
}
