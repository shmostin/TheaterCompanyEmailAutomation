package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MemberTest {

  private Member original;
  private Member same;
  private Member another;
  private Member copy;
  private Member diffFirst;
  private Member diffLast;
  private Member diffCompany;
  private Member diffAddress;
  private Member diffCity;
  private Member diffCounty;
  private Member diffState;
  private Member diffZip;
  private Member diffPhoneOne;
  private Member diffPhoneTwo;
  private Member diffEmail;
  private Member diffWebsite;
  private Member nullMember = null;

  @Before
  public void setUp() throws Exception {
    original  = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    same = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    another = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    copy = original;

    diffFirst = new Member("Fist", "Last", "Company",
      "1234 Test St.", "City", "County", "ST", "12345",
      "123-456-7890","098-765-4321", "email@email.com",
      "http://www.website.com");
    diffLast = new Member("First", "Lst", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffCompany = new Member("First", "Last", "Comny",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffAddress = new Member("First", "Last", "Company",
        "1234 st St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffCity = new Member("First", "Last", "Company",
        "1234 Test St.", "Cy", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffCounty = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "Cnty", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffState = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "AL", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffZip = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "1245",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffPhoneOne = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-70","098-765-4321", "email@email.com",
        "http://www.website.com");
    diffPhoneTwo = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-5-4321", "email@email.com",
        "http://www.website.com");
    diffEmail = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@il.com",
        "http://www.website.com");
    diffWebsite = new Member("First", "Last", "Company",
        "1234 Test St.", "City", "County", "ST", "12345",
        "123-456-7890","098-765-4321", "email@email.com",
        "http://www.site.com");
  }

  @Test
  public void getFirstName() { Assert.assertEquals("First", original.getFirstName()); }

  @Test
  public void getLastName() { Assert.assertEquals("Last", original.getLastName()); }

  @Test
  public void getCompany() { Assert.assertEquals("Company", original.getCompany()); }

  @Test
  public void getAddress() { Assert.assertEquals("1234 Test St.", original.getAddress()); }

  @Test
  public void getCity() { Assert.assertEquals("City", original.getCity()); }

  @Test
  public void getCounty() { Assert.assertEquals("County", original.getCounty()); }

  @Test
  public void getState() { Assert.assertEquals("ST", original.getState()); }

  @Test
  public void getZip() { Assert.assertEquals("12345", original.getZip()); }

  @Test
  public void getPhoneOne(){ Assert.assertEquals("123-456-7890", original.getPhoneOne()); }

  @Test
  public void getPhoneTwo(){ Assert.assertEquals("098-765-4321", original.getPhoneTwo()); }

  @Test
  public void getEmail() { Assert.assertEquals("email@email.com", original.getEmail()); }

  @Test
  public void getWebsite() {
    Assert.assertEquals("http://www.website.com", original.getWebsite());
  }

  @Test
  public void testEquals() {
    Assert.assertTrue(original.equals(original));
    Assert.assertTrue(original.equals(copy));
    Assert.assertTrue(original.equals(same) && same.equals(another) &&
        another.equals(original));
    Assert.assertFalse(original.equals(diffFirst));
    Assert.assertFalse(original.equals(diffLast));
    Assert.assertFalse(original.equals(diffCompany));
    Assert.assertFalse(original.equals(diffAddress));
    Assert.assertFalse(original.equals(diffCity));
    Assert.assertFalse(original.equals(diffCounty));
    Assert.assertFalse(original.equals(diffState));
    Assert.assertFalse(original.equals(diffZip));
    Assert.assertFalse(original.equals(diffPhoneOne));
    Assert.assertFalse(original.equals(diffPhoneTwo));
    Assert.assertFalse(original.equals(diffEmail));
    Assert.assertFalse(original.equals(diffWebsite));
    Assert.assertFalse(original.equals("not a member"));
    Assert.assertFalse(original.equals(nullMember));
  }

  @Test
  public void testHashCode() { Assert.assertEquals(original.hashCode(), same.hashCode()); }

  @Test
  public void testToString() { Assert.assertEquals(original.toString(), same.toString()); }
}