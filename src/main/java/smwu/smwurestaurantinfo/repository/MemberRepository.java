package smwu.smwurestaurantinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smwu.smwurestaurantinfo.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
