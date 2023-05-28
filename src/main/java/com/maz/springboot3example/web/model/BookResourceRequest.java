package com.maz.springboot3example.web.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record BookResourceRequest(List<UUID> resources, UUID booker,
                                  List<BookResourceGuest> guests, OffsetDateTime startDateTime,
                                  OffsetDateTime endDateTime) {
}
