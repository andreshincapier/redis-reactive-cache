package co.com.rediscache.model.notification.gateways;

import reactor.core.publisher.Mono;

public interface NotificationGateway {

    Mono<Void> sendNotification(String message);
}
