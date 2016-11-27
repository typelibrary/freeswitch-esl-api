package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Test;

public class EslHeaderEntryTest {

    private EslHeaderEntry entry;

    @Test
    public void testBasic() {
        entry = new EslHeaderEntry("name", "value");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("value", entry.getValue());
    }

    @Test
    public void testValueUnProcessing() {
        entry = new EslHeaderEntry("name", "  value");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("  value", entry.getValue());
        entry = new EslHeaderEntry("name", "\t\tvalue");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("\t\tvalue", entry.getValue());
        entry = new EslHeaderEntry("name", " \tvalue");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals(" \tvalue", entry.getValue());
        entry = new EslHeaderEntry("name", "\t value");
        Assert.assertEquals("name", entry.getName());
        Assert.assertEquals("\t value", entry.getValue());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPlainConsNullName() {
        entry = new EslHeaderEntry(null, "value1");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPlainConsIllegalName() {
        entry = new EslHeaderEntry("na:me", "value1");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPlainConsNullValue() {
        entry = new EslHeaderEntry("key1", (String) null);
    }

    @Test
    public void testEquals() {
        entry = new EslHeaderEntry("key1", "value1");
        Assert.assertEquals(true, entry.equals(entry));
        Assert.assertEquals(false, entry.equals(null));
        Assert.assertEquals(false, entry.equals("key1"));

        EslHeaderEntry other1 = new EslHeaderEntry("key1", "value2");
        Assert.assertEquals(true, entry.equals(other1));

        EslHeaderEntry other2 = new EslHeaderEntry("key2", "value2");
        Assert.assertEquals(false, entry.equals(other2));
    }

    @Test
    public void testHashCode() {
        entry = new EslHeaderEntry(new String("key1"), "value1");
        
        EslHeaderEntry other1 = new EslHeaderEntry(new String("key1"), "value2");
        Assert.assertEquals(entry.hashCode(), other1.hashCode());

        EslHeaderEntry other2 = new EslHeaderEntry("KEY1", "value3");
        Assert.assertEquals(entry.hashCode(), other2.hashCode());

        EslHeaderEntry other3 = new EslHeaderEntry("key2", "value3");
        Assert.assertNotEquals(entry.hashCode(), other3.hashCode());
    }

}
