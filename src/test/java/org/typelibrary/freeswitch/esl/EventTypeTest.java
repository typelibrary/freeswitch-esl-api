package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Test;


public class EventTypeTest {

    @Test
    public void testLabels() {
        Assert.assertEquals(EventType.Plain, EventType.valueOf("Plain"));
    }
    
}
