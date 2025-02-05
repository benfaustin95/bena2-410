package edu.pdx.cs410J.bena2;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can capture data)
 * written to {@link System#out} and the like.
 */
class CommandLineParserTest {

  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
            InputStream readme = CommandLineParser.class.getResourceAsStream("README.txt")
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("#################################################################################"));
    }
  }

  @Test
  void createAirlineAndFlight()
  {
    CommandLineParser test = new CommandLineParser();
    ArrayList<String> arguments= getInvalidFlightData();


    assertThrows(IllegalArgumentException.class,() -> test.createAirlineAndFlight(arguments));
  }

  public static ArrayList<String> getInvalidFlightData()
  {
    ArrayList<String> test = new ArrayList<>();
    String[] toAdd = {"1","src","1/1/2023","10:39","dsw", "1/2/2023","12:23"};

    Collections.addAll(test, toAdd);

    return test;
  }

  public static ArrayList<String> getValidFlightData()
  {
    ArrayList<String> test = new ArrayList<>();
    String[] toAdd = {"name","1","pdx","1/1/2023","10:39","am","sea", "1/2/2023","12:23","pm"};

    Collections.addAll(test, toAdd);

    return test;
  }
  @Test
  void testCorrectNumberOfArguments()
  {
    ArrayList<String> test1 = getInvalidFlightData();
    ArrayList<String> test2 = getValidFlightData();
    test2.add("last");

    assertThrows(IllegalArgumentException.class, () -> CommandLineParser.correctNumberOfArguments(test1));
    assertThrows(IllegalArgumentException.class, () -> CommandLineParser.correctNumberOfArguments(test2));

  }

}
