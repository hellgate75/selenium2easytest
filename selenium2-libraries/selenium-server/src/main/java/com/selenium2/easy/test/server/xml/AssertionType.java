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
 * @author Fabrizio Torelli
 *
 */
@XmlEnum
public enum AssertionType {
	EQUALS, NOT_EQUALS, TRUE, FALSE, ARRAY_EQUALS, NULL, 
	NOT_NULL, SAME, NOT_SAME, THAT, STARTS_WITH, STARTS_WITH_IGNORE_CASE, 
	ENDS_WITH, ENDS_WITH_IGNORE_CASE
}
