package fr.marketsim.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PUBLIC)
public class Account {
    private Long id;
    private UUID publicId;
    private BigDecimal balance;
}
