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
        addFamous(db, new Person(context.getString(R.string.veksler), -2361484800000L));
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



        //8 december
        addFamous(db, new Person("Georges Méliès", -3410208000000L));
        // 16 december
        addFamous(db, new Person("Ludwig van Beethoven", -6281193600000L));
        // 17 december
        addFamous(db, new Person("Humphry Davy", -6028646400000L));
    }

    private static void addFamous(SQLiteDatabase db, Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        db.insert(DB_FAMOUS, null, cv);
    }
}
