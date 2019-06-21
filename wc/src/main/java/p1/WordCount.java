package p1;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.FileSystem.WriteMode;

public class WordCount
{
  public static void main(String[] args)
    throws Exception
  {
	int a = 1;
	while(a > 0) { 
    ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
    
    ParameterTool params = ParameterTool.fromArgs(args);
    
    env.getConfig().setGlobalJobParameters(params);
    
    DataSet<String> text = env.readTextFile("/Users/lingessrajoo/Desktop/location.txt");
    ///Users/lingessrajoo/Desktop/Streewise/src/main/java/pipeline/twitter/location.txt
    DataSet<String> filtered = text.filter(new FilterFunction<String>()

    {
      public boolean filter(String value)
      {
        return value.contains("USA");
      }
    });
    

    DataSet<Tuple2<String, Integer>> tokenized = filtered.map(new Tokenizer());
    
    DataSet<Tuple2<String, Integer>> counts = tokenized.groupBy(new int[] { 0 }).sum(1);

    counts.writeAsCsv("/Users/lingessrajoo/Desktop/result.txt", "\n", " ", WriteMode.OVERWRITE);
      
    env.execute("WordCount Example");
   
  }
  }
  public static final class Tokenizer
    implements MapFunction<String, Tuple2<String, Integer>>
  {
    public Tuple2<String, Integer> map(String value)
    {
      return new Tuple2(value, Integer.valueOf(1));
    }
  }
}
