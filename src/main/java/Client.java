import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private int stylist_id;

  public Client (String name) {
    this.name = name;
  }


  public String getName() {
    return name;
  }

  public int getID() {
    return id;
  }

  public void setStylistID(int stylistID) {
    stylist_id = stylistID;
  }

  public int getStylistID() {
    return stylist_id;
  }

  //read all clients from database
  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  //save client to database
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  //retrieves client from database
  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, name FROM clients WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
    }
  }

  //update client in database
  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, stylist_id = :stylist_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("name", this.name)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate();
    }
  }

  //find client by stylist_id
  public static List<Client> findByStylist(int stylistID) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id= :stylist_id";
      return con.createQuery(sql)
        .addParameter("stylist_id", stylistID)
        .executeAndFetch(Client.class);
    }
  }

  // ensure that objects are considered equal if their name strings match
  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName());
    }
  }
}
