package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityXDealerDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcFacilityXDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dealer/facilities")
public class DealerFacilityController {
    @Autowired
    IDcFacilityXDealerService facilityXDealerService;

    @GetMapping("/{dealerId}")
    public ResponseEntity<?> getDealerFacilities(
            @PathVariable Long dealerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage) {
        return new ResponseEntity<>(facilityXDealerService.findAllDto(dealerId, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @GetMapping("/{dealerId}/{facilityId}")
    public ResponseEntity<?> getDealerFacility(@PathVariable Long dealerId, @PathVariable Long facilityId) {
        return new ResponseEntity<>(facilityXDealerService.findOneDto(new DcFacilityXDealerPK(facilityId, dealerId)), HttpStatus.OK);
    }

    @GetMapping("/filter/{dealerId}/{term}")
    public ResponseEntity<?> filterDealerFacilities(
            @PathVariable Long dealerId,
            @PathVariable String term,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage
    ) {
        return new ResponseEntity<>(facilityXDealerService.findAllByTermDto(dealerId, term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDealerFacility(@RequestBody @Valid DcFacilityXDealerDto facility, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcFacilityXDealerDto newDealerFacility = facilityXDealerService.saveDto(facility);
            if (newDealerFacility == null) {
                response.addAttribute("friendlyError", new FriendlyError("Dealer Facility not found", "warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("dealerFacility", newDealerFacility);
            response.addAttribute("msg", "Dealer Facility Added Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDealerFacility(@RequestBody DcFacilityXDealerDto facility) {
        Model response = new ExtendedModelMap();
        if(facility.getQuantity() < 1){
            response.addAttribute("quantity","Quantity must bet major than one");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcFacilityXDealerDto editedDealerFacility = facilityXDealerService.updateDto(facility, new DcFacilityXDealerPK(facility.getFacility().getId(), facility.getDealer().getId()));
            if (editedDealerFacility == null) {
                response.addAttribute("friendlyError", new FriendlyError("Dealer Facility not found", "warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("dealerFacility", editedDealerFacility);
            response.addAttribute("msg", "Dealer Facility Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDealerFacility(@RequestBody DcFacilityXDealerDto facility) {
        Model response = new ExtendedModelMap();
        DcFacilityXDealerPK id = new DcFacilityXDealerPK(facility.getFacility().getId(), facility.getDealer().getId());
        DcFacilityXDealerDto dealerFacility = facilityXDealerService.findOneDto(id);
        if (dealerFacility == null) {
            response.addAttribute("friendlyError", new FriendlyError("Dealer Facility not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        facilityXDealerService.deleteById(id);
        response.addAttribute("msg", "Dealer Facility Deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
