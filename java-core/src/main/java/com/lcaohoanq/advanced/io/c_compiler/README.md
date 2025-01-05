- You need to use -shared to create a shared library, -o to specify the output file name (e.g., libsum.so), and -fPIC to generate position-independent code. Make sure the path to your Java headers (jni.h) is correct.

```zsh
gcc -shared -fPIC \
  -I/usr/lib/jvm/java-17-openjdk-amd64/include \
  -I/usr/lib/jvm/java-17-openjdk-amd64/include/linux \
  -o libsum.so \
  /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/sum.c
```

- Time measurement:

```zsh
time gcc -shared -fPIC \
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

# Compile with many Java file relate

- Compile all Java files in the current directory and its subdirectories:

```zsh
javac \
    /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/model/Submission.java \
    /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/model/Testcase.java \
    /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/Sum.java
```

- Compile all Java files in the current directory and its subdirectories and all the generated class files will be saved in the target directory:

```zsh
# Compile all Java files in the current directory and its subdirectories and all the generated class files will be saved in the target directory
javac -d /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/target \
    /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/model/Submission.java \
    /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/model/Testcase.java \
    /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/Sum.java

# Run
java -Djava.library.path=/media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler \
-cp /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/target \
com.lcaohoanq.advanced.io.c_compiler.Sum
```

- After that you can run the Java program as follows:

```zsh
# Change to the target directory
cd /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/target/com/lcaohoanq/advanced/io/c_compiler

java -Djava.library.path=/media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler \
-cp /media/lcaohoanq/SSD-DATA/PROJECT/Java-Issues/java-core/src/main/java/com/lcaohoanq/advanced/io/c_compiler/target \
com.lcaohoanq.advanced.io.c_compiler.Sum
```

- The output should be:

```zsh
Compiling C code...
C code compiled successfully in 40 ms
Test case results:
Test Case 961eca68-1a4e-4c37-be08-977550f7cf79: PASSED (Execution time: 0 ms)
Test Case 57dedc29-2f57-49b8-9d43-a702d9dc821c: PASSED (Execution time: 0 ms)
Test Case ab522ad8-cb41-409a-9d24-0012ca2de7f1: PASSED (Execution time: 0 ms)
Test Case 00c619ed-6c1b-4282-8b8a-05caa3536db9: PASSED (Execution time: 0 ms)

Test cases passed: 4/4
Submission status: ACCEPTED
```