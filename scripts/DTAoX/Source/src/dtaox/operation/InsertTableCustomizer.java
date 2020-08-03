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

package dtaox.operation;

import java.awt.Component;
import java.awt.Frame;

import ro.sync.annotations.api.API;
import ro.sync.annotations.api.APIType;
import ro.sync.annotations.api.SourceType;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.commons.table.operations.SATableCustomizerDialog;
import ro.sync.ecss.extensions.commons.table.operations.TableCustomizer;
import ro.sync.ecss.extensions.commons.table.operations.TableInfo;

/**
 * Customize a TEI table. It is used on standalone implementation.
 */
@API(type=APIType.INTERNAL, src=SourceType.PUBLIC)
public class InsertTableCustomizer extends TableCustomizer {

  private static InsertTableCustomizer instance;

  public static InsertTableCustomizer getInstance(){
    if(instance == null){
      instance = new InsertTableCustomizer();
    }
    return instance;
  }

  InsertTableCustomizer() {}

  @Override
  protected TableInfo showCustomizeTableDialog(AuthorAccess authorAccess, int predefinedRowsCount,
      int predefinedColumnsCount, int defaultTableModel) {
    SATableCustomizerDialog tableCustomizerDialog = new InsertTableDialog(
        (Frame) authorAccess.getWorkspaceAccess().getParentFrame(), authorAccess.getAuthorResourceBundle(),
        predefinedRowsCount, predefinedColumnsCount);
    tableCustomizerDialog.setLocationRelativeTo((Component) authorAccess.getWorkspaceAccess().getParentFrame());
    TableInfo newTableInfo = tableCustomizerDialog.showDialog(tableInfo);

    return newTableInfo;
  }
}