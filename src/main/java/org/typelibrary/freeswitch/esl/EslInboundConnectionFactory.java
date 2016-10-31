package org.typelibrary.freeswitch.esl;

import java.util.concurrent.CompletableFuture;

public interface EslInboundConnectionFactory {

    CompletableFuture<EslConnection> createConnection(String host, int port, String password);

}
