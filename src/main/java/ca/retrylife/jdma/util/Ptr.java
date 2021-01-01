package ca.retrylife.jdma.util;

import ca.retrylife.jdma.DMA;
import ca.retrylife.jdma.annotations.Pointer;

/**
 * A safe wrapper around pointers
 */
public class Ptr implements AutoCloseable {

    private final @Pointer long address;
    private boolean open = true;

    /**
     * Wrap a unsafe Pointer with a Ptr
     * 
     * @param address address to point to
     */
    public Ptr(@Pointer long address) {
        this.address = address;
    }

    /**
     * Calculate this address with an offset
     * 
     * @param offset Positive offset
     * @return New address
     */
    public final long withOffset(long offset) {

        if (!this.open) {
            throw new IllegalStateException("Cannot use a freed pointer");
        }

        return this.address + offset;
    }

    /**
     * Get the address this pointer points to
     * 
     * @return Address
     */
    public final @Pointer long get() {
        if (!this.open) {
            throw new IllegalStateException("Cannot use a freed pointer");
        }

        return this.address;
    }

    @Override
    public void close() {
        DMA.free(this.address);
        this.open = false;
    }

    /**
     * Free the pointer
     */
    public void free() {
        close();
    }

}