package Ojakgyo.com.example.Ojakgyo.repository;

import Ojakgyo.com.example.Ojakgyo.domain.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LockerRepository extends JpaRepository<Locker, Long> {
    Optional<Locker> findById(Long lockerId);
    List<Locker> findAll();

    List<Locker> findByAddress(String address);
}
