package org.typelibrary.freeswitch.esl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EslHeadersTest {

    private EslHeaders headers;
    private List<String> names;
    private String value;

    @Test
    public void testEmpty() {
        headers = EslHeaders.EMPTY;
        names = headers.getNames();
        assertNames();
        Assert.assertEquals("", headers.toString());
    }

    @Test
    public void testEntry() {
        EslHeaderEntry entry = new EslHeaderEntry("name", "value");
        headers = new EslHeaders(entry);
        grabDetails("name");
        assertValueOnly("value");
        assertNames("name");
        Assert.assertEquals("name: value\n", headers.toString());
    }
    
    @Test
    public void testEntryList() {
        EslHeaderEntry entry1 = new EslHeaderEntry("name", "value");
        EslHeaderEntry entry2 = new EslHeaderEntry("name2", "value2");
        headers = new EslHeaders(Arrays.asList(entry1, entry2));
        grabDetails("name");
        assertValueOnly("value");
        assertNames("name", "name2");
        grabDetails("name2");
        assertValueOnly("value2");
        assertNames("name", "name2");
        Assert.assertEquals("name: value\nname2: value2\n", headers.toString());
    }

    @Test
    public void testEntryDup() {
        EslHeaderEntry entry1 = new EslHeaderEntry("name", "value");
        EslHeaderEntry entry2 = new EslHeaderEntry("name", "value2");
        headers = new EslHeaders(Arrays.asList(entry1, entry2));
        grabDetails("name");
        assertValueOnly("value");
        assertNames("name");
    }

    @Test
    public void testEntryDupCase() {
        EslHeaderEntry entry1 = new EslHeaderEntry("name", "value");
        EslHeaderEntry entry2 = new EslHeaderEntry("NAME", "value2");
        headers = new EslHeaders(Arrays.asList(entry1, entry2));
        grabDetails("name");
        assertValueOnly("value");
        assertNames("name");
    }

    @Test
    public void testValueNotExist() {
        headers = new EslHeaders("name", "value1");
        Assert.assertNull(headers.getValue("no-name"));
    }

    @Test
    public void testContains() {
        headers = new EslHeaders("name", "value");
        Assert.assertEquals(true, headers.contains("name"));
        Assert.assertEquals(false, headers.contains("naem"));
    }

    @Test
    public void testContentLength() {
        headers = EslHeaders.EMPTY;
        Assert.assertEquals(0, headers.getContentLength());

        headers = new EslHeaders("Content-Length", "");
        Assert.assertEquals(0, headers.getContentLength());

        headers = new EslHeaders("Content-Length", "One");
        Assert.assertEquals(0, headers.getContentLength());
        
        headers = new EslHeaders("Content-Length", "0");
        Assert.assertEquals(0, headers.getContentLength());

        headers = new EslHeaders("Content-Length", "1");
        Assert.assertEquals(1, headers.getContentLength());

    }

    @Test
    public void testOverwrite() {
        headers = new EslHeaders("name", "value1");
        Assert.assertEquals("value1", headers.getValue("name"));
        
        headers = headers.overwrite("name", "value2");
        Assert.assertEquals("value2", headers.getValue("name"));
    }

    @Test
    public void testCommandReply() {
        EslHeaderEntry entry1 = new EslHeaderEntry("Content-Type", "command/reply");
        EslHeaderEntry entry2 = new EslHeaderEntry("Reply-Text", "+OK");
        headers = new EslHeaders(Arrays.asList(entry1, entry2));
        Assert.assertEquals(true, headers.isOkCommandReply());

        entry1 = new EslHeaderEntry("Content-Type", "command/reply");
        entry2 = new EslHeaderEntry("Reply-Text", "+FAIL");
        headers = new EslHeaders(Arrays.asList(entry1, entry2));
        Assert.assertEquals(false, headers.isOkCommandReply());

        entry1 = new EslHeaderEntry("Content-Type", "command/not-reply");
        entry2 = new EslHeaderEntry("Reply-Text", "+OK");
        headers = new EslHeaders(Arrays.asList(entry1, entry2));
        Assert.assertEquals(false, headers.isOkCommandReply());

    }

    private void grabDetails(String name) {
        value = headers.getValue(name);
        names = headers.getNames();
    }

    private void assertValueOnly(String expectedValue) {
        Assert.assertNotNull(value);
        Assert.assertEquals(expectedValue, value);
    }

    private void assertNames(String... expectedNames) {
        Assert.assertEquals(Arrays.asList(expectedNames), names);
    }

}
