package com.library.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query(nativeQuery=true,value="SELECT * FROM book WHERE isbn=:isbn")
	Book findOneByIsbn(@Param("isbn") String isbn);

}
