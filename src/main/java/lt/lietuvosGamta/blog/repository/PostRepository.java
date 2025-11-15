package lt.lietuvosGamta.blog.repository;

import lt.lietuvosGamta.blog.document.PostDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostRepository extends MongoRepository<PostDocument, ObjectId> {

}
