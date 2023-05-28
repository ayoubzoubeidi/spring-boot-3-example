package com.maz.springboot3example.repository;

import com.maz.springboot3example.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(value = """
            SELECT CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT)
            FROM resources_bookings RB
            JOIN booking B on B.id = RB.booking_id
            WHERE RB.resource_id = ?1 AND  B.start_date_time < ?2 AND B.start_date_time > ?2
            """, nativeQuery = true)
    boolean isResourceAvailable(UUID resourceId, OffsetDateTime startDateTime);
}
