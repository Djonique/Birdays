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

package com.djonique.birdays.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Person;
import com.djonique.birdays.utils.Utils;

import org.joda.time.LocalDate;

import static com.djonique.birdays.database.DbHelper.COLUMN_DATE;
import static com.djonique.birdays.database.DbHelper.COLUMN_NAME;
import static com.djonique.birdays.database.DbHelper.DB_FAMOUS;

class DbFamous {

    private static void addFamous(SQLiteDatabase db, Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        if (Utils.getTimeOffset() <= 0) {
            // 43200000 is twelve hour offset
            cv.put(COLUMN_DATE, person.getDate().toDateTimeAtCurrentTime().getMillis() + 43200000);
        } else {
            cv.put(COLUMN_DATE, person.getDate().toDateTimeAtCurrentTime().getMillis());
        }
        db.insert(DB_FAMOUS, null, cv);
    }

    static void createFamousDbPart1(Context context, SQLiteDatabase db) {
//January 1
        addFamous(db, new Person(context.getString(R.string.medici), new LocalDate(1449, 1, 1)));
        addFamous(db, new Person(context.getString(R.string.giordano_bruno), new LocalDate(1548, 1, 1)));
        addFamous(db, new Person(context.getString(R.string.frazer), new LocalDate(1854, 1, 1)));
        addFamous(db, new Person(context.getString(R.string.coubertin), new LocalDate(1863, 1, 1)));
        addFamous(db, new Person(context.getString(R.string.william_fox), new LocalDate(1879, 1, 1)));

//January 2
        addFamous(db, new Person(context.getString(R.string.piero_di_cosimo), new LocalDate(1462, 1, 2)));
        addFamous(db, new Person(context.getString(R.string.vasily_perov), new LocalDate(1834, 1, 2)));
        addFamous(db, new Person(context.getString(R.string.balakirev), new LocalDate(1837, 1, 2)));
        addFamous(db, new Person(context.getString(R.string.tippett), new LocalDate(1905, 1, 2)));
        addFamous(db, new Person(context.getString(R.string.isaac_asimov), new LocalDate(1920, 1, 2)));

//January 3
        addFamous(db, new Person(context.getString(R.string.louis_poinsot), new LocalDate(1777, 1, 3)));
        addFamous(db, new Person(context.getString(R.string.dabbadie), new LocalDate(1810, 1, 3)));
        addFamous(db, new Person(context.getString(R.string.fletcher), new LocalDate(1886, 1, 3)));
        addFamous(db, new Person(context.getString(R.string.tolkien), new LocalDate(1892, 1, 3)));
        addFamous(db, new Person(context.getString(R.string.moore), new LocalDate(1929, 1, 3)));
        addFamous(db, new Person(context.getString(R.string.mel_gibson), new LocalDate(1956, 1, 3)));
        addFamous(db, new Person(context.getString(R.string.schumacher), new LocalDate(1969, 1, 3)));

//January 4
        addFamous(db, new Person(context.getString(R.string.isaac_newton), new LocalDate(1643, 1, 4)));
        addFamous(db, new Person(context.getString(R.string.pergolesi), new LocalDate(1710, 1, 4)));
        addFamous(db, new Person(context.getString(R.string.jacob_grimm), new LocalDate(1785, 1, 4)));
        addFamous(db, new Person(context.getString(R.string.braille), new LocalDate(1809, 1, 4)));
        addFamous(db, new Person(context.getString(R.string.tsereteli), new LocalDate(1934, 1, 4)));
        addFamous(db, new Person(context.getString(R.string.josephson), new LocalDate(1940, 1, 4)));

//January 5
        addFamous(db, new Person(context.getString(R.string.eucken), new LocalDate(1846, 1, 5)));
        addFamous(db, new Person(context.getString(R.string.gillette), new LocalDate(1855, 1, 5)));
        addFamous(db, new Person(context.getString(R.string.erlanger), new LocalDate(1874, 1, 5)));
        addFamous(db, new Person(context.getString(R.string.umberto_eco), new LocalDate(1932, 1, 5)));
        addFamous(db, new Person(context.getString(R.string.manson), new LocalDate(1969, 1, 5)));
        addFamous(db, new Person(context.getString(R.string.bradley_cooper), new LocalDate(1975, 1, 5)));

//January 6
        addFamous(db, new Person(context.getString(R.string.darc), new LocalDate(1412, 1, 6)));
        addFamous(db, new Person(context.getString(R.string.jakob_bernoulli), new LocalDate(1655, 1, 6)));
        addFamous(db, new Person(context.getString(R.string.montgolfier), new LocalDate(1745, 1, 6)));
        addFamous(db, new Person(context.getString(R.string.schliemann), new LocalDate(1822, 1, 6)));
        addFamous(db, new Person(context.getString(R.string.scriabin), new LocalDate(1871, 1, 6)));
        addFamous(db, new Person(context.getString(R.string.celentano), new LocalDate(1938, 1, 6)));
        addFamous(db, new Person(context.getString(R.string.atkinson), new LocalDate(1955, 1, 6)));
        addFamous(db, new Person(context.getString(R.string.redmayne), new LocalDate(1982, 1, 6)));

//January 7
        addFamous(db, new Person(context.getString(R.string.pope_gregory), new LocalDate(1502, 1, 7)));
        addFamous(db, new Person(context.getString(R.string.fleming), new LocalDate(1827, 1, 7)));
        addFamous(db, new Person(context.getString(R.string.eliezer), new LocalDate(1858, 1, 7)));
        addFamous(db, new Person(context.getString(R.string.borel), new LocalDate(1871, 1, 7)));
        addFamous(db, new Person(context.getString(R.string.nicolas_cage), new LocalDate(1964, 1, 7)));

//January 8
        addFamous(db, new Person(context.getString(R.string.sirani), new LocalDate(1638, 1, 8)));
        addFamous(db, new Person(context.getString(R.string.nijinska), new LocalDate(1891, 1, 8)));
        addFamous(db, new Person(context.getString(R.string.presley), new LocalDate(1935, 1, 8)));
        addFamous(db, new Person(context.getString(R.string.hawking), new LocalDate(1942, 1, 8)));
        addFamous(db, new Person(context.getString(R.string.daviw_bowie), new LocalDate(1947, 1, 8)));

//January 9
        addFamous(db, new Person(context.getString(R.string.simon_vouet), new LocalDate(1590, 1, 9)));
        addFamous(db, new Person(context.getString(R.string.wrangel), new LocalDate(1797, 1, 9)));
        addFamous(db, new Person(context.getString(R.string.watson), new LocalDate(1878, 1, 9)));
        addFamous(db, new Person(context.getString(R.string.capek), new LocalDate(1890, 1, 9)));
        addFamous(db, new Person(context.getString(R.string.beauvoir), new LocalDate(1908, 1, 9)));

//January 10
        addFamous(db, new Person(context.getString(R.string.birkbeck), new LocalDate(1776, 1, 10)));
        addFamous(db, new Person(context.getString(R.string.tolstoy), new LocalDate(1883, 1, 10)));
        addFamous(db, new Person(context.getString(R.string.bertoni), new LocalDate(1903, 1, 10)));
        addFamous(db, new Person(context.getString(R.string.wilson), new LocalDate(1936, 1, 10)));
        addFamous(db, new Person(context.getString(R.string.knuth), new LocalDate(1938, 1, 10)));

//January 11
        addFamous(db, new Person(context.getString(R.string.parmigianino), new LocalDate(1503, 1, 11)));
        addFamous(db, new Person(context.getString(R.string.guidobaldo), new LocalDate(1545, 1, 11)));
        addFamous(db, new Person(context.getString(R.string.stensen), new LocalDate(1638, 1, 11)));
        addFamous(db, new Person(context.getString(R.string.hofmann), new LocalDate(1906, 1, 11)));
        addFamous(db, new Person(context.getString(R.string.mendoza), new LocalDate(1943, 1, 11)));

//January 12
        addFamous(db, new Person(context.getString(R.string.helmont), new LocalDate(1580, 1, 12)));
        addFamous(db, new Person(context.getString(R.string.perrault), new LocalDate(1628, 1, 12)));
        addFamous(db, new Person(context.getString(R.string.jack_london), new LocalDate(1876, 1, 12)));
        addFamous(db, new Person(context.getString(R.string.kurchatov), new LocalDate(1903, 1, 12)));
        addFamous(db, new Person(context.getString(R.string.korolev), new LocalDate(1907, 1, 12)));
        addFamous(db, new Person(context.getString(R.string.maharishi), new LocalDate(1917, 1, 12)));
        addFamous(db, new Person(context.getString(R.string.murakami), new LocalDate(1949, 1, 12)));

//January 13
        addFamous(db, new Person(context.getString(R.string.beketov), new LocalDate(1827, 1, 13)));
        addFamous(db, new Person(context.getString(R.string.wien), new LocalDate(1864, 1, 13)));
        addFamous(db, new Person(context.getString(R.string.soutine), new LocalDate(1893, 1, 13)));
        addFamous(db, new Person(context.getString(R.string.lifshitz), new LocalDate(1917, 1, 13)));
        addFamous(db, new Person(context.getString(R.string.feyerabend), new LocalDate(1924, 1, 13)));

//January 14
        addFamous(db, new Person(context.getString(R.string.semyonov), new LocalDate(1827, 1, 14)));
        addFamous(db, new Person(context.getString(R.string.morisot), new LocalDate(1841, 1, 14)));
        addFamous(db, new Person(context.getString(R.string.schweitzer), new LocalDate(1875, 1, 14)));
        addFamous(db, new Person(context.getString(R.string.mishima), new LocalDate(1925, 1, 14)));
        addFamous(db, new Person(context.getString(R.string.kharlamov), new LocalDate(1948, 1, 14)));

//January 15
        addFamous(db, new Person(context.getString(R.string.moliere), new LocalDate(1622, 1, 15)));
        addFamous(db, new Person(context.getString(R.string.griboyedov), new LocalDate(1795, 1, 15)));
        addFamous(db, new Person(context.getString(R.string.virtanen), new LocalDate(1895, 1, 15)));
        addFamous(db, new Person(context.getString(R.string.teller), new LocalDate(1908, 1, 15)));
        addFamous(db, new Person(context.getString(R.string.luther_king), new LocalDate(1929, 1, 15)));

//January 16
        addFamous(db, new Person(context.getString(R.string.schoner), new LocalDate(1477, 1, 16)));
        addFamous(db, new Person(context.getString(R.string.piccinni), new LocalDate(1728, 1, 16)));
        addFamous(db, new Person(context.getString(R.string.alfieri), new LocalDate(1749, 1, 16)));
        addFamous(db, new Person(context.getString(R.string.veresaev), new LocalDate(1867, 1, 16)));
        addFamous(db, new Person(context.getString(R.string.roy_jones), new LocalDate(1969, 1, 16)));

//January 17
        addFamous(db, new Person(context.getString(R.string.franklin), new LocalDate(1706, 1, 17)));
        addFamous(db, new Person(context.getString(R.string.zhukovsky), new LocalDate(1847, 1, 17)));
        addFamous(db, new Person(context.getString(R.string.stanislavski), new LocalDate(1863, 1, 17)));
        addFamous(db, new Person(context.getString(R.string.al_capone), new LocalDate(1899, 1, 17)));
        addFamous(db, new Person(context.getString(R.string.muhammad_ali), new LocalDate(1942, 1, 17)));
        addFamous(db, new Person(context.getString(R.string.jim_carrey), new LocalDate(1962, 1, 17)));

//January 18
        addFamous(db, new Person(context.getString(R.string.montesquieu), new LocalDate(1689, 1, 18)));
        addFamous(db, new Person(context.getString(R.string.cesar_cui), new LocalDate(1835, 1, 18)));
        addFamous(db, new Person(context.getString(R.string.ehrenfest), new LocalDate(1880, 1, 18)));
        addFamous(db, new Person(context.getString(R.string.milne), new LocalDate(1882, 1, 18)));
        addFamous(db, new Person(context.getString(R.string.kitano), new LocalDate(1947, 1, 18)));
        addFamous(db, new Person(context.getString(R.string.costner), new LocalDate(1955, 1, 18)));
        addFamous(db, new Person(context.getString(R.string.guardiola), new LocalDate(1971, 1, 18)));

//January 19
        addFamous(db, new Person(context.getString(R.string.cagnacci), new LocalDate(1601, 1, 19)));
        addFamous(db, new Person(context.getString(R.string.comte), new LocalDate(1798, 1, 19)));
        addFamous(db, new Person(context.getString(R.string.edgar_poe), new LocalDate(1809, 1, 19)));
        addFamous(db, new Person(context.getString(R.string.kapteyn), new LocalDate(1851, 1, 19)));
        addFamous(db, new Person(context.getString(R.string.serov), new LocalDate(1865, 1, 19)));
        addFamous(db, new Person(context.getString(R.string.kantorovich), new LocalDate(1912, 1, 19)));

//January 20
        addFamous(db, new Person(context.getString(R.string.gessi), new LocalDate(1588, 1, 20)));
        addFamous(db, new Person(context.getString(R.string.ampere), new LocalDate(1775, 1, 20)));
        addFamous(db, new Person(context.getString(R.string.chausson), new LocalDate(1855, 1, 20)));
        addFamous(db, new Person(context.getString(R.string.onassis), new LocalDate(1906, 1, 20)));
        addFamous(db, new Person(context.getString(R.string.fellini), new LocalDate(1920, 1, 20)));

//January 21
        addFamous(db, new Person(context.getString(R.string.browning), new LocalDate(1855, 1, 21)));
        addFamous(db, new Person(context.getString(R.string.florensky), new LocalDate(1882, 1, 21)));
        addFamous(db, new Person(context.getString(R.string.dior), new LocalDate(1905, 1, 21)));
        addFamous(db, new Person(context.getString(R.string.benny_hill), new LocalDate(1924, 1, 21)));
        addFamous(db, new Person(context.getString(R.string.domingo), new LocalDate(1941, 1, 21)));

//January 22
        addFamous(db, new Person(context.getString(R.string.bacon), new LocalDate(1561, 1, 22)));
        addFamous(db, new Person(context.getString(R.string.byron), new LocalDate(1788, 1, 22)));
        addFamous(db, new Person(context.getString(R.string.scoville), new LocalDate(1865, 1, 22)));
        addFamous(db, new Person(context.getString(R.string.picabia), new LocalDate(1879, 1, 22)));
        addFamous(db, new Person(context.getString(R.string.landau), new LocalDate(1908, 1, 22)));

//January 23
        addFamous(db, new Person(context.getString(R.string.stendhal), new LocalDate(1783, 1, 23)));
        addFamous(db, new Person(context.getString(R.string.manet), new LocalDate(1832, 1, 23)));
        addFamous(db, new Person(context.getString(R.string.abbe), new LocalDate(1840, 1, 23)));
        addFamous(db, new Person(context.getString(R.string.hilbert), new LocalDate(1862, 1, 23)));
        addFamous(db, new Person(context.getString(R.string.yukawa), new LocalDate(1907, 1, 23)));
        addFamous(db, new Person(context.getString(R.string.hauer), new LocalDate(1944, 1, 23)));

//January 24
        addFamous(db, new Person(context.getString(R.string.congreve), new LocalDate(1670, 1, 24)));
        addFamous(db, new Person(context.getString(R.string.beaumarchais), new LocalDate(1732, 1, 24)));
        addFamous(db, new Person(context.getString(R.string.hoffmann), new LocalDate(1776, 1, 24)));
        addFamous(db, new Person(context.getString(R.string.surikov), new LocalDate(1848, 1, 24)));
        addFamous(db, new Person(context.getString(R.string.shechtman), new LocalDate(1941, 1, 24)));
        addFamous(db, new Person(context.getString(R.string.kinski), new LocalDate(1961, 1, 24)));

//January 25
        addFamous(db, new Person(context.getString(R.string.lagrange), new LocalDate(1736, 1, 25)));
        addFamous(db, new Person(context.getString(R.string.burns), new LocalDate(1759, 1, 25)));
        addFamous(db, new Person(context.getString(R.string.shishkin), new LocalDate(1832, 1, 25)));
        addFamous(db, new Person(context.getString(R.string.maugham), new LocalDate(1874, 1, 25)));
        addFamous(db, new Person(context.getString(R.string.woolf), new LocalDate(1882, 1, 25)));
        addFamous(db, new Person(context.getString(R.string.prigogine), new LocalDate(1917, 1, 25)));
        addFamous(db, new Person(context.getString(R.string.eusebio), new LocalDate(1942, 1, 25)));

//January 26
        addFamous(db, new Person(context.getString(R.string.morita), new LocalDate(1921, 1, 26)));
        addFamous(db, new Person(context.getString(R.string.newman), new LocalDate(1925, 1, 26)));
        addFamous(db, new Person(context.getString(R.string.davis), new LocalDate(1944, 1, 26)));
        addFamous(db, new Person(context.getString(R.string.gretzky), new LocalDate(1961, 1, 26)));
        addFamous(db, new Person(context.getString(R.string.mourinho), new LocalDate(1963, 1, 26)));

//January 27
        addFamous(db, new Person(context.getString(R.string.neumann), new LocalDate(1687, 1, 27)));
        addFamous(db, new Person(context.getString(R.string.mozart), new LocalDate(1756, 1, 27)));
        addFamous(db, new Person(context.getString(R.string.schelling), new LocalDate(1775, 1, 27)));
        addFamous(db, new Person(context.getString(R.string.saltykov_shchedrin), new LocalDate(1826, 1, 27)));
        addFamous(db, new Person(context.getString(R.string.carroll), new LocalDate(1832, 1, 27)));
        addFamous(db, new Person(context.getString(R.string.bjorndalen), new LocalDate(1974, 1, 27)));

//January 28
        addFamous(db, new Person(context.getString(R.string.borelli), new LocalDate(1608, 1, 28)));
        addFamous(db, new Person(context.getString(R.string.heweliusz), new LocalDate(1611, 1, 28)));
        addFamous(db, new Person(context.getString(R.string.baskerville), new LocalDate(1706, 1, 28)));
        addFamous(db, new Person(context.getString(R.string.rubinstein), new LocalDate(1887, 1, 28)));
        addFamous(db, new Person(context.getString(R.string.buffon), new LocalDate(1978, 1, 28)));

//January 29
        addFamous(db, new Person(context.getString(R.string.swedenborg), new LocalDate(1688, 1, 29)));
        addFamous(db, new Person(context.getString(R.string.mohs), new LocalDate(1773, 1, 29)));
        addFamous(db, new Person(context.getString(R.string.auber), new LocalDate(1782, 1, 29)));
        addFamous(db, new Person(context.getString(R.string.shibasaburo), new LocalDate(1853, 1, 29)));
        addFamous(db, new Person(context.getString(R.string.chekhov), new LocalDate(1860, 1, 29)));
        addFamous(db, new Person(context.getString(R.string.rolland), new LocalDate(1886, 1, 29)));

//January 30
        addFamous(db, new Person(context.getString(R.string.watt), new LocalDate(1736, 1, 30)));
        addFamous(db, new Person(context.getString(R.string.chamisso), new LocalDate(1781, 1, 30)));
        addFamous(db, new Person(context.getString(R.string.kotelnikov), new LocalDate(1872, 1, 30)));
        addFamous(db, new Person(context.getString(R.string.gaidai), new LocalDate(1923, 1, 30)));
        addFamous(db, new Person(context.getString(R.string.engelbart), new LocalDate(1925, 1, 30)));
        addFamous(db, new Person(context.getString(R.string.brown), new LocalDate(1928, 1, 30)));

//January 31
        addFamous(db, new Person(context.getString(R.string.schubert), new LocalDate(1797, 1, 31)));
        addFamous(db, new Person(context.getString(R.string.richards), new LocalDate(1868, 1, 31)));
        addFamous(db, new Person(context.getString(R.string.langmuir), new LocalDate(1881, 1, 31)));
        addFamous(db, new Person(context.getString(R.string.vanga), new LocalDate(1911, 1, 31)));
        addFamous(db, new Person(context.getString(R.string.timberlake), new LocalDate(1981, 1, 31)));

//February 1
        addFamous(db, new Person(context.getString(R.string.bekhterev), new LocalDate(1857, 2, 1)));
        addFamous(db, new Person(context.getString(R.string.john_ford), new LocalDate(1894, 2, 1)));
        addFamous(db, new Person(context.getString(R.string.gable), new LocalDate(1901, 2, 1)));
        addFamous(db, new Person(context.getString(R.string.segre), new LocalDate(1905, 2, 1)));
        addFamous(db, new Person(context.getString(R.string.brandon_lee), new LocalDate(1965, 2, 1)));

//February 2
        addFamous(db, new Person(context.getString(R.string.bourdon), new LocalDate(1616, 2, 2)));
        addFamous(db, new Person(context.getString(R.string.boussingault), new LocalDate(1802, 2, 2)));
        addFamous(db, new Person(context.getString(R.string.forel), new LocalDate(1841, 2, 2)));
        addFamous(db, new Person(context.getString(R.string.chkalov), new LocalDate(1904, 2, 2)));
        addFamous(db, new Person(context.getString(R.string.haasse), new LocalDate(1918, 2, 2)));
        addFamous(db, new Person(context.getString(R.string.holland), new LocalDate(1929, 2, 2)));

//February 3
        addFamous(db, new Person(context.getString(R.string.mendelssohn), new LocalDate(1809, 2, 3)));
        addFamous(db, new Person(context.getString(R.string.trubner), new LocalDate(1851, 2, 3)));
        addFamous(db, new Person(context.getString(R.string.fomin), new LocalDate(1872, 2, 3)));
        addFamous(db, new Person(context.getString(R.string.stein), new LocalDate(1874, 2, 3)));
        addFamous(db, new Person(context.getString(R.string.joachim_low), new LocalDate(1960, 2, 3)));

//February 4
        addFamous(db, new Person(context.getString(R.string.bottger), new LocalDate(1682, 2, 4)));
        addFamous(db, new Person(context.getString(R.string.nemcova), new LocalDate(1820, 2, 4)));
        addFamous(db, new Person(context.getString(R.string.prandtl), new LocalDate(1875, 2, 4)));
        addFamous(db, new Person(context.getString(R.string.maillard), new LocalDate(1878, 2, 4)));
        addFamous(db, new Person(context.getString(R.string.tombaugh), new LocalDate(1906, 2, 4)));

//February 5
        addFamous(db, new Person(context.getString(R.string.runeberg), new LocalDate(1804, 2, 5)));
        addFamous(db, new Person(context.getString(R.string.maxim), new LocalDate(1840, 2, 5)));
        addFamous(db, new Person(context.getString(R.string.dunlop), new LocalDate(1840, 2, 5)));
        addFamous(db, new Person(context.getString(R.string.teike), new LocalDate(1864, 2, 5)));
        addFamous(db, new Person(context.getString(R.string.citroen), new LocalDate(1878, 2, 5)));
        addFamous(db, new Person(context.getString(R.string.voisin), new LocalDate(1880, 2, 5)));
        addFamous(db, new Person(context.getString(R.string.cristiano_ronaldo), new LocalDate(1985, 2, 5)));
        addFamous(db, new Person(context.getString(R.string.neymar), new LocalDate(1992, 2, 5)));

//February 6
        addFamous(db, new Person(context.getString(R.string.heinecken), new LocalDate(1721, 2, 6)));
        addFamous(db, new Person(context.getString(R.string.zelinsky), new LocalDate(1861, 2, 6)));
        addFamous(db, new Person(context.getString(R.string.bragg), new LocalDate(1895, 2, 6)));
        addFamous(db, new Person(context.getString(R.string.truffaut), new LocalDate(1932, 2, 6)));
        addFamous(db, new Person(context.getString(R.string.bob_marley), new LocalDate(1945, 2, 6)));

//February 7
        addFamous(db, new Person(context.getString(R.string.dickens), new LocalDate(1812, 2, 7)));
        addFamous(db, new Person(context.getString(R.string.alfred_adler), new LocalDate(1870, 2, 7)));
        addFamous(db, new Person(context.getString(R.string.sinclair_lewis), new LocalDate(1885, 2, 7)));
        addFamous(db, new Person(context.getString(R.string.chizhevsky), new LocalDate(1897, 2, 7)));
        addFamous(db, new Person(context.getString(R.string.euler), new LocalDate(1905, 2, 7)));
        addFamous(db, new Person(context.getString(R.string.desmond_doss), new LocalDate(1919, 2, 7)));
        addFamous(db, new Person(context.getString(R.string.kutcher), new LocalDate(1978, 2, 7)));

//February 8
        addFamous(db, new Person(context.getString(R.string.bernoulli), new LocalDate(1700, 2, 8)));
        addFamous(db, new Person(context.getString(R.string.courtois), new LocalDate(1777, 2, 8)));
        addFamous(db, new Person(context.getString(R.string.jules_verne), new LocalDate(1828, 2, 8)));
        addFamous(db, new Person(context.getString(R.string.mendeleev), new LocalDate(1828, 2, 8)));
        addFamous(db, new Person(context.getString(R.string.carlson), new LocalDate(1906, 2, 8)));
        addFamous(db, new Person(context.getString(R.string.williams), new LocalDate(1932, 2, 8)));

//February 9
        addFamous(db, new Person(context.getString(R.string.navai), new LocalDate(1441, 2, 9)));
        addFamous(db, new Person(context.getString(R.string.valisy_zhukovsky), new LocalDate(1783, 2, 9)));
        addFamous(db, new Person(context.getString(R.string.maybach), new LocalDate(1846, 2, 9)));
        addFamous(db, new Person(context.getString(R.string.soseki), new LocalDate(1867, 2, 9)));
        addFamous(db, new Person(context.getString(R.string.berg), new LocalDate(1885, 2, 9)));
        addFamous(db, new Person(context.getString(R.string.valier), new LocalDate(1895, 2, 9)));
        addFamous(db, new Person(context.getString(R.string.monod), new LocalDate(1910, 2, 9)));

//February 10
        addFamous(db, new Person(context.getString(R.string.molter), new LocalDate(1696, 2, 10)));
        addFamous(db, new Person(context.getString(R.string.lamb), new LocalDate(1775, 2, 10)));
        addFamous(db, new Person(context.getString(R.string.navier), new LocalDate(1785, 2, 10)));
        addFamous(db, new Person(context.getString(R.string.pasternak), new LocalDate(1890, 2, 10)));
        addFamous(db, new Person(context.getString(R.string.brecht), new LocalDate(1898, 2, 10)));

//February 11
        addFamous(db, new Person(context.getString(R.string.talbot), new LocalDate(1800, 2, 11)));
        addFamous(db, new Person(context.getString(R.string.edison), new LocalDate(1847, 2, 11)));
        addFamous(db, new Person(context.getString(R.string.henry), new LocalDate(1887, 2, 11)));
        addFamous(db, new Person(context.getString(R.string.sheldon), new LocalDate(1917, 2, 11)));
        addFamous(db, new Person(context.getString(R.string.nielsen), new LocalDate(1926, 2, 11)));
        addFamous(db, new Person(context.getString(R.string.aniston), new LocalDate(1969, 2, 11)));

//February 12
        addFamous(db, new Person(context.getString(R.string.gottsched), new LocalDate(1700, 2, 12)));
        addFamous(db, new Person(context.getString(R.string.darwin), new LocalDate(1809, 2, 12)));
        addFamous(db, new Person(context.getString(R.string.lincoln), new LocalDate(1809, 2, 12)));
        addFamous(db, new Person(context.getString(R.string.roerich), new LocalDate(1879, 2, 12)));
        addFamous(db, new Person(context.getString(R.string.anna_pavlova), new LocalDate(1881, 2, 12)));
        addFamous(db, new Person(context.getString(R.string.byung_chul), new LocalDate(1910, 2, 12)));

//February 13
        addFamous(db, new Person(context.getString(R.string.malthus), new LocalDate(1766, 2, 13)));
        addFamous(db, new Person(context.getString(R.string.krylov), new LocalDate(1769, 2, 13)));
        addFamous(db, new Person(context.getString(R.string.chaliapin), new LocalDate(1873, 2, 13)));
        addFamous(db, new Person(context.getString(R.string.shockley), new LocalDate(1910, 2, 13)));
        addFamous(db, new Person(context.getString(R.string.collina), new LocalDate(1960, 2, 13)));
        addFamous(db, new Person(context.getString(R.string.robbie_williams), new LocalDate(1974, 2, 13)));

//February 14
        addFamous(db, new Person(context.getString(R.string.alberti), new LocalDate(1404, 2, 14)));
        addFamous(db, new Person(context.getString(R.string.babur), new LocalDate(1483, 2, 14)));
        addFamous(db, new Person(context.getString(R.string.ferris), new LocalDate(1859, 2, 14)));
        addFamous(db, new Person(context.getString(R.string.germi), new LocalDate(1914, 2, 14)));
        addFamous(db, new Person(context.getString(R.string.sergey_kapitsa), new LocalDate(1928, 2, 14)));
        addFamous(db, new Person(context.getString(R.string.alan_parker), new LocalDate(1944, 2, 14)));

//February 15
        addFamous(db, new Person(context.getString(R.string.galilei), new LocalDate(1564, 2, 15)));
        addFamous(db, new Person(context.getString(R.string.praetorius), new LocalDate(1571, 2, 15)));
        addFamous(db, new Person(context.getString(R.string.bentham), new LocalDate(1748, 2, 15)));
        addFamous(db, new Person(context.getString(R.string.stoney), new LocalDate(1826, 2, 15)));
        addFamous(db, new Person(context.getString(R.string.guillaume), new LocalDate(1861, 2, 15)));
        addFamous(db, new Person(context.getString(R.string.whitehead), new LocalDate(1861, 2, 15)));
        addFamous(db, new Person(context.getString(R.string.barrymore), new LocalDate(1882, 2, 15)));

//February 16
        addFamous(db, new Person(context.getString(R.string.bouguer), new LocalDate(1698, 2, 16)));
        addFamous(db, new Person(context.getString(R.string.bodoni), new LocalDate(1740, 2, 16)));
        addFamous(db, new Person(context.getString(R.string.galton), new LocalDate(1822, 2, 16)));
        addFamous(db, new Person(context.getString(R.string.haeckel), new LocalDate(1834, 2, 16)));
        addFamous(db, new Person(context.getString(R.string.rossi), new LocalDate(1979, 2, 16)));

//February 17
        addFamous(db, new Person(context.getString(R.string.laennec), new LocalDate(1781, 2, 17)));
        addFamous(db, new Person(context.getString(R.string.beilstein), new LocalDate(1838, 2, 17)));
        addFamous(db, new Person(context.getString(R.string.john_watson), new LocalDate(1874, 2, 17)));
        addFamous(db, new Person(context.getString(R.string.fisher), new LocalDate(1890, 2, 17)));
        addFamous(db, new Person(context.getString(R.string.michael_bay), new LocalDate(1965, 2, 17)));
        addFamous(db, new Person(context.getString(R.string.denise_richards), new LocalDate(1971, 2, 17)));

//February 18
        addFamous(db, new Person(context.getString(R.string.volta), new LocalDate(1745, 2, 18)));
        addFamous(db, new Person(context.getString(R.string.bates), new LocalDate(1825, 2, 18)));
        addFamous(db, new Person(context.getString(R.string.ernst_mach), new LocalDate(1838, 2, 18)));
        addFamous(db, new Person(context.getString(R.string.ferrari), new LocalDate(1898, 2, 18)));
        addFamous(db, new Person(context.getString(R.string.yoko_ono), new LocalDate(1933, 2, 18)));
        addFamous(db, new Person(context.getString(R.string.travolta), new LocalDate(1954, 2, 18)));

//February 19
        addFamous(db, new Person(context.getString(R.string.copernicus), new LocalDate(1473, 2, 19)));
        addFamous(db, new Person(context.getString(R.string.boccherini), new LocalDate(1743, 2, 19)));
        addFamous(db, new Person(context.getString(R.string.murchison), new LocalDate(1792, 2, 19)));
        addFamous(db, new Person(context.getString(R.string.ducommun), new LocalDate(1833, 2, 19)));
        addFamous(db, new Person(context.getString(R.string.arrhenius), new LocalDate(1859, 2, 19)));
        addFamous(db, new Person(context.getString(R.string.del_toro), new LocalDate(1967, 2, 19)));

//February 20
        addFamous(db, new Person(context.getString(R.string.reil), new LocalDate(1759, 2, 20)));
        addFamous(db, new Person(context.getString(R.string.boltzmann), new LocalDate(1844, 2, 20)));
        addFamous(db, new Person(context.getString(R.string.crawford), new LocalDate(1966, 2, 20)));
        addFamous(db, new Person(context.getString(R.string.cobain), new LocalDate(1967, 2, 20)));
        addFamous(db, new Person(context.getString(R.string.rihanna), new LocalDate(1988, 2, 20)));

//February 21
        addFamous(db, new Person(context.getString(R.string.delibes), new LocalDate(1836, 2, 21)));
        addFamous(db, new Person(context.getString(R.string.calment), new LocalDate(1875, 2, 21)));
        addFamous(db, new Person(context.getString(R.string.sullivan), new LocalDate(1892, 2, 21)));
        addFamous(db, new Person(context.getString(R.string.henrik_dam), new LocalDate(1895, 2, 21)));
        addFamous(db, new Person(context.getString(R.string.givenchy), new LocalDate(1927, 2, 21)));
        addFamous(db, new Person(context.getString(R.string.palahniuk), new LocalDate(1962, 2, 21)));

//February 22
        addFamous(db, new Person(context.getString(R.string.washington), new LocalDate(1732, 2, 22)));
        addFamous(db, new Person(context.getString(R.string.schopenhauer), new LocalDate(1788, 2, 22)));
        addFamous(db, new Person(context.getString(R.string.quetelet), new LocalDate(1796, 2, 22)));
        addFamous(db, new Person(context.getString(R.string.janssen), new LocalDate(1824, 2, 22)));
        addFamous(db, new Person(context.getString(R.string.hertz), new LocalDate(1857, 2, 22)));
        addFamous(db, new Person(context.getString(R.string.bronsted), new LocalDate(1879, 2, 22)));
        addFamous(db, new Person(context.getString(R.string.drew_barrymore), new LocalDate(1975, 2, 22)));

//February 23
        addFamous(db, new Person(context.getString(R.string.handel), new LocalDate(1685, 2, 23)));
        addFamous(db, new Person(context.getString(R.string.chambers), new LocalDate(1723, 2, 23)));
        addFamous(db, new Person(context.getString(R.string.rothschild), new LocalDate(1744, 2, 23)));
        addFamous(db, new Person(context.getString(R.string.malevich), new LocalDate(1879, 2, 23)));
        addFamous(db, new Person(context.getString(R.string.jaspers), new LocalDate(1883, 2, 23)));
        addFamous(db, new Person(context.getString(R.string.blunt), new LocalDate(1983, 2, 23)));

//February 24
        addFamous(db, new Person(context.getString(R.string.banks), new LocalDate(1743, 2, 24)));
        addFamous(db, new Person(context.getString(R.string.grimm), new LocalDate(1768, 2, 24)));
        addFamous(db, new Person(context.getString(R.string.borgman), new LocalDate(1849, 2, 24)));
        addFamous(db, new Person(context.getString(R.string.freda), new LocalDate(1909, 2, 24)));
        addFamous(db, new Person(context.getString(R.string.legrand), new LocalDate(1932, 2, 24)));
        addFamous(db, new Person(context.getString(R.string.steve_jobs), new LocalDate(1955, 2, 24)));

//February 25
        addFamous(db, new Person(context.getString(R.string.battuta), new LocalDate(1304, 2, 25)));
        addFamous(db, new Person(context.getString(R.string.goldoni), new LocalDate(1707, 2, 25)));
        addFamous(db, new Person(context.getString(R.string.renoir), new LocalDate(1841, 2, 25)));
        addFamous(db, new Person(context.getString(R.string.karl_may), new LocalDate(1842, 2, 25)));
        addFamous(db, new Person(context.getString(R.string.caruso), new LocalDate(1873, 2, 25)));
        addFamous(db, new Person(context.getString(R.string.burgess), new LocalDate(1917, 2, 25)));

//February 26
        addFamous(db, new Person(context.getString(R.string.marlowe), new LocalDate(1564, 2, 26)));
        addFamous(db, new Person(context.getString(R.string.arago), new LocalDate(1786, 2, 26)));
        addFamous(db, new Person(context.getString(R.string.hugo), new LocalDate(1802, 2, 26)));
        addFamous(db, new Person(context.getString(R.string.levi_strauss), new LocalDate(1829, 2, 26)));
        addFamous(db, new Person(context.getString(R.string.flammarion), new LocalDate(1842, 2, 26)));

//February 27
        addFamous(db, new Person(context.getString(R.string.constantine), new LocalDate(272, 2, 27)));
        addFamous(db, new Person(context.getString(R.string.longfellow), new LocalDate(1807, 2, 27)));
        addFamous(db, new Person(context.getString(R.string.ge), new LocalDate(1831, 2, 27)));
        addFamous(db, new Person(context.getString(R.string.best), new LocalDate(1899, 2, 27)));
        addFamous(db, new Person(context.getString(R.string.steinbeck), new LocalDate(1902, 2, 27)));
        addFamous(db, new Person(context.getString(R.string.taylor), new LocalDate(1932, 2, 27)));

//February 28
        addFamous(db, new Person(context.getString(R.string.montaigne), new LocalDate(1533, 2, 28)));
        addFamous(db, new Person(context.getString(R.string.reaumur), new LocalDate(1683, 2, 28)));
        addFamous(db, new Person(context.getString(R.string.renan), new LocalDate(1823, 2, 28)));
        addFamous(db, new Person(context.getString(R.string.pauling), new LocalDate(1901, 2, 28)));
        addFamous(db, new Person(context.getString(R.string.gehry), new LocalDate(1929, 2, 28)));
        addFamous(db, new Person(context.getString(R.string.cooper), new LocalDate(1930, 2, 28)));
        addFamous(db, new Person(context.getString(R.string.vodianova), new LocalDate(1982, 2, 28)));

//February 29
        addFamous(db, new Person(context.getString(R.string.klenze), new LocalDate(1784, 2, 29)));
        addFamous(db, new Person(context.getString(R.string.rossini), new LocalDate(1792, 2, 29)));
        addFamous(db, new Person(context.getString(R.string.john_holland), new LocalDate(1840, 2, 29)));
        addFamous(db, new Person(context.getString(R.string.рollerith), new LocalDate(1860, 2, 29)));
        addFamous(db, new Person(context.getString(R.string.papert), new LocalDate(1928, 2, 29)));

//March 1
        addFamous(db, new Person(context.getString(R.string.botticelli), new LocalDate(1445, 3, 1)));
        addFamous(db, new Person(context.getString(R.string.chopin), new LocalDate(1810, 3, 1)));
        addFamous(db, new Person(context.getString(R.string.akutagawa), new LocalDate(1892, 3, 1)));
        addFamous(db, new Person(context.getString(R.string.miller), new LocalDate(1904, 3, 1)));
        addFamous(db, new Person(context.getString(R.string.snyder), new LocalDate(1966, 3, 1)));
        addFamous(db, new Person(context.getString(R.string.bieber), new LocalDate(1994, 3, 1)));

//March 2
        addFamous(db, new Person(context.getString(R.string.dekker), new LocalDate(1820, 3, 2)));
        addFamous(db, new Person(context.getString(R.string.smetana), new LocalDate(1824, 3, 2)));
        addFamous(db, new Person(context.getString(R.string.irving), new LocalDate(1942, 3, 2)));
        addFamous(db, new Person(context.getString(R.string.bon_jovi), new LocalDate(1962, 3, 2)));
        addFamous(db, new Person(context.getString(R.string.craig), new LocalDate(1968, 3, 2)));

//March 3
        addFamous(db, new Person(context.getString(R.string.pullman), new LocalDate(1831, 3, 3)));
        addFamous(db, new Person(context.getString(R.string.cantor), new LocalDate(1845, 3, 3)));
        addFamous(db, new Person(context.getString(R.string.bell), new LocalDate(1847, 3, 3)));
        addFamous(db, new Person(context.getString(R.string.frisch), new LocalDate(1895, 3, 3)));
        addFamous(db, new Person(context.getString(R.string.kornberg), new LocalDate(1918, 3, 3)));

//March 4
        addFamous(db, new Person(context.getString(R.string.vivaldi), new LocalDate(1678, 3, 4)));
        addFamous(db, new Person(context.getString(R.string.raeburn), new LocalDate(1756, 3, 4)));
        addFamous(db, new Person(context.getString(R.string.gamow), new LocalDate(1904, 3, 4)));
        addFamous(db, new Person(context.getString(R.string.veksler), new LocalDate(1907, 3, 4)));
        addFamous(db, new Person(context.getString(R.string.mauriat), new LocalDate(1925, 3, 4)));

//March 5
        addFamous(db, new Person(context.getString(R.string.mercator), new LocalDate(1512, 3, 5)));
        addFamous(db, new Person(context.getString(R.string.tiepolo), new LocalDate(1696, 3, 5)));
        addFamous(db, new Person(context.getString(R.string.marey), new LocalDate(1830, 3, 5)));
        addFamous(db, new Person(context.getString(R.string.tarrasch), new LocalDate(1862, 3, 5)));
        addFamous(db, new Person(context.getString(R.string.ando), new LocalDate(1910, 3, 5)));
        addFamous(db, new Person(context.getString(R.string.tobin), new LocalDate(1918, 3, 5)));

//March 6
        addFamous(db, new Person(context.getString(R.string.michelangelo), new LocalDate(1475, 3, 6)));
        addFamous(db, new Person(context.getString(R.string.bergerac), new LocalDate(1619, 3, 6)));
        addFamous(db, new Person(context.getString(R.string.elizabeth_browning), new LocalDate(1806, 3, 6)));
        addFamous(db, new Person(context.getString(R.string.jerzy_lec), new LocalDate(1909, 3, 6)));
        addFamous(db, new Person(context.getString(R.string.marquez), new LocalDate(1927, 3, 6)));
        addFamous(db, new Person(context.getString(R.string.tereshkova), new LocalDate(1937, 3, 6)));
        addFamous(db, new Person(context.getString(R.string.shaquille), new LocalDate(1972, 3, 6)));

//March 7
        addFamous(db, new Person(context.getString(R.string.rob_roy), new LocalDate(1671, 3, 7)));
        addFamous(db, new Person(context.getString(R.string.niepce), new LocalDate(1765, 3, 7)));
        addFamous(db, new Person(context.getString(R.string.palmer), new LocalDate(1845, 3, 7)));
        addFamous(db, new Person(context.getString(R.string.montesquiou), new LocalDate(1855, 3, 7)));
        addFamous(db, new Person(context.getString(R.string.mondrian), new LocalDate(1872, 3, 7)));
        addFamous(db, new Person(context.getString(R.string.ravel), new LocalDate(1875, 3, 7)));
        addFamous(db, new Person(context.getString(R.string.kobo_abe), new LocalDate(1924, 3, 7)));

//March 8
        addFamous(db, new Person(context.getString(R.string.fothergill), new LocalDate(1712, 3, 8)));
        addFamous(db, new Person(context.getString(R.string.potocki), new LocalDate(1761, 3, 8)));
        addFamous(db, new Person(context.getString(R.string.ignacy), new LocalDate(1822, 3, 8)));
        addFamous(db, new Person(context.getString(R.string.thompson), new LocalDate(1848, 3, 8)));
        addFamous(db, new Person(context.getString(R.string.otto_hahn), new LocalDate(1879, 3, 8)));
        addFamous(db, new Person(context.getString(R.string.kendall), new LocalDate(1886, 3, 8)));
        addFamous(db, new Person(context.getString(R.string.aiken), new LocalDate(1900, 3, 8)));

//March 9
        addFamous(db, new Person(context.getString(R.string.vespucci), new LocalDate(1454, 3, 9)));
        addFamous(db, new Person(context.getString(R.string.shevchenko), new LocalDate(1814, 3, 9)));
        addFamous(db, new Person(context.getString(R.string.barragan), new LocalDate(1902, 3, 9)));
        addFamous(db, new Person(context.getString(R.string.kohn), new LocalDate(1923, 3, 9)));
        addFamous(db, new Person(context.getString(R.string.gagarin), new LocalDate(1934, 3, 9)));
        addFamous(db, new Person(context.getString(R.string.binoche), new LocalDate(1964, 3, 9)));

//March 10
        addFamous(db, new Person(context.getString(R.string.schlegel), new LocalDate(1772, 3, 10)));
        addFamous(db, new Person(context.getString(R.string.eichendorff), new LocalDate(1788, 3, 10)));
        addFamous(db, new Person(context.getString(R.string.blatter), new LocalDate(1936, 3, 10)));
        addFamous(db, new Person(context.getString(R.string.norris), new LocalDate(1940, 3, 10)));
        addFamous(db, new Person(context.getString(R.string.bin_laden), new LocalDate(1957, 3, 10)));
        addFamous(db, new Person(context.getString(R.string.stone), new LocalDate(1958, 3, 10)));

//March 11
        addFamous(db, new Person(context.getString(R.string.tasso), new LocalDate(1544, 3, 11)));
        addFamous(db, new Person(context.getString(R.string.verrier), new LocalDate(1811, 3, 11)));
        addFamous(db, new Person(context.getString(R.string.bertrand), new LocalDate(1822, 3, 11)));
        addFamous(db, new Person(context.getString(R.string.vannevar_bush), new LocalDate(1890, 3, 11)));
        addFamous(db, new Person(context.getString(R.string.bloembergen), new LocalDate(1920, 3, 11)));
        addFamous(db, new Person(context.getString(R.string.adams), new LocalDate(1952, 3, 11)));
        addFamous(db, new Person(context.getString(R.string.knoxville), new LocalDate(1971, 3, 11)));

//March 12
        addFamous(db, new Person(context.getString(R.string.notre), new LocalDate(1613, 3, 12)));
        addFamous(db, new Person(context.getString(R.string.berkeley), new LocalDate(1685, 3, 12)));
        addFamous(db, new Person(context.getString(R.string.bazhenov), new LocalDate(1737, 3, 12)));
        addFamous(db, new Person(context.getString(R.string.kirchhoff), new LocalDate(1824, 3, 12)));
        addFamous(db, new Person(context.getString(R.string.newcomb), new LocalDate(1835, 3, 12)));
        addFamous(db, new Person(context.getString(R.string.perkin), new LocalDate(1838, 3, 12)));
        addFamous(db, new Person(context.getString(R.string.vernadsky), new LocalDate(1863, 3, 12)));

//March 13
        addFamous(db, new Person(context.getString(R.string.bonnet), new LocalDate(1720, 3, 13)));
        addFamous(db, new Person(context.getString(R.string.lowell), new LocalDate(1855, 3, 13)));
        addFamous(db, new Person(context.getString(R.string.eliade), new LocalDate(1907, 3, 13)));
        addFamous(db, new Person(context.getString(R.string.hubbard), new LocalDate(1911, 3, 13)));
        addFamous(db, new Person(context.getString(R.string.scatman), new LocalDate(1942, 3, 13)));

//March 14
        addFamous(db, new Person(context.getString(R.string.telemann), new LocalDate(1681, 3, 14)));
        addFamous(db, new Person(context.getString(R.string.strauss), new LocalDate(1804, 3, 14)));
        addFamous(db, new Person(context.getString(R.string.banville), new LocalDate(1823, 3, 14)));
        addFamous(db, new Person(context.getString(R.string.schiaparelli), new LocalDate(1835, 3, 14)));
        addFamous(db, new Person(context.getString(R.string.ehrlich), new LocalDate(1854, 3, 14)));
        addFamous(db, new Person(context.getString(R.string.einstein), new LocalDate(1879, 3, 14)));
        addFamous(db, new Person(context.getString(R.string.caine), new LocalDate(1933, 3, 14)));

//March 15
        addFamous(db, new Person(context.getString(R.string.sylvius), new LocalDate(1614, 3, 15)));
        addFamous(db, new Person(context.getString(R.string.loschmidt), new LocalDate(1821, 3, 15)));
        addFamous(db, new Person(context.getString(R.string.heyse), new LocalDate(1830, 3, 15)));
        addFamous(db, new Person(context.getString(R.string.behring), new LocalDate(1854, 3, 15)));
        addFamous(db, new Person(context.getString(R.string.haffkine), new LocalDate(1860, 3, 15)));
        addFamous(db, new Person(context.getString(R.string.alferov), new LocalDate(1930, 3, 15)));

//March 16
        addFamous(db, new Person(context.getString(R.string.georg_ohm), new LocalDate(1789, 3, 16)));
        addFamous(db, new Person(context.getString(R.string.prudhomme), new LocalDate(1839, 3, 16)));
        addFamous(db, new Person(context.getString(R.string.beijerinck), new LocalDate(1851, 3, 16)));
        addFamous(db, new Person(context.getString(R.string.yayser), new LocalDate(1853, 3, 16)));
        addFamous(db, new Person(context.getString(R.string.damadian), new LocalDate(1936, 3, 16)));
        addFamous(db, new Person(context.getString(R.string.bertolucci), new LocalDate(1940, 3, 16)));
        addFamous(db, new Person(context.getString(R.string.stallman), new LocalDate(1953, 3, 16)));

//March 17
        addFamous(db, new Person(context.getString(R.string.daimler), new LocalDate(1834, 3, 17)));
        addFamous(db, new Person(context.getString(R.string.vrubel), new LocalDate(1856, 3, 17)));
        addFamous(db, new Person(context.getString(R.string.hess), new LocalDate(1881, 3, 17)));
        addFamous(db, new Person(context.getString(R.string.nureyev), new LocalDate(1938, 3, 17)));
        addFamous(db, new Person(context.getString(R.string.gibson), new LocalDate(1948, 3, 17)));
        addFamous(db, new Person(context.getString(R.string.russell), new LocalDate(1951, 3, 17)));

//March 18
        addFamous(db, new Person(context.getString(R.string.steiner), new LocalDate(1796, 3, 18)));
        addFamous(db, new Person(context.getString(R.string.hebbel), new LocalDate(1813, 3, 18)));
        addFamous(db, new Person(context.getString(R.string.diesel), new LocalDate(1858, 3, 18)));
        addFamous(db, new Person(context.getString(R.string.stekel), new LocalDate(1868, 3, 18)));
        addFamous(db, new Person(context.getString(R.string.koffka), new LocalDate(1886, 3, 18)));
        addFamous(db, new Person(context.getString(R.string.besson), new LocalDate(1959, 3, 18)));

//March 19
        addFamous(db, new Person(context.getString(R.string.burton), new LocalDate(1821, 3, 19)));
        addFamous(db, new Person(context.getString(R.string.wheeler), new LocalDate(1865, 3, 19)));
        addFamous(db, new Person(context.getString(R.string.reger), new LocalDate(1873, 3, 19)));
        addFamous(db, new Person(context.getString(R.string.haworth), new LocalDate(1883, 3, 19)));
        addFamous(db, new Person(context.getString(R.string.joliot_curie), new LocalDate(1900, 3, 19)));
        addFamous(db, new Person(context.getString(R.string.molina), new LocalDate(1943, 3, 19)));
        addFamous(db, new Person(context.getString(R.string.bruce_willis), new LocalDate(1955, 3, 19)));

//March 20
        addFamous(db, new Person(context.getString(R.string.ibsen), new LocalDate(1828, 3, 20)));
        addFamous(db, new Person(context.getString(R.string.gigli), new LocalDate(1890, 3, 20)));
        addFamous(db, new Person(context.getString(R.string.skinner), new LocalDate(1904, 3, 20)));
        addFamous(db, new Person(context.getString(R.string.cattell), new LocalDate(1905, 3, 20)));
        addFamous(db, new Person(context.getString(R.string.neher), new LocalDate(1944, 3, 20)));
        addFamous(db, new Person(context.getString(R.string.bennington), new LocalDate(1976, 3, 20)));

//March 21
        addFamous(db, new Person(context.getString(R.string.fourier), new LocalDate(1768, 3, 21)));
        addFamous(db, new Person(context.getString(R.string.mozhaysky), new LocalDate(1825, 3, 21)));
        addFamous(db, new Person(context.getString(R.string.gilbert), new LocalDate(1932, 3, 21)));
        addFamous(db, new Person(context.getString(R.string.oldman), new LocalDate(1958, 3, 21)));
        addFamous(db, new Person(context.getString(R.string.senna), new LocalDate(1960, 3, 21)));
        addFamous(db, new Person(context.getString(R.string.ronaldinho), new LocalDate(1980, 3, 21)));

//March 22
        addFamous(db, new Person(context.getString(R.string.van_dyck), new LocalDate(1599, 3, 22)));
        addFamous(db, new Person(context.getString(R.string.pelletier), new LocalDate(1788, 3, 22)));
        addFamous(db, new Person(context.getString(R.string.lysenko), new LocalDate(1842, 3, 22)));
        addFamous(db, new Person(context.getString(R.string.millikan), new LocalDate(1868, 3, 22)));
        addFamous(db, new Person(context.getString(R.string.richter), new LocalDate(1931, 3, 22)));
        addFamous(db, new Person(context.getString(R.string.webber), new LocalDate(1948, 3, 22)));

//March 23
        addFamous(db, new Person(context.getString(R.string.laplace), new LocalDate(1749, 3, 23)));
        addFamous(db, new Person(context.getString(R.string.du_gard), new LocalDate(1881, 3, 23)));
        addFamous(db, new Person(context.getString(R.string.noether), new LocalDate(1882, 3, 23)));
        addFamous(db, new Person(context.getString(R.string.juan_gris), new LocalDate(1887, 3, 23)));
        addFamous(db, new Person(context.getString(R.string.fromm), new LocalDate(1900, 3, 23)));
        addFamous(db, new Person(context.getString(R.string.kurosawa), new LocalDate(1910, 3, 23)));
        addFamous(db, new Person(context.getString(R.string.von_braun), new LocalDate(1912, 3, 23)));

//March 24
        addFamous(db, new Person(context.getString(R.string.agricola), new LocalDate(1494, 3, 24)));
        addFamous(db, new Person(context.getString(R.string.priestley), new LocalDate(1733, 3, 24)));
        addFamous(db, new Person(context.getString(R.string.morris), new LocalDate(1834, 3, 24)));
        addFamous(db, new Person(context.getString(R.string.houdini), new LocalDate(1874, 3, 24)));
        addFamous(db, new Person(context.getString(R.string.dario_fo), new LocalDate(1926, 3, 24)));
        addFamous(db, new Person(context.getString(R.string.ballmer), new LocalDate(1956, 3, 24)));
        addFamous(db, new Person(context.getString(R.string.jim_parsons), new LocalDate(1973, 3, 24)));

//March 25
        addFamous(db, new Person(context.getString(R.string.amici), new LocalDate(1786, 3, 25)));
        addFamous(db, new Person(context.getString(R.string.toscanini), new LocalDate(1867, 3, 25)));
        addFamous(db, new Person(context.getString(R.string.aretha_franklin), new LocalDate(1942, 3, 25)));
        addFamous(db, new Person(context.getString(R.string.elton_john), new LocalDate(1947, 3, 25)));
        addFamous(db, new Person(context.getString(R.string.parker), new LocalDate(1965, 3, 25)));

//March 26
        addFamous(db, new Person(context.getString(R.string.gesner), new LocalDate(1516, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.prorok_divis), new LocalDate(1698, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.benjamin_thompson), new LocalDate(1753, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.feddersen), new LocalDate(1832, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.frost), new LocalDate(1874, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.tennessee_williams), new LocalDate(1911, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.katz), new LocalDate(1911, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.anfinsen), new LocalDate(1916, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.nimoy), new LocalDate(1931, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.tinto_brass), new LocalDate(1933, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.tyler), new LocalDate(1948, 3, 26)));
        addFamous(db, new Person(context.getString(R.string.knightley), new LocalDate(1985, 3, 26)));

//March 27
        addFamous(db, new Person(context.getString(R.string.hittorf), new LocalDate(1824, 3, 27)));
        addFamous(db, new Person(context.getString(R.string.rontgen), new LocalDate(1845, 3, 27)));
        addFamous(db, new Person(context.getString(R.string.wallach), new LocalDate(1847, 3, 27)));
        addFamous(db, new Person(context.getString(R.string.pearson), new LocalDate(1857, 3, 27)));
        addFamous(db, new Person(context.getString(R.string.henry_royce), new LocalDate(1863, 3, 27)));
        addFamous(db, new Person(context.getString(R.string.steichen), new LocalDate(1879, 3, 27)));
        addFamous(db, new Person(context.getString(R.string.tarantino), new LocalDate(1963, 3, 27)));

//March 28
        addFamous(db, new Person(context.getString(R.string.raphael), new LocalDate(1483, 3, 28)));
        addFamous(db, new Person(context.getString(R.string.comenius), new LocalDate(1592, 3, 28)));
        addFamous(db, new Person(context.getString(R.string.tamburini), new LocalDate(1800, 3, 28)));
        addFamous(db, new Person(context.getString(R.string.maxim_gorky), new LocalDate(1868, 3, 28)));
        addFamous(db, new Person(context.getString(R.string.heymans), new LocalDate(1892, 3, 28)));
        addFamous(db, new Person(context.getString(R.string.brzezinski), new LocalDate(1928, 3, 28)));
        addFamous(db, new Person(context.getString(R.string.friedman), new LocalDate(1930, 3, 28)));
        addFamous(db, new Person(context.getString(R.string.lady_gaga), new LocalDate(1986, 3, 28)));

//March 29
        addFamous(db, new Person(context.getString(R.string.santorio), new LocalDate(1561, 3, 29)));
        addFamous(db, new Person(context.getString(R.string.musaus), new LocalDate(1735, 3, 29)));
        addFamous(db, new Person(context.getString(R.string.schneider), new LocalDate(1805, 3, 29)));
        addFamous(db, new Person(context.getString(R.string.walton), new LocalDate(1918, 3, 29)));
        addFamous(db, new Person(context.getString(R.string.vane), new LocalDate(1927, 3, 29)));
        addFamous(db, new Person(context.getString(R.string.gleeson), new LocalDate(1955, 3, 29)));

//March 30
        addFamous(db, new Person(context.getString(R.string.goya), new LocalDate(1746, 3, 30)));
        addFamous(db, new Person(context.getString(R.string.rozier), new LocalDate(1754, 3, 30)));
        addFamous(db, new Person(context.getString(R.string.bunsen), new LocalDate(1811, 3, 30)));
        addFamous(db, new Person(context.getString(R.string.verlaine), new LocalDate(1844, 3, 30)));
        addFamous(db, new Person(context.getString(R.string.van_gogh), new LocalDate(1853, 3, 30)));
        addFamous(db, new Person(context.getString(R.string.sharpe), new LocalDate(1928, 3, 30)));
        addFamous(db, new Person(context.getString(R.string.dion), new LocalDate(1968, 3, 30)));

//March 31
        addFamous(db, new Person(context.getString(R.string.descartes), new LocalDate(1596, 3, 31)));
        addFamous(db, new Person(context.getString(R.string.marvell), new LocalDate(1621, 3, 31)));
        addFamous(db, new Person(context.getString(R.string.bach), new LocalDate(1685, 3, 31)));
        addFamous(db, new Person(context.getString(R.string.haydn), new LocalDate(1732, 3, 31)));
        addFamous(db, new Person(context.getString(R.string.chukovsky), new LocalDate(1882, 3, 31)));
        addFamous(db, new Person(context.getString(R.string.william_bragg), new LocalDate(1890, 3, 31)));
        addFamous(db, new Person(context.getString(R.string.walken), new LocalDate(1943, 3, 31)));

    }

    static void createFamousDbPart2(Context context, SQLiteDatabase db) {
//April 1
        addFamous(db, new Person(context.getString(R.string.harvey), new LocalDate(1578, 4, 1)));
        addFamous(db, new Person(context.getString(R.string.germain), new LocalDate(1776, 4, 1)));
        addFamous(db, new Person(context.getString(R.string.gogol), new LocalDate(1809, 4, 1)));
        addFamous(db, new Person(context.getString(R.string.zsigmondy), new LocalDate(1865, 4, 1)));
        addFamous(db, new Person(context.getString(R.string.busoni), new LocalDate(1866, 4, 1)));
        addFamous(db, new Person(context.getString(R.string.rachmaninoff), new LocalDate(1873, 4, 1)));
        addFamous(db, new Person(context.getString(R.string.lon_chaney), new LocalDate(1883, 4, 1)));
        addFamous(db, new Person(context.getString(R.string.maslow), new LocalDate(1908, 4, 1)));

//April 2
        addFamous(db, new Person(context.getString(R.string.grimaldi), new LocalDate(1618, 4, 2)));
        addFamous(db, new Person(context.getString(R.string.casanova), new LocalDate(1725, 4, 2)));
        addFamous(db, new Person(context.getString(R.string.andersen), new LocalDate(1805, 4, 2)));
        addFamous(db, new Person(context.getString(R.string.butler), new LocalDate(1862, 4, 2)));
        addFamous(db, new Person(context.getString(R.string.chrysler), new LocalDate(1875, 4, 2)));
        addFamous(db, new Person(context.getString(R.string.fassbender), new LocalDate(1977, 4, 2)));

//April 3
        addFamous(db, new Person(context.getString(R.string.washington_irving), new LocalDate(1783, 4, 3)));
        addFamous(db, new Person(context.getString(R.string.velde), new LocalDate(1863, 4, 3)));
        addFamous(db, new Person(context.getString(R.string.jansky), new LocalDate(1873, 4, 3)));
        addFamous(db, new Person(context.getString(R.string.brando), new LocalDate(1924, 4, 3)));
        addFamous(db, new Person(context.getString(R.string.baldwin), new LocalDate(1958, 4, 3)));
        addFamous(db, new Person(context.getString(R.string.murphy), new LocalDate(1961, 4, 3)));

//April 4
        addFamous(db, new Person(context.getString(R.string.reid), new LocalDate(1818, 4, 4)));
        addFamous(db, new Person(context.getString(R.string.siemens), new LocalDate(1823, 4, 4)));
        addFamous(db, new Person(context.getString(R.string.simmons), new LocalDate(1848, 4, 4)));
        addFamous(db, new Person(context.getString(R.string.weaving), new LocalDate(1960, 4, 4)));
        addFamous(db, new Person(context.getString(R.string.robert_downey), new LocalDate(1965, 4, 4)));
        addFamous(db, new Person(context.getString(R.string.ledger), new LocalDate(1979, 4, 4)));

//April 5
        addFamous(db, new Person(context.getString(R.string.hobbes), new LocalDate(1588, 4, 5)));
        addFamous(db, new Person(context.getString(R.string.viviani), new LocalDate(1622, 4, 5)));
        addFamous(db, new Person(context.getString(R.string.yale), new LocalDate(1649, 4, 5)));
        addFamous(db, new Person(context.getString(R.string.spohr), new LocalDate(1784, 4, 5)));
        addFamous(db, new Person(context.getString(R.string.dupre), new LocalDate(1811, 4, 5)));
        addFamous(db, new Person(context.getString(R.string.nadar), new LocalDate(1820, 4, 5)));
        addFamous(db, new Person(context.getString(R.string.lister), new LocalDate(1827, 4, 5)));
        addFamous(db, new Person(context.getString(R.string.hailey), new LocalDate(1920, 4, 5)));

//April 6
        addFamous(db, new Person(context.getString(R.string.gosse), new LocalDate(1810, 4, 6)));
        addFamous(db, new Person(context.getString(R.string.moreau), new LocalDate(1826, 4, 6)));
        addFamous(db, new Person(context.getString(R.string.douglas), new LocalDate(1892, 4, 6)));
        addFamous(db, new Person(context.getString(R.string.lynen), new LocalDate(1911, 4, 6)));
        addFamous(db, new Person(context.getString(R.string.fischer), new LocalDate(1920, 4, 6)));
        addFamous(db, new Person(context.getString(R.string.james_watson), new LocalDate(1928, 4, 6)));

//April 7
        addFamous(db, new Person(context.getString(R.string.gerard_dou), new LocalDate(1613, 4, 7)));
        addFamous(db, new Person(context.getString(R.string.wordsworth), new LocalDate(1770, 4, 7)));
        addFamous(db, new Person(context.getString(R.string.selmi), new LocalDate(1817, 4, 7)));
        addFamous(db, new Person(context.getString(R.string.christiansen), new LocalDate(1891, 4, 7)));
        addFamous(db, new Person(context.getString(R.string.holiday), new LocalDate(1915, 4, 7)));
        addFamous(db, new Person(context.getString(R.string.coppola), new LocalDate(1939, 4, 7)));
        addFamous(db, new Person(context.getString(R.string.chan), new LocalDate(1954, 4, 7)));
        addFamous(db, new Person(context.getString(R.string.crowe), new LocalDate(1964, 4, 7)));

//April 8
        addFamous(db, new Person(context.getString(R.string.tartini), new LocalDate(1692, 4, 8)));
        addFamous(db, new Person(context.getString(R.string.von_hofmann), new LocalDate(1818, 4, 8)));
        addFamous(db, new Person(context.getString(R.string.husserl), new LocalDate(1859, 4, 8)));
        addFamous(db, new Person(context.getString(R.string.hicks), new LocalDate(1904, 4, 8)));
        addFamous(db, new Person(context.getString(R.string.calvin), new LocalDate(1911, 4, 8)));

//April 9
        addFamous(db, new Person(context.getString(R.string.timur), new LocalDate(1336, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.boehm), new LocalDate(1794, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.brunel), new LocalDate(1806, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.muybridge), new LocalDate(1830, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.pincus), new LocalDate(1903, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.eckert), new LocalDate(1919, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.hefner), new LocalDate(1926, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.belmondo), new LocalDate(1933, 4, 9)));
        addFamous(db, new Person(context.getString(R.string.stewart), new LocalDate(1990, 4, 9)));

//April 10
        addFamous(db, new Person(context.getString(R.string.grotius), new LocalDate(1583, 4, 10)));
        addFamous(db, new Person(context.getString(R.string.tschirnhaus), new LocalDate(1651, 4, 10)));
        addFamous(db, new Person(context.getString(R.string.heinicke), new LocalDate(1727, 4, 10)));
        addFamous(db, new Person(context.getString(R.string.pulitzer), new LocalDate(1847, 4, 10)));
        addFamous(db, new Person(context.getString(R.string.seagal), new LocalDate(1952, 4, 10)));
        addFamous(db, new Person(context.getString(R.string.canet), new LocalDate(1973, 4, 10)));

//April 11
        addFamous(db, new Person(context.getString(R.string.parkinson), new LocalDate(1775, 4, 11)));
        addFamous(db, new Person(context.getString(R.string.bertini), new LocalDate(1888, 4, 11)));
        addFamous(db, new Person(context.getString(R.string.julian), new LocalDate(1899, 4, 11)));
        addFamous(db, new Person(context.getString(R.string.lavey), new LocalDate(1930, 4, 11)));
        addFamous(db, new Person(context.getString(R.string.wiles), new LocalDate(1953, 4, 11)));

//April 12
        addFamous(db, new Person(context.getString(R.string.meyerhof), new LocalDate(1884, 4, 12)));
        addFamous(db, new Person(context.getString(R.string.lily_pons), new LocalDate(1898, 4, 12)));
        addFamous(db, new Person(context.getString(R.string.tinbergen), new LocalDate(1903, 4, 12)));
        addFamous(db, new Person(context.getString(R.string.cabalie), new LocalDate(1933, 4, 12)));
        addFamous(db, new Person(context.getString(R.string.hancock), new LocalDate(1940, 4, 12)));
        addFamous(db, new Person(context.getString(R.string.garcia), new LocalDate(1956, 4, 12)));

//April 13
        addFamous(db, new Person(context.getString(R.string.favre), new LocalDate(1506, 4, 13)));
        addFamous(db, new Person(context.getString(R.string.fawkes), new LocalDate(1570, 4, 13)));
        addFamous(db, new Person(context.getString(R.string.bramah), new LocalDate(1748, 4, 13)));
        addFamous(db, new Person(context.getString(R.string.trevithick), new LocalDate(1771, 4, 13)));
        addFamous(db, new Person(context.getString(R.string.meucci), new LocalDate(1808, 4, 13)));
        addFamous(db, new Person(context.getString(R.string.lacan), new LocalDate(1901, 4, 13)));
        addFamous(db, new Person(context.getString(R.string.beckett), new LocalDate(1906, 4, 13)));

//April 14
        addFamous(db, new Person(context.getString(R.string.ortelius), new LocalDate(1527, 4, 14)));
        addFamous(db, new Person(context.getString(R.string.huygens), new LocalDate(1629, 4, 14)));
        addFamous(db, new Person(context.getString(R.string.fonvizin), new LocalDate(1745, 4, 14)));
        addFamous(db, new Person(context.getString(R.string.rohlfs), new LocalDate(1831, 4, 14)));
        addFamous(db, new Person(context.getString(R.string.horsley), new LocalDate(1857, 4, 14)));
        addFamous(db, new Person(context.getString(R.string.matsumoto), new LocalDate(1965, 4, 14)));

//April 15
        addFamous(db, new Person(context.getString(R.string.da_vinci), new LocalDate(1452, 4, 15)));
        addFamous(db, new Person(context.getString(R.string.leonhard_euler), new LocalDate(1707, 4, 15)));
        addFamous(db, new Person(context.getString(R.string.cullen), new LocalDate(1710, 4, 15)));
        addFamous(db, new Person(context.getString(R.string.busch), new LocalDate(1832, 4, 15)));
        addFamous(db, new Person(context.getString(R.string.gumilyov), new LocalDate(1886, 4, 15)));
        addFamous(db, new Person(context.getString(R.string.emma_thompson), new LocalDate(1959, 4, 15)));
        addFamous(db, new Person(context.getString(R.string.emma_watson), new LocalDate(1990, 4, 15)));

//April 16
        addFamous(db, new Person(context.getString(R.string.apianus), new LocalDate(1495, 4, 16)));
        addFamous(db, new Person(context.getString(R.string.hadley), new LocalDate(1682, 4, 16)));
        addFamous(db, new Person(context.getString(R.string.eisenstein), new LocalDate(1823, 4, 16)));
        addFamous(db, new Person(context.getString(R.string.france), new LocalDate(1844, 4, 16)));
        addFamous(db, new Person(context.getString(R.string.wright), new LocalDate(1867, 4, 16)));
        addFamous(db, new Person(context.getString(R.string.chaplin), new LocalDate(1889, 4, 16)));

//April 17
        addFamous(db, new Person(context.getString(R.string.morgan), new LocalDate(1837, 4, 17)));
        addFamous(db, new Person(context.getString(R.string.starling), new LocalDate(1866, 4, 17)));
        addFamous(db, new Person(context.getString(R.string.saeverud), new LocalDate(1897, 4, 17)));
        addFamous(db, new Person(context.getString(R.string.kohler), new LocalDate(1946, 4, 17)));
        addFamous(db, new Person(context.getString(R.string.garner), new LocalDate(1972, 4, 17)));
        addFamous(db, new Person(context.getString(R.string.beckham), new LocalDate(1974, 4, 17)));

//April 18
        addFamous(db, new Person(context.getString(R.string.ricardo), new LocalDate(1772, 4, 18)));
        addFamous(db, new Person(context.getString(R.string.boisbaudran), new LocalDate(1838, 4, 18)));
        addFamous(db, new Person(context.getString(R.string.goldstein), new LocalDate(1940, 4, 18)));
        addFamous(db, new Person(context.getString(R.string.sokolov), new LocalDate(1950, 4, 18)));
        addFamous(db, new Person(context.getString(R.string.eric_roberts), new LocalDate(1956, 4, 18)));
        addFamous(db, new Person(context.getString(R.string.tennant), new LocalDate(1971, 4, 18)));

//April 19
        addFamous(db, new Person(context.getString(R.string.ehrenberg), new LocalDate(1795, 4, 19)));
        addFamous(db, new Person(context.getString(R.string.gerstner), new LocalDate(1796, 4, 19)));
        addFamous(db, new Person(context.getString(R.string.fechner), new LocalDate(1801, 4, 19)));
        addFamous(db, new Person(context.getString(R.string.hughes), new LocalDate(1900, 4, 19)));
        addFamous(db, new Person(context.getString(R.string.seaborg), new LocalDate(1912, 4, 19)));
        addFamous(db, new Person(context.getString(R.string.judd), new LocalDate(1968, 4, 19)));
        addFamous(db, new Person(context.getString(R.string.christensen), new LocalDate(1981, 4, 19)));

//April 20
        addFamous(db, new Person(context.getString(R.string.aretino), new LocalDate(1492, 4, 20)));
        addFamous(db, new Person(context.getString(R.string.pinel), new LocalDate(1745, 4, 20)));
        addFamous(db, new Person(context.getString(R.string.raffaelli), new LocalDate(1850, 4, 20)));
        addFamous(db, new Person(context.getString(R.string.hitler), new LocalDate(1889, 4, 20)));
        addFamous(db, new Person(context.getString(R.string.lloyd), new LocalDate(1893, 4, 20)));
        addFamous(db, new Person(context.getString(R.string.leiris), new LocalDate(1901, 4, 20)));
        addFamous(db, new Person(context.getString(R.string.muller), new LocalDate(1927, 4, 20)));
        addFamous(db, new Person(context.getString(R.string.sedgwick), new LocalDate(1943, 4, 20)));

//April 21
        addFamous(db, new Person(context.getString(R.string.riebeeck), new LocalDate(1619, 4, 21)));
        addFamous(db, new Person(context.getString(R.string.kulibin), new LocalDate(1735, 4, 21)));
        addFamous(db, new Person(context.getString(R.string.frobel), new LocalDate(1782, 4, 21)));
        addFamous(db, new Person(context.getString(R.string.starley), new LocalDate(1830, 4, 21)));
        addFamous(db, new Person(context.getString(R.string.flemming), new LocalDate(1843, 4, 21)));
        addFamous(db, new Person(context.getString(R.string.weber), new LocalDate(1864, 4, 21)));
        addFamous(db, new Person(context.getString(R.string.bridgman), new LocalDate(1882, 4, 21)));
        addFamous(db, new Person(context.getString(R.string.karrer), new LocalDate(1889, 4, 21)));

//April 22
        addFamous(db, new Person(context.getString(R.string.fielding), new LocalDate(1707, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.kant), new LocalDate(1724, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.plante), new LocalDate(1834, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.eichler), new LocalDate(1839, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.bohr), new LocalDate(1887, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.mabokov), new LocalDate(1899, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.oppenheimer), new LocalDate(1904, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.mingus), new LocalDate(1922, 4, 22)));
        addFamous(db, new Person(context.getString(R.string.nicholson), new LocalDate(1937, 4, 22)));

//April 23
        addFamous(db, new Person(context.getString(R.string.planck), new LocalDate(1858, 4, 23)));
        addFamous(db, new Person(context.getString(R.string.fibiger), new LocalDate(1867, 4, 23)));
        addFamous(db, new Person(context.getString(R.string.marsh), new LocalDate(1895, 4, 23)));
        addFamous(db, new Person(context.getString(R.string.ohlin), new LocalDate(1899, 4, 23)));
        addFamous(db, new Person(context.getString(R.string.laxness), new LocalDate(1902, 4, 23)));
        addFamous(db, new Person(context.getString(R.string.cena), new LocalDate(1977, 4, 23)));
        addFamous(db, new Person(context.getString(R.string.patel), new LocalDate(1990, 4, 23)));

//April 24
        addFamous(db, new Person(context.getString(R.string.martini), new LocalDate(1706, 4, 24)));
        addFamous(db, new Person(context.getString(R.string.cartwright), new LocalDate(1743, 4, 24)));
        addFamous(db, new Person(context.getString(R.string.spitteler), new LocalDate(1845, 4, 24)));
        addFamous(db, new Person(context.getString(R.string.bertillon), new LocalDate(1853, 4, 24)));
        addFamous(db, new Person(context.getString(R.string.sundback), new LocalDate(1880, 4, 24)));
        addFamous(db, new Person(context.getString(R.string.streisand), new LocalDate(1942, 4, 24)));

//April 25
        addFamous(db, new Person(context.getString(R.string.marc_brunel), new LocalDate(1769, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.klein), new LocalDate(1849, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.felix_dherelle), new LocalDate(1873, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.marconi), new LocalDate(1874, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.pauli), new LocalDate(1900, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.fitzgerald), new LocalDate(1917, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.al_pacino), new LocalDate(1940, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.cruyff), new LocalDate(1947, 4, 25)));
        addFamous(db, new Person(context.getString(R.string.zellweger), new LocalDate(1969, 4, 25)));

//April 26
        addFamous(db, new Person(context.getString(R.string.aurelius), new LocalDate(121, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.shakespeare), new LocalDate(1564, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.uhland), new LocalDate(1787, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.delacroix), new LocalDate(1798, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.krupp), new LocalDate(1812, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.billroth), new LocalDate(1829, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.richardson), new LocalDate(1879, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.wittgenstein), new LocalDate(1889, 4, 26)));
        addFamous(db, new Person(context.getString(R.string.charles_richter), new LocalDate(1900, 4, 26)));

//April 27
        addFamous(db, new Person(context.getString(R.string.kolreuter), new LocalDate(1733, 4, 27)));
        addFamous(db, new Person(context.getString(R.string.wollstonecraft), new LocalDate(1759, 4, 27)));
        addFamous(db, new Person(context.getString(R.string.morse), new LocalDate(1791, 4, 27)));
        addFamous(db, new Person(context.getString(R.string.carothers), new LocalDate(1896, 4, 27)));
        addFamous(db, new Person(context.getString(R.string.lantz), new LocalDate(1899, 4, 27)));

//April 28
        addFamous(db, new Person(context.getString(R.string.achard), new LocalDate(1753, 4, 28)));
        addFamous(db, new Person(context.getString(R.string.kraus), new LocalDate(1874, 4, 28)));
        addFamous(db, new Person(context.getString(R.string.godel), new LocalDate(1906, 4, 28)));
        addFamous(db, new Person(context.getString(R.string.schindler), new LocalDate(1908, 4, 28)));
        addFamous(db, new Person(context.getString(R.string.lamborghini), new LocalDate(1916, 4, 28)));
        addFamous(db, new Person(context.getString(R.string.harper_lee), new LocalDate(1926, 4, 28)));
        addFamous(db, new Person(context.getString(R.string.yves_klein), new LocalDate(1928, 4, 28)));
        addFamous(db, new Person(context.getString(R.string.cruz), new LocalDate(1974, 4, 28)));

//April 29
        addFamous(db, new Person(context.getString(R.string.drais), new LocalDate(1785, 4, 29)));
        addFamous(db, new Person(context.getString(R.string.poincare), new LocalDate(1854, 4, 29)));
        addFamous(db, new Person(context.getString(R.string.hearst), new LocalDate(1863, 4, 29)));
        addFamous(db, new Person(context.getString(R.string.urey), new LocalDate(1893, 4, 29)));
        addFamous(db, new Person(context.getString(R.string.jack_williamson), new LocalDate(1908, 4, 29)));
        addFamous(db, new Person(context.getString(R.string.pfeiffer), new LocalDate(1958, 4, 29)));
        addFamous(db, new Person(context.getString(R.string.thurman), new LocalDate(1970, 4, 29)));

//April 30
        addFamous(db, new Person(context.getString(R.string.gauss), new LocalDate(1777, 4, 30)));
        addFamous(db, new Person(context.getString(R.string.bleuler), new LocalDate(1857, 4, 30)));
        addFamous(db, new Person(context.getString(R.string.kuznets), new LocalDate(1901, 4, 30)));
        addFamous(db, new Person(context.getString(R.string.schultz), new LocalDate(1902, 4, 30)));
        addFamous(db, new Person(context.getString(R.string.shannon), new LocalDate(1916, 4, 30)));
        addFamous(db, new Person(context.getString(R.string.gal_gadot), new LocalDate(1985, 4, 30)));

//May 1
        addFamous(db, new Person(context.getString(R.string.addison), new LocalDate(1672, 5, 1)));
        addFamous(db, new Person(context.getString(R.string.cajal), new LocalDate(1852, 5, 1)));
        addFamous(db, new Person(context.getString(R.string.chardin), new LocalDate(1881, 5, 1)));
        addFamous(db, new Person(context.getString(R.string.woo), new LocalDate(1946, 5, 1)));
        addFamous(db, new Person(context.getString(R.string.dornan), new LocalDate(1982, 5, 1)));

//May 2
        addFamous(db, new Person(context.getString(R.string.kirche), new LocalDate(1602, 5, 2)));
        addFamous(db, new Person(context.getString(R.string.jerome), new LocalDate(1859, 5, 2)));
        addFamous(db, new Person(context.getString(R.string.wood), new LocalDate(1868, 5, 2)));
        addFamous(db, new Person(context.getString(R.string.marshall), new LocalDate(1902, 5, 2)));
        addFamous(db, new Person(context.getString(R.string.springer), new LocalDate(1912, 5, 2)));
        addFamous(db, new Person(context.getString(R.string.johnson), new LocalDate(1972, 5, 2)));
        addFamous(db, new Person(context.getString(R.string.david_beckham), new LocalDate(1975, 5, 2)));

//May 3
        addFamous(db, new Person(context.getString(R.string.machiavelli), new LocalDate(1469, 5, 3)));
        addFamous(db, new Person(context.getString(R.string.haldane), new LocalDate(1860, 5, 3)));
        addFamous(db, new Person(context.getString(R.string.ekman), new LocalDate(1874, 5, 3)));
        addFamous(db, new Person(context.getString(R.string.coty), new LocalDate(1874, 5, 3)));
        addFamous(db, new Person(context.getString(R.string.thomson), new LocalDate(1892, 5, 3)));
        addFamous(db, new Person(context.getString(R.string.kastler), new LocalDate(1902, 5, 3)));

//May 4
        addFamous(db, new Person(context.getString(R.string.cristofori), new LocalDate(1655, 5, 4)));
        addFamous(db, new Person(context.getString(R.string.borda), new LocalDate(1733, 5, 4)));
        addFamous(db, new Person(context.getString(R.string.brockhaus), new LocalDate(1772, 5, 4)));
        addFamous(db, new Person(context.getString(R.string.thenard), new LocalDate(1777, 5, 4)));
        addFamous(db, new Person(context.getString(R.string.liddell), new LocalDate(1852, 5, 4)));
        addFamous(db, new Person(context.getString(R.string.mandelstam), new LocalDate(1879, 5, 4)));
        addFamous(db, new Person(context.getString(R.string.hepburn), new LocalDate(1929, 5, 4)));

//May 5
        addFamous(db, new Person(context.getString(R.string.kierkegaard), new LocalDate(1813, 5, 5)));
        addFamous(db, new Person(context.getString(R.string.marx), new LocalDate(1818, 5, 5)));
        addFamous(db, new Person(context.getString(R.string.sienkiewicz), new LocalDate(1846, 5, 5)));
        addFamous(db, new Person(context.getString(R.string.schawlow), new LocalDate(1921, 5, 5)));
        addFamous(db, new Person(context.getString(R.string.adele), new LocalDate(1988, 5, 5)));

//May 6
        addFamous(db, new Person(context.getString(R.string.freud), new LocalDate(1856, 5, 6)));
        addFamous(db, new Person(context.getString(R.string.peary), new LocalDate(1856, 5, 6)));
        addFamous(db, new Person(context.getString(R.string.grignard), new LocalDate(1871, 5, 6)));
        addFamous(db, new Person(context.getString(R.string.martinson), new LocalDate(1904, 5, 6)));
        addFamous(db, new Person(context.getString(R.string.clooney), new LocalDate(1961, 5, 6)));

//May 7
        addFamous(db, new Person(context.getString(R.string.clairaut), new LocalDate(1713, 5, 7)));
        addFamous(db, new Person(context.getString(R.string.robert_browning), new LocalDate(1812, 5, 7)));
        addFamous(db, new Person(context.getString(R.string.tchaikovsky), new LocalDate(1840, 5, 7)));
        addFamous(db, new Person(context.getString(R.string.tagore), new LocalDate(1861, 5, 7)));
        addFamous(db, new Person(context.getString(R.string.reymont), new LocalDate(1867, 5, 7)));
        addFamous(db, new Person(context.getString(R.string.land), new LocalDate(1909, 5, 7)));

//May 8
        addFamous(db, new Person(context.getString(R.string.dunant), new LocalDate(1828, 5, 8)));
        addFamous(db, new Person(context.getString(R.string.lwoff), new LocalDate(1902, 5, 8)));
        addFamous(db, new Person(context.getString(R.string.fernandel), new LocalDate(1903, 5, 8)));
        addFamous(db, new Person(context.getString(R.string.rossellini), new LocalDate(1906, 5, 8)));
        addFamous(db, new Person(context.getString(R.string.iglesias), new LocalDate(1975, 5, 8)));

//May 9
        addFamous(db, new Person(context.getString(R.string.monge), new LocalDate(1746, 5, 9)));
        addFamous(db, new Person(context.getString(R.string.opel), new LocalDate(1837, 5, 9)));
        addFamous(db, new Person(context.getString(R.string.laval), new LocalDate(1845, 5, 9)));
        addFamous(db, new Person(context.getString(R.string.carter), new LocalDate(1874, 5, 9)));
        addFamous(db, new Person(context.getString(R.string.gasset), new LocalDate(1883, 5, 9)));
        addFamous(db, new Person(context.getString(R.string.richard_day), new LocalDate(1896, 5, 9)));
        addFamous(db, new Person(context.getString(R.string.eigen), new LocalDate(1927, 5, 9)));

//May 10
        addFamous(db, new Person(context.getString(R.string.lisle), new LocalDate(1760, 5, 10)));
        addFamous(db, new Person(context.getString(R.string.fresnel), new LocalDate(1788, 5, 10)));
        addFamous(db, new Person(context.getString(R.string.killing), new LocalDate(1847, 5, 10)));
        addFamous(db, new Person(context.getString(R.string.lipton), new LocalDate(1848, 5, 10)));
        addFamous(db, new Person(context.getString(R.string.gaumont), new LocalDate(1864, 5, 10)));
        addFamous(db, new Person(context.getString(R.string.barth), new LocalDate(1886, 5, 10)));
        addFamous(db, new Person(context.getString(R.string.selznick), new LocalDate(1902, 5, 10)));
        addFamous(db, new Person(context.getString(R.string.chapman), new LocalDate(1955, 5, 10)));

//May 11
        addFamous(db, new Person(context.getString(R.string.munchhausen), new LocalDate(1720, 5, 11)));
        addFamous(db, new Person(context.getString(R.string.blumenbach), new LocalDate(1752, 5, 11)));
        addFamous(db, new Person(context.getString(R.string.voynich), new LocalDate(1864, 5, 11)));
        addFamous(db, new Person(context.getString(R.string.dali), new LocalDate(1904, 5, 11)));
        addFamous(db, new Person(context.getString(R.string.feynman), new LocalDate(1918, 5, 11)));
        addFamous(db, new Person(context.getString(R.string.dijkstra), new LocalDate(1930, 5, 11)));
        addFamous(db, new Person(context.getString(R.string.iniesta), new LocalDate(1984, 5, 11)));

//May 12
        addFamous(db, new Person(context.getString(R.string.lear), new LocalDate(1812, 5, 12)));
        addFamous(db, new Person(context.getString(R.string.hind), new LocalDate(1823, 5, 12)));
        addFamous(db, new Person(context.getString(R.string.pirquet), new LocalDate(1874, 5, 12)));
        addFamous(db, new Person(context.getString(R.string.giauque), new LocalDate(1895, 5, 12)));
        addFamous(db, new Person(context.getString(R.string.devi), new LocalDate(1899, 5, 12)));
        addFamous(db, new Person(context.getString(R.string.voznesensky), new LocalDate(1933, 5, 12)));

//May 13
        addFamous(db, new Person(context.getString(R.string.nevsky), new LocalDate(1221, 5, 13)));
        addFamous(db, new Person(context.getString(R.string.daudet), new LocalDate(1840, 5, 13)));
        addFamous(db, new Person(context.getString(R.string.ross), new LocalDate(1857, 5, 13)));
        addFamous(db, new Person(context.getString(R.string.braque), new LocalDate(1882, 5, 13)));
        addFamous(db, new Person(context.getString(R.string.wonder), new LocalDate(1950, 5, 13)));
        addFamous(db, new Person(context.getString(R.string.rodman), new LocalDate(1961, 5, 13)));
        addFamous(db, new Person(context.getString(R.string.pattinson), new LocalDate(1986, 5, 13)));

//May 14
        addFamous(db, new Person(context.getString(R.string.gainsborough), new LocalDate(1727, 5, 14)));
        addFamous(db, new Person(context.getString(R.string.steinitz), new LocalDate(1836, 5, 14)));
        addFamous(db, new Person(context.getString(R.string.tsvet), new LocalDate(1872, 5, 14)));
        addFamous(db, new Person(context.getString(R.string.lucas), new LocalDate(1944, 5, 14)));
        addFamous(db, new Person(context.getString(R.string.zemeckis), new LocalDate(1952, 5, 14)));
        addFamous(db, new Person(context.getString(R.string.tim_roth), new LocalDate(1961, 5, 14)));
        addFamous(db, new Person(context.getString(R.string.blanchett), new LocalDate(1969, 5, 14)));
        addFamous(db, new Person(context.getString(R.string.zuckerberg), new LocalDate(1984, 5, 14)));

//May 15
        addFamous(db, new Person(context.getString(R.string.mechnikov), new LocalDate(1845, 5, 15)));
        addFamous(db, new Person(context.getString(R.string.vasnetsov), new LocalDate(1848, 5, 15)));
        addFamous(db, new Person(context.getString(R.string.wernicke), new LocalDate(1848, 5, 15)));
        addFamous(db, new Person(context.getString(R.string.baum), new LocalDate(1856, 5, 15)));
        addFamous(db, new Person(context.getString(R.string.curie), new LocalDate(1859, 5, 15)));
        addFamous(db, new Person(context.getString(R.string.bulgakov), new LocalDate(1891, 5, 15)));

//May 16
        addFamous(db, new Person(context.getString(R.string.agnesi), new LocalDate(1718, 5, 16)));
        addFamous(db, new Person(context.getString(R.string.vauquelin), new LocalDate(1763, 5, 16)));
        addFamous(db, new Person(context.getString(R.string.david_hughes), new LocalDate(1831, 5, 16)));
        addFamous(db, new Person(context.getString(R.string.fonda), new LocalDate(1905, 5, 16)));
        addFamous(db, new Person(context.getString(R.string.herman), new LocalDate(1913, 5, 16)));
        addFamous(db, new Person(context.getString(R.string.trejo), new LocalDate(1944, 5, 16)));
        addFamous(db, new Person(context.getString(R.string.brosnan), new LocalDate(1953, 5, 16)));
        addFamous(db, new Person(context.getString(R.string.megan_fox), new LocalDate(1986, 5, 16)));

//May 17
        addFamous(db, new Person(context.getString(R.string.jenner), new LocalDate(1749, 5, 17)));
        addFamous(db, new Person(context.getString(R.string.lockyer), new LocalDate(1836, 5, 17)));
        addFamous(db, new Person(context.getString(R.string.hassel), new LocalDate(1897, 5, 17)));
        addFamous(db, new Person(context.getString(R.string.gabin), new LocalDate(1904, 5, 17)));
        addFamous(db, new Person(context.getString(R.string.nilsson), new LocalDate(1918, 5, 17)));
        addFamous(db, new Person(context.getString(R.string.hopper), new LocalDate(1936, 5, 17)));

//May 18
        addFamous(db, new Person(context.getString(R.string.khayyam), new LocalDate(1048, 5, 18)));
        addFamous(db, new Person(context.getString(R.string.clapperton), new LocalDate(1788, 5, 18)));
        addFamous(db, new Person(context.getString(R.string.hofmeister), new LocalDate(1824, 5, 18)));
        addFamous(db, new Person(context.getString(R.string.heaviside), new LocalDate(1850, 5, 18)));
        addFamous(db, new Person(context.getString(R.string.bertrand_russell), new LocalDate(1872, 5, 18)));
        addFamous(db, new Person(context.getString(R.string.vigneaud), new LocalDate(1901, 5, 18)));
        addFamous(db, new Person(context.getString(R.string.cretu), new LocalDate(1957, 5, 18)));

//May 19
        addFamous(db, new Person(context.getString(R.string.evola), new LocalDate(1898, 5, 19)));
        addFamous(db, new Person(context.getString(R.string.colin_chapman), new LocalDate(1928, 5, 19)));
        addFamous(db, new Person(context.getString(R.string.placido), new LocalDate(1946, 5, 19)));
        addFamous(db, new Person(context.getString(R.string.karapetyan), new LocalDate(1953, 5, 19)));
        addFamous(db, new Person(context.getString(R.string.oreiro), new LocalDate(1977, 5, 19)));
        addFamous(db, new Person(context.getString(R.string.pirlo), new LocalDate(1979, 5, 19)));
        addFamous(db, new Person(context.getString(R.string.sam_smith), new LocalDate(1992, 5, 19)));

//May 20
        addFamous(db, new Person(context.getString(R.string.fabricius), new LocalDate(1533, 5, 20)));
        addFamous(db, new Person(context.getString(R.string.balzac), new LocalDate(1799, 5, 20)));
        addFamous(db, new Person(context.getString(R.string.passy), new LocalDate(1822, 5, 20)));
        addFamous(db, new Person(context.getString(R.string.berliner), new LocalDate(1851, 5, 20)));
        addFamous(db, new Person(context.getString(R.string.hewlett), new LocalDate(1913, 5, 20)));
        addFamous(db, new Person(context.getString(R.string.edward_lewis), new LocalDate(1918, 5, 20)));
        addFamous(db, new Person(context.getString(R.string.cher), new LocalDate(1946, 5, 20)));

//May 21
        addFamous(db, new Person(context.getString(R.string.durer), new LocalDate(1471, 5, 21)));
        addFamous(db, new Person(context.getString(R.string.coriolis), new LocalDate(1792, 5, 21)));
        addFamous(db, new Person(context.getString(R.string.kock), new LocalDate(1793, 5, 21)));
        addFamous(db, new Person(context.getString(R.string.renault), new LocalDate(1843, 5, 21)));
        addFamous(db, new Person(context.getString(R.string.einthoven), new LocalDate(1860, 5, 21)));
        addFamous(db, new Person(context.getString(R.string.sakharov), new LocalDate(1921, 5, 21)));

//May 22
        addFamous(db, new Person(context.getString(R.string.wagner), new LocalDate(1813, 5, 22)));
        addFamous(db, new Person(context.getString(R.string.doyle), new LocalDate(1859, 5, 22)));
        addFamous(db, new Person(context.getString(R.string.olivier), new LocalDate(1907, 5, 22)));
        addFamous(db, new Person(context.getString(R.string.herge), new LocalDate(1907, 5, 22)));
        addFamous(db, new Person(context.getString(R.string.herbert_brown), new LocalDate(1912, 5, 22)));
        addFamous(db, new Person(context.getString(R.string.campbell), new LocalDate(1970, 5, 22)));

//May 23
        addFamous(db, new Person(context.getString(R.string.linnaeus), new LocalDate(1707, 5, 23)));
        addFamous(db, new Person(context.getString(R.string.mesmer), new LocalDate(1734, 5, 23)));
        addFamous(db, new Person(context.getString(R.string.lilienthal), new LocalDate(1848, 5, 23)));
        addFamous(db, new Person(context.getString(R.string.fairbanks), new LocalDate(1883, 5, 23)));
        addFamous(db, new Person(context.getString(R.string.lagerkvist), new LocalDate(1891, 5, 23)));
        addFamous(db, new Person(context.getString(R.string.bardeen), new LocalDate(1908, 5, 23)));
        addFamous(db, new Person(context.getString(R.string.moog), new LocalDate(1934, 5, 23)));

//May 24
        addFamous(db, new Person(context.getString(R.string.pontormo), new LocalDate(1494, 5, 24)));
        addFamous(db, new Person(context.getString(R.string.fahrenheit), new LocalDate(1686, 5, 24)));
        addFamous(db, new Person(context.getString(R.string.sholokhov), new LocalDate(1905, 5, 24)));
        addFamous(db, new Person(context.getString(R.string.brodsky), new LocalDate(1940, 5, 24)));
        addFamous(db, new Person(context.getString(R.string.dylan), new LocalDate(1941, 5, 24)));
        addFamous(db, new Person(context.getString(R.string.deakins), new LocalDate(1949, 5, 24)));

//May 25
        addFamous(db, new Person(context.getString(R.string.emerson), new LocalDate(1803, 5, 25)));
        addFamous(db, new Person(context.getString(R.string.burckhardt), new LocalDate(1818, 5, 25)));
        addFamous(db, new Person(context.getString(R.string.zeeman), new LocalDate(1865, 5, 25)));
        addFamous(db, new Person(context.getString(R.string.steinberger), new LocalDate(1921, 5, 25)));
        addFamous(db, new Person(context.getString(R.string.mckellen), new LocalDate(1939, 5, 25)));
        addFamous(db, new Person(context.getString(R.string.myers), new LocalDate(1963, 5, 25)));

//May 26
        addFamous(db, new Person(context.getString(R.string.petty), new LocalDate(1623, 5, 26)));
        addFamous(db, new Person(context.getString(R.string.moivre), new LocalDate(1667, 5, 26)));
        addFamous(db, new Person(context.getString(R.string.john_wayne), new LocalDate(1907, 5, 26)));
        addFamous(db, new Person(context.getString(R.string.miles_davis), new LocalDate(1926, 5, 26)));
        addFamous(db, new Person(context.getString(R.string.kevorkian), new LocalDate(1928, 5, 26)));
        addFamous(db, new Person(context.getString(R.string.kravitz), new LocalDate(1964, 5, 26)));
        addFamous(db, new Person(context.getString(R.string.helena_carter), new LocalDate(1966, 5, 26)));

//May 27
        addFamous(db, new Person(context.getString(R.string.vanderbilt), new LocalDate(1794, 5, 27)));
        addFamous(db, new Person(context.getString(R.string.duncan), new LocalDate(1877, 5, 27)));
        addFamous(db, new Person(context.getString(R.string.cockcroft), new LocalDate(1897, 5, 27)));
        addFamous(db, new Person(context.getString(R.string.christopher_lee), new LocalDate(1922, 5, 27)));
        addFamous(db, new Person(context.getString(R.string.bettany), new LocalDate(1971, 5, 27)));

//May 28
        addFamous(db, new Person(context.getString(R.string.guillotin), new LocalDate(1738, 5, 28)));
        addFamous(db, new Person(context.getString(R.string.thomas_moore), new LocalDate(1779, 5, 28)));
        addFamous(db, new Person(context.getString(R.string.agassiz), new LocalDate(1807, 5, 28)));
        addFamous(db, new Person(context.getString(R.string.milankovic), new LocalDate(1879, 5, 28)));
        addFamous(db, new Person(context.getString(R.string.ian_fleming), new LocalDate(1908, 5, 28)));
        addFamous(db, new Person(context.getString(R.string.minogue), new LocalDate(1968, 5, 28)));

//May 29
        addFamous(db, new Person(context.getString(R.string.david_bruce), new LocalDate(1855, 5, 29)));
        addFamous(db, new Person(context.getString(R.string.chesterton), new LocalDate(1874, 5, 29)));
        addFamous(db, new Person(context.getString(R.string.spengler), new LocalDate(1880, 5, 29)));
        addFamous(db, new Person(context.getString(R.string.bob_hope), new LocalDate(1903, 5, 29)));
        addFamous(db, new Person(context.getString(R.string.goldberg), new LocalDate(1911, 5, 29)));
        addFamous(db, new Person(context.getString(R.string.kennedy), new LocalDate(1917, 5, 29)));

//May 30
        addFamous(db, new Person(context.getString(R.string.hagen), new LocalDate(1817, 5, 30)));
        addFamous(db, new Person(context.getString(R.string.faberge), new LocalDate(1846, 5, 30)));
        addFamous(db, new Person(context.getString(R.string.thalberg), new LocalDate(1899, 5, 30)));
        addFamous(db, new Person(context.getString(R.string.alfven), new LocalDate(1908, 5, 30)));
        addFamous(db, new Person(context.getString(R.string.blanc), new LocalDate(1908, 5, 30)));
        addFamous(db, new Person(context.getString(R.string.gerrard), new LocalDate(1980, 5, 30)));

//May 31
        addFamous(db, new Person(context.getString(R.string.tieck), new LocalDate(1773, 5, 31)));
        addFamous(db, new Person(context.getString(R.string.pugni), new LocalDate(1802, 5, 31)));
        addFamous(db, new Person(context.getString(R.string.pirrie), new LocalDate(1847, 5, 31)));
        addFamous(db, new Person(context.getString(R.string.perse), new LocalDate(1887, 5, 31)));
        addFamous(db, new Person(context.getString(R.string.allais), new LocalDate(1887, 5, 31)));
        addFamous(db, new Person(context.getString(R.string.eastwood), new LocalDate(1930, 5, 31)));
        addFamous(db, new Person(context.getString(R.string.jay_miner), new LocalDate(1932, 5, 31)));

//June 1
        addFamous(db, new Person(context.getString(R.string.paer), new LocalDate(1771, 6, 1)));
        addFamous(db, new Person(context.getString(R.string.carnot), new LocalDate(1796, 6, 1)));
        addFamous(db, new Person(context.getString(R.string.glinka), new LocalDate(1804, 6, 1)));
        addFamous(db, new Person(context.getString(R.string.monroe), new LocalDate(1926, 6, 1)));
        addFamous(db, new Person(context.getString(R.string.foster), new LocalDate(1935, 6, 1)));
        addFamous(db, new Person(context.getString(R.string.freeman), new LocalDate(1937, 6, 1)));
        addFamous(db, new Person(context.getString(R.string.persson), new LocalDate(1979, 6, 1)));

//June 2
        addFamous(db, new Person(context.getString(R.string.de_sade), new LocalDate(1740, 6, 2)));
        addFamous(db, new Person(context.getString(R.string.cagliostro), new LocalDate(1743, 6, 2)));
        addFamous(db, new Person(context.getString(R.string.akimov), new LocalDate(1755, 6, 2)));
        addFamous(db, new Person(context.getString(R.string.hardy), new LocalDate(1840, 6, 2)));
        addFamous(db, new Person(context.getString(R.string.weissmüller), new LocalDate(1904, 6, 2)));
        addFamous(db, new Person(context.getString(R.string.quinto), new LocalDate(1977, 6, 2)));

//June 3
        addFamous(db, new Person(context.getString(R.string.hutton), new LocalDate(1726, 6, 3)));
        addFamous(db, new Person(context.getString(R.string.shrapnel), new LocalDate(1761, 6, 3)));
        addFamous(db, new Person(context.getString(R.string.cobden), new LocalDate(1804, 6, 3)));
        addFamous(db, new Person(context.getString(R.string.timiryazev), new LocalDate(1843, 6, 3)));
        addFamous(db, new Person(context.getString(R.string.pearl), new LocalDate(1879, 6, 3)));
        addFamous(db, new Person(context.getString(R.string.arber), new LocalDate(1929, 6, 3)));
        addFamous(db, new Person(context.getString(R.string.nadal), new LocalDate(1986, 6, 3)));

//June 4
        addFamous(db, new Person(context.getString(R.string.quesnay), new LocalDate(1694, 6, 4)));
        addFamous(db, new Person(context.getString(R.string.nazimova), new LocalDate(1879, 6, 4)));
        addFamous(db, new Person(context.getString(R.string.cockerell), new LocalDate(1910, 6, 4)));
        addFamous(db, new Person(context.getString(R.string.bartoli), new LocalDate(1966, 6, 4)));
        addFamous(db, new Person(context.getString(R.string.jolie), new LocalDate(1975, 6, 4)));

//June 5
        addFamous(db, new Person(context.getString(R.string.chippendale), new LocalDate(1718, 6, 5)));
        addFamous(db, new Person(context.getString(R.string.keynes), new LocalDate(1883, 6, 5)));
        addFamous(db, new Person(context.getString(R.string.lorca), new LocalDate(1898, 6, 5)));
        addFamous(db, new Person(context.getString(R.string.gabor), new LocalDate(1900, 6, 5)));
        addFamous(db, new Person(context.getString(R.string.peierls), new LocalDate(1907, 6, 5)));
        addFamous(db, new Person(context.getString(R.string.wahlberg), new LocalDate(1971, 6, 5)));

//June 6
        addFamous(db, new Person(context.getString(R.string.regiomontanus), new LocalDate(1436, 6, 6)));
        addFamous(db, new Person(context.getString(R.string.velazquez), new LocalDate(1599, 6, 6)));
        addFamous(db, new Person(context.getString(R.string.corneille), new LocalDate(1606, 6, 6)));
        addFamous(db, new Person(context.getString(R.string.pushkin), new LocalDate(1799, 6, 6)));
        addFamous(db, new Person(context.getString(R.string.braun), new LocalDate(1850, 6, 6)));
        addFamous(db, new Person(context.getString(R.string.mann), new LocalDate(1875, 6, 6)));

//June 7
        addFamous(db, new Person(context.getString(R.string.brummell), new LocalDate(1778, 6, 7)));
        addFamous(db, new Person(context.getString(R.string.auer), new LocalDate(1845, 6, 7)));
        addFamous(db, new Person(context.getString(R.string.mackintosh), new LocalDate(1868, 6, 7)));
        addFamous(db, new Person(context.getString(R.string.barkla), new LocalDate(1877, 6, 7)));
        addFamous(db, new Person(context.getString(R.string.mulliken), new LocalDate(1896, 6, 7)));
        addFamous(db, new Person(context.getString(R.string.apgar), new LocalDate(1909, 6, 7)));
        addFamous(db, new Person(context.getString(R.string.martin), new LocalDate(1917, 6, 7)));
        addFamous(db, new Person(context.getString(R.string.neeson), new LocalDate(1952, 6, 7)));

//June 8
        addFamous(db, new Person(context.getString(R.string.cassini), new LocalDate(1625, 6, 8)));
        addFamous(db, new Person(context.getString(R.string.albinoni), new LocalDate(1671, 6, 8)));
        addFamous(db, new Person(context.getString(R.string.careme), new LocalDate(1784, 6, 8)));
        addFamous(db, new Person(context.getString(R.string.schumann), new LocalDate(1810, 6, 8)));
        addFamous(db, new Person(context.getString(R.string.yeste), new LocalDate(1895, 6, 8)));
        addFamous(db, new Person(context.getString(R.string.john_campbell), new LocalDate(1910, 6, 8)));
        addFamous(db, new Person(context.getString(R.string.kanye_west), new LocalDate(1977, 6, 8)));

//June 9
        addFamous(db, new Person(context.getString(R.string.stephenson), new LocalDate(1781, 6, 9)));
        addFamous(db, new Person(context.getString(R.string.galle), new LocalDate(1812, 6, 9)));
        addFamous(db, new Person(context.getString(R.string.suttner), new LocalDate(1843, 6, 9)));
        addFamous(db, new Person(context.getString(R.string.dale), new LocalDate(1875, 6, 9)));
        addFamous(db, new Person(context.getString(R.string.fox), new LocalDate(1961, 6, 9)));
        addFamous(db, new Person(context.getString(R.string.depp), new LocalDate(1963, 6, 9)));
        addFamous(db, new Person(context.getString(R.string.portman), new LocalDate(1981, 6, 9)));

//June 10
        addFamous(db, new Person(context.getString(R.string.courbet), new LocalDate(1819, 6, 10)));
        addFamous(db, new Person(context.getString(R.string.otto), new LocalDate(1832, 6, 10)));
        addFamous(db, new Person(context.getString(R.string.cook), new LocalDate(1865, 6, 10)));
        addFamous(db, new Person(context.getString(R.string.mcdaniel), new LocalDate(1895, 6, 10)));
        addFamous(db, new Person(context.getString(R.string.bellow), new LocalDate(1915, 6, 10)));
        addFamous(db, new Person(context.getString(R.string.garland), new LocalDate(1922, 6, 10)));

//June 11
        addFamous(db, new Person(context.getString(R.string.constable), new LocalDate(1776, 6, 11)));
        addFamous(db, new Person(context.getString(R.string.fortuny), new LocalDate(1838, 6, 11)));
        addFamous(db, new Person(context.getString(R.string.richard_strauss), new LocalDate(1864, 6, 11)));
        addFamous(db, new Person(context.getString(R.string.cousteau), new LocalDate(1910, 6, 11)));
        addFamous(db, new Person(context.getString(R.string.styron), new LocalDate(1925, 6, 11)));
        addFamous(db, new Person(context.getString(R.string.laurie), new LocalDate(1959, 6, 11)));
        addFamous(db, new Person(context.getString(R.string.dinklage), new LocalDate(1969, 6, 11)));
        addFamous(db, new Person(context.getString(R.string.labeouf), new LocalDate(1986, 6, 11)));

//June 12
        addFamous(db, new Person(context.getString(R.string.roebling), new LocalDate(1806, 6, 12)));
        addFamous(db, new Person(context.getString(R.string.lipmann), new LocalDate(1899, 6, 12)));
        addFamous(db, new Person(context.getString(R.string.frank), new LocalDate(1929, 6, 12)));
        addFamous(db, new Person(context.getString(R.string.sakmann), new LocalDate(1942, 6, 12)));
        addFamous(db, new Person(context.getString(R.string.lima), new LocalDate(1981, 6, 12)));

//June 13
        addFamous(db, new Person(context.getString(R.string.young), new LocalDate(1773, 6, 13)));
        addFamous(db, new Person(context.getString(R.string.maxwell), new LocalDate(1831, 6, 13)));
        addFamous(db, new Person(context.getString(R.string.yeats), new LocalDate(1865, 6, 13)));
        addFamous(db, new Person(context.getString(R.string.john_nash), new LocalDate(1928, 6, 13)));
        addFamous(db, new Person(context.getString(R.string.mcdowell), new LocalDate(1943, 6, 13)));
        addFamous(db, new Person(context.getString(R.string.perelman), new LocalDate(1966, 6, 13)));
        addFamous(db, new Person(context.getString(R.string.evans), new LocalDate(1981, 6, 13)));

//June 14
        addFamous(db, new Person(context.getString(R.string.coulomb), new LocalDate(1736, 6, 14)));
        addFamous(db, new Person(context.getString(R.string.stowe), new LocalDate(1811, 6, 14)));
        addFamous(db, new Person(context.getString(R.string.landsteiner), new LocalDate(1868, 6, 14)));
        addFamous(db, new Person(context.getString(R.string.tokarev), new LocalDate(1871, 6, 14)));
        addFamous(db, new Person(context.getString(R.string.church), new LocalDate(1903, 6, 14)));
        addFamous(db, new Person(context.getString(R.string.guevara), new LocalDate(1928, 6, 14)));
        addFamous(db, new Person(context.getString(R.string.graf), new LocalDate(1969, 6, 14)));

//June 15
        addFamous(db, new Person(context.getString(R.string.poussin), new LocalDate(1594, 6, 15)));
        addFamous(db, new Person(context.getString(R.string.fourcroy), new LocalDate(1755, 6, 15)));
        addFamous(db, new Person(context.getString(R.string.balmont), new LocalDate(1867, 6, 15)));
        addFamous(db, new Person(context.getString(R.string.belushi), new LocalDate(1954, 6, 15)));
        addFamous(db, new Person(context.getString(R.string.helen_hunt), new LocalDate(1963, 6, 15)));
        addFamous(db, new Person(context.getString(R.string.kahn), new LocalDate(1969, 6, 15)));
        addFamous(db, new Person(context.getString(R.string.harris), new LocalDate(1973, 6, 15)));

//June 16
        addFamous(db, new Person(context.getString(R.string.boccaccio), new LocalDate(1313, 6, 16)));
        addFamous(db, new Person(context.getString(R.string.plucker), new LocalDate(1801, 6, 16)));
        addFamous(db, new Person(context.getString(R.string.friedmann), new LocalDate(1888, 6, 16)));
        addFamous(db, new Person(context.getString(R.string.leinster), new LocalDate(1896, 6, 16)));
        addFamous(db, new Person(context.getString(R.string.chakraborty), new LocalDate(1950, 6, 16)));
        addFamous(db, new Person(context.getString(R.string.shakur), new LocalDate(1971, 6, 16)));
        addFamous(db, new Person(context.getString(R.string.john_newman), new LocalDate(1990, 6, 16)));

//June 17
        addFamous(db, new Person(context.getString(R.string.panini), new LocalDate(1691, 6, 17)));
        addFamous(db, new Person(context.getString(R.string.gounod), new LocalDate(1818, 6, 17)));
        addFamous(db, new Person(context.getString(R.string.stravinsky), new LocalDate(1882, 6, 17)));
        addFamous(db, new Person(context.getString(R.string.escher), new LocalDate(1898, 6, 17)));
        addFamous(db, new Person(context.getString(R.string.wakefield), new LocalDate(1903, 6, 17)));
        addFamous(db, new Person(context.getString(R.string.jacob), new LocalDate(1920, 6, 17)));

//June 18
        addFamous(db, new Person(context.getString(R.string.goncharov), new LocalDate(1812, 6, 18)));
        addFamous(db, new Person(context.getString(R.string.laveran), new LocalDate(1845, 6, 18)));
        addFamous(db, new Person(context.getString(R.string.flagg), new LocalDate(1877, 6, 18)));
        addFamous(db, new Person(context.getString(R.string.macdonald), new LocalDate(1903, 6, 18)));
        addFamous(db, new Person(context.getString(R.string.mccartney), new LocalDate(1942, 6, 18)));
        addFamous(db, new Person(context.getString(R.string.capello), new LocalDate(1946, 6, 18)));

//June 19
        addFamous(db, new Person(context.getString(R.string.pascal), new LocalDate(1623, 6, 19)));
        addFamous(db, new Person(context.getString(R.string.dazai), new LocalDate(1909, 6, 19)));
        addFamous(db, new Person(context.getString(R.string.flory), new LocalDate(1910, 6, 19)));
        addFamous(db, new Person(context.getString(R.string.aage_bohr), new LocalDate(1922, 6, 19)));
        addFamous(db, new Person(context.getString(R.string.rushdie), new LocalDate(1947, 6, 19)));
        addFamous(db, new Person(context.getString(R.string.dujardin), new LocalDate(1972, 6, 19)));

//June 20
        addFamous(db, new Person(context.getString(R.string.rosa), new LocalDate(1615, 6, 20)));
        addFamous(db, new Person(context.getString(R.string.offenbach), new LocalDate(1819, 6, 20)));
        addFamous(db, new Person(context.getString(R.string.bonnat), new LocalDate(1833, 6, 20)));
        addFamous(db, new Person(context.getString(R.string.kidman), new LocalDate(1967, 6, 20)));
        addFamous(db, new Person(context.getString(R.string.rodriguez), new LocalDate(1968, 6, 20)));

//June 21
        addFamous(db, new Person(context.getString(R.string.poisson), new LocalDate(1781, 6, 21)));
        addFamous(db, new Person(context.getString(R.string.sartre), new LocalDate(1905, 6, 21)));
        addFamous(db, new Person(context.getString(R.string.mcewan), new LocalDate(1948, 6, 21)));
        addFamous(db, new Person(context.getString(R.string.platini), new LocalDate(1955, 6, 21)));
        addFamous(db, new Person(context.getString(R.string.tsoi), new LocalDate(1962, 6, 21)));
        addFamous(db, new Person(context.getString(R.string.lana_del_rey), new LocalDate(1985, 6, 21)));

//June 22
        addFamous(db, new Person(context.getString(R.string.haggard), new LocalDate(1856, 6, 22)));
        addFamous(db, new Person(context.getString(R.string.minkowski), new LocalDate(1864, 6, 22)));
        addFamous(db, new Person(context.getString(R.string.huxley), new LocalDate(1887, 6, 22)));
        addFamous(db, new Person(context.getString(R.string.remarque), new LocalDate(1898, 6, 22)));
        addFamous(db, new Person(context.getString(R.string.dillinger), new LocalDate(1903, 6, 22)));
        addFamous(db, new Person(context.getString(R.string.wilder), new LocalDate(1906, 6, 22)));
        addFamous(db, new Person(context.getString(R.string.streep), new LocalDate(1949, 6, 22)));
        addFamous(db, new Person(context.getString(R.string.dan_brown), new LocalDate(1964, 6, 22)));

//June 23
        addFamous(db, new Person(context.getString(R.string.vico), new LocalDate(1668, 6, 23)));
        addFamous(db, new Person(context.getString(R.string.beauharnais), new LocalDate(1763, 6, 23)));
        addFamous(db, new Person(context.getString(R.string.akhmatova), new LocalDate(1889, 6, 23)));
        addFamous(db, new Person(context.getString(R.string.turing), new LocalDate(1912, 6, 23)));
        addFamous(db, new Person(context.getString(R.string.fosse), new LocalDate(1927, 6, 23)));
        addFamous(db, new Person(context.getString(R.string.zidane), new LocalDate(1972, 6, 23)));

//June 24
        addFamous(db, new Person(context.getString(R.string.victor_hess), new LocalDate(1883, 6, 24)));
        addFamous(db, new Person(context.getString(R.string.fangio), new LocalDate(1911, 6, 24)));
        addFamous(db, new Person(context.getString(R.string.perl), new LocalDate(1927, 6, 24)));
        addFamous(db, new Person(context.getString(R.string.chabrol), new LocalDate(1930, 6, 24)));
        addFamous(db, new Person(context.getString(R.string.messi), new LocalDate(1987, 6, 24)));

//June 25
        addFamous(db, new Person(context.getString(R.string.gaudi), new LocalDate(1852, 6, 25)));
        addFamous(db, new Person(context.getString(R.string.nernst), new LocalDate(1864, 6, 25)));
        addFamous(db, new Person(context.getString(R.string.orwell), new LocalDate(1903, 6, 25)));
        addFamous(db, new Person(context.getString(R.string.lumet), new LocalDate(1924, 6, 25)));
        addFamous(db, new Person(context.getString(R.string.abrikosov), new LocalDate(1928, 6, 25)));
        addFamous(db, new Person(context.getString(R.string.michael), new LocalDate(1963, 6, 25)));

//June 26
        addFamous(db, new Person(context.getString(R.string.brandt), new LocalDate(1694, 6, 26)));
        addFamous(db, new Person(context.getString(R.string.kelvin), new LocalDate(1824, 6, 26)));
        addFamous(db, new Person(context.getString(R.string.buck), new LocalDate(1892, 6, 26)));
        addFamous(db, new Person(context.getString(R.string.bill_lear), new LocalDate(1902, 6, 26)));
        addFamous(db, new Person(context.getString(R.string.robert_richardson), new LocalDate(1937, 6, 26)));

//June 27
        addFamous(db, new Person(context.getString(R.string.mauser), new LocalDate(1838, 6, 27)));
        addFamous(db, new Person(context.getString(R.string.spemann), new LocalDate(1869, 6, 27)));
        addFamous(db, new Person(context.getString(R.string.keller), new LocalDate(1880, 6, 27)));
        addFamous(db, new Person(context.getString(R.string.abrams), new LocalDate(1966, 6, 27)));
        addFamous(db, new Person(context.getString(R.string.maguire), new LocalDate(1975, 6, 27)));
        addFamous(db, new Person(context.getString(R.string.raul), new LocalDate(1977, 6, 27)));

//June 28
        addFamous(db, new Person(context.getString(R.string.rubens), new LocalDate(1577, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.rousseau), new LocalDate(1712, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.broca), new LocalDate(1824, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.pirandello), new LocalDate(1867, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.carrel), new LocalDate(1873, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.goeppert_mayer), new LocalDate(1906, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.kathy_bates), new LocalDate(1948, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.cusack), new LocalDate(1966, 6, 28)));
        addFamous(db, new Person(context.getString(R.string.musk), new LocalDate(1971, 6, 28)));

//June 29
        addFamous(db, new Person(context.getString(R.string.dodoens), new LocalDate(1517, 6, 29)));
        addFamous(db, new Person(context.getString(R.string.ressel), new LocalDate(1793, 6, 29)));
        addFamous(db, new Person(context.getString(R.string.leopardi), new LocalDate(1798, 6, 29)));
        addFamous(db, new Person(context.getString(R.string.exupery), new LocalDate(1900, 6, 29)));
        addFamous(db, new Person(context.getString(R.string.fallaci), new LocalDate(1929, 6, 29)));
        addFamous(db, new Person(context.getString(R.string.scherzinger), new LocalDate(1978, 6, 29)));

//June 30
        addFamous(db, new Person(context.getString(R.string.vernet), new LocalDate(1789, 6, 30)));
        addFamous(db, new Person(context.getString(R.string.hooker), new LocalDate(1817, 6, 30)));
        addFamous(db, new Person(context.getString(R.string.duhamel), new LocalDate(1884, 6, 30)));
        addFamous(db, new Person(context.getString(R.string.milosz), new LocalDate(1911, 6, 30)));
        addFamous(db, new Person(context.getString(R.string.ballard), new LocalDate(1942, 6, 30)));
        addFamous(db, new Person(context.getString(R.string.tyson), new LocalDate(1966, 6, 30)));
        addFamous(db, new Person(context.getString(R.string.phelps), new LocalDate(1985, 6, 30)));

//July 1
        addFamous(db, new Person(context.getString(R.string.leibniz), new LocalDate(1646, 7, 1)));
        addFamous(db, new Person(context.getString(R.string.poncelet), new LocalDate(1788, 7, 1)));
        addFamous(db, new Person(context.getString(R.string.george_sand), new LocalDate(1804, 7, 1)));
        addFamous(db, new Person(context.getString(R.string.vierordt), new LocalDate(1818, 7, 1)));
        addFamous(db, new Person(context.getString(R.string.bleriot), new LocalDate(1872, 7, 1)));
        addFamous(db, new Person(context.getString(R.string.lauder), new LocalDate(1908, 7, 1)));
        addFamous(db, new Person(context.getString(R.string.diana), new LocalDate(1961, 7, 1)));
        addFamous(db, new Person(context.getString(R.string.pamela_anderson), new LocalDate(1967, 7, 1)));

//July 2
        addFamous(db, new Person(context.getString(R.string.gluck), new LocalDate(1714, 7, 2)));
        addFamous(db, new Person(context.getString(R.string.henry_bragg), new LocalDate(1862, 7, 2)));
        addFamous(db, new Person(context.getString(R.string.hesse), new LocalDate(1877, 7, 2)));
        addFamous(db, new Person(context.getString(R.string.lacoste), new LocalDate(1904, 7, 2)));
        addFamous(db, new Person(context.getString(R.string.cardin), new LocalDate(1922, 7, 2)));
        addFamous(db, new Person(context.getString(R.string.lumumba), new LocalDate(1925, 7, 2)));
        addFamous(db, new Person(context.getString(R.string.naceri), new LocalDate(1961, 7, 2)));
        addFamous(db, new Person(context.getString(R.string.robbie), new LocalDate(1990, 7, 2)));

//July 3
        addFamous(db, new Person(context.getString(R.string.adam), new LocalDate(1728, 7, 3)));
        addFamous(db, new Person(context.getString(R.string.kafka), new LocalDate(1883, 7, 3)));
        addFamous(db, new Person(context.getString(R.string.stoppard), new LocalDate(1937, 7, 3)));
        addFamous(db, new Person(context.getString(R.string.cruise), new LocalDate(1962, 7, 3)));
        addFamous(db, new Person(context.getString(R.string.selanne), new LocalDate(1970, 7, 3)));

//July 4
        addFamous(db, new Person(context.getString(R.string.blanchard), new LocalDate(1753, 7, 4)));
        addFamous(db, new Person(context.getString(R.string.everest), new LocalDate(1790, 7, 4)));
        addFamous(db, new Person(context.getString(R.string.garibaldi), new LocalDate(1807, 7, 4)));
        addFamous(db, new Person(context.getString(R.string.manolete), new LocalDate(1917, 7, 4)));
        addFamous(db, new Person(context.getString(R.string.lollobrigida), new LocalDate(1927, 7, 4)));

//July 5
        addFamous(db, new Person(context.getString(R.string.bulgarin), new LocalDate(1789, 7, 5)));
        addFamous(db, new Person(context.getString(R.string.fitzroy), new LocalDate(1805, 7, 5)));
        addFamous(db, new Person(context.getString(R.string.rankine), new LocalDate(1820, 7, 5)));
        addFamous(db, new Person(context.getString(R.string.zetkin), new LocalDate(1857, 7, 5)));
        addFamous(db, new Person(context.getString(R.string.gasser), new LocalDate(1888, 7, 5)));
        addFamous(db, new Person(context.getString(R.string.cocteau), new LocalDate(1889, 7, 5)));

//July 6
        addFamous(db, new Person(context.getString(R.string.raffles), new LocalDate(1781, 7, 6)));
        addFamous(db, new Person(context.getString(R.string.heidenstam), new LocalDate(1859, 7, 6)));
        addFamous(db, new Person(context.getString(R.string.chagall), new LocalDate(1887, 7, 6)));
        addFamous(db, new Person(context.getString(R.string.bill_haley), new LocalDate(1925, 7, 6)));
        addFamous(db, new Person(context.getString(R.string.stallone), new LocalDate(1946, 7, 6)));
        addFamous(db, new Person(context.getString(R.string.rush), new LocalDate(1951, 7, 6)));
        addFamous(db, new Person(context.getString(R.string.cent), new LocalDate(1975, 7, 6)));
        addFamous(db, new Person(context.getString(R.string.eva_green), new LocalDate(1980, 7, 6)));

//July 7
        addFamous(db, new Person(context.getString(R.string.jacquard), new LocalDate(1752, 7, 7)));
        addFamous(db, new Person(context.getString(R.string.golgi), new LocalDate(1843, 7, 7)));
        addFamous(db, new Person(context.getString(R.string.mahler), new LocalDate(1860, 7, 7)));
        addFamous(db, new Person(context.getString(R.string.feuchtwanger), new LocalDate(1884, 7, 7)));
        addFamous(db, new Person(context.getString(R.string.ringo_starr), new LocalDate(1940, 7, 7)));
        addFamous(db, new Person(context.getString(R.string.cutugno), new LocalDate(1943, 7, 7)));

//July 8
        addFamous(db, new Person(context.getString(R.string.fontaine), new LocalDate(1621, 7, 8)));
        addFamous(db, new Person(context.getString(R.string.zeppelin), new LocalDate(1838, 7, 8)));
        addFamous(db, new Person(context.getString(R.string.rockefeller), new LocalDate(1839, 7, 8)));
        addFamous(db, new Person(context.getString(R.string.benardos), new LocalDate(1842, 7, 8)));
        addFamous(db, new Person(context.getString(R.string.arthus_evans), new LocalDate(1851, 7, 8)));
        addFamous(db, new Person(context.getString(R.string.perls), new LocalDate(1893, 7, 8)));
        addFamous(db, new Person(context.getString(R.string.kapitsa), new LocalDate(1894, 7, 8)));

//July 9
        addFamous(db, new Person(context.getString(R.string.radcliffe), new LocalDate(1764, 7, 9)));
        addFamous(db, new Person(context.getString(R.string.davenport), new LocalDate(1802, 7, 9)));
        addFamous(db, new Person(context.getString(R.string.elias_howe), new LocalDate(1819, 7, 9)));
        addFamous(db, new Person(context.getString(R.string.boas), new LocalDate(1858, 7, 9)));
        addFamous(db, new Person(context.getString(R.string.chagas), new LocalDate(1879, 7, 9)));
        addFamous(db, new Person(context.getString(R.string.tom_hanks), new LocalDate(1956, 7, 9)));
        addFamous(db, new Person(context.getString(R.string.love), new LocalDate(1964, 7, 9)));

//July 10
        addFamous(db, new Person(context.getString(R.string.jean_calvin), new LocalDate(1509, 7, 10)));
        addFamous(db, new Person(context.getString(R.string.marryat), new LocalDate(1792, 7, 10)));
        addFamous(db, new Person(context.getString(R.string.pissarro), new LocalDate(1830, 7, 10)));
        addFamous(db, new Person(context.getString(R.string.tesla), new LocalDate(1856, 7, 10)));
        addFamous(db, new Person(context.getString(R.string.proust), new LocalDate(1871, 7, 10)));
        addFamous(db, new Person(context.getString(R.string.chamberlain), new LocalDate(1920, 7, 10)));

//July 11
        addFamous(db, new Person(context.getString(R.string.gondora), new LocalDate(1561, 7, 11)));
        addFamous(db, new Person(context.getString(R.string.lalande), new LocalDate(1732, 7, 11)));
        addFamous(db, new Person(context.getString(R.string.nelson), new LocalDate(1882, 7, 11)));
        addFamous(db, new Person(context.getString(R.string.abel), new LocalDate(1903, 7, 11)));
        addFamous(db, new Person(context.getString(R.string.brynner), new LocalDate(1920, 7, 11)));
        addFamous(db, new Person(context.getString(R.string.armani), new LocalDate(1934, 7, 11)));

//July 12
        addFamous(db, new Person(context.getString(R.string.bernard), new LocalDate(1813, 7, 12)));
        addFamous(db, new Person(context.getString(R.string.eastman), new LocalDate(1854, 7, 12)));
        addFamous(db, new Person(context.getString(R.string.tod_browning), new LocalDate(1880, 7, 12)));
        addFamous(db, new Person(context.getString(R.string.modigliani), new LocalDate(1884, 7, 12)));
        addFamous(db, new Person(context.getString(R.string.meruda), new LocalDate(1904, 7, 12)));
        addFamous(db, new Person(context.getString(R.string.wyeth), new LocalDate(1917, 7, 12)));
        addFamous(db, new Person(context.getString(R.string.michelle_rodriguez), new LocalDate(1978, 7, 12)));

//July 13
        addFamous(db, new Person(context.getString(R.string.john_dee), new LocalDate(1527, 7, 13)));
        addFamous(db, new Person(context.getString(R.string.cannizzaro), new LocalDate(1826, 7, 13)));
        addFamous(db, new Person(context.getString(R.string.otto_wagner), new LocalDate(1841, 7, 13)));
        addFamous(db, new Person(context.getString(R.string.babel), new LocalDate(1894, 7, 13)));
        addFamous(db, new Person(context.getString(R.string.ascari), new LocalDate(1918, 7, 13)));
        addFamous(db, new Person(context.getString(R.string.ford), new LocalDate(1942, 7, 13)));
        addFamous(db, new Person(context.getString(R.string.rubik), new LocalDate(1944, 7, 13)));
        addFamous(db, new Person(context.getString(R.string.benassi), new LocalDate(1967, 7, 13)));

//July 14
        addFamous(db, new Person(context.getString(R.string.dumas), new LocalDate(1800, 7, 14)));
        addFamous(db, new Person(context.getString(R.string.klimt), new LocalDate(1862, 7, 14)));
        addFamous(db, new Person(context.getString(R.string.irving_stone), new LocalDate(1903, 7, 14)));
        addFamous(db, new Person(context.getString(R.string.bergman), new LocalDate(1918, 7, 14)));
        addFamous(db, new Person(context.getString(R.string.forrester), new LocalDate(1918, 7, 14)));

//July 15
        addFamous(db, new Person(context.getString(R.string.rembrandt), new LocalDate(1606, 7, 15)));
        addFamous(db, new Person(context.getString(R.string.pareto), new LocalDate(1848, 7, 15)));
        addFamous(db, new Person(context.getString(R.string.harmsworth), new LocalDate(1865, 7, 15)));
        addFamous(db, new Person(context.getString(R.string.brockhouse), new LocalDate(1918, 7, 15)));
        addFamous(db, new Person(context.getString(R.string.savage), new LocalDate(1967, 7, 15)));
        addFamous(db, new Person(context.getString(R.string.kruger), new LocalDate(1976, 7, 15)));

//July 16
        addFamous(db, new Person(context.getString(R.string.assisi), new LocalDate(1194, 7, 16)));
        addFamous(db, new Person(context.getString(R.string.amundsen), new LocalDate(1872, 7, 16)));
        addFamous(db, new Person(context.getString(R.string.stanwyck), new LocalDate(1907, 7, 16)));
        addFamous(db, new Person(context.getString(R.string.laroche), new LocalDate(1921, 7, 16)));
        addFamous(db, new Person(context.getString(R.string.sheckley), new LocalDate(1928, 7, 16)));

//July 17
        addFamous(db, new Person(context.getString(R.string.friedrich_krupp), new LocalDate(1787, 7, 17)));
        addFamous(db, new Person(context.getString(R.string.corot), new LocalDate(1796, 7, 17)));
        addFamous(db, new Person(context.getString(R.string.nicholas), new LocalDate(1846, 7, 17)));
        addFamous(db, new Person(context.getString(R.string.lamaitre), new LocalDate(1894, 7, 17)));
        addFamous(db, new Person(context.getString(R.string.abbott), new LocalDate(1898, 7, 17)));
        addFamous(db, new Person(context.getString(R.string.sutherland), new LocalDate(1935, 7, 17)));

//July 18
        addFamous(db, new Person(context.getString(R.string.thackeray), new LocalDate(1811, 7, 18)));
        addFamous(db, new Person(context.getString(R.string.viardot), new LocalDate(1821, 7, 18)));
        addFamous(db, new Person(context.getString(R.string.lorentz), new LocalDate(1853, 7, 18)));
        addFamous(db, new Person(context.getString(R.string.mandela), new LocalDate(1918, 7, 18)));
        addFamous(db, new Person(context.getString(R.string.hunter_thompson), new LocalDate(1937, 7, 18)));
        addFamous(db, new Person(context.getString(R.string.branson), new LocalDate(1950, 7, 18)));
        addFamous(db, new Person(context.getString(R.string.vin_diesel), new LocalDate(1967, 7, 18)));

//July 19
        addFamous(db, new Person(context.getString(R.string.colt), new LocalDate(1814, 7, 19)));
        addFamous(db, new Person(context.getString(R.string.degas), new LocalDate(1834, 7, 19)));
        addFamous(db, new Person(context.getString(R.string.mayakovsky), new LocalDate(1893, 7, 19)));
        addFamous(db, new Person(context.getString(R.string.cronin), new LocalDate(1896, 7, 19)));
        addFamous(db, new Person(context.getString(R.string.coloane), new LocalDate(1910, 7, 19)));
        addFamous(db, new Person(context.getString(R.string.yalow), new LocalDate(1921, 7, 19)));
        addFamous(db, new Person(context.getString(R.string.cumberbatch), new LocalDate(1976, 7, 19)));

//July 20
        addFamous(db, new Person(context.getString(R.string.petrarca), new LocalDate(1304, 7, 20)));
        addFamous(db, new Person(context.getString(R.string.owen), new LocalDate(1804, 7, 20)));
        addFamous(db, new Person(context.getString(R.string.mendel), new LocalDate(1822, 7, 20)));
        addFamous(db, new Person(context.getString(R.string.georg_muller), new LocalDate(1850, 7, 20)));
        addFamous(db, new Person(context.getString(R.string.morandi), new LocalDate(1890, 7, 20)));
        addFamous(db, new Person(context.getString(R.string.dobrev), new LocalDate(1914, 7, 20)));
        addFamous(db, new Person(context.getString(R.string.bundchen), new LocalDate(1980, 7, 20)));

//July 21
        addFamous(db, new Person(context.getString(R.string.picard), new LocalDate(1620, 7, 21)));
        addFamous(db, new Person(context.getString(R.string.regnault), new LocalDate(1810, 7, 21)));
        addFamous(db, new Person(context.getString(R.string.reuter), new LocalDate(1816, 7, 21)));
        addFamous(db, new Person(context.getString(R.string.hemingway), new LocalDate(1899, 7, 21)));
        addFamous(db, new Person(context.getString(R.string.robin_williams), new LocalDate(1951, 7, 21)));
        addFamous(db, new Person(context.getString(R.string.josh_hartnett), new LocalDate(1978, 7, 21)));

//July 22
        addFamous(db, new Person(context.getString(R.string.soufflot), new LocalDate(1713, 7, 22)));
        addFamous(db, new Person(context.getString(R.string.gustav_hertz), new LocalDate(1887, 7, 22)));
        addFamous(db, new Person(context.getString(R.string.mathieu), new LocalDate(1946, 7, 22)));
        addFamous(db, new Person(context.getString(R.string.dafoe), new LocalDate(1955, 7, 22)));
        addFamous(db, new Person(context.getString(R.string.selena_gomez), new LocalDate(1992, 7, 22)));

//July 23
        addFamous(db, new Person(context.getString(R.string.vyazemsky), new LocalDate(1792, 7, 23)));
        addFamous(db, new Person(context.getString(R.string.cilea), new LocalDate(1866, 7, 23)));
        addFamous(db, new Person(context.getString(R.string.harrelson), new LocalDate(1961, 7, 23)));
        addFamous(db, new Person(context.getString(R.string.hoffman), new LocalDate(1967, 7, 23)));
        addFamous(db, new Person(context.getString(R.string.lewinsky), new LocalDate(1973, 7, 23)));
        addFamous(db, new Person(context.getString(R.string.daniel_radcliffe), new LocalDate(1989, 7, 23)));

//July 24
        addFamous(db, new Person(context.getString(R.string.vidocq), new LocalDate(1775, 7, 24)));
        addFamous(db, new Person(context.getString(R.string.alexandre_dumas), new LocalDate(1802, 7, 24)));
        addFamous(db, new Person(context.getString(R.string.mucha), new LocalDate(1860, 7, 24)));
        addFamous(db, new Person(context.getString(R.string.benson), new LocalDate(1867, 7, 24)));
        addFamous(db, new Person(context.getString(R.string.lopez), new LocalDate(1969, 7, 24)));

//July 25
        addFamous(db, new Person(context.getString(R.string.scheiner), new LocalDate(1575, 7, 25)));
        addFamous(db, new Person(context.getString(R.string.eakins), new LocalDate(1844, 7, 25)));
        addFamous(db, new Person(context.getString(R.string.davidson_black), new LocalDate(1884, 7, 25)));
        addFamous(db, new Person(context.getString(R.string.canetti), new LocalDate(1905, 7, 25)));
        addFamous(db, new Person(context.getString(R.string.leblanc), new LocalDate(1967, 7, 25)));

//July 26
        addFamous(db, new Person(context.getString(R.string.remak), new LocalDate(1815, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.shaw), new LocalDate(1856, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.jung), new LocalDate(1875, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.maurois), new LocalDate(1885, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.kubrick), new LocalDate(1928, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.jagger), new LocalDate(1943, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.spacey), new LocalDate(1959, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.bullock), new LocalDate(1964, 7, 26)));
        addFamous(db, new Person(context.getString(R.string.statham), new LocalDate(1967, 7, 26)));

//July 27
        addFamous(db, new Person(context.getString(R.string.corday), new LocalDate(1768, 7, 27)));
        addFamous(db, new Person(context.getString(R.string.carducci), new LocalDate(1835, 7, 27)));
        addFamous(db, new Person(context.getString(R.string.hans_fischer), new LocalDate(1881, 7, 27)));
        addFamous(db, new Person(context.getString(R.string.monaco), new LocalDate(1915, 7, 27)));
        addFamous(db, new Person(context.getString(R.string.nikolaj), new LocalDate(1970, 7, 27)));

//July 28
        addFamous(db, new Person(context.getString(R.string.hooke), new LocalDate(1635, 7, 28)));
        addFamous(db, new Person(context.getString(R.string.feuerbach), new LocalDate(1804, 7, 28)));
        addFamous(db, new Person(context.getString(R.string.grisi), new LocalDate(1811, 7, 28)));
        addFamous(db, new Person(context.getString(R.string.duchamp), new LocalDate(1887, 7, 28)));
        addFamous(db, new Person(context.getString(R.string.popper), new LocalDate(1902, 7, 28)));
        addFamous(db, new Person(context.getString(R.string.burda), new LocalDate(1909, 7, 28)));
        addFamous(db, new Person(context.getString(R.string.chavez), new LocalDate(1954, 7, 28)));

//July 29
        addFamous(db, new Person(context.getString(R.string.aivazovsky), new LocalDate(1817, 7, 29)));
        addFamous(db, new Person(context.getString(R.string.mussolini), new LocalDate(1883, 7, 29)));
        addFamous(db, new Person(context.getString(R.string.theda_bara), new LocalDate(1885, 7, 29)));
        addFamous(db, new Person(context.getString(R.string.clara_bow), new LocalDate(1905, 7, 29)));
        addFamous(db, new Person(context.getString(R.string.alonso), new LocalDate(1981, 7, 29)));

//July 30
        addFamous(db, new Person(context.getString(R.string.vasari), new LocalDate(1511, 7, 30)));
        addFamous(db, new Person(context.getString(R.string.bronte), new LocalDate(1818, 7, 30)));
        addFamous(db, new Person(context.getString(R.string.henry_ford), new LocalDate(1863, 7, 30)));
        addFamous(db, new Person(context.getString(R.string.cyril_parkinson), new LocalDate(1909, 7, 30)));
        addFamous(db, new Person(context.getString(R.string.schwarzenegger), new LocalDate(1947, 7, 30)));
        addFamous(db, new Person(context.getString(R.string.jean_reno), new LocalDate(1948, 7, 30)));
        addFamous(db, new Person(context.getString(R.string.nolan), new LocalDate(1970, 7, 30)));

//July 31
        addFamous(db, new Person(context.getString(R.string.cramer), new LocalDate(1704, 7, 31)));
        addFamous(db, new Person(context.getString(R.string.wohler), new LocalDate(1800, 7, 31)));
        addFamous(db, new Person(context.getString(R.string.planquette), new LocalDate(1848, 7, 31)));
        addFamous(db, new Person(context.getString(R.string.milton_friedman), new LocalDate(1912, 7, 31)));
        addFamous(db, new Person(context.getString(R.string.de_funes), new LocalDate(1914, 7, 31)));
        addFamous(db, new Person(context.getString(R.string.primo_levi), new LocalDate(1919, 7, 31)));
        addFamous(db, new Person(context.getString(R.string.rowling), new LocalDate(1965, 7, 31)));
    }

    static void createFamousDbPart3(Context context, SQLiteDatabase db) {

//August 1
        addFamous(db, new Person(context.getString(R.string.lamarck), new LocalDate(1744, 8, 1)));
        addFamous(db, new Person(context.getString(R.string.melville), new LocalDate(1819, 8, 1)));
        addFamous(db, new Person(context.getString(R.string.taro), new LocalDate(1910, 8, 1)));
        addFamous(db, new Person(context.getString(R.string.laurent), new LocalDate(1936, 8, 1)));
        addFamous(db, new Person(context.getString(R.string.mendes), new LocalDate(1965, 8, 1)));
        addFamous(db, new Person(context.getString(R.string.momoa), new LocalDate(1979, 8, 1)));

//August 2
        addFamous(db, new Person(context.getString(R.string.hoogstraten), new LocalDate(1627, 8, 2)));
        addFamous(db, new Person(context.getString(R.string.tyndall), new LocalDate(1820, 8, 2)));
        addFamous(db, new Person(context.getString(R.string.olcott), new LocalDate(1832, 8, 2)));
        addFamous(db, new Person(context.getString(R.string.bartholdi), new LocalDate(1834, 8, 2)));
        addFamous(db, new Person(context.getString(R.string.loy), new LocalDate(1905, 8, 2)));
        addFamous(db, new Person(context.getString(R.string.worthington), new LocalDate(1976, 8, 2)));

//August 3
        addFamous(db, new Person(context.getString(R.string.otis), new LocalDate(1811, 8, 3)));
        addFamous(db, new Person(context.getString(R.string.simak), new LocalDate(1904, 8, 3)));
        addFamous(db, new Person(context.getString(R.string.james), new LocalDate(1920, 8, 3)));
        addFamous(db, new Person(context.getString(R.string.sheen), new LocalDate(1940, 8, 3)));
        addFamous(db, new Person(context.getString(R.string.lilly), new LocalDate(1979, 8, 3)));

//August 4
        addFamous(db, new Person(context.getString(R.string.shelley), new LocalDate(1792, 8, 4)));
        addFamous(db, new Person(context.getString(R.string.john_venn), new LocalDate(1834, 8, 4)));
        addFamous(db, new Person(context.getString(R.string.hamsun), new LocalDate(1859, 8, 4)));
        addFamous(db, new Person(context.getString(R.string.armstrong), new LocalDate(1900, 8, 4)));
        addFamous(db, new Person(context.getString(R.string.thornton), new LocalDate(1955, 8, 4)));

//August 5
        addFamous(db, new Person(context.getString(R.string.niels_abel), new LocalDate(1802, 8, 5)));
        addFamous(db, new Person(context.getString(R.string.repin), new LocalDate(1844, 8, 5)));
        addFamous(db, new Person(context.getString(R.string.maupassant), new LocalDate(1850, 8, 5)));
        addFamous(db, new Person(context.getString(R.string.wain), new LocalDate(1860, 8, 5)));
        addFamous(db, new Person(context.getString(R.string.huston), new LocalDate(1906, 8, 5)));
        addFamous(db, new Person(context.getString(R.string.neil_armstrong), new LocalDate(1930, 8, 5)));

//August 6
        addFamous(db, new Person(context.getString(R.string.malebranche), new LocalDate(1638, 8, 6)));
        addFamous(db, new Person(context.getString(R.string.johann_bernoulli), new LocalDate(1667, 8, 6)));
        addFamous(db, new Person(context.getString(R.string.alexander_fleming), new LocalDate(1881, 8, 6)));
        addFamous(db, new Person(context.getString(R.string.lucille_ball), new LocalDate(1911, 8, 6)));
        addFamous(db, new Person(context.getString(R.string.andy_warhol), new LocalDate(1928, 8, 6)));
        addFamous(db, new Person(context.getString(R.string.shyamalan), new LocalDate(1970, 8, 6)));

//August 7
        addFamous(db, new Person(context.getString(R.string.bathory), new LocalDate(1560, 8, 7)));
        addFamous(db, new Person(context.getString(R.string.mata_hari), new LocalDate(1876, 8, 7)));
        addFamous(db, new Person(context.getString(R.string.tobin_bell), new LocalDate(1942, 8, 7)));
        addFamous(db, new Person(context.getString(R.string.duchovny), new LocalDate(1960, 8, 7)));
        addFamous(db, new Person(context.getString(R.string.jimmy_wales), new LocalDate(1966, 8, 7)));
        addFamous(db, new Person(context.getString(R.string.theron), new LocalDate(1975, 8, 7)));

//August 8
        addFamous(db, new Person(context.getString(R.string.bateson), new LocalDate(1861, 8, 8)));
        addFamous(db, new Person(context.getString(R.string.lawrence), new LocalDate(1901, 8, 8)));
        addFamous(db, new Person(context.getString(R.string.dirac), new LocalDate(1902, 8, 8)));
        addFamous(db, new Person(context.getString(R.string.dustin_hoffman), new LocalDate(1937, 8, 8)));
        addFamous(db, new Person(context.getString(R.string.federer), new LocalDate(1981, 8, 8)));

//August 9
        addFamous(db, new Person(context.getString(R.string.avogadro), new LocalDate(1776, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.morton_william), new LocalDate(1819, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.huckel), new LocalDate(1896, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.piaget), new LocalDate(1896, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.travers), new LocalDate(1899, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.jansson), new LocalDate(1914, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.griffith), new LocalDate(1957, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.houston), new LocalDate(1963, 8, 9)));
        addFamous(db, new Person(context.getString(R.string.tautou), new LocalDate(1976, 8, 9)));

//August 10
        addFamous(db, new Person(context.getString(R.string.nestle), new LocalDate(1814, 8, 10)));
        addFamous(db, new Person(context.getString(R.string.qunanbaiuli), new LocalDate(1845, 8, 10)));
        addFamous(db, new Person(context.getString(R.string.darrow), new LocalDate(1889, 8, 10)));
        addFamous(db, new Person(context.getString(R.string.shearer), new LocalDate(1902, 8, 10)));
        addFamous(db, new Person(context.getString(R.string.tiselius), new LocalDate(1902, 8, 10)));
        addFamous(db, new Person(context.getString(R.string.banderas), new LocalDate(1960, 8, 10)));

//August 11
        addFamous(db, new Person(context.getString(R.string.andrew_davis), new LocalDate(1826, 8, 11)));
        addFamous(db, new Person(context.getString(R.string.savant), new LocalDate(1946, 8, 11)));
        addFamous(db, new Person(context.getString(R.string.wozniak), new LocalDate(1950, 8, 11)));
        addFamous(db, new Person(context.getString(R.string.hogan), new LocalDate(1953, 8, 11)));
        addFamous(db, new Person(context.getString(R.string.hemsworth), new LocalDate(1983, 8, 11)));

//August 12
        addFamous(db, new Person(context.getString(R.string.bering), new LocalDate(1681, 8, 12)));
        addFamous(db, new Person(context.getString(R.string.demille), new LocalDate(1881, 8, 12)));
        addFamous(db, new Person(context.getString(R.string.bendix), new LocalDate(1881, 8, 12)));
        addFamous(db, new Person(context.getString(R.string.schrodinger), new LocalDate(1887, 8, 12)));
        addFamous(db, new Person(context.getString(R.string.soros), new LocalDate(1930, 8, 12)));
        addFamous(db, new Person(context.getString(R.string.delevingne), new LocalDate(1992, 8, 12)));

//August 13
        addFamous(db, new Person(context.getString(R.string.angstrom), new LocalDate(1814, 8, 13)));
        addFamous(db, new Person(context.getString(R.string.miescher), new LocalDate(1844, 8, 13)));
        addFamous(db, new Person(context.getString(R.string.agnelli), new LocalDate(1866, 8, 13)));
        addFamous(db, new Person(context.getString(R.string.hitchcock), new LocalDate(1899, 8, 13)));
        addFamous(db, new Person(context.getString(R.string.wankel), new LocalDate(1902, 8, 13)));
        addFamous(db, new Person(context.getString(R.string.castro), new LocalDate(1926, 8, 13)));

//August 14
        addFamous(db, new Person(context.getString(R.string.orsted), new LocalDate(1777, 8, 14)));
        addFamous(db, new Person(context.getString(R.string.holliday), new LocalDate(1851, 8, 14)));
        addFamous(db, new Person(context.getString(R.string.merezhkovsky), new LocalDate(1866, 8, 14)));
        addFamous(db, new Person(context.getString(R.string.galsworthy), new LocalDate(1867, 8, 14)));
        addFamous(db, new Person(context.getString(R.string.dempster), new LocalDate(1886, 8, 14)));
        addFamous(db, new Person(context.getString(R.string.steve_martin), new LocalDate(1945, 8, 14)));
        addFamous(db, new Person(context.getString(R.string.berry), new LocalDate(1966, 8, 14)));
        addFamous(db, new Person(context.getString(R.string.kunis), new LocalDate(1983, 8, 14)));

//August 15
        addFamous(db, new Person(context.getString(R.string.carmontelle), new LocalDate(1717, 8, 15)));
        addFamous(db, new Person(context.getString(R.string.napoleon), new LocalDate(1769, 8, 15)));
        addFamous(db, new Person(context.getString(R.string.scott), new LocalDate(1771, 8, 15)));
        addFamous(db, new Person(context.getString(R.string.broglie), new LocalDate(1892, 8, 15)));
        addFamous(db, new Person(context.getString(R.string.inarritu), new LocalDate(1963, 8, 15)));
        addFamous(db, new Person(context.getString(R.string.affleck), new LocalDate(1972, 8, 15)));
        addFamous(db, new Person(context.getString(R.string.jennifer_lawrence), new LocalDate(1990, 8, 15)));

//August 16
        addFamous(db, new Person(context.getString(R.string.bruyere), new LocalDate(1645, 8, 16)));
        addFamous(db, new Person(context.getString(R.string.lippmann), new LocalDate(1845, 8, 16)));
        addFamous(db, new Person(context.getString(R.string.bukowski), new LocalDate(1920, 8, 16)));
        addFamous(db, new Person(context.getString(R.string.richard), new LocalDate(1934, 8, 16)));
        addFamous(db, new Person(context.getString(R.string.cameron), new LocalDate(1954, 8, 16)));
        addFamous(db, new Person(context.getString(R.string.madonna), new LocalDate(1958, 8, 16)));

//August 17
        addFamous(db, new Person(context.getString(R.string.fermat), new LocalDate(1601, 8, 17)));
        addFamous(db, new Person(context.getString(R.string.hodgkin), new LocalDate(1798, 8, 17)));
        addFamous(db, new Person(context.getString(R.string.fokker), new LocalDate(1887, 8, 17)));
        addFamous(db, new Person(context.getString(R.string.naipaul), new LocalDate(1932, 8, 17)));
        addFamous(db, new Person(context.getString(R.string.de_niro), new LocalDate(1943, 8, 17)));
        addFamous(db, new Person(context.getString(R.string.penn), new LocalDate(1960, 8, 17)));

//August 18
        addFamous(db, new Person(context.getString(R.string.brook_taylor), new LocalDate(1685, 8, 18)));
        addFamous(db, new Person(context.getString(R.string.salieri), new LocalDate(1750, 8, 18)));
        addFamous(db, new Person(context.getString(R.string.pierre_martin), new LocalDate(1824, 8, 18)));
        addFamous(db, new Person(context.getString(R.string.swayze), new LocalDate(1952, 8, 18)));
        addFamous(db, new Person(context.getString(R.string.norton), new LocalDate(1969, 8, 18)));
        addFamous(db, new Person(context.getString(R.string.slater), new LocalDate(1969, 8, 18)));

//August 19
        addFamous(db, new Person(context.getString(R.string.samuel_richardson), new LocalDate(1689, 8, 19)));
        addFamous(db, new Person(context.getString(R.string.platov), new LocalDate(1753, 8, 19)));
        addFamous(db, new Person(context.getString(R.string.nasmyth), new LocalDate(1808, 8, 19)));
        addFamous(db, new Person(context.getString(R.string.meyer), new LocalDate(1830, 8, 19)));
        addFamous(db, new Person(context.getString(R.string.enescu), new LocalDate(1881, 8, 19)));
        addFamous(db, new Person(context.getString(R.string.chanel), new LocalDate(1883, 8, 19)));
        addFamous(db, new Person(context.getString(R.string.perry), new LocalDate(1969, 8, 19)));

//August 20
        addFamous(db, new Person(context.getString(R.string.berzelius), new LocalDate(1779, 8, 20)));
        addFamous(db, new Person(context.getString(R.string.quasimodo), new LocalDate(1901, 8, 20)));
        addFamous(db, new Person(context.getString(R.string.susann), new LocalDate(1918, 8, 20)));
        addFamous(db, new Person(context.getString(R.string.durst), new LocalDate(1970, 8, 20)));
        addFamous(db, new Person(context.getString(R.string.amy_adams), new LocalDate(1974, 8, 20)));
        addFamous(db, new Person(context.getString(R.string.garfield), new LocalDate(1983, 8, 20)));

//August 21
        addFamous(db, new Person(context.getString(R.string.murdoch), new LocalDate(1754, 8, 21)));
        addFamous(db, new Person(context.getString(R.string.basie), new LocalDate(1904, 8, 21)));
        addFamous(db, new Person(context.getString(R.string.consuelo_velazquez), new LocalDate(1916, 8, 21)));
        addFamous(db, new Person(context.getString(R.string.wilt_chamberlain), new LocalDate(1936, 8, 21)));
        addFamous(db, new Person(context.getString(R.string.brin), new LocalDate(1973, 8, 21)));
        addFamous(db, new Person(context.getString(R.string.bolt), new LocalDate(1986, 8, 21)));

//August 22
        addFamous(db, new Person(context.getString(R.string.papin), new LocalDate(1647, 8, 22)));
        addFamous(db, new Person(context.getString(R.string.maudslay), new LocalDate(1771, 8, 22)));
        addFamous(db, new Person(context.getString(R.string.nipkow), new LocalDate(1860, 8, 22)));
        addFamous(db, new Person(context.getString(R.string.debussy), new LocalDate(1862, 8, 22)));
        addFamous(db, new Person(context.getString(R.string.scheler), new LocalDate(1874, 8, 22)));
        addFamous(db, new Person(context.getString(R.string.bradbury), new LocalDate(1920, 8, 22)));

//August 23
        addFamous(db, new Person(context.getString(R.string.laperouse), new LocalDate(1741, 8, 23)));
        addFamous(db, new Person(context.getString(R.string.cuvier), new LocalDate(1769, 8, 23)));
        addFamous(db, new Person(context.getString(R.string.jirasek), new LocalDate(1851, 8, 23)));
        addFamous(db, new Person(context.getString(R.string.arrow), new LocalDate(1921, 8, 23)));
        addFamous(db, new Person(context.getString(R.string.phoenix), new LocalDate(1970, 8, 23)));

//August 24
        addFamous(db, new Person(context.getString(R.string.weddell), new LocalDate(1787, 8, 24)));
        addFamous(db, new Person(context.getString(R.string.borges), new LocalDate(1899, 8, 24)));
        addFamous(db, new Person(context.getString(R.string.coelho), new LocalDate(1947, 8, 24)));
        addFamous(db, new Person(context.getString(R.string.jarre), new LocalDate(1948, 8, 24)));
        addFamous(db, new Person(context.getString(R.string.fry), new LocalDate(1957, 8, 24)));
        addFamous(db, new Person(context.getString(R.string.guttenberg), new LocalDate(1958, 8, 24)));
        addFamous(db, new Person(context.getString(R.string.grint), new LocalDate(1988, 8, 24)));

//August 25
        addFamous(db, new Person(context.getString(R.string.pinkerton), new LocalDate(1819, 8, 25)));
        addFamous(db, new Person(context.getString(R.string.elo), new LocalDate(1903, 8, 25)));
        addFamous(db, new Person(context.getString(R.string.brian_moore), new LocalDate(1921, 8, 25)));
        addFamous(db, new Person(context.getString(R.string.connery), new LocalDate(1930, 8, 25)));
        addFamous(db, new Person(context.getString(R.string.tim_burton), new LocalDate(1958, 8, 25)));
        addFamous(db, new Person(context.getString(R.string.schiffer), new LocalDate(1970, 8, 25)));

//August 26
        addFamous(db, new Person(context.getString(R.string.lambert), new LocalDate(1728, 8, 26)));
        addFamous(db, new Person(context.getString(R.string.joseph_montgolfier), new LocalDate(1740, 8, 26)));
        addFamous(db, new Person(context.getString(R.string.lavoisier), new LocalDate(1743, 8, 26)));
        addFamous(db, new Person(context.getString(R.string.forest), new LocalDate(1873, 8, 26)));
        addFamous(db, new Person(context.getString(R.string.teresa), new LocalDate(1910, 8, 26)));
        addFamous(db, new Person(context.getString(R.string.culkin), new LocalDate(1980, 8, 26)));

//August 27
        addFamous(db, new Person(context.getString(R.string.hegel), new LocalDate(1770, 8, 27)));
        addFamous(db, new Person(context.getString(R.string.niebuhr), new LocalDate(1776, 8, 27)));
        addFamous(db, new Person(context.getString(R.string.bosch), new LocalDate(1874, 8, 27)));
        addFamous(db, new Person(context.getString(R.string.rolls), new LocalDate(1877, 8, 27)));
        addFamous(db, new Person(context.getString(R.string.ranevskaya), new LocalDate(1896, 8, 27)));
        addFamous(db, new Person(context.getString(R.string.chalke), new LocalDate(1976, 8, 27)));
        addFamous(db, new Person(context.getString(R.string.aaron_paul), new LocalDate(1979, 8, 27)));

//August 28
        addFamous(db, new Person(context.getString(R.string.goethe), new LocalDate(1749, 8, 28)));
        addFamous(db, new Person(context.getString(R.string.blondel), new LocalDate(1863, 8, 28)));
        addFamous(db, new Person(context.getString(R.string.whipple), new LocalDate(1878, 8, 28)));
        addFamous(db, new Person(context.getString(R.string.theremin), new LocalDate(1896, 8, 28)));
        addFamous(db, new Person(context.getString(R.string.fincher), new LocalDate(1962, 8, 28)));
        addFamous(db, new Person(context.getString(R.string.jack_black), new LocalDate(1969, 8, 28)));

//August 29
        addFamous(db, new Person(context.getString(R.string.locke), new LocalDate(1632, 8, 29)));
        addFamous(db, new Person(context.getString(R.string.maeterlinck), new LocalDate(1862, 8, 29)));
        addFamous(db, new Person(context.getString(R.string.forssmann), new LocalDate(1904, 8, 29)));
        addFamous(db, new Person(context.getString(R.string.ingrid_bergman), new LocalDate(1915, 8, 29)));
        addFamous(db, new Person(context.getString(R.string.charlie_parker), new LocalDate(1920, 8, 29)));
        addFamous(db, new Person(context.getString(R.string.michael_jackson), new LocalDate(1958, 8, 29)));

//August 30
        addFamous(db, new Person(context.getString(R.string.mary_shelley), new LocalDate(1797, 8, 30)));
        addFamous(db, new Person(context.getString(R.string.adolf_hesse), new LocalDate(1809, 8, 30)));
        addFamous(db, new Person(context.getString(R.string.hoff), new LocalDate(1852, 8, 30)));
        addFamous(db, new Person(context.getString(R.string.rutherford), new LocalDate(1871, 8, 30)));
        addFamous(db, new Person(context.getString(R.string.cummings), new LocalDate(1887, 8, 30)));
        addFamous(db, new Person(context.getString(R.string.mclaren), new LocalDate(1937, 8, 30)));
        addFamous(db, new Person(context.getString(R.string.diaz), new LocalDate(1972, 8, 30)));

//August 31
        addFamous(db, new Person(context.getString(R.string.helmholtz), new LocalDate(1821, 8, 31)));
        addFamous(db, new Person(context.getString(R.string.paneth), new LocalDate(1887, 8, 31)));
        addFamous(db, new Person(context.getString(R.string.fredric_march), new LocalDate(1897, 8, 31)));
        addFamous(db, new Person(context.getString(R.string.gere), new LocalDate(1949, 8, 31)));
        addFamous(db, new Person(context.getString(R.string.tucker), new LocalDate(1971, 8, 31)));

//September 1
        addFamous(db, new Person(context.getString(R.string.jevons), new LocalDate(1835, 9, 1)));
        addFamous(db, new Person(context.getString(R.string.auguste_forel), new LocalDate(1848, 9, 1)));
        addFamous(db, new Person(context.getString(R.string.burroughs), new LocalDate(1875, 9, 1)));
        addFamous(db, new Person(context.getString(R.string.marilyn_miller), new LocalDate(1898, 9, 1)));
        addFamous(db, new Person(context.getString(R.string.marciano), new LocalDate(1923, 9, 1)));
        addFamous(db, new Person(context.getString(R.string.estefan), new LocalDate(1957, 9, 1)));

//September 2
        addFamous(db, new Person(context.getString(R.string.howard), new LocalDate(1726, 9, 2)));
        addFamous(db, new Person(context.getString(R.string.echeverria), new LocalDate(1805, 9, 2)));
        addFamous(db, new Person(context.getString(R.string.field), new LocalDate(1850, 9, 2)));
        addFamous(db, new Person(context.getString(R.string.soddy), new LocalDate(1877, 9, 2)));
        addFamous(db, new Person(context.getString(R.string.reeves), new LocalDate(1964, 9, 2)));
        addFamous(db, new Person(context.getString(R.string.hayek), new LocalDate(1966, 9, 2)));

//September 3
        addFamous(db, new Person(context.getString(R.string.louis_sullivan), new LocalDate(1856, 9, 3)));
        addFamous(db, new Person(context.getString(R.string.pregl), new LocalDate(1869, 9, 3)));
        addFamous(db, new Person(context.getString(R.string.porsche), new LocalDate(1875, 9, 3)));
        addFamous(db, new Person(context.getString(R.string.anderson), new LocalDate(1905, 9, 3)));
        addFamous(db, new Person(context.getString(R.string.dovlatov), new LocalDate(1941, 9, 3)));
        addFamous(db, new Person(context.getString(R.string.jeunet), new LocalDate(1953, 9, 3)));
        addFamous(db, new Person(context.getString(R.string.charlie_sheen), new LocalDate(1965, 9, 3)));

//September 4
        addFamous(db, new Person(context.getString(R.string.constantijn_huygens), new LocalDate(1596, 9, 4)));
        addFamous(db, new Person(context.getString(R.string.chateaubriand), new LocalDate(1768, 9, 4)));
        addFamous(db, new Person(context.getString(R.string.richard_wright), new LocalDate(1908, 9, 4)));
        addFamous(db, new Person(context.getString(R.string.tange), new LocalDate(1913, 9, 4)));
        addFamous(db, new Person(context.getString(R.string.beyonce), new LocalDate(1981, 9, 4)));

//September 5
        addFamous(db, new Person(context.getString(R.string.campanella), new LocalDate(1568, 9, 5)));
        addFamous(db, new Person(context.getString(R.string.meyerbeer), new LocalDate(1791, 9, 5)));
        addFamous(db, new Person(context.getString(R.string.aleksey_tolstoy), new LocalDate(1817, 9, 5)));
        addFamous(db, new Person(context.getString(R.string.jesse_james), new LocalDate(1847, 9, 5)));
        addFamous(db, new Person(context.getString(R.string.mercury), new LocalDate(1946, 9, 5)));
        addFamous(db, new Person(context.getString(R.string.keaton), new LocalDate(1951, 9, 5)));

//September 6
        addFamous(db, new Person(context.getString(R.string.serlio), new LocalDate(1475, 9, 6)));
        addFamous(db, new Person(context.getString(R.string.moses_mendelssohn), new LocalDate(1729, 9, 6)));
        addFamous(db, new Person(context.getString(R.string.dalton), new LocalDate(1766, 9, 6)));
        addFamous(db, new Person(context.getString(R.string.berdan), new LocalDate(1824, 9, 6)));
        addFamous(db, new Person(context.getString(R.string.addams), new LocalDate(1860, 9, 6)));
        addFamous(db, new Person(context.getString(R.string.essen), new LocalDate(1908, 9, 6)));

//September 7
        addFamous(db, new Person(context.getString(R.string.leclerc), new LocalDate(1707, 9, 7)));
        addFamous(db, new Person(context.getString(R.string.gossen), new LocalDate(1810, 9, 7)));
        addFamous(db, new Person(context.getString(R.string.kuprin), new LocalDate(1870, 9, 7)));
        addFamous(db, new Person(context.getString(R.string.gala_dali), new LocalDate(1894, 9, 7)));
        addFamous(db, new Person(context.getString(R.string.debakey), new LocalDate(1908, 9, 7)));
        addFamous(db, new Person(context.getString(R.string.packard), new LocalDate(1912, 9, 7)));

//September 8
        addFamous(db, new Person(context.getString(R.string.lionheart), new LocalDate(1157, 9, 8)));
        addFamous(db, new Person(context.getString(R.string.neckam), new LocalDate(1157, 9, 8)));
        addFamous(db, new Person(context.getString(R.string.mistral), new LocalDate(1830, 9, 8)));
        addFamous(db, new Person(context.getString(R.string.martin_freeman), new LocalDate(1971, 9, 8)));
        addFamous(db, new Person(context.getString(R.string.pink), new LocalDate(1971, 9, 8)));
        addFamous(db, new Person(context.getString(R.string.wiz_khalifa), new LocalDate(1987, 9, 8)));

//September 9
        addFamous(db, new Person(context.getString(R.string.frederik_chapman), new LocalDate(1721, 9, 9)));
        addFamous(db, new Person(context.getString(R.string.galvani), new LocalDate(1737, 9, 9)));
        addFamous(db, new Person(context.getString(R.string.leo_tolstoy), new LocalDate(1828, 9, 9)));
        addFamous(db, new Person(context.getString(R.string.usmanov), new LocalDate(1953, 9, 9)));
        addFamous(db, new Person(context.getString(R.string.hugh_grant), new LocalDate(1960, 9, 9)));
        addFamous(db, new Person(context.getString(R.string.sandler), new LocalDate(1966, 9, 9)));

//September 10
        addFamous(db, new Person(context.getString(R.string.peirce), new LocalDate(1839, 9, 10)));
        addFamous(db, new Person(context.getString(R.string.elsa_schiaparelli), new LocalDate(1890, 9, 10)));
        addFamous(db, new Person(context.getString(R.string.compton), new LocalDate(1892, 9, 10)));
        addFamous(db, new Person(context.getString(R.string.messing), new LocalDate(1899, 9, 10)));
        addFamous(db, new Person(context.getString(R.string.lagerfeld), new LocalDate(1933, 9, 10)));
        addFamous(db, new Person(context.getString(R.string.joe_perry), new LocalDate(1950, 9, 10)));
        addFamous(db, new Person(context.getString(R.string.firth), new LocalDate(1960, 9, 10)));
        addFamous(db, new Person(context.getString(R.string.ritchie), new LocalDate(1968, 9, 10)));

//September 11
        addFamous(db, new Person(context.getString(R.string.james_thomson), new LocalDate(1700, 9, 11)));
        addFamous(db, new Person(context.getString(R.string.zeiss), new LocalDate(1816, 9, 11)));
        addFamous(db, new Person(context.getString(R.string.o_henry), new LocalDate(1862, 9, 11)));
        addFamous(db, new Person(context.getString(R.string.jeans), new LocalDate(1877, 9, 11)));
        addFamous(db, new Person(context.getString(R.string.beckenbauer), new LocalDate(1945, 9, 11)));

//September 12
        addFamous(db, new Person(context.getString(R.string.breitner), new LocalDate(1857, 9, 12)));
        addFamous(db, new Person(context.getString(R.string.irene_curie), new LocalDate(1897, 9, 12)));
        addFamous(db, new Person(context.getString(R.string.lem), new LocalDate(1921, 9, 12)));
        addFamous(db, new Person(context.getString(R.string.barry_white), new LocalDate(1944, 9, 12)));
        addFamous(db, new Person(context.getString(R.string.farmer), new LocalDate(1961, 9, 12)));
        addFamous(db, new Person(context.getString(R.string.walker), new LocalDate(1973, 9, 12)));

//September 13
        addFamous(db, new Person(context.getString(R.string.samuel_wilson), new LocalDate(1766, 9, 13)));
        addFamous(db, new Person(context.getString(R.string.reed), new LocalDate(1851, 9, 13)));
        addFamous(db, new Person(context.getString(R.string.john_priestley), new LocalDate(1894, 9, 13)));
        addFamous(db, new Person(context.getString(R.string.dahl), new LocalDate(1916, 9, 13)));
        addFamous(db, new Person(context.getString(R.string.maurice_jarre), new LocalDate(1924, 9, 13)));
        addFamous(db, new Person(context.getString(R.string.bisset), new LocalDate(1944, 9, 13)));

//September 14
        addFamous(db, new Person(context.getString(R.string.agrippa), new LocalDate(1486, 9, 14)));
        addFamous(db, new Person(context.getString(R.string.lely), new LocalDate(1618, 9, 14)));
        addFamous(db, new Person(context.getString(R.string.cecil), new LocalDate(1864, 9, 14)));
        addFamous(db, new Person(context.getString(R.string.dana_gibson), new LocalDate(1867, 9, 14)));
        addFamous(db, new Person(context.getString(R.string.neill), new LocalDate(1947, 9, 14)));
        addFamous(db, new Person(context.getString(R.string.winehouse), new LocalDate(1983, 9, 14)));

//September 15
        addFamous(db, new Person(context.getString(R.string.marco_polo), new LocalDate(1254, 9, 15)));
        addFamous(db, new Person(context.getString(R.string.james_cooper), new LocalDate(1789, 9, 15)));
        addFamous(db, new Person(context.getString(R.string.bugatti), new LocalDate(1881, 9, 15)));
        addFamous(db, new Person(context.getString(R.string.christie), new LocalDate(1890, 9, 15)));
        addFamous(db, new Person(context.getString(R.string.jean_renoir), new LocalDate(1894, 9, 15)));
        addFamous(db, new Person(context.getString(R.string.tommy_lee_jones), new LocalDate(1946, 9, 15)));
        addFamous(db, new Person(context.getString(R.string.oliver_stone), new LocalDate(1946, 9, 15)));
        addFamous(db, new Person(context.getString(R.string.tom_hardy), new LocalDate(1977, 9, 15)));

//September 16
        addFamous(db, new Person(context.getString(R.string.kossel), new LocalDate(1853, 9, 16)));
        addFamous(db, new Person(context.getString(R.string.boyd), new LocalDate(1887, 9, 16)));
        addFamous(db, new Person(context.getString(R.string.jellinek), new LocalDate(1889, 9, 16)));
        addFamous(db, new Person(context.getString(R.string.korda), new LocalDate(1893, 9, 16)));
        addFamous(db, new Person(context.getString(R.string.bbking), new LocalDate(1925, 9, 16)));
        addFamous(db, new Person(context.getString(R.string.rourke), new LocalDate(1952, 9, 16)));
        addFamous(db, new Person(context.getString(R.string.copperfield), new LocalDate(1956, 9, 16)));

//September 17
        addFamous(db, new Person(context.getString(R.string.riemann), new LocalDate(1826, 9, 17)));
        addFamous(db, new Person(context.getString(R.string.buick), new LocalDate(1854, 9, 17)));
        addFamous(db, new Person(context.getString(R.string.tsiolkovsky), new LocalDate(1857, 9, 17)));
        addFamous(db, new Person(context.getString(R.string.kesey), new LocalDate(1935, 9, 17)));
        addFamous(db, new Person(context.getString(R.string.messner), new LocalDate(1944, 9, 17)));
        addFamous(db, new Person(context.getString(R.string.anastacia), new LocalDate(1968, 9, 17)));
        addFamous(db, new Person(context.getString(R.string.ovechkin), new LocalDate(1985, 9, 17)));

//September 18
        addFamous(db, new Person(context.getString(R.string.samuel_johnson), new LocalDate(1709, 9, 18)));
        addFamous(db, new Person(context.getString(R.string.foucault), new LocalDate(1819, 9, 18)));
        addFamous(db, new Person(context.getString(R.string.garbo), new LocalDate(1905, 9, 18)));
        addFamous(db, new Person(context.getString(R.string.mcmillan), new LocalDate(1907, 9, 18)));
        addFamous(db, new Person(context.getString(R.string.werber), new LocalDate(1961, 9, 18)));
        addFamous(db, new Person(context.getString(R.string.gandolfini), new LocalDate(1961, 9, 18)));
        addFamous(db, new Person(context.getString(R.string.shuttleworth), new LocalDate(1973, 9, 18)));

//September 19
        addFamous(db, new Person(context.getString(R.string.pajou), new LocalDate(1730, 9, 19)));
        addFamous(db, new Person(context.getString(R.string.golding), new LocalDate(1911, 9, 19)));
        addFamous(db, new Person(context.getString(R.string.irons), new LocalDate(1948, 9, 19)));
        addFamous(db, new Person(context.getString(R.string.hornby), new LocalDate(1949, 9, 19)));
        addFamous(db, new Person(context.getString(R.string.karelin), new LocalDate(1967, 9, 19)));

//September 20
        addFamous(db, new Person(context.getString(R.string.moneta), new LocalDate(1833, 9, 20)));
        addFamous(db, new Person(context.getString(R.string.dewar), new LocalDate(1842, 9, 20)));
        addFamous(db, new Person(context.getString(R.string.leo_strauss), new LocalDate(1899, 9, 20)));
        addFamous(db, new Person(context.getString(R.string.loren), new LocalDate(1934, 9, 20)));
        addFamous(db, new Person(context.getString(R.string.george_martin), new LocalDate(1948, 9, 20)));

//September 21
        addFamous(db, new Person(context.getString(R.string.mcadam), new LocalDate(1756, 9, 21)));
        addFamous(db, new Person(context.getString(R.string.onnes), new LocalDate(1853, 9, 21)));
        addFamous(db, new Person(context.getString(R.string.wells), new LocalDate(1866, 9, 21)));
        addFamous(db, new Person(context.getString(R.string.nicolle), new LocalDate(1866, 9, 21)));
        addFamous(db, new Person(context.getString(R.string.stephen_king), new LocalDate(1947, 9, 21)));
        addFamous(db, new Person(context.getString(R.string.murray), new LocalDate(1950, 9, 21)));
        addFamous(db, new Person(context.getString(R.string.beigbeder), new LocalDate(1965, 9, 21)));

//September 22
        addFamous(db, new Person(context.getString(R.string.faraday), new LocalDate(1791, 9, 22)));
        addFamous(db, new Person(context.getString(R.string.george_bentham), new LocalDate(1800, 9, 22)));
        addFamous(db, new Person(context.getString(R.string.ciurlionis), new LocalDate(1875, 9, 22)));
        addFamous(db, new Person(context.getString(R.string.muni), new LocalDate(1895, 9, 22)));
        addFamous(db, new Person(context.getString(R.string.huggins), new LocalDate(1901, 9, 22)));
        addFamous(db, new Person(context.getString(R.string.dean_reed), new LocalDate(1938, 9, 22)));

//September 23
        addFamous(db, new Person(context.getString(R.string.fizeau), new LocalDate(1819, 9, 23)));
        addFamous(db, new Person(context.getString(R.string.robert_bosch), new LocalDate(1861, 9, 23)));
        addFamous(db, new Person(context.getString(R.string.orr), new LocalDate(1880, 9, 23)));
        addFamous(db, new Person(context.getString(R.string.coltrane), new LocalDate(1926, 9, 23)));
        addFamous(db, new Person(context.getString(R.string.romy_schneider), new LocalDate(1938, 9, 23)));
        addFamous(db, new Person(context.getString(R.string.julio_iglesias), new LocalDate(1943, 9, 23)));
        addFamous(db, new Person(context.getString(R.string.springsteen), new LocalDate(1949, 9, 23)));

//September 24
        addFamous(db, new Person(context.getString(R.string.cardano), new LocalDate(1501, 9, 24)));
        addFamous(db, new Person(context.getString(R.string.walpole), new LocalDate(1717, 9, 24)));
        addFamous(db, new Person(context.getString(R.string.triolet), new LocalDate(1896, 9, 24)));
        addFamous(db, new Person(context.getString(R.string.f_s_fitzgerald), new LocalDate(1896, 9, 24)));
        addFamous(db, new Person(context.getString(R.string.ochoa), new LocalDate(1905, 9, 24)));
        addFamous(db, new Person(context.getString(R.string.brunner), new LocalDate(1934, 9, 24)));

//September 25
        addFamous(db, new Person(context.getString(R.string.thomas_morgan), new LocalDate(1866, 9, 25)));
        addFamous(db, new Person(context.getString(R.string.faulkner), new LocalDate(1897, 9, 25)));
        addFamous(db, new Person(context.getString(R.string.shostakovich), new LocalDate(1906, 9, 25)));
        addFamous(db, new Person(context.getString(R.string.michael_douglas), new LocalDate(1944, 9, 25)));
        addFamous(db, new Person(context.getString(R.string.almodovar), new LocalDate(1949, 9, 25)));
        addFamous(db, new Person(context.getString(R.string.will_smith), new LocalDate(1968, 9, 25)));
        addFamous(db, new Person(context.getString(R.string.zeta_jones), new LocalDate(1969, 9, 25)));

//September 26
        addFamous(db, new Person(context.getString(R.string.grew), new LocalDate(1641, 9, 26)));
        addFamous(db, new Person(context.getString(R.string.joseph_proust), new LocalDate(1754, 9, 26)));
        addFamous(db, new Person(context.getString(R.string.pavlov), new LocalDate(1849, 9, 26)));
        addFamous(db, new Person(context.getString(R.string.hine), new LocalDate(1874, 9, 26)));
        addFamous(db, new Person(context.getString(R.string.wallis), new LocalDate(1887, 9, 26)));
        addFamous(db, new Person(context.getString(R.string.eliot), new LocalDate(1888, 9, 26)));

//September 27
        addFamous(db, new Person(context.getString(R.string.bossuet), new LocalDate(1628, 9, 27)));
        addFamous(db, new Person(context.getString(R.string.deledda), new LocalDate(1871, 9, 27)));
        addFamous(db, new Person(context.getString(R.string.larry_wall), new LocalDate(1954, 9, 27)));
        addFamous(db, new Person(context.getString(R.string.welsh), new LocalDate(1958, 9, 27)));
        addFamous(db, new Person(context.getString(R.string.paltrow), new LocalDate(1972, 9, 27)));
        addFamous(db, new Person(context.getString(R.string.wayne), new LocalDate(1982, 9, 27)));

//September 28
        addFamous(db, new Person(context.getString(R.string.merimee), new LocalDate(1803, 9, 28)));
        addFamous(db, new Person(context.getString(R.string.moissan), new LocalDate(1852, 9, 28)));
        addFamous(db, new Person(context.getString(R.string.finch), new LocalDate(1916, 9, 28)));
        addFamous(db, new Person(context.getString(R.string.mastroianni), new LocalDate(1924, 9, 28)));
        addFamous(db, new Person(context.getString(R.string.bardot), new LocalDate(1934, 9, 28)));
        addFamous(db, new Person(context.getString(R.string.watts), new LocalDate(1968, 9, 28)));
        addFamous(db, new Person(context.getString(R.string.dita_von_teese), new LocalDate(1972, 9, 28)));
        addFamous(db, new Person(context.getString(R.string.emelianenko), new LocalDate(1976, 9, 28)));

//September 29
        addFamous(db, new Person(context.getString(R.string.caravaggio), new LocalDate(1571, 9, 29)));
        addFamous(db, new Person(context.getString(R.string.horatio_nelson), new LocalDate(1758, 9, 29)));
        addFamous(db, new Person(context.getString(R.string.gaskell), new LocalDate(1810, 9, 29)));
        addFamous(db, new Person(context.getString(R.string.fermi), new LocalDate(1901, 9, 29)));
        addFamous(db, new Person(context.getString(R.string.ostrovsky), new LocalDate(1904, 9, 29)));
        addFamous(db, new Person(context.getString(R.string.antonioni), new LocalDate(1912, 9, 29)));

//September 30
        addFamous(db, new Person(context.getString(R.string.condillac), new LocalDate(1714, 9, 30)));
        addFamous(db, new Person(context.getString(R.string.wrigley), new LocalDate(1861, 9, 30)));
        addFamous(db, new Person(context.getString(R.string.perrin), new LocalDate(1870, 9, 30)));
        addFamous(db, new Person(context.getString(R.string.geiger), new LocalDate(1882, 9, 30)));
        addFamous(db, new Person(context.getString(R.string.kerr), new LocalDate(1921, 9, 30)));
        addFamous(db, new Person(context.getString(R.string.capote), new LocalDate(1924, 9, 30)));
        addFamous(db, new Person(context.getString(R.string.bellucci), new LocalDate(1964, 9, 30)));
        addFamous(db, new Person(context.getString(R.string.cotillard), new LocalDate(1975, 9, 30)));

//October 1
        addFamous(db, new Person(context.getString(R.string.boeing), new LocalDate(1881, 10, 1)));
        addFamous(db, new Person(context.getString(R.string.richard_harris), new LocalDate(1930, 10, 1)));
        addFamous(db, new Person(context.getString(R.string.andrews), new LocalDate(1935, 10, 1)));
        addFamous(db, new Person(context.getString(R.string.annaud), new LocalDate(1943, 10, 1)));
        addFamous(db, new Person(context.getString(R.string.galifianakis), new LocalDate(1969, 10, 1)));
        addFamous(db, new Person(context.getString(R.string.brie_larson), new LocalDate(1989, 10, 1)));

//October 2
        addFamous(db, new Person(context.getString(R.string.ramsay), new LocalDate(1852, 10, 2)));
        addFamous(db, new Person(context.getString(R.string.gandhi), new LocalDate(1869, 10, 2)));
        addFamous(db, new Person(context.getString(R.string.greene), new LocalDate(1904, 10, 2)));
        addFamous(db, new Person(context.getString(R.string.willy_ley), new LocalDate(1906, 10, 2)));
        addFamous(db, new Person(context.getString(R.string.karan), new LocalDate(1948, 10, 2)));
        addFamous(db, new Person(context.getString(R.string.sting), new LocalDate(1951, 10, 2)));

//October 3
        addFamous(db, new Person(context.getString(R.string.shmelyov), new LocalDate(1873, 10, 3)));
        addFamous(db, new Person(context.getString(R.string.yesenin), new LocalDate(1895, 10, 3)));
        addFamous(db, new Person(context.getString(R.string.aragon), new LocalDate(1897, 10, 3)));
        addFamous(db, new Person(context.getString(R.string.wolfe), new LocalDate(1900, 10, 3)));
        addFamous(db, new Person(context.getString(R.string.stefani), new LocalDate(1969, 10, 3)));
        addFamous(db, new Person(context.getString(R.string.headey), new LocalDate(1973, 10, 3)));
        addFamous(db, new Person(context.getString(R.string.ibrahimovic), new LocalDate(1981, 10, 3)));
        addFamous(db, new Person(context.getString(R.string.vikander), new LocalDate(1988, 10, 3)));

//October 4
        addFamous(db, new Person(context.getString(R.string.piranesi), new LocalDate(1720, 10, 4)));
        addFamous(db, new Person(context.getString(R.string.pottier), new LocalDate(1816, 10, 4)));
        addFamous(db, new Person(context.getString(R.string.boussenard), new LocalDate(1847, 10, 4)));
        addFamous(db, new Person(context.getString(R.string.sarandon), new LocalDate(1946, 10, 4)));
        addFamous(db, new Person(context.getString(R.string.waltz), new LocalDate(1956, 10, 4)));
        addFamous(db, new Person(context.getString(R.string.silverstone), new LocalDate(1976, 10, 4)));
        addFamous(db, new Person(context.getString(R.string.dakota_johnson), new LocalDate(1989, 10, 4)));

//October 5
        addFamous(db, new Person(context.getString(R.string.diderot), new LocalDate(1713, 10, 5)));
        addFamous(db, new Person(context.getString(R.string.lumiere), new LocalDate(1864, 10, 5)));
        addFamous(db, new Person(context.getString(R.string.rous), new LocalDate(1879, 10, 5)));
        addFamous(db, new Person(context.getString(R.string.kroc), new LocalDate(1902, 10, 5)));
        addFamous(db, new Person(context.getString(R.string.lemieux), new LocalDate(1965, 10, 5)));
        addFamous(db, new Person(context.getString(R.string.pearce), new LocalDate(1967, 10, 5)));
        addFamous(db, new Person(context.getString(R.string.winslet), new LocalDate(1975, 10, 5)));
        addFamous(db, new Person(context.getString(R.string.eisenberg), new LocalDate(1983, 10, 5)));

//October 6
        addFamous(db, new Person(context.getString(R.string.maskelyne), new LocalDate(1732, 10, 6)));
        addFamous(db, new Person(context.getString(R.string.smuglewicz), new LocalDate(1745, 10, 6)));
        addFamous(db, new Person(context.getString(R.string.westinghouse), new LocalDate(1846, 10, 6)));
        addFamous(db, new Person(context.getString(R.string.fessenden), new LocalDate(1866, 10, 6)));
        addFamous(db, new Person(context.getString(R.string.corbusier), new LocalDate(1887, 10, 6)));
        addFamous(db, new Person(context.getString(R.string.ernest_walton), new LocalDate(1903, 10, 6)));
        addFamous(db, new Person(context.getString(R.string.heyerdahl), new LocalDate(1914, 10, 6)));

//October 7
        addFamous(db, new Person(context.getString(R.string.niels_bohr), new LocalDate(1885, 10, 7)));
        addFamous(db, new Person(context.getString(R.string.alcantara), new LocalDate(1896, 10, 7)));
        addFamous(db, new Person(context.getString(R.string.keneally), new LocalDate(1935, 10, 7)));
        addFamous(db, new Person(context.getString(R.string.putin), new LocalDate(1952, 10, 7)));
        addFamous(db, new Person(context.getString(R.string.braxton), new LocalDate(1967, 10, 7)));

//October 8
        addFamous(db, new Person(context.getString(R.string.geyter), new LocalDate(1848, 10, 8)));
        addFamous(db, new Person(context.getString(R.string.poddubny), new LocalDate(1871, 10, 8)));
        addFamous(db, new Person(context.getString(R.string.tsvetaeva), new LocalDate(1892, 10, 8)));
        addFamous(db, new Person(context.getString(R.string.voicu), new LocalDate(1923, 10, 8)));
        addFamous(db, new Person(context.getString(R.string.louise_hay), new LocalDate(1926, 10, 8)));
        addFamous(db, new Person(context.getString(R.string.weaver), new LocalDate(1949, 10, 8)));
        addFamous(db, new Person(context.getString(R.string.matt_damon), new LocalDate(1970, 10, 8)));

//October 9
        addFamous(db, new Person(context.getString(R.string.sorbon), new LocalDate(1201, 10, 9)));
        addFamous(db, new Person(context.getString(R.string.segner), new LocalDate(1704, 10, 9)));
        addFamous(db, new Person(context.getString(R.string.saint_saens), new LocalDate(1835, 10, 9)));
        addFamous(db, new Person(context.getString(R.string.lennon), new LocalDate(1940, 10, 9)));
        addFamous(db, new Person(context.getString(R.string.mcqueen), new LocalDate(1969, 10, 9)));

//October 10
        addFamous(db, new Person(context.getString(R.string.watteau), new LocalDate(1684, 10, 10)));
        addFamous(db, new Person(context.getString(R.string.cavendish), new LocalDate(1731, 10, 10)));
        addFamous(db, new Person(context.getString(R.string.verdi), new LocalDate(1813, 10, 10)));
        addFamous(db, new Person(context.getString(R.string.nansen), new LocalDate(1861, 10, 10)));
        addFamous(db, new Person(context.getString(R.string.andric), new LocalDate(1892, 10, 10)));
        addFamous(db, new Person(context.getString(R.string.giacometti), new LocalDate(1901, 10, 10)));
        addFamous(db, new Person(context.getString(R.string.pavel_durov), new LocalDate(1984, 10, 10)));

//October 11
        addFamous(db, new Person(context.getString(R.string.olbers), new LocalDate(1758, 10, 11)));
        addFamous(db, new Person(context.getString(R.string.berlier), new LocalDate(1841, 10, 11)));
        addFamous(db, new Person(context.getString(R.string.heinz), new LocalDate(1844, 10, 11)));
        addFamous(db, new Person(context.getString(R.string.roosevelt), new LocalDate(1884, 10, 11)));
        addFamous(db, new Person(context.getString(R.string.mauriac), new LocalDate(1885, 10, 11)));

//October 12
        addFamous(db, new Person(context.getString(R.string.sperry), new LocalDate(1860, 10, 12)));
        addFamous(db, new Person(context.getString(R.string.harden), new LocalDate(1865, 10, 12)));
        addFamous(db, new Person(context.getString(R.string.horch), new LocalDate(1868, 10, 12)));
        addFamous(db, new Person(context.getString(R.string.crowley), new LocalDate(1875, 10, 12)));
        addFamous(db, new Person(context.getString(R.string.montale), new LocalDate(1896, 10, 12)));
        addFamous(db, new Person(context.getString(R.string.pavarotti), new LocalDate(1935, 10, 12)));
        addFamous(db, new Person(context.getString(R.string.jackman), new LocalDate(1968, 10, 12)));

//October 13
        addFamous(db, new Person(context.getString(R.string.tatum), new LocalDate(1909, 10, 13)));
        addFamous(db, new Person(context.getString(R.string.thatcher), new LocalDate(1925, 10, 13)));
        addFamous(db, new Person(context.getString(R.string.hunter), new LocalDate(1941, 10, 13)));
        addFamous(db, new Person(context.getString(R.string.simon), new LocalDate(1941, 10, 13)));
        addFamous(db, new Person(context.getString(R.string.cohen), new LocalDate(1971, 10, 13)));

//October 14
        addFamous(db, new Person(context.getString(R.string.william_penn), new LocalDate(1644, 10, 14)));
        addFamous(db, new Person(context.getString(R.string.gish), new LocalDate(1893, 10, 14)));
        addFamous(db, new Person(context.getString(R.string.roger_moore), new LocalDate(1927, 10, 14)));
        addFamous(db, new Person(context.getString(R.string.lauren), new LocalDate(1939, 10, 14)));
        addFamous(db, new Person(context.getString(R.string.wasikowska), new LocalDate(1989, 10, 14)));

//October 15
        addFamous(db, new Person(context.getString(R.string.torricelli), new LocalDate(1608, 10, 15)));
        addFamous(db, new Person(context.getString(R.string.lermontov), new LocalDate(1814, 10, 15)));
        addFamous(db, new Person(context.getString(R.string.asaph_hall), new LocalDate(1829, 10, 15)));
        addFamous(db, new Person(context.getString(R.string.nietzsche), new LocalDate(1844, 10, 15)));
        addFamous(db, new Person(context.getString(R.string.ilf), new LocalDate(1897, 10, 15)));
        addFamous(db, new Person(context.getString(R.string.puzo), new LocalDate(1920, 10, 15)));
        addFamous(db, new Person(context.getString(R.string.fm_2030), new LocalDate(1930, 10, 15)));

//October 16
        addFamous(db, new Person(context.getString(R.string.haller), new LocalDate(1708, 10, 16)));
        addFamous(db, new Person(context.getString(R.string.wilde), new LocalDate(1854, 10, 16)));
        addFamous(db, new Person(context.getString(R.string.oneill), new LocalDate(1888, 10, 16)));
        addFamous(db, new Person(context.getString(R.string.grass), new LocalDate(1927, 10, 16)));
        addFamous(db, new Person(context.getString(R.string.paffgen), new LocalDate(1938, 10, 16)));
        addFamous(db, new Person(context.getString(R.string.robbins), new LocalDate(1958, 10, 16)));

//October 17
        addFamous(db, new Person(context.getString(R.string.orlov), new LocalDate(1734, 10, 17)));
        addFamous(db, new Person(context.getString(R.string.saint_simon), new LocalDate(1760, 10, 17)));
        addFamous(db, new Person(context.getString(R.string.jordan), new LocalDate(1948, 10, 17)));
        addFamous(db, new Person(context.getString(R.string.eminem), new LocalDate(1972, 10, 17)));
        addFamous(db, new Person(context.getString(R.string.raikkonen), new LocalDate(1979, 10, 17)));
        addFamous(db, new Person(context.getString(R.string.felicity_jones), new LocalDate(1983, 10, 17)));

//October 18
        addFamous(db, new Person(context.getString(R.string.schonbein), new LocalDate(1799, 10, 18)));
        addFamous(db, new Person(context.getString(R.string.glumer), new LocalDate(1825, 10, 18)));
        addFamous(db, new Person(context.getString(R.string.lodygin), new LocalDate(1847, 10, 18)));
        addFamous(db, new Person(context.getString(R.string.bergson), new LocalDate(1859, 10, 18)));
        addFamous(db, new Person(context.getString(R.string.chuck_berry), new LocalDate(1926, 10, 18)));
        addFamous(db, new Person(context.getString(R.string.george_scott), new LocalDate(1927, 10, 18)));
        addFamous(db, new Person(context.getString(R.string.van_damme), new LocalDate(1960, 10, 18)));

//October 19
        addFamous(db, new Person(context.getString(R.string.ficino), new LocalDate(1433, 10, 19)));
        addFamous(db, new Person(context.getString(R.string.auguste_lumiere), new LocalDate(1862, 10, 19)));
        addFamous(db, new Person(context.getString(R.string.boccioni), new LocalDate(1882, 10, 19)));
        addFamous(db, new Person(context.getString(R.string.gilels), new LocalDate(1916, 10, 19)));
        addFamous(db, new Person(context.getString(R.string.holyfield), new LocalDate(1962, 10, 19)));
        addFamous(db, new Person(context.getString(R.string.trey_parker), new LocalDate(1969, 10, 19)));

//October 20
        addFamous(db, new Person(context.getString(R.string.bartholin), new LocalDate(1616, 10, 20)));
        addFamous(db, new Person(context.getString(R.string.wren), new LocalDate(1632, 10, 20)));
        addFamous(db, new Person(context.getString(R.string.rimbaud), new LocalDate(1854, 10, 20)));
        addFamous(db, new Person(context.getString(R.string.chadwick), new LocalDate(1891, 10, 20)));
        addFamous(db, new Person(context.getString(R.string.bernat), new LocalDate(1923, 10, 20)));
        addFamous(db, new Person(context.getString(R.string.jelinek), new LocalDate(1946, 10, 20)));
        addFamous(db, new Person(context.getString(R.string.snoop_dogg), new LocalDate(1971, 10, 20)));

//October 21
        addFamous(db, new Person(context.getString(R.string.coleridge), new LocalDate(1772, 10, 21)));
        addFamous(db, new Person(context.getString(R.string.nobel), new LocalDate(1833, 10, 21)));
        addFamous(db, new Person(context.getString(R.string.mikhalkov), new LocalDate(1945, 10, 21)));
        addFamous(db, new Person(context.getString(R.string.carrie_fisher), new LocalDate(1956, 10, 21)));
        addFamous(db, new Person(context.getString(R.string.geim), new LocalDate(1958, 10, 21)));
        addFamous(db, new Person(context.getString(R.string.kardashian), new LocalDate(1980, 10, 21)));

//October 22
        addFamous(db, new Person(context.getString(R.string.liszt), new LocalDate(1811, 10, 22)));
        addFamous(db, new Person(context.getString(R.string.bernhardt), new LocalDate(1844, 10, 22)));
        addFamous(db, new Person(context.getString(R.string.bunin), new LocalDate(1870, 10, 22)));
        addFamous(db, new Person(context.getString(R.string.yashin), new LocalDate(1929, 10, 22)));
        addFamous(db, new Person(context.getString(R.string.christopher_lloyd), new LocalDate(1938, 10, 22)));
        addFamous(db, new Person(context.getString(R.string.deneuve), new LocalDate(1943, 10, 22)));
        addFamous(db, new Person(context.getString(R.string.wenger), new LocalDate(1949, 10, 22)));

//October 23
        addFamous(db, new Person(context.getString(R.string.larousse), new LocalDate(1817, 10, 23)));
        addFamous(db, new Person(context.getString(R.string.lanchester), new LocalDate(1868, 10, 23)));
        addFamous(db, new Person(context.getString(R.string.lewis), new LocalDate(1875, 10, 23)));
        addFamous(db, new Person(context.getString(R.string.bloch), new LocalDate(1905, 10, 23)));
        addFamous(db, new Person(context.getString(R.string.pele), new LocalDate(1940, 10, 23)));
        addFamous(db, new Person(context.getString(R.string.reynolds), new LocalDate(1976, 10, 23)));
        addFamous(db, new Person(context.getString(R.string.clarke), new LocalDate(1986, 10, 23)));

//October 24
        addFamous(db, new Person(context.getString(R.string.robbia), new LocalDate(1435, 10, 24)));
        addFamous(db, new Person(context.getString(R.string.leeuwenhoek), new LocalDate(1632, 10, 24)));
        addFamous(db, new Person(context.getString(R.string.wilhelm_weber), new LocalDate(1804, 10, 24)));
        addFamous(db, new Person(context.getString(R.string.swarovski), new LocalDate(1862, 10, 24)));
        addFamous(db, new Person(context.getString(R.string.raikin), new LocalDate(1911, 10, 24)));
        addFamous(db, new Person(context.getString(R.string.rooney), new LocalDate(1985, 10, 24)));
        addFamous(db, new Person(context.getString(R.string.drake), new LocalDate(1986, 10, 24)));

//October 25
        addFamous(db, new Person(context.getString(R.string.galois), new LocalDate(1811, 10, 25)));
        addFamous(db, new Person(context.getString(R.string.johann_strauss), new LocalDate(1825, 10, 25)));
        addFamous(db, new Person(context.getString(R.string.bizet), new LocalDate(1838, 10, 25)));
        addFamous(db, new Person(context.getString(R.string.picasso), new LocalDate(1881, 10, 25)));
        addFamous(db, new Person(context.getString(R.string.gance), new LocalDate(1889, 10, 25)));
        addFamous(db, new Person(context.getString(R.string.katy_perry), new LocalDate(1984, 10, 25)));

//October 26
        addFamous(db, new Person(context.getString(R.string.scarlatti), new LocalDate(1685, 10, 26)));
        addFamous(db, new Person(context.getString(R.string.goldschmidt), new LocalDate(1819, 10, 26)));
        addFamous(db, new Person(context.getString(R.string.vereshchagin), new LocalDate(1842, 10, 26)));
        addFamous(db, new Person(context.getString(R.string.bely), new LocalDate(1880, 10, 26)));
        addFamous(db, new Person(context.getString(R.string.napoleon_hill), new LocalDate(1883, 10, 26)));

//October 27
        addFamous(db, new Person(context.getString(R.string.paganini), new LocalDate(1782, 10, 27)));
        addFamous(db, new Person(context.getString(R.string.falk), new LocalDate(1886, 10, 27)));
        addFamous(db, new Person(context.getString(R.string.cleese), new LocalDate(1939, 10, 27)));
        addFamous(db, new Person(context.getString(R.string.simon_le_bon), new LocalDate(1958, 10, 27)));
        addFamous(db, new Person(context.getString(R.string.vanessa_mae), new LocalDate(1978, 10, 27)));

//October 28
        addFamous(db, new Person(context.getString(R.string.edith_head), new LocalDate(1897, 10, 28)));
        addFamous(db, new Person(context.getString(R.string.waugh), new LocalDate(1903, 10, 28)));
        addFamous(db, new Person(context.getString(R.string.garrincha), new LocalDate(1933, 10, 28)));
        addFamous(db, new Person(context.getString(R.string.bill_gates), new LocalDate(1955, 10, 28)));
        addFamous(db, new Person(context.getString(R.string.ramazzotti), new LocalDate(1963, 10, 28)));
        addFamous(db, new Person(context.getString(R.string.julia_roberts), new LocalDate(1967, 10, 28)));
        addFamous(db, new Person(context.getString(R.string.joaquin_phoenix), new LocalDate(1974, 10, 28)));

//October 29
        addFamous(db, new Person(context.getString(R.string.stur), new LocalDate(1815, 10, 29)));
        addFamous(db, new Person(context.getString(R.string.ioffe), new LocalDate(1880, 10, 29)));
        addFamous(db, new Person(context.getString(R.string.phalle), new LocalDate(1930, 10, 29)));
        addFamous(db, new Person(context.getString(R.string.dreyfuss), new LocalDate(1947, 10, 29)));
        addFamous(db, new Person(context.getString(R.string.ryder), new LocalDate(1971, 10, 29)));

//October 30
        addFamous(db, new Person(context.getString(R.string.kauffmann), new LocalDate(1741, 10, 30)));
        addFamous(db, new Person(context.getString(R.string.sheridan), new LocalDate(1751, 10, 30)));
        addFamous(db, new Person(context.getString(R.string.chenier), new LocalDate(1762, 10, 30)));
        addFamous(db, new Person(context.getString(R.string.valery), new LocalDate(1871, 10, 30)));
        addFamous(db, new Person(context.getString(R.string.maradona), new LocalDate(1960, 10, 30)));
        addFamous(db, new Person(context.getString(R.string.belleci), new LocalDate(1970, 10, 30)));

//October 31
        addFamous(db, new Person(context.getString(R.string.vermeer), new LocalDate(1632, 10, 31)));
        addFamous(db, new Person(context.getString(R.string.keats), new LocalDate(1795, 10, 31)));
        addFamous(db, new Person(context.getString(R.string.weierstrass), new LocalDate(1815, 10, 31)));
        addFamous(db, new Person(context.getString(R.string.baeyer), new LocalDate(1835, 10, 31)));
        addFamous(db, new Person(context.getString(R.string.helmut_newton), new LocalDate(1920, 10, 31)));
        addFamous(db, new Person(context.getString(R.string.peter_jackson), new LocalDate(1961, 10, 31)));
        addFamous(db, new Person(context.getString(R.string.rob_schneider), new LocalDate(1963, 10, 31)));

//November 1
        addFamous(db, new Person(context.getString(R.string.cortona), new LocalDate(1596, 11, 1)));
        addFamous(db, new Person(context.getString(R.string.canova), new LocalDate(1757, 11, 1)));
        addFamous(db, new Person(context.getString(R.string.grieg), new LocalDate(1902, 11, 1)));
        addFamous(db, new Person(context.getString(R.string.flynt), new LocalDate(1942, 11, 1)));
        addFamous(db, new Person(context.getString(R.string.kiedis), new LocalDate(1962, 11, 1)));
        addFamous(db, new Person(context.getString(R.string.rai), new LocalDate(1973, 11, 1)));

//November 2
        addFamous(db, new Person(context.getString(R.string.antoinette), new LocalDate(1755, 11, 2)));
        addFamous(db, new Person(context.getString(R.string.boole), new LocalDate(1815, 11, 2)));
        addFamous(db, new Person(context.getString(R.string.sorel), new LocalDate(1847, 11, 2)));
        addFamous(db, new Person(context.getString(R.string.visconti), new LocalDate(1906, 11, 2)));
        addFamous(db, new Person(context.getString(R.string.keith_emerson), new LocalDate(1944, 11, 2)));
        addFamous(db, new Person(context.getString(R.string.khan), new LocalDate(1965, 11, 2)));
        addFamous(db, new Person(context.getString(R.string.schwimmer), new LocalDate(1966, 11, 2)));

//November 3
        addFamous(db, new Person(context.getString(R.string.cellini), new LocalDate(1500, 11, 3)));
        addFamous(db, new Person(context.getString(R.string.marshak), new LocalDate(1887, 11, 3)));
        addFamous(db, new Person(context.getString(R.string.dassler), new LocalDate(1900, 11, 3)));
        addFamous(db, new Person(context.getString(R.string.gerd_muller), new LocalDate(1945, 11, 3)));
        addFamous(db, new Person(context.getString(R.string.lundgren), new LocalDate(1957, 11, 3)));
        addFamous(db, new Person(context.getString(R.string.newell), new LocalDate(1962, 11, 3)));

//November 4
        addFamous(db, new Person(context.getString(R.string.reni), new LocalDate(1575, 11, 4)));
        addFamous(db, new Person(context.getString(R.string.bove), new LocalDate(1784, 11, 4)));
        addFamous(db, new Person(context.getString(R.string.shakurantala_devi), new LocalDate(1929, 11, 4)));
        addFamous(db, new Person(context.getString(R.string.mcconaughey), new LocalDate(1969, 11, 4)));
        addFamous(db, new Person(context.getString(R.string.figo), new LocalDate(1972, 11, 4)));

//November 5
        addFamous(db, new Person(context.getString(R.string.petrov_vodkin), new LocalDate(1878, 11, 5)));
        addFamous(db, new Person(context.getString(R.string.leigh), new LocalDate(1913, 11, 5)));
        addFamous(db, new Person(context.getString(R.string.dassin), new LocalDate(1938, 11, 5)));
        addFamous(db, new Person(context.getString(R.string.patrick), new LocalDate(1958, 11, 5)));
        addFamous(db, new Person(context.getString(R.string.bryan_adams), new LocalDate(1959, 11, 5)));
        addFamous(db, new Person(context.getString(R.string.swinton), new LocalDate(1960, 11, 5)));

//November 6
        addFamous(db, new Person(context.getString(R.string.sax), new LocalDate(1814, 11, 6)));
        addFamous(db, new Person(context.getString(R.string.charles_dow), new LocalDate(1851, 11, 6)));
        addFamous(db, new Person(context.getString(R.string.sousa), new LocalDate(1854, 11, 6)));
        addFamous(db, new Person(context.getString(R.string.nailsmith), new LocalDate(1861, 11, 6)));
        addFamous(db, new Person(context.getString(R.string.emma_stone), new LocalDate(1988, 11, 6)));

//November 7
        addFamous(db, new Person(context.getString(R.string.stukeley), new LocalDate(1687, 11, 7)));
        addFamous(db, new Person(context.getString(R.string.james_cook), new LocalDate(1728, 11, 7)));
        addFamous(db, new Person(context.getString(R.string.erkel), new LocalDate(1810, 11, 7)));
        addFamous(db, new Person(context.getString(R.string.casal), new LocalDate(1863, 11, 7)));
        addFamous(db, new Person(context.getString(R.string.marie_curie), new LocalDate(1867, 11, 7)));
        addFamous(db, new Person(context.getString(R.string.camus), new LocalDate(1913, 11, 7)));
        addFamous(db, new Person(context.getString(R.string.guetta), new LocalDate(1967, 11, 7)));

//November 8
        addFamous(db, new Person(context.getString(R.string.stoker), new LocalDate(1847, 11, 8)));
        addFamous(db, new Person(context.getString(R.string.hausdorff), new LocalDate(1868, 11, 8)));
        addFamous(db, new Person(context.getString(R.string.rorschach), new LocalDate(1884, 11, 8)));
        addFamous(db, new Person(context.getString(R.string.mitchell), new LocalDate(1900, 11, 8)));
        addFamous(db, new Person(context.getString(R.string.barnard), new LocalDate(1922, 11, 8)));
        addFamous(db, new Person(context.getString(R.string.kilby), new LocalDate(1923, 11, 8)));
        addFamous(db, new Person(context.getString(R.string.delon), new LocalDate(1935, 11, 8)));
        addFamous(db, new Person(context.getString(R.string.hiddink), new LocalDate(1946, 11, 8)));

//November 9
        addFamous(db, new Person(context.getString(R.string.borden), new LocalDate(1801, 11, 9)));
        addFamous(db, new Person(context.getString(R.string.turgenev), new LocalDate(1818, 11, 9)));
        addFamous(db, new Person(context.getString(R.string.gaboriau), new LocalDate(1832, 11, 9)));
        addFamous(db, new Person(context.getString(R.string.monnet), new LocalDate(1888, 11, 9)));
        addFamous(db, new Person(context.getString(R.string.sagan), new LocalDate(1934, 11, 9)));
        addFamous(db, new Person(context.getString(R.string.del_piero), new LocalDate(1974, 11, 9)));

//November 10
        addFamous(db, new Person(context.getString(R.string.luther), new LocalDate(1483, 11, 10)));
        addFamous(db, new Person(context.getString(R.string.hogarth), new LocalDate(1697, 11, 10)));
        addFamous(db, new Person(context.getString(R.string.schiller), new LocalDate(1759, 11, 10)));
        addFamous(db, new Person(context.getString(R.string.innes), new LocalDate(1861, 11, 10)));
        addFamous(db, new Person(context.getString(R.string.morricone), new LocalDate(1928, 11, 10)));
        addFamous(db, new Person(context.getString(R.string.brittany_murphy), new LocalDate(1977, 11, 10)));

//November 11
        addFamous(db, new Person(context.getString(R.string.dostoyevsky), new LocalDate(1821, 11, 11)));
        addFamous(db, new Person(context.getString(R.string.maurice_leblanc), new LocalDate(1864, 11, 11)));
        addFamous(db, new Person(context.getString(R.string.vonnegut), new LocalDate(1922, 11, 11)));
        addFamous(db, new Person(context.getString(R.string.brugiroux), new LocalDate(1937, 11, 11)));
        addFamous(db, new Person(context.getString(R.string.demi_moore), new LocalDate(1962, 11, 11)));
        addFamous(db, new Person(context.getString(R.string.dicaprio), new LocalDate(1974, 11, 11)));

//November 12
        addFamous(db, new Person(context.getString(R.string.rodin), new LocalDate(1840, 11, 12)));
        addFamous(db, new Person(context.getString(R.string.grace_kelly), new LocalDate(1929, 11, 12)));
        addFamous(db, new Person(context.getString(R.string.gurchenko), new LocalDate(1935, 11, 12)));
        addFamous(db, new Person(context.getString(R.string.gosling), new LocalDate(1980, 11, 12)));
        addFamous(db, new Person(context.getString(R.string.hathaway), new LocalDate(1982, 11, 12)));

//November 13
        addFamous(db, new Person(context.getString(R.string.montagu), new LocalDate(1718, 11, 13)));
        addFamous(db, new Person(context.getString(R.string.hauy), new LocalDate(1745, 11, 13)));
        addFamous(db, new Person(context.getString(R.string.stevenson), new LocalDate(1850, 11, 13)));
        addFamous(db, new Person(context.getString(R.string.kokkonen), new LocalDate(1921, 11, 13)));
        addFamous(db, new Person(context.getString(R.string.whoopi_goldberg), new LocalDate(1955, 11, 13)));
        addFamous(db, new Person(context.getString(R.string.gerard_butler), new LocalDate(1969, 11, 13)));

//November 14
        addFamous(db, new Person(context.getString(R.string.fulton), new LocalDate(1765, 11, 14)));
        addFamous(db, new Person(context.getString(R.string.bichat), new LocalDate(1771, 11, 14)));
        addFamous(db, new Person(context.getString(R.string.lyell), new LocalDate(1797, 11, 14)));
        addFamous(db, new Person(context.getString(R.string.monet), new LocalDate(1840, 11, 14)));
        addFamous(db, new Person(context.getString(R.string.banting), new LocalDate(1891, 11, 14)));
        addFamous(db, new Person(context.getString(R.string.lindgren), new LocalDate(1907, 11, 14)));

//November 15
        addFamous(db, new Person(context.getString(R.string.lavater), new LocalDate(1741, 11, 15)));
        addFamous(db, new Person(context.getString(R.string.chasles), new LocalDate(1793, 11, 15)));
        addFamous(db, new Person(context.getString(R.string.hauptmann), new LocalDate(1862, 11, 15)));
        addFamous(db, new Person(context.getString(R.string.krogh), new LocalDate(1874, 11, 15)));
        addFamous(db, new Person(context.getString(R.string.kroeger), new LocalDate(1974, 11, 15)));

//November 16
        addFamous(db, new Person(context.getString(R.string.kreutzer), new LocalDate(1766, 11, 16)));
        addFamous(db, new Person(context.getString(R.string.saramago), new LocalDate(1922, 11, 16)));
        addFamous(db, new Person(context.getString(R.string.achebe), new LocalDate(1930, 11, 16)));
        addFamous(db, new Person(context.getString(R.string.krall), new LocalDate(1964, 11, 16)));
        addFamous(db, new Person(context.getString(R.string.gyllenhaal), new LocalDate(1977, 11, 16)));

//November 17
        addFamous(db, new Person(context.getString(R.string.bronzino), new LocalDate(1503, 11, 17)));
        addFamous(db, new Person(context.getString(R.string.mobius), new LocalDate(1790, 11, 17)));
        addFamous(db, new Person(context.getString(R.string.wigner), new LocalDate(1902, 11, 17)));
        addFamous(db, new Person(context.getString(R.string.honda), new LocalDate(1906, 11, 17)));
        addFamous(db, new Person(context.getString(R.string.scorsese), new LocalDate(1942, 11, 17)));
        addFamous(db, new Person(context.getString(R.string.devito), new LocalDate(1944, 11, 17)));
        addFamous(db, new Person(context.getString(R.string.marceau), new LocalDate(1966, 11, 17)));
        addFamous(db, new Person(context.getString(R.string.mcadams), new LocalDate(1978, 11, 17)));

//November 18
        addFamous(db, new Person(context.getString(R.string.down), new LocalDate(1828, 11, 18)));
        addFamous(db, new Person(context.getString(R.string.nordenskiold), new LocalDate(1832, 11, 18)));
        addFamous(db, new Person(context.getString(R.string.gallup), new LocalDate(1901, 11, 18)));
        addFamous(db, new Person(context.getString(R.string.issigonis), new LocalDate(1906, 11, 18)));
        addFamous(db, new Person(context.getString(R.string.ryazanov), new LocalDate(1927, 11, 18)));
        addFamous(db, new Person(context.getString(R.string.owen_wilson), new LocalDate(1968, 11, 18)));

//November 19
        addFamous(db, new Person(context.getString(R.string.lomonosov), new LocalDate(1711, 11, 19)));
        addFamous(db, new Person(context.getString(R.string.skoda), new LocalDate(1839, 11, 19)));
        addFamous(db, new Person(context.getString(R.string.avenarius), new LocalDate(1843, 11, 19)));
        addFamous(db, new Person(context.getString(R.string.drucker), new LocalDate(1909, 11, 19)));
        addFamous(db, new Person(context.getString(R.string.calvin_klein), new LocalDate(1942, 11, 19)));
        addFamous(db, new Person(context.getString(R.string.ryan), new LocalDate(1961, 11, 19)));
        addFamous(db, new Person(context.getString(R.string.jodie_foster), new LocalDate(1962, 11, 19)));

//November 20
        addFamous(db, new Person(context.getString(R.string.guericke), new LocalDate(1602, 11, 20)));
        addFamous(db, new Person(context.getString(R.string.lagerlof), new LocalDate(1858, 11, 20)));
        addFamous(db, new Person(context.getString(R.string.karl_von_frisch), new LocalDate(1886, 11, 20)));
        addFamous(db, new Person(context.getString(R.string.hubble), new LocalDate(1889, 11, 20)));
        addFamous(db, new Person(context.getString(R.string.osgood), new LocalDate(1916, 11, 20)));

//November 21
        addFamous(db, new Person(context.getString(R.string.voltaire), new LocalDate(1694, 11, 21)));
        addFamous(db, new Person(context.getString(R.string.schleiermacher), new LocalDate(1768, 11, 21)));
        addFamous(db, new Person(context.getString(R.string.lewis_morgan), new LocalDate(1818, 11, 21)));
        addFamous(db, new Person(context.getString(R.string.makarova), new LocalDate(1940, 11, 21)));
        addFamous(db, new Person(context.getString(R.string.hawn), new LocalDate(1945, 11, 21)));
        addFamous(db, new Person(context.getString(R.string.bjork), new LocalDate(1965, 11, 21)));

//November 22
        addFamous(db, new Person(context.getString(R.string.vladimir_dal), new LocalDate(1801, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.thomas_cook), new LocalDate(1808, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.gide), new LocalDate(1869, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.gaulle), new LocalDate(1890, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.pelevin), new LocalDate(1962, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.mikkelsen), new LocalDate(1965, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.ruffalo), new LocalDate(1967, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.ville_valo), new LocalDate(1976, 11, 22)));
        addFamous(db, new Person(context.getString(R.string.scarlett_johansson), new LocalDate(1984, 11, 22)));

//November 23
        addFamous(db, new Person(context.getString(R.string.waals), new LocalDate(1837, 11, 23)));
        addFamous(db, new Person(context.getString(R.string.karloff), new LocalDate(1887, 11, 23)));
        addFamous(db, new Person(context.getString(R.string.moseley), new LocalDate(1887, 11, 23)));
        addFamous(db, new Person(context.getString(R.string.nosov), new LocalDate(1908, 11, 23)));
        addFamous(db, new Person(context.getString(R.string.cyrus), new LocalDate(1992, 11, 23)));

//November 24
        addFamous(db, new Person(context.getString(R.string.spinoza), new LocalDate(1632, 11, 24)));
        addFamous(db, new Person(context.getString(R.string.suvorov), new LocalDate(1729, 11, 24)));
        addFamous(db, new Person(context.getString(R.string.ellis), new LocalDate(1806, 11, 24)));
        addFamous(db, new Person(context.getString(R.string.collodi), new LocalDate(1826, 11, 24)));
        addFamous(db, new Person(context.getString(R.string.carnegie), new LocalDate(1888, 11, 24)));
        addFamous(db, new Person(context.getString(R.string.kusturica), new LocalDate(1954, 11, 24)));
        addFamous(db, new Person(context.getString(R.string.heigl), new LocalDate(1978, 11, 24)));

//November 25
        addFamous(db, new Person(context.getString(R.string.vega), new LocalDate(1562, 11, 25)));
        addFamous(db, new Person(context.getString(R.string.sumarokov), new LocalDate(1717, 11, 25)));
        addFamous(db, new Person(context.getString(R.string.pirogov), new LocalDate(1810, 11, 25)));
        addFamous(db, new Person(context.getString(R.string.benz), new LocalDate(1844, 11, 25)));
        addFamous(db, new Person(context.getString(R.string.vavilov), new LocalDate(1887, 11, 25)));
        addFamous(db, new Person(context.getString(R.string.poul_anderson), new LocalDate(1926, 11, 25)));

//November 26
        addFamous(db, new Person(context.getString(R.string.harvard), new LocalDate(1607, 11, 26)));
        addFamous(db, new Person(context.getString(R.string.saussure), new LocalDate(1857, 11, 26)));
        addFamous(db, new Person(context.getString(R.string.leck), new LocalDate(1876, 11, 26)));
        addFamous(db, new Person(context.getString(R.string.wiener), new LocalDate(1894, 11, 26)));
        addFamous(db, new Person(context.getString(R.string.ionesco), new LocalDate(1909, 11, 26)));
        addFamous(db, new Person(context.getString(R.string.tina_turner), new LocalDate(1939, 11, 26)));

//November 27
        addFamous(db, new Person(context.getString(R.string.celsius), new LocalDate(1701, 11, 27)));
        addFamous(db, new Person(context.getString(R.string.weizmann), new LocalDate(1874, 11, 27)));
        addFamous(db, new Person(context.getString(R.string.matsushita), new LocalDate(1894, 11, 27)));
        addFamous(db, new Person(context.getString(R.string.bruce_lee), new LocalDate(1940, 11, 27)));
        addFamous(db, new Person(context.getString(R.string.mashkov), new LocalDate(1963, 11, 27)));

//November 28
        addFamous(db, new Person(context.getString(R.string.lully), new LocalDate(1632, 11, 28)));
        addFamous(db, new Person(context.getString(R.string.blake), new LocalDate(1757, 11, 28)));
        addFamous(db, new Person(context.getString(R.string.cousin), new LocalDate(1792, 11, 28)));
        addFamous(db, new Person(context.getString(R.string.engels), new LocalDate(1820, 11, 28)));
        addFamous(db, new Person(context.getString(R.string.anton_rubinstein), new LocalDate(1829, 11, 28)));
        addFamous(db, new Person(context.getString(R.string.blok), new LocalDate(1880, 11, 28)));
        addFamous(db, new Person(context.getString(R.string.zanetti), new LocalDate(1956, 11, 28)));
        addFamous(db, new Person(context.getString(R.string.galliano), new LocalDate(1960, 11, 28)));

//November 29
        addFamous(db, new Person(context.getString(R.string.donizetti), new LocalDate(1797, 11, 29)));
        addFamous(db, new Person(context.getString(R.string.hauff), new LocalDate(1802, 11, 29)));
        addFamous(db, new Person(context.getString(R.string.charcot), new LocalDate(1825, 11, 29)));
        addFamous(db, new Person(context.getString(R.string.john_fleming), new LocalDate(1849, 11, 29)));
        addFamous(db, new Person(context.getString(R.string.giggs), new LocalDate(1973, 11, 29)));
        addFamous(db, new Person(context.getString(R.string.faris), new LocalDate(1976, 11, 29)));

//November 30
        addFamous(db, new Person(context.getString(R.string.swift), new LocalDate(1667, 11, 30)));
        addFamous(db, new Person(context.getString(R.string.twain), new LocalDate(1835, 11, 30)));
        addFamous(db, new Person(context.getString(R.string.churchill), new LocalDate(1874, 11, 30)));
        addFamous(db, new Person(context.getString(R.string.ridley_scott), new LocalDate(1937, 11, 30)));
        addFamous(db, new Person(context.getString(R.string.idol), new LocalDate(1955, 11, 30)));
        addFamous(db, new Person(context.getString(R.string.stiller), new LocalDate(1965, 11, 30)));

//December 1
        addFamous(db, new Person(context.getString(R.string.falconet), new LocalDate(1716, 12, 1)));
        addFamous(db, new Person(context.getString(R.string.tussaud), new LocalDate(1761, 12, 1)));
        addFamous(db, new Person(context.getString(R.string.lobachevsky), new LocalDate(1792, 12, 1)));
        addFamous(db, new Person(context.getString(R.string.zhukov), new LocalDate(1896, 12, 1)));
        addFamous(db, new Person(context.getString(R.string.allen), new LocalDate(1935, 12, 1)));
        addFamous(db, new Person(context.getString(R.string.escobar), new LocalDate(1949, 12, 1)));

//December 2
        addFamous(db, new Person(context.getString(R.string.joseph_bell), new LocalDate(1837, 12, 2)));
        addFamous(db, new Person(context.getString(R.string.seuratl), new LocalDate(1859, 12, 2)));
        addFamous(db, new Person(context.getString(R.string.callas), new LocalDate(1923, 12, 2)));
        addFamous(db, new Person(context.getString(R.string.versace), new LocalDate(1946, 12, 2)));
        addFamous(db, new Person(context.getString(R.string.liu), new LocalDate(1968, 12, 2)));
        addFamous(db, new Person(context.getString(R.string.furtado), new LocalDate(1978, 12, 2)));
        addFamous(db, new Person(context.getString(R.string.spears), new LocalDate(1981, 12, 2)));

//December 3
        addFamous(db, new Person(context.getString(R.string.hill), new LocalDate(1795, 12, 3)));
        addFamous(db, new Person(context.getString(R.string.rota), new LocalDate(1911, 12, 3)));
        addFamous(db, new Person(context.getString(R.string.osbourne), new LocalDate(1948, 12, 3)));
        addFamous(db, new Person(context.getString(R.string.julianne_moore), new LocalDate(1960, 12, 3)));
        addFamous(db, new Person(context.getString(R.string.seyfried), new LocalDate(1985, 12, 3)));

//December 4
        addFamous(db, new Person(context.getString(R.string.chapelain), new LocalDate(1595, 12, 4)));
        addFamous(db, new Person(context.getString(R.string.carlyle), new LocalDate(1795, 12, 4)));
        addFamous(db, new Person(context.getString(R.string.adler), new LocalDate(1913, 12, 4)));
        addFamous(db, new Person(context.getString(R.string.bridges), new LocalDate(1949, 12, 4)));
        addFamous(db, new Person(context.getString(R.string.bubka), new LocalDate(1963, 12, 4)));
        addFamous(db, new Person(context.getString(R.string.jay_z), new LocalDate(1969, 12, 4)));

//December 5
        addFamous(db, new Person(context.getString(R.string.tyutchev), new LocalDate(1803, 12, 5)));
        addFamous(db, new Person(context.getString(R.string.lang), new LocalDate(1890, 12, 5)));
        addFamous(db, new Person(context.getString(R.string.disney), new LocalDate(1901, 12, 5)));
        addFamous(db, new Person(context.getString(R.string.heisenberg), new LocalDate(1901, 12, 5)));
        addFamous(db, new Person(context.getString(R.string.carreras), new LocalDate(1946, 12, 5)));
        addFamous(db, new Person(context.getString(R.string.kaas), new LocalDate(1966, 12, 5)));

//December 6
        addFamous(db, new Person(context.getString(R.string.nicolas_leblanc), new LocalDate(1742, 12, 6)));
        addFamous(db, new Person(context.getString(R.string.gay_lussac), new LocalDate(1778, 12, 6)));
        addFamous(db, new Person(context.getString(R.string.veqilharxhi), new LocalDate(1797, 12, 6)));
        addFamous(db, new Person(context.getString(R.string.bazille), new LocalDate(1841, 12, 6)));
        addFamous(db, new Person(context.getString(R.string.crali), new LocalDate(1910, 12, 6)));
        addFamous(db, new Person(context.getString(R.string.nick_park), new LocalDate(1958, 12, 6)));

//December 7
        addFamous(db, new Person(context.getString(R.string.bernini), new LocalDate(1598, 12, 7)));
        addFamous(db, new Person(context.getString(R.string.schwann), new LocalDate(1810, 12, 7)));
        addFamous(db, new Person(context.getString(R.string.mascagni), new LocalDate(1863, 12, 7)));
        addFamous(db, new Person(context.getString(R.string.yosano), new LocalDate(1878, 12, 7)));
        addFamous(db, new Person(context.getString(R.string.waits), new LocalDate(1949, 12, 7)));
        addFamous(db, new Person(context.getString(R.string.emily_browning), new LocalDate(1988, 12, 7)));

//December 8
        addFamous(db, new Person(context.getString(R.string.dholbach), new LocalDate(1723, 12, 8)));
        addFamous(db, new Person(context.getString(R.string.menzel), new LocalDate(1815, 12, 8)));
        addFamous(db, new Person(context.getString(R.string.bjornson), new LocalDate(1832, 12, 8)));
        addFamous(db, new Person(context.getString(R.string.reynaud), new LocalDate(1844, 12, 8)));
        addFamous(db, new Person(context.getString(R.string.melies), new LocalDate(1861, 12, 8)));
        addFamous(db, new Person(context.getString(R.string.morrison), new LocalDate(1943, 12, 8)));
        addFamous(db, new Person(context.getString(R.string.basinger), new LocalDate(1953, 12, 8)));
        addFamous(db, new Person(context.getString(R.string.somerhalder), new LocalDate(1978, 12, 8)));

//December 9
        addFamous(db, new Person(context.getString(R.string.milton), new LocalDate(1608, 12, 9)));
        addFamous(db, new Person(context.getString(R.string.winckelmann), new LocalDate(1717, 12, 9)));
        addFamous(db, new Person(context.getString(R.string.scheele), new LocalDate(1742, 12, 9)));
        addFamous(db, new Person(context.getString(R.string.berthollet), new LocalDate(1748, 12, 9)));
        addFamous(db, new Person(context.getString(R.string.grace_hopper), new LocalDate(1906, 12, 9)));
        addFamous(db, new Person(context.getString(R.string.dench), new LocalDate(1934, 12, 9)));
        addFamous(db, new Person(context.getString(R.string.malkovich), new LocalDate(1953, 12, 9)));

//December 10
        addFamous(db, new Person(context.getString(R.string.lovelace), new LocalDate(1815, 12, 10)));
        addFamous(db, new Person(context.getString(R.string.nekrasov), new LocalDate(1821, 12, 10)));
        addFamous(db, new Person(context.getString(R.string.sachs), new LocalDate(1891, 12, 10)));
        addFamous(db, new Person(context.getString(R.string.tarasov), new LocalDate(1918, 12, 10)));
        addFamous(db, new Person(context.getString(R.string.michael_duncan), new LocalDate(1957, 12, 10)));
        addFamous(db, new Person(context.getString(R.string.branagh), new LocalDate(1960, 12, 10)));
        addFamous(db, new Person(context.getString(R.string.molko), new LocalDate(1972, 12, 10)));

//December 11
        addFamous(db, new Person(context.getString(R.string.berlioz), new LocalDate(1803, 12, 11)));
        addFamous(db, new Person(context.getString(R.string.musset), new LocalDate(1810, 12, 11)));
        addFamous(db, new Person(context.getString(R.string.koch), new LocalDate(1843, 12, 11)));
        addFamous(db, new Person(context.getString(R.string.born), new LocalDate(1882, 12, 11)));
        addFamous(db, new Person(context.getString(R.string.gardel), new LocalDate(1890, 12, 11)));
        addFamous(db, new Person(context.getString(R.string.marais), new LocalDate(1913, 12, 11)));

//December 12
        addFamous(db, new Person(context.getString(R.string.karamzin), new LocalDate(1766, 12, 12)));
        addFamous(db, new Person(context.getString(R.string.serebriakova), new LocalDate(1884, 12, 12)));
        addFamous(db, new Person(context.getString(R.string.ozu), new LocalDate(1903, 12, 12)));
        addFamous(db, new Person(context.getString(R.string.sinatra), new LocalDate(1915, 12, 12)));
        addFamous(db, new Person(context.getString(R.string.noyce), new LocalDate(1927, 12, 12)));
        addFamous(db, new Person(context.getString(R.string.konyukhov), new LocalDate(1951, 12, 12)));

//December 13
        addFamous(db, new Person(context.getString(R.string.gozzi), new LocalDate(1720, 12, 13)));
        addFamous(db, new Person(context.getString(R.string.heine), new LocalDate(1797, 12, 13)));
        addFamous(db, new Person(context.getString(R.string.werner_siemens), new LocalDate(1816, 12, 13)));
        addFamous(db, new Person(context.getString(R.string.birkeland), new LocalDate(1867, 12, 13)));
        addFamous(db, new Person(context.getString(R.string.buscemi), new LocalDate(1957, 12, 13)));
        addFamous(db, new Person(context.getString(R.string.foxx), new LocalDate(1967, 12, 13)));
        addFamous(db, new Person(context.getString(R.string.amy_lee), new LocalDate(1981, 12, 13)));
        addFamous(db, new Person(context.getString(R.string.taylor_swift), new LocalDate(1989, 12, 13)));

//December 14
        addFamous(db, new Person(context.getString(R.string.nostradamus), new LocalDate(1503, 12, 14)));
        addFamous(db, new Person(context.getString(R.string.brahe), new LocalDate(1546, 12, 14)));
        addFamous(db, new Person(context.getString(R.string.ueshiba), new LocalDate(1883, 12, 14)));
        addFamous(db, new Person(context.getString(R.string.basov), new LocalDate(1922, 12, 14)));
        addFamous(db, new Person(context.getString(R.string.kapoor), new LocalDate(1924, 12, 14)));

//December 15
        addFamous(db, new Person(context.getString(R.string.carey), new LocalDate(1793, 12, 15)));
        addFamous(db, new Person(context.getString(R.string.bolyai), new LocalDate(1802, 12, 15)));
        addFamous(db, new Person(context.getString(R.string.eiffel), new LocalDate(1832, 12, 15)));
        addFamous(db, new Person(context.getString(R.string.becquerel), new LocalDate(1852, 12, 15)));
        addFamous(db, new Person(context.getString(R.string.zamenhof), new LocalDate(1859, 12, 15)));
        addFamous(db, new Person(context.getString(R.string.getty), new LocalDate(1892, 12, 15)));

//December 16
        addFamous(db, new Person(context.getString(R.string.austen), new LocalDate(1775, 12, 16)));
        addFamous(db, new Person(context.getString(R.string.walras), new LocalDate(1834, 12, 16)));
        addFamous(db, new Person(context.getString(R.string.bergmann), new LocalDate(1836, 12, 16)));
        addFamous(db, new Person(context.getString(R.string.kandinsky), new LocalDate(1866, 12, 16)));
        addFamous(db, new Person(context.getString(R.string.linder), new LocalDate(1883, 12, 16)));
        addFamous(db, new Person(context.getString(R.string.arthur_clarke), new LocalDate(1917, 12, 16)));
        addFamous(db, new Person(context.getString(R.string.paul_van_dyk), new LocalDate(1971, 12, 16)));

//December 17
        addFamous(db, new Person(context.getString(R.string.cimarosa), new LocalDate(1749, 12, 17)));
        addFamous(db, new Person(context.getString(R.string.beethoven), new LocalDate(1770, 12, 17)));
        addFamous(db, new Person(context.getString(R.string.davy), new LocalDate(1778, 12, 17)));
        addFamous(db, new Person(context.getString(R.string.ganelin), new LocalDate(1944, 12, 17)));
        addFamous(db, new Person(context.getString(R.string.darryl_way), new LocalDate(1948, 12, 17)));
        addFamous(db, new Person(context.getString(R.string.jovovich), new LocalDate(1975, 12, 17)));

//December 18
        addFamous(db, new Person(context.getString(R.string.polhem), new LocalDate(1661, 12, 18)));
        addFamous(db, new Person(context.getString(R.string.joseph_thomson), new LocalDate(1856, 12, 18)));
        addFamous(db, new Person(context.getString(R.string.stalin), new LocalDate(1878, 12, 18)));
        addFamous(db, new Person(context.getString(R.string.spielberg), new LocalDate(1946, 12, 18)));
        addFamous(db, new Person(context.getString(R.string.liotta), new LocalDate(1954, 12, 18)));
        addFamous(db, new Person(context.getString(R.string.brad_pitt), new LocalDate(1963, 12, 18)));
        addFamous(db, new Person(context.getString(R.string.holmes), new LocalDate(1978, 12, 18)));
        addFamous(db, new Person(context.getString(R.string.aguilera), new LocalDate(1980, 12, 18)));

//December 19
        addFamous(db, new Person(context.getString(R.string.michelson), new LocalDate(1852, 12, 19)));
        addFamous(db, new Person(context.getString(R.string.joze_lima), new LocalDate(1910, 12, 19)));
        addFamous(db, new Person(context.getString(R.string.piaf), new LocalDate(1915, 12, 19)));
        addFamous(db, new Person(context.getString(R.string.schweiger), new LocalDate(1963, 12, 19)));
        addFamous(db, new Person(context.getString(R.string.milano), new LocalDate(1972, 12, 19)));
        addFamous(db, new Person(context.getString(R.string.jake_gyllenhaal), new LocalDate(1980, 12, 19)));

//December 20
        addFamous(db, new Person(context.getString(R.string.heyrovsky), new LocalDate(1890, 12, 20)));
        addFamous(db, new Person(context.getString(R.string.balandin), new LocalDate(1898, 12, 20)));
        addFamous(db, new Person(context.getString(R.string.graaff), new LocalDate(1901, 12, 20)));
        addFamous(db, new Person(context.getString(R.string.bohm), new LocalDate(1917, 12, 20)));
        addFamous(db, new Person(context.getString(R.string.uri_geller), new LocalDate(1946, 12, 20)));
        addFamous(db, new Person(context.getString(R.string.jonah_hill), new LocalDate(1983, 12, 20)));

//December 21
        addFamous(db, new Person(context.getString(R.string.robert_brown), new LocalDate(1773, 12, 21)));
        addFamous(db, new Person(context.getString(R.string.hermann_muller), new LocalDate(1890, 12, 21)));
        addFamous(db, new Person(context.getString(R.string.boll), new LocalDate(1917, 12, 21)));
        addFamous(db, new Person(context.getString(R.string.monterroso), new LocalDate(1921, 12, 21)));
        addFamous(db, new Person(context.getString(R.string.jane_fonda), new LocalDate(1937, 12, 21)));
        addFamous(db, new Person(context.getString(R.string.zappa), new LocalDate(1940, 12, 21)));
        addFamous(db, new Person(context.getString(R.string.samuel_jackson), new LocalDate(1948, 12, 21)));

//December 22
        addFamous(db, new Person(context.getString(R.string.liotard), new LocalDate(1702, 12, 22)));
        addFamous(db, new Person(context.getString(R.string.fabre), new LocalDate(1823, 12, 22)));
        addFamous(db, new Person(context.getString(R.string.puccini), new LocalDate(1858, 12, 22)));
        addFamous(db, new Person(context.getString(R.string.elizondo), new LocalDate(1936, 12, 22)));
        addFamous(db, new Person(context.getString(R.string.fiennes), new LocalDate(1962, 12, 22)));
        addFamous(db, new Person(context.getString(R.string.paradis), new LocalDate(1972, 12, 22)));

//December 23
        addFamous(db, new Person(context.getString(R.string.champollion), new LocalDate(1790, 12, 23)));
        addFamous(db, new Person(context.getString(R.string.bryullov), new LocalDate(1799, 12, 23)));
        addFamous(db, new Person(context.getString(R.string.dino_risi), new LocalDate(1916, 12, 23)));
        addFamous(db, new Person(context.getString(R.string.baker), new LocalDate(1929, 12, 23)));
        addFamous(db, new Person(context.getString(R.string.bosque), new LocalDate(1950, 12, 23)));
        addFamous(db, new Person(context.getString(R.string.bruni), new LocalDate(1967, 12, 23)));

//December 24
        addFamous(db, new Person(context.getString(R.string.joule), new LocalDate(1818, 12, 24)));
        addFamous(db, new Person(context.getString(R.string.howard_hughes), new LocalDate(1905, 12, 24)));
        addFamous(db, new Person(context.getString(R.string.chase), new LocalDate(1906, 12, 24)));
        addFamous(db, new Person(context.getString(R.string.gardner), new LocalDate(1922, 12, 24)));
        addFamous(db, new Person(context.getString(R.string.ricky_martin), new LocalDate(1971, 12, 24)));
        addFamous(db, new Person(context.getString(R.string.stephenie_meyer), new LocalDate(1973, 12, 24)));

//December 25
        addFamous(db, new Person(context.getString(R.string.chevrolet), new LocalDate(1878, 12, 25)));
        addFamous(db, new Person(context.getString(R.string.rosenzweig), new LocalDate(1886, 12, 25)));
        addFamous(db, new Person(context.getString(R.string.hilton), new LocalDate(1887, 12, 25)));
        addFamous(db, new Person(context.getString(R.string.bogart), new LocalDate(1899, 12, 25)));
        addFamous(db, new Person(context.getString(R.string.castaneda), new LocalDate(1925, 12, 25)));
        addFamous(db, new Person(context.getString(R.string.lennox), new LocalDate(1954, 12, 25)));
        addFamous(db, new Person(context.getString(R.string.buuren), new LocalDate(1976, 12, 25)));

//December 26
        addFamous(db, new Person(context.getString(R.string.dinglinger), new LocalDate(1664, 12, 26)));
        addFamous(db, new Person(context.getString(R.string.babbage), new LocalDate(1791, 12, 26)));
        addFamous(db, new Person(context.getString(R.string.henry_miller), new LocalDate(1891, 12, 26)));
        addFamous(db, new Person(context.getString(R.string.zedong), new LocalDate(1893, 12, 26)));
        addFamous(db, new Person(context.getString(R.string.ulrich), new LocalDate(1963, 12, 26)));
        addFamous(db, new Person(context.getString(R.string.jared_leto), new LocalDate(1971, 12, 26)));

//December 27
        addFamous(db, new Person(context.getString(R.string.kepler), new LocalDate(1571, 12, 27)));
        addFamous(db, new Person(context.getString(R.string.cayley), new LocalDate(1773, 12, 27)));
        addFamous(db, new Person(context.getString(R.string.pasteur), new LocalDate(1822, 12, 27)));
        addFamous(db, new Person(context.getString(R.string.tretyakov), new LocalDate(1832, 12, 27)));
        addFamous(db, new Person(context.getString(R.string.dietrich), new LocalDate(1901, 12, 27)));
        addFamous(db, new Person(context.getString(R.string.depardieu), new LocalDate(1948, 12, 27)));

//December 28
        addFamous(db, new Person(context.getString(R.string.eddington), new LocalDate(1882, 12, 28)));
        addFamous(db, new Person(context.getString(R.string.murnau), new LocalDate(1888, 12, 28)));
        addFamous(db, new Person(context.getString(R.string.john_neumann), new LocalDate(1903, 12, 28)));
        addFamous(db, new Person(context.getString(R.string.denzel_washington), new LocalDate(1954, 12, 28)));
        addFamous(db, new Person(context.getString(R.string.torvalds), new LocalDate(1969, 12, 28)));
        addFamous(db, new Person(context.getString(R.string.sienna_miller), new LocalDate(1981, 12, 28)));

//December 29
        addFamous(db, new Person(context.getString(R.string.pompadour), new LocalDate(1721, 12, 29)));
        addFamous(db, new Person(context.getString(R.string.siqueiros), new LocalDate(1896, 12, 29)));
        addFamous(db, new Person(context.getString(R.string.voight), new LocalDate(1938, 12, 29)));
        addFamous(db, new Person(context.getString(R.string.dexter_holland), new LocalDate(1965, 12, 29)));
        addFamous(db, new Person(context.getString(R.string.jude_law), new LocalDate(1972, 12, 29)));

//December 30
        addFamous(db, new Person(context.getString(R.string.jablonskis), new LocalDate(1860, 12, 30)));
        addFamous(db, new Person(context.getString(R.string.kipling), new LocalDate(1865, 12, 30)));
        addFamous(db, new Person(context.getString(R.string.patti_smith), new LocalDate(1946, 12, 30)));
        addFamous(db, new Person(context.getString(R.string.jay_kay), new LocalDate(1969, 12, 30)));
        addFamous(db, new Person(context.getString(R.string.tiger_woods), new LocalDate(1975, 12, 30)));
        addFamous(db, new Person(context.getString(R.string.lebron_james), new LocalDate(1984, 12, 30)));

//December 31
        addFamous(db, new Person(context.getString(R.string.boldini), new LocalDate(1842, 12, 31)));
        addFamous(db, new Person(context.getString(R.string.matisse), new LocalDate(1869, 12, 31)));
        addFamous(db, new Person(context.getString(R.string.hopkins), new LocalDate(1937, 12, 31)));
        addFamous(db, new Person(context.getString(R.string.ferguson), new LocalDate(1941, 12, 31)));
        addFamous(db, new Person(context.getString(R.string.kingsley), new LocalDate(1943, 12, 31)));
        addFamous(db, new Person(context.getString(R.string.willis), new LocalDate(1945, 12, 31)));
        addFamous(db, new Person(context.getString(R.string.val_kilmer), new LocalDate(1959, 12, 31)));
        addFamous(db, new Person(context.getString(R.string.psy), new LocalDate(1977, 12, 31)));
    }

    static void createFamousDb(Context context, SQLiteDatabase db) {
        createFamousDbPart1(context, db);
        createFamousDbPart2(context, db);
        createFamousDbPart3(context, db);
    }
}