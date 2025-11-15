package lt.lietuvosGamta.blog.document;

import lt.lietuvosGamta.blog.dto.Role;
import lt.lietuvosGamta.blog.dto.User;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "Users")
public class UserDocument {
    @Id
    private ObjectId id;
    private String name;
    private String surname;
    private String username;
    private String avatar;

    @Email
    private String email;
    private String password;
    private Set<Role> roles;

    public static UserDocument convert(User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new UserDocument(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getAvatar(),
                user.getEmail(),
                encoder.encode(user.getPassword()),
                user.getRoles()  );
    }

}
