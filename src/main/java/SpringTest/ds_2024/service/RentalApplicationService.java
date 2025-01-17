package SpringTest.ds_2024.service;

import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.entity.RentalApplication;
import SpringTest.ds_2024.repository.PropertyRepository;
import SpringTest.ds_2024.repository.RentalApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalApplicationService {
    private final RentalApplicationRepository rentalApplicationRepository;
    private final PropertyRepository propertyRepository;

    public RentalApplicationService(RentalApplicationRepository rentalApplicationRepository, PropertyRepository propertyRepository) {
        this.rentalApplicationRepository = rentalApplicationRepository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public void saveRentalApplication(RentalApplication rentalApplication) {
        rentalApplicationRepository.save(rentalApplication);
    }

    @Transactional
    public void createAndSaveRentalApplication(Long propertyId, String applicationStatus, double rentalAmount) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));

        RentalApplication application = new RentalApplication();
        application.setProperty(property);

        application.setApplicationStatus(applicationStatus);
        application.setApplicationDate(LocalDate.now());
        application.setRentalAmount(rentalAmount);

        rentalApplicationRepository.save(application);
    }
}