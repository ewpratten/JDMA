package ca.retrylife.jdma;

import ca.retrylife.jdma.annotations.Pointer;

public class DMAString {

    /**
     * Allocates a Java string as an off-heap char array
     * 
     * @param str Java string
     * @return Base pointer
     */
    public static @Pointer long allocateString(String str) {

        // Get String as bytes
        byte[] bytes = str.getBytes();

        // Allocate the needed memory + "\0"
        @Pointer
        long str_ptr = DMA.malloc(bytes.length + 1);

        // Copy the String bytes into memory
        for (int i = 0; i < bytes.length; i++) {
            DMA.poke(str_ptr + i, bytes[i]);
        }

        // Null terminate
        DMA.poke(str_ptr + bytes.length, (byte) '\0');

        return str_ptr;
    }

    /**
     * Get a portion of memory as a Java String
     * 
     * @param address Base address
     * @param size    Number of characters to read
     * @return Java String
     */
    public static String toJavaString(@Pointer long address, int size) {

        // Get the dma string as a byte array
        byte[] strBytes = DMA.getByteArray(address, size);

        // Build a Java string
        String output = new String(strBytes);

        return output;
    }

    /**
     * Get the length of a DMA string
     * 
     * @param address Base address of string
     * @return Length in bytes
     */
    public static long strlen(@Pointer long address) {
        // TODO: This wont play well with unicode
        long i = 0;
        while (true) {
            if (DMA.peek(address) == (byte) '\0') {
                return i;
            }

            i++;
            address++;
        }
    }

}