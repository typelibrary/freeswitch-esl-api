package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Test;


public class EventNameTest {

    @Test
    public void testLabels() {
        Assert.assertEquals(EventName.API, EventName.valueOf("API"));
    }
    
}
