package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.CaisseController;
import com.lsd.logement.dto.CaisseDTO;
import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.mapper.CaisseMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.CaisseService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/caisse")
@RestController
public class CaisseControllerImpl implements CaisseController {
    private final CaisseService caisseService;
    private final CaisseMapper caisseMapper;

    public CaisseControllerImpl(CaisseService caisseService, CaisseMapper caisseMapper) {
        this.caisseService = caisseService;
        this.caisseMapper = caisseMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody CaisseDTO caisseDTO) {
        try {
            Caisse caisse = caisseMapper.asEntity(caisseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.save(caisse)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            Caisse caisse = caisseService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisse))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            caisseService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "Caisse supprimé avec succes")
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }

    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTOList(caisseService.findAll()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<Caisse> caissePage = caisseService.findAll(pageable);
            List<CaisseDTO> dtoList = caissePage
                    .stream()
                    .map(caisseMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, caissePage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody CaisseDTO caisseDTO, @PathVariable("id") Integer id) {
        try {
            Caisse caisse = caisseMapper.asEntity(caisseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.update(caisse, id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/opened/{id}")
    public ResponseEntity<ApiResponse<?>> findUserOpenedCaisse(Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.findOpened(id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<?>> findUserCaisse(Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.findByUserId(id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PostMapping("/open")
    public ResponseEntity<ApiResponse<?>> openCaisse(CaisseDTO caisseDTO) {
        try {
            Caisse caisse = caisseMapper.asEntity(caisseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.open(caisse)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PostMapping("/close-request")
    public ResponseEntity<ApiResponse<?>> closeCaisseRequest(CaisseDTO caisseDTO) {
        try {
            Caisse caisse = caisseMapper.asEntity(caisseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.closeRequest(caisse)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/close/{id}")
    public ResponseEntity<ApiResponse<?>> closeCaisse(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.close(id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/validate/{id}")
    public ResponseEntity<ApiResponse<?>> openCaisse(Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(caisseMapper.asDTO(caisseService.validate(id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/export/journal/{id}")
    public ResponseEntity<?> downloadCaisseReport(@PathVariable("id") Integer id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=Casier-report.pdf");
            InputStream pdfStream = caisseService.generatePdf(id);
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(pdfStream));
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}