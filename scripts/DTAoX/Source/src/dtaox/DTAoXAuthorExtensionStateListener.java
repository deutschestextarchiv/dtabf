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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.text.BadLocationException;

import ro.sync.contentcompletion.xml.CIElement;
import ro.sync.contentcompletion.xml.WhatElementsCanGoHereContext;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.AuthorCaretEvent;
import ro.sync.ecss.extensions.api.AuthorCaretListener;
import ro.sync.ecss.extensions.api.AuthorDocumentController;
import ro.sync.ecss.extensions.api.AuthorExtensionStateListener;
import ro.sync.ecss.extensions.api.AuthorSchemaManager;


public class DTAoXAuthorExtensionStateListener implements
		AuthorExtensionStateListener {

	private Map<String, Object> authorExtensionActionMap = new HashMap<String, Object>();
	private boolean enabledStatesCanBeChanged = false;

	@Override
	public String getDescription() {
		return "activates/deactivates context sensitively action options in bar menu and context menu\n";
	}

    /**
     * @see ro.sync.ecss.extensions.api.AuthorExtensionStateListener#activated(ro.sync.ecss.extensions.api.AuthorAccess)
     */
	public void activated(final AuthorAccess authorAccess){

		final Map<String, Object> authorExtensionActions = authorAccess.getEditorAccess().getActionsProvider().getAuthorExtensionActions();
	    Iterator<Object> actionsIter = authorExtensionActions.values().iterator();
	    while(actionsIter.hasNext()) {
	      final AbstractAction action = (AbstractAction) actionsIter.next();
	      action.addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent evt) {
	          if("enabled".equals(evt.getPropertyName())){
	            if(!enabledStatesCanBeChanged) {
	              //Enabled states of actions cannot be changed now, probably the Oxygen code tries to
	              //enable or disable the action based on the XPath condition
	              //So set it back as it was before.
	              try {
	                enabledStatesCanBeChanged = true;
	                action.setEnabled((Boolean) evt.getOldValue());
	              } finally {
	                enabledStatesCanBeChanged = false;
	              }
	            }
	          }
	        }
	      });
	    }

		//get access to author actions
		authorExtensionActionMap = authorAccess.getEditorAccess().getActionsProvider().getAuthorExtensionActions();

		//Add a caret listener to enable/disable extension actions
		authorAccess.getEditorAccess().addAuthorCaretListener(new AuthorCaretListener() {


		  @Override
	      public void caretMoved(AuthorCaretEvent caretEvent) {
			  //get access to document and schema
		  	  AuthorDocumentController docController = authorAccess.getDocumentController();
		  	  AuthorSchemaManager schemaManager = docController.getAuthorSchemaManager();

	  		 //get current caret context
    		  int offset = authorAccess.getEditorAccess().getCaretOffset();
    		  try {
    			  //get list of possible elements at current caret position
    			  WhatElementsCanGoHereContext goHereContext = schemaManager.createWhatElementsCanGoHereContext(offset);
    			  if(goHereContext==null) return;
    			  List<CIElement> possibleChildren = schemaManager.whatElementsCanGoHere(goHereContext);
    			  if(possibleChildren==null) possibleChildren = new LinkedList<CIElement>();

	    		  //iterate over all possible author actions
actionFor: 	  	  for(Map.Entry<String, Object> authorExtensionAction : authorExtensionActionMap.entrySet()) {
			    	  String actionName = authorExtensionAction.getKey();
			    	  // author actions can be tagged to be ignored by context evaluation
		    		  if(actionName.contains("[nocontextvalidation]")) continue;
		    		  AbstractAction actionValue = (AbstractAction) authorExtensionAction.getValue();
		    		  //String actionDesc = (String) actionValue.getValue(Action.SHORT_DESCRIPTION);

		    		  try {
		                  enabledStatesCanBeChanged = true;
			    		  //at start unable every single action
			    		  actionValue.setEnabled(false);

			    		  String[] tokenized = actionName.split(" ");

		    			  //if list contains actual action enable action
		    			  for(CIElement children : possibleChildren) {
		    				  //reenable the action if the tag name appears as token in the action ID
		    				  for(String token : tokenized) {
		    					  if(token.equals(children.getQName())) {
			    					  actionValue.setEnabled(true);
			    					  continue actionFor;
		    					  }
		    				  }
		    			  }
		    		  }
		    		  finally {
		                  enabledStatesCanBeChanged = false;
		    		  }
	    		  }
	    	  } catch (BadLocationException e) {
	    		  System.err.println("Exception in CaretListener.caretMoved - Extension: bad location");
	    		  e.printStackTrace();
	    	  }
	      }
	    });
	}

	@Override
	public void deactivated(AuthorAccess authorAccess) {
	}

	public Set<String> getAuthorActionSet() {
		return(authorExtensionActionMap.keySet());
	}
}