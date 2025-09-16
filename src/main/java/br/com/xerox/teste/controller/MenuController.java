package br.com.xerox.teste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xerox.teste.common.View;
import br.com.xerox.teste.dto.MenuDTO;
import br.com.xerox.teste.service.MenuService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService service;

    @PostMapping
    public ResponseEntity<MenuDTO> create(@Validated(View.Create.class) @RequestBody MenuDTO dto) {
        MenuDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> update(@PathVariable long id, @Validated(View.Update.class) @RequestBody MenuDTO dto) {
        MenuDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> findById(@PathVariable long id) {
        MenuDTO found = service.findById(id);
        return ResponseEntity.ok(found);
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> findAll() {
        List<MenuDTO> all = (List<MenuDTO>) service.findAll();
        return ResponseEntity.ok(all);
    }
}
