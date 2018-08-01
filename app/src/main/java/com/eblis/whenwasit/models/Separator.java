/*
 * Copyright 2017 Evgeny Timofeev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eblis.whenwasit.models;

public class Separator implements Item {
    private int month;

    public Separator(int month) {
        this.month = month;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.SEPARATOR;
    }

    @Override
    public AnniversaryType getAnniversaryType() {
        return null;
    }

    @Override
    public boolean isSeparator() {
        return true;
    }
}