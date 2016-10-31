package org.typelibrary.freeswitch.esl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EslHeaderEntryTest {

    private EslHeaderEntry entry;

    @Test
    public void testBasic() {
        entry = new EslHeaderEntry("name", "value");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("value", entry.getValue());
        Assert.assertNull(entry.getArray());
    }

    @Test
    public void testStripValue() {
        entry = new EslHeaderEntry("name", "  value");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("value", entry.getValue());
        Assert.assertNull(entry.getArray());
        entry = new EslHeaderEntry("name", "\t\tvalue");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("value", entry.getValue());
        Assert.assertNull(entry.getArray());
        entry = new EslHeaderEntry("name", " \tvalue");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("value", entry.getValue());
        Assert.assertNull(entry.getArray());
        entry = new EslHeaderEntry("name", "\t value");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("value", entry.getValue());
        Assert.assertNull(entry.getArray());
    }

    @Test
    public void testArray() {
        entry = new EslHeaderEntry("name", Arrays.asList("v1", "v2"));
        Assert.assertEquals("name", entry.getName());
        Assert.assertNull(entry.getValue());
        Assert.assertEquals(Arrays.asList("v1", "v2"), entry.getArray());
    }

    @Test
    public void testArrayParse() {

        entry = new EslHeaderEntry("name", "ARRAY::value");
        Assert.assertEquals("name", entry.getName());
        Assert.assertNull(entry.getValue());
        Assert.assertEquals(Arrays.asList("value"), entry.getArray());

        entry = new EslHeaderEntry("name", "ARRAY::value|:value1");
        Assert.assertEquals("name", entry.getName());
        Assert.assertNull(entry.getValue());
        Assert.assertEquals(Arrays.asList("value", "value1"), entry.getArray());

        entry = new EslHeaderEntry("name", "ARRAY::value|:|:value1");
        Assert.assertEquals("name", entry.getName());
        Assert.assertNull(entry.getValue());
        Assert.assertEquals(Arrays.asList("value", "", "value1"), entry.getArray());

    }

    @Test(expected=UnsupportedOperationException.class)
    public void testImmutableArray() {
        List<String> arrayIn = new ArrayList<>();
        arrayIn.add("v1");
        arrayIn.add("v2");
        entry = new EslHeaderEntry("name", arrayIn);
        Assert.assertEquals("name", entry.getName());
        Assert.assertNull(entry.getValue());
        List<String> array = entry.getArray();
        array.add("v3");
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testImmutableArrayParse() {
        entry = new EslHeaderEntry("name", "ARRAY::v1|:v2");
        Assert.assertEquals("name", entry.getName());
        Assert.assertNull(entry.getValue());
        List<String> array = entry.getArray();
        array.add("v3");
    }

}
