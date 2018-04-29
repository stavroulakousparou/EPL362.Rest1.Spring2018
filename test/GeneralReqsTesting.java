package test;

import java.io.PrintStream;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GeneralReqsTesting
{
  ClientConfig config = new ClientConfig();
  Client client = ClientBuilder.newClient(config);
  WebTarget target = client.target(UriBuilder.fromUri("http://localhost:80/LawOSREST").build(new Object[0]));
  

  public GeneralReqsTesting() {}
  

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
  public void viewSuspiciousClients()
  {
    String res12 = 
      (String)target.path("rest").path("lawos").path("search").path("clientwithhistory").request().accept(new String[] {"application/json" }).get(String.class);
    System.out.println("HOM : The clients that you have to be careful from are: -> " + res12 + "\n");
  }
}
