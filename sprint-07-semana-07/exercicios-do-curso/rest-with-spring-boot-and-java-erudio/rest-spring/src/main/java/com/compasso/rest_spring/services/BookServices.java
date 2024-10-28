package com.compasso.rest_spring.services;

import com.compasso.rest_spring.controller.BookController;
import com.compasso.rest_spring.controller.PersonController;
import com.compasso.rest_spring.data.vo.v1.BookVO;
import com.compasso.rest_spring.data.vo.v1.PersonVO;
import com.compasso.rest_spring.data.vo.v2.PersonVOV2;
import com.compasso.rest_spring.exceptions.RequiredObjectIsNullException;
import com.compasso.rest_spring.exceptions.ResourceNotFoundException;
import com.compasso.rest_spring.mapper.DozerMapper;
import com.compasso.rest_spring.mapper.custom.PersonMapper;
import com.compasso.rest_spring.model.Book;
import com.compasso.rest_spring.model.Person;
import com.compasso.rest_spring.repositories.BookRepository;
import com.compasso.rest_spring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    private BookRepository repository;

    @Autowired
    PagedResourcesAssembler<BookVO> assembler;

    public BookVO findById(Long id) {
        logger.info("Finding one book");

        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        BookVO vo = DozerMapper.parseObject(book, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return vo;
    }

    public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) {

        logger.info("Finding all books!");

        var booksPage = repository.findAll(pageable);

        var booksVOs = booksPage.map(p -> DozerMapper.parseObject(p, BookVO.class));
        booksVOs.map(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()));

        Link findAllLink = linkTo(
                methodOn(BookController.class)
                        .findAll(pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc")).withSelfRel();

        return assembler.toModel(booksVOs, findAllLink);
    }

    public BookVO create(BookVO object) {
        if (object == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one book");

        Book book = DozerMapper.parseObject(object, Book.class);
        BookVO vo = DozerMapper.parseObject(repository.save(book), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public BookVO update(BookVO object) {
        if (object == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one book");

        Book book = repository.findById(object.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        book.setAuthor(object.getAuthor());
        book.setLaunchDate(object.getLaunchDate());
        book.setPrice(object.getPrice());
        book.setTitle(object.getTitle());

        BookVO vo = DozerMapper.parseObject(repository.save(book), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one book");

        Book object = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(object);
    }
}
