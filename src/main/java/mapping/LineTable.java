package mapping;

import org.hibernate.Session;
import org.project.model.DataBaseHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lines")
public class LineTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_line;

    private String line_name;

    public LineTable(int id_line, String line_name) {
        this.id_line = id_line;
        this.line_name = line_name;
    }

    public LineTable() {
        super();
    }

    public int getId_line() {
        return id_line;
    }

    public void setId_line(int id_line) {
        this.id_line = id_line;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public static List<LineTable> getLines() {
        Session session = DataBaseHelper.startConnection();
        Query query = session.createQuery("from LineTable l");

        return query.getResultList();
    }
}
