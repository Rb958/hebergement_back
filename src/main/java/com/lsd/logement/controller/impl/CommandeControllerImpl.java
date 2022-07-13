package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.CommandeController;
import com.lsd.logement.dto.CommandeDTO;
import com.lsd.logement.entity.stock.Commande;
import com.lsd.logement.mapper.CommandeMapper;
import com.lsd.logement.service.CommandeService;
import okhttp3.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/commande")
@RestController
public class CommandeControllerImpl implements CommandeController {
    private final CommandeService commandeService;
    private final CommandeMapper commandeMapper;

    public CommandeControllerImpl(CommandeService commandeService, CommandeMapper commandeMapper) {
        this.commandeService = commandeService;
        this.commandeMapper = commandeMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommandeDTO save(@RequestBody CommandeDTO commandeDTO) {
        Commande commande = commandeMapper.asEntity(commandeDTO);
        return commandeMapper.asDTO(commandeService.save(commande));
    }

    @Override
    @GetMapping("/{id}")
    public CommandeDTO findById(@PathVariable("id") Integer id) {
        Commande commande = commandeService.findById(id).orElse(null);
        return commandeMapper.asDTO(commande);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        commandeService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<CommandeDTO> list() {
        return commandeMapper.asDTOList(commandeService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<CommandeDTO> pageQuery(Pageable pageable) {
        Page<Commande> commandePage = commandeService.findAll(pageable);
        List<CommandeDTO> dtoList = commandePage
                .stream()
                .map(commandeMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, commandePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public CommandeDTO update(@RequestBody CommandeDTO commandeDTO, @PathVariable("id") Integer id) {
        Commande commande = commandeMapper.asEntity(commandeDTO);
        return commandeMapper.asDTO(commandeService.update(commande, id));
    }
}