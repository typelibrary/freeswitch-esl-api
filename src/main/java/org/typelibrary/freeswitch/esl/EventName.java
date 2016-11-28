package org.typelibrary.freeswitch.esl;

import java.util.Locale;

/**
 * The event names supported by the ESL protocol. This class should be kept synchronized with the
 * EVENT_NAMES array in /src/switch_event.c.
 * 
 * @see <a href="https://freeswitch.org/stash/projects/FS/repos/freeswitch/browse/src/switch_event.c?at=v1.6" target="_parent">switch_event.c (v1.6)</a>
 * 
 */
public enum EventName {

    CUSTOM,
    CLONE,
    CHANNEL_CREATE,
    CHANNEL_DESTROY,
    CHANNEL_STATE,
    CHANNEL_CALLSTATE,
    CHANNEL_ANSWER,
    CHANNEL_HANGUP,
    CHANNEL_HANGUP_COMPLETE,
    CHANNEL_EXECUTE,
    CHANNEL_EXECUTE_COMPLETE,
    CHANNEL_HOLD,
    CHANNEL_UNHOLD,
    CHANNEL_BRIDGE,
    CHANNEL_UNBRIDGE,
    CHANNEL_PROGRESS,
    CHANNEL_PROGRESS_MEDIA,
    CHANNEL_OUTGOING,
    CHANNEL_PARK,
    CHANNEL_UNPARK,
    CHANNEL_APPLICATION,
    CHANNEL_ORIGINATE,
    CHANNEL_UUID,
    API,
    LOG,
    INBOUND_CHAN,
    OUTBOUND_CHAN,
    STARTUP,
    SHUTDOWN,
    PUBLISH,
    UNPUBLISH,
    TALK,
    NOTALK,
    SESSION_CRASH,
    MODULE_LOAD,
    MODULE_UNLOAD,
    DTMF,
    MESSAGE,
    PRESENCE_IN,
    NOTIFY_IN,
    PRESENCE_OUT,
    PRESENCE_PROBE,
    MESSAGE_WAITING,
    MESSAGE_QUERY,
    ROSTER,
    CODEC,
    BACKGROUND_JOB,
    DETECTED_SPEECH,
    DETECTED_TONE,
    PRIVATE_COMMAND,
    HEARTBEAT,
    TRAP,
    ADD_SCHEDULE,
    DEL_SCHEDULE,
    EXE_SCHEDULE,
    RE_SCHEDULE,
    RELOADXML,
    NOTIFY,
    PHONE_FEATURE,
    PHONE_FEATURE_SUBSCRIBE,
    SEND_MESSAGE,
    RECV_MESSAGE,
    REQUEST_PARAMS,
    CHANNEL_DATA,
    GENERAL,
    COMMAND,
    SESSION_HEARTBEAT,
    CLIENT_DISCONNECTED,
    SERVER_DISCONNECTED,
    SEND_INFO,
    RECV_INFO,
    RECV_RTCP_MESSAGE,
    CALL_SECURE,
    NAT,
    RECORD_START,
    RECORD_STOP,
    PLAYBACK_START,
    PLAYBACK_STOP,
    CALL_UPDATE,
    FAILURE,
    SOCKET_DATA,
    MEDIA_BUG_START,
    MEDIA_BUG_STOP,
    CONFERENCE_DATA_QUERY,
    CONFERENCE_DATA,
    CALL_SETUP_REQ,
    CALL_SETUP_RESULT,
    CALL_DETAIL,
    DEVICE_STATE,
    ALL;
    
    public static EventName parseEventName(String name) {
        if (name == null) {
            return null;
        }
        try {
            return valueOf(name.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
