All related project materials (datasets, python scripts, Impala scripts and command lines) are within the project directory in HDFS (/user/sl6166/project). Code and commands for data ingestion are in the /data_ingest directory.

Datasets(/user/sl6166/project): crime.csv

Python scripts (/user/sl6166/project/python_code): CleanMapper.py, CleanReducer.py, CountRecsMapper.py, CountRecsReducer.py


ETL/cleaning code is in the /etl_code directory. Run the python scripts with the following command line.

hadoop jar /opt/cloudera/parcels/CDH/lib/hadoop-mapreduce/hadoop-streaming.jar \
-D mapreduce.job.reduces=0 \
-files hdfs://dumbo/user/sl6166/project/python_code/CleanMapper.py,hdfs://dumbo/user/sl6166/project/python_code//CleanReducer.py \
-mapper "python CleanMapper.py" \
-reducer "python CleanReducer.py" \
-input /user/sl6166/project/crime.csv \
-output /user/sl6166/project/output

Fetch results with the following command line.

hdfs dfs -ls /user/sl6166/project/output
hdfs dfs -get /user/sl6166/project/output/part-00000
hdfs dfs -get /user/sl6166/project/output/part-00001

The cleaned dataset is mapped to two files: part-00001 and part-00000. Download the files and concatenate them into one file called "cleanedCrime.csv". Remove the header in "part-00000" to ensure the final output will have only one header. Upload the cleaned dataset and put it in HDFS.

scp sl6166@dumbo.es.its.nyu.edu:/home/sl6166/project/part-00000 Desktop
scp sl6166@dumbo.es.its.nyu.edu:/home/sl6166/project/part-00001 Desktop
cat part-00001 part-00000 > cleanedCrime.csv
hdfs dfs -put cleanedCrime.csv /user/sl6166/project


Profiling code is in the /profiling_code directory. Run the python scripts with the following command line. Use "hdfs dfs -rm -r project/output" to clear the output directory each time before running. Use "hdfs dfs -cat /user/sl6166/project/output/part-00000" to fetch the results.

hadoop jar /opt/cloudera/parcels/CDH/lib/hadoop-mapreduce/hadoop-streaming.jar \
-D mapreduce.job.reduces=1 \
-files hdfs://dumbo/user/sl6166/project/python_code/CountRecsMapper.py,hdfs://dumbo/user/sl6166/project/python_code//CountRecsReducer.py \
-mapper "python CountRecsMapper.py" \
-reducer "python CountRecsReducer.py" \
-input /user/sl6166/project/crime.csv \
-output /user/sl6166/project/output

hadoop jar /opt/cloudera/parcels/CDH/lib/hadoop-mapreduce/hadoop-streaming.jar \
-D mapreduce.job.reduces=1 \
-files hdfs://dumbo/user/sl6166/project/python_code/CountRecsMapper.py,hdfs://dumbo/user/sl6166/project/python_code//CountRecsReducer.py \
-mapper "python CountRecsMapper.py" \
-reducer "python CountRecsReducer.py" \
-input /user/sl6166/project/cleanedCrime.csv \
-output /user/sl6166/project/output


Create an input data directory called "impalaInput" in HDFS. Remove the header in "cleanedCrime.csv". Populate the directory with "cleanedCrime.csv". Follow the command lines in /ana_code/impalaScript in impala-shell on Dumbo for further analysis. 

hdfs dfs -mkdir impalaInput
hdfs dfs -put cleanedCrime.csv impalaInput
