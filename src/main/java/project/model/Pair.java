package project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long fUserId;

    public long sUserId;

    public Date date;


}
