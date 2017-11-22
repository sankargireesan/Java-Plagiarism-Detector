package comparison;


import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


//import com.in28minutes.springboot.model.Course;
//import com.in28minutes.springboot.service.StudentService;

@RestController
public class PDController {

	//@Autowired
	//private StudentService studentService;

	//@GetMapping("/path1/{studentId}/path")
	//public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
	//	return studentService.retrieveCourses(studentId);
	//}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/path1")
	public String returnSimilarMethods(@RequestBody RequestInput req){
		String pathone = req.Pathone;
		String pathtwo = req.Pathtwo;
    	try {
    		
			  return Handler.astComparisonHandler(pathone,pathtwo);
    		 //return pathone+"hello"+pathtwo;
		} catch (Exception e) {			
			return "error in function";
		}
    	
	}
	
	/*@CrossOrigin(origins = "*")
	@GetMapping("/dp")
	public String returnSomething(){
			return "INSIDE";
    	
	}*/
	
//	@GetMapping("/path1/path2/{pathOne}/{pathTwo}")
//	public String returnSimilarMethods(@PathVariable String pathOne, @PathVariable String pathTwo){
//		String pathone = pathOne;
//		String pathtwo = pathTwo;
//    	try {
//			return GenerateAST.compareSimilarity(pathone,pathtwo);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			
//			return "error in function";
//		}
//    	
//	}
	
	/*@PostMapping("/path1/{pathone}/path2/{pathtwo}")
	public String returnSimilarMethods(@PathVariable String pathone,@PathVariable String pathtwo){
		
    	try {
			return GenerateAST.compareSimilarity(pathone,pathtwo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			return "error in function";
		}
    	
	}*/
    			
	

}
