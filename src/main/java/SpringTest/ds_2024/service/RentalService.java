package SpringTest.ds_2024.service;
import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.entity.PropertyStatus;
import SpringTest.ds_2024.entity.Rental;
import SpringTest.ds_2024.entity.RentalApplication;
import SpringTest.ds_2024.repository.RentalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import SpringTest.ds_2024.repository.RentalApplicationRepository;

import java.util.List;

@Service
public class RentalService {
    private RentalRepository rentalRepository;
    private final RentalApplicationRepository rentalApplicationRepository;

    public RentalService(RentalRepository rentalRepository, RentalApplicationRepository rentalApplicationRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalApplicationRepository = rentalApplicationRepository;
    }

    @Transactional
    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    @Transactional
    public Rental getRental(Integer rental_id) {
        return rentalRepository.findById(rental_id).get();
    }

    @Transactional
    public void saveRental(Rental rental){
        Property property = rental.getProperty();
        property.setStatus(PropertyStatus.RENTED);
        rentalRepository.save(rental);
    }

    public List<RentalApplication> getAllRentalApplications() {
        return rentalApplicationRepository.findAll();
    }

    public RentalApplication getRentalApplicationById(int id) {
        return rentalApplicationRepository.findById(id).orElse(null);
    }

    public void deleteRentalApplication(RentalApplication application) {
        rentalApplicationRepository.delete(application);
    }

}
