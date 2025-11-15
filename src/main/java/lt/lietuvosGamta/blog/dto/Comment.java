package lt.lietuvosGamta.blog.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Comment {

    private String id;
    @NotBlank
    @Max(100)
    private String commentText;
    private String username;
    private LocalDateTime dateTime;
}
