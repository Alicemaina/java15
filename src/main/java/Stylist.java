import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String name;

  public Stylist (String name) {
    this.name = name;
  }

  //read all stylists from database
  public static List<Stylist> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
}
