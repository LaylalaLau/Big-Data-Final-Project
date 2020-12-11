import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.fieldsel.FieldSelectionHelper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SelectFieldsRunner {

    /**
     * select fields
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // conf.set(FieldSelectionHelper.MAP_OUTPUT_KEY_VALUE_SPEC, "0,3:1,2,4-");
        conf.set(FieldSelectionHelper.MAP_OUTPUT_KEY_VALUE_SPEC, ":1,2,7,13");
        conf.set(FieldSelectionHelper.REDUCE_OUTPUT_KEY_VALUE_SPEC, "0,1,2,3");
        conf.set(FieldSelectionHelper.DATA_FIELD_SEPERATOR, ",");
        // conf.set("mapred.textintputformat.separator", ",");
        conf.set("mapred.textoutputformat.separator", "");

        Job job = new Job(conf, "GroupTest");
        job.setJarByClass(SelectFieldsRunner.class);

        job.setMapperClass(SelectFieldsMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(SelectFieldsReducer.class);

        // job.setOutputKeyClass(Text.class);
        // job.setOutputValueClass(Text.class);

        // job.setNumReduceTasks(0);
        job.setNumReduceTasks(1);

        // FileInputFormat.setInputPaths(job, new Path("data.txt"));
        FileInputFormat.addInputPath(job, new Path("./NYPD_Complaint_Data_Historic.csv"));
        FileOutputFormat.setOutputPath(job, new Path("./fieldsel"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}