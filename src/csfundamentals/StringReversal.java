package csfundamentals;

public class StringReversal {

    //by converting String into a Character Array
    public String reverseString(String st){

        if( st == null|| st.length() < 2){
            return st;
        }

        int left = 0;
        int right = st.length()-1;
        char[] result = st.toCharArray();

        while(left < right){

            char temp = result[left];
            result[left] = result[right];
            result[right]= temp;
            left++;
            right--;

        }

        return new String(result);

    }

    //by recursive function
    public String reverseStringRecursive(String st)
    {
        if ((st == null)||(st.length() < 2) )
            return st;
        return reverseStringRecursive(st.substring(1)) + st.charAt(0);
    }

    public static void main(String[] args){

        StringReversal stringReversal = new StringReversal();

        System.out.println("Reverse of foobar baz: " + stringReversal.reverseString("foobar baz") + "\n");

        System.out.println("Reverse of foobar baz: " + stringReversal.reverseStringRecursive("foobar baz"));

    }

}