package ca.retrylife.jdma;

import ca.retrylife.legalaccess.Accessor;
import sun.misc.Unsafe;

/**
 * A reflective wrapper for sun.misc.Unsafe
 */
public class UnsafeAPI {

    // Instance var
    private static Unsafe instance = null;

    /**
     * Use reflection to get the global instance of Unsafe
     * 
     * @return Unsafe
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Unsafe getRaw()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        // Get an accessor for Unsafe
        Accessor<Unsafe> unsafeAccessor = new Accessor<>("theUnsafe", Unsafe.class, Unsafe.class);

        return unsafeAccessor.get();

    }

    /**
     * A safer way to get the global instance of Unsafe
     * 
     * @return Unsafe
     */
    public static Unsafe get() {
        if (instance == null) {
            try {
                instance = getRaw();
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
                throw new RuntimeException("This JVM does not allow access to sun.misc.Unsafe");
            }
        }

        return instance;
    }

}