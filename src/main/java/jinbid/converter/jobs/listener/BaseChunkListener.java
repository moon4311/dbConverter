package jinbid.converter.jobs.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class BaseChunkListener implements ChunkListener{

	
	@Override
	public void beforeChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}
	public BaseChunkListener() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
