package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.entity.dto.DcContactXDealerDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcContactXDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dealer/contact")
public class DealerContactController {

	@Lazy
    @Autowired
    IDcContactXDealerService contactXDealerService;

    @GetMapping("/list/{dealerId}")
    public ResponseEntity<?> getDealerContacts(
            @PathVariable Long dealerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage) {
        return new ResponseEntity<>(contactXDealerService.findAllDto(dealerId, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @GetMapping("/{dealerContactId}")
    public ResponseEntity<?> getDealerContact(@PathVariable Long dealerContactId) {
        return new ResponseEntity<>(contactXDealerService.findOneDto(dealerContactId), HttpStatus.OK);
    }

    @GetMapping("/filter/{dealerId}/{term}")
    public ResponseEntity<?> filterDealerContact(
            @PathVariable Long dealerId,
            @PathVariable String term,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage
    ) {
        return new ResponseEntity<>(contactXDealerService.findAllByTermDto(dealerId, term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDealerContact(@RequestBody @Valid DcContactXDealerDto dealerContact, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcContactXDealerDto newDealerContact = contactXDealerService.saveDto(dealerContact);
            if (newDealerContact == null) {
                response.addAttribute("friendlyError", new FriendlyError("Dealer, Position or any Notification not found", "warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("dealerContact", newDealerContact);
            response.addAttribute("msg", "Dealer Facility Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{dealerContactId}")
    public ResponseEntity<?> updateDealerContact(@PathVariable Long dealerContactId, @RequestBody @Valid DcContactXDealerDto dealerContact, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcContactXDealerDto editedDealerContact = contactXDealerService.updateDto(dealerContact, dealerContactId);
            if (editedDealerContact == null) {
                response.addAttribute("friendlyError", new FriendlyError("Dealer Contact not found", "warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("dealerContact", editedDealerContact);
            response.addAttribute("msg", "Dealer Contact Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{dealerContactId}")
    public ResponseEntity<?> deleteDealerContact(@PathVariable Long dealerContactId) {
        Model response = new ExtendedModelMap();
        DcContactXDealerDto dealerContact = contactXDealerService.findOneDto(dealerContactId);
        if (dealerContact == null) {
            response.addAttribute("friendlyError", new FriendlyError("Dealer Contact not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        contactXDealerService.deleteById(dealerContactId);
        response.addAttribute("msg", "Dealer Contact Deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
