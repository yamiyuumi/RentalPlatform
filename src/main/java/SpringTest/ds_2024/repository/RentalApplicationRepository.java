package SpringTest.ds_2024.repository;
import SpringTest.ds_2024.entity.RentalApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalApplicationRepository extends JpaRepository<RentalApplication, Integer> {
}