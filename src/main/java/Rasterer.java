import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    private static final String IMGFORMAT = ".png";
    private Quadtree qt;


    public Rasterer() {
        Quadtree.Node root = new Quadtree.Node(0, MapServer.ROOT_ULLAT, MapServer.ROOT_ULLON, MapServer.ROOT_LRLAT, MapServer.ROOT_LRLON, 8);
        qt = new Quadtree(root, 8);
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     * <p>
     * The grid of images must obey the following properties, where image in the
     * grid is referred to as a "tile".
     * <ul>
     * <li>The tiles collected must cover the most longitudinal distance per pixel
     * (LonDPP) possible, while still covering less than or equal to the amount of
     * longitudinal distance per pixel in the query box for the user viewport size. </li>
     * <li>Contains all tiles that intersect the query bounding box that fulfill the
     * above condition.</li>
     * <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     * </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     * forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        double queryLrlon = params.get("lrlon");
        double queryUllon = params.get("ullon");
        double queryUllat = params.get("ullat");
        double queryLrlat = params.get("lrlat");
        if (queryLrlon < queryUllon || queryLrlat > queryUllat) {
            System.out.println("the value of longitude and latitude is wrong");
        }
        double width = params.get("w");
        double queryxDisPerPixel = (queryLrlon - queryUllon) / width;
        Map<String, Object> results = new HashMap<>();
        List<List<Quadtree.Node>> tempLst = new ArrayList<>();
        qt.getRaster(params, qt.root, queryxDisPerPixel, results, tempLst);
        String[][] filesName = convertNodesListToArray(tempLst);
        results.put("render_grid", filesName);

        return results;
    }

    private String[][] convertNodesListToArray(List<List<Quadtree.Node>> lst) {
        String[][] result = new String[lst.size()][];
        for (int i = 0; i < lst.size(); i++) {
            List<Quadtree.Node> currRow = lst.get(i);
            result[i] = new String[currRow.size()];
            for (int j = 0; j < currRow.size(); j++) {
                result[i][j] = currRow.get(j).dir + IMGFORMAT;
            }
        }
        return result;
    }

}
