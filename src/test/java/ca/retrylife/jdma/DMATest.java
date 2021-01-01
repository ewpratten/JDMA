package ca.retrylife.jdma;

import static ca.retrylife.jdma.DMA.*;
import static ca.retrylife.jdma.DMAString.*;
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

    // @Test
    // public void testMemcmp() {

    //     // Allocate two strings to compare
    //     @Pointer
    //     long str1 = allocateString("DWgaOtP12df0");
    //     @Pointer
    //     long str2 = allocateString("DWGAOTP12DF0");

    //     assertEquals(-1, DMA.memcmp(str1, str2, strlen(str1)));

    // }

}