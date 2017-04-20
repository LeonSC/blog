package blog.dao;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Repository;

import blog.model.Carousel;
import blog.startup.MongoDBConnector;

@Repository
public class CarouselDao {

	public Carousel update(Carousel carousel)
	{
		Query<Carousel> updateQuery = MongoDBConnector.datastore.createQuery(Carousel.class).field("BM_ID").equal(carousel.getBM_ID());
		
		UpdateOperations<Carousel> ops=MongoDBConnector.datastore.createUpdateOperations(Carousel.class);
		
		ops.set("img", carousel.getImg());
		ops.set("link", carousel.getLink());
		
		MongoDBConnector.datastore.updateFirst(updateQuery, ops);
		
		return MongoDBConnector.datastore.createQuery(Carousel.class).field("BM_ID").equal(carousel.getBM_ID()).get();
	}
	
}
