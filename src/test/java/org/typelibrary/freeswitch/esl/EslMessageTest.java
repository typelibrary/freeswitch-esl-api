package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EslMessageTest {

    private EslHeaders headers;
    private String body;
    private EslMessage msg;
    
    public void testHeadersNull() {
        msg = new EslMessage(headers);
        Assert.assertEquals(0, msg.getHeaders().size());
        Assert.assertEquals("", msg.getBody());
    }
    
    public void testBodyNull() {
        msg = new EslMessage(body);
        Assert.assertEquals(0, msg.getHeaders().size());
        Assert.assertEquals("", msg.getBody());
    }

    @Test
    public void testHeadersAndBodyWithBodyNull() {
        headers = EslHeaders.EMPTY;
        msg = new EslMessage(headers, body);
        Assert.assertEquals(0, msg.getHeaders().size());
        Assert.assertEquals("", msg.getBody());
    }

    public void testHeadersAndBodyWithHeadersNull() {
        body = "content";
        msg = new EslMessage(headers, body);
        Assert.assertEquals(1, msg.getHeaders().size());
        Assert.assertEquals("content", msg.getBody());
        Assert.assertEquals(7, headers.getContentLength());
    }

    public void testHeadersAndBodyWithHeadersAndBodyNull() {
        msg = new EslMessage(headers, body);
        Assert.assertEquals(0, msg.getHeaders().size());
        Assert.assertEquals("", msg.getBody());
    }

    @Before
    public void setUp() {
        headers = null;
        body = null;
    }
}
