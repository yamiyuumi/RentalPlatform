package SpringTest.ds_2024.service;

import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.entity.PropertyApplication;
import SpringTest.ds_2024.repository.PropertyRepository;
import SpringTest.ds_2024.repository.PropertyApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PropertyApplicationService {

    private final PropertyApplicationRepository propertyApplicationRepository;
    private final PropertyRepository propertyRepository;

    public PropertyApplicationService(PropertyApplicationRepository propertyApplicationRepository, PropertyRepository propertyRepository) {
        this.propertyApplicationRepository = propertyApplicationRepository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public void createAndSavePropertyApplication(Property property) {
//        propertyRepository.save(property);


        PropertyApplication application = new PropertyApplication();
        application.setProperty(property);
        application.setApplicationStatus("Pending");
        application.setApplicationDate(LocalDate.now());

        propertyApplicationRepository.save(application);

//        propertyRepository.deleteById(property.getPropertyId());
    }

    @Transactional
    public void approveApplication(int id) {
        PropertyApplication application = propertyApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        Property property = application.getProperty();

        propertyRepository.save(property);

        propertyApplicationRepository.delete(application);

    }

    @Transactional
    public void rejectApplication(int id) {
        PropertyApplication application = propertyApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        propertyApplicationRepository.delete(application);
    }

    public List<PropertyApplication> getAllPendingApplications() {
        return propertyApplicationRepository.findByApplicationStatus("Pending");
    }
    @Transactional
    public void deletePropertyApplication(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}