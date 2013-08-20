package com.bimoku.common.bean;

import java.io.Serializable;

import javax.persistence.Table;

import com.bimoku.util.Constant;


/**
 * 图书明细---亚马逊站点
 * @author LPM
 * @Date 13-07-31
 */
@Table(name="t_bookamazon")
@SuppressWarnings("serial")
public class BookAmazon extends BookDetail implements Serializable{

}
