import java.util.ArrayList;
import java.util.Hashtable;

public class Compressor {
    public static void main(String[] args){
        LZW compressor = new LZW();

        String stringToEncode = "aababacbaacbaadaaa";

        /* Create the initial dictionary for encoding and decoding */
        Hashtable<String, Integer> initialDictionaryEncode = compressor.makeInitialDictionaryEncode(stringToEncode);
        Hashtable<Integer, String> initialDictionaryDecode = compressor.makeInitialDictionaryDecode(stringToEncode);

        /* Compress and then decompress the string */
        ArrayList<Integer> compressedString = compressor.encode(stringToEncode, initialDictionaryEncode);
        String decompressedString = compressor.decode(compressedString, initialDictionaryDecode);

        System.out.println("Compressed String:");
        System.out.println(compressedString);
        System.out.println("\nDecompressed String:");
        System.out.println(decompressedString);
    }
}
