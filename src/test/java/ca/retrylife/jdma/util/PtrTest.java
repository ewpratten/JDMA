package ca.retrylife.jdma.util;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import static ca.retrylife.jdma.DMA.*;

public class PtrTest {

    @Test
    public void testAllocAndFree() {

        // Create a ptr
        Ptr p = new Ptr(malloc(2));

        assertNotEquals("Address not NULL/0", 0, p.get());

        // Free
        p.close();
    }

}