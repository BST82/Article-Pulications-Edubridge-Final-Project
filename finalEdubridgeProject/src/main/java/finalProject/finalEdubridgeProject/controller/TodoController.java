package finalProject.finalEdubridgeProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finalProject.finalEdubridgeProject.model.Todo;
import finalProject.finalEdubridgeProject.repository.TodoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TodoController {

	
	@Autowired
	  TodoRepository todoRepository;

	  @GetMapping("/todo")
	  public ResponseEntity<List<Todo>> getAllTutorials(@RequestParam(required = false) String title) {
		  try {
		      List<Todo> tutorials = new ArrayList<Todo>();

		      if(title == null)
		    	  todoRepository.findAll().forEach(tutorials::add);
		      else
		    	  todoRepository.findByTitleContaining(title).forEach(tutorials::add);

		      if (tutorials.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(tutorials, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }

	  @GetMapping("/todo/{id}")
	  public ResponseEntity<Todo> getTutorialById(@PathVariable("id") long id) {
		  Optional<Todo> tutorialData = todoRepository.findById(id);

		    if (tutorialData.isPresent()) {
		      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	  }

	  @PostMapping("/todo")
	  public ResponseEntity<Todo> createTutorial(@RequestBody Todo tutorial) {
		  try {
			  Todo _tutorial = todoRepository
		          .save(new Todo(tutorial.getTitle(), tutorial.getDescription(), false));
		      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }

	  @PutMapping("/todo/{id}")
	  public ResponseEntity<Todo> updateTutorial(@PathVariable("id") long id, @RequestBody Todo tutorial) {
		  Optional<Todo> tutorialData = todoRepository.findById(id);

		    if (tutorialData.isPresent()) {
		    	Todo _tutorial = tutorialData.get();
		      _tutorial.setTitle(tutorial.getTitle());
		      _tutorial.setDescription(tutorial.getDescription());
		      _tutorial.setPublished(tutorial.isPublished());
		      return new ResponseEntity<>(todoRepository.save(_tutorial), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	  }

	  @DeleteMapping("/todo/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		  try {
			  todoRepository.deleteById(id);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }

	  @DeleteMapping("/todo")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
		  try {
			  todoRepository.deleteAll();
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }

	  @GetMapping("/todo/published")
	  public ResponseEntity<List<Todo>> findByPublished() {
		  try {
		      List<Todo> tutorials = todoRepository.findByPublished(true);

		      if (tutorials.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(tutorials, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  }
	  }

