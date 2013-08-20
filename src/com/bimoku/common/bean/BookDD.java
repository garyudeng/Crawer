package com.bimoku.common.bean;

import java.io.Serializable;

import javax.persistence.Table;

/**
 * 图书明细----当当网站点
 * @author LPM
 * @Date 13-07-31
 */
@SuppressWarnings("serial")
@Table(name="t_bookdd")
public class BookDD extends BookDetail implements Serializable{
	public BookDD(){
		super();
	}
}
