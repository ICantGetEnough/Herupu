package admin.models.abs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
public abstract class Hospital {
    private Long id;
    private Date createDate;
    private Integer active;

    @Override
    public String toString() {
        return "Id = " + id;
    }
}
