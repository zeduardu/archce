package dev.arch420x0.archce.infrastructure.shortbus;

/**
 * Defines a handler for all notification.
 * 
 * @param <T> Notification
 */
public interface NotificationHandler<T> {
	/**
	 * Handles all notification
	 * 
	 * @param notification notification parameter
	 */
	public void handle(T notification);
}
