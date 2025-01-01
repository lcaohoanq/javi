package com.lcaohoanq.advanced.reflection;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;
    private Double salary;
    private String name;
    private int age;
    private Integer grade;
    private Long money;

    public static void main(String[] args) {
        // Step 1: Generate a list of students
        List<Student> students = generateStudents(10);

        // Step 2: Print original students
        System.out.println("Original Students:");
        printStudents(students);

        // Step 3: Sort the students by multiple fields
//        List<SortCriterion> sortCriteria = List.of(
//                new SortCriterion("grade", SortOrder.DESC),
//                new SortCriterion("age", SortOrder.ASC),
//                new SortCriterion("salary", SortOrder.DESC)
//        );
//        SortUtils.sort(students, sortCriteria, Student.class);

        // single field sort
        SortUtils.sort(students, "id", SortOrder.DESC, Student.class);

        // Step 4: Print sorted students
        System.out.println("\nSorted Students (Grade DESC, Age ASC, Salary DESC):");
        printStudents(students);

        // Step 5: Generate a list of class entities using the students
        List<ClassEntity> classes = generateClasses(5, students);

        // Step 6: Print the original classes
        System.out.println("\nOriginal Classes:");
        classes.forEach(System.out::println);
    }

    // Helper methods for demo
    private static List<Student> generateStudents(int count) {
        Faker faker = new Faker();
        List<Student> students = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (long i = 1; i <= count; i++) {
            students.add(new Student(
                i,
                random.nextDouble(3000.0, 8000.0),
                faker.name().fullName(),
                random.nextInt(18, 25),
                random.nextInt(60, 100),
                random.nextLong(5000L, 100000L)
            ));
        }
        return students;
    }

    private static List<ClassEntity> generateClasses(int count, List<Student> allStudents) {
        Faker faker = new Faker();
        List<ClassEntity> classes = new ArrayList<>();

        for (long i = 1; i <= count; i++) {
            Collections.shuffle(allStudents);
            classes.add(new ClassEntity(
                i,
                faker.educator().course(),
                new ArrayList<>(allStudents.subList(0, 3))
            ));
        }
        return classes;
    }

    private static void printStudents(List<Student> students) {
        students.forEach(student -> System.out.printf(
            "ID: %d, Name: %-20s, Age: %d, Grade: %d, Salary: $%.2f, Money: $%d%n",
            student.getId(),
            student.getName(),
            student.getAge(),
            student.getGrade(),
            student.getSalary(),
            student.getMoney()
        ));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ClassEntity {

    private Long id;
    private String name;
    private List<Student> students;
}