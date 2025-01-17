package SpringTest.ds_2024.repository;
import SpringTest.ds_2024.entity.PropertyApplication;
import SpringTest.ds_2024.entity.RentalApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyApplicationRepository extends JpaRepository<PropertyApplication, Integer>{
    List<PropertyApplication> findByApplicationStatus(String applicationStatus);
}
