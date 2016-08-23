package org.tushar.projects.AdCampaign.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.eval.adcampaign.to.AdCampaign;

public class CommonUtils {
	private final static Logger logger = Logger.getLogger(CommonUtils.class);
	
	public static AdCampaign convertStringToJson(String input){
		logger.debug("entered getAdCampaignFromJson(String input)");
		AdCampaign adCampaign = null;
		try {
			JSONObject jsonObject = new JSONObject(input);
			logger.debug("partner_id : "+jsonObject.getString("partner_id")+" duration : "+jsonObject.getString("duration")+" ad_content : "+jsonObject.getString("ad_content"));
			System.out.println("partner_id : "+jsonObject.getString("partner_id")+" duration : "+jsonObject.getString("duration")+" ad_content : "+jsonObject.getString("ad_content"));
			
	    	adCampaign = new AdCampaign(
	    			jsonObject.getString("partner_id"),
	    			Integer.parseInt(jsonObject.getString("duration")),
	    			jsonObject.getString("ad_content"),
	    			new Date()
	    			);
		} catch (Exception e) {
			System.out.println("Error converting json: - ");
			e.printStackTrace();
		}	
		return adCampaign;			
	}	
	
    public static String surround(final Object o) {
        return "'" + o + "'";
    }

    public static boolean isEmpty(final String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isEmpty(final List l) {
        return l == null || l.isEmpty();
    }

    public static boolean isEmpty(final Object[] o) {
        return o == null || o.length == 0;
    }

    public static boolean isEmpty(final Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean notEmpty(final Collection c) {
        return c != null && !c.isEmpty();
    }

    public static boolean notEmpty(final String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static String generateToString(final Object obj) {
        if (obj == null)
            return null;

        final StringBuilder builder = new StringBuilder(
                obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1,
                        obj.getClass().getName().length()) + '['
        );

        final Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0)
                builder.append(", ");
            final Field f = fields[i];
            f.setAccessible(true);
            builder.append(f.getName());
            builder.append("=");
            try {
                if (f.getType().equals(Calendar.class))
                    builder.append(CommonUtils.formatDate(((Calendar) f.get(obj)).getTime()));
                else
                    builder.append(f.get(obj));
            } catch (final IllegalAccessException e) {
                logger.trace(e, e);
            }
        }
        builder.append(']');
        return builder.toString();
    }

    public static int size(final Collection c) {
        if (CommonUtils.isEmpty(c))
            return 0;

        return c.size();
    }

    public static String formatDate(final Date date) {
        return new SimpleDateFormat("MM/dd/yy HH:mm:ss a").format(date);
    }
    
    public static boolean isExpired(long inTime) {
        return inTime < Calendar.getInstance().getTimeInMillis();
    }  
    
    public static void main (String[] args) {
    	System.out.println("Hello World!");
    	
    	System.out.println("1: "+(new Date().getTime() + 200));
    	System.out.println("2: "+ Calendar.getInstance().getTimeInMillis());
    	
    	
    }

}
