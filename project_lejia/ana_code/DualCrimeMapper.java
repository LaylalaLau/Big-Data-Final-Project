import java.io.IOException;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DualCrimeMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private final static IntWritable zero = new IntWritable(0);
    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {


        String line = value.toString();

        String[] fields = line.split(",");

        List<String> groups = Arrays.asList("PETIT LARCENY",
                "HARRASSMENT 2",
                "ASSAULT 3 & RELATED OFFENSES",
                "CRIMINAL MISCHIEF & RELATED OF",
                "GRAND LARCENY",
                "DANGEROUS DRUGS",
                "OFF. AGNST PUB ORD SENSBLTY &",
                "FELONY ASSAULT",
                "ROBBERY",
                "BURGLARY",
                "MISCELLANEOUS PENAL LAW",
                "DANGEROUS WEAPONS",
                "OFFENSES AGAINST PUBLIC ADMINI",
                "GRAND LARCENY OF MOTOR VEHICLE",
                "INTOXICATED & IMPAIRED DRIVING",
                "VEHICLE AND TRAFFIC LAWS",
                "SEX CRIMES",
                "FORGERY",
                "THEFT-FRAUD",
                "CRIMINAL TRESPASS");

        if (fields.length == 4) {
            String month = fields[0].trim();
            if (!month.matches("\\d+"))
            {
                month = "0";
            }

            String crime = fields[2].trim();

            if (groups.contains(crime)) {
                word.set(crime + "\t" + month);
                context.write(word, one);
            }
        }
    }
}

