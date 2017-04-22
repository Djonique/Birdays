package com.djonique.birdays.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.djonique.birdays.R;
import com.djonique.birdays.models.Person;

import static com.djonique.birdays.database.DBHelper.COLUMN_DATE;
import static com.djonique.birdays.database.DBHelper.COLUMN_NAME;
import static com.djonique.birdays.database.DBHelper.DB_FAMOUS;

class DBFamous {

    private static void addFamous(SQLiteDatabase db, Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        db.insert(DB_FAMOUS, null, cv);
    }

    static void createDB(Context context, SQLiteDatabase db) {

        // 1 january
        addFamous(db, new Person(context.getString(R.string.medici), -16440364800000L));
        addFamous(db, new Person(context.getString(R.string.giordano_bruno), -13316227200000L));
        addFamous(db, new Person(context.getString(R.string.frazer), -3660595200000L));
        addFamous(db, new Person(context.getString(R.string.coubertin), -3376598400000L));
        addFamous(db, new Person(context.getString(R.string.william_fox), -2871676800000L));

        // 2 january
        addFamous(db, new Person(context.getString(R.string.piero_di_cosimo), -16030051200000L));
        addFamous(db, new Person(context.getString(R.string.vasily_perov), -4291660800000L));
        addFamous(db, new Person(context.getString(R.string.balakirev), -4196966400000L));
        addFamous(db, new Person(context.getString(R.string.tippett), -2051136000000L));
        addFamous(db, new Person(context.getString(R.string.isaac_asimov), -1577836800000L));

        // 3 january
        addFamous(db, new Person(context.getString(R.string.louis_poinsot), -6090249600000L));
        addFamous(db, new Person(context.getString(R.string.dabbadie), -5048956800000L));
        addFamous(db, new Person(context.getString(R.string.fletcher), -2650579200000L));
        addFamous(db, new Person(context.getString(R.string.tolkien), -2461276800000L));
        addFamous(db, new Person(context.getString(R.string.moore), -1293667200000L));
        addFamous(db, new Person(context.getString(R.string.mel_gibson), -441676800000L));
        addFamous(db, new Person(context.getString(R.string.schumacher), -31363200000L));

        // 4 january
        addFamous(db, new Person(context.getString(R.string.isaac_newton), -10318838400000L));
        addFamous(db, new Person(context.getString(R.string.pergolesi), -8204544000000L));
        addFamous(db, new Person(context.getString(R.string.jacob_grimm), -5837702400000L));
        addFamous(db, new Person(context.getString(R.string.braille), -5080406400000L));
        addFamous(db, new Person(context.getString(R.string.tsereteli), -1135814400000L));
        addFamous(db, new Person(context.getString(R.string.josephson), -946512000000L));

        // 5 january
        addFamous(db, new Person(context.getString(R.string.eucken), -3912710400000L));
        addFamous(db, new Person(context.getString(R.string.gillette), -3628713600000L));
        addFamous(db, new Person(context.getString(R.string.erlanger), -3029097600000L));
        addFamous(db, new Person(context.getString(R.string.umberto_eco), -1198886400000L));
        addFamous(db, new Person(context.getString(R.string.manson), -31190400000L));
        addFamous(db, new Person(context.getString(R.string.bradley_cooper), 158112000000L));

        // 6 january
        addFamous(db, new Person(context.getString(R.string.darc), -17607628800000L));
        addFamous(db, new Person(context.getString(R.string.jakob_bernoulli), -9939974400000L));
        addFamous(db, new Person(context.getString(R.string.montgolfier), -7099833600000L));
        addFamous(db, new Person(context.getString(R.string.schliemann), -4670006400000L));
        addFamous(db, new Person(context.getString(R.string.scriabin), -3123705600000L));
        addFamous(db, new Person(context.getString(R.string.celentano), -1009411200000L));
        addFamous(db, new Person(context.getString(R.string.atkinson), -472953600000L));
        addFamous(db, new Person(context.getString(R.string.redmayne), 379123200000L));

        // 7 january
        addFamous(db, new Person(context.getString(R.string.pope_gregory), -14767315200000L));
        addFamous(db, new Person(context.getString(R.string.fleming), -4512153600000L));
        addFamous(db, new Person(context.getString(R.string.eliezer), -3533846400000L));
        addFamous(db, new Person(context.getString(R.string.borel), -3123619200000L));
        addFamous(db, new Person(context.getString(R.string.nicolas_cage), -188870400000L));

        // 8 january
        addFamous(db, new Person(context.getString(R.string.sirani), -10476259200000L));
        addFamous(db, new Person(context.getString(R.string.nijinska), -2492380800000L));
        addFamous(db, new Person(context.getString(R.string.presley), -1103932800000L));
        addFamous(db, new Person(context.getString(R.string.hawking), -883008000000L));
        addFamous(db, new Person(context.getString(R.string.daviw_bowie), -725241600000L));

        // 9 january
        addFamous(db, new Person(context.getString(R.string.simon_vouet), -11990937600000L));
        addFamous(db, new Person(context.getString(R.string.wrangel), -5458579200000L));
        addFamous(db, new Person(context.getString(R.string.watson), -2902521600000L));
        addFamous(db, new Person(context.getString(R.string.capek), -2523830400000L));
        addFamous(db, new Person(context.getString(R.string.beauvoir), -1955923200000L));

        // 10 january
        addFamous(db, new Person(context.getString(R.string.birkbeck), -6121267200000L));
        addFamous(db, new Person(context.getString(R.string.tolstoy), -2744668800000L));
        addFamous(db, new Person(context.getString(R.string.bertoni), -2113603200000L));
        addFamous(db, new Person(context.getString(R.string.wilson), -1072224000000L));
        addFamous(db, new Person(context.getString(R.string.knuth), -1009065600000L));

        // 11 january
        addFamous(db, new Person(context.getString(R.string.parmigianino), -14735433600000L));
        addFamous(db, new Person(context.getString(R.string.guidobaldo), -13409971200000L));
        addFamous(db, new Person(context.getString(R.string.stensen), -10476000000000L));
        addFamous(db, new Person(context.getString(R.string.hofmann), -2018822400000L));
        addFamous(db, new Person(context.getString(R.string.mendoza), -851212800000L));

        // 12 january
        addFamous(db, new Person(context.getString(R.string.helmont), -12305433600000L));
        addFamous(db, new Person(context.getString(R.string.perrault), -10791532800000L));
        addFamous(db, new Person(context.getString(R.string.jack_london), -2965420800000L));
        addFamous(db, new Person(context.getString(R.string.kurchatov), -2113430400000L));
        addFamous(db, new Person(context.getString(R.string.Korolev), -1987200000000L));
        addFamous(db, new Person(context.getString(R.string.maharishi), -1671580800000L));
        addFamous(db, new Person(context.getString(R.string.murakami), -661737600000L));

        // 13 january
        addFamous(db, new Person(context.getString(R.string.beketov), -4511635200000L));
        addFamous(db, new Person(context.getString(R.string.wien), -3344025600000L));
        addFamous(db, new Person(context.getString(R.string.soutine), -2428790400000L));
        addFamous(db, new Person(context.getString(R.string.lifshitz), -1671494400000L));
        addFamous(db, new Person(context.getString(R.string.feyerabend), -1450656000000L));

        // 14 january
        addFamous(db, new Person(context.getString(R.string.semyonov), -4511548800000L));
        addFamous(db, new Person(context.getString(R.string.Morisot), -4069699200000L));
        addFamous(db, new Person(context.getString(R.string.Schweitzer), -2996784000000L));
        addFamous(db, new Person(context.getString(R.string.mishima), -1418947200000L));
        addFamous(db, new Person(context.getString(R.string.kharlamov), -693187200000L));

        // 15 january
        addFamous(db, new Person(context.getString(R.string.moliere), -10980576000000L));
        addFamous(db, new Person(context.getString(R.string.griboyedov), -5521219200000L));
        addFamous(db, new Person(context.getString(R.string.virtanen), -2365545600000L));
        addFamous(db, new Person(context.getString(R.string.teller), -1955404800000L));
        addFamous(db, new Person(context.getString(R.string.luther_king), -1292630400000L));

        // 16 january
        addFamous(db, new Person(context.getString(R.string.schoner), -15555456000000L));
        addFamous(db, new Person(context.getString(R.string.piccinni), -7635513600000L));
        addFamous(db, new Person(context.getString(R.string.alfieri), -6972739200000L));
        addFamous(db, new Person(context.getString(R.string.veresaev), -3249072000000L));
        addFamous(db, new Person(context.getString(R.string.roy_jones), -30240000000L));

        // 17 january
        addFamous(db, new Person(context.getString(R.string.franklin), -8329651200000L));
        addFamous(db, new Person(context.getString(R.string.zhukovsky), -3880137600000L));
        addFamous(db, new Person(context.getString(R.string.stanislavski), -3375216000000L));
        addFamous(db, new Person(context.getString(R.string.al_capone), -2239142400000L));
        addFamous(db, new Person(context.getString(R.string.muhammad_ali), -882230400000L));
        addFamous(db, new Person(context.getString(R.string.jim_carrey), -251078400000L));

        // 18 january
        addFamous(db, new Person(context.getString(R.string.montesquieu), -8865936000000L));
        addFamous(db, new Person(context.getString(R.string.cesar_cui), -4258742400000L));
        addFamous(db, new Person(context.getString(R.string.ehrenfest), -2838672000000L));
        addFamous(db, new Person(context.getString(R.string.milne), -2775513600000L));
        addFamous(db, new Person(context.getString(R.string.kitano), -724377600000L));
        addFamous(db, new Person(context.getString(R.string.guardiola), 33004800000L));

        // 19 january
        addFamous(db, new Person(context.getString(R.string.cagnacci), -11642918400000L));
        addFamous(db, new Person(context.getString(R.string.comte), -5426179200000L));
        addFamous(db, new Person(context.getString(R.string.edgar_poe), -5079110400000L));
        addFamous(db, new Person(context.getString(R.string.kapteyn), -3753734400000L));
        addFamous(db, new Person(context.getString(R.string.serov), -3311884800000L));
        addFamous(db, new Person(context.getString(R.string.kantorovich), -1828828800000L));

        // 20 january
        addFamous(db, new Person(context.getString(R.string.gessi), -12053145600000L));
        addFamous(db, new Person(context.getString(R.string.ampere), -6151939200000L));
        addFamous(db, new Person(context.getString(R.string.chausson), -3627417600000L));
        addFamous(db, new Person(context.getString(R.string.onassis), -2018044800000L));
        addFamous(db, new Person(context.getString(R.string.fellini), -1576281600000L));

        // 21 january
        addFamous(db, new Person(context.getString(R.string.browning), -3627331200000L));
        addFamous(db, new Person(context.getString(R.string.florensky), -2775254400000L));
        addFamous(db, new Person(context.getString(R.string.dior), -2049494400000L));
        addFamous(db, new Person(context.getString(R.string.benny_hill), -1449964800000L));
        addFamous(db, new Person(context.getString(R.string.domingo), -913420800000L));

        // 22 january
        addFamous(db, new Person(context.getString(R.string.bacon), -12904099200000L));
        addFamous(db, new Person(context.getString(R.string.byron), -5741539200000L));
        addFamous(db, new Person(context.getString(R.string.scoville), -3311625600000L));
        addFamous(db, new Person(context.getString(R.string.picabia), -2869862400000L));
        addFamous(db, new Person(context.getString(R.string.landau), -1954800000000L));

        // 23 january
        addFamous(db, new Person(context.getString(R.string.stendhal), -5899219200000L));
        addFamous(db, new Person(context.getString(R.string.manet), -4353004800000L));
        addFamous(db, new Person(context.getString(R.string.abbe), -4100544000000L));
        addFamous(db, new Person(context.getString(R.string.hilbert), -3406233600000L));
        addFamous(db, new Person(context.getString(R.string.yukawa), -1986249600000L));
        addFamous(db, new Person(context.getString(R.string.hauer), -818640000000L));

        // 24 january
        addFamous(db, new Person(context.getString(R.string.congreve), -9465033600000L));
        addFamous(db, new Person(context.getString(R.string.beaumarchais), -7508592000000L));
        addFamous(db, new Person(context.getString(R.string.hoffmann), -6120057600000L));
        addFamous(db, new Person(context.getString(R.string.surikov), -3847996800000L));
        addFamous(db, new Person(context.getString(R.string.shechtman), -913161600000L));

        // 25 january
        addFamous(db, new Person(context.getString(R.string.lagrange), -7382275200000L));
        addFamous(db, new Person(context.getString(R.string.burns), -6656428800000L));
        addFamous(db, new Person(context.getString(R.string.shishkin), -4352832000000L));
        addFamous(db, new Person(context.getString(R.string.maugham), -3027369600000L));
        addFamous(db, new Person(context.getString(R.string.woolf), -2774908800000L));
        addFamous(db, new Person(context.getString(R.string.prigogine), -1670457600000L));
        addFamous(db, new Person(context.getString(R.string.eusebio), -881539200000L));

        // 26 january
        addFamous(db, new Person(context.getString(R.string.morita), -1544140800000L));
        addFamous(db, new Person(context.getString(R.string.newman), -1417910400000L));
        addFamous(db, new Person(context.getString(R.string.davis), -818380800000L));
        addFamous(db, new Person(context.getString(R.string.gretzky), -281836800000L));
        addFamous(db, new Person(context.getString(R.string.mourinho), -218764800000L));

        // 27 january
        addFamous(db, new Person(context.getString(R.string.neumann), -8928316800000L));
        addFamous(db, new Person(context.getString(R.string.mozart), -6750950400000L));
        addFamous(db, new Person(context.getString(R.string.schelling), -6151334400000L));
        addFamous(db, new Person(context.getString(R.string.saltykov_shchedrin), -4541961600000L));
        addFamous(db, new Person(context.getString(R.string.carroll), -4352659200000L));
        addFamous(db, new Person(context.getString(R.string.bjorndalen), 128476800000L));

        // 28 january
        addFamous(db, new Person(context.getString(R.string.borelli), -11421302400000L));
        addFamous(db, new Person(context.getString(R.string.heweliusz), -11326608000000L));
        addFamous(db, new Person(context.getString(R.string.baskerville), -8328700800000L));
        addFamous(db, new Person(context.getString(R.string.rubinstein), -2616883200000L));
        addFamous(db, new Person(context.getString(R.string.buffon), 254793600000L));

        // 29 january
        addFamous(db, new Person(context.getString(R.string.swedenborg), -8896608000000L));
        addFamous(db, new Person(context.getString(R.string.mohs), -6214233600000L));
        addFamous(db, new Person(context.getString(R.string.auber), -5930236800000L));
        addFamous(db, new Person(context.getString(R.string.shibasaburo), -3689712000000L));
        addFamous(db, new Person(context.getString(R.string.chekhov), -3468873600000L));
        addFamous(db, new Person(context.getString(R.string.rolland), -2648332800000L));

        // 30 january
        addFamous(db, new Person(context.getString(R.string.watt), -7381843200000L));
        addFamous(db, new Person(context.getString(R.string.chamisso), -5961686400000L));
        addFamous(db, new Person(context.getString(R.string.kotelnikov), -3090096000000L));
        addFamous(db, new Person(context.getString(R.string.gaidai), -1480723200000L));
        addFamous(db, new Person(context.getString(R.string.engelbart), -1417564800000L));
        addFamous(db, new Person(context.getString(R.string.brown), -1322956800000L));

        // 31 january
        addFamous(db, new Person(context.getString(R.string.schubert), -5456678400000L));
        addFamous(db, new Person(context.getString(R.string.richards), -3216240000000L));
        addFamous(db, new Person(context.getString(R.string.langmuir), -2805926400000L));
        addFamous(db, new Person(context.getString(R.string.vanga), -1859328000000L));
        addFamous(db, new Person(context.getString(R.string.timberlake), 349747200000L));

        // 1 february
        addFamous(db, new Person(context.getString(R.string.bekhterev), -3563222400000L));
        addFamous(db, new Person(context.getString(R.string.john_ford), -2395612800000L));
        addFamous(db, new Person(context.getString(R.string.gable), -2174774400000L));
        addFamous(db, new Person(context.getString(R.string.segre), -2048544000000L));
        addFamous(db, new Person(context.getString(R.string.brandon_lee), -155088000000L));

        // 2 february
        addFamous(db, new Person(context.getString(R.string.bourdon), -11168409600000L));
        addFamous(db, new Person(context.getString(R.string.boussingault), -5298825600000L));
        addFamous(db, new Person(context.getString(R.string.forel), -4068057600000L));
        addFamous(db, new Person(context.getString(R.string.chkalov), -2080080000000L));
        addFamous(db, new Person(context.getString(R.string.haasse), -1638230400000L));
        addFamous(db, new Person(context.getString(R.string.holland), -1291075200000L));

        // 3 february
        addFamous(db, new Person(context.getString(R.string.mendelssohn), -5077814400000L));
        addFamous(db, new Person(context.getString(R.string.trubner), -3752438400000L));
        addFamous(db, new Person(context.getString(R.string.fomin), -3089750400000L));
        addFamous(db, new Person(context.getString(R.string.stein), -3026592000000L));
        addFamous(db, new Person(context.getString(R.string.joachim_low), -312768000000L));

        // 4 february
        addFamous(db, new Person(context.getString(R.string.bottger), -9085392000000L));
        addFamous(db, new Person(context.getString(R.string.nemcova), -4730659200000L));
        addFamous(db, new Person(context.getString(R.string.prandtl), -2994969600000L));
        addFamous(db, new Person(context.getString(R.string.maillard), -2900275200000L));
        addFamous(db, new Person(context.getString(R.string.tombaugh), -2016748800000L));

        // 5 february
        addFamous(db, new Person(context.getString(R.string.runeberg), -5235494400000L));
        addFamous(db, new Person(context.getString(R.string.maxim), -4099420800000L));
        addFamous(db, new Person(context.getString(R.string.dunlop), -4099420800000L));
        addFamous(db, new Person(context.getString(R.string.teike), -3342038400000L));
        addFamous(db, new Person(context.getString(R.string.citroen), -2900188800000L));
        addFamous(db, new Person(context.getString(R.string.voisin), -2837116800000L));
        addFamous(db, new Person(context.getString(R.string.cristiano_ronaldo), 476409600000L));
        addFamous(db, new Person(context.getString(R.string.neymar), 697248000000L));

        // 6 february
        addFamous(db, new Person(context.getString(R.string.heinecken), -7854537600000L));
        addFamous(db, new Person(context.getString(R.string.zelinsky), -3436560000000L));
        addFamous(db, new Person(context.getString(R.string.bragg), -2363644800000L));
        addFamous(db, new Person(context.getString(R.string.truffaut), -1196121600000L));
        addFamous(db, new Person(context.getString(R.string.bob_marley), -785808000000L));

        // 7 february
        addFamous(db, new Person(context.getString(R.string.dickens), -4982860800000L));
        addFamous(db, new Person(context.getString(R.string.alfred_adler), -3152476800000L));
        addFamous(db, new Person(context.getString(R.string.sinclair_lewis), -2679091200000L));
        addFamous(db, new Person(context.getString(R.string.chizhevsky), -2300400000000L));
        addFamous(db, new Person(context.getString(R.string.euler), -2048025600000L));
        addFamous(db, new Person(context.getString(R.string.desmond_doss), -1606262400000L));
        addFamous(db, new Person(context.getString(R.string.kutcher), 255657600000L));

        // 8 february
        addFamous(db, new Person(context.getString(R.string.bernoulli), -8517052800000L));
        addFamous(db, new Person(context.getString(R.string.courtois), -6087139200000L));
        addFamous(db, new Person(context.getString(R.string.jules_verne), -4477852800000L));
        addFamous(db, new Person(context.getString(R.string.mendeleev), -4477852800000L));
        addFamous(db, new Person(context.getString(R.string.carlson), -2016403200000L));
        addFamous(db, new Person(context.getString(R.string.williams), -1195948800000L));

        // 9 february
        addFamous(db, new Person(context.getString(R.string.navai), -16689456000000L));
        addFamous(db, new Person(context.getString(R.string.valisy_zhukovsky), -5897750400000L));
        addFamous(db, new Person(context.getString(R.string.maybach), -3909686400000L));
        addFamous(db, new Person(context.getString(R.string.soseki), -3246998400000L));
        addFamous(db, new Person(context.getString(R.string.berg), -2678918400000L));
        addFamous(db, new Person(context.getString(R.string.valier), -2363385600000L));
        addFamous(db, new Person(context.getString(R.string.monod), -1890086400000L));

        // 10 february
        addFamous(db, new Person(context.getString(R.string.molter), -8643110400000L));
        addFamous(db, new Person(context.getString(R.string.lamb), -6150124800000L));
        addFamous(db, new Person(context.getString(R.string.navier), -5834505600000L));
        addFamous(db, new Person(context.getString(R.string.pasternak), -2521065600000L));
        addFamous(db, new Person(context.getString(R.string.brecht), -2268604800000L));

        // 11 february
        addFamous(db, new Person(context.getString(R.string.talbot), -5361120000000L));
        addFamous(db, new Person(context.getString(R.string.edison), -3877977600000L));
        addFamous(db, new Person(context.getString(R.string.henry), -2615673600000L));
        addFamous(db, new Person(context.getString(R.string.sheldon), -1668988800000L));
        addFamous(db, new Person(context.getString(R.string.nielsen), -1384992000000L));
        addFamous(db, new Person(context.getString(R.string.aniston), -27993600000L));

        // 12 february
        addFamous(db, new Person(context.getString(R.string.gottsched), -8516707200000L));
        addFamous(db, new Person(context.getString(R.string.darwin), -5077036800000L));
        addFamous(db, new Person(context.getString(R.string.lincoln), -5077036800000L));
        addFamous(db, new Person(context.getString(R.string.roerich), -2868048000000L));
        addFamous(db, new Person(context.getString(R.string.anna_pavlova), -2804889600000L));
        addFamous(db, new Person(context.getString(R.string.byung_chul), -1889827200000L));

        // 13 february
        addFamous(db, new Person(context.getString(R.string.malthus), -6433862400000L));
        addFamous(db, new Person(context.getString(R.string.krylov), -6339168000000L));
        addFamous(db, new Person(context.getString(R.string.chaliapin), -3057264000000L));
        addFamous(db, new Person(context.getString(R.string.shockley), -1889740800000L));
        addFamous(db, new Person(context.getString(R.string.collina), -311904000000L));
        addFamous(db, new Person(context.getString(R.string.robbie_williams), 129945600000L));

        // 14 february
        addFamous(db, new Person(context.getString(R.string.alberti), -17856720000000L));
        addFamous(db, new Person(context.getString(R.string.babur), -15363648000000L));
        addFamous(db, new Person(context.getString(R.string.ferris), -3499027200000L));
        addFamous(db, new Person(context.getString(R.string.germi), -1763424000000L));
        addFamous(db, new Person(context.getString(R.string.sergey_kapitsa), -1321660800000L));

        // 15 february
        addFamous(db, new Person(context.getString(R.string.galilei), -12807417600000L));
        addFamous(db, new Person(context.getString(R.string.praetorius), -12586492800000L));
        addFamous(db, new Person(context.getString(R.string.bentham), -7001769600000L));
        addFamous(db, new Person(context.getString(R.string.stoney), -4540320000000L));
        addFamous(db, new Person(context.getString(R.string.guillaume), -3435782400000L));
        addFamous(db, new Person(context.getString(R.string.whitehead), -3435782400000L));
        addFamous(db, new Person(context.getString(R.string.barrymore), -2773094400000L));

        // 16 february
        addFamous(db, new Person(context.getString(R.string.bouguer), -8579433600000L));
        addFamous(db, new Person(context.getString(R.string.bodoni), -7254144000000L));
        addFamous(db, new Person(context.getString(R.string.galton), -4666464000000L));
        addFamous(db, new Person(context.getString(R.string.haeckel), -4287772800000L));
        addFamous(db, new Person(context.getString(R.string.rossi), 287971200000L));

        // 17 february
        addFamous(db, new Person(context.getString(R.string.laennec), -5960131200000L));
        addFamous(db, new Person(context.getString(R.string.beilstein), -4161456000000L));
        addFamous(db, new Person(context.getString(R.string.john_watson), -3025382400000L));
        addFamous(db, new Person(context.getString(R.string.fisher), -2520460800000L));
        addFamous(db, new Person(context.getString(R.string.michael_bay), -153705600000L));

        // 18 february
        addFamous(db, new Person(context.getString(R.string.volta), -7096118400000L));
        addFamous(db, new Person(context.getString(R.string.bates), -4571596800000L));
        addFamous(db, new Person(context.getString(R.string.ernst_mach), -4161369600000L));
        addFamous(db, new Person(context.getString(R.string.ferrari), -2267913600000L));
        addFamous(db, new Person(context.getString(R.string.yoko_ono), -1163462400000L));
        addFamous(db, new Person(context.getString(R.string.travolta), -500774400000L));

        // 19 february
        addFamous(db, new Person(context.getString(R.string.copernicus), -15678748800000L));
        addFamous(db, new Person(context.getString(R.string.boccherini), -7159190400000L));
        addFamous(db, new Person(context.getString(R.string.murchison), -5612889600000L));
        addFamous(db, new Person(context.getString(R.string.ducommun), -4319049600000L));
        addFamous(db, new Person(context.getString(R.string.arrhenius), -3498595200000L));
        addFamous(db, new Person(context.getString(R.string.del_toro), -90460800000L));

        // 20 february
        addFamous(db, new Person(context.getString(R.string.reil), -6654182400000L));
        addFamous(db, new Person(context.getString(R.string.boltzmann), -3971894400000L));
        addFamous(db, new Person(context.getString(R.string.crawford), -121910400000L));
        addFamous(db, new Person(context.getString(R.string.cobain), -90374400000L));
        addFamous(db, new Person(context.getString(R.string.rihanna), 572313600000L));

        // 21 february
        addFamous(db, new Person(context.getString(R.string.delibes), -4224268800000L));
        addFamous(db, new Person(context.getString(R.string.calment), -2993500800000L));
        addFamous(db, new Person(context.getString(R.string.sullivan), -2457043200000L));
        addFamous(db, new Person(context.getString(R.string.henrik_dam), -2362348800000L));
        addFamous(db, new Person(context.getString(R.string.givenchy), -1352592000000L));
        addFamous(db, new Person(context.getString(R.string.palahniuk), -248054400000L));

        // 22 february
        addFamous(db, new Person(context.getString(R.string.washington), -7506086400000L));
        addFamous(db, new Person(context.getString(R.string.schopenhauer), -5738860800000L));
        addFamous(db, new Person(context.getString(R.string.quetelet), -5486400000000L));
        addFamous(db, new Person(context.getString(R.string.janssen), -4602873600000L));
        addFamous(db, new Person(context.getString(R.string.hertz), -3561408000000L));
        addFamous(db, new Person(context.getString(R.string.bronsted), -2867184000000L));

        // 23 february
        addFamous(db, new Person(context.getString(R.string.handel), -8989056000000L));
        addFamous(db, new Person(context.getString(R.string.chambers), -7789996800000L));
        addFamous(db, new Person(context.getString(R.string.rothschild), -7127308800000L));
        addFamous(db, new Person(context.getString(R.string.malevich), -2867097600000L));
        addFamous(db, new Person(context.getString(R.string.jaspers), -2740867200000L));
        addFamous(db, new Person(context.getString(R.string.blunt), 414806400000L));

        // 24 february
        addFamous(db, new Person(context.getString(R.string.banks), -7158758400000L));
        addFamous(db, new Person(context.getString(R.string.grimm), -6369840000000L));
        addFamous(db, new Person(context.getString(R.string.borgman), -3813696000000L));
        addFamous(db, new Person(context.getString(R.string.freda), -1920326400000L));
        addFamous(db, new Person(context.getString(R.string.legrand), -1194566400000L));
        addFamous(db, new Person(context.getString(R.string.steve_jobs), -468720000000L));

        // 25 february
        addFamous(db, new Person(context.getString(R.string.battuta), -21011529600000L));
        addFamous(db, new Person(context.getString(R.string.goldoni), -8294745600000L));
        addFamous(db, new Person(context.getString(R.string.renoir), -4066070400000L));
        addFamous(db, new Person(context.getString(R.string.karl_may), -4034534400000L));
        addFamous(db, new Person(context.getString(R.string.caruso), -3056227200000L));
        addFamous(db, new Person(context.getString(R.string.burgess), -1667779200000L));

        // 26 february
        addFamous(db, new Person(context.getString(R.string.marlowe), -12806467200000L));
        addFamous(db, new Person(context.getString(R.string.arago), -5801587200000L));
        addFamous(db, new Person(context.getString(R.string.hugo), -5296752000000L));
        addFamous(db, new Person(context.getString(R.string.levi_strauss), -4444675200000L));
        addFamous(db, new Person(context.getString(R.string.flammarion), -4034448000000L));

        // 27 february
        addFamous(db, new Person(context.getString(R.string.constantine), -53578800000000L));
        addFamous(db, new Person(context.getString(R.string.longfellow), -5138899200000L));
        addFamous(db, new Person(context.getString(R.string.ge), -4381516800000L));
        addFamous(db, new Person(context.getString(R.string.best), -2235600000000L));
        addFamous(db, new Person(context.getString(R.string.steinbeck), -2140992000000L));
        addFamous(db, new Person(context.getString(R.string.taylor), -1194307200000L));

        // 28 february
        addFamous(db, new Person(context.getString(R.string.montaigne), -13784515200000L));
        addFamous(db, new Person(context.getString(R.string.reaumur), -9051782400000L));
        addFamous(db, new Person(context.getString(R.string.renan), -4633891200000L));
        addFamous(db, new Person(context.getString(R.string.pauling), -2172441600000L));
        addFamous(db, new Person(context.getString(R.string.gehry), -1288828800000L));
        addFamous(db, new Person(context.getString(R.string.cooper), -1257292800000L));
        addFamous(db, new Person(context.getString(R.string.vodianova), 383702400000L));

        // 29 february
        addFamous(db, new Person(context.getString(R.string.klenze), -5864486400000L));
        addFamous(db, new Person(context.getString(R.string.rossini), -5612025600000L));
        addFamous(db, new Person(context.getString(R.string.john_holland), -4097347200000L));
        addFamous(db, new Person(context.getString(R.string.рollerith), -3466195200000L));
        addFamous(db, new Person(context.getString(R.string.papert), -1320364800000L));

        // 1 march
        addFamous(db, new Person(context.getString(R.string.botticelli), -16561497600000L));
        addFamous(db, new Person(context.getString(R.string.chopin), -2867184000000L));
        addFamous(db, new Person(context.getString(R.string.akutagawa), -2456265600000L));
        addFamous(db, new Person(context.getString(R.string.miller), -2077747200000L));
        addFamous(db, new Person(context.getString(R.string.snyder), -121132800000L));
        addFamous(db, new Person(context.getString(R.string.bieber), 762480000000L));

        // 2 march
        addFamous(db, new Person(context.getString(R.string.dekker), -4728326400000L));
        addFamous(db, new Person(context.getString(R.string.smetana), -4602096000000L));
        addFamous(db, new Person(context.getString(R.string.irving), -878428800000L));
        addFamous(db, new Person(context.getString(R.string.bon_jovi), -247276800000L));
        addFamous(db, new Person(context.getString(R.string.craig), -57888000000L));

        // 3 march
        addFamous(db, new Person(context.getString(R.string.pullman), -4381171200000L));
        addFamous(db, new Person(context.getString(R.string.cantor), -3939321600000L));
        addFamous(db, new Person(context.getString(R.string.bell), -3876249600000L));
        addFamous(db, new Person(context.getString(R.string.frisch), -2361484800000L));
        addFamous(db, new Person(context.getString(R.string.kornberg), -1635724800000L));

        // 4 march
        addFamous(db, new Person(context.getString(R.string.vivaldi), -9209203200000L));
        addFamous(db, new Person(context.getString(R.string.raeburn), -6747753600000L));
        addFamous(db, new Person(context.getString(R.string.gamow), -2077401600000L));
        addFamous(db, new Person(context.getString(R.string.veksler), -1982793600000L));
        addFamous(db, new Person(context.getString(R.string.mauriat), -1414713600000L));

        // 5 march
        addFamous(db, new Person(context.getString(R.string.mercator), -14446771200000L));
        addFamous(db, new Person(context.getString(R.string.tiepolo), -8641036800000L));
        addFamous(db, new Person(context.getString(R.string.marey), -4412534400000L));
        addFamous(db, new Person(context.getString(R.string.tarrasch), -3402691200000L));
        addFamous(db, new Person(context.getString(R.string.ando), -1888012800000L));
        addFamous(db, new Person(context.getString(R.string.tobin), -1635552000000L));

        // 6 march
        addFamous(db, new Person(context.getString(R.string.michelangelo), -15614380800000L));
        addFamous(db, new Person(context.getString(R.string.bergerac), -11070950400000L));
        addFamous(db, new Person(context.getString(R.string.elizabeth_browning), -5169830400000L));
        addFamous(db, new Person(context.getString(R.string.jerzy_lec), -1919462400000L));
        addFamous(db, new Person(context.getString(R.string.marquez), -1351468800000L));
        addFamous(db, new Person(context.getString(R.string.tereshkova), -1035849600000L));
        addFamous(db, new Person(context.getString(R.string.shaquille), 68688000000L));

        // 7 march
        addFamous(db, new Person(context.getString(R.string.rob_roy), -9429868800000L));
        addFamous(db, new Person(context.getString(R.string.niepce), -6463497600000L));
        addFamous(db, new Person(context.getString(R.string.palmer), -3938976000000L));
        addFamous(db, new Person(context.getString(R.string.montesquiou), -3623443200000L));
        addFamous(db, new Person(context.getString(R.string.mondrian), -3086899200000L));
        addFamous(db, new Person(context.getString(R.string.ravel), -2992291200000L));
        addFamous(db, new Person(context.getString(R.string.kobo_abe), -1445990400000L));

        // 8 march
        addFamous(db, new Person(context.getString(R.string.fothergill), -8135942400000L));
        addFamous(db, new Person(context.getString(R.string.potocki), -6589641600000L));
        addFamous(db, new Person(context.getString(R.string.ignacy), -4664736000000L));
        addFamous(db, new Person(context.getString(R.string.thompson), -3844195200000L));
        addFamous(db, new Person(context.getString(R.string.otto_hahn), -2865974400000L));
        addFamous(db, new Person(context.getString(R.string.kendall), -2645049600000L));
        addFamous(db, new Person(context.getString(R.string.aiken), -2203286400000L));

        // 9 march
        addFamous(db, new Person(context.getString(R.string.vespucci), -16276809600000L));
        addFamous(db, new Person(context.getString(R.string.shevchenko), -4917110400000L));
        addFamous(db, new Person(context.getString(R.string.barragan), -2140128000000L));
        addFamous(db, new Person(context.getString(R.string.kohn), -1477440000000L));
        addFamous(db, new Person(context.getString(R.string.gagarin), -1130284800000L));

        // 10 march
        addFamous(db, new Person(context.getString(R.string.schlegel), -6242313600000L));
        addFamous(db, new Person(context.getString(R.string.eichendorff), -5737392000000L));
        addFamous(db, new Person(context.getString(R.string.blatter), -1067040000000L));
        addFamous(db, new Person(context.getString(R.string.norris), -940809600000L));
        addFamous(db, new Person(context.getString(R.string.bin_laden), -404352000000L));
        addFamous(db, new Person(context.getString(R.string.stone), -372816000000L));

        // 11 march
        addFamous(db, new Person(context.getString(R.string.tasso), -13436409600000L));
        addFamous(db, new Person(context.getString(R.string.verrier), -5011632000000L));
        addFamous(db, new Person(context.getString(R.string.bertrand), -4664476800000L));
        addFamous(db, new Person(context.getString(R.string.vannevar_bush), -2518560000000L));
        addFamous(db, new Person(context.getString(R.string.bloembergen), -1571875200000L));
        addFamous(db, new Person(context.getString(R.string.adams), -562032000000L));
        addFamous(db, new Person(context.getString(R.string.knoxville), 37497600000L));

        // 12 march
        addFamous(db, new Person(context.getString(R.string.notre), -11259734400000L));
        addFamous(db, new Person(context.getString(R.string.berkeley), -8987587200000L));
        addFamous(db, new Person(context.getString(R.string.bazhenov), -7346678400000L));
        addFamous(db, new Person(context.getString(R.string.kirchhoff), -4601232000000L));
        addFamous(db, new Person(context.getString(R.string.newcomb), -4254163200000L));
        addFamous(db, new Person(context.getString(R.string.perkin), -4159468800000L));
        addFamous(db, new Person(context.getString(R.string.vernadsky), -3370550400000L));

        // 13 march
        addFamous(db, new Person(context.getString(R.string.bonnet), -7883049600000L));
        addFamous(db, new Person(context.getString(R.string.lowell), -3622924800000L));
        addFamous(db, new Person(context.getString(R.string.eliade), -1982016000000L));
        addFamous(db, new Person(context.getString(R.string.hubbard), -1855785600000L));
        addFamous(db, new Person(context.getString(R.string.scatman), -877478400000L));

        // 14 march
        addFamous(db, new Person(context.getString(R.string.telemann), -9113644800000L));
        addFamous(db, new Person(context.getString(R.string.strauss), -5232211200000L));
        addFamous(db, new Person(context.getString(R.string.banville), -4632681600000L));
        addFamous(db, new Person(context.getString(R.string.schiaparelli), -4253990400000L));
        addFamous(db, new Person(context.getString(R.string.ehrlich), -3654374400000L));
        addFamous(db, new Person(context.getString(R.string.einstein), -2865456000000L));
        addFamous(db, new Person(context.getString(R.string.caine), -1161388800000L));

        // 15 march
        addFamous(db, new Person(context.getString(R.string.sylvius), -11227939200000L));
        addFamous(db, new Person(context.getString(R.string.loschmidt), -4695667200000L));
        addFamous(db, new Person(context.getString(R.string.heyse), -4411670400000L));
        addFamous(db, new Person(context.getString(R.string.behring), -3654288000000L));
        addFamous(db, new Person(context.getString(R.string.haffkine), -3464899200000L));
        addFamous(db, new Person(context.getString(R.string.alferov), -1255996800000L));

        // 16 march
        addFamous(db, new Person(context.getString(R.string.georg_ohm), -5705337600000L));
        addFamous(db, new Person(context.getString(R.string.prudhomme), -4127587200000L));
        addFamous(db, new Person(context.getString(R.string.beijerinck), -3748896000000L));
        addFamous(db, new Person(context.getString(R.string.yayser), -3685737600000L));
        addFamous(db, new Person(context.getString(R.string.damadian), -1066521600000L));
        addFamous(db, new Person(context.getString(R.string.stallman), -530064000000L));

        // 17 march
        addFamous(db, new Person(context.getString(R.string.daimler), -4285267200000L));
        addFamous(db, new Person(context.getString(R.string.vrubel), -3590956800000L));
        addFamous(db, new Person(context.getString(R.string.hess), -2802038400000L));
        addFamous(db, new Person(context.getString(R.string.nureyev), -1003363200000L));
        addFamous(db, new Person(context.getString(R.string.gibson), -687744000000L));
        addFamous(db, new Person(context.getString(R.string.russell), -593136000000L));

        // 18 march
        addFamous(db, new Person(context.getString(R.string.steiner), -5484240000000L));
        addFamous(db, new Person(context.getString(R.string.hebbel), -4947868800000L));
        addFamous(db, new Person(context.getString(R.string.diesel), -3527798400000L));
        addFamous(db, new Person(context.getString(R.string.stekel), -3212179200000L));
        addFamous(db, new Person(context.getString(R.string.koffka), -2644185600000L));
        addFamous(db, new Person(context.getString(R.string.besson), -340588800000L));

        // 19 march
        addFamous(db, new Person(context.getString(R.string.burton), -4695321600000L));
        addFamous(db, new Person(context.getString(R.string.wheeler), -3306787200000L));
        addFamous(db, new Person(context.getString(R.string.reger), -3054326400000L));
        addFamous(db, new Person(context.getString(R.string.haworth), -2738793600000L));
        addFamous(db, new Person(context.getString(R.string.joliot_curie), -2202336000000L));
        addFamous(db, new Person(context.getString(R.string.molina), -845424000000L));

        // 20 march
        addFamous(db, new Person(context.getString(R.string.ibsen), -4474310400000L));
        addFamous(db, new Person(context.getString(R.string.gigli), -2517782400000L));
        addFamous(db, new Person(context.getString(R.string.skinner), -2076019200000L));
        addFamous(db, new Person(context.getString(R.string.cattell), -2044483200000L));
        addFamous(db, new Person(context.getString(R.string.neher), -813715200000L));
        addFamous(db, new Person(context.getString(R.string.bennington), 196128000000L));

        // 21 march
        addFamous(db, new Person(context.getString(R.string.fourier), -6367593600000L));
        addFamous(db, new Person(context.getString(R.string.mozhaysky), -4568918400000L));
        addFamous(db, new Person(context.getString(R.string.senna), -308707200000L));
        addFamous(db, new Person(context.getString(R.string.gilbert), -1192320000000L));
        addFamous(db, new Person(context.getString(R.string.ronaldinho), 322444800000L));

        // 22 march
        addFamous(db, new Person(context.getString(R.string.van_dyck), -11700720000000L));
        addFamous(db, new Person(context.getString(R.string.pelletier), -5736355200000L));
        addFamous(db, new Person(context.getString(R.string.lysenko), -4032374400000L));
        addFamous(db, new Person(context.getString(R.string.millikan), -3211833600000L));
        addFamous(db, new Person(context.getString(R.string.richter), -1223856000000L));
        addFamous(db, new Person(context.getString(R.string.webber), -687312000000L));

        // 23 march
        addFamous(db, new Person(context.getString(R.string.laplace), -6967036800000L));
        addFamous(db, new Person(context.getString(R.string.du_gard), -2801520000000L));
        addFamous(db, new Person(context.getString(R.string.noether), -2769984000000L));
        addFamous(db, new Person(context.getString(R.string.juan_gris), -2612217600000L));
        addFamous(db, new Person(context.getString(R.string.fromm), -2201990400000L));
        addFamous(db, new Person(context.getString(R.string.kurosawa), -1886457600000L));
        addFamous(db, new Person(context.getString(R.string.von_braun), -1823299200000L));

        // 24 march
        addFamous(db, new Person(context.getString(R.string.agricola), -15013209600000L));
        addFamous(db, new Person(context.getString(R.string.priestley), -7471872000000L));
        addFamous(db, new Person(context.getString(R.string.morris), -4284662400000L));
        addFamous(db, new Person(context.getString(R.string.houdini), -3022358400000L));
        addFamous(db, new Person(context.getString(R.string.dario_fo), -1381449600000L));
        addFamous(db, new Person(context.getString(R.string.ballmer), -434678400000L));
        addFamous(db, new Person(context.getString(R.string.jim_parsons), 101779200000L));

        // 25 march
        addFamous(db, new Person(context.getString(R.string.amici), -5799254400000L));
        addFamous(db, new Person(context.getString(R.string.toscanini), -3243196800000L));
        addFamous(db, new Person(context.getString(R.string.aretha_franklin), -876441600000L));
        addFamous(db, new Person(context.getString(R.string.elton_john), -718675200000L));
        addFamous(db, new Person(context.getString(R.string.parker), -150595200000L));

        // 26 march
        addFamous(db, new Person(context.getString(R.string.gesner), -14318726400000L));
        addFamous(db, new Person(context.getString(R.string.prorok_divis), -8576150400000L));
        addFamous(db, new Person(context.getString(R.string.benjamin_thompson), -6840547200000L));
        addFamous(db, new Person(context.getString(R.string.feddersen), -4347561600000L));
        addFamous(db, new Person(context.getString(R.string.frost), -3022185600000L));
        addFamous(db, new Person(context.getString(R.string.tennessee_williams), -1854662400000L));
        addFamous(db, new Person(context.getString(R.string.katz), -1854662400000L));
        addFamous(db, new Person(context.getString(R.string.anfinsen), -1696809600000L));
        addFamous(db, new Person(context.getString(R.string.nimoy), -1223510400000L));
        addFamous(db, new Person(context.getString(R.string.tinto_brass), -1160352000000L));

        // 27 march
        addFamous(db, new Person(context.getString(R.string.hittorf), -4599936000000L));
        addFamous(db, new Person(context.getString(R.string.rontgen), -3937248000000L));
        addFamous(db, new Person(context.getString(R.string.wallach), -3874176000000L));
        addFamous(db, new Person(context.getString(R.string.pearson), -3558556800000L));
        addFamous(db, new Person(context.getString(R.string.henry_royce), -3369254400000L));
        addFamous(db, new Person(context.getString(R.string.steichen), -2864332800000L));
        addFamous(db, new Person(context.getString(R.string.tarantino), -213580800000L));

        // 28 march
        addFamous(db, new Person(context.getString(R.string.raphael), -15360019200000L));
        addFamous(db, new Person(context.getString(R.string.comenius), -11921040000000L));
        addFamous(db, new Person(context.getString(R.string.tamburini), -5357232000000L));
        addFamous(db, new Person(context.getString(R.string.maxim_gorky), -3211315200000L));
        addFamous(db, new Person(context.getString(R.string.heymans), -2453932800000L));
        addFamous(db, new Person(context.getString(R.string.brzezinski), -1317945600000L));
        addFamous(db, new Person(context.getString(R.string.friedman), -1254873600000L));
        addFamous(db, new Person(context.getString(R.string.lady_gaga), 512352000000L));

        // 29 march
        addFamous(db, new Person(context.getString(R.string.santorio), -12898396800000L));
        addFamous(db, new Person(context.getString(R.string.musaus), -7408368000000L));
        addFamous(db, new Person(context.getString(R.string.schneider), -5199379200000L));
        addFamous(db, new Person(context.getString(R.string.walton), -1633478400000L));
        addFamous(db, new Person(context.getString(R.string.vane), -1349481600000L));
        addFamous(db, new Person(context.getString(R.string.gleeson), -465868800000L));

        // 30 march
        addFamous(db, new Person(context.getString(R.string.goya), -7061126400000L));
        addFamous(db, new Person(context.getString(R.string.rozier), -6808665600000L));
        addFamous(db, new Person(context.getString(R.string.bunsen), -5009990400000L));
        addFamous(db, new Person(context.getString(R.string.verlaine), -3968524800000L));
        addFamous(db, new Person(context.getString(R.string.van_gogh), -3684528000000L));
        addFamous(db, new Person(context.getString(R.string.sharpe), -1317772800000L));
        addFamous(db, new Person(context.getString(R.string.dion), -55468800000L));

        // 31 march
        addFamous(db, new Person(context.getString(R.string.descartes), -11794550400000L));
        addFamous(db, new Person(context.getString(R.string.marvell), -11005632000000L));
        addFamous(db, new Person(context.getString(R.string.bach), -8985945600000L));
        addFamous(db, new Person(context.getString(R.string.haydn), -7502803200000L));
        addFamous(db, new Person(context.getString(R.string.chukovsky), -2769292800000L));
        addFamous(db, new Person(context.getString(R.string.william_bragg), -2516832000000L));

        // 1 april
        addFamous(db, new Person(context.getString(R.string.harvey), -12361680000000L));
        addFamous(db, new Person(context.getString(R.string.germain), -6114182400000L));
        addFamous(db, new Person(context.getString(R.string.gogol), -5072889600000L));
        addFamous(db, new Person(context.getString(R.string.zsigmondy), -3305664000000L));
        addFamous(db, new Person(context.getString(R.string.busoni), -3274128000000L));
        addFamous(db, new Person(context.getString(R.string.rachmaninoff), -3053203200000L));
        addFamous(db, new Person(context.getString(R.string.lon_chaney), -2737670400000L));
        addFamous(db, new Person(context.getString(R.string.maslow), -1948752000000L));

        // 2 april
        addFamous(db, new Person(context.getString(R.string.grimaldi), -11100153600000L));
        addFamous(db, new Person(context.getString(R.string.casanova), -7723555200000L));
        addFamous(db, new Person(context.getString(R.string.andersen), -5199033600000L));
        addFamous(db, new Person(context.getString(R.string.butler), -3400272000000L));
        addFamous(db, new Person(context.getString(R.string.chrysler), -2990044800000L));
        addFamous(db, new Person(context.getString(R.string.fassbender), 228787200000L));

        // 3 april
        addFamous(db, new Person(context.getString(R.string.washington_irving), -5893171200000L));
        addFamous(db, new Person(context.getString(R.string.velde), -3368649600000L));
        addFamous(db, new Person(context.getString(R.string.jansky), -3053030400000L));
        addFamous(db, new Person(context.getString(R.string.brando), -1443657600000L));
        addFamous(db, new Person(context.getString(R.string.baldwin), -370742400000L));
        addFamous(db, new Person(context.getString(R.string.murphy), -276048000000L));

        // 4 april
        addFamous(db, new Person(context.getString(R.string.reid), -4788633600000L));
        addFamous(db, new Person(context.getString(R.string.siemens), -4630867200000L));
        addFamous(db, new Person(context.getString(R.string.simmons), -3841862400000L));
        addFamous(db, new Person(context.getString(R.string.weaving), -307497600000L));
        addFamous(db, new Person(context.getString(R.string.robert_downey), -149731200000L));
        addFamous(db, new Person(context.getString(R.string.ledger), 292032000000L));

        // 5 april
        addFamous(db, new Person(context.getString(R.string.hobbes), -12046579200000L));
        addFamous(db, new Person(context.getString(R.string.viviani), -10973664000000L));
        addFamous(db, new Person(context.getString(R.string.yale), -10121587200000L));
        addFamous(db, new Person(context.getString(R.string.spohr), -5861376000000L));
        addFamous(db, new Person(context.getString(R.string.dupre), -5009472000000L));
        addFamous(db, new Person(context.getString(R.string.nadar), -4725388800000L));
        addFamous(db, new Person(context.getString(R.string.lister), -4504550400000L));

        // 6 april
        addFamous(db, new Person(context.getString(R.string.gosse), -5040921600000L));
        addFamous(db, new Person(context.getString(R.string.moreau), -4536000000000L));
        addFamous(db, new Person(context.getString(R.string.douglas), -2453155200000L));
        addFamous(db, new Person(context.getString(R.string.lynen), -1853712000000L));
        addFamous(db, new Person(context.getString(R.string.fischer), -1569628800000L));
        addFamous(db, new Person(context.getString(R.string.james_watson), -1317168000000L));

        // 7 april
        addFamous(db, new Person(context.getString(R.string.gerard_dou), -11257488000000L));
        addFamous(db, new Person(context.getString(R.string.wordsworth), -6303052800000L));
        addFamous(db, new Person(context.getString(R.string.selmi), -4819910400000L));
        addFamous(db, new Person(context.getString(R.string.christiansen), -2484691200000L));
        addFamous(db, new Person(context.getString(R.string.holiday), -1727395200000L));
        addFamous(db, new Person(context.getString(R.string.coppola), -970012800000L));
        addFamous(db, new Person(context.getString(R.string.chan), -496627200000L));
        addFamous(db, new Person(context.getString(R.string.crowe), -181008000000L));

        // 8 april
        addFamous(db, new Person(context.getString(R.string.tartini), -8764329600000L));
        addFamous(db, new Person(context.getString(R.string.von_hofmann), -4788288000000L));
        addFamous(db, new Person(context.getString(R.string.husserl), -3494448000000L));
        addFamous(db, new Person(context.getString(R.string.hicks), -2074377600000L));
        addFamous(db, new Person(context.getString(R.string.calvin), -1853539200000L));

        // 9 april
        addFamous(db, new Person(context.getString(R.string.timur), -19997884800000L));
        addFamous(db, new Person(context.getString(R.string.boehm), -5545497600000L));
        addFamous(db, new Person(context.getString(R.string.brunel), -5166892800000L));
        addFamous(db, new Person(context.getString(R.string.muybridge), -4409510400000L));
        addFamous(db, new Person(context.getString(R.string.pincus), -2105913600000L));
        addFamous(db, new Person(context.getString(R.string.eckert), -1600992000000L));
        addFamous(db, new Person(context.getString(R.string.hefner), -1380067200000L));
        addFamous(db, new Person(context.getString(R.string.belmondo), -1159142400000L));
        addFamous(db, new Person(context.getString(R.string.stewart), 639619200000L));

        // 10 april
        addFamous(db, new Person(context.getString(R.string.grotius), -12204000000000L));
        addFamous(db, new Person(context.getString(R.string.tschirnhaus), -10058083200000L));
        addFamous(db, new Person(context.getString(R.string.heinicke), -7659792000000L));
        addFamous(db, new Person(context.getString(R.string.pulitzer), -3872966400000L));
        addFamous(db, new Person(context.getString(R.string.seagal), -559440000000L));
        addFamous(db, new Person(context.getString(R.string.canet), 103248000000L));

        // 11 april
        addFamous(db, new Person(context.getString(R.string.parkinson), -6144940800000L));
        addFamous(db, new Person(context.getString(R.string.bertini), -2578953600000L));
        addFamous(db, new Person(context.getString(R.string.julian), -2231884800000L));
        addFamous(db, new Person(context.getString(R.string.lavey), -1253664000000L));
        addFamous(db, new Person(context.getString(R.string.wiles), -527817600000L));

        // 12 april
        addFamous(db, new Person(context.getString(R.string.meyerhof), -2705097600000L));
        addFamous(db, new Person(context.getString(R.string.lily_pons), -2263334400000L));
        addFamous(db, new Person(context.getString(R.string.tinbergen), -2105654400000L));
        addFamous(db, new Person(context.getString(R.string.cabalie), -1158883200000L));
        addFamous(db, new Person(context.getString(R.string.hancock), -937958400000L));
        addFamous(db, new Person(context.getString(R.string.garcia), -433036800000L));

        // 13 april
        addFamous(db, new Person(context.getString(R.string.favre), -14632790400000L));
        addFamous(db, new Person(context.getString(R.string.fawkes), -12613104000000L));
        addFamous(db, new Person(context.getString(R.string.bramah), -6996758400000L));
        addFamous(db, new Person(context.getString(R.string.trevithick), -6270998400000L));
        addFamous(db, new Person(context.getString(R.string.meucci), -5103388800000L));
        addFamous(db, new Person(context.getString(R.string.lacan), -2168640000000L));
        addFamous(db, new Person(context.getString(R.string.beckett), -2010873600000L));

        // 14 april
        addFamous(db, new Person(context.getString(R.string.ortelius), -13970016000000L));
        addFamous(db, new Person(context.getString(R.string.huygens), -10751961600000L));
        addFamous(db, new Person(context.getString(R.string.fonvizin), -7091366400000L));
        addFamous(db, new Person(context.getString(R.string.rohlfs), -4377542400000L));
        addFamous(db, new Person(context.getString(R.string.horsley), -3557001600000L));
        addFamous(db, new Person(context.getString(R.string.matsumoto), -148867200000L));

        // 15 april
        addFamous(db, new Person(context.getString(R.string.da_vinci), -16336684800000L));
        addFamous(db, new Person(context.getString(R.string.leonhard_euler), -8290512000000L));
        addFamous(db, new Person(context.getString(R.string.cullen), -8195817600000L));
        addFamous(db, new Person(context.getString(R.string.busch), -4345833600000L));
        addFamous(db, new Person(context.getString(R.string.gumilyov), -2641766400000L));
        addFamous(db, new Person(context.getString(R.string.emma_thompson), -338169600000L));
        addFamous(db, new Person(context.getString(R.string.emma_watson), 640137600000L));

        // 16 april
        addFamous(db, new Person(context.getString(R.string.apianus), -14979686400000L));
        addFamous(db, new Person(context.getString(R.string.hadley), -9079257600000L));
        addFamous(db, new Person(context.getString(R.string.eisenstein), -4629830400000L));
        addFamous(db, new Person(context.getString(R.string.france), -3967056000000L));
        addFamous(db, new Person(context.getString(R.string.wright), -3241296000000L));
        addFamous(db, new Person(context.getString(R.string.chaplin), -2546985600000L));

        // 17 april
        addFamous(db, new Person(context.getString(R.string.morgan), -4187894400000L));
        addFamous(db, new Person(context.getString(R.string.starling), -3272745600000L));
        addFamous(db, new Person(context.getString(R.string.saeverud), -2294438400000L));
        addFamous(db, new Person(context.getString(R.string.kohler), -748224000000L));
        addFamous(db, new Person(context.getString(R.string.garner), 72316800000L));
        addFamous(db, new Person(context.getString(R.string.beckham), 135388800000L));

        // 18 april
        addFamous(db, new Person(context.getString(R.string.ricardo), -6238944000000L));
        addFamous(db, new Person(context.getString(R.string.boisbaudran), -4156272000000L));
        addFamous(db, new Person(context.getString(R.string.goldstein), -937440000000L));
        addFamous(db, new Person(context.getString(R.string.sokolov), -621907200000L));
        addFamous(db, new Person(context.getString(R.string.tennant), 40780800000L));

        // 19 april
        addFamous(db, new Person(context.getString(R.string.ehrenberg), -5513097600000L));
        addFamous(db, new Person(context.getString(R.string.gerstner), -5481475200000L));
        addFamous(db, new Person(context.getString(R.string.fechner), -5323795200000L));
        addFamous(db, new Person(context.getString(R.string.hughes), -2199657600000L));
        addFamous(db, new Person(context.getString(R.string.seaborg), -1820966400000L));
        addFamous(db, new Person(context.getString(R.string.christensen), 356486400000L));

        // 20 april
        addFamous(db, new Person(context.getString(R.string.aretino), -15073948800000L));
        addFamous(db, new Person(context.getString(R.string.pinel), -7090848000000L));
        addFamous(db, new Person(context.getString(R.string.raffaelli), -3777408000000L));
        addFamous(db, new Person(context.getString(R.string.hitler), -2546640000000L));
        addFamous(db, new Person(context.getString(R.string.lloyd), -2420409600000L));
        addFamous(db, new Person(context.getString(R.string.leiris), -2168035200000L));
        addFamous(db, new Person(context.getString(R.string.muller), -1347580800000L));
        addFamous(db, new Person(context.getString(R.string.sedgwick), -842659200000L));

        // 21 april
        addFamous(db, new Person(context.getString(R.string.riebeeck), -11066976000000L));
        addFamous(db, new Person(context.getString(R.string.kulibin), -7406380800000L));
        addFamous(db, new Person(context.getString(R.string.frobel), -5923152000000L));
        addFamous(db, new Person(context.getString(R.string.starley), -4408473600000L));
        addFamous(db, new Person(context.getString(R.string.flemming), -3998246400000L));
        addFamous(db, new Person(context.getString(R.string.weber), -3335472000000L));
        addFamous(db, new Person(context.getString(R.string.bridgman), -2767478400000L));
        addFamous(db, new Person(context.getString(R.string.karrer), -2546553600000L));

        // 22 april
        addFamous(db, new Person(context.getString(R.string.fielding), -8289907200000L));
        addFamous(db, new Person(context.getString(R.string.kant), -7753363200000L));
        addFamous(db, new Person(context.getString(R.string.plante), -4282156800000L));
        addFamous(db, new Person(context.getString(R.string.eichler), -4124390400000L));
        addFamous(db, new Person(context.getString(R.string.bohr), -2609625600000L));
        addFamous(db, new Person(context.getString(R.string.mabokov), -2230934400000L));
        addFamous(db, new Person(context.getString(R.string.oppenheimer), -2073168000000L));
        addFamous(db, new Person(context.getString(R.string.mingus), -1505174400000L));

        // 23 april
        addFamous(db, new Person(context.getString(R.string.planck), -3524688000000L));
        addFamous(db, new Person(context.getString(R.string.fibiger), -3240691200000L));
        addFamous(db, new Person(context.getString(R.string.marsh), -2357078400000L));
        addFamous(db, new Person(context.getString(R.string.ohlin), -2230848000000L));
        addFamous(db, new Person(context.getString(R.string.laxness), -2136240000000L));
        addFamous(db, new Person(context.getString(R.string.cena), 230601600000L));
        addFamous(db, new Person(context.getString(R.string.patel), 640828800000L));

        // 24 april
        addFamous(db, new Person(context.getString(R.string.martini), -8321270400000L));
        addFamous(db, new Person(context.getString(R.string.cartwright), -7153660800000L));
        addFamous(db, new Person(context.getString(R.string.spitteler), -3934828800000L));
        addFamous(db, new Person(context.getString(R.string.bertillon), -3682368000000L));
        addFamous(db, new Person(context.getString(R.string.sundback), -2830291200000L));
        addFamous(db, new Person(context.getString(R.string.streisand), -873849600000L));

        // 25 april
        addFamous(db, new Person(context.getString(R.string.marc_brunel), -6333033600000L));
        addFamous(db, new Person(context.getString(R.string.klein), -3808512000000L));
        addFamous(db, new Person(context.getString(R.string.felix_dherelle), -3051129600000L));
        addFamous(db, new Person(context.getString(R.string.marconi), -3019593600000L));
        addFamous(db, new Person(context.getString(R.string.pauli), -2199139200000L));
        addFamous(db, new Person(context.getString(R.string.fitzgerald), -1662681600000L));
        addFamous(db, new Person(context.getString(R.string.al_pacino), -936835200000L));
        addFamous(db, new Person(context.getString(R.string.cruyff), -715996800000L));

        // 26 april
        addFamous(db, new Person(context.getString(R.string.aurelius), -58338921600000L));
        addFamous(db, new Person(context.getString(R.string.shakespeare), -12801283200000L));
        addFamous(db, new Person(context.getString(R.string.uhland), -5764953600000L));
        addFamous(db, new Person(context.getString(R.string.delacroix), -5417798400000L));
        addFamous(db, new Person(context.getString(R.string.krupp), -4976035200000L));
        addFamous(db, new Person(context.getString(R.string.billroth), -4439577600000L));
        addFamous(db, new Person(context.getString(R.string.richardson), -2861740800000L));
        addFamous(db, new Person(context.getString(R.string.wittgenstein), -2546121600000L));
        addFamous(db, new Person(context.getString(R.string.charles_richter), -2199052800000L));

        // 27 april
        addFamous(db, new Person(context.getString(R.string.kolreuter), -7468934400000L));
        addFamous(db, new Person(context.getString(R.string.wollstonecraft), -6648480000000L));
        addFamous(db, new Person(context.getString(R.string.morse), -5638636800000L));
        addFamous(db, new Person(context.getString(R.string.carothers), -2325110400000L));
        addFamous(db, new Person(context.getString(R.string.lantz), -2230502400000L));

        // 28 april
        addFamous(db, new Person(context.getString(R.string.achard), -6837696000000L));
        addFamous(db, new Person(context.getString(R.string.kraus), -3019334400000L));
        addFamous(db, new Person(context.getString(R.string.godel), -2009577600000L));
        addFamous(db, new Person(context.getString(R.string.schindler), -1946419200000L));
        addFamous(db, new Person(context.getString(R.string.lamborghini), -1693958400000L));
        addFamous(db, new Person(context.getString(R.string.harper_lee), -1378425600000L));
        addFamous(db, new Person(context.getString(R.string.yves_klein), -1315267200000L));

        // 29 april
        addFamous(db, new Person(context.getString(R.string.drais), -5827766400000L));
        addFamous(db, new Person(context.getString(R.string.poincare), -3650400000000L));
        addFamous(db, new Person(context.getString(R.string.hearst), -3366403200000L));
        addFamous(db, new Person(context.getString(R.string.urey), -2419632000000L));
        addFamous(db, new Person(context.getString(R.string.jack_williamson), -1946332800000L));
        addFamous(db, new Person(context.getString(R.string.thurman), 10195200000L));

        // 30 april
        addFamous(db, new Person(context.getString(R.string.gauss), -6080140800000L));
        addFamous(db, new Person(context.getString(R.string.bleuler), -3555619200000L));
        addFamous(db, new Person(context.getString(R.string.kuznets), -2167171200000L));
        addFamous(db, new Person(context.getString(R.string.schultz), -2135635200000L));
        addFamous(db, new Person(context.getString(R.string.shannon), -1693785600000L));
        addFamous(db, new Person(context.getString(R.string.gal_gadot), 483667200000L));

        // 1 may
        addFamous(db, new Person(context.getString(R.string.addison), -9393494400000L));
        addFamous(db, new Person(context.getString(R.string.cajal), -3713299200000L));
        addFamous(db, new Person(context.getString(R.string.chardin), -2798150400000L));
        addFamous(db, new Person(context.getString(R.string.woo), -747014400000L));
        addFamous(db, new Person(context.getString(R.string.dornan), 389059200000L));

        // 2 may
        addFamous(db, new Person(context.getString(R.string.kirche), -11602483200000L));
        addFamous(db, new Person(context.getString(R.string.jerome), -3492374400000L));
        addFamous(db, new Person(context.getString(R.string.wood), -3208291200000L));
        addFamous(db, new Person(context.getString(R.string.marshall), -2135462400000L));
        addFamous(db, new Person(context.getString(R.string.springer), -1819843200000L));
        addFamous(db, new Person(context.getString(R.string.johnson), 73612800000L));
        addFamous(db, new Person(context.getString(R.string.david_beckham), 168220800000L));

        // 3 may
        addFamous(db, new Person(context.getString(R.string.machiavelli), -15798672000000L));
        addFamous(db, new Person(context.getString(R.string.haldane), -3460665600000L));
        addFamous(db, new Person(context.getString(R.string.ekman), -3018902400000L));
        addFamous(db, new Person(context.getString(R.string.coty), -3018902400000L));
        addFamous(db, new Person(context.getString(R.string.thomson), -2450822400000L));
        addFamous(db, new Person(context.getString(R.string.kastler), -2135376000000L));

        // 4 may
        addFamous(db, new Person(context.getString(R.string.cristofori), -9929779200000L));
        addFamous(db, new Person(context.getString(R.string.borda), -7468329600000L));
        addFamous(db, new Person(context.getString(R.string.brockhaus), -6237561600000L));
        addFamous(db, new Person(context.getString(R.string.thenard), -6079795200000L));
        addFamous(db, new Person(context.getString(R.string.liddell), -3713040000000L));
        addFamous(db, new Person(context.getString(R.string.mandelstam), -2861049600000L));
        addFamous(db, new Person(context.getString(R.string.hepburn), -1283212800000L));

        // 5 may
        addFamous(db, new Person(context.getString(R.string.kierkegaard), -4943721600000L));
        addFamous(db, new Person(context.getString(R.string.marx), -4785955200000L));
        addFamous(db, new Person(context.getString(R.string.sienkiewicz), -3902342400000L));
        addFamous(db, new Person(context.getString(R.string.schawlow), -1535587200000L));
        addFamous(db, new Person(context.getString(R.string.adele), 578793600000L));

        // 6 may
        addFamous(db, new Person(context.getString(R.string.freud), -3586636800000L));
        addFamous(db, new Person(context.getString(R.string.peary), -3586636800000L));
        addFamous(db, new Person(context.getString(R.string.grignard), -3113337600000L));
        addFamous(db, new Person(context.getString(R.string.martinson), -2071958400000L));
        addFamous(db, new Person(context.getString(R.string.clooney), -273196800000L));

        // 7 may
        addFamous(db, new Person(context.getString(R.string.clairaut), -8099222400000L));
        addFamous(db, new Person(context.getString(R.string.robert_browning), -4975084800000L));
        addFamous(db, new Person(context.getString(R.string.tchaikovsky), -4091472000000L));
        addFamous(db, new Person(context.getString(R.string.tagore), -3428784000000L));
        addFamous(db, new Person(context.getString(R.string.reymont), -3239481600000L));
        addFamous(db, new Person(context.getString(R.string.land), -1914105600000L));

        // 8 may
        addFamous(db, new Person(context.getString(R.string.dunant), -4470076800000L));
        addFamous(db, new Person(context.getString(R.string.lwoff), -2134944000000L));
        addFamous(db, new Person(context.getString(R.string.fernandel), -2103408000000L));
        addFamous(db, new Person(context.getString(R.string.rossellini), -2008713600000L));
        addFamous(db, new Person(context.getString(R.string.iglesias), 168739200000L));

        // 9 may
        addFamous(db, new Person(context.getString(R.string.monge), -7057670400000L));
        addFamous(db, new Person(context.getString(R.string.opel), -4185993600000L));
        addFamous(db, new Person(context.getString(R.string.laval), -3933532800000L));
        addFamous(db, new Person(context.getString(R.string.carter), -3018384000000L));
        addFamous(db, new Person(context.getString(R.string.gasset), -2734387200000L));
        addFamous(db, new Person(context.getString(R.string.richard_day), -2324073600000L));
        addFamous(db, new Person(context.getString(R.string.eigen), -1345939200000L));

        // 10 may
        addFamous(db, new Person(context.getString(R.string.lisle), -6615734400000L));
        addFamous(db, new Person(context.getString(R.string.fresnel), -5732121600000L));
        addFamous(db, new Person(context.getString(R.string.killing), -3870374400000L));
        addFamous(db, new Person(context.getString(R.string.lipton), -3838752000000L));
        addFamous(db, new Person(context.getString(R.string.gaumont), -3333830400000L));
        addFamous(db, new Person(context.getString(R.string.barth), -2639606400000L));
        addFamous(db, new Person(context.getString(R.string.selznick), -2134771200000L));
        addFamous(db, new Person(context.getString(R.string.chapman), -462240000000L));

        // 11 may
        addFamous(db, new Person(context.getString(R.string.munchhausen), -7877952000000L));
        addFamous(db, new Person(context.getString(R.string.blumenbach), -6868108800000L));
        addFamous(db, new Person(context.getString(R.string.voynich), -3333744000000L));
        addFamous(db, new Person(context.getString(R.string.dali), -2071526400000L));
        addFamous(db, new Person(context.getString(R.string.feynman), -1629763200000L));
        addFamous(db, new Person(context.getString(R.string.dijkstra), -1251072000000L));
        addFamous(db, new Person(context.getString(R.string.iniesta), 453081600000L));

        // 12 may
        addFamous(db, new Person(context.getString(R.string.lear), -4974652800000L));
        addFamous(db, new Person(context.getString(R.string.hind), -4627584000000L));
        addFamous(db, new Person(context.getString(R.string.pirquet), -3018124800000L));
        addFamous(db, new Person(context.getString(R.string.giauque), -2355436800000L));
        addFamous(db, new Person(context.getString(R.string.voznesensky), -1156291200000L));
        addFamous(db, new Person(context.getString(R.string.devi), -2229206400000L));

        // 13 may
        addFamous(db, new Person(context.getString(R.string.nevsky), -23624092800000L));
        addFamous(db, new Person(context.getString(R.string.ross), -3554496000000L));
        addFamous(db, new Person(context.getString(R.string.braque), -2765577600000L));
        addFamous(db, new Person(context.getString(R.string.wonder), -619747200000L));
        addFamous(db, new Person(context.getString(R.string.rodman), -272592000000L));
        addFamous(db, new Person(context.getString(R.string.pattinson), 516326400000L));

        // 14 may
        addFamous(db, new Person(context.getString(R.string.gainsborough), -7656854400000L));
        addFamous(db, new Person(context.getString(R.string.steinitz), -4217097600000L));
        addFamous(db, new Person(context.getString(R.string.tsvet), -3081024000000L));
        addFamous(db, new Person(context.getString(R.string.lucas), -808963200000L));
        addFamous(db, new Person(context.getString(R.string.zemeckis), -556502400000L));
        addFamous(db, new Person(context.getString(R.string.tim_roth), -272505600000L));
        addFamous(db, new Person(context.getString(R.string.zuckerberg), 453340800000L));

        // 15 may
        addFamous(db, new Person(context.getString(R.string.mechnikov), -3933014400000L));
        addFamous(db, new Person(context.getString(R.string.vasnetsov), -3838320000000L));
        addFamous(db, new Person(context.getString(R.string.wernicke), -3838320000000L));
        addFamous(db, new Person(context.getString(R.string.baum), -3585859200000L));
        addFamous(db, new Person(context.getString(R.string.curie), -3491251200000L));
        addFamous(db, new Person(context.getString(R.string.bulgakov), -2481408000000L));

        // 16 may
        addFamous(db, new Person(context.getString(R.string.agnesi), -7940678400000L));
        addFamous(db, new Person(context.getString(R.string.vauquelin), -6520608000000L));
        addFamous(db, new Person(context.getString(R.string.david_hughes), -4374777600000L));
        addFamous(db, new Person(context.getString(R.string.fonda), -2039558400000L));
        addFamous(db, new Person(context.getString(R.string.herman), -1787097600000L));
        addFamous(db, new Person(context.getString(R.string.trejo), -808790400000L));
        addFamous(db, new Person(context.getString(R.string.brosnan), -524793600000L));
        addFamous(db, new Person(context.getString(R.string.megan_fox), 516585600000L));

        // 17 may
        addFamous(db, new Person(context.getString(R.string.jenner), -6962284800000L));
        addFamous(db, new Person(context.getString(R.string.lockyer), -4216838400000L));
        addFamous(db, new Person(context.getString(R.string.hassel), -2291846400000L));
        addFamous(db, new Person(context.getString(R.string.gabin), -2071008000000L));
        addFamous(db, new Person(context.getString(R.string.nilsson), -1629244800000L));
        addFamous(db, new Person(context.getString(R.string.hopper), -1061164800000L));

        // 18 may
        addFamous(db, new Person(context.getString(R.string.khayyam), -29083104000000L));
        addFamous(db, new Person(context.getString(R.string.clapperton), -5731430400000L));
        addFamous(db, new Person(context.getString(R.string.hofmeister), -4595443200000L));
        addFamous(db, new Person(context.getString(R.string.heaviside), -3774988800000L));
        addFamous(db, new Person(context.getString(R.string.bertrand_russell), -3080678400000L));
        addFamous(db, new Person(context.getString(R.string.vigneaud), -2165616000000L));
        addFamous(db, new Person(context.getString(R.string.cretu), -398390400000L));

        // 19 may
        addFamous(db, new Person(context.getString(R.string.evola), -2260137600000L));
        addFamous(db, new Person(context.getString(R.string.colin_chapman), -1313452800000L));
        addFamous(db, new Person(context.getString(R.string.placido), -745459200000L));
        addFamous(db, new Person(context.getString(R.string.karapetyan), -524534400000L));
        addFamous(db, new Person(context.getString(R.string.oreiro), 232848000000L));
        addFamous(db, new Person(context.getString(R.string.pirlo), 295920000000L));
        addFamous(db, new Person(context.getString(R.string.sam_smith), 706233600000L));

        // 20 may
        addFamous(db, new Person(context.getString(R.string.fabricius), -13777516800000L));
        addFamous(db, new Person(context.getString(R.string.balzac), -5384188800000L));
        addFamous(db, new Person(context.getString(R.string.passy), -4658428800000L));
        addFamous(db, new Person(context.getString(R.string.berliner), -3743280000000L));
        addFamous(db, new Person(context.getString(R.string.hewlett), -1786752000000L));
        addFamous(db, new Person(context.getString(R.string.edward_lewis), -1628985600000L));
        addFamous(db, new Person(context.getString(R.string.cher), -745372800000L));

        // 21 may
        addFamous(db, new Person(context.getString(R.string.durer), -15734044800000L));
        addFamous(db, new Person(context.getString(R.string.coriolis), -5604940800000L));
        addFamous(db, new Person(context.getString(R.string.kock), -5573404800000L));
        addFamous(db, new Person(context.getString(R.string.renault), -3995654400000L));
        addFamous(db, new Person(context.getString(R.string.einthoven), -3459110400000L));
        addFamous(db, new Person(context.getString(R.string.sakharov), -1534204800000L));

        // 22 may
        addFamous(db, new Person(context.getString(R.string.wagner), -4942252800000L));
        addFamous(db, new Person(context.getString(R.string.doyle), -3490646400000L));
        addFamous(db, new Person(context.getString(R.string.olivier), -1975968000000L));
        addFamous(db, new Person(context.getString(R.string.herge), -1975968000000L));
        addFamous(db, new Person(context.getString(R.string.herbert_brown), -1818115200000L));
        addFamous(db, new Person(context.getString(R.string.campbell), 12182400000L));

        // 23 may
        addFamous(db, new Person(context.getString(R.string.linnaeus), -8287228800000L));
        addFamous(db, new Person(context.getString(R.string.mesmer), -7435152000000L));
        addFamous(db, new Person(context.getString(R.string.lilienthal), -3837628800000L));
        addFamous(db, new Person(context.getString(R.string.fairbanks), -2733177600000L));
        addFamous(db, new Person(context.getString(R.string.lagerkvist), -2480716800000L));
        addFamous(db, new Person(context.getString(R.string.bardeen), -1944259200000L));
        addFamous(db, new Person(context.getString(R.string.moog), -1123804800000L));

        // 24 may
        addFamous(db, new Person(context.getString(R.string.pontormo), -15007939200000L));
        addFamous(db, new Person(context.getString(R.string.fahrenheit), -8949744000000L));
        addFamous(db, new Person(context.getString(R.string.sholokhov), -2038867200000L));
        addFamous(db, new Person(context.getString(R.string.brodsky), -934329600000L));
        addFamous(db, new Person(context.getString(R.string.dylan), -902793600000L));
        addFamous(db, new Person(context.getString(R.string.deakins), -650332800000L));

        // 25 may
        addFamous(db, new Person(context.getString(R.string.emerson), -5257612800000L));
        addFamous(db, new Person(context.getString(R.string.burckhardt), -4784227200000L));
        addFamous(db, new Person(context.getString(R.string.zeeman), -3300998400000L));
        addFamous(db, new Person(context.getString(R.string.steinberger), -1533859200000L));
        addFamous(db, new Person(context.getString(R.string.mckellen), -965865600000L));
        addFamous(db, new Person(context.getString(R.string.myers), -208483200000L));

        // 26 may
        addFamous(db, new Person(context.getString(R.string.petty), -10937721600000L));
        addFamous(db, new Person(context.getString(R.string.moivre), -9549187200000L));
        addFamous(db, new Person(context.getString(R.string.john_wayne), -1975622400000L));
        addFamous(db, new Person(context.getString(R.string.miles_davis), -1376006400000L));
        addFamous(db, new Person(context.getString(R.string.kevorkian), -1312848000000L));
        addFamous(db, new Person(context.getString(R.string.kravitz), -176774400000L));
        addFamous(db, new Person(context.getString(R.string.helena_carter), -113702400000L));

        // 27 may
        addFamous(db, new Person(context.getString(R.string.vanderbilt), -5541350400000L));
        addFamous(db, new Person(context.getString(R.string.duncan), -2922134400000L));
        addFamous(db, new Person(context.getString(R.string.cockcroft), -2290982400000L));
        addFamous(db, new Person(context.getString(R.string.christopher_lee), -1502150400000L));
        addFamous(db, new Person(context.getString(R.string.bettany), 44150400000L));

        // 28 may
        addFamous(db, new Person(context.getString(R.string.guillotin), -7308489600000L));
        addFamous(db, new Person(context.getString(R.string.thomas_moore), -6014649600000L));
        addFamous(db, new Person(context.getString(R.string.agassiz), -5131123200000L));
        addFamous(db, new Person(context.getString(R.string.milankovic), -2858976000000L));
        addFamous(db, new Person(context.getString(R.string.ian_fleming), -1943827200000L));
        addFamous(db, new Person(context.getString(R.string.minogue), -50371200000L));

        // 29 may
        addFamous(db, new Person(context.getString(R.string.david_bruce), -3616272000000L));
        addFamous(db, new Person(context.getString(R.string.chesterton), -3016656000000L));
        addFamous(db, new Person(context.getString(R.string.spengler), -2827267200000L));
        addFamous(db, new Person(context.getString(R.string.bob_hope), -2101593600000L));
        addFamous(db, new Person(context.getString(R.string.goldberg), -1849132800000L));
        addFamous(db, new Person(context.getString(R.string.kennedy), -1659744000000L));

        // 30 may
        addFamous(db, new Person(context.getString(R.string.hagen), -4815331200000L));
        addFamous(db, new Person(context.getString(R.string.faberge), -3900182400000L));
        addFamous(db, new Person(context.getString(R.string.thalberg), -2227651200000L));
        addFamous(db, new Person(context.getString(R.string.alfven), -1943654400000L));
        addFamous(db, new Person(context.getString(R.string.blanc), -1943654400000L));
        addFamous(db, new Person(context.getString(R.string.gerrard), 328492800000L));

        // 31 may
        addFamous(db, new Person(context.getString(R.string.tieck), -6203692800000L));
        addFamous(db, new Person(context.getString(R.string.pugni), -5288630400000L));
        addFamous(db, new Person(context.getString(R.string.pirrie), -3868560000000L));
        addFamous(db, new Person(context.getString(R.string.perse), -2606256000000L));
        addFamous(db, new Person(context.getString(R.string.allais), -2606256000000L));
        addFamous(db, new Person(context.getString(R.string.eastwood), -1249344000000L));
        addFamous(db, new Person(context.getString(R.string.jay_miner), -1186185600000L));

        // 1 june
        addFamous(db, new Person(context.getString(R.string.paer), -6266764800000L));
        addFamous(db, new Person(context.getString(R.string.carnot), -5477760000000L));
        addFamous(db, new Person(context.getString(R.string.glinka), -5225385600000L));
        addFamous(db, new Person(context.getString(R.string.monroe), -1375488000000L));
        addFamous(db, new Person(context.getString(R.string.foster), -1091491200000L));
        addFamous(db, new Person(context.getString(R.string.freeman), -1028332800000L));
        addFamous(db, new Person(context.getString(R.string.persson), 297043200000L));

        // 2 june
        addFamous(db, new Person(context.getString(R.string.de_sade), -7244899200000L));
        addFamous(db, new Person(context.getString(R.string.cagliostro), -7150291200000L));
        addFamous(db, new Person(context.getString(R.string.akimov), -6771600000000L));
        addFamous(db, new Person(context.getString(R.string.hardy), -4089225600000L));
        addFamous(db, new Person(context.getString(R.string.weissmüller), -2069625600000L));
        addFamous(db, new Person(context.getString(R.string.quinto), 234057600000L));

        // 3 june
        addFamous(db, new Person(context.getString(R.string.hutton), -7686662400000L));
        addFamous(db, new Person(context.getString(R.string.shrapnel), -6582124800000L));
        addFamous(db, new Person(context.getString(R.string.cobden), -5225212800000L));
        addFamous(db, new Person(context.getString(R.string.timiryazev), -3994531200000L));
        addFamous(db, new Person(context.getString(R.string.pearl), -2858457600000L));
        addFamous(db, new Person(context.getString(R.string.arber), -1280620800000L));
        addFamous(db, new Person(context.getString(R.string.nadal), 518140800000L));

        // 4 june
        addFamous(db, new Person(context.getString(R.string.quesnay), -8696332800000L));
        addFamous(db, new Person(context.getString(R.string.nazimova), -2858371200000L));
        addFamous(db, new Person(context.getString(R.string.cockerell), -1880150400000L));
        addFamous(db, new Person(context.getString(R.string.bartoli), -112924800000L));
        addFamous(db, new Person(context.getString(R.string.jolie), 171072000000L));

        // 5 june
        addFamous(db, new Person(context.getString(R.string.chippendale), -7938950400000L));
        addFamous(db, new Person(context.getString(R.string.keynes), -2732054400000L));
        addFamous(db, new Person(context.getString(R.string.lorca), -2258668800000L));
        addFamous(db, new Person(context.getString(R.string.gabor), -2195596800000L));
        addFamous(db, new Person(context.getString(R.string.peierls), -1974758400000L));
        addFamous(db, new Person(context.getString(R.string.wahlberg), 44928000000L));

        // 6 june
        addFamous(db, new Person(context.getString(R.string.regiomontanus), -16837113600000L));
        addFamous(db, new Person(context.getString(R.string.velazquez), -11694153600000L));
        addFamous(db, new Person(context.getString(R.string.corneille), -11473228800000L));
        addFamous(db, new Person(context.getString(R.string.pushkin), -5382720000000L));
        addFamous(db, new Person(context.getString(R.string.braun), -3773347200000L));
        addFamous(db, new Person(context.getString(R.string.mann), -2984428800000L));

        // 7 june
        addFamous(db, new Person(context.getString(R.string.brummell), -6045321600000L));
        addFamous(db, new Person(context.getString(R.string.auer), -3931027200000L));
        addFamous(db, new Person(context.getString(R.string.mackintosh), -3205180800000L));
        addFamous(db, new Person(context.getString(R.string.barkla), -2921184000000L));
        addFamous(db, new Person(context.getString(R.string.mulliken), -2321568000000L));
        addFamous(db, new Person(context.getString(R.string.apgar), -1911427200000L));
        addFamous(db, new Person(context.getString(R.string.martin), -1658966400000L));
        addFamous(db, new Person(context.getString(R.string.neeson), -554428800000L));

        // 8 june
        addFamous(db, new Person(context.getString(R.string.cassini), -10873440000000L));
        addFamous(db, new Person(context.getString(R.string.albinoni), -9421833600000L));
        addFamous(db, new Person(context.getString(R.string.careme), -5855846400000L));
        addFamous(db, new Person(context.getString(R.string.schumann), -5035478400000L));
        addFamous(db, new Person(context.getString(R.string.yeste), -2353104000000L));
        addFamous(db, new Person(context.getString(R.string.john_campbell), -1879804800000L));
        addFamous(db, new Person(context.getString(R.string.kanye_west), 234576000000L));

        // 9 june
        addFamous(db, new Person(context.getString(R.string.stephenson), -5950454400000L));
        addFamous(db, new Person(context.getString(R.string.galle), -4972233600000L));
        addFamous(db, new Person(context.getString(R.string.suttner), -3994012800000L));
        addFamous(db, new Person(context.getString(R.string.dale), -2984169600000L));
        addFamous(db, new Person(context.getString(R.string.fox), -270259200000L));
        addFamous(db, new Person(context.getString(R.string.depp), -207187200000L));
        addFamous(db, new Person(context.getString(R.string.portman), 360892800000L));

        // 10 june
        addFamous(db, new Person(context.getString(R.string.courbet), -4751308800000L));
        addFamous(db, new Person(context.getString(R.string.otto), -4340995200000L));
        addFamous(db, new Person(context.getString(R.string.cook), -3299616000000L));
        addFamous(db, new Person(context.getString(R.string.mcdaniel), -2352931200000L));
        addFamous(db, new Person(context.getString(R.string.bellow), -1721865600000L));
        addFamous(db, new Person(context.getString(R.string.garland), -1500940800000L));

        // 11 june
        addFamous(db, new Person(context.getString(R.string.constable), -6108048000000L));
        addFamous(db, new Person(context.getString(R.string.fortuny), -4151606400000L));
        addFamous(db, new Person(context.getString(R.string.richard_strauss), -3331065600000L));
        addFamous(db, new Person(context.getString(R.string.cousteau), -1879545600000L));
        addFamous(db, new Person(context.getString(R.string.styron), -1406160000000L));
        addFamous(db, new Person(context.getString(R.string.laurie), -333244800000L));
        addFamous(db, new Person(context.getString(R.string.dinklage), -17625600000L));
        addFamous(db, new Person(context.getString(R.string.labeouf), 518832000000L));

        // 12 june
        addFamous(db, new Person(context.getString(R.string.roebling), -5161363200000L));
        addFamous(db, new Person(context.getString(R.string.lipmann), -2226528000000L));
        addFamous(db, new Person(context.getString(R.string.frank), -1279843200000L));
        addFamous(db, new Person(context.getString(R.string.sakmann), -869616000000L));
        addFamous(db, new Person(context.getString(R.string.lima), 361152000000L));

        // 13 june
        addFamous(db, new Person(context.getString(R.string.young), -6202569600000L));
        addFamous(db, new Person(context.getString(R.string.maxwell), -4372358400000L));
        addFamous(db, new Person(context.getString(R.string.yeats), -3299356800000L));
        addFamous(db, new Person(context.getString(R.string.john_nash), -1311292800000L));
        addFamous(db, new Person(context.getString(R.string.mcdowell), -837993600000L));
        addFamous(db, new Person(context.getString(R.string.perelman), -112147200000L));
        addFamous(db, new Person(context.getString(R.string.evans), 361238400000L));

        // 14 june
        addFamous(db, new Person(context.getString(R.string.coulomb), -7370092800000L));
        addFamous(db, new Person(context.getString(R.string.stowe), -5003424000000L));
        addFamous(db, new Person(context.getString(R.string.landsteiner), -3204576000000L));
        addFamous(db, new Person(context.getString(R.string.tokarev), -3109968000000L));
        addFamous(db, new Person(context.getString(R.string.church), -2100211200000L));
        addFamous(db, new Person(context.getString(R.string.graf), -17366400000L));

        // 15 june
        addFamous(db, new Person(context.getString(R.string.poussin), -11851142400000L));
        addFamous(db, new Person(context.getString(R.string.fourcroy), -6770476800000L));
        addFamous(db, new Person(context.getString(R.string.balmont), -3236112000000L));
        addFamous(db, new Person(context.getString(R.string.belushi), -490665600000L));
        addFamous(db, new Person(context.getString(R.string.helen_hunt), -206668800000L));
        addFamous(db, new Person(context.getString(R.string.kahn), -17280000000L));
        addFamous(db, new Person(context.getString(R.string.harris), 108950400000L));

        // 16 june
        addFamous(db, new Person(context.getString(R.string.boccaccio), -20717856000000L));
        addFamous(db, new Person(context.getString(R.string.plucker), -5318784000000L));
        addFamous(db, new Person(context.getString(R.string.friedmann), -2573251200000L));
        addFamous(db, new Person(context.getString(R.string.leinster), -2320790400000L));
        addFamous(db, new Person(context.getString(R.string.chakraborty), -616809600000L));
        addFamous(db, new Person(context.getString(R.string.shakur), 45878400000L));
        addFamous(db, new Person(context.getString(R.string.john_newman), 645494400000L));

        // 17 june
        addFamous(db, new Person(context.getString(R.string.panini), -8789904000000L));
        addFamous(db, new Person(context.getString(R.string.gounod), -4782240000000L));
        addFamous(db, new Person(context.getString(R.string.stravinsky), -2762553600000L));
        addFamous(db, new Person(context.getString(R.string.escher), -2257632000000L));
        addFamous(db, new Person(context.getString(R.string.wakefield), -2099952000000L));
        addFamous(db, new Person(context.getString(R.string.jacob), -1563408000000L));

        // 18 june
        addFamous(db, new Person(context.getString(R.string.goncharov), -4971456000000L));
        addFamous(db, new Person(context.getString(R.string.laveran), -3930076800000L));
        addFamous(db, new Person(context.getString(R.string.flagg), -2920233600000L));
        addFamous(db, new Person(context.getString(R.string.macdonald), -2099865600000L));
        addFamous(db, new Person(context.getString(R.string.mccartney), -869097600000L));
        addFamous(db, new Person(context.getString(R.string.capello), -742867200000L));

        // 19 june
        addFamous(db, new Person(context.getString(R.string.pascal), -10935648000000L));
        addFamous(db, new Person(context.getString(R.string.dazai), -1910390400000L));
        addFamous(db, new Person(context.getString(R.string.flory), -1878854400000L));
        addFamous(db, new Person(context.getString(R.string.aage_bohr), -1500163200000L));
        addFamous(db, new Person(context.getString(R.string.rushdie), -711244800000L));
        addFamous(db, new Person(context.getString(R.string.dujardin), 77760000000L));

        // 20 june
        addFamous(db, new Person(context.getString(R.string.rosa), -11188022400000L));
        addFamous(db, new Person(context.getString(R.string.offenbach), -4750444800000L));
        addFamous(db, new Person(context.getString(R.string.bonnat), -4308595200000L));
        addFamous(db, new Person(context.getString(R.string.kidman), -80006400000L));
        addFamous(db, new Person(context.getString(R.string.rodriguez), -48384000000L));

        // 21 june
        addFamous(db, new Person(context.getString(R.string.poisson), -5949417600000L));
        addFamous(db, new Person(context.getString(R.string.sartre), -2036448000000L));
        addFamous(db, new Person(context.getString(R.string.mcewan), -679449600000L));
        addFamous(db, new Person(context.getString(R.string.platini), -458611200000L));
        addFamous(db, new Person(context.getString(R.string.tsoi), -237686400000L));
        addFamous(db, new Person(context.getString(R.string.lana_del_rey), 488160000000L));

        // 22 june
        addFamous(db, new Person(context.getString(R.string.haggard), -3582576000000L));
        addFamous(db, new Person(context.getString(R.string.minkowski), -3330115200000L));
        addFamous(db, new Person(context.getString(R.string.huxley), -2604355200000L));
        addFamous(db, new Person(context.getString(R.string.remarque), -2257200000000L));
        addFamous(db, new Person(context.getString(R.string.dillinger), -2099520000000L));
        addFamous(db, new Person(context.getString(R.string.wilder), -2004825600000L));
        addFamous(db, new Person(context.getString(R.string.streep), -647827200000L));
        addFamous(db, new Person(context.getString(R.string.dan_brown), -174441600000L));

        // 23 june
        addFamous(db, new Person(context.getString(R.string.vico), -9515145600000L));
        addFamous(db, new Person(context.getString(R.string.beauharnais), -6517324800000L));
        addFamous(db, new Person(context.getString(R.string.akhmatova), -2541110400000L));
        addFamous(db, new Person(context.getString(R.string.turing), -1815350400000L));
        addFamous(db, new Person(context.getString(R.string.fosse), -1342051200000L));
        addFamous(db, new Person(context.getString(R.string.zidane), 78105600000L));

        // 24 june
        addFamous(db, new Person(context.getString(R.string.victor_hess), -2730412800000L));
        addFamous(db, new Person(context.getString(R.string.fangio), -1846886400000L));
        addFamous(db, new Person(context.getString(R.string.perl), -1341964800000L));
        addFamous(db, new Person(context.getString(R.string.chabrol), -1247270400000L));
        addFamous(db, new Person(context.getString(R.string.messi), 551491200000L));

        // 25 june
        addFamous(db, new Person(context.getString(R.string.gaudi), -3708547200000L));
        addFamous(db, new Person(context.getString(R.string.nernst), -3329856000000L));
        addFamous(db, new Person(context.getString(R.string.orwell), -2099260800000L));
        addFamous(db, new Person(context.getString(R.string.lumet), -1436486400000L));
        addFamous(db, new Person(context.getString(R.string.abrikosov), -1310256000000L));
        addFamous(db, new Person(context.getString(R.string.michael), -205804800000L));

        // 26 june
        addFamous(db, new Person(context.getString(R.string.brandt), -8694432000000L));
        addFamous(db, new Person(context.getString(R.string.kelvin), -4592073600000L));
        addFamous(db, new Person(context.getString(R.string.buck), -2446156800000L));
        addFamous(db, new Person(context.getString(R.string.bill_lear), -2130710400000L));
        addFamous(db, new Person(context.getString(R.string.robert_richardson), -1026172800000L));

        // 27 june
        addFamous(db, new Person(context.getString(R.string.mauser), -4150224000000L));
        addFamous(db, new Person(context.getString(R.string.spemann), -3171916800000L));
        addFamous(db, new Person(context.getString(R.string.keller), -2824761600000L));
        addFamous(db, new Person(context.getString(R.string.abrams), -110937600000L));
        addFamous(db, new Person(context.getString(R.string.maguire), 173059200000L));
        addFamous(db, new Person(context.getString(R.string.raul), 236217600000L));

        // 28 june
        addFamous(db, new Person(context.getString(R.string.rubens), -12385612800000L));
        addFamous(db, new Person(context.getString(R.string.rousseau), -8126265600000L));
        addFamous(db, new Person(context.getString(R.string.broca), -4591900800000L));
        addFamous(db, new Person(context.getString(R.string.pirandello), -3234988800000L));
        addFamous(db, new Person(context.getString(R.string.carrel), -3045600000000L));
        addFamous(db, new Person(context.getString(R.string.goeppert_mayer), -2004307200000L));
        addFamous(db, new Person(context.getString(R.string.kathy_bates), -678844800000L));
        addFamous(db, new Person(context.getString(R.string.cusack), -110851200000L));
        addFamous(db, new Person(context.getString(R.string.musk), 46915200000L));

        // 29 june
        addFamous(db, new Person(context.getString(R.string.dodoens), -14278982400000L));
        addFamous(db, new Person(context.getString(R.string.ressel), -5570035200000L));
        addFamous(db, new Person(context.getString(R.string.leopardi), -5412268800000L));
        addFamous(db, new Person(context.getString(R.string.exupery), -2193523200000L));
        addFamous(db, new Person(context.getString(R.string.fallaci), -1278374400000L));
        addFamous(db, new Person(context.getString(R.string.scherzinger), 267926400000L));

        // 30 june
        addFamous(db, new Person(context.getString(R.string.vernet), -5696179200000L));
        addFamous(db, new Person(context.getString(R.string.hooker), -4812652800000L));
        addFamous(db, new Person(context.getString(R.string.duhamel), -2698272000000L));
        addFamous(db, new Person(context.getString(R.string.milosz), -1846368000000L));
        addFamous(db, new Person(context.getString(R.string.ballard), -868060800000L));
        addFamous(db, new Person(context.getString(R.string.tyson), -110678400000L));
        addFamous(db, new Person(context.getString(R.string.phelps), 488937600000L));

        // 1 july
        addFamous(db, new Person(context.getString(R.string.leibniz), -10208764800000L));
        addFamous(db, new Person(context.getString(R.string.poncelet), -5727628800000L));
        addFamous(db, new Person(context.getString(R.string.george_sand), -5222793600000L));
        addFamous(db, new Person(context.getString(R.string.vierordt), -4781030400000L));
        addFamous(db, new Person(context.getString(R.string.bleriot), -3076876800000L));
        addFamous(db, new Person(context.getString(R.string.lauder), -1940889600000L));
        addFamous(db, new Person(context.getString(R.string.diana), -268358400000L));
        addFamous(db, new Person(context.getString(R.string.pamela_anderson), -79056000000L));

        // 2 july
        addFamous(db, new Person(context.getString(R.string.gluck), -8062848000000L));
        addFamous(db, new Person(context.getString(R.string.henry_bragg), -3392409600000L));
        addFamous(db, new Person(context.getString(R.string.hesse), -2919024000000L));
        addFamous(db, new Person(context.getString(R.string.lacoste), -2067033600000L));
        addFamous(db, new Person(context.getString(R.string.cardin), -1499040000000L));
        addFamous(db, new Person(context.getString(R.string.lumumba), -1404345600000L));
        addFamous(db, new Person(context.getString(R.string.naceri), -268272000000L));
        addFamous(db, new Person(context.getString(R.string.robbie), 646876800000L));

        // 3 july
        addFamous(db, new Person(context.getString(R.string.adam), -7620912000000L));
        addFamous(db, new Person(context.getString(R.string.kafka), -2729635200000L));
        addFamous(db, new Person(context.getString(R.string.stoppard), -1025568000000L));
        addFamous(db, new Person(context.getString(R.string.cruise), -236649600000L));
        addFamous(db, new Person(context.getString(R.string.selanne), 15811200000L));

        // 4 july
        addFamous(db, new Person(context.getString(R.string.blanchard), -6831907200000L));
        addFamous(db, new Person(context.getString(R.string.everest), -5664297600000L));
        addFamous(db, new Person(context.getString(R.string.garibaldi), -5127926400000L));
        addFamous(db, new Person(context.getString(R.string.manolete), -1656633600000L));
        addFamous(db, new Person(context.getString(R.string.lollobrigida), -1341100800000L));

        // 5 july
        addFamous(db, new Person(context.getString(R.string.bulgarin), -5695747200000L));
        addFamous(db, new Person(context.getString(R.string.fitzroy), -5190912000000L));
        addFamous(db, new Person(context.getString(R.string.rankine), -4717526400000L));
        addFamous(db, new Person(context.getString(R.string.zetkin), -3549916800000L));
        addFamous(db, new Person(context.getString(R.string.gasser), -2571609600000L));
        addFamous(db, new Person(context.getString(R.string.cocteau), -2540073600000L));

        // 6 july
        addFamous(db, new Person(context.getString(R.string.raffles), -5948121600000L));
        addFamous(db, new Person(context.getString(R.string.heidenstam), -3486758400000L));
        addFamous(db, new Person(context.getString(R.string.chagall), -2603145600000L));
        addFamous(db, new Person(context.getString(R.string.bill_haley), -1404000000000L));
        addFamous(db, new Person(context.getString(R.string.stallone), -741312000000L));
        addFamous(db, new Person(context.getString(R.string.rush), -583545600000L));
        addFamous(db, new Person(context.getString(R.string.cent), 173836800000L));
        addFamous(db, new Person(context.getString(R.string.eva_green), 331689600000L));

        // 7 july
        addFamous(db, new Person(context.getString(R.string.jacquard), -6863184000000L));
        addFamous(db, new Person(context.getString(R.string.golgi), -3991593600000L));
        addFamous(db, new Person(context.getString(R.string.mahler), -3455049600000L));
        addFamous(db, new Person(context.getString(R.string.feuchtwanger), -2697667200000L));
        addFamous(db, new Person(context.getString(R.string.ringo_starr), -930528000000L));
        addFamous(db, new Person(context.getString(R.string.cutugno), -835920000000L));

        // 8 july
        addFamous(db, new Person(context.getString(R.string.fontaine), -10997078400000L));
        addFamous(db, new Person(context.getString(R.string.zeppelin), -4149273600000L));
        addFamous(db, new Person(context.getString(R.string.rockefeller), -4117737600000L));
        addFamous(db, new Person(context.getString(R.string.benardos), -4023043200000L));
        addFamous(db, new Person(context.getString(R.string.arthus_evans), -3739046400000L));
        addFamous(db, new Person(context.getString(R.string.perls), -2413584000000L));
        addFamous(db, new Person(context.getString(R.string.kapitsa), -2382048000000L));

        // 9 july
        addFamous(db, new Person(context.getString(R.string.radcliffe), -6484320000000L));
        addFamous(db, new Person(context.getString(R.string.davenport), -5285260800000L));
        addFamous(db, new Person(context.getString(R.string.elias_howe), -4748803200000L));
        addFamous(db, new Person(context.getString(R.string.boas), -3518035200000L));
        addFamous(db, new Person(context.getString(R.string.chagas), -2855347200000L));
        addFamous(db, new Person(context.getString(R.string.tom_hanks), -425433600000L));

        // 10 july
        addFamous(db, new Person(context.getString(R.string.jean_calvin), -14530492800000L));
        addFamous(db, new Person(context.getString(R.string.marryat), -5600620800000L));
        addFamous(db, new Person(context.getString(R.string.pissarro), -4401561600000L));
        addFamous(db, new Person(context.getString(R.string.tesla), -3581020800000L));
        addFamous(db, new Person(context.getString(R.string.proust), -3107721600000L));
        addFamous(db, new Person(context.getString(R.string.chamberlain), -1561420800000L));

        // 11 july
        addFamous(db, new Person(context.getString(R.string.gondora), -12889411200000L));
        addFamous(db, new Person(context.getString(R.string.lalande), -7493990400000L));
        addFamous(db, new Person(context.getString(R.string.nelson), -2760480000000L));
        addFamous(db, new Person(context.getString(R.string.abel), -2097878400000L));
        addFamous(db, new Person(context.getString(R.string.brynner), -1561334400000L));
        addFamous(db, new Person(context.getString(R.string.armani), -1119571200000L));

        // 12 july
        addFamous(db, new Person(context.getString(R.string.bernard), -4937846400000L));
        addFamous(db, new Person(context.getString(R.string.eastman), -3644006400000L));
        addFamous(db, new Person(context.getString(R.string.tod_browning), -2823465600000L));
        addFamous(db, new Person(context.getString(R.string.modigliani), -2697235200000L));
        addFamous(db, new Person(context.getString(R.string.meruda), -2066169600000L));
        addFamous(db, new Person(context.getString(R.string.wyeth), -1655942400000L));
        addFamous(db, new Person(context.getString(R.string.michelle_rodriguez), 269049600000L));

        // 13 july
        addFamous(db, new Person(context.getString(R.string.john_dee), -13962240000000L));
        addFamous(db, new Person(context.getString(R.string.cannizzaro), -4527532800000L));
        addFamous(db, new Person(context.getString(R.string.otto_wagner), -4054147200000L));
        addFamous(db, new Person(context.getString(R.string.babel), -2381616000000L));
        addFamous(db, new Person(context.getString(R.string.ascari), -1624320000000L));
        addFamous(db, new Person(context.getString(R.string.rubik), -803779200000L));
        addFamous(db, new Person(context.getString(R.string.benassi), -78019200000L));

        // 14 july
        addFamous(db, new Person(context.getString(R.string.dumas), -5347900800000L));
        addFamous(db, new Person(context.getString(R.string.klimt), -3391372800000L));
        addFamous(db, new Person(context.getString(R.string.irving_stone), -2097619200000L));
        addFamous(db, new Person(context.getString(R.string.bergman), -1624233600000L));
        addFamous(db, new Person(context.getString(R.string.forrester), -1624233600000L));

        // 15 july
        addFamous(db, new Person(context.getString(R.string.rembrandt), -11469859200000L));
        addFamous(db, new Person(context.getString(R.string.pareto), -3833049600000L));
        addFamous(db, new Person(context.getString(R.string.harmsworth), -3296592000000L));
        addFamous(db, new Person(context.getString(R.string.brockhouse), -1624147200000L));
        addFamous(db, new Person(context.getString(R.string.savage), -77846400000L));
        addFamous(db, new Person(context.getString(R.string.kruger), 206236800000L));

        // 16 july
        addFamous(db, new Person(context.getString(R.string.assisi), -24470640000000L));
        addFamous(db, new Person(context.getString(R.string.amundsen), -3075580800000L));
        addFamous(db, new Person(context.getString(R.string.stanwyck), -1971216000000L));
        addFamous(db, new Person(context.getString(R.string.laroche), -1529366400000L));
        addFamous(db, new Person(context.getString(R.string.sheckley), -1308441600000L));

        // 17 july
        addFamous(db, new Person(context.getString(R.string.friedrich_krupp), -5757868800000L));
        addFamous(db, new Person(context.getString(R.string.corot), -5473785600000L));
        addFamous(db, new Person(context.getString(R.string.nicholas), -3896035200000L));
        addFamous(db, new Person(context.getString(R.string.lamaitre), -2381270400000L));
        addFamous(db, new Person(context.getString(R.string.abbott), -2255040000000L));
        addFamous(db, new Person(context.getString(R.string.sutherland), -1087516800000L));

        // 18 july
        addFamous(db, new Person(context.getString(R.string.thackeray), -5000486400000L));
        addFamous(db, new Person(context.getString(R.string.viardot), -4684867200000L));
        addFamous(db, new Person(context.getString(R.string.lorentz), -3675024000000L));
        addFamous(db, new Person(context.getString(R.string.mandela), -1623888000000L));
        addFamous(db, new Person(context.getString(R.string.hunter_thompson), -1024272000000L));
        addFamous(db, new Person(context.getString(R.string.branson), -614044800000L));
        addFamous(db, new Person(context.getString(R.string.vin_diesel), -77587200000L));

        // 19 july
        addFamous(db, new Person(context.getString(R.string.colt), -4905705600000L));
        addFamous(db, new Person(context.getString(R.string.degas), -4274553600000L));
        addFamous(db, new Person(context.getString(R.string.mayakovsky), -2412633600000L));
        addFamous(db, new Person(context.getString(R.string.cronin), -2317939200000L));
        addFamous(db, new Person(context.getString(R.string.coloane), -1876262400000L));
        addFamous(db, new Person(context.getString(R.string.yalow), -1529107200000L));
        addFamous(db, new Person(context.getString(R.string.cumberbatch), 206582400000L));

        // 20 july
        addFamous(db, new Person(context.getString(R.string.petrarca), -20998915200000L));
        addFamous(db, new Person(context.getString(R.string.owen), -5221152000000L));
        addFamous(db, new Person(context.getString(R.string.mendel), -4653158400000L));
        addFamous(db, new Person(context.getString(R.string.georg_muller), -3769545600000L));
        addFamous(db, new Person(context.getString(R.string.morandi), -2507241600000L));
        addFamous(db, new Person(context.getString(R.string.dobrev), -1749945600000L));
        addFamous(db, new Person(context.getString(R.string.bundchen), 332899200000L));

        // 21 july
        addFamous(db, new Person(context.getString(R.string.picard), -11027491200000L));
        addFamous(db, new Person(context.getString(R.string.regnault), -5031763200000L));
        addFamous(db, new Person(context.getString(R.string.reuter), -4842374400000L));
        addFamous(db, new Person(context.getString(R.string.hemingway), -2223158400000L));
        addFamous(db, new Person(context.getString(R.string.robin_williams), -582249600000L));
        addFamous(db, new Person(context.getString(R.string.josh_hartnett), 269827200000L));

        // 22 july
        addFamous(db, new Person(context.getString(R.string.soufflot), -8092656000000L));
        addFamous(db, new Person(context.getString(R.string.gustav_hertz), -2601763200000L));
        addFamous(db, new Person(context.getString(R.string.mathieu), -739929600000L));
        addFamous(db, new Person(context.getString(R.string.dafoe), -455932800000L));
        addFamous(db, new Person(context.getString(R.string.selena_gomez), 711763200000L));

        // 23 july
        addFamous(db, new Person(context.getString(R.string.vyazemsky), -5599497600000L));
        addFamous(db, new Person(context.getString(R.string.cilea), -3264364800000L));
        addFamous(db, new Person(context.getString(R.string.harrelson), -266457600000L));
        addFamous(db, new Person(context.getString(R.string.hoffman), -77155200000L));
        addFamous(db, new Person(context.getString(R.string.lewinsky), 112233600000L));
        addFamous(db, new Person(context.getString(R.string.daniel_radcliffe), 617155200000L));

        // 24 july
        addFamous(db, new Person(context.getString(R.string.vidocq), -6135955200000L));
        addFamous(db, new Person(context.getString(R.string.alexandre_dumas), -5283964800000L));
        addFamous(db, new Person(context.getString(R.string.mucha), -3453580800000L));
        addFamous(db, new Person(context.getString(R.string.benson), -3232742400000L));
        addFamous(db, new Person(context.getString(R.string.lopez), -13910400000L));

        // 25 july
        addFamous(db, new Person(context.getString(R.string.scheiner), -12446438400000L));
        addFamous(db, new Person(context.getString(R.string.eakins), -3958416000000L));
        addFamous(db, new Person(context.getString(R.string.davidson_black), -2696112000000L));
        addFamous(db, new Person(context.getString(R.string.canetti), -2033510400000L));
        addFamous(db, new Person(context.getString(R.string.leblanc), -76982400000L));

        // 26 july
        addFamous(db, new Person(context.getString(R.string.remak), -4873564800000L));
        addFamous(db, new Person(context.getString(R.string.shaw), -3579638400000L));
        addFamous(db, new Person(context.getString(R.string.jung), -2980108800000L));
        addFamous(db, new Person(context.getString(R.string.maurois), -2664489600000L));
        addFamous(db, new Person(context.getString(R.string.kubrick), -1307577600000L));
        addFamous(db, new Person(context.getString(R.string.jagger), -834278400000L));
        addFamous(db, new Person(context.getString(R.string.spacey), -329356800000L));
        addFamous(db, new Person(context.getString(R.string.bullock), -171504000000L));
        addFamous(db, new Person(context.getString(R.string.statham), -76896000000L));

        // 27 july
        addFamous(db, new Person(context.getString(R.string.corday), -6356534400000L));
        addFamous(db, new Person(context.getString(R.string.carducci), -4242326400000L));
        addFamous(db, new Person(context.getString(R.string.hans_fischer), -2790633600000L));
        addFamous(db, new Person(context.getString(R.string.monaco), -1717804800000L));
        addFamous(db, new Person(context.getString(R.string.nikolaj), 17884800000L));

        // 28 july
        addFamous(db, new Person(context.getString(R.string.hooke), -10553587200000L));
        addFamous(db, new Person(context.getString(R.string.feuerbach), -5220460800000L));
        addFamous(db, new Person(context.getString(R.string.grisi), -4999622400000L));
        addFamous(db, new Person(context.getString(R.string.duchamp), -2601244800000L));
        addFamous(db, new Person(context.getString(R.string.popper), -2127945600000L));
        addFamous(db, new Person(context.getString(R.string.burda), -1907020800000L));
        addFamous(db, new Person(context.getString(R.string.chavez), -486950400000L));

        // 29 july
        addFamous(db, new Person(context.getString(R.string.aivazovsky), -4810147200000L));
        addFamous(db, new Person(context.getString(R.string.mussolini), -2727388800000L));
        addFamous(db, new Person(context.getString(R.string.theda_bara), -2664230400000L));
        addFamous(db, new Person(context.getString(R.string.clara_bow), -2033164800000L));
        addFamous(db, new Person(context.getString(R.string.alonso), 365212800000L));

        // 30 july
        addFamous(db, new Person(context.getString(R.string.vasari), -14465692800000L));
        addFamous(db, new Person(context.getString(R.string.bronte), -4778524800000L));
        addFamous(db, new Person(context.getString(R.string.henry_ford), -3358454400000L));
        addFamous(db, new Person(context.getString(R.string.cyril_parkinson), -1906848000000L));
        addFamous(db, new Person(context.getString(R.string.schwarzenegger), -707702400000L));
        addFamous(db, new Person(context.getString(R.string.jean_reno), -676080000000L));
        addFamous(db, new Person(context.getString(R.string.nolan), 18144000000L));

        // 31 july
        addFamous(db, new Person(context.getString(R.string.cramer), -8375875200000L));
        addFamous(db, new Person(context.getString(R.string.wohler), -5346432000000L));
        addFamous(db, new Person(context.getString(R.string.planquette), -3831667200000L));
        addFamous(db, new Person(context.getString(R.string.milton_friedman), -1812067200000L));
        addFamous(db, new Person(context.getString(R.string.de_funes), -1748995200000L));
        addFamous(db, new Person(context.getString(R.string.primo_levi), -1591228800000L));
        addFamous(db, new Person(context.getString(R.string.rowling), -139536000000L));

        // 1 august
        addFamous(db, new Person(context.getString(R.string.lamarck), -7113484800000L));
        addFamous(db, new Person(context.getString(R.string.melville), -4746816000000L));
        addFamous(db, new Person(context.getString(R.string.taro), -1875139200000L));
        addFamous(db, new Person(context.getString(R.string.laurent), -1054598400000L));
        addFamous(db, new Person(context.getString(R.string.mendes), -139449600000L));
        addFamous(db, new Person(context.getString(R.string.momoa), 302313600000L));

        // 2 august
        addFamous(db, new Person(context.getString(R.string.hoogstraten), -10805616000000L));
        addFamous(db, new Person(context.getString(R.string.tyndall), -4715107200000L));
        addFamous(db, new Person(context.getString(R.string.olcott), -4336416000000L));
        addFamous(db, new Person(context.getString(R.string.bartholdi), -4273344000000L));
        addFamous(db, new Person(context.getString(R.string.loy), -2032819200000L));
        addFamous(db, new Person(context.getString(R.string.worthington), 207792000000L));

        // 3 august
        addFamous(db, new Person(context.getString(R.string.otis), -4999104000000L));
        addFamous(db, new Person(context.getString(R.string.simak), -2064268800000L));
        addFamous(db, new Person(context.getString(R.string.james), -1559347200000L));
        addFamous(db, new Person(context.getString(R.string.sheen), -928195200000L));
        addFamous(db, new Person(context.getString(R.string.lilly), 302486400000L));

        // 4 august
        addFamous(db, new Person(context.getString(R.string.john_venn), -4273171200000L));
        addFamous(db, new Person(context.getString(R.string.shelley), -5598460800000L));
        addFamous(db, new Person(context.getString(R.string.hamsun), -3484252800000L));
        addFamous(db, new Person(context.getString(R.string.armstrong), -2190412800000L));
        addFamous(db, new Person(context.getString(R.string.thornton), -454809600000L));

        // 5 august
        addFamous(db, new Person(context.getString(R.string.niels_abel), -5282928000000L));
        addFamous(db, new Person(context.getString(R.string.repin), -3957465600000L));
        addFamous(db, new Person(context.getString(R.string.maupassant), -3768163200000L));
        addFamous(db, new Person(context.getString(R.string.wain), -3452544000000L));
        addFamous(db, new Person(context.getString(R.string.huston), -2001024000000L));
        addFamous(db, new Person(context.getString(R.string.neil_armstrong), -1243641600000L));

        // 6 august
        addFamous(db, new Person(context.getString(R.string.malebranche), -10458115200000L));
        addFamous(db, new Person(context.getString(R.string.johann_bernoulli), -9542966400000L));
        addFamous(db, new Person(context.getString(R.string.alexander_fleming), -2789769600000L));
        addFamous(db, new Person(context.getString(R.string.lucille_ball), -1843171200000L));
        addFamous(db, new Person(context.getString(R.string.andy_warhol), -1306627200000L));
        addFamous(db, new Person(context.getString(R.string.shyamalan), 18748800000L));

        // 7 august
        addFamous(db, new Person(context.getString(R.string.bathory), -12918614400000L));
        addFamous(db, new Person(context.getString(R.string.mata_hari), -2947449600000L));
        addFamous(db, new Person(context.getString(R.string.tobin_bell), -864777600000L));
        addFamous(db, new Person(context.getString(R.string.duchovny), -296697600000L));
        addFamous(db, new Person(context.getString(R.string.jimmy_wales), -107395200000L));
        addFamous(db, new Person(context.getString(R.string.theron), 176601600000L));

        // 8 august
        addFamous(db, new Person(context.getString(R.string.bateson), -3420748800000L));
        addFamous(db, new Person(context.getString(R.string.lawrence), -2158531200000L));
        addFamous(db, new Person(context.getString(R.string.dirac), -2126995200000L));
        addFamous(db, new Person(context.getString(R.string.dustin_hoffman), -1022457600000L));
        addFamous(db, new Person(context.getString(R.string.federer), 366076800000L));

        // 9 august
        addFamous(db, new Person(context.getString(R.string.avogadro), -6102950400000L));
        addFamous(db, new Person(context.getString(R.string.morton_william), -4746124800000L));
        addFamous(db, new Person(context.getString(R.string.huckel), -2316124800000L));
        addFamous(db, new Person(context.getString(R.string.piaget), -2316124800000L));
        addFamous(db, new Person(context.getString(R.string.travers), -2221516800000L));
        addFamous(db, new Person(context.getString(R.string.jansson), -1748217600000L));
        addFamous(db, new Person(context.getString(R.string.houston), -201916800000L));
        addFamous(db, new Person(context.getString(R.string.tautou), 208396800000L));

        // 10 august
        addFamous(db, new Person(context.getString(R.string.nestle), -4903804800000L));
        addFamous(db, new Person(context.getString(R.string.qunanbaiuli), -3925497600000L));
        addFamous(db, new Person(context.getString(R.string.darrow), -2536963200000L));
        addFamous(db, new Person(context.getString(R.string.shearer), -2126822400000L));
        addFamous(db, new Person(context.getString(R.string.tiselius), -2126822400000L));
        addFamous(db, new Person(context.getString(R.string.banderas), -296438400000L));

        // 11 august
        addFamous(db, new Person(context.getString(R.string.andrew_davis), -4525027200000L));
        addFamous(db, new Person(context.getString(R.string.savant), -738201600000L));
        addFamous(db, new Person(context.getString(R.string.wozniak), -611971200000L));
        addFamous(db, new Person(context.getString(R.string.hogan), -517276800000L));
        addFamous(db, new Person(context.getString(R.string.hemsworth), 429408000000L));

        // 12 august
        addFamous(db, new Person(context.getString(R.string.bering), -9100598400000L));
        addFamous(db, new Person(context.getString(R.string.demille), -2789251200000L));
        addFamous(db, new Person(context.getString(R.string.bendix), -2789251200000L));
        addFamous(db, new Person(context.getString(R.string.schrodinger), -2599948800000L));
        addFamous(db, new Person(context.getString(R.string.soros), -1243036800000L));
        addFamous(db, new Person(context.getString(R.string.delevingne), 713577600000L));

        // 13 august
        addFamous(db, new Person(context.getString(R.string.angstrom), -4903545600000L));
        addFamous(db, new Person(context.getString(R.string.miescher), -3956774400000L));
        addFamous(db, new Person(context.getString(R.string.agnelli), -3262550400000L));
        addFamous(db, new Person(context.getString(R.string.hitchcock), -2221171200000L));
        addFamous(db, new Person(context.getString(R.string.wankel), -2126563200000L));
        addFamous(db, new Person(context.getString(R.string.castro), -1369180800000L));

        // 14 august
        addFamous(db, new Person(context.getString(R.string.orsted), -6070982400000L));
        addFamous(db, new Person(context.getString(R.string.holliday), -3735849600000L));
        addFamous(db, new Person(context.getString(R.string.merezhkovsky), -3262464000000L));
        addFamous(db, new Person(context.getString(R.string.galsworthy), -3230928000000L));
        addFamous(db, new Person(context.getString(R.string.dempster), -2631312000000L));
        addFamous(db, new Person(context.getString(R.string.steve_martin), -769478400000L));
        addFamous(db, new Person(context.getString(R.string.berry), -106790400000L));
        addFamous(db, new Person(context.getString(R.string.kunis), 429667200000L));

        // 15 august
        addFamous(db, new Person(context.getString(R.string.carmontelle), -7964352000000L));
        addFamous(db, new Person(context.getString(R.string.napoleon), -6323356800000L));
        addFamous(db, new Person(context.getString(R.string.scott), -6260284800000L));
        addFamous(db, new Person(context.getString(R.string.broglie), -2441836800000L));
        addFamous(db, new Person(context.getString(R.string.inarritu), -201398400000L));
        addFamous(db, new Person(context.getString(R.string.affleck), 82684800000L));
        addFamous(db, new Person(context.getString(R.string.jennifer_lawrence), 650678400000L));

        // 16 august
        addFamous(db, new Person(context.getString(R.string.bruyere), -10236326400000L));
        addFamous(db, new Person(context.getString(R.string.lippmann), -3924979200000L));
        addFamous(db, new Person(context.getString(R.string.bukowski), -1558224000000L));
        addFamous(db, new Person(context.getString(R.string.richard), -1116460800000L));
        addFamous(db, new Person(context.getString(R.string.cameron), -485308800000L));
        addFamous(db, new Person(context.getString(R.string.madonna), -359078400000L));

        // 17 august
        addFamous(db, new Person(context.getString(R.string.fermat), -11624774400000L));
        addFamous(db, new Person(context.getString(R.string.hodgkin), -5408035200000L));
        addFamous(db, new Person(context.getString(R.string.fokker), -2599516800000L));
        addFamous(db, new Person(context.getString(R.string.naipaul), -1179446400000L));
        addFamous(db, new Person(context.getString(R.string.de_niro), -832377600000L));
        addFamous(db, new Person(context.getString(R.string.penn), -295833600000L));

        // 18 august
        addFamous(db, new Person(context.getString(R.string.brook_taylor), -8973849600000L));
        addFamous(db, new Person(context.getString(R.string.salieri), -6922713600000L));
        addFamous(db, new Person(context.getString(R.string.pierre_martin), -4587494400000L));
        addFamous(db, new Person(context.getString(R.string.swayze), -548208000000L));
        addFamous(db, new Person(context.getString(R.string.norton), -11750400000L));
        addFamous(db, new Person(context.getString(R.string.slater), -11750400000L));

        // 19 august
        addFamous(db, new Person(context.getString(R.string.samuel_richardson), -8847532800000L));
        addFamous(db, new Person(context.getString(R.string.platov), -6827932800000L));
        addFamous(db, new Person(context.getString(R.string.nasmyth), -5092329600000L));
        addFamous(db, new Person(context.getString(R.string.meyer), -4398105600000L));
        addFamous(db, new Person(context.getString(R.string.enescu), -2788646400000L));
        addFamous(db, new Person(context.getString(R.string.chanel), -2725574400000L));
        addFamous(db, new Person(context.getString(R.string.perry), -11664000000L));

        // 20 august
        addFamous(db, new Person(context.getString(R.string.berzelius), -6007392000000L));
        addFamous(db, new Person(context.getString(R.string.quasimodo), -2157494400000L));
        addFamous(db, new Person(context.getString(R.string.susann), -1621036800000L));
        addFamous(db, new Person(context.getString(R.string.durst), 19958400000L));
        addFamous(db, new Person(context.getString(R.string.amy_adams), 146188800000L));
        addFamous(db, new Person(context.getString(R.string.garfield), 430185600000L));

        // 21 august
        addFamous(db, new Person(context.getString(R.string.murdoch), -6796224000000L));
        addFamous(db, new Person(context.getString(R.string.basie), -2062713600000L));
        addFamous(db, new Person(context.getString(R.string.consuelo_velazquez), -1684022400000L));
        addFamous(db, new Person(context.getString(R.string.wilt_chamberlain), -1052870400000L));
        addFamous(db, new Person(context.getString(R.string.brin), 114739200000L));
        addFamous(db, new Person(context.getString(R.string.bolt), 524966400000L));

        // 22 august
        addFamous(db, new Person(context.getString(R.string.papin), -10172736000000L));
        addFamous(db, new Person(context.getString(R.string.maudslay), -6259680000000L));
        addFamous(db, new Person(context.getString(R.string.nipkow), -3451075200000L));
        addFamous(db, new Person(context.getString(R.string.debussy), -3388003200000L));
        addFamous(db, new Person(context.getString(R.string.scheler), -3009312000000L));
        addFamous(db, new Person(context.getString(R.string.bradbury), -1557705600000L));

        // 23 august
        addFamous(db, new Person(context.getString(R.string.laperouse), -7206278400000L));
        addFamous(db, new Person(context.getString(R.string.cuvier), -6322665600000L));
        addFamous(db, new Person(context.getString(R.string.jirasek), -3735072000000L));
        addFamous(db, new Person(context.getString(R.string.arrow), -1526083200000L));
        addFamous(db, new Person(context.getString(R.string.phoenix), 20217600000L));

        // 24 august
        addFamous(db, new Person(context.getString(R.string.weddell), -5754585600000L));
        addFamous(db, new Person(context.getString(R.string.borges), -2220220800000L));
        addFamous(db, new Person(context.getString(R.string.coelho), -705542400000L));
        addFamous(db, new Person(context.getString(R.string.jarre), -673920000000L));
        addFamous(db, new Person(context.getString(R.string.fry), -389923200000L));
        addFamous(db, new Person(context.getString(R.string.guttenberg), -358387200000L));
        addFamous(db, new Person(context.getString(R.string.grint), 588384000000L));

        // 25 august
        addFamous(db, new Person(context.getString(R.string.pinkerton), -4744742400000L));
        addFamous(db, new Person(context.getString(R.string.elo), -2093990400000L));
        addFamous(db, new Person(context.getString(R.string.brian_moore), -1525910400000L));
        addFamous(db, new Person(context.getString(R.string.connery), -1241913600000L));
        addFamous(db, new Person(context.getString(R.string.tim_burton), -358300800000L));
        addFamous(db, new Person(context.getString(R.string.schiffer), 20390400000L));

        // 26 august
        addFamous(db, new Person(context.getString(R.string.lambert), -7616246400000L));
        addFamous(db, new Person(context.getString(R.string.joseph_montgolfier), -7237555200000L));
        addFamous(db, new Person(context.getString(R.string.lavoisier), -7142947200000L));
        addFamous(db, new Person(context.getString(R.string.forest), -3040502400000L));
        addFamous(db, new Person(context.getString(R.string.teresa), -1872979200000L));
        addFamous(db, new Person(context.getString(R.string.culkin), 336096000000L));

        // 27 august
        addFamous(db, new Person(context.getString(R.string.hegel), -6290784000000L));
        addFamous(db, new Person(context.getString(R.string.niebuhr), -6101395200000L));
        addFamous(db, new Person(context.getString(R.string.bosch), -3008880000000L));
        addFamous(db, new Person(context.getString(R.string.rolls), -2914185600000L));
        addFamous(db, new Person(context.getString(R.string.ranevskaya), -2314569600000L));
        addFamous(db, new Person(context.getString(R.string.chalke), 209952000000L));
        addFamous(db, new Person(context.getString(R.string.aaron_paul), 304560000000L));

        // 28 august
        addFamous(db, new Person(context.getString(R.string.goethe), -6953385600000L));
        addFamous(db, new Person(context.getString(R.string.blondel), -3355948800000L));
        addFamous(db, new Person(context.getString(R.string.whipple), -2882563200000L));
        addFamous(db, new Person(context.getString(R.string.theremin), -2314483200000L));
        addFamous(db, new Person(context.getString(R.string.fincher), -231811200000L));
        addFamous(db, new Person(context.getString(R.string.jack_black), -10886400000L));

        // 29 august
        addFamous(db, new Person(context.getString(R.string.locke), -10645430400000L));
        addFamous(db, new Person(context.getString(R.string.maeterlinck), -3387398400000L));
        addFamous(db, new Person(context.getString(R.string.forssmann), -2062022400000L));
        addFamous(db, new Person(context.getString(R.string.ingrid_bergman), -1714953600000L));
        addFamous(db, new Person(context.getString(R.string.charlie_parker), -1557100800000L));
        addFamous(db, new Person(context.getString(R.string.michael_jackson), -357955200000L));

        // 30 august
        addFamous(db, new Person(context.getString(R.string.mary_shelley), -5438448000000L));
        addFamous(db, new Person(context.getString(R.string.adolf_hesse), -5059843200000L));
        addFamous(db, new Person(context.getString(R.string.hoff), -3702844800000L));
        addFamous(db, new Person(context.getString(R.string.rutherford), -3103315200000L));
        addFamous(db, new Person(context.getString(R.string.cummings), -2598393600000L));
        addFamous(db, new Person(context.getString(R.string.mclaren), -1020556800000L));
        addFamous(db, new Person(context.getString(R.string.diaz), 83980800000L));

        // 31 august
        addFamous(db, new Person(context.getString(R.string.helmholtz), -4681065600000L));
        addFamous(db, new Person(context.getString(R.string.paneth), -2598307200000L));
        addFamous(db, new Person(context.getString(R.string.fredric_march), -2282688000000L));
        addFamous(db, new Person(context.getString(R.string.gere), -641779200000L));
        addFamous(db, new Person(context.getString(R.string.tucker), 52444800000L));

        // 1 september
        addFamous(db, new Person(context.getString(R.string.jevons), -4239216000000L));
        addFamous(db, new Person(context.getString(R.string.auguste_forel), -3828902400000L));
        addFamous(db, new Person(context.getString(R.string.burroughs), -2976912000000L));
        addFamous(db, new Person(context.getString(R.string.marilyn_miller), -2251065600000L));
        addFamous(db, new Person(context.getString(R.string.marciano), -1462233600000L));
        addFamous(db, new Person(context.getString(R.string.estefan), -389232000000L));

        // 2 september
        addFamous(db, new Person(context.getString(R.string.howard), -7678800000000L));
        addFamous(db, new Person(context.getString(R.string.echeverria), -5185814400000L));
        addFamous(db, new Person(context.getString(R.string.field), -3765744000000L));
        addFamous(db, new Person(context.getString(R.string.soddy), -2913667200000L));
        addFamous(db, new Person(context.getString(R.string.reeves), -168220800000L));
        addFamous(db, new Person(context.getString(R.string.hayek), -105148800000L));

        // 3 september
        addFamous(db, new Person(context.getString(R.string.louis_sullivan), -3576268800000L));
        addFamous(db, new Person(context.getString(R.string.pregl), -3166041600000L));
        addFamous(db, new Person(context.getString(R.string.porsche), -2976739200000L));
        addFamous(db, new Person(context.getString(R.string.anderson), -2030054400000L));
        addFamous(db, new Person(context.getString(R.string.dovlatov), -893980800000L));
        addFamous(db, new Person(context.getString(R.string.jeunet), -515289600000L));
        addFamous(db, new Person(context.getString(R.string.charlie_sheen), -136598400000L));

        // 4 september
        addFamous(db, new Person(context.getString(R.string.constantijn_huygens), -11780985600000L));
        addFamous(db, new Person(context.getString(R.string.chateaubriand), -6353164800000L));
        addFamous(db, new Person(context.getString(R.string.richard_wright), -1935273600000L));
        addFamous(db, new Person(context.getString(R.string.tange), -1777507200000L));
        addFamous(db, new Person(context.getString(R.string.beyonce), 368409600000L));

        // 5 september
        addFamous(db, new Person(context.getString(R.string.campanella), -12663648000000L));
        addFamous(db, new Person(context.getString(R.string.meyerbeer), -5627318400000L));
        addFamous(db, new Person(context.getString(R.string.aleksey_tolstoy), -4806864000000L));
        addFamous(db, new Person(context.getString(R.string.jesse_james), -3860179200000L));
        addFamous(db, new Person(context.getString(R.string.mercury), -736041600000L));
        addFamous(db, new Person(context.getString(R.string.keaton), -578275200000L));

        // 6 september
        addFamous(db, new Person(context.getString(R.string.serlio), -15598483200000L));
        addFamous(db, new Person(context.getString(R.string.moses_mendelssohn), -7583760000000L));
        addFamous(db, new Person(context.getString(R.string.dalton), -6416150400000L));
        addFamous(db, new Person(context.getString(R.string.berdan), -4585852800000L));
        addFamous(db, new Person(context.getString(R.string.addams), -3449779200000L));
        addFamous(db, new Person(context.getString(R.string.essen), -1935100800000L));

        // 7 september
        addFamous(db, new Person(context.getString(R.string.leclerc), -8277984000000L));
        addFamous(db, new Person(context.getString(R.string.gossen), -5027616000000L));
        addFamous(db, new Person(context.getString(R.string.kuprin), -3134160000000L));
        addFamous(db, new Person(context.getString(R.string.gala_dali), -2376777600000L));
        addFamous(db, new Person(context.getString(R.string.debakey), -1935014400000L));
        addFamous(db, new Person(context.getString(R.string.packard), -1808784000000L));

        // 8 september
        addFamous(db, new Person(context.getString(R.string.lionheart), -25633584000000L));
        addFamous(db, new Person(context.getString(R.string.neckam), -25633584000000L));
        addFamous(db, new Person(context.getString(R.string.mistral), -4396377600000L));
        addFamous(db, new Person(context.getString(R.string.martin_freeman), 53136000000L));
        addFamous(db, new Person(context.getString(R.string.pink), 53136000000L));
        addFamous(db, new Person(context.getString(R.string.wiz_khalifa), 558057600000L));

        // 9 september
        addFamous(db, new Person(context.getString(R.string.frederik_chapman), -7835961600000L));
        addFamous(db, new Person(context.getString(R.string.galvani), -7331040000000L));
        addFamous(db, new Person(context.getString(R.string.leo_tolstoy), -4459363200000L));
        addFamous(db, new Person(context.getString(R.string.usmanov), -514771200000L));
        addFamous(db, new Person(context.getString(R.string.hugh_grant), -293846400000L));
        addFamous(db, new Person(context.getString(R.string.sandler), -104544000000L));

        // 10 september
        addFamous(db, new Person(context.getString(R.string.peirce), -4112208000000L));
        addFamous(db, new Person(context.getString(R.string.elsa_schiaparelli), -2502748800000L));
        addFamous(db, new Person(context.getString(R.string.compton), -2439590400000L));
        addFamous(db, new Person(context.getString(R.string.messing), -2218752000000L));
        addFamous(db, new Person(context.getString(R.string.lagerfeld), -1145836800000L));
        addFamous(db, new Person(context.getString(R.string.joe_perry), -609379200000L));
        addFamous(db, new Person(context.getString(R.string.firth), -293760000000L));
        addFamous(db, new Person(context.getString(R.string.ritchie), -41299200000L));

        // 11 september
        addFamous(db, new Person(context.getString(R.string.james_thomson), -8498476800000L));
        addFamous(db, new Person(context.getString(R.string.zeiss), -4837881600000L));
        addFamous(db, new Person(context.getString(R.string.o_henry), -3386275200000L));
        addFamous(db, new Person(context.getString(R.string.jeans), -2912889600000L));
        addFamous(db, new Person(context.getString(R.string.beckenbauer), -767059200000L));

        // 12 september
        addFamous(db, new Person(context.getString(R.string.breitner), -3543955200000L));
        addFamous(db, new Person(context.getString(R.string.irene_curie), -2281651200000L));
        addFamous(db, new Person(context.getString(R.string.lem), -1524355200000L));
        addFamous(db, new Person(context.getString(R.string.barry_white), -798508800000L));
        addFamous(db, new Person(context.getString(R.string.farmer), -262051200000L));
        addFamous(db, new Person(context.getString(R.string.walker), 116640000000L));

        // 13 september
        addFamous(db, new Person(context.getString(R.string.samuel_wilson), -6415545600000L));
        addFamous(db, new Person(context.getString(R.string.reed), -3733257600000L));
        addFamous(db, new Person(context.getString(R.string.john_priestley), -2376259200000L));
        addFamous(db, new Person(context.getString(R.string.dahl), -1682035200000L));
        addFamous(db, new Person(context.getString(R.string.maurice_jarre), -1429574400000L));
        addFamous(db, new Person(context.getString(R.string.bisset), -798422400000L));

        // 14 september
        addFamous(db, new Person(context.getString(R.string.agrippa), -15250636800000L));
        addFamous(db, new Person(context.getString(R.string.lely), -11085897600000L));
        addFamous(db, new Person(context.getString(R.string.cecil), -3322857600000L));
        addFamous(db, new Person(context.getString(R.string.dana_gibson), -3228249600000L));
        addFamous(db, new Person(context.getString(R.string.neill), -703728000000L));
        addFamous(db, new Person(context.getString(R.string.winehouse), 432345600000L));

        // 15 september
        addFamous(db, new Person(context.getString(R.string.marco_polo), -22571913600000L));
        addFamous(db, new Person(context.getString(R.string.james_cooper), -5689526400000L));
        addFamous(db, new Person(context.getString(R.string.bugatti), -2786313600000L));
        addFamous(db, new Person(context.getString(R.string.christie), -2502316800000L));
        addFamous(db, new Person(context.getString(R.string.jean_renoir), -2376086400000L));
        addFamous(db, new Person(context.getString(R.string.tommy_lee_jones), -735177600000L));
        addFamous(db, new Person(context.getString(R.string.oliver_stone), -735177600000L));
        addFamous(db, new Person(context.getString(R.string.tom_hardy), 243129600000L));

        // 16 september
        addFamous(db, new Person(context.getString(R.string.kossel), -3669840000000L));
        addFamous(db, new Person(context.getString(R.string.boyd), -2596924800000L));
        addFamous(db, new Person(context.getString(R.string.jellinek), -2533766400000L));
        addFamous(db, new Person(context.getString(R.string.korda), -2407536000000L));
        addFamous(db, new Person(context.getString(R.string.bbking), -1397779200000L));
        addFamous(db, new Person(context.getString(R.string.rourke), -545702400000L));
        addFamous(db, new Person(context.getString(R.string.copperfield), -419472000000L));

        // 17 september
        addFamous(db, new Person(context.getString(R.string.riemann), -4521830400000L));
        addFamous(db, new Person(context.getString(R.string.buick), -3638217600000L));
        addFamous(db, new Person(context.getString(R.string.tsiolkovsky), -3543523200000L));
        addFamous(db, new Person(context.getString(R.string.kesey), -1082160000000L));
        addFamous(db, new Person(context.getString(R.string.messner), -798076800000L));
        addFamous(db, new Person(context.getString(R.string.anastacia), -40694400000L));
        addFamous(db, new Person(context.getString(R.string.ovechkin), 495763200000L));

        // 18 september
        addFamous(db, new Person(context.getString(R.string.samuel_johnson), -8213875200000L));
        addFamous(db, new Person(context.getString(R.string.foucault), -4742668800000L));
        addFamous(db, new Person(context.getString(R.string.garbo), -2028758400000L));
        addFamous(db, new Person(context.getString(R.string.mcmillan), -1965686400000L));
        addFamous(db, new Person(context.getString(R.string.werber), -261532800000L));
        addFamous(db, new Person(context.getString(R.string.gandolfini), -261532800000L));
        addFamous(db, new Person(context.getString(R.string.shuttleworth), 117158400000L));

        // 19 september
        addFamous(db, new Person(context.getString(R.string.pajou), -7551100800000L));
        addFamous(db, new Person(context.getString(R.string.golding), -1839369600000L));
        addFamous(db, new Person(context.getString(R.string.irons), -671673600000L));
        addFamous(db, new Person(context.getString(R.string.hornby), -640137600000L));
        addFamous(db, new Person(context.getString(R.string.karelin), -72144000000L));

        // 20 september
        addFamous(db, new Person(context.getString(R.string.moneta), -4300646400000L));
        addFamous(db, new Person(context.getString(R.string.dewar), -4016649600000L));
        addFamous(db, new Person(context.getString(R.string.leo_strauss), -2217888000000L));
        addFamous(db, new Person(context.getString(R.string.loren), -1113436800000L));
        addFamous(db, new Person(context.getString(R.string.george_martin), -671587200000L));

        // 21 september
        addFamous(db, new Person(context.getString(R.string.mcadam), -6730387200000L));
        addFamous(db, new Person(context.getString(R.string.onnes), -3669408000000L));
        addFamous(db, new Person(context.getString(R.string.wells), -3259180800000L));
        addFamous(db, new Person(context.getString(R.string.nicolle), -3259180800000L));
        addFamous(db, new Person(context.getString(R.string.stephen_king), -703123200000L));
        addFamous(db, new Person(context.getString(R.string.murray), -608428800000L));
        addFamous(db, new Person(context.getString(R.string.beigbeder), -135043200000L));

        // 22 september
        addFamous(db, new Person(context.getString(R.string.faraday), -5625849600000L));
        addFamous(db, new Person(context.getString(R.string.george_bentham), -5341852800000L));
        addFamous(db, new Person(context.getString(R.string.ciurlionis), -2975097600000L));
        addFamous(db, new Person(context.getString(R.string.muni), -2343945600000L));
        addFamous(db, new Person(context.getString(R.string.huggins), -2154643200000L));
        addFamous(db, new Person(context.getString(R.string.dean_reed), -987033600000L));

        // 23 september
        addFamous(db, new Person(context.getString(R.string.fizeau), -4742236800000L));
        addFamous(db, new Person(context.getString(R.string.robert_bosch), -3416774400000L));
        addFamous(db, new Person(context.getString(R.string.orr), -2817158400000L));
        addFamous(db, new Person(context.getString(R.string.coltrane), -1365638400000L));
        addFamous(db, new Person(context.getString(R.string.romy_schneider), -986947200000L));
        addFamous(db, new Person(context.getString(R.string.julio_iglesias), -829180800000L));
        addFamous(db, new Person(context.getString(R.string.springsteen), -639792000000L));

        // 24 september
        addFamous(db, new Person(context.getString(R.string.cardano), -14776387200000L));
        addFamous(db, new Person(context.getString(R.string.walpole), -7960896000000L));
        addFamous(db, new Person(context.getString(R.string.triolet), -2312150400000L));
        addFamous(db, new Person(context.getString(R.string.f_s_fitzgerald), -2312150400000L));
        addFamous(db, new Person(context.getString(R.string.ochoa), -2028240000000L));
        addFamous(db, new Person(context.getString(R.string.brunner), -1113091200000L));

        // 25 september
        addFamous(db, new Person(context.getString(R.string.thomas_morgan), -3258835200000L));
        addFamous(db, new Person(context.getString(R.string.faulkner), -2280528000000L));
        addFamous(db, new Person(context.getString(R.string.shostakovich), -1996617600000L));
        addFamous(db, new Person(context.getString(R.string.michael_douglas), -797385600000L));
        addFamous(db, new Person(context.getString(R.string.almodovar), -639619200000L));
        addFamous(db, new Person(context.getString(R.string.will_smith), -40003200000L));
        addFamous(db, new Person(context.getString(R.string.zeta_jones), -8467200000L));

        // 26 september
        addFamous(db, new Person(context.getString(R.string.grew), -10359014400000L));
        addFamous(db, new Person(context.getString(R.string.joseph_proust), -6793113600000L));
        addFamous(db, new Person(context.getString(R.string.pavlov), -3795206400000L));
        addFamous(db, new Person(context.getString(R.string.hine), -3006288000000L));
        addFamous(db, new Person(context.getString(R.string.wallis), -2596060800000L));
        addFamous(db, new Person(context.getString(R.string.eliot), -2564438400000L));

        // 27 september
        addFamous(db, new Person(context.getString(R.string.bossuet), -10769155200000L));
        addFamous(db, new Person(context.getString(R.string.deledda), -3100896000000L));
        addFamous(db, new Person(context.getString(R.string.larry_wall), -481680000000L));
        addFamous(db, new Person(context.getString(R.string.welsh), -355449600000L));
        addFamous(db, new Person(context.getString(R.string.paltrow), 86400000000L));
        addFamous(db, new Person(context.getString(R.string.wayne), 401932800000L));

        // 28 september
        addFamous(db, new Person(context.getString(R.string.merimee), -5246726400000L));
        addFamous(db, new Person(context.getString(R.string.moissan), -3700339200000L));
        addFamous(db, new Person(context.getString(R.string.finch), -1680739200000L));
        addFamous(db, new Person(context.getString(R.string.mastroianni), -1428278400000L));
        addFamous(db, new Person(context.getString(R.string.bardot), -1112745600000L));
        addFamous(db, new Person(context.getString(R.string.watts), -39744000000L));
        addFamous(db, new Person(context.getString(R.string.dita_von_teese), 86486400000L));
        addFamous(db, new Person(context.getString(R.string.emelianenko), 212716800000L));

        // 29 september
        addFamous(db, new Person(context.getString(R.string.caravaggio), -12566966400000L));
        addFamous(db, new Person(context.getString(R.string.horatio_nelson), -6666624000000L));
        addFamous(db, new Person(context.getString(R.string.gaskell), -5025715200000L));
        addFamous(db, new Person(context.getString(R.string.fermi), -2154038400000L));
        addFamous(db, new Person(context.getString(R.string.ostrovsky), -2059344000000L));
        addFamous(db, new Person(context.getString(R.string.antonioni), -1806883200000L));

        // 30 september
        addFamous(db, new Person(context.getString(R.string.condillac), -8055072000000L));
        addFamous(db, new Person(context.getString(R.string.wrigley), -3416169600000L));
        addFamous(db, new Person(context.getString(R.string.perrin), -3132172800000L));
        addFamous(db, new Person(context.getString(R.string.geiger), -2753481600000L));
        addFamous(db, new Person(context.getString(R.string.kerr), -1522800000000L));
        addFamous(db, new Person(context.getString(R.string.capote), -1428105600000L));
        addFamous(db, new Person(context.getString(R.string.bellucci), -165801600000L));
        addFamous(db, new Person(context.getString(R.string.cotillard), 181267200000L));

        // 1 october
        addFamous(db, new Person(context.getString(R.string.boeing), -2784931200000L));
        addFamous(db, new Person(context.getString(R.string.richard_harris), -1238716800000L));
        addFamous(db, new Person(context.getString(R.string.andrews), -1080950400000L));
        addFamous(db, new Person(context.getString(R.string.annaud), -828489600000L));
        addFamous(db, new Person(context.getString(R.string.galifianakis), -7948800000L));
        addFamous(db, new Person(context.getString(R.string.brie_larson), 623203200000L));

        // 2 october
        addFamous(db, new Person(context.getString(R.string.ramsay), -3699993600000L));
        addFamous(db, new Person(context.getString(R.string.gandhi), -3163536000000L));
        addFamous(db, new Person(context.getString(R.string.greene), -2059084800000L));
        addFamous(db, new Person(context.getString(R.string.willy_ley), -1996012800000L));
        addFamous(db, new Person(context.getString(R.string.karan), -670550400000L));
        addFamous(db, new Person(context.getString(R.string.sting), -575942400000L));

        // 3 october
        addFamous(db, new Person(context.getString(R.string.shmelyov), -3037219200000L));
        addFamous(db, new Person(context.getString(R.string.yesenin), -2342995200000L));
        addFamous(db, new Person(context.getString(R.string.aragon), -2279836800000L));
        addFamous(db, new Person(context.getString(R.string.wolfe), -2185228800000L));
        addFamous(db, new Person(context.getString(R.string.stefani), -7776000000L));
        addFamous(db, new Person(context.getString(R.string.headey), 118454400000L));
        addFamous(db, new Person(context.getString(R.string.ibrahimovic), 370915200000L));
        addFamous(db, new Person(context.getString(R.string.vikander), 591840000000L));

        // 4 october
        addFamous(db, new Person(context.getString(R.string.piranesi), -7865337600000L));
        addFamous(db, new Person(context.getString(R.string.pottier), -4835894400000L));
        addFamous(db, new Person(context.getString(R.string.boussenard), -3857673600000L));
        addFamous(db, new Person(context.getString(R.string.waltz), -417916800000L));
        addFamous(db, new Person(context.getString(R.string.silverstone), 213235200000L));
        addFamous(db, new Person(context.getString(R.string.dakota_johnson), 623462400000L));

        // 5 october
        addFamous(db, new Person(context.getString(R.string.diderot), -8086176000000L));
        addFamous(db, new Person(context.getString(R.string.lumiere), -3321043200000L));
        addFamous(db, new Person(context.getString(R.string.rous), -2847744000000L));
        addFamous(db, new Person(context.getString(R.string.kroc), -2121984000000L));
        addFamous(db, new Person(context.getString(R.string.lemieux), -133833600000L));
        addFamous(db, new Person(context.getString(R.string.pearce), -70761600000L));
        addFamous(db, new Person(context.getString(R.string.winslet), 181699200000L));
        addFamous(db, new Person(context.getString(R.string.eisenberg), 434160000000L));

        // 6 october
        addFamous(db, new Person(context.getString(R.string.maskelyne), -7486473600000L));
        addFamous(db, new Person(context.getString(R.string.smuglewicz), -7076246400000L));
        addFamous(db, new Person(context.getString(R.string.westinghouse), -3889036800000L));
        addFamous(db, new Person(context.getString(R.string.fessenden), -3257884800000L));
        addFamous(db, new Person(context.getString(R.string.corbusier), -2595196800000L));
        addFamous(db, new Person(context.getString(R.string.ernest_walton), -2090361600000L));
        addFamous(db, new Person(context.getString(R.string.heyerdahl), -1743206400000L));

        // 7 october
        addFamous(db, new Person(context.getString(R.string.niels_bohr), -2658182400000L));
        addFamous(db, new Person(context.getString(R.string.alcantara), -2311027200000L));
        addFamous(db, new Person(context.getString(R.string.keneally), -1080432000000L));
        addFamous(db, new Person(context.getString(R.string.putin), -543888000000L));
        addFamous(db, new Person(context.getString(R.string.braxton), -70588800000L));

        // 8 october
        addFamous(db, new Person(context.getString(R.string.geyter), -3825705600000L));
        addFamous(db, new Person(context.getString(R.string.poddubny), -3099945600000L));
        addFamous(db, new Person(context.getString(R.string.tsvetaeva), -2437171200000L));
        addFamous(db, new Person(context.getString(R.string.voicu), -1459036800000L));
        addFamous(db, new Person(context.getString(R.string.louise_hay), -1364342400000L));
        addFamous(db, new Person(context.getString(R.string.weaver), -638496000000L));
        addFamous(db, new Person(context.getString(R.string.matt_damon), 24192000000L));

        // 9 october
        addFamous(db, new Person(context.getString(R.string.sorbon), -24242371200000L));
        addFamous(db, new Person(context.getString(R.string.segner), -8369827200000L));
        addFamous(db, new Person(context.getString(R.string.saint_saens), -4235932800000L));
        addFamous(db, new Person(context.getString(R.string.lennon), -922406400000L));
        addFamous(db, new Person(context.getString(R.string.mcqueen), -7257600000L));

        // 10 october
        addFamous(db, new Person(context.getString(R.string.watteau), -9000806400000L));
        addFamous(db, new Person(context.getString(R.string.cavendish), -7517750400000L));
        addFamous(db, new Person(context.getString(R.string.verdi), -4930070400000L));
        addFamous(db, new Person(context.getString(R.string.nansen), -3415305600000L));
        addFamous(db, new Person(context.getString(R.string.andric), -2436998400000L));
        addFamous(db, new Person(context.getString(R.string.giacometti), -2153088000000L));
        addFamous(db, new Person(context.getString(R.string.pavel_durov), 466214400000L));

        // 11 october
        addFamous(db, new Person(context.getString(R.string.olbers), -6665587200000L));
        addFamous(db, new Person(context.getString(R.string.berlier), -4046371200000L));
        addFamous(db, new Person(context.getString(R.string.heinz), -3951676800000L));
        addFamous(db, new Person(context.getString(R.string.roosevelt), -2689372800000L));
        addFamous(db, new Person(context.getString(R.string.mauriac), -2657836800000L));

        // 12 october
        addFamous(db, new Person(context.getString(R.string.sperry), -3446668800000L));
        addFamous(db, new Person(context.getString(R.string.harden), -3288902400000L));
        addFamous(db, new Person(context.getString(R.string.horch), -3194208000000L));
        addFamous(db, new Person(context.getString(R.string.crowley), -2973369600000L));
        addFamous(db, new Person(context.getString(R.string.montale), -2310595200000L));
        addFamous(db, new Person(context.getString(R.string.pavarotti), -1080000000000L));
        addFamous(db, new Person(context.getString(R.string.jackman), -38534400000L));

        // 13 october
        addFamous(db, new Person(context.getString(R.string.tatum), -1900368000000L));
        addFamous(db, new Person(context.getString(R.string.thatcher), -1395446400000L));
        addFamous(db, new Person(context.getString(R.string.hunter), -890524800000L));
        addFamous(db, new Person(context.getString(R.string.simon), -890524800000L));
        addFamous(db, new Person(context.getString(R.string.cohen), 56160000000L));

        // 14 october
        addFamous(db, new Person(context.getString(R.string.william_penn), -10262764800000L));
        addFamous(db, new Person(context.getString(R.string.gish), -2405116800000L));
        addFamous(db, new Person(context.getString(R.string.roger_moore), -1332288000000L));
        addFamous(db, new Person(context.getString(R.string.lauren), -953596800000L));
        addFamous(db, new Person(context.getString(R.string.wasikowska), 624326400000L));

        // 15 october
        addFamous(db, new Person(context.getString(R.string.torricelli), -11398752000000L));
        addFamous(db, new Person(context.getString(R.string.lermontov), -4898102400000L));
        addFamous(db, new Person(context.getString(R.string.asaph_hall), -4424716800000L));
        addFamous(db, new Person(context.getString(R.string.nietzsche), -3951331200000L));
        addFamous(db, new Person(context.getString(R.string.ilf), -2278800000000L));
        addFamous(db, new Person(context.getString(R.string.puzo), -1553040000000L));
        addFamous(db, new Person(context.getString(R.string.fm_2030), -1237507200000L));

        // 16 october
        addFamous(db, new Person(context.getString(R.string.haller), -8242992000000L));
        addFamous(db, new Person(context.getString(R.string.wilde), -3635712000000L));
        addFamous(db, new Person(context.getString(R.string.oneill), -2562710400000L));
        addFamous(db, new Person(context.getString(R.string.grass), -1332115200000L));
        addFamous(db, new Person(context.getString(R.string.paffgen), -984960000000L));
        addFamous(db, new Person(context.getString(R.string.robbins), -353808000000L));

        // 17 october
        addFamous(db, new Person(context.getString(R.string.orlov), -7422451200000L));
        addFamous(db, new Person(context.getString(R.string.saint_simon), -6601910400000L));
        addFamous(db, new Person(context.getString(R.string.jordan), -669254400000L));
        addFamous(db, new Person(context.getString(R.string.eminem), 88128000000L));
        addFamous(db, new Person(context.getString(R.string.raikkonen), 308966400000L));
        addFamous(db, new Person(context.getString(R.string.felicity_jones), 435196800000L));

        // 18 october
        addFamous(db, new Person(context.getString(R.string.schonbein), -5371142400000L));
        addFamous(db, new Person(context.getString(R.string.glumer), -4550688000000L));
        addFamous(db, new Person(context.getString(R.string.lodygin), -3856464000000L));
        addFamous(db, new Person(context.getString(R.string.bergson), -3477772800000L));
        addFamous(db, new Person(context.getString(R.string.chuck_berry), -1363478400000L));
        addFamous(db, new Person(context.getString(R.string.george_scott), -1331942400000L));
        addFamous(db, new Person(context.getString(R.string.van_damme), -290476800000L));

        // 19 october
        addFamous(db, new Person(context.getString(R.string.ficino), -16920144000000L));
        addFamous(db, new Person(context.getString(R.string.auguste_lumiere), -3382992000000L));
        addFamous(db, new Person(context.getString(R.string.boccioni), -2751840000000L));
        addFamous(db, new Person(context.getString(R.string.gilels), -1678924800000L));
        addFamous(db, new Person(context.getString(R.string.holyfield), -227318400000L));
        addFamous(db, new Person(context.getString(R.string.trey_parker), -6393600000L));

        // 20 october
        addFamous(db, new Person(context.getString(R.string.bartholin), -11145859200000L));
        addFamous(db, new Person(context.getString(R.string.wren), -10640937600000L));
        addFamous(db, new Person(context.getString(R.string.rimbaud), -3635366400000L));
        addFamous(db, new Person(context.getString(R.string.chadwick), -2467756800000L));
        addFamous(db, new Person(context.getString(R.string.bernat), -1458000000000L));
        addFamous(db, new Person(context.getString(R.string.jelinek), -732153600000L));
        addFamous(db, new Person(context.getString(R.string.snoop_dogg), 56764800000L));

        // 21 october
        addFamous(db, new Person(context.getString(R.string.coleridge), -6222873600000L));
        addFamous(db, new Person(context.getString(R.string.nobel), -4297968000000L));
        addFamous(db, new Person(context.getString(R.string.mikhalkov), -763603200000L));
        addFamous(db, new Person(context.getString(R.string.carrie_fisher), -416448000000L));
        addFamous(db, new Person(context.getString(R.string.geim), -353376000000L));
        addFamous(db, new Person(context.getString(R.string.kardashian), 340934400000L));

        // 22 october
        addFamous(db, new Person(context.getString(R.string.liszt), -4992192000000L));
        addFamous(db, new Person(context.getString(R.string.bernhardt), -3950726400000L));
        addFamous(db, new Person(context.getString(R.string.bunin), -3130272000000L));
        addFamous(db, new Person(context.getString(R.string.yashin), -1268438400000L));
        addFamous(db, new Person(context.getString(R.string.christopher_lloyd), -984441600000L));
        addFamous(db, new Person(context.getString(R.string.deneuve), -826675200000L));
        addFamous(db, new Person(context.getString(R.string.wenger), -637286400000L));

        // 23 october
        addFamous(db, new Person(context.getString(R.string.larousse), -4802716800000L));
        addFamous(db, new Person(context.getString(R.string.lanchester), -3193257600000L));
        addFamous(db, new Person(context.getString(R.string.lewis), -2972419200000L));
        addFamous(db, new Person(context.getString(R.string.bloch), -2025734400000L));
        addFamous(db, new Person(context.getString(R.string.pele), -921196800000L));
        addFamous(db, new Person(context.getString(R.string.reynolds), 214876800000L));
        addFamous(db, new Person(context.getString(R.string.clarke), 530409600000L));

        // 24 october
        addFamous(db, new Person(context.getString(R.string.robbia), -16856640000000L));
        addFamous(db, new Person(context.getString(R.string.leeuwenhoek), -10640592000000L));
        addFamous(db, new Person(context.getString(R.string.wilhelm_weber), -5212857600000L));
        addFamous(db, new Person(context.getString(R.string.swarovski), -3382560000000L));
        addFamous(db, new Person(context.getString(R.string.raikin), -1836345600000L));
        addFamous(db, new Person(context.getString(R.string.rooney), 498960000000L));
        addFamous(db, new Person(context.getString(R.string.drake), 530496000000L));

        // 25 october
        addFamous(db, new Person(context.getString(R.string.galois), -4991932800000L));
        addFamous(db, new Person(context.getString(R.string.johann_strauss), -4550083200000L));
        addFamous(db, new Person(context.getString(R.string.bizet), -4139856000000L));
        addFamous(db, new Person(context.getString(R.string.picasso), -2782857600000L));
        addFamous(db, new Person(context.getString(R.string.gance), -2530396800000L));
        addFamous(db, new Person(context.getString(R.string.katy_perry), 467510400000L));

        // 26 october
        addFamous(db, new Person(context.getString(R.string.scarlatti), -8967888000000L));
        addFamous(db, new Person(context.getString(R.string.goldschmidt), -4739385600000L));
        addFamous(db, new Person(context.getString(R.string.vereshchagin), -4013539200000L));
        addFamous(db, new Person(context.getString(R.string.bely), -2814307200000L));
        addFamous(db, new Person(context.getString(R.string.napoleon_hill), -2719699200000L));

        // 27 october
        addFamous(db, new Person(context.getString(R.string.paganini), -5906822400000L));
        addFamous(db, new Person(context.getString(R.string.falk), -2624918400000L));
        addFamous(db, new Person(context.getString(R.string.cleese), -952473600000L));
        addFamous(db, new Person(context.getString(R.string.simon_le_bon), -352857600000L));
        addFamous(db, new Person(context.getString(R.string.vanessa_mae), 278294400000L));

        // 28 october
        addFamous(db, new Person(context.getString(R.string.edith_head), -2277676800000L));
        addFamous(db, new Person(context.getString(R.string.waugh), -2088460800000L));
        addFamous(db, new Person(context.getString(R.string.garrincha), -1141689600000L));
        addFamous(db, new Person(context.getString(R.string.bill_gates), -447465600000L));
        addFamous(db, new Person(context.getString(R.string.ramazzotti), -195004800000L));
        addFamous(db, new Person(context.getString(R.string.julia_roberts), -68774400000L));
        addFamous(db, new Person(context.getString(R.string.joaquin_phoenix), 152150400000L));

        // 29 october
        addFamous(db, new Person(context.getString(R.string.stur), -4865356800000L));
        addFamous(db, new Person(context.getString(R.string.ioffe), -2814048000000L));
        addFamous(db, new Person(context.getString(R.string.phalle), -1236297600000L));
        addFamous(db, new Person(context.getString(R.string.dreyfuss), -699840000000L));
        addFamous(db, new Person(context.getString(R.string.ryder), 57542400000L));

        // 30 october
        addFamous(db, new Person(context.getString(R.string.kauffmann), -7200403200000L));
        addFamous(db, new Person(context.getString(R.string.sheridan), -6884870400000L));
        addFamous(db, new Person(context.getString(R.string.chenier), -6537715200000L));
        addFamous(db, new Person(context.getString(R.string.valery), -3098044800000L));
        addFamous(db, new Person(context.getString(R.string.maradona), -289440000000L));
        addFamous(db, new Person(context.getString(R.string.belleci), 26092800000L));

        // 31 october
        addFamous(db, new Person(context.getString(R.string.vermeer), -10639987200000L));
        addFamous(db, new Person(context.getString(R.string.keats), -5496249600000L));
        addFamous(db, new Person(context.getString(R.string.weierstrass), -4865184000000L));
        addFamous(db, new Person(context.getString(R.string.baeyer), -4234032000000L));
        addFamous(db, new Person(context.getString(R.string.helmut_newton), -1551657600000L));
        addFamous(db, new Person(context.getString(R.string.peter_jackson), -257817600000L));
        addFamous(db, new Person(context.getString(R.string.rob_schneider), -194745600000L));

        // 1 november
        addFamous(db, new Person(context.getString(R.string.cortona), -11775974400000L));
        addFamous(db, new Person(context.getString(R.string.canova), -6695308800000L));
        addFamous(db, new Person(context.getString(R.string.grieg), -2119651200000L));
        addFamous(db, new Person(context.getString(R.string.flynt), -857347200000L));
        addFamous(db, new Person(context.getString(R.string.kiedis), -226195200000L));
        addFamous(db, new Person(context.getString(R.string.rai), 120960000000L));

        // 2 november
        addFamous(db, new Person(context.getString(R.string.antoinette), -6758380800000L));
        addFamous(db, new Person(context.getString(R.string.boole), -4865011200000L));
        addFamous(db, new Person(context.getString(R.string.sorel), -3855168000000L));
        addFamous(db, new Person(context.getString(R.string.visconti), -1993334400000L));
        addFamous(db, new Person(context.getString(R.string.keith_emerson), -794102400000L));
        addFamous(db, new Person(context.getString(R.string.khan), -131414400000L));
        addFamous(db, new Person(context.getString(R.string.schwimmer), -99878400000L));

        // 3 november
        addFamous(db, new Person(context.getString(R.string.cellini), -14804467200000L));
        addFamous(db, new Person(context.getString(R.string.marshak), -2592777600000L));
        addFamous(db, new Person(context.getString(R.string.dassler), -2182550400000L));
        addFamous(db, new Person(context.getString(R.string.gerd_muller), -762480000000L));
        addFamous(db, new Person(context.getString(R.string.lundgren), -383788800000L));
        addFamous(db, new Person(context.getString(R.string.newell), -226022400000L));

        // 4 november
        addFamous(db, new Person(context.getString(R.string.reni), -12437625600000L));
        addFamous(db, new Person(context.getString(R.string.bove), -5842972800000L));
        addFamous(db, new Person(context.getString(R.string.shakurantala_devi), -1267315200000L));
        addFamous(db, new Person(context.getString(R.string.mcconaughey), -1267315200000L));
        addFamous(db, new Person(context.getString(R.string.figo), 89683200000L));

        // 5 november
        addFamous(db, new Person(context.getString(R.string.petrov_vodkin), -2876601600000L));
        addFamous(db, new Person(context.getString(R.string.leigh), -1772150400000L));
        addFamous(db, new Person(context.getString(R.string.dassin), -983232000000L));
        addFamous(db, new Person(context.getString(R.string.patrick), -352080000000L));
        addFamous(db, new Person(context.getString(R.string.bryan_adams), -320544000000L));
        addFamous(db, new Person(context.getString(R.string.swinton), -288921600000L));

        // 6 november
        addFamous(db, new Person(context.getString(R.string.sax), -4896201600000L));
        addFamous(db, new Person(context.getString(R.string.charles_dow), -3728592000000L));
        addFamous(db, new Person(context.getString(R.string.sousa), -3633897600000L));
        addFamous(db, new Person(context.getString(R.string.nailsmith), -3412972800000L));
        addFamous(db, new Person(context.getString(R.string.emma_stone), 594777600000L));

        // 7 november
        addFamous(db, new Person(context.getString(R.string.stukeley), -8903779200000L));
        addFamous(db, new Person(context.getString(R.string.james_cook), -7609939200000L));
        addFamous(db, new Person(context.getString(R.string.erkel), -5022345600000L));
        addFamous(db, new Person(context.getString(R.string.casal), -3349814400000L));
        addFamous(db, new Person(context.getString(R.string.marie_curie), -3223584000000L));
        addFamous(db, new Person(context.getString(R.string.camus), -1771977600000L));
        addFamous(db, new Person(context.getString(R.string.guetta), -67910400000L));

        // 8 november
        addFamous(db, new Person(context.getString(R.string.stoker), -3854649600000L));
        addFamous(db, new Person(context.getString(R.string.hausdorff), -3191875200000L));
        addFamous(db, new Person(context.getString(R.string.rorschach), -2686953600000L));
        addFamous(db, new Person(context.getString(R.string.mitchell), -2182118400000L));
        addFamous(db, new Person(context.getString(R.string.barnard), -1487894400000L));
        addFamous(db, new Person(context.getString(R.string.kilby), -1456358400000L));
        addFamous(db, new Person(context.getString(R.string.delon), -1077667200000L));
        addFamous(db, new Person(context.getString(R.string.hiddink), -730512000000L));

        // 9 november
        addFamous(db, new Person(context.getString(R.string.borden), -5306169600000L));
        addFamous(db, new Person(context.getString(R.string.turgenev), -4769712000000L));
        addFamous(db, new Person(context.getString(R.string.gaboriau), -4327862400000L));
        addFamous(db, new Person(context.getString(R.string.monnet), -2560636800000L));
        addFamous(db, new Person(context.getString(R.string.sagan), -1109116800000L));
        addFamous(db, new Person(context.getString(R.string.del_piero), 153187200000L));

        // 10 november
        addFamous(db, new Person(context.getString(R.string.luther), -15340406400000L));
        addFamous(db, new Person(context.getString(R.string.hogarth), -8587900800000L));
        addFamous(db, new Person(context.getString(R.string.schiller), -6631459200000L));
        addFamous(db, new Person(context.getString(R.string.innes), -3412627200000L));
        addFamous(db, new Person(context.getString(R.string.morricone), -1298332800000L));
        addFamous(db, new Person(context.getString(R.string.brittany_murphy), 247968000000L));

        // 11 november
        addFamous(db, new Person(context.getString(R.string.dostoyevsky), -4674844800000L));
        addFamous(db, new Person(context.getString(R.string.maurice_leblanc), -3317846400000L));
        addFamous(db, new Person(context.getString(R.string.vonnegut), -1487635200000L));
        addFamous(db, new Person(context.getString(R.string.brugiroux), -1014249600000L));
        addFamous(db, new Person(context.getString(R.string.demi_moore), -225331200000L));
        addFamous(db, new Person(context.getString(R.string.dicaprio), 153360000000L));

        // 12 november
        addFamous(db, new Person(context.getString(R.string.rodin), -4075142400000L));
        addFamous(db, new Person(context.getString(R.string.grace_kelly), -1266624000000L));
        addFamous(db, new Person(context.getString(R.string.gurchenko), -1077321600000L));
        addFamous(db, new Person(context.getString(R.string.gosling), 342835200000L));
        addFamous(db, new Person(context.getString(R.string.hathaway), 405907200000L));

        // 13 november
        addFamous(db, new Person(context.getString(R.string.montagu), -7925040000000L));
        addFamous(db, new Person(context.getString(R.string.hauy), -7072963200000L));
        addFamous(db, new Person(context.getString(R.string.stevenson), -3759523200000L));
        addFamous(db, new Person(context.getString(R.string.kokkonen), -1518998400000L));
        addFamous(db, new Person(context.getString(R.string.whoopi_goldberg), -446083200000L));
        addFamous(db, new Person(context.getString(R.string.gerard_butler), -4233600000L));

        // 14 november
        addFamous(db, new Person(context.getString(R.string.fulton), -6441724800000L));
        addFamous(db, new Person(context.getString(R.string.bichat), -6252422400000L));
        addFamous(db, new Person(context.getString(R.string.lyell), -5431881600000L));
        addFamous(db, new Person(context.getString(R.string.monet), -4074969600000L));
        addFamous(db, new Person(context.getString(R.string.banting), -2465596800000L));
        addFamous(db, new Person(context.getString(R.string.lindgren), -1960761600000L));

        // 15 november
        addFamous(db, new Person(context.getString(R.string.lavater), -7199020800000L));
        addFamous(db, new Person(context.getString(R.string.chasles), -5558025600000L));
        addFamous(db, new Person(context.getString(R.string.hauptmann), -3380659200000L));
        addFamous(db, new Person(context.getString(R.string.krogh), -3001968000000L));
        addFamous(db, new Person(context.getString(R.string.kroeger), 153705600000L));

        // 16 november
        addFamous(db, new Person(context.getString(R.string.kreutzer), -6410016000000L));
        addFamous(db, new Person(context.getString(R.string.saramago), -1487203200000L));
        addFamous(db, new Person(context.getString(R.string.achebe), -1234742400000L));
        addFamous(db, new Person(context.getString(R.string.krall), -161740800000L));
        addFamous(db, new Person(context.getString(R.string.gyllenhaal), 248486400000L));

        // 17 november
        addFamous(db, new Person(context.getString(R.string.bronzino), -14708649600000L));
        addFamous(db, new Person(context.getString(R.string.mobius), -5652547200000L));
        addFamous(db, new Person(context.getString(R.string.wigner), -2118268800000L));
        addFamous(db, new Person(context.getString(R.string.honda), -1992038400000L));
        addFamous(db, new Person(context.getString(R.string.scorsese), -855964800000L));
        addFamous(db, new Person(context.getString(R.string.devito), -792806400000L));
        addFamous(db, new Person(context.getString(R.string.marceau), -98582400000L));
        addFamous(db, new Person(context.getString(R.string.mcadams), 280108800000L));

        // 18 november
        addFamous(db, new Person(context.getString(R.string.down), -4453315200000L));
        addFamous(db, new Person(context.getString(R.string.nordenskiold), -4327084800000L));
        addFamous(db, new Person(context.getString(R.string.gallup), -2149718400000L));
        addFamous(db, new Person(context.getString(R.string.issigonis), -1991952000000L));
        addFamous(db, new Person(context.getString(R.string.ryazanov), -1329264000000L));
        addFamous(db, new Person(context.getString(R.string.owen_wilson), -35337600000L));

        // 19 november
        addFamous(db, new Person(context.getString(R.string.lomonosov), -8145446400000L));
        addFamous(db, new Person(context.getString(R.string.skoda), -4106160000000L));
        addFamous(db, new Person(context.getString(R.string.avenarius), -3979929600000L));
        addFamous(db, new Person(context.getString(R.string.drucker), -1897171200000L));
        addFamous(db, new Person(context.getString(R.string.calvin_klein), -855792000000L));
        addFamous(db, new Person(context.getString(R.string.ryan), -256176000000L));
        addFamous(db, new Person(context.getString(R.string.jodie_foster), -224640000000L));

        // 20 november
        addFamous(db, new Person(context.getString(R.string.guericke), -11585030400000L));
        addFamous(db, new Person(context.getString(R.string.lagerlof), -3506457600000L));
        addFamous(db, new Person(context.getString(R.string.karl_von_frisch), -2622844800000L));
        addFamous(db, new Person(context.getString(R.string.hubble), -2528150400000L));
        addFamous(db, new Person(context.getString(R.string.osgood), -1676160000000L));

        // 21 november
        addFamous(db, new Person(context.getString(R.string.voltaire), -8681644800000L));
        addFamous(db, new Person(context.getString(R.string.schleiermacher), -6346425600000L));
        addFamous(db, new Person(context.getString(R.string.lewis_morgan), -4768675200000L));
        addFamous(db, new Person(context.getString(R.string.makarova), -918691200000L));
        addFamous(db, new Person(context.getString(R.string.hawn), -760924800000L));
        addFamous(db, new Person(context.getString(R.string.bjork), -129772800000L));

        // 22 november
        addFamous(db, new Person(context.getString(R.string.vladimir_dal), -5305046400000L));
        addFamous(db, new Person(context.getString(R.string.thomas_cook), -5084121600000L));
        addFamous(db, new Person(context.getString(R.string.gide), -3159129600000L));
        addFamous(db, new Person(context.getString(R.string.gaulle), -2496441600000L));
        addFamous(db, new Person(context.getString(R.string.pelevin), -224380800000L));
        addFamous(db, new Person(context.getString(R.string.mikkelsen), -129686400000L));
        addFamous(db, new Person(context.getString(R.string.ruffalo), -66614400000L));
        addFamous(db, new Person(context.getString(R.string.ville_valo), 217468800000L));
        addFamous(db, new Person(context.getString(R.string.scarlett_johansson), 469929600000L));

        // 23 november
        addFamous(db, new Person(context.getString(R.string.waals), -4168886400000L));
        addFamous(db, new Person(context.getString(R.string.karloff), -2591049600000L));
        addFamous(db, new Person(context.getString(R.string.moseley), -2591049600000L));
        addFamous(db, new Person(context.getString(R.string.nosov), -1928361600000L));
        addFamous(db, new Person(context.getString(R.string.cyrus), 722476800000L));

        // 24 november
        addFamous(db, new Person(context.getString(R.string.spinoza), -10637913600000L));
        addFamous(db, new Person(context.getString(R.string.suvorov), -7576934400000L));
        addFamous(db, new Person(context.getString(R.string.ellis), -5147107200000L));
        addFamous(db, new Person(context.getString(R.string.collodi), -4515955200000L));
        addFamous(db, new Person(context.getString(R.string.carnegie), -2559340800000L));
        addFamous(db, new Person(context.getString(R.string.kusturica), -476668800000L));
        addFamous(db, new Person(context.getString(R.string.heigl), 280713600000L));

        // 25 november
        addFamous(db, new Person(context.getString(R.string.vega), -12846038400000L));
        addFamous(db, new Person(context.getString(R.string.sumarokov), -7955539200000L));
        addFamous(db, new Person(context.getString(R.string.pirogov), -5020790400000L));
        addFamous(db, new Person(context.getString(R.string.benz), -3947788800000L));
        addFamous(db, new Person(context.getString(R.string.vavilov), -2590876800000L));
        addFamous(db, new Person(context.getString(R.string.poul_anderson), -1360195200000L));

        // 26 november
        addFamous(db, new Person(context.getString(R.string.harvard), -11426745600000L));
        addFamous(db, new Person(context.getString(R.string.saussure), -3537475200000L));
        addFamous(db, new Person(context.getString(R.string.leck), -2937859200000L));
        addFamous(db, new Person(context.getString(R.string.wiener), -2369865600000L));
        addFamous(db, new Person(context.getString(R.string.ionesco), -1896566400000L));
        addFamous(db, new Person(context.getString(R.string.tina_turner), -949881600000L));

        // 27 november
        addFamous(db, new Person(context.getString(R.string.celsius), -8460288000000L));
        addFamous(db, new Person(context.getString(R.string.weizmann), -3000931200000L));
        addFamous(db, new Person(context.getString(R.string.matsushita), -2369779200000L));
        addFamous(db, new Person(context.getString(R.string.bruce_lee), -918172800000L));
        addFamous(db, new Person(context.getString(R.string.mashkov), -192412800000L));

        // 28 november
        addFamous(db, new Person(context.getString(R.string.lully), -10637568000000L));
        addFamous(db, new Person(context.getString(R.string.blake), -6692976000000L));
        addFamous(db, new Person(context.getString(R.string.cousin), -5588438400000L));
        addFamous(db, new Person(context.getString(R.string.engels), -4704912000000L));
        addFamous(db, new Person(context.getString(R.string.anton_rubinstein), -4420915200000L));
        addFamous(db, new Person(context.getString(R.string.blok), -2811456000000L));
        addFamous(db, new Person(context.getString(R.string.zanetti), -413164800000L));
        addFamous(db, new Person(context.getString(R.string.galliano), -286934400000L));

        // 29 november
        addFamous(db, new Person(context.getString(R.string.donizetti), -5430585600000L));
        addFamous(db, new Person(context.getString(R.string.hauff), -5272905600000L));
        addFamous(db, new Person(context.getString(R.string.charcot), -4547059200000L));
        addFamous(db, new Person(context.getString(R.string.john_fleming), -3789676800000L));
        addFamous(db, new Person(context.getString(R.string.giggs), 123379200000L));
        addFamous(db, new Person(context.getString(R.string.faris), 218073600000L));

        // 30 november
        addFamous(db, new Person(context.getString(R.string.swift), -9532944000000L));
        addFamous(db, new Person(context.getString(R.string.twain), -4231440000000L));
        addFamous(db, new Person(context.getString(R.string.churchill), -3000672000000L));
        addFamous(db, new Person(context.getString(R.string.ridley_scott), -1012608000000L));
        addFamous(db, new Person(context.getString(R.string.idol), -444614400000L));
        addFamous(db, new Person(context.getString(R.string.stiller), -128995200000L));

        // 1 december
        addFamous(db, new Person(context.getString(R.string.falconet), -7986556800000L));
        addFamous(db, new Person(context.getString(R.string.tussaud), -6566486400000L));
        addFamous(db, new Person(context.getString(R.string.lobachevsky), -5588179200000L));
        addFamous(db, new Person(context.getString(R.string.zhukov), -2306275200000L));
        addFamous(db, new Person(context.getString(R.string.allen), -1075680000000L));
        addFamous(db, new Person(context.getString(R.string.escobar), -633830400000L));

        // 2 december
        addFamous(db, new Person(context.getString(R.string.joseph_bell), -4168108800000L));
        addFamous(db, new Person(context.getString(R.string.seuratl), -3473884800000L));
        addFamous(db, new Person(context.getString(R.string.callas), -1454284800000L));
        addFamous(db, new Person(context.getString(R.string.versace), -728438400000L));
        addFamous(db, new Person(context.getString(R.string.liu), -34128000000L));
        addFamous(db, new Person(context.getString(R.string.furtado), 281404800000L));
        addFamous(db, new Person(context.getString(R.string.spears), 376099200000L));

        // 3 december
        addFamous(db, new Person(context.getString(R.string.hill), -5493398400000L));
        addFamous(db, new Person(context.getString(R.string.rota), -1832889600000L));
        addFamous(db, new Person(context.getString(R.string.osbourne), -665193600000L));
        addFamous(db, new Person(context.getString(R.string.julianne_moore), -286502400000L));
        addFamous(db, new Person(context.getString(R.string.seyfried), 502416000000L));

        // 4 december
        addFamous(db, new Person(context.getString(R.string.chapelain), -11804745600000L));
        addFamous(db, new Person(context.getString(R.string.carlyle), -5493312000000L));
        addFamous(db, new Person(context.getString(R.string.adler), -1769644800000L));
        addFamous(db, new Person(context.getString(R.string.bridges), -633571200000L));
        addFamous(db, new Person(context.getString(R.string.bubka), -191808000000L));
        addFamous(db, new Person(context.getString(R.string.jay_z), -2419200000L));

        // 5 december
        addFamous(db, new Person(context.getString(R.string.tyutchev), -5240851200000L));
        addFamous(db, new Person(context.getString(R.string.lang), -2495318400000L));
        addFamous(db, new Person(context.getString(R.string.disney), -2148249600000L));
        addFamous(db, new Person(context.getString(R.string.heisenberg), -2148249600000L));
        addFamous(db, new Person(context.getString(R.string.carreras), -728179200000L));
        addFamous(db, new Person(context.getString(R.string.kaas), -97027200000L));

        // 6 december
        addFamous(db, new Person(context.getString(R.string.nicolas_leblanc), -7165670400000L));
        addFamous(db, new Person(context.getString(R.string.gay_lussac), -6029596800000L));
        addFamous(db, new Person(context.getString(R.string.veqilharxhi), -5429980800000L));
        addFamous(db, new Person(context.getString(R.string.bazille), -4041532800000L));
        addFamous(db, new Person(context.getString(R.string.crali), -1864166400000L));
        addFamous(db, new Person(context.getString(R.string.nick_park), -349401600000L));

        // 7 december
        addFamous(db, new Person(context.getString(R.string.bernini), -11709792000000L));
        addFamous(db, new Person(context.getString(R.string.schwann), -5019753600000L));
        addFamous(db, new Person(context.getString(R.string.mascagni), -3347222400000L));
        addFamous(db, new Person(context.getString(R.string.yosano), -2873836800000L));
        addFamous(db, new Person(context.getString(R.string.waits), -633312000000L));
        addFamous(db, new Person(context.getString(R.string.emily_browning), 597456000000L));

        // 8 december
        addFamous(db, new Person(context.getString(R.string.dholbach), -7765113600000L));
        addFamous(db, new Person(context.getString(R.string.menzel), -4861900800000L));
        addFamous(db, new Person(context.getString(R.string.bjornson), -4325356800000L));
        addFamous(db, new Person(context.getString(R.string.reynaud), -3946665600000L));
        addFamous(db, new Person(context.getString(R.string.melies), -3410208000000L));
        addFamous(db, new Person(context.getString(R.string.morrison), -822614400000L));
        addFamous(db, new Person(context.getString(R.string.basinger), -506995200000L));
        addFamous(db, new Person(context.getString(R.string.somerhalder), 281923200000L));

        // 9 december
        addFamous(db, new Person(context.getString(R.string.milton), -11394000000000L));
        addFamous(db, new Person(context.getString(R.string.winckelmann), -7954329600000L));
        addFamous(db, new Person(context.getString(R.string.scheele), -7165411200000L));
        addFamous(db, new Person(context.getString(R.string.berthollet), -6976022400000L));
        addFamous(db, new Person(context.getString(R.string.grace_hopper), -1990137600000L));
        addFamous(db, new Person(context.getString(R.string.dench), -1106524800000L));
        addFamous(db, new Person(context.getString(R.string.malkovich), -506908800000L));

        // 10 december
        addFamous(db, new Person(context.getString(R.string.lovelace), -4861728000000L));
        addFamous(db, new Person(context.getString(R.string.nekrasov), -4672339200000L));
        addFamous(db, new Person(context.getString(R.string.sachs), -2463350400000L));
        addFamous(db, new Person(context.getString(R.string.tarasov), -1611360000000L));
        addFamous(db, new Person(context.getString(R.string.michael_duncan), -380592000000L));
        addFamous(db, new Person(context.getString(R.string.branagh), -285897600000L));
        addFamous(db, new Person(context.getString(R.string.molko), 92793600000L));

        // 11 december
        addFamous(db, new Person(context.getString(R.string.berlioz), -5240332800000L));
        addFamous(db, new Person(context.getString(R.string.musset), -5019408000000L));
        addFamous(db, new Person(context.getString(R.string.koch), -3978028800000L));
        addFamous(db, new Person(context.getString(R.string.born), -2747260800000L));
        addFamous(db, new Person(context.getString(R.string.gardel), -2494800000000L));
        addFamous(db, new Person(context.getString(R.string.marais), -1769040000000L));

        // 12 december
        addFamous(db, new Person(context.getString(R.string.karamzin), -6407769600000L));
        addFamous(db, new Person(context.getString(R.string.serebriakova), -2684016000000L));
        addFamous(db, new Person(context.getString(R.string.ozu), -2084572800000L));
        addFamous(db, new Person(context.getString(R.string.sinatra), -1705881600000L));
        addFamous(db, new Person(context.getString(R.string.noyce), -1327190400000L));
        addFamous(db, new Person(context.getString(R.string.konyukhov), -569808000000L));

        // 13 december
        addFamous(db, new Person(context.getString(R.string.gozzi), -7859289600000L));
        addFamous(db, new Person(context.getString(R.string.heine), -5429376000000L));
        addFamous(db, new Person(context.getString(R.string.werner_siemens), -4829846400000L));
        addFamous(db, new Person(context.getString(R.string.birkeland), -3220473600000L));
        addFamous(db, new Person(context.getString(R.string.buscemi), -380332800000L));
        addFamous(db, new Person(context.getString(R.string.foxx), -64800000000L));
        addFamous(db, new Person(context.getString(R.string.amy_lee), 377049600000L));
        addFamous(db, new Person(context.getString(R.string.taylor_swift), 629510400000L));

        // 14 december
        addFamous(db, new Person(context.getString(R.string.nostradamus), -14706316800000L));
        addFamous(db, new Person(context.getString(R.string.brahe), -13349318400000L));
        addFamous(db, new Person(context.getString(R.string.ueshiba), -2715465600000L));
        addFamous(db, new Person(context.getString(R.string.basov), -1484784000000L));
        addFamous(db, new Person(context.getString(R.string.kapoor), -1421625600000L));

        // 15 december
        addFamous(db, new Person(context.getString(R.string.carey), -5555433600000L));
        addFamous(db, new Person(context.getString(R.string.bolyai), -5271523200000L));
        addFamous(db, new Person(context.getString(R.string.eiffel), -4324752000000L));
        addFamous(db, new Person(context.getString(R.string.becquerel), -3693600000000L));
        addFamous(db, new Person(context.getString(R.string.zamenhof), -3472761600000L));
        addFamous(db, new Person(context.getString(R.string.getty), -2431296000000L));

        // 16 december
        addFamous(db, new Person(context.getString(R.string.austen), -6123427200000L));
        addFamous(db, new Person(context.getString(R.string.walras), -4261593600000L));
        addFamous(db, new Person(context.getString(R.string.bergmann), -4198435200000L));
        addFamous(db, new Person(context.getString(R.string.kandinsky), -3251750400000L));
        addFamous(db, new Person(context.getString(R.string.linder), -2715292800000L));
        addFamous(db, new Person(context.getString(R.string.arthur_clarke), -1642377600000L));
        addFamous(db, new Person(context.getString(R.string.paul_van_dyk), 61689600000L));

        // 17 december
        addFamous(db, new Person(context.getString(R.string.cimarosa), -6943795200000L));
        addFamous(db, new Person(context.getString(R.string.beethoven), -6281107200000L));
        addFamous(db, new Person(context.getString(R.string.davy), -6028646400000L));
        addFamous(db, new Person(context.getString(R.string.ganelin), -790214400000L));
        addFamous(db, new Person(context.getString(R.string.darryl_way), -663984000000L));
        addFamous(db, new Person(context.getString(R.string.jovovich), 188006400000L));

        // 18 december
        addFamous(db, new Person(context.getString(R.string.polhem), -9720691200000L));
        addFamous(db, new Person(context.getString(R.string.joseph_thomson), -3567110400000L));
        addFamous(db, new Person(context.getString(R.string.stalin), -2872886400000L));
        addFamous(db, new Person(context.getString(R.string.spielberg), -727056000000L));
        addFamous(db, new Person(context.getString(R.string.liotta), -474595200000L));
        addFamous(db, new Person(context.getString(R.string.brad_pitt), -190598400000L));
        addFamous(db, new Person(context.getString(R.string.holmes), 282787200000L));
        addFamous(db, new Person(context.getString(R.string.aguilera), 345945600000L));

        // 19 december
        addFamous(db, new Person(context.getString(R.string.michelson), -3693254400000L));
        addFamous(db, new Person(context.getString(R.string.joze_lima), -1863043200000L));
        addFamous(db, new Person(context.getString(R.string.piaf), -1705276800000L));
        addFamous(db, new Person(context.getString(R.string.schweiger), -190512000000L));
        addFamous(db, new Person(context.getString(R.string.milano), 93571200000L));
        addFamous(db, new Person(context.getString(R.string.jake_gyllenhaal), 346032000000L));

        // 20 december
        addFamous(db, new Person(context.getString(R.string.heyrovsky), -2494022400000L));
        addFamous(db, new Person(context.getString(R.string.balandin), -2241561600000L));
        addFamous(db, new Person(context.getString(R.string.graaff), -2146953600000L));
        addFamous(db, new Person(context.getString(R.string.bohm), -1642032000000L));
        addFamous(db, new Person(context.getString(R.string.uri_geller), -726883200000L));
        addFamous(db, new Person(context.getString(R.string.jonah_hill), 440726400000L));

        // 21 december
        addFamous(db, new Person(context.getString(R.string.robert_brown), -6186067200000L));
        addFamous(db, new Person(context.getString(R.string.hermann_muller), -2493936000000L));
        addFamous(db, new Person(context.getString(R.string.boll), -1641945600000L));
        addFamous(db, new Person(context.getString(R.string.monterroso), -1515715200000L));
        addFamous(db, new Person(context.getString(R.string.jane_fonda), -1010793600000L));
        addFamous(db, new Person(context.getString(R.string.zappa), -916099200000L));
        addFamous(db, new Person(context.getString(R.string.samuel_jackson), -663638400000L));

        // 22 december
        addFamous(db, new Person(context.getString(R.string.liotard), -8426592000000L));
        addFamous(db, new Person(context.getString(R.string.fabre), -4608230400000L));
        addFamous(db, new Person(context.getString(R.string.puccini), -3503692800000L));
        addFamous(db, new Person(context.getString(R.string.elizondo), -1042243200000L));
        addFamous(db, new Person(context.getString(R.string.fiennes), -221788800000L));
        addFamous(db, new Person(context.getString(R.string.paradis), 93830400000L));

        // 23 december
        addFamous(db, new Person(context.getString(R.string.champollion), -5649436800000L));
        addFamous(db, new Person(context.getString(R.string.bryullov), -5365440000000L));
        addFamous(db, new Person(context.getString(R.string.dino_risi), -1673308800000L));
        addFamous(db, new Person(context.getString(R.string.baker), -1263081600000L));
        addFamous(db, new Person(context.getString(R.string.bosque), -600393600000L));
        addFamous(db, new Person(context.getString(R.string.bruni), -63936000000L));

        // 24 december
        addFamous(db, new Person(context.getString(R.string.joule), -4765824000000L));
        addFamous(db, new Person(context.getString(R.string.howard_hughes), -2020377600000L));
        addFamous(db, new Person(context.getString(R.string.chase), -1988841600000L));
        addFamous(db, new Person(context.getString(R.string.gardner), -1483920000000L));
        addFamous(db, new Person(context.getString(R.string.ricky_martin), 62380800000L));
        addFamous(db, new Person(context.getString(R.string.stephenie_meyer), 125539200000L));

        // 25 december
        addFamous(db, new Person(context.getString(R.string.chevrolet), -2872281600000L));
        addFamous(db, new Person(context.getString(R.string.rosenzweig), -2619820800000L));
        addFamous(db, new Person(context.getString(R.string.hilton), -2588284800000L));
        addFamous(db, new Person(context.getString(R.string.bogart), -2209593600000L));
        addFamous(db, new Person(context.getString(R.string.castaneda), -1389139200000L));
        addFamous(db, new Person(context.getString(R.string.lennox), -473990400000L));
        addFamous(db, new Person(context.getString(R.string.buuren), 220320000000L));

        // 26 december
        addFamous(db, new Person(context.getString(R.string.dinglinger), -9625305600000L));
        addFamous(db, new Person(context.getString(R.string.babbage), -5617641600000L));
        addFamous(db, new Person(context.getString(R.string.henry_miller), -2461968000000L));
        addFamous(db, new Person(context.getString(R.string.zedong), -2398809600000L));
        addFamous(db, new Person(context.getString(R.string.ulrich), -189907200000L));
        addFamous(db, new Person(context.getString(R.string.jared_leto), 62553600000L));

        // 27 december
        addFamous(db, new Person(context.getString(R.string.kepler), -12559276800000L));
        addFamous(db, new Person(context.getString(R.string.cayley), -6185548800000L));
        addFamous(db, new Person(context.getString(R.string.pasteur), -4639334400000L));
        addFamous(db, new Person(context.getString(R.string.tretyakov), -4323715200000L));
        addFamous(db, new Person(context.getString(R.string.dietrich), -2146348800000L));
        addFamous(db, new Person(context.getString(R.string.depardieu), -663120000000L));

        // 28 december
        addFamous(db, new Person(context.getString(R.string.eddington), -2745792000000L));
        addFamous(db, new Person(context.getString(R.string.murnau), -2556403200000L));
        addFamous(db, new Person(context.getString(R.string.john_neumann), -2083190400000L));
        addFamous(db, new Person(context.getString(R.string.denzel_washington), -473731200000L));
        addFamous(db, new Person(context.getString(R.string.torvalds), -345600000L));
        addFamous(db, new Person(context.getString(R.string.sienna_miller), 378345600000L));

        // 29 december
        addFamous(db, new Person(context.getString(R.string.pompadour), -7826371200000L));
        addFamous(db, new Person(context.getString(R.string.siqueiros), -2303856000000L));
        addFamous(db, new Person(context.getString(R.string.voight), -978566400000L));
        addFamous(db, new Person(context.getString(R.string.dexter_holland), -126489600000L));
        addFamous(db, new Person(context.getString(R.string.jude_law), 94435200000L));

        // 30 december
        addFamous(db, new Person(context.getString(R.string.jablonskis), -3439843200000L));
        addFamous(db, new Person(context.getString(R.string.kipling), -3282076800000L));
        addFamous(db, new Person(context.getString(R.string.patti_smith), -726019200000L));
        addFamous(db, new Person(context.getString(R.string.jay_kay), -172800000L));
        addFamous(db, new Person(context.getString(R.string.tiger_woods), 189129600000L));
        addFamous(db, new Person(context.getString(R.string.lebron_james), 473212800000L));

        // 31 december
        addFamous(db, new Person(context.getString(R.string.boldini), -4007836800000L));
        addFamous(db, new Person(context.getString(R.string.matisse), -3155760000000L));
        addFamous(db, new Person(context.getString(R.string.hopkins), -1009929600000L));
        addFamous(db, new Person(context.getString(R.string.ferguson), -883699200000L));
        addFamous(db, new Person(context.getString(R.string.kingsley), -820627200000L));
        addFamous(db, new Person(context.getString(R.string.willis), -757468800000L));
        addFamous(db, new Person(context.getString(R.string.val_kilmer), -315705600000L));
        addFamous(db, new Person(context.getString(R.string.psy), 252374400000L));
    }
}
