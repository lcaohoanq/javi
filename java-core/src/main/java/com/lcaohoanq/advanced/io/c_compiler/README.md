- You need to use -shared to create a shared library, -o to specify the output file name (e.g., libsum.so), and -fPIC to generate position-independent code. Make sure the path to your Java headers (jni.h) is correct.

```zsh
gcc -shared -fPIC \
  -I/usr/lib/jvm/java-17-openjdk-amd64/include \
  -I/usr/lib/jvm/java-17-openjdk-amd64/include/linux \
  -o libsum.so \
  /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/sum.c
```
- After this above command, you will have a shared library named libsum.so in the current directory. You can now use this library in your Java code.

```zsh
# Compile
javac /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/Sum.java

# Run
java -Djava.library.path=/media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler \
-cp /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java com.lcaohoanq.advanced.io.c_compiler.Sum
```

- The output should be:

```zsh
/media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core git:[master]
java -Djava.library.path=/media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler \
-cp /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java com.lcaohoanq.advanced.io.c_compiler.Sum
Result of 5 + 3 = 8
```