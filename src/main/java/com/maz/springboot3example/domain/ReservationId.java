package com.maz.springboot3example.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReservationId implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID resourceId;
    private UUID applicationUserId;
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
}
