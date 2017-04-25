package blog.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import blog.startup.TCache;

@Component
public class IndexPageTask {

	@Scheduled(cron = "0 0 2 * * ?")
	public void refreshIndexPageCache() {
		TCache.getCache().initIndexPageContentList();
	}
}
