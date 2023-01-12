package com.honda.hdm.datacollect.service.converter;

import com.honda.hdm.datacollect.model.entity.*;
import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.model.entity.dto.*;
import com.honda.hdm.datacollect.model.entity.dto.auth.*;
import com.honda.hdm.datacollect.service.domain.impl.auth.IDcRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ModelConverter {

	@Lazy
    @Autowired
    IDcRoleService roleService;

    public DcContact convertContact(DcContactDto dto) {
        DcContact contact = new DcContact();
        contact.setId(dto.getId());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setMotherLastName(dto.getMotherLastName());
        contact.setEmail(dto.getEmail());
        contact.setNotes(dto.getNotes());
        return contact;
    }

    public DcTerchief convertTerchief(DcTerchiefDto dto) {
        DcTerchief terchief = new DcTerchief();
        terchief.setId(dto.getId());
        terchief.setFirstName(dto.getFirstName());
        terchief.setLastName(dto.getLastName());
        terchief.setMotherLast(dto.getMotherLastName());
        terchief.setNotes(dto.getNotes());
        return terchief;
    }

    public DcDealerGroup convertDealerGroup(DcDealerGroupDto dto) {
        DcDealerGroup dealerGroup = new DcDealerGroup();
        dealerGroup.setId(dto.getId());
        dealerGroup.setName(dto.getName());
        dealerGroup.setDcRecordStatusId(dto.getStatus());
        return dealerGroup;
    }

    public DcFacility convertFacility(DcFacilityDto dto) {
        DcFacility facility = new DcFacility();
        facility.setId(dto.getId());
        facility.setConcept(dto.getConcept());
        facility.setDescription(dto.getDescription());
        return facility;
    }

    public DcOperationCode convertOperationCode(DcOperationCodeDto dto) {
        DcOperationCode operationCode = new DcOperationCode();
        operationCode.setId(dto.getId());
        operationCode.setCode(dto.getCode());
        operationCode.setDescription(dto.getDescription());
        operationCode.setServiceType(dto.getServiceType());
        return operationCode;
    }

    public DcServiceType convertServiceType(DcServiceTypeDto dto) {
        DcServiceType serviceType = new DcServiceType();
        serviceType.setId(dto.getId());
        serviceType.setName(dto.getName());
        return serviceType;
    }

    public DcOrderType convertOrderType(DcOrderTypeDto dto) {
        DcOrderType orderType = new DcOrderType();
        orderType.setId(dto.getId());
        orderType.setCode(dto.getCode());
        orderType.setDescription(dto.getDescription());
        orderType.setDcServicesTypes(dto.getServicesTypes().stream().map(this::convertServiceType).collect(Collectors.toList()));
        return orderType;
    }

    public DcSystemService convertSystemService(DcSystemServiceDto dto) {
        DcSystemService systemService = new DcSystemService();
        systemService.setId(dto.getId());
        systemService.setName(dto.getName());
        return systemService;
    }

    public DcModel convertModel(DcModelDto dto) {
        DcModel model = new DcModel();
        model.setId(dto.getId());
        model.setCode(dto.getCode());
        model.setBrand(dto.getBrand());
        model.setName(dto.getName());
        model.setYear(String.valueOf(dto.getYear()));
        model.setAssemblyLocation(dto.getAssemblyLocation());
        model.setExtColorCode(dto.getExtColorCode());
        model.setExtColorName(dto.getExtColorName());
        model.setIntColorCode(dto.getIntColorCode());
        model.setAccountNumber(dto.getAccountNumber());
        model.setDescription(dto.getDescription());
        model.setDcRecordStatusId(dto.getStatus());
        model.setDcSystemServices(dto.getSystemServices().stream().map(this::convertSystemService).collect(Collectors.toList()));
        return model;
    }

    public DcCity convertCity(DcCityDto dto) {
        DcCity city = new DcCity();
        city.setId(dto.getId());
        city.setName(dto.getName());
        city.setDcStateId(convertStateWithoutCities(dto.getState()));
        return city;
    }

    public DcState convertState(DcStateDto dto){
        DcState state = new DcState();
        state.setId(dto.getId());
        state.setName(dto.getName());
        state.setDcCityList(dto.getCities().stream().map(this::convertCity).collect(Collectors.toList()));
        return state;
    }

    private DcState convertStateWithoutCities(DcStateDto dto){
        DcState state = new DcState();
        state.setId(dto.getId());
        state.setName(dto.getName());
        return state;
    }

    public DcWorkshop convertWorkshop(DcWorkshopDto dto){
        DcWorkshop workshop = new DcWorkshop();
        workshop.setId(dto.getId());
        workshop.setName(dto.getName());
        return workshop;
    }

    public DcDealer convertDealer(DcDealerDto dto) {
        DcDealer dealer = new DcDealer();
        dealer.setDealerNumber(dto.getDealerNumber());
        dealer.setName(dto.getName());
        dealer.setBusinessName(dto.getBusinessName());
        dealer.setRfc(dto.getRfc());
        dealer.setDcCityId(convertCity(dto.getCity()));
        dealer.setAddrStreet(dto.getStreet());
        dealer.setAddrNeighborhood(dto.getNeighborhood());
        if (dto.getPostCode() != null) {
            dealer.setAddrPostcode(String.valueOf(dto.getPostCode()));
        }
        dealer.setDcDealerGroup(convertDealerGroup(dto.getDealerGroup()));
        dealer.setDcTerchief(convertTerchief(dto.getTerchief()));
        dealer.setDcWorkshopId(convertWorkshop(dto.getWorkshop()));
        dealer.setDcRecordStatusId(dto.getStatus());
        return dealer;
    }

    public DcFacilityXDealer convertFacilityXDealer(DcFacilityXDealerDto dto) {
        DcFacilityXDealer facilityXDealer = new DcFacilityXDealer();
        DcFacilityXDealerPK id = new DcFacilityXDealerPK();
        id.setDcDealerId(dto.getDealer().getId());
        id.setDcFacilityId(dto.getFacility().getId());
        facilityXDealer.setDcFacilityXDealerPK(id);
        facilityXDealer.setQuantity(dto.getQuantity());
        return facilityXDealer;
    }

    public DcPosition convertPosition(DcPositionDto dto){
        DcPosition position = new DcPosition();
        position.setId(dto.getId());
        position.setNameDescription(dto.getName());
        position.setJobId(dto.getJobId());
        return position;
    }

    public DcContactXDealer convertContactXDealer(DcContactXDealerDto dto){
        DcContactXDealer contactXDealer = new DcContactXDealer();
        contactXDealer.setId(dto.getId());
        contactXDealer.setContact(convertContact(dto.getContact()));
        contactXDealer.setDealerId(dto.getDealer().getId());
//        contactXDealer.setPosition(convertPosition(dto.getPosition()));
//        contactXDealer.setNotifications(dto.getNotifications().stream().map(this::convertSystemService).collect(Collectors.toList()));
        return contactXDealer;
    }

    public DcMenuCategory convertMenuCategory(DcMenuCategoryDto dto){
        DcMenuCategory menuCategory = new DcMenuCategory();
        menuCategory.setId(dto.getId());
        menuCategory.setName(dto.getName());
        menuCategory.setOrder(dto.getOrder());
        menuCategory.setViews(dto.getViews().stream().map(this::convertView).collect(Collectors.toList()));
        return menuCategory;
    }

    private DcMenuCategory convertMenuCategoryWithoutViews(DcMenuCategoryDto dto){
        DcMenuCategory menuCategory = new DcMenuCategory();
        menuCategory.setId(dto.getId());
        menuCategory.setName(dto.getName());
        menuCategory.setOrder(dto.getOrder());
        return menuCategory;
    }

    public DcView convertView(DcViewDto dto){
        DcView view = new DcView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        view.setFriendlyName(dto.getFriendlyName());
        view.setRoute(dto.getRoute());
        view.setOrder(dto.getOrder());
        view.setMenuCategory(convertMenuCategoryWithoutViews(dto.getMenuCategory()));
        view.setViewActions(dto.getViewActions().stream().map(this::convertViewAction).collect(Collectors.toList()));
        return view;
    }

    private DcView convertViewWithoutViewActions(DcViewDto dto){
        DcView view = new DcView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        view.setFriendlyName(dto.getFriendlyName());
        view.setRoute(dto.getRoute());
        view.setOrder(dto.getOrder());
        view.setMenuCategory(convertMenuCategoryWithoutViews(dto.getMenuCategory()));
        return view;
    }

    public DcViewAction convertViewAction(DcViewActionDto dto){
        DcViewAction viewAction = new DcViewAction();
        viewAction.setId(dto.getId());
        viewAction.setShortName(dto.getShortName());
        viewAction.setFriendlyName(dto.getFriendlyName());
        viewAction.setView(convertViewWithoutViewActions(dto.getView()));
        viewAction.setAssignedRoleList(dto.getAssignedRoles().stream().map(this::convertRole).collect(Collectors.toList()));
        return viewAction;
    }

    private DcViewAction convertViewActionWithoutRoles(DcViewActionDto dto){
        DcViewAction viewAction = new DcViewAction();
        viewAction.setId(dto.getId());
        viewAction.setShortName(dto.getShortName());
        viewAction.setFriendlyName(dto.getFriendlyName());
        viewAction.setView(convertViewWithoutViewActions(dto.getView()));
        return viewAction;
    }

    public DcRole convertRole(DcRoleDto dto){
        DcRole role = new DcRole();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        if(dto.getPositions() != null){
            role.setPositions(dto.getPositions().stream().map(this::convertPosition).collect(Collectors.toList()));
        }
        if(dto.getPermissions() != null){
            role.setPermissionList(dto.getPermissions().stream().map(this::convertViewActionWithoutRoles).collect(Collectors.toList()));
        }
        if(dto.getLdapGroups() != null){
            role.setDcLdapGroupList(dto.getLdapGroups().stream().map(this::convertLdapGroupWithoutRoles).collect(Collectors.toList()));
        }
        return role;
    }

    private DcRole convertRoleWithoutLdapGroups(DcRoleDto dto){
        DcRole role = new DcRole();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        if(dto.getPositions() != null){
            role.setPositions(dto.getPositions().stream().map(this::convertPosition).collect(Collectors.toList()));
        }
        if(dto.getPermissions() != null){
            role.setPermissionList(dto.getPermissions().stream().map(this::convertViewActionWithoutRoles).collect(Collectors.toList()));
        }
        return role;
    }

    public DcLdapGroup convertLdapGroup(DcLdapGroupDto dto){
        DcLdapGroup ldapGroup = new DcLdapGroup();
        ldapGroup.setId(dto.getId());
        ldapGroup.setName(dto.getName());
        ldapGroup.setLdapId(dto.getLdapId());
        ldapGroup.setDcRoleList(dto.getRoles().stream().map(this::convertRoleWithoutLdapGroups).collect(Collectors.toList()));
        return ldapGroup;
    }

    private DcLdapGroup convertLdapGroupWithoutRoles(DcLdapGroupDto dto){
        DcLdapGroup ldapGroup = new DcLdapGroup();
        ldapGroup.setId(dto.getId());
        ldapGroup.setName(dto.getName());
        ldapGroup.setLdapId(dto.getLdapId());
        return ldapGroup;
    }
}
