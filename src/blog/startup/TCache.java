package blog.startup;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.query.FindOptions;

import blog.model.Content;
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
			TCache.tcache.initIndexPageContentList();
		}
		return TCache.tcache;
	}
	
	/**
	 * 标题列表
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
	
	/**
	 * 首页的缓存
	 */
	public List<Content> indexPageContentList = null;
	
	private int initIndexPageContentList()
	{
		if(TCache.getCache().indexPageContentList ==null)
		{
			TCache.getCache().indexPageContentList = new ArrayList<>();
		}
		
		TCache.getCache().indexPageContentList = MongoDBConnector.datastore.find(Content.class).order("-BM_TIME").asList(new FindOptions().limit(10));
		
		return 0;
	}
	
	public List<Content> getIndexPageContentList() {
		return indexPageContentList;
	}
}
