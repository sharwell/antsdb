/*-------------------------------------------------------------------------------------------------
 _______ __   _ _______ _______ ______  ______
 |_____| | \  |    |    |______ |     \ |_____]
 |     | |  \_|    |    ______| |_____/ |_____]

 Copyright (c) 2016, antsdb.com and/or its affiliates. All rights reserved. *-xguo0<@

 This program is free software: you can redistribute it and/or modify it under the terms of the
 GNU GNU Lesser General Public License, version 3, as published by the Free Software Foundation.

 You should have received a copy of the GNU Affero General Public License along with this program.
 If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html>
-------------------------------------------------------------------------------------------------*/
package com.antsdb.saltedfish.sql;

import java.util.HashMap;
import java.util.Properties;

import com.antsdb.saltedfish.minke.Minke;
import com.antsdb.saltedfish.nosql.StorageEngine;
import com.antsdb.saltedfish.sql.vdm.Cursor;
import com.antsdb.saltedfish.sql.vdm.Parameters;
import com.antsdb.saltedfish.sql.vdm.VdmContext;
import com.antsdb.saltedfish.sql.vdm.View;
import com.antsdb.saltedfish.util.CursorUtil;

/**
 * 
 * @author *-xguo0<@
 */
public class SystemViewMinkeInfo extends View {
    Orca orca;

    public SystemViewMinkeInfo(Orca orca) {
        super(CursorUtil.toMeta(Properties.class));
        this.orca = orca;
    }

    @SuppressWarnings("resource")
    @Override
    public Object run(VdmContext ctx, Parameters params, long pMaster) {
        StorageEngine stor = this.orca.getHumpback().getStorageEngine();
        if (stor instanceof Minke) {
            Minke minke = (Minke)stor;
            Cursor c = CursorUtil.toCursor(meta, minke.getSummary());
            return c;
        }
        else {
            Cursor c = CursorUtil.toCursor(meta, new HashMap<String, Object>());
            return c;
        }
    }

}
