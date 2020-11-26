package com.purini.ht;

import java.io.*;
import java.util.*;

import static com.purini.ht.GenerateCodeFile.CHAR_MAX;

public class Encoder {

    public void encode(String codeFile, String inFile, String encodedFile) {

        // open code file and record codes
        try {
            String[] codes = new String[CHAR_MAX + 1];
            Scanner codeInput = new Scanner(new File(codeFile));
            while (codeInput.hasNextLine()) {
                int n = Integer.parseInt(codeInput.nextLine());
                codes[n] = codeInput.nextLine();
            }
            codeInput.close();

            // open source file, open output, encode
            FileInputStream input = new FileInputStream(inFile);
            BitOutputStream output = new BitOutputStream(encodedFile);
            for (;;) {
                int n = input.read();
                if (n == -1)
                    break;
                writeString(codes[n], output);
            }
            input.close();
            writeString(codes[CHAR_MAX], output);
            output.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeString(String s, BitOutputStream output) {
        for (int i = 0; i < s.length(); i++)
            output.writeBit(s.charAt(i) - '0');
    }
}