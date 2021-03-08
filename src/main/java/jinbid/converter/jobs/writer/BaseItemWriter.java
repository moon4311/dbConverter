package jinbid.converter.jobs.writer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.item.ItemWriter;

public class BaseItemWriter implements ItemWriter<Map>{

	/*		to-be DB		*/
	@Resource(name="db2SqlSessionFactory")
	private SqlSessionFactory db2SqlSessionFactory;
	
	@Override
	public void write(List<? extends Map> items) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("write : !!! ");
		for(Map a : items) {
			System.err.println(a);
		}
	}
}
