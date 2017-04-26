package blog.startup;

import java.util.LinkedHashMap;
import java.util.List;

import org.mongodb.morphia.query.FindOptions;

import blog.model.Carousel;
import blog.model.Content;
import blog.model.Notice;
import blog.model.Topic;

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
			TCache.tcache.initIndexPageCarouselList();
			TCache.tcache.initIndexPageNoticeList();
		}
		return TCache.tcache;
	}
	
	/**
	 * 标题列表
	 */
	public LinkedHashMap<String,Topic> titleCache = null;
	
	public int initTitleCache()
	{
		if(TCache.getCache().titleCache ==null)
		{
			TCache.getCache().titleCache = new LinkedHashMap<>();
		}
		
		List<Topic> list = MongoDBConnector.datastore.find(Topic.class).order("order").asList();
		for(Topic topic : list)
		{
			TCache.getCache().titleCache.put(topic.getBM_ID(), topic);
		}
		return 0;
	}

	public LinkedHashMap<String,Topic> getTitleCache() {
		return titleCache;
	}
	
	/**
	 * 首页轮转图
	 */
	public List<Carousel> indexPageCarouselList = null;
	
	public List<Carousel> getIndexPageCarouselList() {
		return indexPageCarouselList;
	}

	private int initIndexPageCarouselList()
	{
		TCache.getCache().indexPageCarouselList = MongoDBConnector.datastore.find(Carousel.class).order("BM_TIME").asList(new FindOptions().limit(3));
		if(TCache.getCache().indexPageCarouselList.size()<3)
		{
			int rest = 3-TCache.getCache().indexPageCarouselList.size();
			for(int i=0;i<rest;i++)
			{
				Carousel c = new Carousel();
				MongoDBConnector.datastore.save(c);
			}
			TCache.getCache().indexPageCarouselList = MongoDBConnector.datastore.find(Carousel.class).order("BM_TIME").asList(new FindOptions().limit(3));
		}
		return 0;
	}
	
	public List<Notice> indexPageNoticeList = null;
	public List<Notice> getIndexPageNoticeList() {
		return indexPageNoticeList;
	}
	private int initIndexPageNoticeList()
	{
		TCache.getCache().indexPageNoticeList = MongoDBConnector.datastore.find(Notice.class).order("-BM_TIME").asList(new FindOptions().limit(4));
		return 0;
	}
	
	/**
	 * 首页的缓存
	 * 下面的瀑布流
	 */
	public List<Content> indexPageContentList = null;
	
	public int initIndexPageContentList()
	{
		TCache.getCache().indexPageContentList = MongoDBConnector.datastore.find(Content.class).order("-replyCount,-BM_TIME").asList(new FindOptions().limit(15));
		return 0;
	}
	
	public List<Content> getIndexPageContentList() {
		return indexPageContentList;
	}
}
