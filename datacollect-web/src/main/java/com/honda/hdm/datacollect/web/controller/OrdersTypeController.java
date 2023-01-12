package com.honda.hdm.datacollect.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honda.hdm.datacollect.model.entity.DcOrderType;
import com.honda.hdm.datacollect.model.enums.ServiceTypeEnum;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderTypeService;

@Controller
@RequestMapping("orderstype")
public class OrdersTypeController {

    @Autowired
    private DcOrderTypeService dcOrderTypeService;

    @GetMapping(path = {"list"})
    @PreAuthorize("hasPermission('orderstype', 'view')")
    public String list(Model model) throws Exception {
        List<DcOrderType> dcOrderTypeList = dcOrderTypeService.findDcOrderType();
        model.addAttribute("operationCodeList", dcOrderTypeList);
        return "orderstype/list";
    }

    @GetMapping(path = {"add"})
    @PreAuthorize("hasPermission('orderstype', 'create')")
    public String add(Model model) {
        model.addAttribute("service", ServiceTypeEnum.values());
        model.addAttribute("ordersTypeForm", new DcOrderType());
        return "orderstype/form";

    }

    @PostMapping(path = {"add"})
    @PreAuthorize("hasPermission('orderstype', 'create')")
    public ModelAndView addOperationCodes(@ModelAttribute("ordersTypeForm") @Validated DcOrderType dcOrderType,
            BindingResult result, Model model, final RedirectAttributes ra) throws Exception {

        if (dcOrderTypeService.findOneByCode(dcOrderType.getCode()) != null) {
            model.addAttribute("error", "The order type already exist");
            return new ModelAndView("orderstype/form");
        }
        dcOrderTypeService.save(dcOrderType);
        ra.addFlashAttribute("message", "Order type created successfully");
        return new ModelAndView("redirect:/orderstype/list");
    }

    @GetMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('orderstype', 'edit')")
    public String edit(Model model, @PathVariable("id") BigDecimal id, final RedirectAttributes ra) {
        DcOrderType dcOrderType = dcOrderTypeService.findOne(id);
        if (dcOrderType == null) {
            ra.addFlashAttribute("error", "Order type not exists");
            return "redirect:/orderstype/list";
        }

        model.addAttribute("edit", true);
        model.addAttribute("service", ServiceTypeEnum.values());
        model.addAttribute("ordersTypeForm", dcOrderType);
        return "orderstype/form";
    }

    @PostMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('orderstype', 'edit')")
    public ModelAndView edit(@ModelAttribute("ordersTypeForm") @Validated DcOrderType dcOrderTypeForm,
            @PathVariable("id") BigDecimal id, Model model, final RedirectAttributes ra) throws Exception {
        DcOrderType dcOrderType = dcOrderTypeService.findOneByCode(dcOrderTypeForm.getCode());

        if (dcOrderType != null && !dcOrderType.getId().equals(id)) {
            model.addAttribute("error", "The order type already exist");
            return new ModelAndView("orderstype/form");
        }
        dcOrderTypeService.save(dcOrderTypeForm);
        ra.addFlashAttribute("message", "Order type updated successfully");
        return new ModelAndView("redirect:/orderstype/list");

    }

}
