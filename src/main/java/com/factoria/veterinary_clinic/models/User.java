// package com.factoria.veterinary_clinic.models;

// public class User {

//     private String name;
//     private int age;
//     private String breed;
//     private String gender;
//     private String idNumber;
//     private String guardianFirstName;
//     private String guardianLastName;
//     private String guardianPhoneNumber;

//     public User(String name, int age, String breed, String gender, String idNumber, String guardianFirstName, String guardianLastName, String guardianPhoneNumber) {
//         this.name = name;
//         this.age = age;
//         this.breed = breed;
//         this.gender = gender;
//         this.idNumber = idNumber;
//         this.guardianFirstName = guardianFirstName;
//         this.guardianLastName = guardianLastName;
//         this.guardianPhoneNumber = guardianPhoneNumber;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public int getAge() {
//         return age;
//     }

//     public void setAge(int age) {
//         this.age = age;
//     }

//     public String getBreed() {
//         return breed;
//     }

//     public void setBreed(String breed) {
//         this.breed = breed;
//     }

//     public String getGender() {
//         return gender;
//     }

//     public void setGender(String gender) {
//         this.gender = gender;
//     }

//     public String getIdNumber() {
//         return idNumber;
//     }

//     public void setIdNumber(String idNumber) {
//         this.idNumber = idNumber;
//     }

//     public String getGuardianFirstName() {
//         return guardianFirstName;
//     }

//     public void setGuardianFirstName(String guardianFirstName) {
//         this.guardianFirstName = guardianFirstName;
//     }

//     public String getGuardianLastName() {
//         return guardianLastName;
//     }

//     public void setGuardianLastName(String guardianLastName) {
//         this.guardianLastName = guardianLastName;
//     }

//     public String getGuardianPhoneNumber() {
//         return guardianPhoneNumber;
//     }

//     public void setGuardianPhoneNumber(String guardianPhoneNumber) {
//         this.guardianPhoneNumber = guardianPhoneNumber;
//     }

//     public void displayInformation() {
//         System.out.println("Name: " + name);
//         System.out.println("Age: " + age);
//         System.out.println("Breed: " + breed);
//         System.out.println("Gender: " + gender);
//         System.out.println("ID Number: " + idNumber);
//         System.out.println("Guardian's First Name: " + guardianFirstName);
//         System.out.println("Guardian's Last Name: " + guardianLastName);
//         System.out.println("Guardian's Phone Number: " + guardianPhoneNumber);
//     }

//     public static void main(String[] args) {
//         User user1 = new User("Zeus", 2, "Dog", "Male", "1", "Kari", "Tovar", "605965874");
//         user1.displayInformation();
//     }
// }

package com.factoria.veterinary_clinic.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    protected User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}