package SpringTest.ds_2024.entity;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class RentalApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int applicationId;

    @Column
    private String applicationStatus;

    @Column
    private LocalDate applicationDate;

    @Column
    private double rentalAmount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "property_id")
    private Property property;

    public RentalApplication(String applicationStatus, LocalDate applicationDate, double rentalAmount) {
        this.applicationStatus = applicationStatus;
        this.applicationDate = applicationDate;
        this.rentalAmount = rentalAmount;
    }


    public RentalApplication() {
    }

    // Getters and Setters
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public double getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "RentalApplication{" +
                "applicationId=" + applicationId +
                ", applicationStatus='" + applicationStatus + '\'' +
                ", applicationDate=" + applicationDate +
                ", rentalAmount=" + rentalAmount +
                '}';
    }
}
