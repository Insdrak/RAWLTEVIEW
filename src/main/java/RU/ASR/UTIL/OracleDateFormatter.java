package RU.ASR.UTIL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aoi on 27.01.2017.
 */
public class OracleDateFormatter {

    protected SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    protected SimpleDateFormat oracledf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.s");
    protected SimpleDateFormat oracledfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
    protected SimpleDateFormat reportdf = new SimpleDateFormat("dd.MM.yyyy");

    public Date formatDate (Date pDate){
        if (pDate != null){
            try {
                return df.parse(df.format(pDate));
            } catch (ParseException e) {
                e.printStackTrace();
                return new Date();
            }
        }
        else {
            try {
                return df.parse(df.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
                return new Date();
            }
        }
    }
    public Date getDatefromstring (String pDate){
        try {
            @SuppressWarnings("unused")
            Date tstdate = df.parse(pDate);
            return df.parse(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public Date getDatefrombase (String pDate){
        try {
            return oracledfout.parse(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("cannot parse date from base");
            return new Date();
        }
    }
    public String getReportDateFromString(String pDate){
        try {
            Date d = oracledfout.parse(pDate);
            return reportdf.format(d);
        } catch (Exception e) {
            return "ERROR PARSING DATE";
        }
    }

    public String getReportPreciseDateFromString(String pDate){
        try {
            Date d = oracledfout.parse(pDate);
            return df.format(d);
        } catch (Exception e) {
            return "ERROR PARSING DATE";
        }
    }

    public Date getReportdate (String pDate){
        try {
            return reportdf.parse(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("cannot parse date from base");
            return new Date();
        }
    }
}
