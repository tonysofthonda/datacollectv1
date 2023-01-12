package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.FetchType;
import com.honda.hdm.datacollect.service.domain.IDcMenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IDcMenuCategoryService menuCategoryService;

    @GetMapping("/list")
    public ResponseEntity<?> listMenu(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @RequestParam(name = "fetchType", defaultValue = "lazy") String fetchType) {
        if (FetchType.LAZY.toString().equals(fetchType)) {
            return new ResponseEntity<>(menuCategoryService.findAllDto(PageRequest.of(page, elementsByPage)), HttpStatus.OK);
        } else if (FetchType.EAGER.toString().equals(fetchType)) {
            return new ResponseEntity<>(menuCategoryService.findAllDto(), HttpStatus.OK);
        } else {
            Model response = new ExtendedModelMap();
            response.addAttribute("msg", "fetchType required as request param");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
