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
 * Das DTA-oXygen-Framework DTAoX ist Freie Software: Sie k�nnen es unter den
 * Bedingungen der GNU Lesser General Public License, wie von der Free Software
 * Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder sp�teren
 * ver�ffentlichten Version, weiterverbreiten und/oder modifizieren.
 * DTAoX wird in der Hoffnung, dass es n�tzlich sein wird, aber OHNE JEDE
 * GEW�HRLEISTUNG, bereitgestellt; sogar ohne die implizite Gew�hrleistung der
 * MARKTF�HIGKEIT oder EIGNUNG F�R EINEN BESTIMMTEN ZWECK. Siehe die GNU Lesser
 * General Public License f�r weitere Details.
 *
 * Sie sollten eine Kopie der GNU Lesser General Public License zusammen mit
 * DTAoX erhalten haben (COPYING.LESSER.txt and COPYING.txt). Wenn nicht, siehe
 * <http://www.gnu.org/licenses/>.
 *
 * DTAoX integriert bzw. nutzt software von Drittanbietern, die ihrerseits
 * eigenen Lizenzen unterstehen. F�r Informationen zu diesen Lizenzen siehe
 * die mitgelieferte Datei Readme.txt.
*/

package dtaox.operation;

import ro.sync.annotations.api.API;
import ro.sync.annotations.api.APIType;
import ro.sync.annotations.api.SourceType;
import ro.sync.ecss.extensions.api.ArgumentDescriptor;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.AuthorOperation;
import ro.sync.ecss.extensions.api.AuthorOperationException;
import ro.sync.exml.workspace.api.PluginWorkspace;
import ro.sync.exml.workspace.api.PluginWorkspaceProvider;

@API(type=APIType.INTERNAL, src=SourceType.PUBLIC)
public class SwitchCursorHighlightingOperation implements AuthorOperation {

	private boolean highlightValue = true;

  public void doOperation(AuthorAccess authorAccess, ArgumentsMap args)
      throws IllegalArgumentException, AuthorOperationException {
	PluginWorkspace workspace = PluginWorkspaceProvider.getPluginWorkspace();
	if(Double.valueOf(workspace.getVersion())>=15) {
		if(highlightValue) {
			PluginWorkspaceProvider.getPluginWorkspace().setGlobalObjectProperty("author.show.caret.proximity.highlight", Boolean.FALSE);
			highlightValue = !highlightValue;
		}
		else {
			PluginWorkspaceProvider.getPluginWorkspace().setGlobalObjectProperty("author.show.caret.proximity.highlight", Boolean.TRUE);
			highlightValue = !highlightValue;
		}
	}
  }

  public ArgumentDescriptor[] getArguments() {
    return null;
  }

  public String getDescription() {
    return "Switches cursor highlighting on and off";
  }
}
