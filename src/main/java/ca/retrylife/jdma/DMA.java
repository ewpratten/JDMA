package ca.retrylife.jdma;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import ca.retrylife.jdma.annotations.Pointer;
import sun.misc.Unsafe;

public class DMA {

    private static final Unsafe unsafe = UnsafeAPI.get();

    /**
     * Allocate SIZE bytes off-heap
     * 
     * @param size Bytes to allocate
     * @return Address of the first byte
     */
    public static @Pointer long malloc(long size) {
        return unsafe.allocateMemory(size);
    }

    /**
     * Free an allocated portion of memory
     * 
     * @param address Base address
     */
    public static void free(@Pointer long address) {
        unsafe.freeMemory(address);
    }

    /**
     * Copy N bytes from src to dest
     * 
     * @param dest Destination base address
     * @param src  Source base address
     * @param n    Number of bytes
     */
    public static void memcpy(@Pointer long dest, @Pointer long src, long n) {
        unsafe.copyMemory(src, dest, n);
    }

    public static int memcmp(@Pointer long a, @Pointer long b, long n) {
        for (int i = 0; i < n; i++, a++, b++) {
            if (unsafe.getByte(a) < unsafe.getByte(b)) {
                return -1;
            } else if (unsafe.getByte(a) > unsafe.getByte(b)) {
                return 1;
            }
        }

        return 0;
    }

    /**
     * Get the byte at an address
     * 
     * @param address Address
     * @return Value
     */
    public static byte peek(@Pointer long address) {
        return unsafe.getByte(address);
    }

    /**
     * Write a byte to an address
     * 
     * @param address Address
     * @param val     Value
     */
    public static void poke(@Pointer long address, byte val) {
        unsafe.putByte(address, val);
    }

    /**
     * Get the shallow size of an object
     * 
     * @param obj Object
     * @return Size in bytes
     */
    public static long sizeof(Object obj) {
        HashSet<Field> fields = new HashSet<Field>();

        // Ref to the object's direct class
        Class<?> clazz = obj.getClass();

        // Walk up the superclass list and grab all fields
        while (clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if ((field.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }

        // Get offset
        long maxSize = 0;
        for (Field field : fields) {
            long offset = unsafe.objectFieldOffset(field);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        // Return the padded size
        return ((maxSize / 8) + 1) * 8;
    }

}