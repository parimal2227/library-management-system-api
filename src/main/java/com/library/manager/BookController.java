package com.library.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	@Autowired
	BookRepository bookRepository;

	@SuppressWarnings("unused")
	@PostMapping("/add")
	public AddResponseVO addBook(@RequestBody AddRequestVO requestVO) {
		AddResponseVO responseVO = new AddResponseVO();
		if (bookRepository.findOneByIsbn(requestVO.getIsbn()) != null) {
			responseVO.setResponseString("isbn already exits");
			return responseVO;
		} else if(requestVO != null){
			Book book = new Book();
			book.setCategory(requestVO.getCategory());
			book.setDate(requestVO.getDate());
			book.setIsbn(requestVO.getIsbn());
			book.setName(requestVO.getName());
			Book savedBook = bookRepository.save(book);
			responseVO.setName(savedBook.getName());
			responseVO.setIsbn(savedBook.getIsbn());
			responseVO.setResponseString("success");
			return responseVO;
		}else {
			responseVO.setResponseString("error");
			return responseVO;
		}
	}

	@GetMapping("/get")
	public List<AddResponseVO> getBook() {
		List<AddResponseVO> responseVOs = new ArrayList<AddResponseVO>();
		List<Book> responses = bookRepository.findAll();
		for (Book book : responses) {
			AddResponseVO responseVO = new AddResponseVO();
			responseVO.setId(book.getId());
			responseVO.setCategory(book.getCategory());
			responseVO.setDate(book.getDate());
			responseVO.setIsbn(book.getIsbn());
			responseVO.setName(book.getName());
			responseVOs.add(responseVO);
		}
		return responseVOs;
	}
}
