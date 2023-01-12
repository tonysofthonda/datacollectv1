package com.honda.hdm.datacollect.service.converter;

import com.honda.hdm.datacollect.model.entity.*;
import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.model.entity.dto.*;
import com.honda.hdm.datacollect.model.entity.dto.auth.*;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.IDcFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DtoConverter {

	@Lazy
    @Autowired
    IDcFacilityService facilityService;

	@Lazy
    @Autowired
    IDcDealerService dealerService;

    public DcContactDto convertContact(DcContact contact) {
        DcContactDto dto = new DcContactDto();
        dto.setId(contact.getId());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setMotherLastName(contact.getMotherLastName());
        dto.setEmail(contact.getEmail());
        dto.setNotes(contact.getNotes());
        return dto;
    }

    public DcTerchiefDto convertTerchief(DcTerchief terchief){
        DcTerchiefDto dto = new DcTerchiefDto();
        dto.setId(terchief.getId());
        dto.setFirstName(terchief.getFirstName());
        dto.setLastName(terchief.getLastName());
        dto.setMotherLastName(terchief.getMotherLast());
        dto.setNotes(terchief.getNotes());
        return dto;
    }

    public DcDealerGroupDto convertDealerGroup(DcDealerGroup dealerGroup) {
        DcDealerGroupDto dto = new DcDealerGroupDto();
        dto.setId(dealerGroup.getId());
        dto.setName(dealerGroup.getName());
        dto.setStatus(dealerGroup.getDcRecordStatusId());
        return dto;
    }

    public DcFacilityDto convertFacility(DcFacility facility) {
        DcFacilityDto dto = new DcFacilityDto();
        dto.setId(facility.getId());
        dto.setConcept(facility.getConcept());
        dto.setDescription(facility.getDescription());
        return dto;
    }

    public DcOperationCodeDto convertOperationCode(DcOperationCode operationCode) {
        DcOperationCodeDto dto = new DcOperationCodeDto();
        dto.setId(operationCode.getId());
        dto.setCode(operationCode.getCode());
        dto.setDescription(operationCode.getDescription());
        dto.setServiceType(operationCode.getServiceType());
        return dto;
    }

    public DcServiceTypeDto convertServiceType(DcServiceType serviceType) {
        DcServiceTypeDto dto = new DcServiceTypeDto();
        dto.setId(serviceType.getId());
        dto.setName(serviceType.getName());
        return dto;
    }

    public DcOrderTypeDto convertOrderType(DcOrderType orderType) {
        DcOrderTypeDto dto = new DcOrderTypeDto();
        dto.setId(orderType.getId());
        dto.setCode(orderType.getCode());
        dto.setDescription(orderType.getDescription());
        dto.setServicesTypes(orderType.getDcServicesTypes().stream().map(this::convertServiceType).collect(Collectors.toList()));
        return dto;
    }

    public DcSystemServiceDto convertSystemService(DcSystemService systemService) {
        DcSystemServiceDto dto = new DcSystemServiceDto();
        dto.setId(systemService.getId());
        dto.setName(systemService.getName());
        return dto;
    }

    public DcModelDto convertModel(DcModel model) {
        DcModelDto dto = new DcModelDto();
        dto.setId(model.getId());
        dto.setCode(model.getCode());
        dto.setBrand(model.getBrand());
        dto.setName(model.getName());
        if(model.getYear() != null){
            dto.setYear(Integer.parseInt(model.getYear()));
        }
        dto.setAssemblyLocation(model.getAssemblyLocation());
        dto.setExtColorCode(model.getExtColorCode());
        dto.setExtColorName(model.getExtColorName());
        dto.setIntColorCode(model.getIntColorCode());
        dto.setAccountNumber(model.getAccountNumber());
        dto.setDescription(model.getDescription());
        dto.setStatus(model.getDcRecordStatusId());
        dto.setSystemServices(model.getDcSystemServices().stream().map(this::convertSystemService).collect(Collectors.toList()));
        return dto;
    }

    public DcCityDto convertCity(DcCity city) {
        DcCityDto dto = new DcCityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setState(convertStateWithoutCities(city.getDcStateId()));
        return dto;
    }

    public DcStateDto convertState(DcState state){
        DcStateDto dto = new DcStateDto();
        dto.setId(state.getId());
        dto.setName(state.getName());
        dto.setCities(state.getDcCityList().stream().map(this::convertCity).collect(Collectors.toList()));
        return dto;
    }

    private DcStateDto convertStateWithoutCities(DcState state){
        DcStateDto dto = new DcStateDto();
        dto.setId(state.getId());
        dto.setName(state.getName());
        return dto;
    }

    public DcWorkshopDto convertWorkshop(DcWorkshop workshop){
        DcWorkshopDto dto = new DcWorkshopDto();
        dto.setId(workshop.getId());
        dto.setName(workshop.getName());
        return dto;
    }

    public DcDealerDto convertDealer(DcDealer dealer) {
        DcDealerDto dto = new DcDealerDto();
        dto.setId(dealer.getId());
        dto.setDealerNumber(dealer.getDealerNumber());
        dto.setName(dealer.getName());
        dto.setBusinessName(dealer.getBusinessName());
        dto.setRfc(dealer.getRfc());
        dto.setCity(convertCity(dealer.getDcCityId()));
        dto.setStreet(dealer.getAddrStreet());
        dto.setNeighborhood(dealer.getAddrNeighborhood());
        if (dealer.getAddrPostcode() != null) {
            dto.setPostCode(Integer.parseInt(dealer.getAddrPostcode()));
        }
        dto.setDealerGroup(convertDealerGroup(dealer.getDcDealerGroup()));
        dto.setTerchief(convertTerchief(dealer.getDcTerchief()));
        dto.setWorkshop(convertWorkshop(dealer.getDcWorkshopId()));
        dto.setStatus(dealer.getDcRecordStatusId());
        return dto;
    }

    public DcFacilityXDealerDto convertFacilityXDealer(DcFacilityXDealer facilityXDealer) {
        DcFacilityXDealerDto dto = new DcFacilityXDealerDto();
        Long facilityId = facilityXDealer.getDcFacilityXDealerPK().getDcFacilityId();
        Long dealerId = facilityXDealer.getDcFacilityXDealerPK().getDcDealerId();

        dto.setFacility(facilityService.findOneDto(facilityId));
        dto.setDealer(dealerService.findOneDto(dealerId));
        dto.setQuantity(facilityXDealer.getQuantity());
        return dto;
    }

    public DcPositionDto convertPosition(DcPosition position){
        DcPositionDto dto = new DcPositionDto();
        dto.setId(position.getId());
        dto.setName(position.getNameDescription());
        dto.setJobId(position.getJobId());
        return dto;
    }

    public DcContactXDealerDto convertContactXDealer(DcContactXDealer contactXDealer){
        DcContactXDealerDto dto = new DcContactXDealerDto();
        dto.setId(contactXDealer.getId());
        dto.setContact(convertContact(contactXDealer.getContact()));
        dto.setDealer(dealerService.findOneDto(contactXDealer.getDealerId()));
//        dto.setPosition(convertPosition(contactXDealer.getPosition()));
//        dto.setNotifications(contactXDealer.getNotifications().stream().map(this::convertSystemService).collect(Collectors.toList()));
        return dto;
    }

    public DcMenuCategoryDto convertMenuCategory(DcMenuCategory menuCategory){
        DcMenuCategoryDto dto = new DcMenuCategoryDto();
        dto.setId(menuCategory.getId());
        dto.setName(menuCategory.getName());
        dto.setOrder(menuCategory.getOrder());
        dto.setViews(menuCategory.getViews().stream().map(this::convertView).collect(Collectors.toList()));
        return dto;
    }

    private DcMenuCategoryDto convertMenuCategoryWithoutViews(DcMenuCategory menuCategory){
        DcMenuCategoryDto dto = new DcMenuCategoryDto();
        dto.setId(menuCategory.getId());
        dto.setName(menuCategory.getName());
        dto.setOrder(menuCategory.getOrder());
        return dto;
    }

    public DcViewDto convertView(DcView view){
        DcViewDto dto = new DcViewDto();
        dto.setId(view.getId());
        dto.setName(view.getName());
        dto.setFriendlyName(view.getFriendlyName());
        dto.setRoute(view.getRoute());
        dto.setOrder(view.getOrder());
        dto.setMenuCategory(convertMenuCategoryWithoutViews(view.getMenuCategory()));
        dto.setViewActions(view.getViewActions().stream().map(this::convertViewAction).collect(Collectors.toList()));
        return dto;
    }

    private DcViewDto convertViewWithoutViewActions(DcView view){
        DcViewDto dto = new DcViewDto();
        dto.setId(view.getId());
        dto.setName(view.getName());
        dto.setFriendlyName(view.getFriendlyName());
        dto.setRoute(view.getRoute());
        dto.setOrder(view.getOrder());
        dto.setMenuCategory(convertMenuCategoryWithoutViews(view.getMenuCategory()));
        return dto;
    }

    public DcViewActionDto convertViewAction(DcViewAction viewAction){
        DcViewActionDto dto = new DcViewActionDto();
        dto.setId(viewAction.getId());
        dto.setShortName(viewAction.getShortName());
        dto.setFriendlyName(viewAction.getFriendlyName());
        dto.setView(convertViewWithoutViewActions(viewAction.getView()));
        dto.setAssignedRoles(viewAction.getAssignedRoleList().stream().map(this::convertRole).collect(Collectors.toList()));
        return dto;
    }

    private DcViewActionDto convertViewActionWithoutRoles(DcViewAction viewAction){
        DcViewActionDto dto = new DcViewActionDto();
        dto.setId(viewAction.getId());
        dto.setShortName(viewAction.getShortName());
        dto.setFriendlyName(viewAction.getFriendlyName());
        dto.setView(convertViewWithoutViewActions(viewAction.getView()));
        return dto;
    }

    public DcRoleDto convertRole(DcRole role){
        DcRoleDto dto = new DcRoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        if(role.getPositions() != null){
            dto.setPositions(role.getPositions().stream().map(this::convertPosition).collect(Collectors.toList()));
        }
        if(role.getPermissionList() != null){
            dto.setPermissions(role.getPermissionList().stream().map(this::convertViewActionWithoutRoles).collect(Collectors.toList()));
        }
        if(role.getDcLdapGroupList() != null){
            dto.setLdapGroups(role.getDcLdapGroupList().stream().map(this::convertLdapGroupWithoutRoles).collect(Collectors.toList()));
        }
        return dto;
    }

    private DcRoleDto convertRoleWithoutLdapGroups(DcRole role){
        DcRoleDto dto = new DcRoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        if(role.getPositions() != null){
            dto.setPositions(role.getPositions().stream().map(this::convertPosition).collect(Collectors.toList()));
        }
        if(role.getPermissionList() != null){
            dto.setPermissions(role.getPermissionList().stream().map(this::convertViewActionWithoutRoles).collect(Collectors.toList()));
        }
        return dto;
    }

    public DcLdapGroupDto convertLdapGroup(DcLdapGroup ldapGroup){
        DcLdapGroupDto dto = new DcLdapGroupDto();
        dto.setId(ldapGroup.getId());
        dto.setName(ldapGroup.getName());
        dto.setLdapId(ldapGroup.getLdapId());
        dto.setRoles(ldapGroup.getDcRoleList().stream().map(this::convertRoleWithoutLdapGroups).collect(Collectors.toList()));
        return dto;
    }

    private DcLdapGroupDto convertLdapGroupWithoutRoles(DcLdapGroup ldapGroup){
        DcLdapGroupDto dto = new DcLdapGroupDto();
        dto.setId(ldapGroup.getId());
        dto.setName(ldapGroup.getName());
        dto.setLdapId(ldapGroup.getLdapId());
        return dto;
    }
}
