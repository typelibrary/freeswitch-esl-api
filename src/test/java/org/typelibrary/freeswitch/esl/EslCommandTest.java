package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EslCommandTest {

    private EslHeaders headers;
    private String body;
    private EslCommand command;

    @Test(expected=IllegalArgumentException.class)
    public void testCommandNull() {
        command = new EslCommand(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCommandEmpty() {
        command = new EslCommand("");
    }

    @Test
    public void testCommandOnly() {
        command = new EslCommand("command");
        Assert.assertEquals("command", command.getCommand());
        Assert.assertEquals(0, command.getHeaders().size());
        Assert.assertEquals("", command.getBody());
    }

    @Test
    public void testCommandWithHeadersNullAndBodyNull() {
        command = new EslCommand("command", headers, body);
        Assert.assertEquals("command", command.getCommand());
        Assert.assertEquals(0, command.getHeaders().size());
        Assert.assertEquals("", command.getBody());
    }

    @Test
    public void testCommandWithHeadersAndBodyNull() {
        headers = new EslHeaders("name", "value");
        command = new EslCommand("command", headers, body);
        Assert.assertEquals("command", command.getCommand());
        Assert.assertEquals(1, command.getHeaders().size());
        Assert.assertEquals("", command.getBody());
    }

    public void testCommandWithHeadersNullAndBody() {
        headers = new EslHeaders("name", "value");
        command = new EslCommand("command", headers, "content");
        Assert.assertEquals("command", command.getCommand());
        Assert.assertEquals(2, command.getHeaders().size());
        Assert.assertEquals("content", command.getBody());
        Assert.assertEquals(7, command.getHeaders().getContentLength());

    }

    @Before
    public void setUp() {
        headers = null;
        body = null;
    }

}
