import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void equalsOverride_returnsTrueIfNamesAreTheSameString_true() {
    Stylist firstStylist = new Stylist("Tyler");
    Stylist secondStylist = new Stylist("Tyler");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesStylistToTheDatabase_true() {
    Stylist testStylist = new Stylist("Tyler");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  // @Test
  // public void find_returnsCorrectStylist_true() {
  //   Stylist testStylist1 = new Stylist("Tyler");
  //   Stylist testStylist2 = new Stylist("Kris");
  //   Stylist testStylist3 = new Stylist("Jon");
  //   testStylist.save();
  //   testStylist.save();
  //   testStylist.save();
  //   Stylist savedStylist = Stylist.find(testStylist2.getID());
  //   assertTrue(testStylist2.equals(savedStylist));
  // }
}
