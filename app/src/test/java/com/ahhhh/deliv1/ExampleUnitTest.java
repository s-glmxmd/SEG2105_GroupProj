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
    public void service_provider_test1() {
        ServiceProvider s1 = new ServiceProvider("user", "pass", "John", "doe");
        s1.setCompanyName("Company");
        assertEquals("Actual vs expected", "Company", s1.getCompanyName());
    }

    @Test
    public void service_provider_test2() {
        ServiceProvider s1 = new ServiceProvider("user", "pass", "John", "doe");
        s1.setLicenced(true);
        assertEquals("Actual vs expected", true, s1.isLicenced());
    }
}