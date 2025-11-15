package lt.lietuvosGamta.blog.repository;
import lt.lietuvosGamta.blog.document.UserDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, ObjectId> {
    Optional<UserDocument> findByUsername(String username);

}
