import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestRouter_2 {
    private static final String PARAMS_FILE = "C:\\Users\\info\\Desktop\\Beer_Maps\\proj3\\path_params.txt";
    private static final String RESULTS_FILE = "C:\\Users\\info\\Desktop\\Beer_Maps\\proj3\\path_results.txt";
    private static final int NUM_TESTS = 8;
    private static final String OSM_DB_PATH = "C:\\Users\\info\\Desktop\\Beer_Maps\\proj3\\library-sp18\\data\\berkeley-2018.osm.xml";
    private static GraphDB graph;
    private static boolean initialized = false;

    @Before
    public void setUp() throws Exception {
        if (initialized) {
            return;
        }
        graph = new GraphDB(OSM_DB_PATH);
        initialized = true;
    }

    @org.junit.Test
    public void testShortestPath() throws Exception {
        List<Map<String, Double>> testParams = paramsFromFile();
        List<List<Long>> expectedResults = resultsFromFile();

        for (int i = 0; i < NUM_TESTS; i++) {
            System.out.println(String.format("Running TestRouter_2: %d", i));
            Map<String, Double> params = testParams.get(i);
            List<Long> actual = Router.shortestPath(graph,
                    params.get("start_lon"), params.get("start_lat"),
                    params.get("end_lon"), params.get("end_lat"));
            List<Long> expected = expectedResults.get(i);
            assertEquals("Your results did not match the expected results", expected, actual);
        }
    }

    @org.junit.Test
    public void testSP1() throws Exception {
        System.out.println("testSP1");
        double lat1 = 37.84355594202374;//StdRandom.uniform(MapServer.ROOT_LRLAT, MapServer.ROOT_ULLAT);
        double lat2 = 37.88487653938814; //StdRandom.uniform(MapServer.ROOT_LRLAT, MapServer.ROOT_ULLAT);
        double lon1 = -122.21287047760431;//StdRandom.uniform(MapServer.ROOT_ULLON, MapServer.ROOT_LRLON);
        double lon2 = -122.21204510154362; //StdRandom.uniform(MapServer.ROOT_ULLON, MapServer.ROOT_LRLON);
        System.out.println(lat1 + " " +  lon1 + " " + lat2 + " " + lon2);
        List<Long> actual = Router.shortestPath(graph, lon1, lat1, lon2, lat2);
    }

    @org.junit.Test
    public void testShortestPath1() throws Exception {
        List<Map<String, Double>> testParams = paramsFromFile();
        List<List<Long>> expectedResults = resultsFromFile();

        for (int i = 0; i < 1000; i++) {
            System.out.println(String.format("Running TestRouter_2: %d", i));
            double lat1 = 37.88700291749453;//StdRandom.uniform(MapServer.ROOT_LRLAT, MapServer.ROOT_ULLAT);
            double lat2 = 37.835311134871276; //StdRandom.uniform(MapServer.ROOT_LRLAT, MapServer.ROOT_ULLAT);
            double lon1 = -122.2166204753628;//StdRandom.uniform(MapServer.ROOT_ULLON, MapServer.ROOT_LRLON);
            double lon2 =  -122.27222235070258; //StdRandom.uniform(MapServer.ROOT_ULLON, MapServer.ROOT_LRLON);
            System.out.println(lat1 + " " +  lon1 + " " + lat2 + " " + lon2);
            List<Long> actual = Router.shortestPath(graph, lon1, lat1, lon2, lat2);
        }
    }

    private List<Map<String, Double>> paramsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(PARAMS_FILE), Charset.defaultCharset());
        List<Map<String, Double>> testParams = new ArrayList<>();
        int lineIdx = 2; // ignore comment lines
        for (int i = 0; i < NUM_TESTS; i++) {
            Map<String, Double> params = new HashMap<>();
            params.put("start_lon", Double.parseDouble(lines.get(lineIdx)));
            params.put("start_lat", Double.parseDouble(lines.get(lineIdx + 1)));
            params.put("end_lon", Double.parseDouble(lines.get(lineIdx + 2)));
            params.put("end_lat", Double.parseDouble(lines.get(lineIdx + 3)));
            testParams.add(params);
            lineIdx += 4;
        }
        return testParams;
    }

    private List<List<Long>> resultsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(RESULTS_FILE), Charset.defaultCharset());
        List<List<Long>> expected = new ArrayList<>();
        int lineIdx = 2;
        for (int i = 0; i < NUM_TESTS; i++) {
            int numVertices = Integer.parseInt(lines.get(lineIdx));
            lineIdx++;
            List<Long> path = new ArrayList<>();
            for (int j = 0; j < numVertices; j++) {
                path.add(Long.parseLong(lines.get(lineIdx)));
                lineIdx++;
            }
            expected.add(path);
        }
        return expected;
    }
}
