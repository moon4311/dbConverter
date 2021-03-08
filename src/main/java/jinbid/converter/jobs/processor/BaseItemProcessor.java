package jinbid.converter.jobs.processor;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;

/**
 * 한 건씩 데이터 가공처리
 * return null 이면 제외
 * @author kjm
 *
 */
public class BaseItemProcessor implements ItemProcessor<Map,Map>{
	
	
	
	
	@Override
	public Map process(Map item) throws Exception {
		System.err.println(item);
		
		if(item.get("TYPE_ID").equals("00000")) return null;
		
		return item;
	}	

}
