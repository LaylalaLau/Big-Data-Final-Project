import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.fieldsel.FieldSelectionReducer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

import java.io.IOException;
import java.util.Locale;

public class SelectFieldsReducer extends FieldSelectionReducer {
    @Override
    public void reduce(Text key, Iterable values, Context context) throws IOException, InterruptedException {

        // System.out.println(key);
        String[] fields = key.toString().split(",");

        // String month = fields[0].split("/")[0];
        // String hour = fields[1].split(":")[0];
        // DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        // DateFormat dateFormat2 = new SimpleDateFormat("MM hh aa", Locale.ENGLISH);
        // DateFormat dateFormat2 = new SimpleDateFormat("MM KK aa", Locale.ENGLISH);
        // DateFormat dateFormat2 = new SimpleDateFormat("M K aa", Locale.ENGLISH);

        DateFormat dateFormatMonth = new SimpleDateFormat("M", Locale.ENGLISH);
        try {
            Date date = dateFormat1.parse(fields[0]);
            // Date date = dateFormat1.parse(fields[0] + ' ' + fields[1]);
            // String dateString = dateFormat2.format(date);
            // System.out.println("Current time in AM/PM: "+dateString);
            String monthString = dateFormatMonth.format(date);
            // String hourString = dateFormatHour.format(date);
            fields[0] = monthString;
            // fields[1] = hourString;
            // String newString = String.join(",", fields);
            // System.out.println(newString);
            // key = new Text(newString);
        } catch (ParseException e) {
            // e.printStackTrace();
        }

        dateFormat1 = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormatHour = new SimpleDateFormat("K aa", Locale.ENGLISH);
        try {
            Date date = dateFormat1.parse(fields[1]);
            String hourString = dateFormatHour.format(date);
            fields[1] = hourString;
        } catch (ParseException e) {
            // e.printStackTrace();
        }

        String newString = String.join(",", fields);
        key = new Text(newString);

        super.reduce(key, values, context);
    }
}
