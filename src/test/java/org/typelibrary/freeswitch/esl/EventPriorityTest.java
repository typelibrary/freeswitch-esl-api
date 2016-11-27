package org.typelibrary.freeswitch.esl;

import org.junit.Assert;
import org.junit.Test;


public class EventPriorityTest {

    @Test
    public void testLabels() {
        Assert.assertEquals(EventPriority.NORMAL, EventPriority.valueOf("NORMAL"));
    }
    
}
