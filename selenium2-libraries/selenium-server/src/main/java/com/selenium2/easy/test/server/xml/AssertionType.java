package com.selenium2.easy.test.server.xml;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Enumeration that describes the different kinds of Assertions available in the framework
 * <br/>
 * The members are :
 * <br/><b>EQUALS</b> - Matches All the attributes comparing two objects
 * <br/><b>NOT_EQUALS</b> - Matches None attribute comparing two objects
 * <br/><b>TRUE</b> - Matches the truth assertion of an object expression
 * <br/><b>FALSE</b> - Matches the false assertion of an object expression
 * <br/><b>ARRAY_EQUALS</b> - Matches Any element in the list comparing two lists of objects
 * <br/><b>NOT_NULL</b> - Matches if an object is not null
 * <br/><b>NULL</b> - Matches if an object not null
 * <br/><b>SAME</b> - Matches Any element in the list comparing two lists of objects
 * <br/><b>NOT_SAME</b> - Matches None element in the list comparing two lists of objects
 * <br/><b>THAT</b> - Matches that an object 'is that' of another object, see: {@link AssertionThatMatcherType}
 * <br/><b>STARTS_WITH</b> - Matches if the an object representative string starts with another object representative string
 * <br/><b>STARTS_WITH_IGNORE_CASE</b> - Matches if the an object representative string starts with, ignoring the case, another object representative string
 * <br/><b>ENDS_WITH</b> - Matches if the an object representative string ends with another object representative string
 * <br/><b>ENDS_WITH_IGNORE_CASE</b> - Matches if the an object representative string ends with, ignoring the case, another object representative string
 * <br/><b>CONTAINS</b> - Matches if the an object representative string contains another object representative string
 * <br/><b>CONTAINS_IGNORE_CASE</b> - Matches if the an object representative string contains, ignoring the case, another object representative string
 * <br/>
 * @author Fabrizio Torelli
 *
 */
@XmlEnum
public enum AssertionType {
	/**
	 * Matches All the attributes comparing two objects
	 */
	EQUALS, 
	/**
	 * Matches None attribute comparing two objects
	 */
	NOT_EQUALS, 
	/**
	 * Matches the truth assertion of an object expression
	 */
	TRUE, 
	/**
	 * Matches the false assertion of an object expression
	 */
	FALSE, 
	/**
	 * Matches Any element in the list comparing two lists of objects
	 */
	ARRAY_EQUALS, 
	/**
	 * Matches if an object not null
	 */
	NULL, 
	/**
	 * Matches if an object is not null
	 */
	NOT_NULL, 
	/**
	 * Matches Any element in the list comparing two lists of objects
	 */
	SAME, 
	/**
	 * Matches None element in the list comparing two lists of objects
	 */
	NOT_SAME, 
	/**
	 * Matches that an object 'is that' of another object, see: {@link AssertionThatMatcherType}
	 */
	THAT, 
	/**
	 * Matches if the an object representative string starts with another object representative string
	 */
	STARTS_WITH, 
	/**
	 * Matches if the an object representative string starts with, ignoring the case, another object representative string
	 */
	STARTS_WITH_IGNORE_CASE, 
	/**
	 * Matches if the an object representative string ends with another object representative string
	 */
	ENDS_WITH, 
	/**
	 * Matches if the an object representative string ends with, ignoring the case, another object representative string
	 */
	ENDS_WITH_IGNORE_CASE,
	/**
	 * Matches if the an object representative string contains another object representative string
	 */
	CONTAINS,
	/**
	 * Matches if the an object representative string contains, ignoring the case, another object representative string
	 */
	CONTAINS_IGNORE_CASE
}
