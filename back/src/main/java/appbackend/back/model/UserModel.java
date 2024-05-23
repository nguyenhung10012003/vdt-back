package appbackend.back.model;

import jakarta.persistence.*;
import org.apache.catalina.User;


@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private int birth;
    private String email;
    private String phone;
    private String gender;
    private String school;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserModel() {
    }

    public void updateUser(UserModel user) {
        this.name = user.getName();
        this.birth = user.getBirth();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.school = user.getSchool();
    }

    public UserModel(String firstname, int birth, String email, String phone, String gender, String school, int id, String country) {
        this.name = firstname;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.school = school;
        this.country = country;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstname) {
        this.name = firstname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }
}