package org.typelibrary.freeswitch.esl;

import java.util.Arrays;

public final class EslEvent {

    private final EslMessage message;

    public EslEvent(EventName eventName, EslHeaders headers) {
        this(eventName, headers, null);
    }
    
    public EslEvent(String eventSubclass, EslHeaders headers) {
        this(eventSubclass, headers, null);
    }
    
    public EslEvent(EventName eventName, EslHeaders headers, String body) {
        this.message = new EslMessage(
                (headers != null) ? headers.overwrite(EslConstants.HEADER_EVENT_NAME, eventName.name())
                        : new EslHeaders(EslConstants.HEADER_EVENT_NAME, eventName.name()),
                body);
    }

    public EslEvent(String eventSubclass, EslHeaders headers, String body) {
        this.message = new EslMessage((headers != null)
                ? headers.overwrite(EslConstants.HEADER_EVENT_NAME, EventName.CUSTOM.name())
                        .overwrite(EslConstants.HEADER_EVENT_SUBCLASS, eventSubclass)
                : new EslHeaders(
                        Arrays.asList(new EslHeaderEntry(EslConstants.HEADER_EVENT_NAME, EventName.CUSTOM.name()),
                                new EslHeaderEntry(EslConstants.HEADER_EVENT_SUBCLASS, eventSubclass))),
                body);
    }

    public EslEvent(EslHeaders headers) {
        this(headers, null);
    }

    public EslEvent(EslHeaders headers, String body) {
        this.message = new EslMessage(headers, body);
        if (!getHeaders().contains(EslConstants.HEADER_EVENT_NAME)) {
            throw new IllegalStateException("Event headers must contain header name");
        }
        EventName eventName = getName();
        if (getHeaders().contains(EslConstants.HEADER_EVENT_SUBCLASS) && eventName != EventName.CUSTOM) {
            throw new IllegalStateException("Event with subclass must have name CUSTOM. name=" + eventName);
        }
    }

    public EventName getName() {
        try {
            return EventName.valueOf(getHeaders().getValue(EslConstants.HEADER_EVENT_NAME));
        } catch (IllegalArgumentException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getSubClass() {
        try {
            return getHeaders().getValue(EslConstants.HEADER_EVENT_SUBCLASS);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
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
