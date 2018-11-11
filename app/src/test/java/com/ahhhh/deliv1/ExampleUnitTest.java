package com.ahhhh.deliv1;

import org.junit.Test;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void service_getters_and_setters() {
        Service s1 = new Service("Plumbing" , 15.0);
        String actual = s1.getServiceName();
        String expected = "Plumbing";
        assertEquals("Actual vs expected", actual, expected );

        Service s2 = new Service("Woodworking", 20.0);

        assertEquals("Comparing hourly rates", s1.getHourlyRate(), 15.0, 0.001);


        assertEquals("Testing field validation", s1.validateEnteredInfo(), null);
        Service s3 = new Service("blah", "not a number");
        assertEquals("Testing default value for instance variable", s3.getServiceName(), "blah");
        assertEquals("Testing default value of hourlyRate", s3.getHourlyRate(), -1.0, 0.001);
    }
}