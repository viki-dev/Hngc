/*Copyright (c) 2019-2020 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.hngc.chat.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.hngc.chat.Messages;

/**
 * Service object for domain model class {@link Messages}.
 */
public interface MessagesService {

    /**
     * Creates a new Messages. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Messages if any.
     *
     * @param messages Details of the Messages to be created; value cannot be null.
     * @return The newly created Messages.
     */
    Messages create(@Valid Messages messages);


	/**
     * Returns Messages by given id if exists.
     *
     * @param messagesId The id of the Messages to get; value cannot be null.
     * @return Messages associated with the given messagesId.
	 * @throws EntityNotFoundException If no Messages is found.
     */
    Messages getById(Integer messagesId);

    /**
     * Find and return the Messages by given id if exists, returns null otherwise.
     *
     * @param messagesId The id of the Messages to get; value cannot be null.
     * @return Messages associated with the given messagesId.
     */
    Messages findById(Integer messagesId);

	/**
     * Find and return the list of Messages by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param messagesIds The id's of the Messages to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Messages associated with the given messagesIds.
     */
    List<Messages> findByMultipleIds(List<Integer> messagesIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Messages. It replaces all fields of the existing Messages with the given messages.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Messages if any.
     *
     * @param messages The details of the Messages to be updated; value cannot be null.
     * @return The updated Messages.
     * @throws EntityNotFoundException if no Messages is found with given input.
     */
    Messages update(@Valid Messages messages);


    /**
     * Partially updates the details of an existing Messages. It updates only the
     * fields of the existing Messages which are passed in the messagesPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Messages if any.
     *
     * @param messagesId The id of the Messages to be deleted; value cannot be null.
     * @param messagesPatch The partial data of Messages which is supposed to be updated; value cannot be null.
     * @return The updated Messages.
     * @throws EntityNotFoundException if no Messages is found with given input.
     */
    Messages partialUpdate(Integer messagesId, Map<String, Object> messagesPatch);

    /**
     * Deletes an existing Messages with the given id.
     *
     * @param messagesId The id of the Messages to be deleted; value cannot be null.
     * @return The deleted Messages.
     * @throws EntityNotFoundException if no Messages found with the given id.
     */
    Messages delete(Integer messagesId);

    /**
     * Deletes an existing Messages with the given object.
     *
     * @param messages The instance of the Messages to be deleted; value cannot be null.
     */
    void delete(Messages messages);

    /**
     * Find all Messages matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Messages.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Messages> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Messages matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Messages.
     *
     * @see Pageable
     * @see Page
     */
    Page<Messages> findAll(String query, Pageable pageable);

    /**
     * Exports all Messages matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all Messages matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the Messages in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Messages.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);


}