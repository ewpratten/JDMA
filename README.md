# Direct Memory Access for Java [![Documentation](https://img.shields.io/badge/-documentation-blue)](https://ewpratten.retrylife.ca/JDMA) ![Build library](https://github.com/Ewpratten/JDMA/workflows/Build%20library/badge.svg)

`jdma` is a Java library that provides c-like `malloc()` and `free()` functions, along with many others. All functions of `jdma` operate off-heap, meaning no need to deal with the garbage collector. This is useful when dealing with code that muse be efficient as possible.

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
    implementation 'ca.retrylife:JDMA:1.+'
    implementation 'ca.retrylife:JDMA:1.+:sources'
    implementation 'ca.retrylife:JDMA:1.+:javadoc'
}
```