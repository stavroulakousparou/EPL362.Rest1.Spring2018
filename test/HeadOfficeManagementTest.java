package test;

import java.io.PrintStream;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HeadOfficeManagementTest
{
  ClientConfig config = new ClientConfig();
  Client client = ClientBuilder.newClient(config);
  WebTarget target = client.target(UriBuilder.fromUri("http://localhost:80/LawOSREST").build(new Object[0]));
  

  public HeadOfficeManagementTest() {}
  

  @BeforeClass
  public static void setUpBeforeClass()
    throws Exception
  {}
  

  @AfterClass
  public static void tearDownAfterClass()
    throws Exception
  {}
  

  @Before
  public void setUp()
    throws Exception
  {}
  

  @After
  public void tearDown()
    throws Exception
  {}
  
  @Test
  public void viewNumofClientsPerBranch()
  {
    Form form = new Form();
    form.param("BranchID", "0");
    String res12 = 
    
      (String)target.path("rest").path("lawos").path("hom").path("reports").path("branch").path("nclient").request().accept(new String[] { "application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println("HOM : numofclients per branch -> " + res12 + "\n");
  }
  
  @Test
  public void recPerMonthTest()
  {
    Form form = new Form();
    form.param("BranchID", "0");
    String res13 = 
    
      (String)target.path("rest").path("lawos").path("hom").path("reports").path("recom").path("permonth").request().accept(new String[] { "application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println("HOM : recommendations per month -> " + res13 + "\n");
  }
  

  @Test
  public void legalOpPerMonthTest()
  {
    Form form = new Form();
    form.param("BranchID", "0");
    String res14 = 
    
      (String)target.path("rest").path("lawos").path("hom").path("reports").path("legalop").path("permonth").request().accept(new String[] { "application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println("HOM : legal opinions per month -> " + res14 + "\n");
  }
  

  @Test
  public void timesWaitTest()
  {
    Form form = new Form();
    form.param("ID", "1");
    String res15 = 
    
      (String)target.path("rest").path("lawos").path("hom").path("reports").path("client").path("timeswait").request().accept(new String[] { "application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println("HOM : completed app of a client (timeswait) -> " + res15 + "\n");
  }
  

  @Test
  public void weeklyAttTest()
  {
    Form form = new Form();
    form.param("BranchID", "0");
    String res16 = 
    
      (String)target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perbranch").path("attended").request().accept(new String[] { "application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println(
      "HOM : Weekly reports for attended number of clients per branch per weekday -> " + res16 + "\n");
  }
  

  @Test
  public void weeklyVisTest()
  {
    Form form = new Form();
    form.param("BranchID", "0");
    String res17 = 
    
      (String)target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perbranch").path("all").request().accept(new String[] { "application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println(
      "HOM : Weekly reports for total visits of clients per branch per weekday -> " + res17 + "\n");
  }
  

  @Test
  public void weeklyClientTest()
  {
    Form form = new Form();
    form.param("ClientID", "1");
    String res18 = 
    
      (String)target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perclient").request().accept(new String[] { "application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println("HOM : Weekly reports per client -> " + res18 + "\n");
  }
}
