package com.kakura.springcourse.controllers;

import com.kakura.springcourse.dao.BookDao;
import com.kakura.springcourse.dao.PersonDao;
import com.kakura.springcourse.models.Book;
import com.kakura.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BookDao bookDao;
    private PersonDao personDao;

    @Autowired
    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @ModelAttribute("person") Person person, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        model.addAttribute("people", personDao.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") @Valid Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/books/new";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        bookDao.update(id, book);
        if (bindingResult.hasErrors()) {
            return "/books/edit";
        }
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setPerson")
    public String setPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDao.setPerson(person.getId(), id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/removePerson")
    public String removePerson(@PathVariable("id") int id) {
        bookDao.removePerson(id);
        return "redirect:/books/{id}";
    }
}
