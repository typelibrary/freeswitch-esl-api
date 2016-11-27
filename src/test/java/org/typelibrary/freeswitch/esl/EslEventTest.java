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
        event = new EslEvent("API", null, headers, null);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals("API", event.getName());
        Assert.assertEquals("", event.getBody());
    }
    
    @Test
    public void testBodyNull() {
        event = new EslEvent("API", null, EslHeaders.EMPTY, body);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals("API", event.getName());
        Assert.assertEquals("", event.getBody());
    }

    @Test
    public void testHeadersAndBodyWithBodyNull() {
        headers = EslHeaders.EMPTY;
        event = new EslEvent("API", null, headers, body);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals("API", event.getName());
        Assert.assertEquals("", event.getBody());
    }

    @Test
    public void testHeadersAndBodyWithHeadersNull() {
        body = "content";
        event = new EslEvent("API", null, headers, body);
        Assert.assertEquals(2, event.getHeaders().size());
        Assert.assertEquals("API", event.getName());
        Assert.assertEquals(7, event.getHeaders().getContentLength());
        Assert.assertEquals("content", event.getBody());
    }

    @Test
    public void testHeadersAndBodyWithHeadersAndBodyNull() {
        event = new EslEvent("API", null, headers, body);
        Assert.assertEquals(1, event.getHeaders().size());
        Assert.assertEquals("API", event.getName());
        Assert.assertEquals("", event.getBody());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNameNull() {
        event = new EslEvent(null, null, headers, body);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNameEmpty() {
        event = new EslEvent("", null, headers, body);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCustom() {
        event = new EslEvent("API", "my event", headers, body);
    }
    
    @Before
    public void setUp() {
        headers = null;
        body = null;
    }

}
