package org.typelibrary.freeswitch.esl;

/**
 * The event types supported by the ESL protocol. This class should be kept synchronized with the
 * esl_format_t enum and format2str mapping function in /src/mod/event_handlers/mod_event_socket/mod_event_socket.c.
 * 
 * 
 * @see <a href="https://freeswitch.org/stash/projects/FS/repos/freeswitch/browse/src/mod/event_handlers/mod_event_socket/mod_event_socket.c?at=v1.6" target="_parent">mod_event_socket.c (v1.6)</a>
 * @see <a href="https://freeswitch.org/stash/projects/FS/repos/freeswitch/browse/src/switch_event.c?at=v1.6" target="_parent">switch_event.c (v1.6)</a>
 * 
 */
public enum EventType {
    Plain,
    Xml,
    JSON;
}
