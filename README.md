# BeerMaps_3_0
Updated_BeerMaps_FromUpWork

<!DOCTYPE html>
<html>
<head>
    <title>Project 3 | CS 61B Spring 2017</title>
    <meta charset="UTF-8">

    <link href="../../../assets/css/common.css" rel="stylesheet" type="text/css">
    <link href="../../../assets/css/lab.css" rel="stylesheet" type="text/css">
    <link href="../../../assets/css/highlight/styles/tomorrow.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

    <script src="../../../assets/js/jquery.min.js" type="text/javascript"></script>
    <script src="../../../assets/css/highlight/highlight.pack.js" type="text/javascript"></script>
    <script src="../../../assets/js/sidebar.js" type="text/javascript"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <div id="navbar" class="navbar-top"><div id="navitems">
        <a href="../../../index.html"><div class="navitem">Main</div></a>
        <a href="../../../about.html"><div class="navitem">Course Info</div></a>
        <a href="../../../staff.html"><div class="navitem">Staff</div></a>
        <a href="../../../assign.html"><div class="navitem">Assignments</div></a>
        <a href="../../../resources.html"><div class="navitem">Resources</div></a>
        <a href="https://piazza.com/class/iiklg7j9ggf2vl"><div class="navitem">Piazza</div></a>
    </div></div>
    <div id="sidebar">
        <div id="sidebar-content">
            <h3>Project 3</br>Navigation</h3>
            <ul>
  <li><a href="#table-of-contents">Table of Contents</a></li>
  <li><a href="#introduction">Introduction</a></li>
  <ul>
    <li><a href="#meta-advice">Meta Advice</a></li>
  </ul>
  <li><a href="#getting-the-skeleton-files">Getting the Skeleton Files</a></li>
  <ul>
    <li><a href="#addendum-for-terminal-users">Addendum for Terminal users</a></li>
  </ul>
  <li><a href="#overview">Overview</a></li>
  <ul>
    <li><a href="#application-structure">Application Structure</a></li>
    <li><a href="#assignment-overview">Assignment Overview</a></li>
  </ul>
  <li><a href="#map-rastering-part-i-overview">Map Rastering (Part I Overview)</a></li>
  <li><a href="#map-rastering-api">Map Rastering (API)</a></li>
  <ul>
    <li><a href="#suggested-approach-for-rastering-quadtree">Suggested Approach for Rastering: QuadTree</a></li>
    <li><a href="#extra-details-and-corner-cases">Extra Details and Corner Cases</a></li>
    <ul>
      <li><a href="#runtime">Runtime</a></li>
      <li><a href="#addendum">Addendum</a></li>
    </ul>
  </ul>
  <li><a href="#routing-amp-location-data-part-ii">Routing &amp; Location Data (Part II)</a></li>
  <li><a href="#route-search-part-iii">Route Search (Part III)</a></li>
  <ul>
    <li><a href="#runtime-amp-a">Runtime &amp; A*</a></li>
    <li><a href="#supplemental-information">Supplemental Information</a></li>
  </ul>
  <li><a href="#autocompletion-and-search-10-gold-points">Autocompletion and Search (10 gold points)</a></li>
  <ul>
    <li><a href="#locations">Locations</a></li>
    <li><a href="#autocomplete">Autocomplete</a></li>
    <ul>
      <li><a href="#runtime">Runtime</a></li>
    </ul>
    <li><a href="#search">Search</a></li>
    <ul>
      <li><a href="#runtime">Runtime</a></li>
    </ul>
  </ul>
  <li><a href="#possible-extensions-optional">Possible Extensions (optional)</a></li>
  <ul>
    <li><a href="#front-end-integration">Front-end Integration</a></li>
    <li><a href="#vectored-tiles">Vectored Tiles</a></li>
    <li><a href="#the-camera-amp-the-quadtree">The Camera &amp; The Quadtree</a></li>
  </ul>
  <li><a href="#deploying-to-quilt">Deploying to Quilt</a></li>
  <ul>
    <li><a href="#what-is-quilt">What is Quilt</a></li>
    <li><a href="#aws-account">AWS Account</a></li>
    <li><a href="#installing-quilt">Installing Quilt</a></li>
    <ul>
      <li><a href="#windows">Windows</a></li>
      <li><a href="#mac-and-linux">Mac and Linux</a></li>
    </ul>
    <li><a href="#run-bearmaps">Run BearMaps</a></li>
    <li><a href="#accessing-your-bearmaps">Accessing Your BearMaps</a></li>
    <li><a href="#stopping-quilt">Stopping Quilt</a></li>
  </ul>
  <li><a href="#faq">FAQ</a></li>
  <ul>
    <li><a href="#i-provided-the-correct-string-output-but-it-doesn-t-show-up">I provided the correct String<a href=""></a> output but it doesn't show up!</a></li>
    <li><a href="#my-initial-map-doesn-t-fill-up-the-screen">My initial map doesn't fill up the screen!</a></li>
    <li><a href="#in-the-browser-zooming-out-only-appears-to-shift-the-map-and-i-m-confident-my-rastering-code-is-correct">In the browser, zooming out only appears to shift the map, and I'm confident my rastering code is correct</a></li>
    <li><a href="#i-don-t-draw-the-entire-query-box-on-some-inputs-because-i-don-t-intersect-enough-tiles">I don't draw the entire query box on some inputs because I don't intersect enough tiles.</a></li>
    <li><a href="#do-i-construct-my-quadtree-in-one-pass-or-do-i-insert-into-it">Do I construct my quadtree in one pass or do I insert into it?</a></li>
    <li><a href="#what-s-a-quadtree-intersection-query">What's a quadtree intersection query?</a></li>
    <li><a href="#how-do-i-keep-my-quadtree-code-simple-when-calculating-rectangle-intersections-this-feels-insane">How do I keep my Quadtree code simple when calculating rectangle intersections? This feels insane.</a></li>
    <li><a href="#i-m-getting-funky-behavior-with-moving-the-map-around-my-image-isn-t-large-enough-at-initial-load-after-the-initial-load-i-can-t-move-the-map-or-after-the-initial-load-i-start-getting-nan-as-input-params">I'm getting funky behavior with moving the map around, my image isn't large enough at initial load, after the initial load, I can't move the map, or after the initial load, I start getting NaN as input params.</a></li>
    <li><a href="#i-sometimes-pass-the-timing-tests-when-i-submit-but-not-consistently">I sometimes pass the timing tests when I submit, but not consistently.</a></li>
  </ul>
  <li><a href="#common-bugs">Common Bugs</a></li>
  <li><a href="#acknowledgements">Acknowledgements</a></li>
</ul>
        </div>
    </div>
    <div id="content-container"><main id="content">
        <header class="title">Project 3: Bear Maps, version 2.0</header>
<h2 id="table-of-contents">Table of Contents</h2>


<ul>
  <li><a href="#introduction">Introduction</a></li>
  <li><a href="#overview">Overview</a></li>
  <li><a href="#getting-the-skeleton-files">Getting the Skeleton Files</a></li>
  <li><a href="#map-rastering">Rastering</a></li>
  <li><a href="#routing">Routing</a></li>
  <li><a href="#autocompletion-and-search">Autocompletion and Search</a></li>
  <li><a href="#faq">Frequently Asked Questions</a></li>
  <li><a href="#common-bugs">Common Bugs</a></li>
  <li><a href="#acknowledgements">Acknowledgements</a></li>
</ul>


<h2 id="introduction">Introduction</h2>


<p>This project was originally created by former TA Alan Yao (now at AirBNB). It is a web mapping application inspired by Alan's time on the Google Maps team and the <a href="http://www.openstreetmap.org/">OpenStreetMap</a> project, from which the tile images and map feature data was downloaded. You are working with real-world mapping data here that is freely available - after you've finished this project, you can even extend your code to support a wider range of features. You will be writing the back end - the web server that powers the API that the front end makes requests to. The <a href="https://www.youtube.com/playlist?list=PL8FaHk7qbOD7rrjWbRN-2W0EpxXK5CNW-">project 3 video playlist</a> starts with an introductory video that should help you get started. The slides used in the project 3 video playlist are <a href="https://docs.google.com/presentation/d/1lZ6Sm9DExLWZuYKUpmpkPPzW_gyWJfyhqaoPWXbnHts/edit?usp=sharing">available here</a>.</p>

<p>This project is a <strong>solo project</strong>. You should not work with a partner. One of our biggest goals for 61B is to develop your independence as a programmer, and this project is a great milestone for you to complete on your own before you go on to bigger and better things.</p>

<p>The point breakdown for this 75 point project are as follows: 50 points for Part I. 25 points for Parts II and III (exact distribution TBA). You can also earn 3 extra credit points for submitting to the extra credit autograder by Sunday 4/16/2017, which will cover only Part I. 10 gold points can be earned for completing Autocomplete.</p>

<p>By the end of this project, with some extra work, you can even host your application as a web app. More details will be provided at the end of this spec.</p>

<p>There is a reference version of the project solution running on Amazon AWS here. While we can't guarantee that the performance will be good, you'll likely get response times that are only slightly slower than your locally hosted version. Why? Because we're using <a href="http://quilt.io">Quilt</a> to deploy the solution in such a way that all your requests will be load balanced across ~10 separate instances of BearMaps. That being said, they're still running on free instances, so it might slow up under high load. If you are experiencing a lot of latency, shrink your window. Your project cannot perform poorly though, and should have sub-0.5s response times, especially since you are hosting locally.</p>


<h4 id="meta-advice">Meta Advice</h4>


<p><em>This spec is not meant to be complete.</em> Many design decisions are left to you, although with suggestions given. Many implementation details are not given; you are expected to read the entirety of the skeleton (which is well-commented or self-explanatory) and the javadoc to determine how to proceed. You will especially want to read all the constants defined.</p>

<p>However, the spec is written in a way so that you can proceed linearly down - that is, while each feature is partially dependent on the previous one, your design decisions, as long as they are generally reasonable, should not obstruct how you think about implementing the following parts. You <strong>are required to read the entire spec section before asking questions</strong>. If your question is answered in the spec, we will only direct you to the spec.</p>


<h2 id="getting-the-skeleton-files">Getting the Skeleton Files</h2>


<p>For this project we <strong>very strongly</strong> recommend using IntelliJ. If IntelliJ doesn't work on your computer, or is too slow, consider using IntelliJ on the lab machines. If you insist, you can also use the command-line / terminal on your personal machine as further described in <code>Addendum for Terminal users</code>.</p>

<p>Pull the skeleton using the command <code>git pull skeleton master</code>.  Then, please download <a href="https://people.eecs.berkeley.edu/~jhug/p3imgs.zip">this zip file</a>; it is the image tile dataset. Unzip it into your proj3/ folder such that there is an img/ directory, with all the png files in it. There are around 50,000 files in this folder, so it might take a bit of time to unzip. Don't use the <strong>-f</strong> flag with GitHub for this project unless you're sure that you're not adding these .png files to your rebo as a result. If you don't know what we're talking about, the basic idea is that you shouldn't use <strong>-f</strong> unless you have a really good reason.</p>

<p>Project 3 uses <a href="https://maven.apache.org/">Apache Maven</a> as its build system; it integrates with IntelliJ. You will want to create a new IntelliJ project for project 3. In IntelliJ, go to New -> Project from Existing Sources. Then:</p>

<ol>
  <li>Select your proj3 folder, press next, and make sure to select "Import project from external model" and select Maven. Press next.</li>
  <li>At the Import Project window, check: "Import Maven projects automatically".</li>
  <li>Press next until the end.</li>
  <li>It is possible that IntelliJ will not "mark" your folders correctly: Make sure to mark, if not done so already, the<code>src/main/java</code> directory as a sources root, the <code>src/static</code> directory as a sources root, and the <code>src/test/java</code> directory as a test sources root. To do this, right click on the folder in the explorer window (the tab on the left), and towards the bottom of the options you'll see "Mark Directory As".</li>
  <li><strong>Do not</strong> add the course javalib to your IntelliJ library. You will not need it and it will cause conflicts. This also means that <strong>you cannot use any libraries outside the Java standard library and the ones already included in the project</strong>. Doing so will immediately cause a compilation error on the autograder. Notably, we are not accommodating usage of the Princeton libraries as they are unnecessary.</li>
</ol>

<p>Build the project, run <code>MapServer.java</code> and navigate your browser (Chrome preferred; errors in other browsers will not be supported) to <code>localhost:4567</code>. This should load up <code>map.html</code>; by default, there should be a blank map. You can also run <code>MapServer.java</code> and then open up <code>src/static/page/map.html</code> manually by right clicking and going to Open In Browser in IntelliJ. Once you've done this, you should see something like the window below, where the front end is patiently waiting on your back end (not yet implementing) to provide image data.</p>

<p><img src="startup.png" alt="startup"></p>

<p>If you get a 404 error, make sure you have marked your folders as described in step 4 above.</p>

<p><strong>Absolutely make sure to end your instance of <code>MapServer</code> before re-running or starting another up.</strong> Not doing so will cause a <code>java.net.BindException: Address already in use</code>. If you end up getting into a situation where you computer insists the address is already in use, either figure out how to kill all java processes running on your machine (using Stack Overflow or similar) or just reboot your computer.</p>


<h4 id="addendum-for-terminal-users">Addendum for Terminal users</h4>


<p>If you do want to use it through the command line here are some basic instructions:
Windows users: Follow the instructions <a href="https://maven.apache.org/guides/getting-started/windows-prerequisites.html">here</a>, making sure to adjust them to your machine which should already have JDK8 installed. Use command prompt, not git bash.
Mac users: <code>brew install maven</code>
Ubuntu users: <code>sudo apt&#x2d;get install maven</code>.</p>

<p>You can then use the <code>mvn compile</code> and <code>mvn exec:java &#x2d;Dexec.mainClass=&quot;MapServer&quot;</code> targets to run <code>MapServer</code>, after patching your pom.xml to include <code>src/static</code> as a sources root. Do so by renaming <code>pom_alternate.xml</code> to <code>pom.xml</code>. You can also run the tests with <code>mvn test</code>.
Choosing to work through terminal may slow down your development cycle considerably as you will not have access to the debugger.</p>


<h2 id="overview">Overview</h2>


<!--There is a <a href="https://youtu.be/J4QNk3hwcR8">Getting Started video</a> that accompanies the spec. This video is completely optional, but it gives some tips and visual motivation for some of the things you're doing in this assignment.-->

<!--

Firstly, we make one simplifying assumption: the world is almost flat. We will be working with longitudes (x-axis) and latitudes (y-axis); because these metrics are defined using the <a href="https://en.wikipedia.org/wiki/Mercator_projection">Mercator projection</a>, the latitudes will be slightly distorted over long distances. We will instead only work inside a small world-region, the area surrounding Berkeley; this makes latitude distortions largely trivial and prevents you from having to deal with more complex math in your calculations. Essentially, this allows you to interpret lon and lat as x and y coordinates, and distances as linear, which is good for linear interpolation.-->


<h3 id="application-structure">Application Structure</h3>


<p>Your job for this project is to implement the back end of a web server. To use your program, a user will open an html file in their web browser that displays a map of the city of Berkeley, and the interface will support scrolling, zooming, and route finding (similar to Google Maps). We've provided all of the <a href="https://en.wikipedia.org/wiki/Front_and_back_ends">"front end"</a> code. Your code will be the "back end" which does all the hard work of figuring out what data to display in the web browser.</p>

<!--
Let's look at a quick example:

<img src="endpoint.PNG" alt="endpoint">-->

<p>At its heart, the project works like this: The user's web browser provides a URL to your Java program, and your Java program will take this URL and generate the appropriate output, which will displayed in the browser. For example, suppose your program were running at bloopblorpmaps.com, the URL might be:</p>

<pre><code>bloopblorpmaps.com/raster&amp;ullat=37.89&amp;ullon=&#x2d;122.29&amp;lrlat=37.83&amp;lrlon=&#x2d;122.22&amp;w=700&amp;h=300</code></pre>

<p>The job of the back end server (i.e. your code) is take this URL and generate an image corresponding to a map of the region specified inside the URL (more on how regions are specified later). This image will be passed back to the front end for display in the web browser.</p>

<!--You'll notice the web address is strange, starting with localhost:4365 instead of something like www.eecs.berkeley.edu. localhost means that the website is on your own computer, and the "4365" refers to a port number, something that you'll learn about in a future class.-->

<p>We'll not only be providing the front end (in the form of .html and javascript files), but we'll also provide a great deal of starter code (in the form of .java files) for the back end. This starter code will handle all URL parsing and communication for the front end. This code uses the <a href="http://sparkjava.com/documentation.html#getting-started">Java Spark</a> as the server framework. You don't need to worry about the internals of this as we are providing the skeleton code to handle the endpoints.</p>

<!--
We are also providing you with a file, <code>map.html</code>, in <code>src/static/page</code>, which implements a basic front-end user interface. This basic Javascript application makes the necessary API calls to render a map that can be navigated around and can show routes and locations. -->


<h3 id="assignment-overview">Assignment Overview</h3>


<p>You will implement three required classes for this project, in addition to any helper classes that you decide to create. These three classes are <code>Rasterer</code>, <code>GraphDB</code>, and <code>Router</code>. They are described very briefly below. More verbose descriptions follow.</p>

<p>The <code>Rasterer</code> class will take as input an upper left latitude and longitude, a lower right latitude and longitude, a window width, and a window height. Using these six numbers, it will produce a 2D array of filenames corresponding to the files to be rendered. This will be the most confusing and time consuming part of the project.</p>

<p>The <code>GraphDB</code> class will read in the Open Street Map dataset and store it as a graph. Each node in the graph will represent a single intersection, and each edge will represent a road. How you store your graph is up to you. This will be the strangest part of the project.</p>

<p>The <code>Router</code> class will take as input a <code>GraphDB</code>, a starting latitude and longitude, and a destination latitude and longitude, and it will produce a list of nodes (i.e. intersections) that you get from the start point to the end point. This part will be similar to the 8Puzzle assignment, since you'll be implementing A* again, but now with an explicit graph object (that you build in <code>GraphDB</code>).</p>

<p>Warning: Unlike prior assignments in your CS classes, we'll be working with somewhat messy real world data. This is going to be frustrating at times, but it's an important skill and something that we think will serve you well moving forwards. If you're someone who thinks of yourself as a weaker programmer, make sure to start ASAP.</p>

<p>The biggest challenges in this assignment will be understanding what rastering is supposed to do (it's tricky!), as well as figuring out how to parse XML files for <code>GraphDB</code>.</p>

<!--The <code>Rasterer</code> class will be the bulk of the points (55/75 points), even though it is not the bulk of the work. -->

<!--### Testing

<code>AGMapServerTest.java</code> in <code>src/test/java</code> is a local version of our autograder that you can run. It takes in serialized validation data from <code>test_ser_data</code> and checks your results against it. You are not recommended to use this to drive your development; it's to give you a sense of expected outputs based on inputs. Effective strategies for debugging do not include: running the JUnit tests over and over again while making small changes each time, staring at the error messages and then posting on piazza asking what they mean before reading the whole message and trying to interpret it; effective strategies include: using the debugger; reproducing your buggy inputs; <a href="https://en.wikipedia.org/wiki/Rubber_duck_debugging">rubber ducky debugging</a>. You can feel free to modify this file as you want. We will not be testing your code on the same data as is given to you for your local testing - that is, as a disclaimer, if you pass all the tests in this file, you are not guaranteed to pass all the tests on the actual autograder.

There is also a file, <code>test.html</code>, that you can use to test your project without using the front-end. It makes a /raster API call and draws the result. You can modify the query parameters in the file. It is not the only way you should test your project, but is there to prevent you from having to learn Javascript to test your server, and allows you to make a specific call easily. You will also find your browser's Javascript console handy, especially when opened on <code>map.html</code>: for example, on Windows, in Chrome you can press F12 to open up the developer tools, click console, and enter in params to get the value of the current query parameters for the map, and <code>route_params</code> to get the value of the current query parameters for your route. It should look something like this:
<img src="js_console.JPG" alt="Console">

Additionally you can use <a href="https://chrome.google.com/webstore/detail/postman-interceptor/aicmkgpgakddgnaphhhpliifpcfhicfo?hl=en">Postman Interceptor</a>. It's a Chrome extension with a good visual interface for setting up queries, tracking requests made by your browser, and modifying params and examining responses. It's an industry standard and I highly recommend it.
-->

<!--### API Documentation

We support four GET endpoints.

The request handlers defined in <code>MapServer.java</code> process the HTTP GET request, pulling out the requests' required attributes and values into a <code>Map&lt;String, Double&gt;</code> and dispatches the work to methods that you implement. See the <code>MapServer::main</code> method and read the code: these are the various request handlers that dispatch work to the methods below and are called every time the HTTP endpoints are requested. These methods must satisfy the requirements given in the Javadoc.

You will want to **read through the <a href="doc/index.html">Javadoc</a>**. It describes all the requirements for implementation, and defines the inputs and outputs of each method. Additionally, you will want to read through the comments in the skeleton code. They explain how the server handlers invoke your methods and encode the responses. You may also find the Javadoc in the skeleton code.

Optionally, you can read through <code>map.html</code>, <code>map.css</code>, and <code>map.js</code>. They are not an example of good web design style, but you can see how the front-end makes the API calls.
-->


<h2 id="map-rastering-part-i-overview">Map Rastering (Part I Overview)</h2>


<p>Rastering is the job of converting information into a pixel-by-pixel image. In the <code>Raster</code> class you will take a user's desired viewing rectangle and generate an image for them.</p>

<p>The user's desired input will be provided to you as a <code>Map&lt;String, Double&gt; params</code>, and the main goal of your rastering code will be to create a <code>String[][]</code> that corresponds to the files that should be displayed in response to this query.</p>

<p>As a specific example (given as "testTwelveImages.html" in the skeleton files), the user might specify that they want the following information:</p>

<pre><code>{lrlon=&#x2d;122.2104604264636, ullon=&#x2d;122.30410170759153, w=1085.0, h=566.0, ullat=37.870213571328854, lrlat=37.8318576119893}</code></pre>

<p>This means that the user wants the area of earth delineated by the rectangle between longitudes -122.2104604264636 and -122.30410170759153 and latitudes 37.870213571328854 and 37.8318576119893, and that they'd like them displayed in a window roughly 1085 x 566 pixels in size (width x height). We call the user's desired display location on earth the <strong>query box</strong>.</p>

<p>To display the requested information, you need street map pictures, which we have provided in the p3imgs.zip file (not posted yet, but if you want to get a sneak peak, you can download it <a href="https://people.eecs.berkeley.edu/~jhug/p3imgs.zip">here</a>) All of the images provided are 256 x 256 pixels. Each image is at various levels of zoom. For example, the files <code>1.png</code>, <code>2.png</code>, <code>3.png</code> and <code>4.png</code> are at the lowest resoution (i.e. lowest level of zoom). Together they constitute a very zoomed out picture of Berkeley, with 1 in the northwest, 2 in the northeast, 3 in the southwest, and 4 in the southeast.</p>

<p>For every file, there are four higher resolution images of the same area. For example <code>11.png</code>, <code>12.png</code>, <code>13.png</code> and <code>14.png</code> corresponding to the northwest, northeast, southwest, and southeast corners of <code>1.png</code>.</p>

<p>The job of your rasterer class is decide, given a user's query, which files to serve up. For the example above, your code should return the following 2D array of strings:</p>

<pre><code>[[img/13.png, img/14.png, img/23.png, img/24.png],
[img/31.png, img/32.png, img/41.png, img/42.png],
[img/33.png, img/34.png, img/43.png, img/44.png]]</code></pre>

<p>This means that the browser should display <code>13.png</code> in the top left, then <code>14.png</code> to the right of <code>13</code>, and so forth. Thus our "rastered" image is actually a combination of 12 images arranged in 3 rows of 4 images each.</p>

<p>Our <code>MapServer</code> code will take your 2D array of strings and display the appropriate image in the browser. If you're curious how this works, see <code>writeImagesToOutputStream</code>.</p>

<p>Since each image is 256 x 256 pixels, the overall image given above will be 1024 x 768 pixels. There are many combinations of images that cover the query box. For example, we could instead use higher resolution images of the exact same areas of Berkeley. Thus, instead of using <code>13.png</code>, we could have used <code>131.png</code>, <code>132.png</code>, <code>133.png</code>, <code>134.png</code> while also substituting <code>14.png</code> by <code>141.png</code>, <code>142.png</code>, etc. The result would be total of 48 images arranged in 6 rows of 8 images each (make sure this makes sense!). This would result in a 2048 x 1536 pixel image.</p>

<p>You might be tempted to say that a 2048 x 1536 image is more appropriate, since the user requested 1085 x 556 since 1024 x 768 is too small in the width direction. However, that's not the way that we decide which images to use.</p>

<p>Instead, to rigorously determine which images to use, we will define the <strong>longitudinal distance per pixel (LonDPP)</strong> as follows: Given a query box or image, the LonDPP of that box or image is the (lower right longitude - upper left longitude) / (width of the image/box in pixels). For example, for the query box in the example in this section, the LonDPP is (-122.2104604264636 + 122.30410170759153) / (1085) or 0.00008630532 units of longitude per pixel. At Berkeley's latitude, this is very roughly 25 feet of distance per pixel.</p>

<p>The images that you return as a <code>String[][]</code> when rastering must be those that:</p>

<ul>
  <li>Include any region of the query box. (an earlier version of the spec said this in a more confusing way)</li>
  <li>Have the greatest LonDPP that is less than or equal to the LonDPP of the query box (as zoomed out as possible).</li>
</ul>

<p>For <code>1.png</code>, the LonDPP is <code>0.000171661376953125</code> which is greater than the LonDPP of the query box, and is thus unusable. This makes intuitive sense: If the user wants an image which covers roughly 25 feet per pixel, we shouldn't use an image that covers ~50 feet per pixel because the resolution is too poor. For <code>13.png</code>, the LonDPP is 0.0000858306884765625, which is better (i.e. smaller) than the user requested, so this is fine to use. For <code>131.png</code>, the LonDPP is 0.00004291534423828125. This is also smaller than the desired LonDPP, but using it is overkill since <code>13.png</code> is already good enough. In my head, I find it useful to think of LonDPP as "blurriness", i.e. <code>1.png</code> is the blurriest (most zoomed out/highest LonDPP).</p>

<p>As an example of an intersection query, consider the image below, which summarizes key parameter names and concepts. In this example search, the query box intersects 9 of the 16 tiles at depth 2.</p>

<p><img src="rastering_example.png" alt="rastering_example"></p>

<p>For a demo of how the provided images are arranged, see this <a href="FileDisplayDemo.html">FileDisplayDemo</a>. Try typing in a filename, and it will show what region of the map this file corresponds to, as well as the exact coordinates of its corners, in addition to the distance per pixel.</p>

<p>One natural question is: Why not use the best quality images available (i.e. smallest LonDPP, e.g. 7 character filenames like <code>4443414.png</code>)? The answer is that the front end doesn't do any rescaling, so if we used ultra low LonDPPs for all queries, we'd end up with absolutely gigantic images displayed in our web browser.</p>


<h2 id="map-rastering-api">Map Rastering (API)</h2>


<p>In Java, you will implement the Rasterer by filling in a single method:</p>

<pre><code>public Map&lt;String, Object&gt; getMapRaster(Map&lt;String, Double&gt; params)</code></pre>

<p>The given params are described in the skeleton code. An example is provided in the "Map Rastering (Overview)" section above, and you can always try opening up one of our provided html files and simply printing out <code>params</code> when this method is called to see what you're given.</p>

<p>Your code should return a <code>Map&lt;String, Object&gt;</code> as described in the Javadocs (the /** */ comments in the skeleton code). We do this as a way to hack around the fact that Java methods can only return one value. This map includes not just the two dimensional array of strings, but also a variety of other useful information that will be used by your web browser to display the image nicely (e.g. "raster_width" and "raster_height"). See the Javadocs for more details.</p>

<!--

Let us define a metric, the distance per pixel. Treat the (lon, lat) of some point on the map the same as an (x, y) point. Then the longitudinal distance per pixel covered on a tile is just (lower right longitude - upper left longitude) / (width of the image). This defines how fine or coarse the resolution of a tile is. If we cover a lot of distance per pixel on a tile, then that means the tile is more zoomed out and closer to the quadtree root; if we cover less distance per pixel, then that means the tile is more zoomed in (corresponding to being lower in the quadtree). Note that the longitudinal (horizontal) distance per pixel is not the same as the latidudinal (vertical) distance per pixel. This is because the earth is curved. If you use the wrong one, or use them interchangably, you will have incorrect results.

If you've represented your tile hierarchy as a quadtree, you are looking to collect all tiles that intersect (overlap) the query window that have a depth that is as close to the root as possible, but still satisfy the condition that the tiles should have a longitudinal distance per pixel less than or equal to the longitudinal distance per pixel in the query box. This enforces that one pixel in the query box is covered by at least one pixel in the rastered image - we want to be as zoomed out as possible, but don't want to give an image smaller than the width and height given in the query box, but we don't want our image to be the maximum resolution either. Essentially, you should be able to recursively traverse your quadtree until you find the tiles that both intersect and satisfy the distance per pixel requirement, and collect each of these full tiles with no need to crop. Take a while to think about how this method satisfies our requirements before asking a friend - it's very confusing the first time around.
-->


<h3 id="suggested-approach-for-rastering-quadtree">Suggested Approach for Rastering: QuadTree</h3>


<p>A <em>quadtree</em> is a tree data structure typically used to represent spatial data, described during <a href="https://docs.google.com/presentation/d/1ifkiC-l0DfQRXEHFfQpg_AcZkaUyj9CCEUKOYPuyBZ0/edit#slide=id.g11f3cf3f77_0_195">lecture 25</a>. A node in a Quadtree is a square in the plane; for this project, each square node will be called a tile interchangeably, and is defined by its upper left and lower right points. Unlike a Binary Tree, a node has four children; each child is a subdivided fourth of its parent, as shown below.</p>

<p><img src="qt.png" alt="Quadtree"></p>

<p>As discussed above, you are provided map data in the <code>img</code> directory as a large set of 256x256 png image files, which we'll call tiles. The filename determines the relationship between one tile and another, as shown above. The easiest way to complete the rastering part of this project is to build a quad-tree where each quad-tree node corresponds to an image tile. <code>11.png,  12.png, 13.png, 14.png</code> are the four quadrant subdivisions of <code>1.png</code> and so on. The longitudes and latitudes of the root node, which is to be subdivided, are given to you as constants <code>ROOT_ULLAT, ROOT_ULLON, ROOT_LRLAT, ROOT_LRLON</code>.</p>

<p>For example, the upper left child of the root, represented by <code>1.png</code>, shares an upper left longitude and latitude with the root, but has a lower right longitude and latitude that is at the center of the root tile, and so on - the structure is defined recursively. If a tile has no children, for example <code>4444444.png</code>, there are no valid files <code>44444441.png</code> and so on.</p>

<p>This helps construct the map since all tiles are the same resolution; you might think of traversing to a child of a node as "zooming in" on that quadrant. Each level of the quadtree has its own LonDPP, where the root has a relatively large LonDPP, and as you go deeper the LonDPP gets smaller (since the images are more zoomed in and thus you get less distance per pixel).</p>

<p>The problem of finding the correct images for a given query is thus equivalent to going to the shallowest level whose LonDPP is less than or equal to the query box, and finding all images at that level that intersect the query box.</p>

<p>This will all be pretty confusing at first. We highly recommend playing around with the <a href="FileDisplayDemo.html">FileDisplayDemo</a> to gain more intuition.</p>


<h3 id="extra-details-and-corner-cases">Extra Details and Corner Cases</h3>


<p>When the client makes a call to <code>/raster</code> with the required parameters, the request handler will validate that all the required parameters are present (as declared in <code>REQUIRED_RASTER_REQUEST_PARAMS</code>. Then, in the Map <code>params</code>, those parameters are keys that you will be able to get the value of: for example, if I wanted to know the upper left point's longitude of the query rectangle, I could call <code>params.get(&quot;ullon&quot;)</code>.</p>

<!
The query window shown above corresponds to the viewing window in the client. Although you are returning a full image, it will be translated (with parts off the window) appropriately by the client. There is one edge case that you may want to consider (although if you write your code naturally, it may not need to be explicitly handled): your query window in pixels may not be perfectly proportional to your query window in world-space distance (lat and lon). However, you only care about the pixels for dpp and lat and lon for intersection. -->

<p>You may end up with an image, for some queries, that ends up not filling the query box and that is okay - this arises when your latitude and longitude query do not intersect enough tiles to fit the query box. You can imagine this happening with a query very near the edge (in which case you just don't collect tiles that go off the edge); a query window that is very large, larger than the entire dataset itself; or a query window in lat and lon that is not proportional to its size in pixels. For example, if you are extremely zoomed in, you have no choice but to collect the leaf tiles and cannot traverse deeper.</p>

<p>You can also imagine that the user might drag the query box to a location that is completely outside of the root longitude/latitudes. In this case, there is nothing to raster, <code>raster_ul_lon</code>, <code>raster_ul_lat</code>, etc. are arbitrary, and you should set <code>query_success: false</code>. If the server receives a query box that doesn't make any sense, eg. <code>ullon, ullat</code> is located to the right of <code>lrlon, lrlat</code>, you should also ensure <code>query_success</code> is set to false.</p>


<h4 id="runtime">Runtime</h4>


<p>Your constructor should take time linear in the number of tiles given.</p>

<p>You may not iterate through / explore all tiles to search for intersections. Suppose there are <code>k</code> tiles intersecting a query box, and <code>n</code> tiles total. Your entire query must run in <code>O(k log k + log n)</code> time (theoretically, on a tree of unbounded depth and size), including constructing the image. This can be broken up into two parts: <code>O(log n)</code> time to traverse the quadtree to where we begin collecting, and <code>O(k log k)</code> time to collect and arrange the intersected tiles. This should correspond to the standard quadtree traversal. It can be done faster than this, but remember that big-O is an upper bound.</p>


<h4 id="addendum">Addendum</h4>


<p>You will get latitude and longitude mixed up at least once. Make sure to check for that!</p>


<h2 id="routing-amp-location-data-part-ii">Routing &amp; Location Data (Part II)</h2>


<p>In this part of the project, you'll use a real world dataset combined with an industrial strength dataset parser to construct a graph. This is similar to tasks you'll find yourself doing in the real world, where you are given a specific tool and a dataset to use, and you have to figure out how they go together. It'll feel shaky at first, but once you understand what's going on it won't be so bad.</p>

<p>Routing and location data is provided to you in the <code>berkeley.osm</code> file. This is a subset of the full planet's routing and location data, pulled from <a href="http://download.bbbike.org/osm/">here</a>. The data is presented in the <a href="http://wiki.openstreetmap.org/wiki/OSM_XML">OSM XML file format</a>.
<br/>

XML is a markup language for encoding data in a document. Open up the <code>berkeley.osm</code> file for an example of how it looks. Each element looks like an HTML tag, but for the OSM XML format, the content enclosed is (optionally), more elements. Each element has attributes, which give information about that element, and sub-elements, which can give additional information and whose name tell you what kind of information is given.</p>

<p>The first step of this part of the project is to build a graph representation of the contents of <code>berkeley.osm</code>. We have chosen to use a SAX parser, which is an "event-driven online algorithm for parsing XML documents". It works by iterating through the elements of the XML file. At the beginning and end of each element, it calls the <code>startElement</code> and <code>endElement</code> callback methods with the appropriate parameters.</p>

<p>Your job will be to override the <code>startElement</code> and <code>endElement</code> methods so that when the SAX parser has completed, you have built a graph. Understanding how the SAX parser works is going to be tricky.</p>

<p>You will find the Javadocs for <a href="doc/GraphDB.html">GraphDB</a> and <a href="doc/GraphBuildingHandler.html">GraphBuildingHandler</a> helpful, as well as the example code in <code>GraphBuildingHandler.java</code>, which gives a basic parsing skeleton example. There is an example of a completed handler in the <code>src/main/java/example</code> folder called <code>CSCourseDBHandler.java</code> that you might find helpful to look at.</p>

<p>Read through the OSM wiki documentation on the various relevant elements: the <a href="http://wiki.openstreetmap.org/wiki/Tags">idea of a tag</a>, the <a href="http://wiki.openstreetmap.org/wiki/Key:highway">highway key</a>, <a href="http://wiki.openstreetmap.org/wiki/Way">the way element</a>, and <a href="http://wiki.openstreetmap.org/wiki/Node">the node element</a>. You will need to use all of these elements, along with their attribute's values, to construct your graph for routing.</p>

<p>The <code>node</code>:
<img src="node_xml.jpg" alt="node"></p>

<p>comprises the backbone of the map; the lat, lon, and id are required attributes of each node. They may be anything from locations to points on a road. If a node is a location, a tag element, with key "name" will tell you what location it is - above, we see an example.</p>

<p>The <code>way</code>:
<img src="way_xml.jpg" alt="way"></p>

<p>is a road or path and defines a list of <code>node</code>s, with name <code>nd</code> and the attribute <code>ref</code> referring to the node id, all of which are connected in linear order. Tags in the <code>way</code> will tell you what kind of road it is - if it has a key of "highway", then the value corresponds to the road type. See the Javadoc on <code>ALLOWED_HIGHWAY_TYPES</code> for restrictions on which roads we care about. <strong>You should ignore all one-way tags and pretend all roads are two-way</strong> (this means your directions are not safe to use, but there are some inaccuracies in the OSM data anyway).</p>

<p>Part of your job will be decide how to store the graph itself in your <code>GraphDB</code> class. Note that the final step of graph construction is to "clean" the graph, i.e. to destroy all nodes that are disconnected. Unlike the Princeton graph implementation, your <code>GraphDB</code> will need to permit the insertion and deletion of nodes.</p>

<p>We make one simplifying assumption for this part of the assignment: the world is flat. In a real world mapping application, calculating the Euclidean distance should take into account the fact that latitude and longitude are in slightly different scales (at our latitude, 1 degree of latitude is ~364,000 feet and 1 degree of longitude is ~288,000 feet), and should also take into account that as you move north or south, these two scales change slightly.</p>

<p>For the purpose of computing the Euclidean distance, simply assume that lon and lat are simply x and y coordinates, i.e. do not take into the difference in scale between latitude and longitude that results from the fact that the world is secretly round. This is a reasonable assumption because our algorithm does not take into account speed limits or turn-taking time in computing routes, which will be dominant effect.</p>

<p>Spec modification (4/14/17): The <code>distance</code> method should return the euclidean distance, not the longitudinal distance. This is simply the sqrt(londiff^2 + latdiff^2). Contrary to the comment in the spec, it should not be in units of longitude.</p>

<p>Note: You don't need to actually store the <code>maxSpeed</code> anywhere since we ignore the speed limits when calculating the route in part III. We've provided this in the skeleton in case you want to play around with this, but unfortunately the provided data set is missing a bunch of speed limits so didn't turn out to be particularly useful.</p>


<h2 id="route-search-part-iii">Route Search (Part III)</h2>


<p>The <code>/route</code> endpoint (kinda like a method in web programming) receives four values for input: the start point's longitude and latitude, and the end point's longitude and latitude. Implement <code>shortestPath</code> in your <code>Router</code> class so that it satisfies the requirements in the Javadoc.</p>

<p>Your route should be the shortest path that starts from the closest connected node to the start point and ends at the closest connected node to the endpoint. Distance between two nodes is defined as the Euclidean distance between their two points (lon1, lat1) and (lon2, lat2). The length of a path is the sum of the distances between the ordered nodes on the path. We do not take into account driving time (speed limits).</p>

<!--Potentially fun (?) task: Find a route for which the driving directions are different if we don't assume the world is flat.-->


<h4 id="runtime-amp-a">Runtime &amp; A*</h4>


<p>Let <code>d</code> be the distance between the start and end node. You cannot explore all nodes within distance <code>d</code> from the start node and expect to pass the autograder (for long paths, this could be more than half the nodes in the map).</p>

<p>While Dijkstra's algorithm for finding shortest paths works well, in practice if we can, we use A* search. Dijkstra's is a Uniform-Cost search algorithm - you visit all nodes at a distance <code>d</code> or less from the start node. However, in cases like this, where we know the direction that we should be searching in, we can employ that information as a heuristic.</p>

<p>Let <code>n</code> be some node on the search fringe (a min priority queue), <code>s</code> be the start node, and <code>t</code> be the destination node. A* differs from Dijkstra's in that it uses a heuristic <code>h(n)</code> for each node <code>n</code> that tells us how close it is to <code>t</code>. The priority associated with <code>n</code> should be <code>f(n) = g(n) + h(n)</code>, where <code>g(n)</code> is the shortest known path distance from <code>s</code> and <code>h(n)</code> is the heuristic distance, the Euclidean distance from <code>n</code> to <code>t</code>, and thus the value of <code>h(n)</code> should shrink as <code>n</code> gets closer to <code>t</code>. This helps prevent Dijkstra from exploring too far in the wrong direction.</p>

<p>This amounts to only a small change in code from the Dijkstra's version (for us, one line).</p>


<h4 id="supplemental-information">Supplemental Information</h4>


<p>For an example of how A* works until I record my Part III tips, you might consider <a href="https://www.youtube.com/watch?v=DhtSZhakyOo">Professor Abbeel's video</a>, where he goes over an example of A* on a general search space. This goes into a bit more detail that my lecture for 61B.</p>

<p>You can also watch his <a href="https://www.youtube.com/watch?v=8pTjoFiICg8&t=6m40s">CS188 lecture on Informed Search</a>, particularly starting from <a href="https://www.youtube.com/watch?v=8pTjoFiICg8&t=16m54s">here</a>, from back when I took CS188. Some of the ideas expressed are outside the scope of what you need for this project - you don't need to worry about the part where he starts explaining about optimality guarantees and heuristic admissibility if you don't want; in a graph embedded in the plane like the one we're working with, Euclidean distance is always admissible and consistent, and thus it guarantees optimality.</p>


<h2 id="autocompletion-and-search-10-gold-points">Autocompletion and Search (10 gold points)</h2>


<p>These gold points are all-or-nothing. You must pass both the timing and correctness parts to get credit. Tests will be available by 4/15/2017.</p>


<h3 id="locations">Locations</h3>


<p>In the <code>berkeley.osm</code> file, we consider all nodes with a name tag a location. This name is not necessarily unique and may contain things like road intersections.</p>


<h3 id="autocomplete">Autocomplete</h3>


<p>We would like to implement an Autocomplete system where a user types in a partial query string, like "Mont", and is returned a list of locations that have "Mont" as a prefix. To do this, implement <code>getLocationsByPrefix</code>, where the prefix is the partial query string. The prefix will be a <em>cleaned</em> name for search that is: (1) everything except characters A through Z and spaces removed, and (2) everything is lowercased. The method will return a list containing the <em>full names</em> of all locations whose cleaned names share the cleaned query string prefix, without duplicates.</p>

<p><img src="autocomplete.png" alt="Autocomplete"></p>

<p>I recommend using a <a href="http://www.wikiwand.com/en/Trie">Trie</a>. You can traverse to the node that matches the prefix (if it exists) and then collect all valid words that are a descendant of that node. We'll discuss Tries in the class later, but this Gold points opportunity assumes you'll either find resources or online or read ahead in the class (by looking at the Spring 2016 website).</p>


<h4 id="runtime">Runtime</h4>


<p>Assuming that the lengths of the names are bounded by some constant, querying for prefix of length <code>s</code> should take <code>O(k)</code> time where <code>k</code> is the number of words sharing the prefix.</p>


<h3 id="search">Search</h3>


<p>The user should also be able to search for places of interest. Implement <code>getLocations</code> which collects a <code>List</code> of <code>Map</code>s containing information about the matching locations - that is, locations whose cleaned name match the cleaned query string exactly. This is <em>not</em> a unique list and should contain duplicates if multiple locations share the same name (i.e. Top Dog, Bongo Burger). See the Javadocs for the information each <code>Map</code> should contain.</p>

<p><img src="selection.png" alt="Selection"></p>

<p>Implementing this method correctly will allow the web application to draw red dot markers on each of the matching locations. Note that because the location data is not very accurate, the markers may be a bit off from their real location. For example, the west side top dog is on the wrong side of the street!</p>


<h4 id="runtime">Runtime</h4>


<p>Suppose there are <code>k</code> results. Your query should run in <code>O(k)</code>.</p>


<h2 id="possible-extensions-optional">Possible Extensions (optional)</h2>


<p>There are some inefficiencies with the current design of this project that set it apart from conventional mapping applications like Google Maps.</p>


<h4 id="front-end-integration">Front-end Integration</h4>


<p>Currently, you raster the entire image and then pass it to the front end for display, and re-raster every call. A better approach, and the one that popular rastering mapping applications nowadays take, would be to simply pass each tile's raster to the front end, and allow the front-end to assemble them on the page dynamically. This way, the front-end can make the requests for the image assets and cache them, vastly reducing repetitive work when drawing queries, especially if they use tiles that have already been drawn before.</p>

<p>Likewise, the front end could handle route drawing as all the back-end needs to pass to the front-end are the points along the route.</p>

<p>However, this poses a major problem to the project's design - it overly simplifies the amount of work you need to do and moves a large amount of the interesting work to the front-end, so for this small project you implement a simplified version.</p>


<h4 id="vectored-tiles">Vectored Tiles</h4>


<p>While for this project we've provided the mapping data in the form of images per tile, in reality these images are rastered from the underlying vector geometry - the roads, lines, filled areas, buildings and so on that make up the tile. These can all be drawn as triangles using a rendering API like OpenGL or WebGL; this speeds up the process even more, as much of the work is now passed on to the GPU which can handle this far more efficiently than the CPU. This data is all available from <a href="http://wiki.openstreetmap.org/wiki/Vector_tiles">OpenStreetMap</a> if you want to pursue this route of action. However, doing so is far beyond the scope of CS61B and more along the lines of CS184.</p>


<h4 id="the-camera-amp-the-quadtree">The Camera &amp; The Quadtree</h4>


<p>The real purpose of the quadtree is to enable multi-level, angled camera rendering (think about tilting the camera, or navigation mode in Google Maps). This idea of having a camera location, viewport, and viewing direction is a natural requirement of drawing vector geometry using OpenGL or similar APIs. However, camera tilt is a very difficult thing to get right and is far beyond the scope of Berkeley's undergrad CS curriculum.</p>

<p>Without that functionality, the Quadtree could be replaced with a different application structure, the <a href="http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames">slippy map</a>, which is an exclusively top-down map like the one you've built in this project. This enforces a natural quad-tree structure but with fast conversion from level, longitude, and latitude to the tile co-ordinates as they exist in some level of the quadtree. This also disables the previous option I talked about, enabling vector geometry with a camera. However, a quadtree abstracts away a lot of the projection math that is outside the scope of this class.</p>


<h2 id="deploying-to-quilt">Deploying to Quilt</h2>


<p>This tutorial will walk you through how to run BearMaps with <a href="http://quilt.io/">Quilt</a>. Right now, your application is only accessible from your laptop, but by the end of this tutorial, your BearMaps application will be hosted in the <a href="https://aws.amazon.com/">Amazon AWS cloud</a>, where it is accessible to you, your friends, parents, pets, and the rest of the world.</p>

<p>Setting up will be quick! You just need to set up an AWS account, install Quilt, and finally change a single line of code the Quilt BearMaps spec before your app is live.</p>


<h3 id="what-is-quilt">What is Quilt</h3>

<p><a href="http://quilt.io">Quilt</a> is a research project here at UC Berkeley and is fueled in part by lots of CS61B spirit in form of current TAs Matt and Vivian, and previous TAs Kay and Luise. Quilt's goal is to be the easiest way to deploy applications -- just like BearMaps -- in the cloud. The Quilt team, will be on Piazza to help out if you run into any issues!</p>

<p>It would be a big help for us if you star the <a href="http://github.com/quilt/quilt">Quilt project on GitHub</a>. You will get absolutely no notifications, emails, or other stuff from us by doing it - it is just like "liking" a photo on Facebook. By staring the project, you’re letting others on GitHub know they should check it out - and maybe one day it will be cool to say you were one of the intitial users. Who knows? You can star the project by pressing the button in the top right corner that looks like this: <a href="http://github.com/quilt/quilt"><img src="github-star.png"></a></p>

<p>To thank you for your time, you can come to 420 Soda and pick up a limited edition, soon-to-be collector's item sticker of Josh Hug in a trash can:</p>

<p align="center">
<img align="center" src="hughex.png" width="150">
</p>

<p>The stickers are in the mail, but will be here soon!</p>


<h3 id="aws-account">AWS Account</h3>


<p>Follow the below instructions to set up your AWS account. You'll be using AWS's <strong>free</strong> tier, so it won't cost you a penny. AWS is super widely used, so chances are that you either already have an account or will need one in your future CS career. If you already have an account, just skip step 1.</p>

<ol>
  <li>Sign up for the AWS Free Tier on the <a href="https://aws.amazon.com/s/dm/optimization/server-side-test/free-tier/free_np/">AWS website</a>. They will ask for a debit/credit card, but don't worry, they won't charge you anything for just running BearMaps.</li>
  <li>Sign in to the <a href="https://aws.amazon.com/console/">AWS console</a>.</li>
  <li>In the top right corner, click your name and then <code>My Security Credentials</code>. In the pop up window, click <code>Continue to Security Credentials</code>.</li>
  <li>Click <code>Access Keys (Access Key ID and Secret Access Key)</code> and then <code>Create New Access Key</code> to download your AWS key and key ID.</li>
  <li><p>Put the key and ID in a file called <code>~/.aws/credentials</code>. This credentials file must be formatted <em>exactly</em> like this:</p>

<pre><code>[default]
aws_access_key_id=YOUR_AWS_KEY_ID
aws_secret_access_key=YOUR_AWS_KEY
</code>
</pre></li>
</ol>

<p>Your AWS account is now ready! You only have to do this setup once. Next time you want to run BearMaps or another application on Quilt, you can skip this and the next steps and jump straight to <a href="#run-bearmaps">Run BearMaps</a>.</p>


<h3 id="installing-quilt">Installing Quilt</h3>


<p>Next, download Quilt by going to <a href="https://github.com/quilt/quilt/releases">releases page on Quilt’s github</a> then follow the instruction depending on your machine below.</p>


<h5 id="windows">Windows</h5>


<ol>
  <li>Click on <code>quilt_windows.zip</code> to download Quilt.</li>
  <li>In your terminal, navigate to the location of <code>quilt_windows.zip</code>, and unzip the file with <code>unzip quilt_windows.zip</code>.</li>
  <li>From the same directory, run <code>./quilt</code>. If Quilt is successfully installed, you should see output starting with <code>Usage: quilt ...</code></li>
</ol>


<h5 id="mac-and-linux">Mac and Linux</h5>


<ol>
  <li>Click on <code>quilt_mac.tgz</code> (or <code>quilt_linux.tgz</code> if on Linux) to download Quilt.</li>
  <li>In the terminal, unzip the file with <code>tar &#x2d;xf quilt_mac.tgz</code> (or <code>quilt_linux.tgz</code>).</li>
  <li>Move the Quilt binary by running <code>sudo mv ~/Downloads/quilt /usr/local/bin/quilt</code>. If you're promted for a password, type the same password you use to log on to your computer.</li>
  <li>Run <code>quilt</code> from the terminal. Quilt is succesfully installed if the output starts with <code>Usage: quilt ...</code></li>
</ol>


<h3 id="run-bearmaps">Run BearMaps</h3>

<p>The rest of the instructions are practically the same for Windows and Mac. The only difference is that if you're on Windows, you need to use <code>./quilt</code> instead of <code>quilt</code> and make sure you're in the directory containing <code>quilt.exe</code>.</p>

<ol>
  <li>We made a <a href="https://github.com/quilt/bear-maps">Quilt specification</a> for you to deploy BearMaps. You can get it by running <code>quilt get github.com/quilt/bear&#x2d;maps</code>. The code is now in <code>~/.quilt/github.com/quilt/bear&#x2d;maps</code>.</li>
  <li>Open the file <code>~/.quilt/github.com/quilt/bear&#x2d;maps/main.js</code>. Change the <code>PROJ_PATH</code> variable to be the path to your BearMaps code (the directory that contains the <code>pom.xml</code> file). Use an absolute path like <code>/Users/Josh/cs61b/abc/proj3</code> (or <code>C:\\Users\\Josh\\...</code> on Windows - notice the <strong>double backslashes</strong>).
  	* <strong>Windows Users</strong>: Replace the current BearMaps import with the import of <code>bear&#x2d;maps&#x2d;windows.js</code> in line 3.</li>
  <li>Open one more terminal window. In one of them, run <code>quilt daemon</code> (it is supposed to keep running), and in the other terminal, run <code>quilt run github.com/quilt/bear&#x2d;maps/main.js</code>.</li>
</ol>


<h3 id="accessing-your-bearmaps">Accessing Your BearMaps</h3>

<p>Quilt is now setting up your virtual machines (VMs) and container (lightweight VM) in the Amazon AWS cloud. You can use the command <code>quilt ps</code> to see the status of your VMs and container. When everything is ready (after a few minutes), you should see a <code>running</code> container in the bottom row, similar to this:</p>

<pre><code>$ quilt ps
MACHINE         ROLE      PROVIDER    REGION       SIZE        PUBLIC IP         STATUS
e5b1839d2bea    Master    Amazon      us-west-1    t2.micro    54.67.64.87       connected
e2401c348c78    Worker    Amazon      us-west-1    t2.micro    54.183.134.153    connected

CONTAINER       MACHINE         COMMAND                              LABELS       STATUS     CREATED           PUBLIC IP
3482fd5f7197    e2401c348c78    luise/bear-maps-base /bin/sh -...    bear-maps    running    53 seconds ago    54.183.134.153:4567
</code>
</pre>

<p>Now you can access your BearMaps application! Simply use your browser to go to the public IP address and port shown in the <code>quilt ps</code> output - in this case <code>54.183.134.153:4567</code>. Now that your BearMaps is running in the cloud, anyone can access it in their browser using the same IP address!</p>


<h3 id="stopping-quilt">Stopping Quilt</h3>

<p>When you're done enjoying your great work, run <code>quilt stop</code> to shut down the VMs. In the terminal that's running <code>quilt daemon</code>, wait for it to say <code>Successfully stopped machines</code> and then exit with Ctrl+c.</p>


<h2 id="faq">FAQ</h2>



<h4 id="i-provided-the-correct-string-output-but-it-doesn-t-show-up">I provided the correct String<a href=""></a> output but it doesn't show up!</h4>


<p>In order for something to show up on test.html, you need to set query_success to true, and in order for something to show up on map.html all the parameters must be set.</p>


<h4 id="my-initial-map-doesn-t-fill-up-the-screen">My initial map doesn't fill up the screen!</h4>


<p>If your monitor resolution is high &amp; the window is fullscreen, this can happen. Refer to the reference solution to see if it is okay (coming soon).</p>


<h4 id="in-the-browser-zooming-out-only-appears-to-shift-the-map-and-i-m-confident-my-rastering-code-is-correct">In the browser, zooming out only appears to shift the map, and I'm confident my rastering code is correct</h4>


<p>If you click on the gear icon, check the box for "Constrain map dimensions". This issue is due to the window size being too large which sometimes causes the front-end to handle zooming out poorly. Also make sure you're allowing all the rastering to finish (sometimes the front-end calls raster a couple more times to get the result of the zoom just right).</p>


<h4 id="i-don-t-draw-the-entire-query-box-on-some-inputs-because-i-don-t-intersect-enough-tiles">I don't draw the entire query box on some inputs because I don't intersect enough tiles.</h4>


<p>That's fine, that'll happen when you go way to the edge of the map. For example, if you go too far west, you'll never reach the bay because it does not exist.</p>


<h4 id="do-i-construct-my-quadtree-in-one-pass-or-do-i-insert-into-it">Do I construct my quadtree in one pass or do I insert into it?</h4>


<p>Construct it recursively in one pass. Inserting into it is much slower.</p>


<h4 id="what-s-a-quadtree-intersection-query">What's a quadtree intersection query?</h4>


<p>Think about a range query on a binary search tree. Given some binary search tree on integers, I want you to return me all integers of depth 4 in between 8 and 69 - how do you do that?  Now, if you can do that, can you do that in two dimensions? Instead of integers, now we have squares that span certain ranges. There are many approaches to solving this.</p>


<h4 id="how-do-i-keep-my-quadtree-code-simple-when-calculating-rectangle-intersections-this-feels-insane">How do I keep my Quadtree code simple when calculating rectangle intersections? This feels insane.</h4>


<p>Rectangle intersection can be done with simple logic, but it takes some cleverness. See <a href="http://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other">this stack overflow post</a> for a simple example.</p>


<h4 id="i-m-getting-funky-behavior-with-moving-the-map-around-my-image-isn-t-large-enough-at-initial-load-after-the-initial-load-i-can-t-move-the-map-or-after-the-initial-load-i-start-getting-nan-as-input-params">I'm getting funky behavior with moving the map around, my image isn't large enough at initial load, after the initial load, I can't move the map, or after the initial load, I start getting NaN as input params.</h4>


<p>These all have to do with your returned parameters being incorrect. Make sure you're returning the exact parameters as given in the project 2 slides or the test html files.</p>


<h4 id="i-sometimes-pass-the-timing-tests-when-i-submit-but-not-consistently">I sometimes pass the timing tests when I submit, but not consistently.</h4>


<p>If you have a efficient solution: it will always pass. I have yet to fail the timing test with either my solution or any of the other staff's solutions over a lot of attempts to check for timing volatility.</p>

<p>If you have a borderline-efficient solution:  it will sometimes pass. That's just how it is, and there really isn't any way around this if we want the autograder to run in a reasonable amount of time.</p>


<h2 id="common-bugs">Common Bugs</h2>


<p>(Coming soon)
<!--
We've created a list of common bugs <a href="https://docs.google.com/document/d/1rQfdunoIhJjPy6HCg2jWMfjH8trqXOu56XE6OO3GnJk/edit">at this link</a>. These are far from comprehensive.

Submission
---------------------
You need only submit the <code>src</code> folder. It should retain the structure given in the skeleton. **DO NOT submit or upload to git your img/ folder**, or your osm or test files. Attempting to do so will eat your internet bandwidth and hang your computer, and will waste a submission.

Do not make your program have any maven dependencies other than the ones already provided. Doing so may fail the autograder.
--></p>


<h2 id="acknowledgements">Acknowledgements</h2>

<p>Data made available by OSM under the <a href="www.openstreetmap.org/copyright">Open Database License</a>.
JavaSpark web framework and Google Gson library.</p>

<p>Alan Yao for creating the original version of this project.</p>
    </main></div>
</body>
</html>
