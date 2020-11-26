package com.purini.ht;

public class HuffmanNode implements Comparable<HuffmanNode> {

    private int frequency;
    private int character;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(int frequency, int character) {
        this.frequency = frequency;
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return Integer.compare(this.getFrequency(), o.getFrequency());
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "frequency=" + frequency +
                ", character=" + character +
                '}';
    }
}
