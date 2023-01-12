/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcModel;
import com.honda.hdm.datacollect.model.entity.dto.DcModelDto;

/**
 * @author VJC80587
 */
public interface IDcModelService extends IRecordStatusableService<DcModel, Long>,
        IDtoService<DcModelDto, Long> {

    public DcModel findOneByCode(String dcModelCode) throws Exception;

    public DcModel findOneByCodeAndYear(String code, String year);

    public DcModelDto findOneByCodeDto(String dcModelCode);

    public DcModelDto findOneByCodeAndYearDto(String code, Integer year);

    public DcModelDto changeStatus(Boolean status, Long id);
}
