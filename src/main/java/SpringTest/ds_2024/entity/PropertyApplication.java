package SpringTest.ds_2024.entity;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class PropertyApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int applicationId;

    @Column
    private String applicationStatus;

    @Column
    private LocalDate applicationDate;

    public PropertyApplication(LocalDate applicationDate, String applicationStatus) {
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
    }
//
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "property_id")
    private Property property;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "property_id", nullable = true)
//    private Property property;

    public PropertyApplication() {
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "PropertyApplication{" +
                "applicationId=" + applicationId +
                ", applicationStatus='" + applicationStatus + '\'' +
                ", applicationDate=" + applicationDate +
                '}';
    }
}
