package dao;

import model.Subscription;
import java.util.*;
import java.util.stream.Collectors;

public class SubscriptionDAO {

    private Map<String, Subscription> subscriptions = new HashMap<>();

    public Subscription create(Subscription subscription) {
        subscriptions.put(subscription.getId(), subscription);
        return subscription;
    }

    public Optional<Subscription> findById(String id) {
        return Optional.ofNullable(subscriptions.get(id));
    }

    public List<Subscription> findAll() {
        return new ArrayList<>(subscriptions.values());
    }

    public Subscription update(Subscription subscription) {
        subscriptions.put(subscription.getId(), subscription);
        return subscription;
    }

    public boolean delete(String id) {
        return subscriptions.remove(id) != null;
    }

    public List<Subscription> findActiveSubscriptions() {
        return subscriptions.values().stream()
                .filter(s -> s.getStatus() == Subscription.Status.Active)
                .collect(Collectors.toList());
    }

    public List<Subscription> findByType(Class<? extends Subscription> type) {
        return subscriptions.values().stream()
                .filter(type::isInstance)
                .collect(Collectors.toList());
    }
}