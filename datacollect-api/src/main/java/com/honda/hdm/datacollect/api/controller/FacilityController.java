package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.FetchType;
import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealer;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcFacilityService;
import com.honda.hdm.datacollect.service.domain.impl.DcFacilityXDealerService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    IDcFacilityService facilityService;

    @Autowired
    DcFacilityXDealerService facilityXDealerService;

    @GetMapping("/list")
    public ResponseEntity<?> listFacilities(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @RequestParam(name = "fetchType", defaultValue = "lazy") String fetchType) {
        if (fetchType.equals(FetchType.LAZY.toString())) {
            return new ResponseEntity<>(facilityService.findByRecordStatusIdDto(DcRecordStatusEnum.ENABLED, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
        } else if (fetchType.equals(FetchType.EAGER.toString())) {
            return new ResponseEntity<>(facilityService.findAllDto(), HttpStatus.OK);
        } else {
            Model response = new ExtendedModelMap();
            response.addAttribute("msg", "fetchType required as request param");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFacility(@PathVariable Long id) {
        DcFacilityDto facility = facilityService.findOneDto(id);
        if (facility == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Facility not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(facility, HttpStatus.OK);
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<Page<DcFacilityDto>> filterFacilities(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @PathVariable String term) {
        return new ResponseEntity<>(facilityService.findAllByTermDto(term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFacility(@RequestBody @Valid DcFacilityDto facility, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            DcFacilityDto newFacility = facilityService.saveDto(facility);
            response.addAttribute("facility", newFacility);
            response.addAttribute("msg", "Facility Added Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFacility(@PathVariable Long id, @RequestBody @Valid DcFacilityDto facility, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            DcFacilityDto editedFacility = facilityService.updateDto(facility, id);
            if (editedFacility == null) {
                response.addAttribute("friendlyError", new FriendlyError("Facility not found", "warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("facility", editedFacility);
            response.addAttribute("msg", "Facility Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableFacility(@PathVariable Long id) {
        Model response = new ExtendedModelMap();
        DcFacility facility = facilityService.findOne(id);
        if (facility == null) {
            response.addAttribute("friendlyError", new FriendlyError("Facility not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        List<DcFacilityXDealer> dealers = facilityXDealerService.findAllByFacilityId(id);
        if (dealers.size() > 0) {
            response.addAttribute("friendlyError", new FriendlyError("This Facility cant be disabled because have dealers","warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        facilityService.disable(id);
        response.addAttribute("msg", "Facility Disabled Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
