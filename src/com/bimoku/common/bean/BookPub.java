package com.bimoku.common.bean;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name="t_bookpub")
@SuppressWarnings("serial")
public class BookPub extends BookDetail implements Serializable{

}
