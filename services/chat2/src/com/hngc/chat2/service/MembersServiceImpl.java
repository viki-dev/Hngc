/*Copyright (c) 2019-2020 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.hngc.chat2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.hngc.chat2.Members;


/**
 * ServiceImpl object for domain model class Members.
 *
 * @see Members
 */
@Service("chat2.MembersService")
@Validated
public class MembersServiceImpl implements MembersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MembersServiceImpl.class);


    @Autowired
    @Qualifier("chat2.MembersDao")
    private WMGenericDao<Members, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Members, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "chat2TransactionManager")
    @Override
    public Members create(Members members) {
        LOGGER.debug("Creating a new Members with information: {}", members);

        Members membersCreated = this.wmGenericDao.create(members);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(membersCreated);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager")
    @Override
    public Members getById(Integer membersId) {
        LOGGER.debug("Finding Members by id: {}", membersId);
        return this.wmGenericDao.findById(membersId);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager")
    @Override
    public Members findById(Integer membersId) {
        LOGGER.debug("Finding Members by id: {}", membersId);
        try {
            return this.wmGenericDao.findById(membersId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Members found with id: {}", membersId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager")
    @Override
    public List<Members> findByMultipleIds(List<Integer> membersIds, boolean orderedReturn) {
        LOGGER.debug("Finding Members by ids: {}", membersIds);

        return this.wmGenericDao.findByMultipleIds(membersIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "chat2TransactionManager")
    @Override
    public Members update(Members members) {
        LOGGER.debug("Updating Members with information: {}", members);

        this.wmGenericDao.update(members);
        this.wmGenericDao.refresh(members);

        return members;
    }

    @Transactional(value = "chat2TransactionManager")
    @Override
    public Members partialUpdate(Integer membersId, Map<String, Object>membersPatch) {
        LOGGER.debug("Partially Updating the Members with id: {}", membersId);

        Members members = getById(membersId);

        try {
            ObjectReader membersReader = this.objectMapper.reader().forType(Members.class).withValueToUpdate(members);
            members = membersReader.readValue(this.objectMapper.writeValueAsString(membersPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", membersPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        members = update(members);

        return members;
    }

    @Transactional(value = "chat2TransactionManager")
    @Override
    public Members delete(Integer membersId) {
        LOGGER.debug("Deleting Members with id: {}", membersId);
        Members deleted = this.wmGenericDao.findById(membersId);
        if (deleted == null) {
            LOGGER.debug("No Members found with id: {}", membersId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Members.class.getSimpleName(), membersId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "chat2TransactionManager")
    @Override
    public void delete(Members members) {
        LOGGER.debug("Deleting Members with {}", members);
        this.wmGenericDao.delete(members);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager")
    @Override
    public Page<Members> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Members");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager")
    @Override
    public Page<Members> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Members");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service chat2 for table Members to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service chat2 for table Members to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "chat2TransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}