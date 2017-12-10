package com.bureauveritas.springsandbox;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;




public class TestSimple {
	    @Test
	    public void testWithAsserts(){
		    assertEquals("TEST","TEST");
		    assertTrue(1==1);
		    assertFalse(1==2);
	    }
}
