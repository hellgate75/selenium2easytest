package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Enumeration that describes the different kinds of clause available for the 'That' assertion type
 * <br/>
 * The members are :
 * <br/><b>ANY</b> - Matches Any element in the list comparing two lists of objects
 * <br/><b>ANYTHING</b> - Matches Any attribute comparing two objects
 * <br/><b>EQUALS_TO</b> - Matches All the attributes comparing two objects
 * <br/><b>INSTANCE_OF</b> - Matches the instances of two objects
 * <br/><b>IS</b> - Matches if an object is part another object
 * <br/><b>NOT</b> - Matches if an object is not part another object
 * <br/><b>NOT_NULL</b> - Matches if an object is not null
 * <br/><b>NULL</b> - Matches if an object not null
 * @author Fabrizio Torelli
 *
 */
@XmlEnum
public enum AssertionThatMatcherType {
	/**
	 * Matches Any element in the list comparing two lists of objects
	 */
	ANY,
	/**
	 * Matches Any attribute comparing two objects
	 */
	ANYTHING, 
	/**
	 * Matches All the attributes comparing two objects
	 */
	EQUALS_TO,
	/**
	 * Matches the instances of two objects
	 */
	INSTANCE_OF, 
	/**
	 * Matches if an object is part another object
	 */
	IS, 
	/**
	 * Matches if an object is not part another object
	 */
	NOT, 
	/**
	 * Matches if an object is not null
	 */
	NOT_NULL, 
	/**
	 * Matches if an object not null
	 */
	NULL
}
