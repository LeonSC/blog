package blog.startup;

import java.util.ArrayList;
import java.util.List;

import blog.model.Title;

public class TCache {
	private static TCache tcache;
	private TCache(){}
	
	public static TCache getCache()
	{
		if(TCache.tcache==null)
		{
			TCache.tcache=new TCache();
			TCache.tcache.initTitleCache();
		}
		return TCache.tcache;
	}
	
	/**
	 * 
	 */
	public List<Title> titleCache = null;
	
	private int initTitleCache()
	{
		if(TCache.getCache().titleCache ==null)
		{
			TCache.getCache().titleCache = new ArrayList<Title>();
		}
		TCache.getCache().titleCache = MongoDBConnector.datastore.find(Title.class).order("order").asList();
		
		return 0;
	}
	
	public List<Title> getTitleCache() {
		return titleCache;
	}
}
