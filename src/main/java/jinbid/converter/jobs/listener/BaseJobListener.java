package jinbid.converter.jobs.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

/**
 * 잡 시작전, 종료후
 * @author kjm
 *
 */
@Slf4j
public class BaseJobListener implements JobExecutionListener{

	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// log.debug(jobExecution.getJobInstance().getJobName() + " START ");
		String text = "\nd888888b d88888b .d8888. d888888b      .88b  d88.  .d88b.  d8888b. d88888b "
		  +"\n`~~88~~' 88'     88'  YP `~~88~~'      88'YbdP`88 .8P  Y8. 88  `8D 88'     \n   88    88ooooo `8bo.      88         88  88  88 88    88 88   88 88ooooo "
		  +"\n   88    88~~~~~   `Y8b.    88         88  88  88 88    88 88   88 88~~~~~ \n   88    88.     db   8D    88         88  88  88 `8b  d8' 88  .8D 88.     "
		  +"\n   YP    Y88888P `8888Y'    YP         YP  YP  YP  `Y88P'  Y8888D' Y88888P \n========================================================================== ";
		log.debug(text);
		System.out.println(" beforeJob-------------------------------------------------");
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		System.out.println(" afterJob-------------------------------------------------");
		
    	log.debug(jobExecution.getJobInstance().getJobName() + " FINISH ");

    	String text =
		   "\n   d88b  .d88b.  d8888b.      d88888b d888888b d8b   db d888888b .d8888. db   db "
    	  +"\n   `8P' .8P  Y8. 88  `8D      88'       `88'   888o  88   `88'   88'  YP 88   88 "
    	  +"\n    88  88    88 88oooY'      88ooo      88    88V8o 88    88    `8bo.   88ooo88 "
    	  +"\n    88  88    88 88~~~b.      88~~~      88    88 V8o88    88      `Y8b. 88~~~88 "
    	  +"\ndb. 88  `8b  d8' 88   8D      88        .88.   88  V888   .88.   db   8D 88   88 "
    	  +"\nY8888P   `Y88P'  Y8888P'      YP      Y888888P VP   V8P Y888888P `8888Y' YP   YP "
    	  +"\n================================================================================ ";
		log.debug(text);
	}
}
