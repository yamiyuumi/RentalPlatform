package SpringTest.ds_2024.repository;
import SpringTest.ds_2024.entity.Property;
import SpringTest.ds_2024.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
