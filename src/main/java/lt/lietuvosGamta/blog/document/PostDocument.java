package lt.lietuvosGamta.blog.document;

import lt.lietuvosGamta.blog.dto.Comment;
import lt.lietuvosGamta.blog.dto.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "Posts")

public class PostDocument {
    @Id
    private ObjectId id;
    private String postName;
    @TextIndexed
    private String postText;
    private LocalDate postDate;
    private String picture;
    private List<Comment> comments;

    public static PostDocument convert(Post post) {
        return new PostDocument(post.getId(),
                post.getPostName(),
                post.getPostText(),
                post.getPostDate(),
                post.getPicture(),
                post.getComments());

    }
}
