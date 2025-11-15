package lt.lietuvosGamta.blog.dto;

import lt.lietuvosGamta.blog.document.PostDocument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Post {
    private ObjectId id;
    @NotBlank
    @Size(min = 5, max = 20)
    private String postName;
    @NotBlank
    @Size(min = 5, max = 500)
    private String postText;
    private LocalDate postDate;
    private String picture;
    private List<Comment> comments;

    public static Post convert(PostDocument postDocument) {
        return new Post(postDocument.getId(),
                postDocument.getPostName(),
                postDocument.getPostText(),
                postDocument.getPostDate(),
                postDocument.getPicture(),
                postDocument.getComments());
    }
}
