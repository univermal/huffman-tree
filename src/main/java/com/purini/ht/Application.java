package com.purini.ht;

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {

        String fileName = "play";
        //String fileName = "test";
        String inFile = fileName + ".txt";
        String codeFile = fileName + ".code";
        String encodedFile = fileName + ".short";
        String decodedFile = fileName + ".decoded";

        System.out.println("Step 1 - Generate code file");
        System.out.println(fileInfo(inFile));
        new GenerateCodeFile().generate(inFile, codeFile);
        System.out.println(fileInfo(codeFile));

        System.out.println("Step 2 - Use code file to encode the input file");
        Encoder encoder = new Encoder();
        encoder.encode(codeFile, inFile, encodedFile);
        System.out.println(fileInfo(encodedFile));

        System.out.println("Step 3 - Decode the encoded file using the code file");
        Decoder decoder = new Decoder();
        decoder.decode(encodedFile, codeFile, decodedFile);
        System.out.println(fileInfo(decodedFile));
    }

    private static String fileInfo(String fileName) {
        final File file = new File(fileName);
        return "\t" + file.getName() + ", bytes - " + file.length();
    }

}
