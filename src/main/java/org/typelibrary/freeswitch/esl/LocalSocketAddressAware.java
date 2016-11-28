package org.typelibrary.freeswitch.esl;

import java.net.SocketAddress;

/**
 * Optional interface for EslConnections that are aware of the local SocketAddress to which
 * they are bound to.
 *
 */
public interface LocalSocketAddressAware {

    SocketAddress getLocalSocketAddress();
    
}
