# Notification Service

A lightweight, extensible notification service built in Java. It supports multiple delivery channels (Email, SMS, Push), asynchronous processing via a queue, and the Observer pattern for event tracking.

## Features

- **Multi-channel support** — Send notifications via Email, SMS, or Push (extensible)
- **Async processing** — Queue-based architecture for non-blocking delivery
- **Observer pattern** — Subscribe to delivery events (success/failure) for logging, metrics, or auditing
- **Factory pattern** — Pluggable senders per channel type
- **Clean separation** — Models, services, senders, queue, and observers are clearly separated

## Project Structure

```
notification-service/
├── README.md
└── src/com/notification/
    ├── Main.java                    # Entry point
    ├── models/                      # Domain models
    │   ├── ChannelType.java         # EMAIL, SMS, PUSH
    │   ├── Notification.java        # Core notification payload
    │   └── NotificationEvent.java  # Delivery event (success/failure)
    ├── services/                    # Business logic
    │   ├── NotificationService.java
    │   └── NotificationSenderFactory.java
    ├── senders/                     # Channel-specific senders
    │   ├── NotificationSender.java  # Interface
    │   └── EmailNotificationSender.java
    ├── queue/                       # Queue abstraction
    │   ├── NotificationQueue.java   # Interface
    │   └── InMemoryNotificationQueue.java
    ├── observers/                   # Event observers
    │   ├── NotificationObserver.java
    │   └── LoggingObserver.java
    └── exceptions/
        ├── NotificationException.java
        ├── NotificationDeliveryException.java
        └── UnsupportedChannelException.java
```

## How to Run

From the `notification-service` directory:

```bash
cd notification-service
mkdir -p out
javac -d out src/com/notification/Main.java src/com/notification/models/*.java \
  src/com/notification/exceptions/*.java src/com/notification/senders/*.java \
  src/com/notification/queue/*.java src/com/notification/observers/*.java \
  src/com/notification/services/*.java
java -cp out com.notification.Main
```

## Usage Example

```java
// 1. Register senders for each channel
NotificationSenderFactory factory = new NotificationSenderFactory(
    Map.of(ChannelType.EMAIL, new EmailNotificationSender())
);

// 2. Add observers (e.g., for logging)
List<NotificationObserver> observers = List.of(new LoggingObserver());

// 3. Create service and queue
NotificationService service = new NotificationService(factory, observers);
NotificationQueue queue = new InMemoryNotificationQueue();

// 4. Publish a notification
queue.publish(new Notification(
    "1",
    "user@example.com",
    "Welcome!",
    ChannelType.EMAIL
));
```

## Extending the Service

### Add a new channel (e.g., SMS)

1. Implement `NotificationSender`:

```java
public class SmsNotificationSender implements NotificationSender {
    @Override
    public void send(Notification notification) {
        // SMS delivery logic
    }
}
```

2. Register it in the factory:

```java
Map.of(
    ChannelType.EMAIL, new EmailNotificationSender(),
    ChannelType.SMS, new SmsNotificationSender()
)
```

### Add a custom observer

Implement `NotificationObserver` and pass it to `NotificationService`:

```java
public class MetricsObserver implements NotificationObserver {
    @Override
    public void onEvent(NotificationEvent event) {
        // Record metrics, send to monitoring system, etc.
    }
}
```

## Design Patterns Used

| Pattern | Where |
|--------|-------|
| **Factory** | `NotificationSenderFactory` — returns the right sender for a channel |
| **Observer** | `NotificationObserver` — notified on delivery success/failure |
| **Strategy** | `NotificationSender` — different send strategies per channel |
| **Producer-Consumer** | `NotificationQueue` — async processing with blocking queue |

## Requirements

- Java 11 or later
