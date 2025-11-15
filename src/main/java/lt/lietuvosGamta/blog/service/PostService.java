package lt.lietuvosGamta.blog.service;

import lt.lietuvosGamta.blog.document.PostDocument;
import lt.lietuvosGamta.blog.dto.Comment;
import lt.lietuvosGamta.blog.dto.Post;
import lt.lietuvosGamta.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    public void createPost(Post post) {
        postRepository.insert(PostDocument.convert(post));
    }

    public List<Post> showAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(Post::convert)
                .toList();
    }

    public List<Post> showSearchedPosts(String text) {
        List<Post> allPosts = showAllPosts();
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : allPosts) {
            if (post.getPostText().toLowerCase().contains(text.toLowerCase())) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }

    public Post showSinglePost(ObjectId id) {
        return Post.convert(Objects.requireNonNull(postRepository.findById(id).orElse(null)));
    }

    public void deletePost(ObjectId id) {
        postRepository.deleteById(id);
    }

    public void updatePost(Post post) {
        Post odlPost = showSinglePost(post.getId());
        List<Comment> comments = odlPost.getComments();
        post.setPostDate(LocalDate.now());
        post.setComments(comments);
        postRepository.save(PostDocument.convert(post));
    }

    public void createComment(ObjectId id, Comment comment) {
        Post post = showSinglePost(id);
        List<Comment> comments = post.getComments();
        comment.setId(UUID.randomUUID().toString());
        comment.setDateTime(LocalDateTime.now());
        comments.add(comment);
        post.setComments(comments);
        postRepository.save(PostDocument.convert(post));
    }

    public void deleteComment(ObjectId postId, String commentId) {
        List<Comment> comments = showSinglePost(postId).getComments();
        comments.removeIf(c -> c.getId().equals(commentId));
        Post post = showSinglePost(postId);
        post.setComments(comments);
        postRepository.save(PostDocument.convert(post));
    }


}
