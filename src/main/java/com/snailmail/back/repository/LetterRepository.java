package com.snailmail.back.repository;

import com.snailmail.back.domain.Letter;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    Optional<Letter> findLetterByReservationKey(String reservationKey);
}
