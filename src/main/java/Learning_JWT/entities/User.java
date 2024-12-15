package Learning_JWT.entities;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private final String ROLE = "USER";
}
