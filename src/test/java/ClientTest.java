import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Client.all().size());
  }

  @Test
  public void equalsOverride_returnsTrueIfNamesAreTheSameString_true() {
    Client firstClient = new Client("Tyler");
    Client secondClient = new Client("Tyler");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesClientToTheDatabase_true() {
    Client testClient = new Client("Tyler");
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void find_returnsCorrectClient_true() {
    Client testClient1 = new Client("Tyler");
    Client testClient2 = new Client("Kris");
    Client testClient3 = new Client("Jon");
    testClient1.save();
    testClient2.save();
    testClient3.save();
    Client savedClient = Client.find(testClient2.getID());
    assertTrue(savedClient.getName().equals("Kris"));
  }

  @Test
  public void setStylistID_assignsSpecificStylistToClient_true() {
    Client testClient = new Client("Tyler");
    testClient.save();
    testClient.setStylistID(1);
    testClient.update();
    assertTrue(Client.all().get(0).equals(testClient));
  }
}
