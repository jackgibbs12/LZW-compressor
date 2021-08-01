# LZW-compressor
## This is an implementation of the LZW algorithm
LZW.java contains an encode and decode method to compress and decompress 
a string using the LZW compression algorithm. The LZW algorithm 
is an adaptive model which progessively leanrs and updates based 
on the incoming text. It uses a dictionary and the compressed text 
consists of positions of string patterns in the dictionary.

##Set-up
To set up the compressor, first create an instance of the LZW class. 

    LZW compressor = new LZW();

You can then declare the string you wish to compress.

    String stringToEncode = "aababacbaacbaadaaa";

The final set-up stage is to create the initial dictionary for compression and 
for decompression. This dictionary will contain only the letters that appear in the 
string you wish to encode.

    Hashtable<String, Integer> initialDictionaryEncode = compressor.makeInitialDictionaryEncode(stringToEncode);
    Hashtable<Integer, String> initialDictionaryDecode = compressor.makeInitialDictionaryDecode(stringToEncode);

##Compress
To compress the string, call the encode method.

    ArrayList<Integer> compressedString = compressor.encode(stringToEncode, initialDictionaryEncode);

##Decompress
To decompress the list of dictionary positions, call the decode method.

    String decompressedString = compressor.decode(compressedString, initialDictionaryDecode);