package org.typelibrary.freeswitch.esl;

/**
 * The event priorities supported by the ESL protocol. This class should be kept synchronized with the
 * values mapped in /src/switch_utils.c.
 * 
 * @see <a href="https://freeswitch.org/stash/projects/FS/repos/freeswitch/browse/src/switch_utils.c?at=v1.6" target="_parent">switch_utils.c (v1.6)</a>
 * @see <a href="https://freeswitch.org/stash/projects/FS/repos/freeswitch/browse/src/include/switch_types.h?at=v1.6" target="_parent">switch_types.h (v1.6)</a>
 * 
 */
public enum EventPriority {
    NORMAL,
    LOW,
    HIGH;
}
