package lt.lietuvosGamta.blog.service;

import lt.lietuvosGamta.blog.document.UserDocument;
import lt.lietuvosGamta.blog.dto.Role;
import lt.lietuvosGamta.blog.dto.User;
import lt.lietuvosGamta.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public void createUser (User user){
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role("USER");
        //Role adminRole = new Role("ADMIN");
        roles.add(userRole);
        //roles.add(adminRole);
        user.setRoles(roles);
        userRepository.insert(UserDocument.convert(user));
    }
    public void deleteUser (ObjectId id){
        userRepository.deleteById(id);
    }
    public void updateUser (User user){
        userRepository.save(UserDocument.convert(user));
    }

    public List<String> getAllUserNames (){
        List<User>users = userRepository.findAll()
                .stream()
                .map(User::convert)
                .toList();
        List<String>usernames = new ArrayList<>();
        for (User user : users){
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDocument userDocument = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return User.convert(userDocument);
    }
}
