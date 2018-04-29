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
import org.junit.Ignore;
import org.junit.Test;


public class ReceptionistTest
{
  ClientConfig config = new ClientConfig();
  Client client = ClientBuilder.newClient(config);
  WebTarget target = client.target(UriBuilder.fromUri("http://localhost:80/LawOSREST").build(new Object[0]));
  


  public ReceptionistTest() {}
  


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
  

  @Ignore
  @Test
  public void newappTest()
  {
    Form form = new Form();
    form.param("date", "2016-10-10");
    form.param("time", "11:00:00");
    form.param("clientID", "1");
    form.param("legalStaffID", "1");
    form.param("caseID", "1");
    String res2 = 
    
      (String)target.path("rest").path("lawos").path("receptionist").path("newapp").request().accept(new String[] {"application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    if (res2.equals("1")) {
      System.out.println("Correct response from server: " + res2 + " Inserted Successfully" + "\n\n");
    } else {
      System.out.println("Not inserted, code: " + res2 + "\n");
    }
  }
  
  @Ignore
  @Test
  public void delappTest() {
    Form form = new Form();
    form.param("appointmentID", "31");
    String res3 = 
    
      (String)target.path("rest").path("lawos").path("receptionist").path("delapp").request().accept(new String[] {"application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    
    if (res3.equals("1")) {
      System.out.println("Correct response from server: " + res3 + " Deleted Successfully" + "\n\n");
    } else {
      System.out.println("Not deleted, code: " + res3 + "\n");
    }
  }
  
  @Test
  public void incTest() {
    String res4 = 
      (String)target.path("rest").path("lawos").path("receptionist").path("app").path("inc").request().accept(new String[] {"application/json" }).get(String.class);
    
    System.out.println("The rest Service responds with the following jSon: \n" + res4 + "\n");
  }
  
  @Test
  public void comTest()
  {
    String res5 = 
      (String)target.path("rest").path("lawos").path("receptionist").path("app").path("com").request().accept(new String[] {"application/json" }).get(String.class);
    
    System.out.println("The rest Service responds with the following jSon: \n" + res5 + "\n");
  }
  
  @Test
  public void passedTest()
  {
    String res6 = 
      (String)target.path("rest").path("lawos").path("receptionist").path("app").path("passed").request().accept(new String[] {"application/json" }).get(String.class);
    System.out.println("The rest Service responds with the following jSon: \n" + res6 + "\n");
  }
  
  @Test
  public void markTest()
  {
    Form form = new Form();
    form.param("appointmentID", "13");
    String res7 = 
    
      (String)target.path("rest").path("lawos").path("receptionist").path("app").path("mark").request().accept(new String[] {"application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    System.out.println("The rest Service responds with the following jSon: \n" + res7 + "\n");
  }
  
  @Test
  public void searchClientTest()
  {
    Form form = new Form();
    form.param("ID", "1");
    String res8 = 
    
      (String)target.path("rest").path("lawos").path("search").path("client").request().accept(new String[] {"application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    System.out.println("The rest Service responds with the following jSon: \n" + res8 + "\n");
  }
  
  @Test
  public void recomStrategiesTest()
  {
    Form form = new Form();
    form.param("ID", "1");
    String res9 = 
    
      (String)target.path("rest").path("lawos").path("recom").path("strategies").request().accept(new String[] {"application/json" }).post(Entity.entity(form, "application/x-www-form-urlencoded"), String.class);
    System.out.println("The rest Service responds with the following jSon: \n" + res9 + "\n");
  }
}
