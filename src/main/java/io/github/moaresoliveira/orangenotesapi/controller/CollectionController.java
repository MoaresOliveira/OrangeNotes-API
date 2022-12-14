package io.github.moaresoliveira.orangenotesapi.controller;

import io.github.moaresoliveira.orangenotesapi.model.dto.CollectionDTO;
import io.github.moaresoliveira.orangenotesapi.model.form.CollectionForm;
import io.github.moaresoliveira.orangenotesapi.model.form.CollectionUpdateForm;
import io.github.moaresoliveira.orangenotesapi.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Collection")
@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Operation(summary = "Get a list of Collection")
    @GetMapping
    public List<CollectionDTO> getCollections() {
        return collectionService.findAll();
    }

    @Operation(summary = "Get a Collection by Id")
    @GetMapping("/{id}")
    public CollectionDTO getCollectionById(@PathVariable Long id) {
        return collectionService.findById(id);
    }

    @Operation(summary = "Get all content of a Collection by Id")
    @GetMapping("/content/{id}")
    public List<CollectionDTO> getCollectionContent(@PathVariable Long id) {
        return collectionService.findCollectionContent(id);
    }

    @Operation(summary = "Create a new Collection")
    @PostMapping
    public CollectionDTO createCollection(@RequestBody CollectionForm collection) {
        System.out.println(collection);
        return collectionService.save(collection);
    }

    @Operation(summary = "Update a Collection by Id")
    @PutMapping("/{id}")
    public CollectionDTO updateCollection(@PathVariable Long id,@RequestBody CollectionUpdateForm collection) {
        return collectionService.update(id,collection);
    }

    @Operation(summary = "Set a Collection/Content as finished")
    @PatchMapping("/{id}")
    public CollectionDTO finishCollection(@PathVariable Long id) {
        return collectionService.finishCollection(id);
    }

    @Operation(summary = "Add Content to a Collection")
    @PostMapping("/add/{id}")
    public CollectionDTO addContentToCollection(@PathVariable Long id,@RequestBody CollectionForm content) {
        System.out.println("Controller: collection a adicionar => " + content);
        return collectionService.addContentToCollection(id, content);
    }

    @Operation(summary = "Delete a Collection")
    @DeleteMapping("/{id}")
    public boolean deleteCollection(@PathVariable Long id) {
        return collectionService.deleteById(id);
    }

}
