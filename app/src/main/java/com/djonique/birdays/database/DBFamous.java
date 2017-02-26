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


        // 1 march
        addFamous(db, new Person("Frédéric Chopin", -2867184000000L));
        //8 december
        addFamous(db, new Person("Georges Méliès", -3410208000000L));
        // 16 december
        addFamous(db, new Person(context.getString(R.string.beethoven), -6281193600000L));
        // 17 december
        addFamous(db, new Person(context.getString(R.string.humphry_davy), -6028646400000L));
        addFamous(db, new Person(context.getString(R.string.franciscus), -1042675200000L));
    }

    private static void addFamous(SQLiteDatabase db, Person person) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_DATE, person.getDate());
        db.insert(DB_FAMOUS, null, cv);
    }
}
