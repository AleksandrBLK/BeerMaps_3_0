import org.xml.sax.SAXException;
import sun.security.provider.certpath.Vertex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /**
     * Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc.
     */
    public Map<Long, Vertex> verticesMap = new HashMap<>();
    private Map<Long, Edge> edgesMap = new HashMap<>();
    public TrieST<Location> locationTrieST = new TrieST<>();

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     *
     * @param dbPath Path to the XML file to be parsed.
     */
    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            // GZIPInputStream stream = new GZIPInputStream(inputStream);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputStream, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     *
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     * Remove nodes with no connections from the graph.
     * While this does not guarantee that any two nodes in the remaining graph are connected,
     * we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        // TODO: Your code here.
        HashSet<Long> removeItemIDs = new HashSet<>();
        for (Vertex v : verticesMap.values()) {
            // if nothing is connecting to this vertex, then delete it from the map
            if (v.adj.size() == 0) {
                removeItemIDs.add(v.id);
            }
        }
        verticesMap.keySet().removeAll(removeItemIDs);
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     *
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        //YOUR CODE HERE, this currently returns only an empty list.
        return verticesMap.keySet();
    }

    /**
     * Returns ids of all vertices adjacent to v.
     *
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {

        return verticesMap.get(v).adj;
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     *
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The great-circle distance between the two locations from the graph.
     */
    double distance(long v, long w) {
        return distance(lon(v), lat(v), lon(w), lat(w));
    }

    static double distance(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Returns the initial bearing (angle) between vertices v and w in degrees.
     * The initial bearing is the angle that, if followed in a straight line
     * along a great-circle arc from the starting point, would take you to the
     * end point.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     *
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    static double bearing(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double lambda1 = Math.toRadians(lonV);
        double lambda2 = Math.toRadians(lonW);

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2);
        x -= Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);
        return Math.toDegrees(Math.atan2(y, x));
    }

    /**
     * Returns the vertex closest to the given longitude and latitude.
     *
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    long closest(double lon, double lat) {

        if (verticesMap.keySet().size() == 0) {
            System.out.println("Nothing inside the Graph, cannot find the closet vertex");
        }
        List<Vertex> vertices = new ArrayList<>(verticesMap.values());
        Vertex closestVertex = null;
        double closestDistance = Double.MAX_VALUE;
        for (Vertex v : vertices) {
            double distance = distance(v.lon, v.lat, lon, lat);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestVertex = v;
            }
        }
        return closestVertex.id;
    }


    /**
     * Gets the longitude of a vertex.
     *
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    double lon(long v) {

        return verticesMap.get(v).lon;
    }

    /**
     * Gets the latitude of a vertex.
     *
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    double lat(long v) {

        return verticesMap.get(v).lat;
    }

    void add_vertex(long id, double lon, double lat) {
        Vertex v = new Vertex(id, lon, lat);
        verticesMap.put(v.id, v);
    }

    void delete_vertex(long id) {
        if (verticesMap.containsKey(id)) {
            verticesMap.remove(id);
        }
    }

    void add_edge(Long id, List<Long> vertexList, String name) {
        Edge e = new Edge(id, vertexList);
        edgesMap.put(id, e);
        for (int i = 0; i < vertexList.size() - 1; i++) {
            long v1ID = vertexList.get(i);
            long v2ID = vertexList.get(i + 1);
            Vertex v1 = verticesMap.get(v1ID);
            Vertex v2 = verticesMap.get(v2ID);
            v1.connectTo(v2ID, name);
            v2.connectTo(v1ID, name);
        }
    }


    static class Vertex {
        long id;
        String name;
        double lon;
        double lat;
        List<Long> adj;
        Map<Long, String> adjWay;

        Vertex(long id, double lon, double lat) {
            this.id = id;
            this.lon = lon;
            this.lat = lat;
            this.adj = new ArrayList<>();
            this.adjWay = new HashMap<Long, String>();
        }

        void setName(String name) {
            this.name = name;
        }

        void connectTo(long vertexId, String way) {
            adj.add(vertexId);
            adjWay.put(vertexId, way);
        }
    }

    static class Edge {
        private long id;
        private String name;
        private List<Long> vertexList;

        Edge(long id, List<Long> vertexList) {
            this.id = id;
            this.vertexList = vertexList;
        }

        void setName(String name) {

            this.name = name;
        }
    }

    static class Location {
        long id;
        String name;
        double lon;
        double lat;

        Location(long id, double lon, double lat, String name) {
            this.id = id;
            this.name = name;
            this.lon = lon;
            this.lat = lat;
        }
    }
}
