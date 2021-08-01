import java.util.Hashtable;
import java.util.ArrayList;

public class LZW {
    /**
     * This method encodes a string using the LZW compression algorithm. A string to encode and the
     * initial dictionary is passed in, the string is iterated over to generate the compressed text, and
     * that compressed text is returned as a string.
     *
     * @param  stringToEncode  The string that is to be compressed using LZW
     * @param  dictionary the initial dictionary containing the characters in the stringToEncode
     * @return the compressed string
     */
    public ArrayList<Integer> encode(String stringToEncode, Hashtable<String, Integer> dictionary){
        ArrayList<Integer> compressedString = new ArrayList<>();
        int dictLength = dictionary.size() + 1;
        int lengthOfStringToEncode = stringToEncode.length();
        String longestMatch = "";

        for (int i =0; i<lengthOfStringToEncode; i++){
            /* Check if the longestMatch plus the current letter is in the dictionary */
            if (dictionary.containsKey(longestMatch + stringToEncode.charAt(i) )){
                longestMatch = longestMatch + stringToEncode.charAt(i);
            }
            else {
                dictionary.put(longestMatch + stringToEncode.charAt(i), dictLength);
                dictLength += 1;
                compressedString.add(dictionary.get(longestMatch));
                longestMatch = "" + stringToEncode.charAt(i);
            }
        }
        compressedString.add(dictionary.get(longestMatch));
        return compressedString;
    }

    /**
     * This method decodes a string that has been encoded with the LZW algorithm.
     *
     * @param  stringToDecode  The message that is to be decompressed using LZW as a list of integers
     * @param  dictionary the initial dictionary containing the characters in the originally compressed string
     * @return the decompressed string
     */
    public String decode(ArrayList<Integer> stringToDecode, Hashtable<Integer, String> dictionary){

        int dictLength = dictionary.size()+1;
        int lengthOfStringToEncode = stringToDecode.size();
        String currentSequence = "";
        String priorSequence = "";
        String currentCode = "";

        for (int i=0;i<lengthOfStringToEncode;i++){
            String codeChar = "" + stringToDecode.get(i);
            int code = Integer.parseInt(codeChar);

            if (!dictionary.containsKey(code)){
                currentSequence = currentSequence + priorSequence + priorSequence.charAt(0);
                dictionary.put(dictLength, (priorSequence+priorSequence.charAt(0)));
                dictLength += 1;
            }else{
                currentCode = dictionary.get(code);
                currentSequence = currentSequence + currentCode;
                if (!dictionary.contains(priorSequence+currentCode.charAt(0))){
                    dictionary.put(dictLength, priorSequence+currentCode.charAt(0));
                    dictLength += 1;
                }
            }
            priorSequence = dictionary.get(code);
        }
        return currentSequence;
    }

    /**
     * This method encodes a string using the LZW compression algorithm. A string to encode and the
     * initial dictionary is passed in, the string is iterated over to generate the compressed text, and
     * that compressed text is returned as a string.
     *
     * @param  stringToEncode  The string that is to be compressed using LZW
     * @return a Hashtable of the letters in the string to be used as the initial dictionary
     */
    public Hashtable<String, Integer> makeInitialDictionaryEncode( String stringToEncode){
        Hashtable<String, Integer> dictionary
                = new Hashtable<String, Integer>();

        int length = stringToEncode.length();
        int dictLength = 1;
        for (int i =0; i<length; i++){
            String letter = "" + stringToEncode.charAt(i);

            if (!dictionary.containsKey(letter)){
                dictionary.put(letter, dictLength);
                dictLength +=1;
            }
        }
        return dictionary;
    }

    /**
     * This method encodes a string using the LZW compression algorithm. A string to encode and the
     * initial dictionary is passed in, the string is iterated over to generate the compressed text, and
     * that compressed text is returned as a string.
     *
     * @param  stringToEncode  The string that is to be compressed using LZW
     * @return a Hashtable of the letters in the string to be used as the initial dictionary
     */
    public Hashtable<Integer, String> makeInitialDictionaryDecode( String stringToEncode){
        Hashtable<Integer, String> dictionary
                = new Hashtable<Integer, String>();

        int length = stringToEncode.length();
        int dictLength = 1;
        for (int i =0; i<length; i++){
            String letter = "" + stringToEncode.charAt(i);

            if (!dictionary.containsValue(letter)){
                dictionary.put(dictLength, letter);
                dictLength +=1;
            }
        }
        return dictionary;
    }
}
