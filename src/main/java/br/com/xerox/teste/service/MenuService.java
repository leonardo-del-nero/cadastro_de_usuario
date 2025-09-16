package br.com.xerox.teste.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;

import br.com.xerox.teste.domain.MenuEntity;
import br.com.xerox.teste.dto.MenuDTO;
import br.com.xerox.teste.exception.RecordNotFoundException;
import br.com.xerox.teste.repository.MenuRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;

    private final Mapper mapper;

    public MenuDTO create(MenuDTO dto) {
        MenuEntity entity = mapper.map(dto, MenuEntity.class);
        entity.setId(null); // Garantir que o ID seja nulo para criação
        MenuEntity result = repository.save(entity);
        return mapper.map(result, MenuDTO.class);
    }

    public MenuDTO update(long id, MenuDTO source) {
        MenuEntity target = repository.findById(id).orElseThrow(RecordNotFoundException::new);
        target.setLabel(source.getLabel());
        target.setUrl(source.getUrl());
        MenuEntity result = repository.save(target);
        return mapper.map(result, MenuDTO.class);
    }

    public void delete(long id) {
        MenuEntity entity = repository.findById(id).orElseThrow(RecordNotFoundException::new);
        repository.delete(entity);
    }

    public MenuDTO findById(long id) {
        MenuEntity result = repository.findById(id).orElseThrow(RecordNotFoundException::new);
        return mapper.map(result, MenuDTO.class);
    }

    public Iterable<MenuDTO> findAll() {
        List<MenuEntity> entities = repository.findAll();
        List<MenuDTO> dtos = entities.stream()
                .map(entity -> mapper.map(entity, MenuDTO.class))
                .toList();
        return dtos;
    }
}
