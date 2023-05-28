package com.maz.springboot3example.service;

import com.maz.springboot3example.domain.ApplicationUser;
import com.maz.springboot3example.domain.Booking;
import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.repository.ApplicationUserRepository;
import com.maz.springboot3example.repository.BookingRepository;
import com.maz.springboot3example.repository.ResourceRepository;
import com.maz.springboot3example.web.model.BookResourceGuest;
import com.maz.springboot3example.web.model.BookResourceRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ApplicationUserRepository applicationUserRepository;

    @Transactional
    @Override
    public UUID bookResources(BookResourceRequest bookResourceRequest) {

        var isAllResourcesAvailable = isAllResourcesAvailable(bookResourceRequest.resources(),
                bookResourceRequest.startDateTime());

        if (!isAllResourcesAvailable) {
            throw new RuntimeException("Resource Not Available");
        }

        var guests = getGuests(bookResourceRequest.guests());
        var resources = bookResourceRequest.resources()
                .stream()
                .map(resourceId -> Resource.builder().id(resourceId).build())
                .collect(Collectors.toSet());

        var booking = Booking.builder()
                .guests(guests)
                .resources(resources)
                .startDateTime(bookResourceRequest.startDateTime())
                .endDateTime(bookResourceRequest.endDateTime())
                .build();

        return bookingRepository.saveAndFlush(booking).getId();
    }

    private Set<ApplicationUser> getGuests(List<BookResourceGuest> bookResourceGuests) {
        return bookResourceGuests
                .stream()
                .map(bookResourceGuest -> applicationUserRepository
                        .findByEmailOrFirstNameOrLastName(
                                bookResourceGuest.email(),
                                bookResourceGuest.firstName(),
                                bookResourceGuest.lastName()
                        ).orElse(applicationUserRepository
                                .save(ApplicationUser
                                        .builder()
                                        .firstName(bookResourceGuest.firstName())
                                        .lastName(bookResourceGuest.lastName())
                                        .email(bookResourceGuest.email())
                                        .build())))
                .collect(Collectors.toSet());
    }

    private boolean isAllResourcesAvailable(List<UUID> resourcesIds, OffsetDateTime startDateTime) {
        return resourcesIds
                .stream()
                .anyMatch(resourceId ->
                        bookingRepository.isResourceAvailable(resourceId, startDateTime));
    }
}
