package org.typelibrary.freeswitch.esl;

import java.util.concurrent.CompletableFuture;

/**
 * The factory by which to create EslConnections originating from application code to a
 * Freeswitch server.
 *
 */
public interface EslInboundConnectionFactory {

    CompletableFuture<EslConnection> createConnection(String host, int port, String password);

}
