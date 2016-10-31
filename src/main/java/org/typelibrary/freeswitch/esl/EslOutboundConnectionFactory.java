package org.typelibrary.freeswitch.esl;

import rx.Observable;

public interface EslOutboundConnectionFactory {

    Observable<EslConnection> observeConnections(int port);
    
    Observable<EslConnection> observeConnections(String host, int port);

}
