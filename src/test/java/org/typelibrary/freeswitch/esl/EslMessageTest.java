package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EslMessageTest {

    private EslHeaders headers;
    private String body;
    private EslMessage msg;
    
    @Test
    public void testHeadersNull() {
        msg = new EslMessage(headers);
        Assert.assertEquals(0, msg.getHeaders().size());
        Assert.assertEquals("", msg.getBody());
    }
    
    @Test
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

    @Test
    public void testHeadersAndBodyWithHeadersNull() {
        body = "content";
        msg = new EslMessage(headers, body);
        Assert.assertEquals(1, msg.getHeaders().size());
        Assert.assertEquals("content", msg.getBody());
        Assert.assertEquals(7, msg.getHeaders().getContentLength());
    }

    @Test
    public void testHeadersAndBodyWithHeadersAndBodyNull() {
        msg = new EslMessage(headers, body);
        Assert.assertEquals(0, msg.getHeaders().size());
        Assert.assertEquals("", msg.getBody());
    }

    @Test
    public void testToString() {
        msg = EslMessage.EMPTY;
        Assert.assertEquals("", msg.toString());
        msg = new EslMessage(new EslHeaders("name", "value"));
        Assert.assertEquals("name: value\n", msg.toString());
        msg = new EslMessage("body");
        Assert.assertEquals("Content-Length: 4\n\nbody", msg.toString());
    }

    @Before
    public void setUp() {
        headers = null;
        body = null;
    }
}
