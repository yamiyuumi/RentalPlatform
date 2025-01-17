package SpringTest.ds_2024.repository;

import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.entity.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByStatus(PropertyStatus status);
}
