package org.typelibrary.freeswitch.esl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EslHeadersTest {

    private EslHeaders headers;
    private List<String> names;
    private List<String> values;
    private String value;

    @Test
    public void testEmpty() {
        headers = EslHeaders.EMPTY;
        names = headers.getNames();
        assertNames();
    }

    @Test
    public void testEntry() {
        EslHeaderEntry entry = new EslHeaderEntry("name", "value");
        headers = new EslHeaders(entry);
        grabDetails("name");
        assertValueOnly("value");
        assertNames("name");
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
    
    private void grabDetails(String name) {
        value = headers.getValue(name);
        values = headers.getArray(name);
        names = headers.getNames();
    }

    private void assertValueOnly(String expectedValue) {
        Assert.assertNotNull(value);
        Assert.assertEquals(expectedValue, value);
        Assert.assertNull(values);
    }

    private void assertNames(String... expectedNames) {
        Assert.assertEquals(Arrays.asList(expectedNames), names);
    }

}
