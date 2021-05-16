SET FOREIGN_KEY_CHECKS = 0 $$
TRUNCATE TABLE cs353.district $$
INSERT INTO cs353.district(cs353.district.city_name, cs353.district.district_name)
VALUES ("adana", "seyhan"),
       ("adana", "yüreğir"),
       ("adana", "çukurova"),
       ("adana", "ceyhan"),
       ("adana", "sarıçam"),
       ("adana", "kozan"),
       ("adana", "imamoğlu"),
       ("adana", "karataş"),
       ("adana", "karaisalı"),
       ("adana", "pozantı"),
       ("adana", "yumurtalık"),
       ("adana", "tufanbeyli"),
       ("adana", "feke"),
       ("adana", "aladağ"),
       ("adana", "saimbeyli"),
       ("adıyaman", "merkez"),
       ("adıyaman", "kahta"),
       ("adıyaman", "besni"),
       ("adıyaman", "gölbaşı"),
       ("adıyaman", "gerger"),
       ("adıyaman", "sincik"),
       ("adıyaman", "çelikhan"),
       ("adıyaman", "tut"),
       ("adıyaman", "samsat"),
       ("afyonkarahisar", "merkez"),
       ("afyonkarahisar", "sandıklı"),
       ("afyonkarahisar", "dinar"),
       ("afyonkarahisar", "bolvadin"),
       ("afyonkarahisar", "sinanpaşa"),
       ("afyonkarahisar", "emirdağ"),
       ("afyonkarahisar", "şuhut"),
       ("afyonkarahisar", "çay"),
       ("afyonkarahisar", "ihsaniye"),
       ("afyonkarahisar", "iscehisar"),
       ("afyonkarahisar", "sultandağı"),
       ("afyonkarahisar", "çobanlar"),
       ("afyonkarahisar", "dazkırı"),
       ("afyonkarahisar", "başmakçı"),
       ("afyonkarahisar", "hocalar"),
       ("afyonkarahisar", "bayat"),
       ("afyonkarahisar", "evciler"),
       ("afyonkarahisar", "kızılören"),
       ("ağrı", "merkez"),
       ("ağrı", "patnos"),
       ("ağrı", "doğubayazıt"),
       ("ağrı", "diyadin"),
       ("ağrı", "eleşkirt"),
       ("ağrı", "tutak"),
       ("ağrı", "taşlıçay"),
       ("ağrı", "hamur"),
       ("amasya", "merkez"),
       ("amasya", "merzifon"),
       ("amasya", "suluova"),
       ("amasya", "taşova"),
       ("amasya", "gümüşhacıköy"),
       ("amasya", "göynücek"),
       ("amasya", "hamamözü"),
       ("ankara", "çankaya"),
       ("ankara", "keçiören"),
       ("ankara", "yenimahalle"),
       ("ankara", "mamak"),
       ("ankara", "etimesgut"),
       ("ankara", "sincan"),
       ("ankara", "altındağ"),
       ("ankara", "pursaklar"),
       ("ankara", "gölbaşı"),
       ("ankara", "polatlı"),
       ("ankara", "çubuk"),
       ("ankara", "kahramankazan"),
       ("ankara", "beypazarı"),
       ("ankara", "elmadağ"),
       ("ankara", "şereflikoçhisar"),
       ("ankara", "akyurt"),
       ("ankara", "nallıhan"),
       ("ankara", "haymana"),
       ("ankara", "kızılcahamam"),
       ("ankara", "bala"),
       ("ankara", "kalecik"),
       ("ankara", "ayaş"),
       ("ankara", "güdül"),
       ("ankara", "çamlıdere"),
       ("ankara", "evren"),
       ("antalya", "kepez"),
       ("antalya", "muratpaşa"),
       ("antalya", "alanya"),
       ("antalya", "manavgat"),
       ("antalya", "konyaaltı"),
       ("antalya", "serik"),
       ("antalya", "aksu"),
       ("antalya", "kumluca"),
       ("antalya", "döşemealtı"),
       ("antalya", "kaş"),
       ("antalya", "korkuteli"),
       ("antalya", "gazipaşa"),
       ("antalya", "finike"),
       ("antalya", "kemer"),
       ("antalya", "elmalı"),
       ("antalya", "demre"),
       ("antalya", "akseki"),
       ("antalya", "gündoğmuş"),
       ("antalya", "ibradı"),
       ("artvin", "hopa"),
       ("artvin", "merkez"),
       ("artvin", "borçka"),
       ("artvin", "yusufeli"),
       ("artvin", "arhavi"),
       ("artvin", "şavşat"),
       ("artvin", "ardanuç"),
       ("artvin", "murgul"),
       ("aydın", "efeler"),
       ("aydın", "nazilli"),
       ("aydın", "söke"),
       ("aydın", "kuşadası"),
       ("aydın", "didim"),
       ("aydın", "çine"),
       ("aydın", "incirliova"),
       ("aydın", "germencik"),
       ("aydın", "bozdoğan"),
       ("aydın", "köşk"),
       ("aydın", "kuyucak"),
       ("aydın", "sultanhisar"),
       ("aydın", "karacasu"),
       ("aydın", "yenipazar"),
       ("aydın", "buharkent"),
       ("aydın", "karpuzlu"),
       ("balıkesir", "karesi"),
       ("balıkesir", "altıeylül"),
       ("balıkesir", "bandırma"),
       ("balıkesir", "edremit"),
       ("balıkesir", "gönen"),
       ("balıkesir", "ayvalık"),
       ("balıkesir", "burhaniye"),
       ("balıkesir", "bigadiç"),
       ("balıkesir", "susurluk"),
       ("balıkesir", "dursunbey"),
       ("balıkesir", "sındırgı"),
       ("balıkesir", "ivrindi"),
       ("balıkesir", "erdek"),
       ("balıkesir", "havran"),
       ("balıkesir", "kepsut"),
       ("balıkesir", "manyas"),
       ("balıkesir", "savaştepe"),
       ("balıkesir", "balya"),
       ("balıkesir", "gömeç"),
       ("balıkesir", "marmara"),
       ("bilecik", "merkez"),
       ("bilecik", "bozüyük"),
       ("bilecik", "osmaneli"),
       ("bilecik", "söğüt"),
       ("bilecik", "gölpazarı"),
       ("bilecik", "pazaryeri"),
       ("bilecik", "yenipazar"),
       ("bilecik", "inhisar"),
       ("bingöl", "merkez"),
       ("bingöl", "genç"),
       ("bingöl", "solhan"),
       ("bingöl", "karlıova"),
       ("bingöl", "adaklı"),
       ("bingöl", "kiğı"),
       ("bingöl", "yedisu"),
       ("bingöl", "yayladere"),
       ("bitlis", "tatvan"),
       ("bitlis", "merkez"),
       ("bitlis", "güroymak"),
       ("bitlis", "ahlat"),
       ("bitlis", "hizan"),
       ("bitlis", "mutki"),
       ("bitlis", "adilcevaz"),
       ("bolu", "merkez"),
       ("bolu", "gerede"),
       ("bolu", "mudurnu"),
       ("bolu", "göynük"),
       ("bolu", "mengen"),
       ("bolu", "yeniçağa"),
       ("bolu", "dörtdivan"),
       ("bolu", "seben"),
       ("bolu", "kıbrıscık"),
       ("burdur", "merkez"),
       ("burdur", "bucak"),
       ("burdur", "gölhisar"),
       ("burdur", "yeşilova"),
       ("burdur", "çavdır"),
       ("burdur", "tefenni"),
       ("burdur", "ağlasun"),
       ("burdur", "karamanlı"),
       ("burdur", "altınyayla"),
       ("burdur", "çeltikçi"),
       ("burdur", "kemer"),
       ("bursa", "osmangazi"),
       ("bursa", "yıldırım"),
       ("bursa", "nilüfer"),
       ("bursa", "gemlik"),
       ("bursa", "mustafakemalpaşa"),
       ("bursa", "mudanya"),
       ("bursa", "gürsu"),
       ("bursa", "karacabey"),
       ("bursa", "orhangazi"),
       ("bursa", "kestel"),
       ("bursa", "yenişehir"),
       ("bursa", "iznik"),
       ("bursa", "orhaneli"),
       ("bursa", "keles"),
       ("bursa", "büyükorhan"),
       ("bursa", "harmancık"),
       ("çanakkale", "merkez"),
       ("çanakkale", "biga"),
       ("çanakkale", "çan"),
       ("çanakkale", "gelibolu"),
       ("çanakkale", "yenice"),
       ("çanakkale", "ayvacık"),
       ("çanakkale", "ezine"),
       ("çanakkale", "bayramiç"),
       ("çanakkale", "lapseki"),
       ("çanakkale", "eceabat"),
       ("çanakkale", "gökçeada"),
       ("çanakkale", "bozcaada"),
       ("çankırı", "merkez"),
       ("çankırı", "çerkeş"),
       ("çankırı", "ılgaz"),
       ("çankırı", "orta"),
       ("çankırı", "şabanözü"),
       ("çankırı", "kurşunlu"),
       ("çankırı", "yapraklı"),
       ("çankırı", "kızılırmak"),
       ("çankırı", "atkaracalar"),
       ("çankırı", "eldivan"),
       ("çankırı", "korgun"),
       ("çankırı", "bayramören"),
       ("çorum", "merkez"),
       ("çorum", "sungurlu"),
       ("çorum", "osmancık"),
       ("çorum", "iskilip"),
       ("çorum", "alaca"),
       ("çorum", "bayat"),
       ("çorum", "mecitözü"),
       ("çorum", "kargı"),
       ("çorum", "ortaköy"),
       ("çorum", "uğurludağ"),
       ("çorum", "dodurga"),
       ("çorum", "oğuzlar"),
       ("çorum", "laçin"),
       ("çorum", "boğazkale"),
       ("denizli", "pamukkale"),
       ("denizli", "merkezefendi"),
       ("denizli", "çivril"),
       ("denizli", "acıpayam"),
       ("denizli", "tavas"),
       ("denizli", "honaz"),
       ("denizli", "sarayköy"),
       ("denizli", "buldan"),
       ("denizli", "kale"),
       ("denizli", "çal"),
       ("denizli", "çameli"),
       ("denizli", "serinhisar"),
       ("denizli", "bozkurt"),
       ("denizli", "güney"),
       ("denizli", "çardak"),
       ("denizli", "bekilli"),
       ("denizli", "beyağaç"),
       ("denizli", "babadağ"),
       ("denizli", "baklan"),
       ("diyarbakır", "bağlar"),
       ("diyarbakır", "kayapınar"),
       ("diyarbakır", "yenişehir"),
       ("diyarbakır", "ergani"),
       ("diyarbakır", "sur"),
       ("diyarbakır", "bismil"),
       ("diyarbakır", "silvan"),
       ("diyarbakır", "çınar"),
       ("diyarbakır", "çermik"),
       ("diyarbakır", "dicle"),
       ("diyarbakır", "kulp"),
       ("diyarbakır", "hani"),
       ("diyarbakır", "lice"),
       ("diyarbakır", "eğil"),
       ("diyarbakır", "hazro"),
       ("diyarbakır", "kocaköy"),
       ("diyarbakır", "çüngüş"),
       ("edirne", "merkez"),
       ("edirne", "keşan"),
       ("edirne", "uzunköprü"),
       ("edirne", "ipsala"),
       ("edirne", "havsa"),
       ("edirne", "meriç"),
       ("edirne", "enez"),
       ("edirne", "süloğlu"),
       ("edirne", "lalapaşa"),
       ("elazığ", "merkez"),
       ("elazığ", "kovancılar"),
       ("elazığ", "karakoçan"),
       ("elazığ", "palu"),
       ("elazığ", "arıcak"),
       ("elazığ", "baskil"),
       ("elazığ", "maden"),
       ("elazığ", "sivrice"),
       ("elazığ", "alacakaya"),
       ("elazığ", "keban"),
       ("elazığ", "ağın"),
       ("erzincan", "merkez"),
       ("erzincan", "tercan"),
       ("erzincan", "üzümlü"),
       ("erzincan", "çayırlı"),
       ("erzincan", "iliç"),
       ("erzincan", "kemah"),
       ("erzincan", "kemaliye"),
       ("erzincan", "otlukbeli"),
       ("erzurum", "yakutiye"),
       ("erzurum", "palandöken"),
       ("erzurum", "aziziye"),
       ("erzurum", "horasan"),
       ("erzurum", "oltu"),
       ("erzurum", "pasinler"),
       ("erzurum", "karayazı"),
       ("erzurum", "hınıs"),
       ("erzurum", "tekman"),
       ("erzurum", "karaçoban"),
       ("erzurum", "aşkale"),
       ("erzurum", "şenkaya"),
       ("erzurum", "çat"),
       ("erzurum", "köprüköy"),
       ("erzurum", "ispir"),
       ("erzurum", "tortum"),
       ("erzurum", "narman"),
       ("erzurum", "uzundere"),
       ("erzurum", "olur"),
       ("erzurum", "pazaryolu"),
       ("eskişehir", "odunpazarı"),
       ("eskişehir", "tepebaşı"),
       ("eskişehir", "sivrihisar"),
       ("eskişehir", "çifteler"),
       ("eskişehir", "seyitgazi"),
       ("eskişehir", "alpu"),
       ("eskişehir", "mihalıççık"),
       ("eskişehir", "mahmudiye"),
       ("eskişehir", "beylikova"),
       ("eskişehir", "inönü"),
       ("eskişehir", "sarıcakaya"),
       ("eskişehir", "günyüzü"),
       ("eskişehir", "mihalgazi"),
       ("eskişehir", "han"),
       ("gaziantep", "şahinbey"),
       ("gaziantep", "şehitkamil"),
       ("gaziantep", "nizip"),
       ("gaziantep", "islahiye"),
       ("gaziantep", "nurdağı"),
       ("gaziantep", "araban"),
       ("gaziantep", "oğuzeli"),
       ("gaziantep", "yavuzeli"),
       ("gaziantep", "karkamış"),
       ("giresun", "merkez"),
       ("giresun", "bulancak"),
       ("giresun", "espiye"),
       ("giresun", "görele"),
       ("giresun", "tirebolu"),
       ("giresun", "dereli"),
       ("giresun", "şebinkarahisar"),
       ("giresun", "keşap"),
       ("giresun", "yağlıdere"),
       ("giresun", "alucra"),
       ("giresun", "piraziz"),
       ("giresun", "eynesil"),
       ("giresun", "güce"),
       ("giresun", "çanakçı"),
       ("giresun", "doğankent"),
       ("giresun", "çamoluk"),
       ("gümüşhane", "merkez"),
       ("gümüşhane", "kelkit"),
       ("gümüşhane", "şiran"),
       ("gümüşhane", "kürtün"),
       ("gümüşhane", "torul"),
       ("gümüşhane", "köse"),
       ("hakkari", "yüksekova"),
       ("hakkari", "merkez"),
       ("hakkari", "çukurca"),
       ("hakkari", "şemdinli"),
       ("hatay", "antakya"),
       ("hatay", "iskenderun"),
       ("hatay", "defne"),
       ("hatay", "dörtyol"),
       ("hatay", "samandağ"),
       ("hatay", "kırıkhan"),
       ("hatay", "reyhanlı"),
       ("hatay", "arsuz"),
       ("hatay", "altınözü"),
       ("hatay", "hassa"),
       ("hatay", "erzin"),
       ("hatay", "payas"),
       ("hatay", "belen"),
       ("hatay", "yayladağı"),
       ("hatay", "kumlu"),
       ("ısparta", "merkez"),
       ("ısparta", "yalvaç"),
       ("ısparta", "eğirdir"),
       ("ısparta", "şarkikaraağaç"),
       ("ısparta", "gelendost"),
       ("ısparta", "keçiborlu"),
       ("ısparta", "senirkent"),
       ("ısparta", "sütçüler"),
       ("ısparta", "gönen"),
       ("ısparta", "uluborlu"),
       ("ısparta", "atabey"),
       ("ısparta", "aksu"),
       ("ısparta", "yenişarbademli"),
       ("mersin", "tarsus"),
       ("mersin", "toroslar"),
       ("mersin", "akdeniz"),
       ("mersin", "yenişehir"),
       ("mersin", "mezitli"),
       ("mersin", "erdemli"),
       ("mersin", "silifke"),
       ("mersin", "anamur"),
       ("mersin", "mut"),
       ("mersin", "bozyazı"),
       ("mersin", "gülnar"),
       ("mersin", "aydıncık"),
       ("mersin", "çamlıyayla"),
       ("istanbul", "esenyurt"),
       ("istanbul", "küçükçekmece"),
       ("istanbul", "bağcılar"),
       ("istanbul", "ümraniye"),
       ("istanbul", "pendik"),
       ("istanbul", "bahçelievler"),
       ("istanbul", "üsküdar"),
       ("istanbul", "sultangazi"),
       ("istanbul", "gaziosmanpaşa"),
       ("istanbul", "maltepe"),
       ("istanbul", "kartal"),
       ("istanbul", "esenler"),
       ("istanbul", "kadıköy"),
       ("istanbul", "kağıthane"),
       ("istanbul", "avcılar"),
       ("istanbul", "ataşehir"),
       ("istanbul", "fatih"),
       ("istanbul", "eyüp"),
       ("istanbul", "sancaktepe"),
       ("istanbul", "başakşehir"),
       ("istanbul", "sarıyer"),
       ("istanbul", "sultanbeyli"),
       ("istanbul", "güngören"),
       ("istanbul", "beylikdüzü"),
       ("istanbul", "zeytinburnu"),
       ("istanbul", "bayrampaşa"),
       ("istanbul", "şişli"),
       ("istanbul", "beykoz"),
       ("istanbul", "arnavutköy"),
       ("istanbul", "tuzla"),
       ("istanbul", "çekmeköy"),
       ("istanbul", "beyoğlu"),
       ("istanbul", "büyükçekmece"),
       ("istanbul", "bakırköy"),
       ("istanbul", "beşiktaş"),
       ("istanbul", "silivri"),
       ("istanbul", "çatalca"),
       ("istanbul", "şile"),
       ("istanbul", "adalar"),
       ("izmir", "buca"),
       ("izmir", "karabağlar"),
       ("izmir", "bornova"),
       ("izmir", "konak"),
       ("izmir", "karşıyaka"),
       ("izmir", "bayraklı"),
       ("izmir", "çiğli"),
       ("izmir", "torbalı"),
       ("izmir", "menemen"),
       ("izmir", "gaziemir"),
       ("izmir", "ödemiş"),
       ("izmir", "kemalpaşa"),
       ("izmir", "bergama"),
       ("izmir", "aliağa"),
       ("izmir", "menderes"),
       ("izmir", "tire"),
       ("izmir", "balçova"),
       ("izmir", "narlıdere"),
       ("izmir", "urla"),
       ("izmir", "kiraz"),
       ("izmir", "dikili"),
       ("izmir", "çeşme"),
       ("izmir", "bayındır"),
       ("izmir", "seferihisar"),
       ("izmir", "selçuk"),
       ("izmir", "güzelbahçe"),
       ("izmir", "foça"),
       ("izmir", "kınık"),
       ("izmir", "beydağ"),
       ("izmir", "karaburun"),
       ("kars", "merkez"),
       ("kars", "kağızman"),
       ("kars", "sarıkamış"),
       ("kars", "selim"),
       ("kars", "digor"),
       ("kars", "arpaçay"),
       ("kars", "akyaka"),
       ("kars", "susuz"),
       ("kastamonu", "merkez"),
       ("kastamonu", "tosya"),
       ("kastamonu", "taşköprü"),
       ("kastamonu", "cide"),
       ("kastamonu", "inebolu"),
       ("kastamonu", "araç"),
       ("kastamonu", "devrekani"),
       ("kastamonu", "bozkurt"),
       ("kastamonu", "daday"),
       ("kastamonu", "azdavay"),
       ("kastamonu", "çatalzeytin"),
       ("kastamonu", "küre"),
       ("kastamonu", "doğanyurt"),
       ("kastamonu", "ihsangazi"),
       ("kastamonu", "pınarbaşı"),
       ("kastamonu", "şenpazar"),
       ("kastamonu", "abana"),
       ("kastamonu", "seydiler"),
       ("kastamonu", "hanönü"),
       ("kastamonu", "ağlı"),
       ("kayseri", "melikgazi"),
       ("kayseri", "kocasinan"),
       ("kayseri", "talas"),
       ("kayseri", "develi"),
       ("kayseri", "yahyalı"),
       ("kayseri", "bünyan"),
       ("kayseri", "incesu"),
       ("kayseri", "pınarbaşı"),
       ("kayseri", "tomarza"),
       ("kayseri", "yeşilhisar"),
       ("kayseri", "sarıoğlan"),
       ("kayseri", "hacılar"),
       ("kayseri", "sarız"),
       ("kayseri", "akkışla"),
       ("kayseri", "felahiye"),
       ("kayseri", "özvatan"),
       ("kırklareli", "lüleburgaz"),
       ("kırklareli", "merkez"),
       ("kırklareli", "babaeski"),
       ("kırklareli", "vize"),
       ("kırklareli", "pınarhisar"),
       ("kırklareli", "demirköy"),
       ("kırklareli", "pehlivanköy"),
       ("kırklareli", "kofçaz"),
       ("kırşehir", "merkez"),
       ("kırşehir", "kaman"),
       ("kırşehir", "mucur"),
       ("kırşehir", "çiçekdağı"),
       ("kırşehir", "akpınar"),
       ("kırşehir", "boztepe"),
       ("kırşehir", "akçakent"),
       ("kocaeli", "gebze"),
       ("kocaeli", "izmit"),
       ("kocaeli", "darıca"),
       ("kocaeli", "körfez"),
       ("kocaeli", "gölcük"),
       ("kocaeli", "derince"),
       ("kocaeli", "çayırova"),
       ("kocaeli", "kartepe"),
       ("kocaeli", "başiskele"),
       ("kocaeli", "karamürsel"),
       ("kocaeli", "kandıra"),
       ("kocaeli", "dilovası"),
       ("konya", "selçuklu"),
       ("konya", "meram"),
       ("konya", "karatay"),
       ("konya", "ereğli"),
       ("konya", "akşehir"),
       ("konya", "beyşehir"),
       ("konya", "çumra"),
       ("konya", "seydişehir"),
       ("konya", "ılgın"),
       ("konya", "cihanbeyli"),
       ("konya", "kulu"),
       ("konya", "karapınar"),
       ("konya", "kadınhanı"),
       ("konya", "sarayönü"),
       ("konya", "bozkır"),
       ("konya", "yunak"),
       ("konya", "doğanhisar"),
       ("konya", "hüyük"),
       ("konya", "altınekin"),
       ("konya", "hadim"),
       ("konya", "çeltik"),
       ("konya", "emirgazi"),
       ("konya", "tuzlukçu"),
       ("konya", "derebucak"),
       ("konya", "akören"),
       ("konya", "halkapınar"),
       ("konya", "yalıhüyük"),
       ("kütahya", "merkez"),
       ("kütahya", "tavşanlı"),
       ("kütahya", "simav"),
       ("kütahya", "gediz"),
       ("kütahya", "emet"),
       ("kütahya", "altıntaş"),
       ("kütahya", "domaniç"),
       ("kütahya", "hisarcık"),
       ("kütahya", "aslanapa"),
       ("kütahya", "çavdarhisar"),
       ("kütahya", "şaphane"),
       ("kütahya", "pazarlar"),
       ("kütahya", "dumlupınar"),
       ("malatya", "battalgazi"),
       ("malatya", "yeşilyurt"),
       ("malatya", "doğanşehir"),
       ("malatya", "akçadağ"),
       ("malatya", "darende"),
       ("malatya", "hekimhan"),
       ("malatya", "pütürge"),
       ("malatya", "yazıhan"),
       ("malatya", "arapgir"),
       ("malatya", "arguvan"),
       ("malatya", "kuluncak"),
       ("malatya", "kale"),
       ("malatya", "doğanyol"),
       ("manisa", "yunusemre"),
       ("manisa", "şehzadeler"),
       ("manisa", "akhisar"),
       ("manisa", "salihli"),
       ("manisa", "turgutlu"),
       ("manisa", "soma"),
       ("manisa", "alaşehir"),
       ("manisa", "saruhanlı"),
       ("manisa", "kula"),
       ("manisa", "kırkağaç"),
       ("manisa", "demirci"),
       ("manisa", "sarıgöl"),
       ("manisa", "gördes"),
       ("manisa", "selendi"),
       ("manisa", "ahmetli"),
       ("manisa", "gölmarmara"),
       ("manisa", "köprübaşı"),
       ("kahramanmaraş", "onikişubat"),
       ("kahramanmaraş", "dulkadiroğlu"),
       ("kahramanmaraş", "elbistan"),
       ("kahramanmaraş", "afşin"),
       ("kahramanmaraş", "türkoğlu"),
       ("kahramanmaraş", "pazarcık"),
       ("kahramanmaraş", "göksun"),
       ("kahramanmaraş", "andırın"),
       ("kahramanmaraş", "çağlayancerit"),
       ("kahramanmaraş", "nurhak"),
       ("kahramanmaraş", "ekinözü"),
       ("mardin", "kızıltepe"),
       ("mardin", "artuklu"),
       ("mardin", "midyat"),
       ("mardin", "nusaybin"),
       ("mardin", "derik"),
       ("mardin", "mazıdağı"),
       ("mardin", "dargeçit"),
       ("mardin", "savur"),
       ("mardin", "yeşilli"),
       ("mardin", "ömerli"),
       ("muğla", "bodrum"),
       ("muğla", "fethiye"),
       ("muğla", "milas"),
       ("muğla", "menteşe"),
       ("muğla", "marmaris"),
       ("muğla", "seydikemer"),
       ("muğla", "ortaca"),
       ("muğla", "yatağan"),
       ("muğla", "dalaman"),
       ("muğla", "köyceğiz"),
       ("muğla", "ula"),
       ("muğla", "datça"),
       ("muğla", "kavaklıdere"),
       ("muş", "merkez"),
       ("muş", "bulanık"),
       ("muş", "malazgirt"),
       ("muş", "varto"),
       ("muş", "hasköy"),
       ("muş", "korkut"),
       ("nevşehir", "merkez"),
       ("nevşehir", "ürgüp"),
       ("nevşehir", "avanos"),
       ("nevşehir", "gülşehir"),
       ("nevşehir", "derinkuyu"),
       ("nevşehir", "acıgöl"),
       ("nevşehir", "kozaklı"),
       ("nevşehir", "hacıbektaş"),
       ("niğde", "merkez"),
       ("niğde", "bor"),
       ("niğde", "çiftlik"),
       ("niğde", "ulukışla"),
       ("niğde", "altunhisar"),
       ("niğde", "çamardı"),
       ("ordu", "altınordu"),
       ("ordu", "ünye"),
       ("ordu", "fatsa"),
       ("ordu", "gölköy"),
       ("ordu", "perşembe"),
       ("ordu", "kumru"),
       ("ordu", "aybastı"),
       ("ordu", "korgan"),
       ("ordu", "akkuş"),
       ("ordu", "ulubey"),
       ("ordu", "mesudiye"),
       ("ordu", "ikizce"),
       ("ordu", "gürgentepe"),
       ("ordu", "çatalpınar"),
       ("ordu", "çaybaşı"),
       ("ordu", "kabataş"),
       ("ordu", "çamaş"),
       ("ordu", "gülyalı"),
       ("rize", "merkez"),
       ("rize", "çayeli"),
       ("rize", "ardeşen"),
       ("rize", "pazar"),
       ("rize", "fındıklı"),
       ("rize", "güneysu"),
       ("rize", "kalkandere"),
       ("rize", "iyidere"),
       ("rize", "derepazarı"),
       ("rize", "çamlıhemşin"),
       ("rize", "ikizdere"),
       ("rize", "hemşin"),
       ("sakarya", "adapazarı"),
       ("sakarya", "serdivan"),
       ("sakarya", "akyazı"),
       ("sakarya", "erenler"),
       ("sakarya", "hendek"),
       ("sakarya", "karasu"),
       ("sakarya", "geyve"),
       ("sakarya", "arifiye"),
       ("sakarya", "sapanca"),
       ("sakarya", "pamukova"),
       ("sakarya", "ferizli"),
       ("sakarya", "kaynarca"),
       ("sakarya", "kocaali"),
       ("sakarya", "karapürçek"),
       ("sakarya", "taraklı"),
       ("samsun", "ilkadım"),
       ("samsun", "atakum"),
       ("samsun", "bafra"),
       ("samsun", "çarşamba"),
       ("samsun", "canik"),
       ("samsun", "vezirköprü"),
       ("samsun", "terme"),
       ("samsun", "tekkeköy"),
       ("samsun", "havza"),
       ("samsun", "alaçam"),
       ("samsun", "19 mayıs"),
       ("samsun", "ayvacık"),
       ("samsun", "kavak"),
       ("samsun", "salıpazarı"),
       ("samsun", "asarcık"),
       ("samsun", "ladik"),
       ("samsun", "yakakent"),
       ("siirt", "merkez"),
       ("siirt", "kurtalan"),
       ("siirt", "pervari"),
       ("siirt", "baykan"),
       ("siirt", "şirvan"),
       ("siirt", "eruh"),
       ("siirt", "tillo"),
       ("sinop", "merkez"),
       ("sinop", "boyabat"),
       ("sinop", "gerze"),
       ("sinop", "ayancık"),
       ("sinop", "durağan"),
       ("sinop", "türkeli"),
       ("sinop", "erfelek"),
       ("sinop", "dikmen"),
       ("sinop", "saraydüzü"),
       ("sivas", "merkez"),
       ("sivas", "şarkışla"),
       ("sivas", "yıldızeli"),
       ("sivas", "suşehri"),
       ("sivas", "gemerek"),
       ("sivas", "zara"),
       ("sivas", "kangal"),
       ("sivas", "gürün"),
       ("sivas", "divriği"),
       ("sivas", "koyulhisar"),
       ("sivas", "altınyayla"),
       ("sivas", "hafik"),
       ("sivas", "ulaş"),
       ("sivas", "imranlı"),
       ("sivas", "akıncılar"),
       ("sivas", "gülova"),
       ("sivas", "doğanşar"),
       ("tekirdağ", "çorlu"),
       ("tekirdağ", "süleymanpaşa"),
       ("tekirdağ", "çerkezköy"),
       ("tekirdağ", "kapaklı"),
       ("tekirdağ", "ergene"),
       ("tekirdağ", "malkara"),
       ("tekirdağ", "saray"),
       ("tekirdağ", "hayrabolu"),
       ("tekirdağ", "şarköy"),
       ("tekirdağ", "muratlı"),
       ("tekirdağ", "marmaraereğlisi"),
       ("tokat", "merkez"),
       ("tokat", "erbaa"),
       ("tokat", "turhal"),
       ("tokat", "niksar"),
       ("tokat", "zile"),
       ("tokat", "reşadiye"),
       ("tokat", "almus"),
       ("tokat", "pazar"),
       ("tokat", "başçiftlik"),
       ("tokat", "yeşilyurt"),
       ("tokat", "artova"),
       ("tokat", "sulusaray"),
       ("trabzon", "ortahisar"),
       ("trabzon", "akçaabat"),
       ("trabzon", "araklı"),
       ("trabzon", "of"),
       ("trabzon", "yomra"),
       ("trabzon", "arsin"),
       ("trabzon", "vakfıkebir"),
       ("trabzon", "sürmene"),
       ("trabzon", "maçka"),
       ("trabzon", "beşikdüzü"),
       ("trabzon", "çarşıbaşı"),
       ("trabzon", "tonya"),
       ("trabzon", "düzköy"),
       ("trabzon", "çaykara"),
       ("trabzon", "şalpazarı"),
       ("trabzon", "hayrat"),
       ("trabzon", "köprübaşı"),
       ("trabzon", "dernekpazarı"),
       ("tunceli", "merkez"),
       ("tunceli", "pertek"),
       ("tunceli", "mazgirt"),
       ("tunceli", "çemişgezek"),
       ("tunceli", "hozat"),
       ("tunceli", "ovacık"),
       ("tunceli", "pülümür"),
       ("tunceli", "nazımiye"),
       ("şanlıurfa", "eyyübiye"),
       ("şanlıurfa", "haliliye"),
       ("şanlıurfa", "siverek"),
       ("şanlıurfa", "viranşehir"),
       ("şanlıurfa", "karaköprü"),
       ("şanlıurfa", "akçakale"),
       ("şanlıurfa", "suruç"),
       ("şanlıurfa", "birecik"),
       ("şanlıurfa", "ceylanpınar"),
       ("şanlıurfa", "harran"),
       ("şanlıurfa", "bozova"),
       ("şanlıurfa", "hilvan"),
       ("şanlıurfa", "halfeti"),
       ("uşak", "merkez"),
       ("uşak", "banaz"),
       ("uşak", "eşme"),
       ("uşak", "sivaslı"),
       ("uşak", "ulubey"),
       ("uşak", "karahallı"),
       ("van", "ipekyolu"),
       ("van", "erciş"),
       ("van", "tuşba"),
       ("van", "edremit"),
       ("van", "özalp"),
       ("van", "çaldıran"),
       ("van", "başkale"),
       ("van", "muradiye"),
       ("van", "gürpınar"),
       ("van", "gevaş"),
       ("van", "saray"),
       ("van", "çatak"),
       ("van", "bahçesaray"),
       ("yozgat", "merkez"),
       ("yozgat", "sorgun"),
       ("yozgat", "akdağmadeni"),
       ("yozgat", "yerköy"),
       ("yozgat", "sarıkaya"),
       ("yozgat", "boğazlıyan"),
       ("yozgat", "çekerek"),
       ("yozgat", "şefaatli"),
       ("yozgat", "saraykent"),
       ("yozgat", "çayıralan"),
       ("yozgat", "kadışehri"),
       ("yozgat", "aydıncık"),
       ("yozgat", "yenifakıllı"),
       ("yozgat", "çandır"),
       ("zonguldak", "ereğli"),
       ("zonguldak", "merkez"),
       ("zonguldak", "çaycuma"),
       ("zonguldak", "devrek"),
       ("zonguldak", "kozlu"),
       ("zonguldak", "alaplı"),
       ("zonguldak", "kilimli"),
       ("zonguldak", "gökçebey"),
       ("aksaray", "merkez"),
       ("aksaray", "ortaköy"),
       ("aksaray", "eskil"),
       ("aksaray", "gülağaç"),
       ("aksaray", "güzelyurt"),
       ("aksaray", "ağaçören"),
       ("aksaray", "sarıyahşi"),
       ("bayburt", "merkez"),
       ("bayburt", "demirözü"),
       ("bayburt", "aydıntepe"),
       ("karaman", "merkez"),
       ("karaman", "ermenek"),
       ("karaman", "sarıevliler"),
       ("karaman", "ayrancı"),
       ("karaman", "kazımkarabekir"),
       ("karaman", "başyayla"),
       ("kırıkkale", "merkez"),
       ("kırıkkale", "yahşihan"),
       ("kırıkkale", "keskin"),
       ("kırıkkale", "delice"),
       ("kırıkkale", "bahşili"),
       ("kırıkkale", "sulakyurt"),
       ("kırıkkale", "balışeyh"),
       ("kırıkkale", "karakeçili"),
       ("kırıkkale", "çelebi"),
       ("batman", "merkez"),
       ("batman", "kozluk"),
       ("batman", "beşiri"),
       ("batman", "sason"),
       ("batman", "gerçüş"),
       ("batman", "hasankeyf"),
       ("şırnak", "cizre"),
       ("şırnak", "silopi"),
       ("şırnak", "merkez"),
       ("şırnak", "idil"),
       ("şırnak", "uludere"),
       ("şırnak", "beytüşşebap"),
       ("şırnak", "güçlükonak"),
       ("bartın", "merkez"),
       ("bartın", "ulus"),
       ("bartın", "amasra"),
       ("bartın", "kurucaşile"),
       ("ardahan", "merkez"),
       ("ardahan", "göle"),
       ("ardahan", "çıldır"),
       ("ardahan", "hanak"),
       ("ardahan", "posof"),
       ("ardahan", "damal"),
       ("ığdır", "merkez"),
       ("ığdır", "tuzluca"),
       ("ığdır", "aralık"),
       ("ığdır", "karakoyunlu"),
       ("yalova", "merkez"),
       ("yalova", "çiftlikköy"),
       ("yalova", "çınarcık"),
       ("yalova", "altınova"),
       ("yalova", "armutlu"),
       ("yalova", "termal"),
       ("karabük", "merkez"),
       ("karabük", "safranbolu"),
       ("karabük", "yenice"),
       ("karabük", "eskipazar"),
       ("karabük", "eflani"),
       ("karabük", "ovacık"),
       ("kilis", "merkez"),
       ("kilis", "musabeyli"),
       ("kilis", "elbeyli"),
       ("kilis", "polateli"),
       ("osmaniye", "merkez"),
       ("osmaniye", "kadirli"),
       ("osmaniye", "düziçi"),
       ("osmaniye", "bahçe"),
       ("osmaniye", "toprakkale"),
       ("osmaniye", "sumbas"),
       ("osmaniye", "hasanbeyli"),
       ("düzce", "merkez"),
       ("düzce", "akçakoca"),
       ("düzce", "kaynaşlı"),
       ("düzce", "gölyaka"),
       ("düzce", "çilimli"),
       ("düzce", "yığılca"),
       ("düzce", "gümüşova"),
       ("düzce", "cumayeri") $$
SET FOREIGN_KEY_CHECKS = 1 $$

DROP TRIGGER IF EXISTS cs353.on_manager_delete $$
CREATE TRIGGER on_manager_delete
    AFTER UPDATE
    ON restaurant_manager
    FOR EACH ROW
BEGIN
    IF NEW.deleted = 1 THEN
        UPDATE restaurant R SET R.deleted = 1 WHERE R.manager_username = NEW.username;
    END IF;
END;
$$

DROP TRIGGER IF EXISTS on_restaurant_delete $$
CREATE TRIGGER on_restaurant_delete
    AFTER UPDATE
    ON restaurant
    FOR EACH ROW
BEGIN
    IF NEW.deleted = 1 THEN
        UPDATE meal M SET M.deleted = 1 WHERE M.rid = NEW.rid;
        DELETE W FROM works_with W WHERE W.restaurant_id = NEW.rid;
        DELETE D FROM discount D WHERE D.rid = NEW.rid;
        DELETE S FROM serves S WHERE S.rid = NEW.rid;
        DELETE T FROM restaurant_tags T WHERE T.rid = NEW.rid;
    END IF;
END;
$$
