package smwu.smwurestaurantinfo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smwu.smwurestaurantinfo.domain.tag.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    public Optional<Tag> findByName(String name);
}
