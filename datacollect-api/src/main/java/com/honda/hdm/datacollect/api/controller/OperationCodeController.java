package com.honda.hdm.datacollect.api.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import com.honda.hdm.datacollect.api.helpers.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.honda.hdm.datacollect.service.domain.impl.DcOperationCodeService;
import com.honda.hdm.datacollect.model.entity.dto.DcOperationCodeDto;
import com.honda.hdm.datacollect.model.enums.ServiceTypeEnum;
import com.honda.hdm.datacollect.response.FriendlyError;

/**
 * @author VJC80519
 * @package com.honda.hdm.datacollect.api.controller
 * @project datacollect-api
 * @date 25 nov 2021
 *
 */
@RestController
@RequestMapping(value = "/operation/code")
public class OperationCodeController {

    @Autowired
    private DcOperationCodeService dcOperationCodeService;

    @GetMapping
    public ResponseEntity<Page<DcOperationCodeDto>> getOperationCode(@RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage) throws Exception {
        return new ResponseEntity<>(dcOperationCodeService.getOperationCodeList(PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByOne(@PathVariable BigDecimal id) throws Exception {
        DcOperationCodeDto dcOperationCodeDto = dcOperationCodeService.findOneDto(id);
        if (dcOperationCodeDto == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Operation Code not found","warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(dcOperationCodeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savedOperationCode(@RequestBody @Valid DcOperationCodeDto dcOperationCodeDto, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            dcOperationCodeService.saveOrUpdate(dcOperationCodeDto);
            response.addAttribute("msg", "Operation code add Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateOperationCode(@RequestBody @Valid DcOperationCodeDto dcOperationCodeDto, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            dcOperationCodeService.saveOrUpdate(dcOperationCodeDto);
            response.addAttribute("msg", "Operation code update Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/service/type")
    public ResponseEntity<?> getServiceType() {
        return new ResponseEntity<>(ServiceTypeEnum.values(), HttpStatus.CREATED);
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filterTerchief(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @PathVariable String term) {
        return new ResponseEntity<>(dcOperationCodeService.findAllByTermDto(term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }
}
