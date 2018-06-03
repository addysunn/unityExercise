package csfundamentals;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringReversalTest {
    @Test
    public void reverseString() {
        StringReversal stringReversal = new StringReversal();

        assertEquals("zab raboof",stringReversal.reverseString("foobar baz"));
        assertEquals("baz",stringReversal.reverseString("zab"));
        assertEquals("raboof",stringReversal.reverseString("foobar"));
        assertEquals(" esicrexe ytinu aytida ",stringReversal.reverseString(" aditya unity exercise "));
        assertNull(stringReversal.reverseString(null));
    }

    @Test
    public void reverseStringRecursive() {
        StringReversal stringReversal = new StringReversal();

        assertEquals("zab raboof",stringReversal.reverseStringRecursive("foobar baz"));
        assertEquals("baz",stringReversal.reverseStringRecursive("zab"));
        assertEquals("raboof",stringReversal.reverseStringRecursive("foobar"));
        assertEquals("esicrexe ytinu aytida",stringReversal.reverseStringRecursive("aditya unity exercise"));
        assertNull(stringReversal.reverseStringRecursive(null));
    }

}