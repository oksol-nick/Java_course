package phone.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<PersonContact, Long> {

    Optional<PersonContact> findById(long id);
    void deleteById(long id);

    @Modifying
    @Transactional
    @Query("update PersonContact p set p.phoneNumber = :newPhoneNumber where p.id = :id")
    void updatePhoneNumber(@Param("id") long id, @Param("newPhoneNumber") String newPhoneNumber);

    @Modifying
    @Transactional
    @Query("update PersonContact p set p.email = :newEmail where p.id = :id")
    void updateEmail(@Param("id") long id, @Param("newEmail") String newEmail);
}
