package com.basu.utils;

import android.text.format.DateFormat;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CalenderUtil {
    /*static ArrayList<String> daysList = new ArrayList<>();
    public static List<SessionCalender> getAllDates(String start_date, String end_date){
        List<SessionCalender> requiredDates = new ArrayList<>();
        try {
            daysList.add("Sun");
            daysList.add("Mon");
            daysList.add("Tue");
            daysList.add("Wed");
            daysList.add("Thu");
            daysList.add("Fri");
            daysList.add("Sat");

            List<Date> datesInRange = new ArrayList<>();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(start_date));
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(end_date));

            while (calendar.before(endCalendar)) {
                Date result = calendar.getTime();
                datesInRange.add(result);
                calendar.add(Calendar.DATE, 1);
            }

            Log.e("datesInRange + ",""+datesInRange.size());
            for(int i=0; i<datesInRange.size(); i++) {
                //Log.e("datesInRange + ", "" + datesInRange.get(i).getDate()+ " "+datesInRange.get(i).getDay()+" "+datesInRange.get(i).getMonth()+" "+datesInRange.get(i).getYear());

                String date = String.valueOf(datesInRange.get(i).getDate());
                if(datesInRange.get(i).getDate() > 0 && datesInRange.get(i).getDate() < 10){
                    date = "0"+datesInRange.get(i).getDate();
                }

                String month = String.valueOf(datesInRange.get(i).getMonth()+1);
                if((datesInRange.get(i).getMonth()+1) > 0 && (datesInRange.get(i).getMonth()+1) < 10){
                    month = "0"+(datesInRange.get(i).getMonth()+1);
                }

                String fulldate = date+"-"+month+"-20"+datesInRange.get(i).getYear();
                Log.e("fulldate ",""+fulldate);
                SessionCalender sessionCalender = new SessionCalender();
                sessionCalender.setFulldate(fulldate);
                sessionCalender.setDays(daysList.get(datesInRange.get(i).getDay()));
                sessionCalender.setMonth(String.valueOf(datesInRange.get(i).getMonth()+1));
                sessionCalender.setYear("20"+datesInRange.get(i).getYear());
                requiredDates.add(sessionCalender);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return requiredDates;
    }

    public static Date getDate(String datetobeformated, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(datetobeformated);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Calendar startDate = Calendar.getInstance();
//        startDate.setTime(date);
        return date;
    }


    public static String getDateFromCharSequence(Calendar date) {
        final StringBuilder sb = new StringBuilder(DateFormat.format("dd-MM-yyyy", date).length());
        sb.append(DateFormat.format("dd-MM-yyyy", date));
        return sb.toString();
    }*/
}
