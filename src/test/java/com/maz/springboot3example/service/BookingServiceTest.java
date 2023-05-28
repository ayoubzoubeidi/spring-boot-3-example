package com.maz.springboot3example.service;

import com.maz.springboot3example.domain.ApplicationUser;
import com.maz.springboot3example.domain.Booking;
import com.maz.springboot3example.repository.ApplicationUserRepository;
import com.maz.springboot3example.repository.BookingRepository;
import com.maz.springboot3example.web.model.BookResourceGuest;
import com.maz.springboot3example.web.model.BookResourceRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookingServiceTest {

    AutoCloseable closeable;
    BookingService bookingService;
    @Mock
    BookingRepository bookingRepository;
    @Mock
    ApplicationUserRepository applicationUserRepository;

    UUID BOOKER = UUID.randomUUID();
    UUID BOOKING_RETURN_ID = UUID.randomUUID();

    List<UUID> RESOURCES = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    List<BookResourceGuest> GUESTS = List.of(
            new BookResourceGuest("John", "Doe", "john.doe@example.com"),
            new BookResourceGuest("Jane", "Smith", "jane.smith@example.com")
    );

    OffsetDateTime START_DATE_TIME = OffsetDateTime.now();
    OffsetDateTime END_DATE_TIME = START_DATE_TIME.plusDays(2);
    BookResourceRequest BOOK_RESOURCE_REQUEST = new BookResourceRequest(RESOURCES, BOOKER, GUESTS, START_DATE_TIME, END_DATE_TIME);


    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        bookingService = new BookingServiceImpl(bookingRepository, applicationUserRepository);

        // given

        BOOK_RESOURCE_REQUEST.guests()
                .forEach(guest -> when(applicationUserRepository
                        .findByEmailOrFirstNameOrLastName(guest.email(), guest.firstName(), guest.lastName()))
                        .thenReturn(
                                Optional.of(ApplicationUser.builder().id(UUID.randomUUID())
                                        .firstName(guest.firstName()).lastName(guest.lastName())
                                        .build()))
                );
        when(bookingRepository.saveAndFlush(any())).thenAnswer(i -> {
            var booking = (Booking) i.getArguments()[0];
            booking.setId(BOOKING_RETURN_ID);
            return booking;
        });
    }

    @Test
    public void createBookingHappyPathTest() {

        // given
        when(bookingRepository.isResourceAvailable(any(), any())).thenReturn(true);

        //when
        UUID bookingId = bookingService.bookResources(BOOK_RESOURCE_REQUEST);

        //then
        assertEquals(BOOKING_RETURN_ID, bookingId);

    }

    @Test
    public void createWhenBookingResourceUnAvailable() {

        // given
        when(bookingRepository.isResourceAvailable(any(), any())).thenReturn(false);

        //then
        assertThrows(RuntimeException.class, () -> bookingService.bookResources(BOOK_RESOURCE_REQUEST));

    }

    @AfterEach
    public void destroy() throws Exception {
        closeable.close();
    }

}
