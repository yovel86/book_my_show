package com.projects.bookmyshow.repositories;

import com.projects.bookmyshow.models.Show;
import com.projects.bookmyshow.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

    Optional<ShowSeat> findById(int showSeatId);

//    @Lock(LockModeType.PESSIMISTIC_WRITE) // FOR UPDATE (Setting lock for READ operation)
//    List<ShowSeat> findShowSeatByIdInAndSeatStatus_AvailableAndShow(List<Integer> showSeatIds, Show show);

}
