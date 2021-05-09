package ua.store.it;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.store.Application;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    private ByteArrayOutputStream out;


    @Before
    public void init() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @After
    public void clear() {
        out.reset();
    }

    @Test
    public void testApplicationMain_happyPath() {
        String expectedOutput =
                "\n Code           Name          Price     PromoPrice     PromoCount     TotalCount      TotalCost\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "    A          Apple           1,25           3,00           3,00           2,00           2,50\n" +
                        "    B     Watermelon           4,25           0,00           0,00           4,00          17,00\n" +
                        "    C         Banana           1,00           5,00           6,00           1,00           1,00\n" +
                        "    D          Lemon           0,75           0,00           0,00           2,00           1,50\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "                                                                    TOTAL COST:\t          22,00\n" +
                        "\n" +
                        "\n" +
                        " Code           Name          Price     PromoPrice     PromoCount     TotalCount      TotalCost\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "    A          Apple           1,25           3,00           3,00           1,00           1,25\n" +
                        "    B     Watermelon           4,25           0,00           0,00           3,00          12,75\n" +
                        "    C         Banana           1,00           5,00           6,00           0,00           0,00\n" +
                        "    D          Lemon           0,75           0,00           0,00           2,00           1,50\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "                                                                    TOTAL COST:\t          15,50\n" +
                        "\n" +
                        "Total cost of products with code 'ABCDABA' is 13,25 of some currency\n" +
                        " Code           Name          Price     PromoPrice     PromoCount     TotalCount      TotalCost\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "    A          Apple           1,25           3,00           3,00           3,00           3,00\n" +
                        "    B     Watermelon           4,25           0,00           0,00           2,00           8,50\n" +
                        "    C         Banana           1,00           5,00           6,00           1,00           1,00\n" +
                        "    D          Lemon           0,75           0,00           0,00           1,00           0,75\n" +
                        "--------------------------------------------------------------------------------------------------\n" +
                        "                                                                    TOTAL COST:\t          13,25\n" +
                        "\n";
        Application.main(new String[0]);
        String actualOutput = out.toString(StandardCharsets.UTF_8).replaceAll("\r\n", "\n");
        assertEquals(expectedOutput, actualOutput);
    }
}
