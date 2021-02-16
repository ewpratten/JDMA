# Direct Memory Access for Java 
[![Documentation](https://img.shields.io/badge/-documentation-blue)](https://ewpratten.retrylife.ca/JDMA) ![Build library](https://github.com/Ewpratten/JDMA/workflows/Build%20library/badge.svg)

`jdma` is a Java library that provides c-like `malloc()` and `free()` functions, along with many others. All functions operate off-heap, meaning no need to deal with the garbage collector. This is useful when dealing with code that must be efficient as possible.

## Demo

```java
import static ca.retrylife.jdma.DMA.*;
import static ca.retrylife.jdma.DMAString.*;

// Allocate 50 bytes
@Pointer long bytes = malloc(50);

// Set the first half to 0
memset(bytes, (byte)0, 25);

// Set the second half to 20
memset(bytes + 25, (byte)20, 25);

// Free the bytes
free(bytes);

// Reuse the bytes pointer for a c-like string
bytes = allocateString("Hello, world!");

// Truths about the string
assert strlen(bytes) == 13;
assert toJavaString(bytes, strlen(bytes)).equals("Hello, world!");

// Free the string
free(bytes);
```

## Using in your project

**Step 1.** Add the RetryLife maven server to your `build.gradle` file:

```groovy
repositories {
    maven { url 'https://gpr.retrylife.ca/ewpratten/JDMA' }
}
```

**Step 1.** Add this library as a dependency:

```groovy
dependencies {
    implementation 'ca.retrylife:jdma:1.+'
    implementation 'ca.retrylife:jdma:1.+:sources'
    implementation 'ca.retrylife:jdma:1.+:javadoc'
}
```

## Notes

This library specifically **does not** use the JVM garbage collector, meaning you must `free()` everything when finished. All the same warnings that come with C programming apply here too. This library also comes with a `@Pointer` annotation, which has no effect on program execution, but just exists to visually flag values that are pointers in source code.
