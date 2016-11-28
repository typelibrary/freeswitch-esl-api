package org.typelibrary.freeswitch.esl;

/**
 * An event sent from a Freeswitch server to application code.
 * 
 */
public final class EslEvent {

    private final EslMessage message;

    public EslEvent(String eventName, String eventSubclass, EslHeaders headers, String body) {
        if (eventName == null || eventName.isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }
        if (eventSubclass != null && !"CUSTOM".equals(eventName)) {
            throw new IllegalArgumentException("Event with subclass must have name CUSTOM. name=" + eventName);
        }
        if (headers == null) {
            headers = EslHeaders.EMPTY;
        }
        headers = headers.overwrite(EslConstants.HEADER_EVENT_NAME, eventName);
        headers = headers.overwrite(EslConstants.HEADER_EVENT_SUBCLASS, eventSubclass);
        this.message = new EslMessage(headers, body);
    }

    public String getName() {
        return getHeaders().getValue(EslConstants.HEADER_EVENT_NAME);
    }

    public String getSubclass() {
        return getHeaders().getValue(EslConstants.HEADER_EVENT_SUBCLASS);
    }

    public EslHeaders getHeaders() {
        return message.getHeaders();
    }

    public String getBody() {
        return message.getBody();
    }

}
