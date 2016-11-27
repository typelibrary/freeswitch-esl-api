
freeswitch-esl-api
==============================================================================

[![Travis](https://travis-ci.org/typelibrary/freeswitch-esl-api.svg?branch=master)](https://travis-ci.org/typelibrary/freeswitch-esl-api)

[![codecov](https://codecov.io/gh/typelibrary/freeswitch-esl-api/branch/master/graph/badge.svg)](https://codecov.io/gh/typelibrary/freeswitch-esl-api)

**freeswitch-esl-api** is a Java based API for the Event Socket Library of the
[FreeSWITCH](https://freeswitch.org/) server.

Example
------------------------------------------------------------------------------

```java
package org.typelibrary.freeswitch.esl.sample;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.typelibrary.freeswitch.esl.EslCommand;
import org.typelibrary.freeswitch.esl.EslConnection;
import org.typelibrary.freeswitch.esl.EslInboundConnectionFactory;
import org.typelibrary.freeswitch.esl.EslMessage;

public class TestInboundConnection {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Implementation of your choice
        EslInboundConnectionFactory clientFactory = new MyEslInboundConnectionFactory();
        
        CompletableFuture<EslConnection> connFuture = clientFactory.createConnection("localhost", 8021, "ClueCon");
        
        EslConnection conn = connFuture.get();

        CompletableFuture<EslMessage> future;

        conn.observeEvents().subscribe((e) -> {
            System.out.println("Event received: " + e);
        }, (t) -> {
            System.out.println("Error received: " + t.getMessage());
        }, () -> {
            System.out.println("Done.");
        });

        future = conn.sendCommand(new EslCommand("log INFO"));
        future.whenComplete((m,t) -> {
            System.out.println("Response: " + m);
        });
        
        future = conn.sendCommand(new EslCommand("event plain ALL"));
        future.whenComplete((m,t) -> {
            System.out.println("Response: " + m);
        });

    }
    
}
```

Authors
------------------------------------------------------------------------------

- [TypeLibrary](mailto:git.user@typelibrary.org)

License
------------------------------------------------------------------------------

**freeswitch-esl-api** is licensed under the [Apache License, version 2](LICENSE).
