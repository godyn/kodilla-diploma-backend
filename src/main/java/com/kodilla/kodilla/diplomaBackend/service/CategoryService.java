package com.kodilla.kodilla.diplomaBackend.service;

import com.kodilla.kodilla.diplomaBackend.domain.Category;
import com.kodilla.kodilla.diplomaBackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LogHistoryService logHistoryService;

    @Autowired
    UserService userService;

    public Category getCategory(String name) throws CategoryNotFoundException{
        return categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category){
        logHistoryService.saveLog(userService.getAdmin(), "New car category created: " + category.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        logHistoryService.saveLog(userService.getAdmin(), "Update category: " + category.getId());
        return categoryRepository.save(category);
    }

    public void deleteCategory(long id){
        logHistoryService.saveLog(userService.getAdmin(), "Removal of category: " + id);
        categoryRepository.deleteById(id);
    }


}