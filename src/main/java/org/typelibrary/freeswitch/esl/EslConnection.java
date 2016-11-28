package org.typelibrary.freeswitch.esl;

import java.util.concurrent.CompletableFuture;

import rx.Observable;

/**
 * The interface between application code and a Freeswitch server via the ESL protocol.
 * 
 */
public interface EslConnection {

    CompletableFuture<EslMessage> sendCommand(EslCommand command);

    Observable<EslEvent> observeEvents();

    void shutdown();

}
