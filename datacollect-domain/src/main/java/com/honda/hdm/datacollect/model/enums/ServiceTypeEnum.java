package com.honda.hdm.datacollect.model.enums;

public enum ServiceTypeEnum {

	BP("BP"),
	SE("SE"),
	TO("TO");
	
	private final String service;
	
	private ServiceTypeEnum(String service) {
		this.service = service;
	}
	
	public static ServiceTypeEnum findByService(String name) {
		for(ServiceTypeEnum enumService : ServiceTypeEnum.values()) {
			if(enumService.service.equals(name)) {
				return enumService;
			}
		}
		return null;
	}
}
