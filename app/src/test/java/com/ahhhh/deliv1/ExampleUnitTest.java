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

    @Test
    public void rating_test() {
        Rating rating1 = new Rating(5, "Very good service provider.");
        int numRating = rating1.getNumericRating();
        assertEquals("Actual vs expected", numRating, 5);
    }

    @Test
    public void rating_test2() {
        Rating rating1 = new Rating(5, "Very good service provider.");
        String comment = rating1.getComment();
        assertEquals("Actual vs expected", comment, "Very good service provider.");
    }

    @Test
    public void rating_test3() {
        Rating rating1 = new Rating(5, "Very good service provider.");
        rating1.setNumericRating(4);
        assertEquals("Actual vs expected", rating1.getNumericRating(), 4);

        //setComment
    }

    @Test
    public void rating_test4() {
        Rating rating1 = new Rating(5, "Very good service provider.");
        String res = rating1.validateRating();
        assertEquals("Actual vs expected", res, "");
        //validateRating

    }

    @Test
    public void rating_test5() {
        Rating rating1 = new Rating(555555563, "Very good service provider.");
        String res = rating1.validateRating();
        assertEquals("Actual vs expected", res, "Please rate the service between 1 and 5 (inclusive)");

        //validateRating

    }


    @Test
    public void booking_test() {
        Booking booking = new Booking("saturday", null);
        String day = booking.getWeekday();
        assertEquals("Actual vs expected", day, "saturday");
    }

    @Test
    public void booking_test2() {
        Booking booking = new Booking("saturday", null);
        Service service = booking.getService();
        assertEquals("Actual vs expected", service, null);
    }

    @Test
    public void booking_test3() {
        Booking booking = new Booking("saturday", null);
        Rating rating1 = new Rating(5, "Very good service provider.");
        booking.setHomeownerRating(rating1);
        Service service = booking.getService();
        assertEquals("Actual vs expected", booking.getHomeownerRating(), rating1);
    }

    @Test
    public void booking_test4() {
        Booking booking = new Booking("saturday", null);
        Rating rating1 = new Rating(5, "Very good service provider.");
        booking.setHomeownerRating(null);
        Service service = booking.getService();
        assertEquals("Actual vs expected", booking.getHomeownerRating(), null);
    }

    @Test
    public void booking_test5() {
        Booking booking = new Booking(null, null);
        String res = booking.getWeekday();
        assertEquals("Actual vs expected", res, null);
    }












}