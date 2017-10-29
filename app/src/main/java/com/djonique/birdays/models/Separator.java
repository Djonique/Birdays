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

package com.djonique.birdays.models;

public class Separator implements Item {

    public static final int TYPE_JANUARY = 0;
    public static final int TYPE_FEBRUARY = 1;
    public static final int TYPE_MARCH = 2;
    public static final int TYPE_APRIL = 3;
    public static final int TYPE_MAY = 4;
    public static final int TYPE_JUNE = 5;
    public static final int TYPE_JULY = 6;
    public static final int TYPE_AUGUST = 7;
    public static final int TYPE_SEPTEMBER = 8;
    public static final int TYPE_OCTOBER = 9;
    public static final int TYPE_NOVEMBER = 10;
    public static final int TYPE_DECEMBER = 11;

    private int type;

    public Separator(int type) {
        this.type = type;
    }

    public boolean isPerson() {
        return false;
    }

    public int getType() {
        return type;
    }
}