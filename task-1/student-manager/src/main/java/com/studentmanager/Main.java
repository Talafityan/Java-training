
package com.studentmanager;

public class Main {

    public static void main(String[] args) {

        StudentService service = new StudentService();

        System.out.println("All Students:");
        service.getAll().forEach(System.out::println);

        System.out.println("\nCreating Students:");
        Student student1 = service.create("Tala", "tala@gmail.com", 21);
        Student student2 = service.create("Sara", "sara@gmail.com", 22);

        System.out.println(student1);
        System.out.println(student2);

        System.out.println("\nAll Students After Create:");
        service.getAll().forEach(System.out::println);

        System.out.println("\nGet Student By ID:");
        Student student = service.getById(student1.getId());
        System.out.println(student);

        System.out.println("\nUpdate Student:");
        boolean updated = service.update(
                student1.getId(), "Tala Hussam", "tala@gmail.com", 22
        );
        System.out.println(updated ? "Student updated." : "Student not found.");

        service.getAll().forEach(System.out::println);

        System.out.println("\nDelete Student:");
        boolean deleted = service.delete(student2.getId());
        System.out.println(deleted ? "Student deleted." : "Student not found.");

        service.getAll().forEach(System.out::println);

        System.out.println("\nTesting Non-Existing ID:");
        Student missing = service.getById(99999L);
        System.out.println(missing == null ? "Student not found." : missing);

        boolean missingDelete = service.delete(99999L);
        System.out.println(missingDelete ? "Student deleted." : "Student not found.");
    }
}
