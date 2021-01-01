package ca.retrylife.jdma;

import ca.retrylife.legalaccess.Accessor;
import sun.misc.Unsafe;

public class UnsafeAPI {

    // Instance var
    private static Unsafe instance = null;

    public static Unsafe getRaw() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {

        // Get an accessor for Unsafe
        Accessor<Unsafe> unsafeAccessor = new Accessor<>("theUnsafe", Unsafe.class, Unsafe.class);

        return unsafeAccessor.get();

    }

    public static Unsafe get() {
        if (instance == null) {
            try{
                instance = getRaw();
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
                throw new RuntimeException("This JVM does not allow access to sun.misc.Unsafe");
            }
        }

        return instance;
    }

}