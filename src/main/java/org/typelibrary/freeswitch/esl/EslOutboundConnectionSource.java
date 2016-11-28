package org.typelibrary.freeswitch.esl;

import rx.Observable;

/**
 * The source from which EslConnections originating from a Freeswitch server are received
 * by application code.
 *
 */
public interface EslOutboundConnectionSource {

    Observable<EslConnection> observeConnections();

}
