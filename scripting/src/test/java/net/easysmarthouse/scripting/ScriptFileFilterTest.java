package net.easysmarthouse.scripting;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScriptFileFilterTest {

    @Test
    public void testAccept() throws Exception {
       System.out.println("***** accept *****");

        ScriptFileFilter filter = new ScriptFileFilter();
        assertTrue(filter.accept(null,  "script.js"));
        assertFalse(filter.accept(null, "abc.txt"));
    }
}