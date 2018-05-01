import org.junit.Before;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public abstract class AGMapTest {
    private static final double DOUBLE_THRESHOLD = 0.0000000000001;
    public static final String TEST_PARAMS = "C:\\Users\\info\\Desktop\\Beer_Maps\\proj3\\path_params.txt";
    public static final String IMG_ROOT = "C:\\Users\\info\\Desktop\\Beer_Maps\\proj3\\library-sp18\\data\\proj3_imgs";
    public static final String OSM_DB_PATH = "C:\\Users\\info\\Desktop\\Beer_Maps\\proj3\\library-sp18\\data\\berkeley-2018.osm.xml";

    protected static List<TestParameters> params;
    protected static boolean initialized = false;
    protected static Rasterer rasterer;
    protected static GraphDB graph;

    /**
     * Initializes the student MapServer statically.
     * Reads in the serialized <code>List</code> of TestParameters.
     * You should not need to modify this code. If you do, then the Autograder
     * may not work with your code.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        if (initialized) {
            return;
        }
        graph = new GraphDB(OSM_DB_PATH);
        rasterer = new Rasterer();
        MapServer.initialize();
        params = readTestParameters(TEST_PARAMS);
        initialized = true;
    }

    private List<TestParameters> readTestParameters(String filename) throws Exception {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<TestParameters> returnParams = (List<TestParameters>) ois.readObject();
        fis.close();
        return returnParams;
    }

    protected void checkParamsMap(String err, Map<String, Object> m1, Map<String, Object> m2) {
        for (String key : m1.keySet()) {
            assertTrue("Your results map is missing " + key, m2.containsKey(key));
            Object o1 = m1.get(key);
            Object o2 = m2.get(key);
            if (o1 instanceof Double) {
                assertTrue(err, Math.abs((Double) o1 - (Double) o2) < DOUBLE_THRESHOLD);
            } else if (o1 instanceof String[][]) {
                assertArrayEquals(err, (String[][]) o1, (String[][]) o2);
            } else {
                assertEquals(err, o1, o2);
            }
        }
    }
}