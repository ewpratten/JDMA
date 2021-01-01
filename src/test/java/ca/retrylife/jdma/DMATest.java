package ca.retrylife.jdma;

import static ca.retrylife.jdma.DMA.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import ca.retrylife.jdma.annotations.Pointer;

public class DMATest {

    @Test
    public void testMalloc() {

        // Allocate a byte
        @Pointer
        long addr = malloc(1);

        assertNotEquals("Address not NULL/0", 0, addr);

        free(addr);
    }

    @Test
    public void testMemcpy() {

        // Allocate 10 source bytes and make them all ones
        @Pointer
        long src = malloc(10);
        memset(src, (byte) 1, 10);

        // Allocate 5 dest bytes and make them all zeros
        @Pointer
        long dest = malloc(5);
        memset(dest, (byte) 0, 5);

        // Copy 4 bytes
        memcpy(dest, src, 4);

        // Check that the first 4 are ones
        assertEquals("First byte is 1", 1, peek(dest + 0));
        assertEquals("Second byte is 1", 1, peek(dest + 1));
        assertEquals("Third byte is 1", 1, peek(dest + 2));
        assertEquals("Fourth byte is 1", 1, peek(dest + 3));

        // Check that the last is a zero
        assertEquals("Fifth byte is 0", 0, peek(dest + 4));

        // Clean up
        free(src);
        free(dest);

    }

    @Test
    public void testMemcmp() {

        // Allocate two arrays
        @Pointer
        long set1 = malloc(5);
        @Pointer
        long set2 = malloc(5);

        // Fill the arrays with values, ensuring set1&lt;set2
        for (int i = 0; i < 5; i++) {
            poke(set1 + i, (byte) i);
        }
        for (int i = 0; i < 5; i++) {
            poke(set2 + i, (byte) (i + 1));
        }

        // Check the comparison
        assertEquals(-1, DMA.memcmp(set1, set2, 5));

        // Clean up
        free(set1);
        free(set2);

    }

}