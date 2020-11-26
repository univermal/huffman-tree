# Huffman Tree

Huffman Tree is an algorithm to compress text. It counts the frequencies of each character and based on the found character space, encodes the characters using lesser amount of bits, thus reducing the size.

So given a file, first a code is produced for each unique character. Then the file is encoded using the code. When the encoded file needs to be decoded, the same code is used for decoding. For simplicity, this program produces a separate code file. But for production use, the code part also should be included in the encoded file itself.

See further details here: https://en.wikipedia.org/w/index.php?title=Huffman_tree

### Usage

Simply execute Application.java, it currently passes play.txt as input file, which is also included in the project. Change or pass your own file through args.

This is the output it produces:

>Step 1 - Generate code file
>>	play.txt, bytes - 196197\
>>	play.code, bytes - 964

>Step 2 - Use code file to encode the input file
>>	play.short, bytes - 109263

>Step 3 - Decode the encoded file using the code file
>>	play.decoded, bytes - 196197     

So after encoding, the file size is (109263 + 964 = 110227), which is 56% of the original file.

## Data structures used
- Binary Tree: to store the bit pattern for each character, which gets traversed while decoding
- Priority Queue: temp data structure to keep processing the least frequent characters and combining them 
- Stack: temp data structure for reading the code for each character 
