package creational.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Demo {

    public static void main(String[] args) {

        var student = Student.builder()
                .name("John Doe")
                .age(20)
                .address("123 Main St")
                .phoneNumber("123-456-7890")
                .build();

        System.out.println("Student Name: " + student.getName());

    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student{

        private String name;
        private int age;
        private String address;
        private String phoneNumber;

    }

}
