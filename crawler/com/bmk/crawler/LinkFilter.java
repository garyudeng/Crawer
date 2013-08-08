/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmk.crawler;

/**
 *
 * @author Lee
 */
public interface LinkFilter {
	public boolean accept(String url);
}