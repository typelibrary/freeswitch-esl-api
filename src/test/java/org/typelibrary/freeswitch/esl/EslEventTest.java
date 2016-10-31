package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EslEventTest {

    private EslHeaders headers;
    private String body;
    private EslEvent event;
    
    @Test
    public void testHeadersNull() {
        event = new EslEvent(EventName.API, headers);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals(EventName.API, event.getName());
        Assert.assertEquals("", event.getBody());
    }
    
    @Test
    public void testBodyNull() {
        event = new EslEvent(EventName.API, EslHeaders.EMPTY, body);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals(EventName.API, event.getName());
        Assert.assertEquals("", event.getBody());
    }

    @Test
    public void testHeadersAndBodyWithBodyNull() {
        headers = EslHeaders.EMPTY;
        event = new EslEvent(EventName.API, headers, body);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals(EventName.API, event.getName());
        Assert.assertEquals("", event.getBody());
    }

    @Test
    public void testHeadersAndBodyWithHeadersNull() {
        body = "content";
        event = new EslEvent(EventName.API, headers, body);
        Assert.assertEquals(2, event.getHeaders().size());
        Assert.assertEquals(EventName.API, event.getName());
        Assert.assertEquals(7, event.getHeaders().getContentLength());
        Assert.assertEquals("content", event.getBody());
    }

    @Test
    public void testHeadersAndBodyWithHeadersAndBodyNull() {
        event = new EslEvent(EventName.API, headers, body);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals(EventName.API, event.getName());
        Assert.assertEquals("", event.getBody());
    }

    @Before
    public void setUp() {
        headers = null;
        body = null;
    }

}
