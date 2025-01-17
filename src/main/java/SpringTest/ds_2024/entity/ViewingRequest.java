package SpringTest.ds_2024.entity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ViewingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int requestId;

    @Column
    private String requestStatus;

    @Column
    private LocalDate requestDate;

    @Column
    private LocalDate viewingDate;

    public ViewingRequest(String requestStatus, LocalDate requestDate, LocalDate viewingDate) {
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
        this.viewingDate = viewingDate;
    }

    public ViewingRequest() {
    }

    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getViewingDate() {
        return viewingDate;
    }

    public void setViewingDate(LocalDate viewingDate) {
        this.viewingDate = viewingDate;
    }

    @Override
    public String toString() {
        return "ViewingRequest{" +
                "requestId=" + requestId +
                ", requestStatus='" + requestStatus + '\'' +
                ", requestDate=" + requestDate +
                ", viewingDate=" + viewingDate +
                '}';
    }
}
