import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.KeyValue;

import java.util.List;


public class GetExample {

  public static void main(String[] args) throws IOException {
    Configuration conf = HBaseConfiguration.create();

    HTable table = new HTable("t1");
    Get get = new Get(Bytes.toBytes("r2"));
    get.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("c1")); 

	get.setMaxVersions();	// <--- Get many versions

    Result result = table.get(get); 

    //byte[] val = result.getValue(Bytes.toBytes("cf1"),
	//Bytes.toBytes("c1"));
    //System.out.println("Value: " + Bytes.toString(val)); 

	List<KeyValue> list = result.list();
	for (KeyValue kv : list) {
		String k = Bytes.toString(kv.getRow());
		String f = Bytes.toString(kv.getFamily());
		String c = Bytes.toString(kv.getQualifier());
		String v = Bytes.toString(kv.getValue());
		System.out.println(k + "/" + f + ":" + c + "=" + v);
	}

    table.close(); 
  }
}
