package ca.retrylife.jdma;

import static ca.retrylife.jdma.DMA.*;
import static ca.retrylife.jdma.DMAString.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ca.retrylife.jdma.annotations.Pointer;

public class DMAStringTest {

    @Test
    public void testStringAllocation() {

        // Allocate a simple string
        @Pointer
        long str = allocateString("abc");

        // Check that the correct values are set
        byte[] expected = new byte[] { 'a', 'b', 'c', '\0' };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], peek(str + i));
        }

        // Clean up
        free(str);
    }

    @Test
    public void testStrlen() {

        // Allocate a simple string
        @Pointer
        long str = allocateString("abc");

        // Get the length
        long len = strlen(str);

        // Compare
        assertEquals(3, len);

        // Clean up
        free(str);

    }
    
    @Test
    public void testStringConversions() {

        // Define a string to test with 
        String testStr = "Hello, world!";
        
        // Allocate a DMA string
        @Pointer
        long str = allocateString(testStr);

        // Get the string length
        long len = strlen(str);

        // Convert it to a Java string
        String jStr = toJavaString(str, (int) len);

        // Ensure the strings are not the same object
        assertFalse(testStr == jStr);

        // Ensure the strings are equal value-wise
        assertEquals(testStr, jStr);

        // Clean up
        free(str);
    }

}