package com.jswiftdev.news.utils;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;

public class Utils {
    public static boolean isEmailAddressValid(String emailAddress) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return emailAddress.matches(emailPattern);
    }


    public static String getRelativeTime(String dateToFind) {
        DateTime dateNow = DateTime.now();
        DateTime dateToCompare = (DateTimeFormat.forPattern(C.SERVER_DATE_FORMAT)).parseDateTime(dateToFind);

        int seconds, minutes, hours, days;

        Duration duration = new Duration(dateToCompare, dateNow);
        seconds = (int) duration.getStandardSeconds();
        minutes = (int) duration.getStandardMinutes();
        hours = (int) duration.getStandardHours();
        days = (int) duration.getStandardDays();

        if (seconds < 15)
            return "Just Now";

        if (seconds >= 15 && seconds < 60) {
            return seconds + " seconds ago";
        }

        if (minutes >= 0 && minutes < 60) {
            if (minutes == 1)
                return minutes + " minute ago";

            return minutes + " minutes ago";
        }


        if (minutes >= 60 && hours < 24)
            return hours + " hours ago";

        if (hours == 24 || days == 1)
            return "Yesterday";

        if (days > 1)
            return days + " days ago";

        if (days >= 6)
            return days / 7 + " weeks ago";

        if ((days / 7) > 4)
            return days / 30 + " months ago";

        Log.d(C.LOG_TAG, "Failed " + dateToFind + " hours -> " + hours + "\tdays -> " + days);
        return "Not applicable";
    }
}