package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\nCall AuthorRepository#findAll():");
            bookstoreService.displayAuthorsAndBooks();

            System.out.println("\nCall AuthorRepository#findByAgeLessThanOrderByNameDesc(int age):");
            bookstoreService.displayAuthorsAndBooksByAge(40);

            System.out.println("\nCall AuthorRepository#findAll(Specification spec):");
            bookstoreService.displayAuthorsAndBooksByAgeWithSpec();

            System.out.println("\nCall AuthorRepository#fetchAllAgeBetween20And40():");
            bookstoreService.displayAuthorsAndBooksFetchAllAgeBetween20And40();

            System.out.println("\nCall BookRepository#findAll():");
            bookstoreService.displayBooksAndAuthors();
        };
    }
}
/*
 * 
 * How To Use Entity Graphs (@NamedEntityGraph) In Spring Boot

Note: In a nutshell, entity graphs (aka, fetch plans) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single SELECT statement. We can define multiple entity graphs for the same entity and chain any number of entities and even use sub-graphs to create complex fetch plans. To override the current FetchType semantics there are properties that can be set:

Fetch Graph (default), javax.persistence.fetchgraph
The attributes present in attributeNodes are treated as FetchType.EAGER. The remaining attributes are treated as FetchType.LAZY regardless of the default/explicit FetchType.

Load Graph, javax.persistence.loadgraph
The attributes present in attributeNodes are treated as FetchType.EAGER. The remaining attributes are treated according to their specified or default FetchType.

Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (@Basic) attributes.. More details here.

Description: This is a sample application of using entity graphs in Spring Boot.

Key points:

define two entities, Author and Book, involved in a lazy bidirectional @OneToMany association
in Author entity use the @NamedEntityGraph to define the entity graph (e.g., load in a single SELECT the authors and the associatated books)
in AuthorRepositry rely on Spring @EntityGraph annotation to indicate the entity graph defined at the previous step
 * 
 * 
 */
