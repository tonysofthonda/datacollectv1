package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.entity.dto.DcModelDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    IDcModelService modelService;

    @GetMapping("/list")
    public ResponseEntity<Page<DcModelDto>> listModels(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage) {
        return new ResponseEntity<>(modelService.findAllDto(PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModel(@PathVariable Long id) {
        DcModelDto model = modelService.findOneDto(id);
        if (model == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Model not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<Page<DcModelDto>> filterModel(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @PathVariable String term) {
        return new ResponseEntity<>(modelService.findAllByTermDto(term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @GetMapping("/code-year/{code}/{year}")
    public ResponseEntity<?> findOrderTypeByCode(@PathVariable String code, @PathVariable Integer year) {
        return new ResponseEntity<>(modelService.findOneByCodeAndYearDto(code, year), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addModel(@RequestBody @Valid DcModelDto model, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcModelDto newModel = modelService.saveDto(model);
            response.addAttribute("model", newModel);
            response.addAttribute("msg", "Model Added Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateModel(@PathVariable Long id, @RequestBody @Valid DcModelDto model, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcModelDto editedModel = modelService.updateDto(model, id);
            if (editedModel == null) {
                response.addAttribute("friendlyError", new FriendlyError("Model not found", "warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("model", editedModel);
            response.addAttribute("msg", "Model Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateModelStatus(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Model response = new ExtendedModelMap();
        Boolean status = (Boolean) request.get("status");
        DcModelDto model = modelService.changeStatus(status, id);
        if (model == null) {
            response.addAttribute("friendlyError", new FriendlyError("Model not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.addAttribute("model", model);
        response.addAttribute("msg", "Model Status Updated Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
