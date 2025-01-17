package SpringTest.ds_2024.entity;

import java.util.List;
import java.util.Set;
import jakarta.persistence.*;

@Entity
//@DiscriminatorValue("Tenant")
public class Tenant extends User{

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    //@Column
    //private List<Rental> rentals;



    public Tenant(String username, String password, String email, Set<Roles> roles,
                  String firstName, String lastName, String phoneNumber, List<Rental> rentals) {
        super(username, password, email, roles);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        //this.rentals = rentals;

    }
    public Tenant(String username, String password, String email,
                  String firstName, String lastName, String phoneNumber) {
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }

    public Tenant() {
        }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public List<Rental> getRentals() {
//        return rentals;
//    }
//
//    public void setRentals(List<Rental> rentals) {
//        this.rentals = rentals;
//    }


    public void viewRentalHistory() {
        System.out.println("Viewing rental history for " + getUsername());
    }

    public void makeRental() {
        System.out.println("Making a rental...");
    }

    public void searchProperty(String filters) {
        System.out.println("Searching for properties with filters: " + filters);
    }

    public void cancelRental(int rentalId) {
        System.out.println("Cancelling rental with ID: " + rentalId);
    }

    public void submitViewingRequest() {
        System.out.println("Submitting a viewing request.");
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
