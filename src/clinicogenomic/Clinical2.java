/*
 * Copyright (c) 1997, 2011, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package clinicogenomic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * This class consists exclusively of public methods that operate on Clinical2 or
 * ClinicalPoint2 objects or return primitive types and ArrayLists.
 *
 * @author  Ioannis Pavlos Panteliadis <panteliad@csd.uoc.gr>
 */
public class Clinical2 {
	private ClinicalDictionary clinicalDictionary;
	
	private ArrayList<String> keys;
	private ArrayList<ClinicalPoint2> cpoints;
	
	private int dimensions;
	
	// Suppresses default constructor, ensuring non-instantiability
	@SuppressWarnings("unused")
	private Clinical2() {
		
	}
	
	/**
	 * Constructor for the clinical file class. By calling this method, the program is opening the specified <i>file</i>
	 * and uses the specified <i>delimiter</i> to split the file and correctly represent it on the memory.
	 * @param file an absolute path giving the base location of the file
	 * @param delimiter
	 */
	public Clinical2(String file, String delimiter) {
		System.out.println("Opening: " + file + "...");
		clinicalDictionary = new ClinicalDictionary();
		keys = new ArrayList<String>();
		cpoints = new ArrayList<ClinicalPoint2>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));  //DEBUG
			reader.readLine();		//skip header
			String words[];
			String line;
			ArrayList<String> my_row = null;
			
			while ((line = reader.readLine()) != null) {
				words = line.split(delimiter);
				my_row = new ArrayList<String>(Arrays.asList(words));
				/*First item is the attribute name*/
				/*The rest is what should be added to the index*/
				String my_key = my_row.get(0);
				keys.add(my_key);
				my_row.remove(0);
				for (String my_val : my_row) {
					clinicalDictionary.addValue(my_key, my_val);
				}
				ClinicalPoint2 cp = new ClinicalPoint2(my_key, my_row);
				cpoints.add(cp);
			}
			this.setDimensions(my_row.size());
			reader.close();
		}
		catch (FileNotFoundException fnfe) {
			System.err.println("FileNotFoundException: " + fnfe.getMessage());
		}
		catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	/**
	 * Generates the string for the current Clinical object that will be used in the weka Header part.
	 * @return A string to be used in the weka Header
	 */
	public String clinicalToWekaHeader() {
		Collections.reverse(keys);
		return this.clinicalDictionary.toWekaHeader(keys);
	}
	
	/**
	 * Generates the string for the current Clinical object to be used in the weka Data part for the specified index.
	 * To be used in an iterative function. The user must be careful to supply a correct index, otherwise an
	 * <tt>IndexOutBounds</tt> Exception will be raised.
	 * @param index 
	 * @return
	 */
	public String clinicalToWekaData(int index) {
		String ret = "";
		for (ClinicalPoint2 p : this.cpoints) {
			ret += p.getValueID(index) + ",";
			
		}
		return ret.substring(0, ret.length() - 1);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<ClinicalPoint2> getClinicalPoints() {
		return this.cpoints;
	}

	/**
	 * 
	 * @return
	 */
	public int getDimensions() {
		return this.dimensions;
	}

	/**
	 * 
	 * @param dimensions
	 */
	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}
	
	
}
