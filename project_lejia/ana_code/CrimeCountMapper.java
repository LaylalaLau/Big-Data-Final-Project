import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CrimeCountMapper extends
        Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);

    private Text word = new Text();

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();

        String[] fields = line.split(",");

        if (fields.length == 4) {
            String crime = fields[2].trim();
            word.set(crime);
            context.write(word, one);
        }
    }
}
