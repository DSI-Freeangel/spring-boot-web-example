package org.demo.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.demo.model.ErrorResponse;
import org.demo.model.ItemModel;
import org.demo.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(value = "Use to create, update, get or delete item", tags = {"Item CRUD API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ItemModel> findOne(
            @ApiParam(value = "ID of the Item", example = "1", required = true) @PathVariable Integer id) {
        return itemService.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemModel save(@RequestBody @Valid ItemModel itemModel) {
        return itemService.save(itemModel);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@ApiParam(value = "ID of the Item", example = "1", required = true) @PathVariable Integer id) {
//        throw new RuntimeException("Ooops!!!");
        itemService.delete(id);
    }

    @ApiOperation("Read items by pages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", paramType = "query", value = "Count of items per page", defaultValue = "20"),
            @ApiImplicitParam(name = "page", paramType = "query", value = "Page number starting from 0", defaultValue = "0"),
            @ApiImplicitParam(name = "sort", paramType = "query", value = "Sort field and direction separated by coma")
    })
    @GetMapping
    public Page<ItemModel> findAll(@ApiIgnore @PageableDefault(size = 20) Pageable page) {
        return itemService.findAll(page);
    }
}