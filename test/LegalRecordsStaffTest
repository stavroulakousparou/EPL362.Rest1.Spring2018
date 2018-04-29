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


public class LegalRecordsStaffTest
{
  ClientConfig config = new ClientConfig();
  Client client = ClientBuilder.newClient(config);
  WebTarget target = client.target(UriBuilder.fromUri("http://localhost:80/LawOSREST").build(new Object[0]));
  

  public LegalRecordsStaffTest() {}
  

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
  public void clientTransactionsTest()
  {
    Form form = new Form();
    form.param("ID", "1");
    String res11 = 
    
      (String)target.path("rest").path("lawos").path("lrs").path("transactions").path("client").request().accept(new String[] {"application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println("Transactions -> " + res11 + "\n");
  }
  


  @Test
  public void updateClientTest()
  {
    Form form = new Form();
    form.param("ID", "1");
    form.param("Name", "Andreas");
    form.param("Surname", "Andreou");
    form.param("Unwillingness", "Simple solutions");
    String res10 = 
    
      (String)target.path("rest").path("lawos").path("lrs").path("edit").path("client").request().accept(new String[] {"application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    System.out.println("Update client status -> " + res10);
  }
}
