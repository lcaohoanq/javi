package com.lcaohoanq.jdk.src.main.java.com.lcaohoanq.java11;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MyFileProcessing {
    final static String LOCATION = "src/main/java/com/lcaohoanq/jdk/src/main/resources/";
    final static String FILE_PATH = "src/main/java/com/lcaohoanq/jdk/src/main/resources/test.txt";
    final static String FILE_PATH2 = "src/main/java/com/lcaohoanq/jdk/src/main/resources/test2.txt";
    final static String FILE_C = "src/main/java/com/lcaohoanq/jdk/src/main/resources/sum.c";
    final static String FILE_C2 = "src/main/java/com/lcaohoanq/jdk/src/main/resources/newFile.c";
    
    static class ReadFileExample{
        public static void main(String[] args) throws IOException {
            Path path = Path.of(FILE_PATH);
            String content = Files.readString(path);
            System.out.println(content);
            
            Path pathC = Path.of(FILE_C);
            String contentC = Files.readString(pathC);
            System.out.println(contentC);
            
            Path pathC2 = Path.of(FILE_C2);
            String contentC2 = Files.readString(pathC2);
            System.out.println(contentC2);
            
            if(contentC.equals(contentC2)){
                System.out.println("Equal");
            } else {
                System.out.println("Not equal");
            }
        }
    }

    static class WriteFileExample{
        public static void main(String[] args) throws IOException {
            Path path = Path.of(LOCATION + "newFile.txt");
            String content = "Hello Java 11";
            Files.writeString(path, content, StandardOpenOption.CREATE);
            
            Path pathC = Path.of(LOCATION + "newFile.c");
            String contentC = """
                #include <jni.h>
                
                JNIEXPORT jint JNICALL Java_com_lcaohoanq_advanced_io_c_1compiler_Sum_sum
                  (JNIEnv *env, jobject obj, jint a, jint b) {
                    printf("Hello from C\\n");
                    return a + b;
                }
                """;
            Files.writeString(pathC, contentC, StandardOpenOption.CREATE);
        }
    }

    static class ReadLineExample{
        public static void main(String[] args) throws IOException {
            Path path = Path.of(FILE_PATH);
            Files.readAllLines(path).forEach(System.out::println);
        }
    }

    static class WriteLineExample{
        public static void main(String[] args) throws IOException {
            Path path = Path.of(FILE_PATH);
            List<String> lines = List.of("Welcome", "to", "hell");
            Files.write(path, lines, StandardOpenOption.APPEND);
        }
    }

    static class BufferReaderExample{
        public static void main(String[] args) throws IOException {
            Path path = Path.of(FILE_PATH);
            Files.newBufferedReader(path).lines().forEach(System.out::println);
        }
    }

    static class BufferWriterExample {
        public static void main(String[] args) throws IOException {
            Path path = Path.of(FILE_PATH);
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.write("\nHahahahahahahhahahah");
            }
        }
    }

    static class FileMismatchExample {
        public static void main(String[] args) throws IOException {
            Path path1 = Path.of(FILE_PATH);
            Path path2 = Path.of("src/main/resources/test2.txt");
            //different content: return 1
            //same content: return -1
            System.out.println(Files.mismatch(path1, path2));

            System.out.println("Different at: " + Files.mismatch(path1, path2));
        }
    }

}
