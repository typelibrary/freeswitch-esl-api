package org.typelibrary.freeswitch.esl;

import java.util.concurrent.CompletableFuture;

import rx.Observable;

public interface EslConnection {

    CompletableFuture<EslMessage> sendCommand(EslCommand command);

    Observable<EslEvent> observeEvents();

    void shutdown();

}
