package uz.pdp.g30springrestful.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    @Size(min = 10)
    private String content;
    @NotBlank
    @Size(min = 5, max = 255)
    private String author;
}
