package hacs.scheduler;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	
	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);
	
//	5초에 한 번씩 돌게하는 것 
	@Scheduled(cron = "*/5 * * * * *")
	public void scheduler1() {
		log.info("스케쥴러 동작하는 중  :  {}",Calendar.getInstance().getTime());
	}

}
