package edu.csupomona.cs480.controller;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;


import java.util.Map;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}
	//Byung Hun Lee simple String return method
	@RequestMapping(value = "/cs480/test", method = RequestMethod.GET)
	String Test() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "Testing";
	}
	
	//Byung Hun Lee jsoup method to get title of site (google in particular)
	@RequestMapping(value = "/cs480/jsoup", method = RequestMethod.GET)
	String Site(){
		Document doc;
		String title = null;
        try {

            // need http protocol
            doc = Jsoup.connect("http://google.com").get();

            // get page title
            title = doc.title();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return title;
	}

	// Josh Montgomery Method
	@RequestMapping(value = "/cs480/classinfo", method = RequestMethod.GET)
	private String classDetails() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/

		return "CS 480, Fall 2017" +
				"\nT/Th 10:00am" +
				"\nCal Poly Pomona";
	}

	// Josh Montgomery Guava Method
	 private void guavaExample()
	{
		final Map<String, Map<String, Integer>> lookup = Maps.newHashMap();
		// Guava can be used to rewrite final Map<String, Map<String, Integer>> lookup = new HashMap<>();
		// as the above code. This makes for more verbose code
	}
	//Ethan Smith Method
	@RequestMapping(value = "/cs480/hello", method = RequestMethod.GET)
	private String helloWorld() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "Hello World!";
	}
	
	//Ethan Smith Commons IO Method
	@RequestMapping(value = "/cs480/commonsIO", method = RequestMethod.GET)
	private void commonsIOExample() {
		//reads bytes from a URL and prints them
		//run application locally with http://localhost:8080/commonsIO
		InputStream in = null;
		try {
			in = new URL( "http://commons.apache.org" ).openStream();
			System.out.println(IOUtils.toString(in));
		}catch(IOException ex){
			ex.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	//Erick Lopez Method!
	@RequestMapping(value = "/cs480/hi", method = RequestMethod.GET)
	String PrintHi() {
		//run the application locally
		// with the URL: http://localhost:8080/hi
		double[][] matrixData1 =  {{1d,2d,3d}, {2d,5d,3d}};
		double[][] matrixData2 =  { {1d,2d}, {2d,5d}, {1d, 7d}};

		RealMatrix m = MatrixUtils.createRealMatrix(matrixData1);
		RealMatrix n = MatrixUtils.createRealMatrix(matrixData2);

		RealMatrix p = m.multiply(n);
		String matrixP = p.toString();
		return "<h1>Using Math Commons to multiply to Matrices</h1>" +
				"<h2>Matrix A:</h2> <dv><p>|1 2 3|</br>|2 5 3|</p> </div><h2>Matrix B:</h2><div><p>|1 2|</br>|2 5|</p></div>" +
				"<h4>Result of A x B:</h4><div><p>"+matrixP+"</p></div>" ;
	}


	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}
	/**
	 * This is a test API command
	 */
	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

}