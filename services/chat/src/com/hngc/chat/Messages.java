/*Copyright (c) 2019-2020 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.hngc.chat;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Messages generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`messages`")
public class Messages implements Serializable {

    private Integer id;
    private String name;
    private String message;
    private String time;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`name`", nullable = false, insertable = false, updatable = false, length = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "`message`", nullable = false, insertable = false, updatable = false, length = 100)
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "`time`", nullable = true, insertable = false, updatable = false, length = 10)
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Messages)) return false;
        final Messages messages = (Messages) o;
        return Objects.equals(getId(), messages.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}