package finalProject.finalEdubridgeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import finalProject.finalEdubridgeProject.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	  List<Todo> findByPublished(boolean published);
	  List<Todo> findByTitleContaining(String title);
}
