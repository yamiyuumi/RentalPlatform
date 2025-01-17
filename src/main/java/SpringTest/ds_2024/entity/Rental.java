package SpringTest.ds_2024.entity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int rentalId;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column
    private LocalDate rentalStartDate;

    @Column
    private LocalDate rentalEndDate;

    @Column
    private double rentalAmount;

    @Column
    private String status;

    public Rental(LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }

    public Rental() {
    }

    // Getters and Setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public double getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", tenant=" + tenant +
                ", property=" + property +
                ", rentalStartDate=" + rentalStartDate +
                ", rentalEndDate=" + rentalEndDate +
                ", rentalAmount=" + rentalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
