package com.purini.ht;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Decoder {

    public void decode(String encodedFile, String codeFile, String decodedFile) throws FileNotFoundException {

        // open code file and construct tree
        Scanner codeInput = new Scanner(new File(codeFile));
        HuffmanTree t = new HuffmanTree(codeInput);

        // open encoded file, open output, decode
        BitInputStream input = new BitInputStream(encodedFile);
        PrintStream output = new PrintStream(new File(decodedFile));
        t.decode(input, output);
        output.close();
    }
}