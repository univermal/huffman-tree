package com.purini.ht;

import java.io.*;

public class GenerateCodeFile {
    public static final int CHAR_MAX = 256; // max char value to be encoded

    public void generate(String inFile, String codeFile) throws IOException {
        // open input file and count character frequencies
        try (FileInputStream input = new FileInputStream(inFile)) {
            int[] count = new int[CHAR_MAX];
            while (true) {
                int n = input.read();
                if (n == -1)
                    break;
                count[n]++;
            }
            HuffmanTree t = new HuffmanTree(count);
            writeCodeFile(codeFile, t);
        }
    }

    private void writeCodeFile(String codeFile, HuffmanTree t) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(codeFile));
        t.write(output);
    }

}