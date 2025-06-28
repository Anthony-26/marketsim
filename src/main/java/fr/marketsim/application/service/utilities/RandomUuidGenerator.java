package fr.marketsim.application.service.utilities;

import java.util.UUID;

public class RandomUuidGenerator implements UuidGenerator {

    @Override
    public UUID generateUuid() {
         return UUID.randomUUID();
    }

}
