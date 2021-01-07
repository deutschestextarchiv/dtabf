/*
 * DTAoX version 1.0.1, 2013-07-10
 * Copyright 2013 Deutsches Textarchiv (DTA; http://www.deutschestextarchiv.de)
 ##########################################################################
 * The DTA-oXygen-Framework DTAoX is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * DTAoX is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DTAoX (COPYING.LESSER.txt and COPYING.txt). If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * DTAoX includes and makes use of software from third parties, which are
 * licensed seperately. For information about theses licenses see the file
 * Readme.txt, which is included in the DTAoX-package.
 ##########################################################################
 * Das DTA-oXygen-Framework DTAoX ist Freie Software: Sie können es unter den
 * Bedingungen der GNU Lesser General Public License, wie von der Free Software
 * Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder späteren
 * veröffentlichten Version, weiterverbreiten und/oder modifizieren.
 * DTAoX wird in der Hoffnung, dass es nützlich sein wird, aber OHNE JEDE
 * GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite Gewährleistung der
 * MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK. Siehe die GNU Lesser
 * General Public License für weitere Details.
 *
 * Sie sollten eine Kopie der GNU Lesser General Public License zusammen mit
 * DTAoX erhalten haben (COPYING.LESSER.txt and COPYING.txt). Wenn nicht, siehe
 * <http://www.gnu.org/licenses/>.
 *
 * DTAoX integriert bzw. nutzt software von Drittanbietern, die ihrerseits
 * eigenen Lizenzen unterstehen. Für Informationen zu diesen Lizenzen siehe
 * die mitgelieferte Datei Readme.txt.
*/

package dtaox;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ro.sync.contentcompletion.xml.CIAttribute;
import ro.sync.contentcompletion.xml.CIElement;
import ro.sync.contentcompletion.xml.CIValue;
import ro.sync.contentcompletion.xml.Context;
import ro.sync.contentcompletion.xml.SchemaManagerFilter;
import ro.sync.contentcompletion.xml.WhatAttributesCanGoHereContext;
import ro.sync.contentcompletion.xml.WhatElementsCanGoHereContext;
import ro.sync.contentcompletion.xml.WhatPossibleValuesHasAttributeContext;


public class DTAoXSchemaManagerFilter implements SchemaManagerFilter {

	private Set<String> authorExtensionActions = new HashSet<String>();	//list of possible author actions

	@Override
	public List<CIElement> filterElements(List<CIElement> elementList,
			WhatElementsCanGoHereContext context) {
		List<CIElement> newElementList = new LinkedList<CIElement>();

		for(String actionName	 : authorExtensionActions) {

			String[] tokenized = actionName.split(" ");

			//add all elements of the old element list to the new element list, that matches with an author action
	        for(CIElement element : elementList) {

	        	for(String token : tokenized) {
	        		if(token.equals(element.getQName())) {
	        			newElementList.add(element);
	        			break;
	        		}
	          	}
	        }
		}
        return newElementList;
	}

	//get all possible author actions
	public void setAuthorActionSet(Set<String> authorActionList) {
		authorExtensionActions = authorActionList;
	}




	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public List<CIValue> filterAttributeValues(List<CIValue> list,
			WhatPossibleValuesHasAttributeContext arg1) {
		return list;
	}

	@Override
	public List<CIAttribute> filterAttributes(List<CIAttribute> arg0,
			WhatAttributesCanGoHereContext arg1) {
		return arg0;
	}

	@Override
	public List<CIValue> filterElementValues(List<CIValue> list, Context arg1) {
		return list;
	}

}
