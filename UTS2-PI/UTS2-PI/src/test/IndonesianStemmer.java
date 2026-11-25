package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Stemmer Bahasa Indonesia murni Java berbasis algoritma Nazief-Adriani
 * (Enhanced Confix Stripping) dengan kamus kata dasar bawaan dan
 * fallback aturan morfologi untuk kata yang belum terdaftar di kamus.
 */
public final class IndonesianStemmer {

    private static final Set<String> ROOT_WORDS;

    static {
        Set<String> roots = new HashSet<String>(Arrays.asList(
            // Kata dasar umum & leksikon berita/korpus agar tidak terjadi over-stemming
            "abdullah", "acara", "adu", "agama", "ajar", "akibat", "aksi", "aku", "alam",
            "alamat", "alas", "alasan", "alat", "alih", "alun", "aman", "amat",
            "ambil", "amunisi", "aneka", "anggar", "anggota", "angkat", "angkut",
            "anjak", "antar", "anti", "antisipasi", "aparat", "api", "apresiasi",
            "arah", "arena", "arti", "artis", "arus", "asal", "asap", "asyik",
            "atap", "atas", "atur", "awal", "awan", "awas", "ayam",
            "baca", "bagai", "bagan", "bagi", "bahan", "bahas", "bahaya", "bahu",
            "baik", "baju", "bakar", "bakso", "bakti", "balik", "band", "banding",
            "bangun", "banjir", "bantu", "banyak", "barang", "barat", "baris",
            "baru", "basah", "batas", "bawa", "bawah", "bayar", "beban", "bebas",
            "beda", "bedah", "begadang", "bekal", "bekas", "beken", "bekerja",
            "belanja", "belas", "beli", "belok", "benar", "bencana", "benda",
            "benderang", "bensin", "bentuk", "bentrok", "berani", "berapa",
            "beras", "berat", "beri", "beringin", "berita", "berkat", "bersih",
            "besar", "besi", "besok", "betul", "biaya", "biasa", "bicara",
            "bidang", "bijak", "biji", "bilang", "bina", "bintang", "bisa",
            "bisnis", "blok", "bola", "boleh", "bom", "bondong", "botol",
            "buah", "buang", "buat", "bubakan", "bubar", "budaya", "buka", "bukti", "buku",
            "bulan", "bulu", "bumbu", "bumi", "bundar", "bunga", "bungkus",
            "bunuh", "bunyi", "bupati", "buru", "buruk", "bus", "butuh",
            "cabut", "cahaya", "camat", "campur", "capai", "capek", "cari",
            "catat", "cegah", "cepat", "cerah", "cerita", "cetak", "cipta",
            "cita", "coba", "cocok", "copet", "cuaca", "cuci", "cukup", "curi",
            "curiga",
            "dadak", "daerah", "dagang", "dahsyat", "dalam", "damai", "dampak",
            "damping", "dana", "dangdut", "dapat", "dapur", "darat", "darurat",
            "dasar", "datang", "daya", "dekat", "dengar", "depan", "derita",
            "desember", "desing", "detik", "dewan", "diam", "diameter", "didik",
            "dinas", "dini", "diponegoro", "direktur", "diri", "distributor", "doa", "dobrak", "dorong", "dua", "duduk",
            "duet", "duga", "duka", "dukung", "dunia", "dusta",
            "ekor", "empat", "enak", "enam", "enggan", "entah", "erat", "es",
            "faktor", "fakta", "farmasi", "favorit", "fisik", "fokus", "foto",
            "gagal", "gajah", "gaji", "gali", "gambar", "ganggu", "ganti",
            "gantung", "garis", "gas", "geber", "gedung", "gegana", "gelar",
            "geleng", "gemar", "gembira", "gempa", "genap", "gerai", "gerak",
            "gereja", "getar", "gigi", "giliran", "gizi", "golong", "goyang",
            "granat", "gratis", "grup", "gubernur", "gudang", "gugur", "gulung",
            "guna", "guncang", "gunung", "guru", "guyur",
            "habis", "hadap", "hadir", "hajar", "hakim", "hal", "halaman",
            "halau", "halus", "hambat", "hambur", "hampir", "hancur", "hangat",
            "hantam", "hapus", "harap", "harga", "hari", "harta", "harum", "hasbullah",
            "hasil", "hati", "hebat", "hemat", "hendak", "henti", "heran",
            "hewan", "hias", "hibur", "hidup", "hijau", "hilang", "himbau",
            "hina", "hindar", "hirau", "hitung", "hormat", "hotel", "hubung",
            "hujan", "hukum", "huni", "hutan",
            "ibu", "iklan", "ikut", "ilmu", "iman", "imbang", "impian", "indah",
            "indonesia", "info", "informasi", "ingat", "ingin", "injak", "intan",
            "intelijen", "inti", "iring", "iseng", "isi", "istirahat", "izin",
            "jabat", "jadi", "jaga", "jago", "jahat", "jajan", "jajar", "jakarta",
            "jalan", "jam", "jamin", "jangkau", "janji", "jantung", "januari",
            "jarak", "jaring", "jatuh", "jauh", "jawab", "jaya", "jejal",
            "jelang", "jelas", "jemput", "jenazah", "jenderal", "jenis", "jilat",
            "jinak", "jiwa", "jodoh", "joget", "jual", "juang", "jubel",
            "judul", "juga", "jumlah", "jumpa", "junjung", "juru", "juta",
            "kabar", "kabupaten", "kaca", "kacau", "kadang", "kaget", "kagum",
            "kain", "kakak", "kaki", "kaku", "kalah", "kalau", "kaleng", "kali",
            "kalimat", "kamar", "kambing", "kampung", "kanan", "kandang",
            "kandidat", "kandung", "kantor", "kantong", "kapal", "kapan", "kapas",
            "kapten", "karang", "karet", "karya", "karyawan", "kasar", "kasih",
            "kasus", "kata", "kawan", "kawat", "kawasan", "kawula", "kaya",
            "kayu", "kejar", "kelahi", "kelam", "kelapa", "kelas", "kelenteng",
            "keliling", "kelompok", "keluar", "keluarga", "kemarin", "kembang",
            "kembali", "kemas", "kemudi", "kemuka", "kena", "kenal", "kenang", "kencang",
            "kendali", "kendara", "kenyang", "kepala", "keping", "kepung", "kerah",
            "keras", "kereta", "kering", "kerja", "kertas", "kerumun", "kesan",
            "kesal", "ketat", "ketua", "khusus", "kian", "kirim", "kiri",
            "klakson", "knalpot", "kobar", "kolam", "koma", "komando", "kompak",
            "komplain", "kondisi", "konser", "kontrak", "konvoi", "koordinasi",
            "koper", "koran", "korban", "kota", "kotor", "kreatif", "kritis",
            "kristen", "kuat", "kubu", "kucing", "kuda", "kue", "kuintal",
            "kukuh", "kulit", "kumpul", "kunci", "kunjung", "kuno", "kupas",
            "kurang", "kursi", "kurung", "kusut",
            "label", "labuh", "lacak", "ladang", "lagu", "lahan", "lahir",
            "lain", "laju", "laki", "laksana", "laku", "lalu", "lama", "lambat", "lampau",
            "lampu", "lancar", "landa", "langgar", "langit", "langkah",
            "langsung", "lanjut", "lantai", "lantang", "lantar", "lantas",
            "lantun", "lapang", "lapar", "lapor", "larang", "lari", "laris",
            "larut", "latih", "laut", "lawan", "layak", "layan", "layar",
            "lebar", "lebaran", "lebih", "ledak", "lega", "leher", "lelah",
            "leluasa", "lemah", "lemak", "lembaga", "lembar", "lembut", "lempar",
            "lengan", "lengang", "lengkap", "lepas", "lesat", "lestari",
            "letak", "lewat", "libat", "libur", "licin", "lidah", "lihat",
            "lima", "limpah", "lindung", "lingkar", "lingkung", "lintas",
            "lipat", "liput", "lirik", "listrik", "lobi", "logo", "lokasi",
            "lolos", "lomba", "lompat", "lonjak", "lontar", "losmen", "luang",
            "luar", "luas", "lubang", "luber", "lucu", "luhur", "luka",
            "lukis", "lulus", "lumayan", "lumpuh", "lunak", "luncur", "lupa",
            "luput", "lurus", "luwes",
            "macam", "macet", "mahal", "main", "maju", "makam", "makan",
            "makbul", "makin", "makna", "maksimal", "maksud", "malam", "malang",
            "malas", "malu", "mampir", "mampu", "mana", "manajemen", "mancur",
            "mandi", "mandiri", "manfaat", "mangga", "manis", "manja", "mantap",
            "manusia", "marah", "margasatwa", "mari", "marina", "markas", "masa",
            "masak", "masalah", "masjid", "massa", "massal", "masuk",
            "masyarakat", "mata", "matahari", "matang", "materi", "mati",
            "matras", "maut", "meja", "mekar", "melati", "merah", "mercon",
            "merdeka", "merdu", "meriah", "mesin", "meski", "meteor", "meter",
            "metro", "mewah", "milik", "mimpi", "minggu", "mini", "minta",
            "minum", "minyak", "mirip", "misal", "miskin", "mobil", "modal",
            "modern", "mohon", "monas", "motivasi", "motor", "muat", "muda", "mudah",
            "muka", "mulai", "mulia", "mulus", "mulut", "muncul", "mundur",
            "mungkin", "murah", "murid", "murni", "musibah", "musik", "musim",
            "musnah", "musuh", "mutu",
            "naas", "nafas", "naik", "nakal", "nama", "nampak", "nanggap", "nanti",
            "narkoba", "nasi", "nasib", "nasional", "natal", "negara", "negeri",
            "nenas", "nekat", "niat", "nilai", "nikmat", "nisbi", "nobat",
            "nongkrong", "normal", "nyala", "nyali", "nyaman", "nyanyi",
            "nyaris", "nyata", "nyawa", "nyonya",
            "obat", "objek", "obral", "olah", "oleh", "ombak", "omong",
            "operasi", "optimal", "orang", "organ", "organisasi", "otak", "otot",
            "pabrik", "pacu", "pada", "padam", "padat", "padu", "pagar", "pagi",
            "paham", "pahlawan", "pajak", "pak", "pakai", "paket", "paksa", "paling",
            "palsu", "palu", "paman", "pamer", "panas", "pancang", "pancing",
            "pandang", "pandu", "panggil", "panggung", "pangkal", "panik",
            "panitia", "panjang", "panjat", "pantai", "pantau", "pantas",
            "pantun", "papan", "parah", "parasit", "parkir", "partai",
            "partisipasi", "pas", "pasang", "pasar", "pasien", "pasir", "pasti",
            "pasuk", "patah", "patok", "patroli", "patuh", "patung", "payah",
            "payung", "pecah", "pedas", "peduli", "pegang", "pegawai", "pekan",
            "pekik", "pelan", "pelat", "peleton", "pelita", "peluang", "peluk",
            "peluru", "penat", "pendar", "pendapa", "pendek", "penjara",
            "penjuru", "pensiun", "pentas", "penting", "penuh", "perah", "perak",
            "peran", "perang", "percaya", "percik", "perdana", "perempuan",
            "pergi", "periksa", "perintah", "peristiwa", "perkara", "perkasa",
            "perlu", "permata", "pernah", "persen", "persis", "pertama", "perut",
            "perwira", "pesan", "pesat", "pesawat", "peserta", "pesta", "peta",
            "petang", "petani", "petasan", "peti", "petik", "petir", "picu",
            "pidana", "pihak", "pijak", "pijar", "pikir", "pikul", "pilih",
            "pimpin", "pinang", "pindah", "pinggir", "pinjam", "pinta", "pintar",
            "pintu", "pisah", "pisau", "plastik", "plaza", "pohon", "poin",
            "pokok", "pola", "polda", "polisi", "polres", "polri", "polsek",
            "poltabes", "pompa", "pondok", "pop", "populer", "posisi", "poso",
            "potong", "pramuka", "prestasi", "pria", "pribadi", "prioritas",
            "produk", "profesional", "program", "proyek", "proses", "protokol",
            "puas", "publik", "pucat", "pucuk", "puisi", "puja", "puji",
            "pukul", "pulang", "pulau", "pulih", "puluh", "punah", "puncak",
            "pungut", "punya", "pupuk", "pura", "puri", "pusat", "pusing",
            "pustaka", "putar", "putih", "putra", "putri", "putus",
            "rabu", "racun", "radio", "raga", "ragu", "rahasia", "raih", "raja",
            "rajin", "rakit", "rakyat", "ramah", "ramai", "rambat", "rampas",
            "rampok", "rancak", "rancang", "rangkai", "rangkul", "rantai",
            "rapat", "rapi", "rasa", "rata", "ratu", "ratus", "rawan", "rawat",
            "raya", "rebut", "reda", "rekan", "rekor", "rekreasi", "rela",
            "relatif", "remaja", "remuk", "renang", "rencana", "rendah",
            "renovasi", "rentan", "rental", "resah", "resep", "resmi", "respon",
            "restu", "retak", "rezeki", "ria", "riang", "ribu", "ribut", "rimba",
            "ringan", "ringkas", "rintang", "rintik", "risau", "riuh", "riwayat",
            "roboh", "roda", "rokok", "rombong", "rontok", "ruang", "ruas",
            "rugi", "rukun", "rumah", "rumit", "rumput", "rumus", "runcing",
            "runding", "runyam", "rupa", "rupiah", "rusak", "rusuh", "rute",
            "saat", "sabar", "sabtu", "sabun", "sadar", "sah", "sahabat",
            "sahut", "saing", "saji", "sakit", "saksi", "saku", "salah",
            "salam", "salatiga", "salin", "salip", "salur", "sama", "sambar",
            "sambut", "sampah", "sampai", "samping", "samudra", "sana", "sandang",
            "sandar", "sanding", "sangat", "sanggup", "sangka", "sangsi",
            "santai", "santap", "santun", "sapu", "saran", "sarana", "sarang",
            "sarat", "saring", "sarjana", "sasaran", "satgas", "satlantas",
            "satria", "satu", "saudara", "sawah", "sayang", "sayap", "sayur",
            "sebab", "sebar", "sebelah", "sebelas", "seberang", "sebut", "sedang",
            "sederhana", "sedih", "sedikit", "sedia", "segar", "segera", "sehat",
            "sejak", "sejarah", "sejuk", "sekarang", "sekat", "sekian", "sekejap",
            "sekolah", "sekutu", "sela", "selalu", "selam", "selamat", "selang",
            "selasa", "selatan", "seleksi", "selendang", "selepas", "selera",
            "selesai", "selidik", "selimut", "selisih", "selokan", "seluruh",
            "semak", "semalam", "semangat", "semarak", "semarang", "sembah",
            "sembarang", "sembelih", "sembilan", "sembuh", "sembunyi", "sembur",
            "semen", "sementara", "sempat", "sempit", "semprot", "sempurna",
            "semua", "semula", "semut", "senam", "senang", "senapan", "sendi",
            "sendiri", "sendok", "sengaja", "sengit", "sengsara", "seni",
            "senin", "senja", "senjata", "sentosa", "sentuh", "sepak", "sepatu",
            "sepeda", "seperti", "sepi", "sepuh", "sepuluh", "seputar",
            "seragam", "serah", "serambi", "serang", "serangga", "serap",
            "serat", "serba", "serbu", "serdadu", "serempak", "serentak",
            "seret", "sergap", "seri", "serikat", "sering", "serius", "serong",
            "serpih", "serta", "seru", "seruling", "serupa", "sesak", "sesal",
            "sesat", "sesuai", "setan", "setia", "setiap", "setir", "setuju",
            "sewa", "siang", "siap", "siaga", "sibuk", "sidang", "sifat",
            "sigap", "sihir", "sikap", "siksa", "siku", "silang", "silau",
            "simpang", "simpan", "simpul", "sinar", "singgah", "singkat",
            "singkir", "singgung", "sini", "sipil", "siram", "sirna", "sisa",
            "sisi", "sistem", "sita", "situ", "situasi", "skala", "soal",
            "sobek", "sodok", "sokong", "solid", "solo", "solusi", "sombong",
            "sopan", "sorai", "sorak", "sore", "sorot", "sosok", "spektakuler",
            "spontan", "stabil", "staf", "stasiun", "status", "steril",
            "sterilisasi", "strategi", "studio", "suaka", "suami", "suara",
            "suasana", "subuh", "subur", "suci", "sudut", "suguh", "suhu",
            "suka", "sukses", "sulit", "sulut", "sumber", "sumbang", "sumpah",
            "sumpal", "sumur", "sunat", "sungai", "sungguh", "suntuk", "sunyi",
            "supaya", "super", "supir", "surat", "surut", "susah", "susu",
            "susul", "susun", "susut", "syarat", "syukur",
            "taat", "tabah", "tabrak", "tabung", "tabur", "tadah", "tadi",
            "tagih", "tahan", "tahap", "tahu", "tahun", "tajam", "takar",
            "taksir", "takut", "talang", "tali", "tamak", "taman", "tamat",
            "tambah", "tambal", "tambang", "tampak", "tampan", "tampar",
            "tampil", "tampung", "tamu", "tanah", "tanam", "tanda", "tandas",
            "tanding", "tangan", "tangga", "tanggap", "tangguh", "tanggung",
            "tangis", "tangkai", "tangkap", "tangkis", "tanjung", "tanpa",
            "tanya", "tapa", "taraf", "tari", "tarik", "taruh", "tarung",
            "tata", "tatap", "tato", "tawa", "tawan", "tawar", "teater",
            "tebak", "tebal", "tebang", "tebar", "tebas", "tebing", "tebus",
            "teduh", "tegah", "tegak", "tegang", "tegas", "teguh", "teguk",
            "tegur", "teknik", "tekun", "teladan", "telah", "telan", "telanjang",
            "telat", "telepon", "televisi", "telinga", "teliti", "teluk",
            "telur", "telusur", "teman", "tembak", "tembaga", "tembang",
            "tembus", "tempa", "tempat", "tempel", "tempo", "tempuh", "tempur",
            "temu", "tenaga", "tenang", "tenda", "tendang", "tengah",
            "tenggelam", "tengok", "tentang", "tentara", "tenteram", "tentu",
            "tenun", "tepat", "tepi", "tepuk", "tepung", "terang", "teras",
            "teratai", "terbang", "terbit", "teriak", "terigu", "terima",
            "terjal", "terjang", "terjun", "terka", "terkam", "ternak",
            "terobos", "terompet", "terong", "teropong", "teror", "terpa",
            "terpal", "tertawa", "tertib", "terung", "terus", "tes", "tetap",
            "tetangga", "tewas", "tiang", "tiap", "tiba", "tidak", "tidur",
            "tiga", "tikam", "tiket", "tikung", "tikus", "tilang", "tim",
            "timba", "timbang", "timbul", "timbun", "timer", "timpa", "timur",
            "tindak", "tindas", "tindih", "tinggal", "tinggi", "tingkah",
            "tingkat", "tinjau", "tinju", "tipis", "tipu", "tirakat", "tiru",
            "titik", "titip", "tiup", "toko", "tokoh", "tolak", "tolong",
            "tomat", "tombak", "tongkat", "tonton", "top", "topang", "topeng",
            "topi", "total", "tradisi", "tragedi", "transaksi", "transparan",
            "truk", "tua", "tuang", "tubuh", "tuduh", "tugas", "tugu", "tuhan",
            "tujuh", "tuju", "tukar", "tulang", "tular", "tulis", "tulus",
            "tumbang", "tumbuh", "tumbuk", "tumit", "tumpah", "tumpang",
            "tumpas", "tumpeng", "tumplek", "tumpuk", "tumpu", "tumpul",
            "tunai", "tunas", "tunda", "tunduk", "tunggal", "tunggu", "tungku",
            "tunjuk", "tuntun", "tuntut", "tupai", "turun", "turut", "tusuk",
            "tutup", "tutur",
            "uang", "uap", "ubah", "uban", "ubi", "ucap", "udara", "uji",
            "ujung", "ukir", "ukur", "ulah", "ulang", "ular", "ulas", "ulet",
            "ulur", "umat", "umbul", "umpan", "umum", "umur", "undang",
            "undur", "unggas", "unggul", "ungkap", "ungkit", "ungu", "unik",
            "unit", "unjuk", "unsur", "untai", "untuk", "untung", "upacara",
            "upah", "upaya", "urai", "urang", "urat", "urus", "urut", "usah",
            "usaha", "usai", "usap", "usia", "usik", "usir", "usul", "usung",
            "usus", "usut", "utama", "utang", "utara", "utuh", "utus",
            "variasi", "wabah", "wadah", "wajah", "wajar", "wajib", "wakil",
            "waktu", "walau", "wali", "waluh", "wangi", "wanita", "warga",
            "warna", "warni", "warta", "warung", "wasit", "waspada", "watak",
            "wawancara", "wibawa", "wilayah", "wisata", "wisuda", "wujud",
            "yakin", "yatim", "zaman", "ziarah", "zigzag"
        ));
        ROOT_WORDS = Collections.unmodifiableSet(roots);
    }

    /**
     * Konstruktor privat karena seluruh method bersifat statis.
     */
    private IndonesianStemmer() {
    }

    /**
     * Mengembalikan kata dasar dari sebuah token Bahasa Indonesia.
     * Alur utama: cek pengecualian/kamus kata dasar -> hapus akhiran infleksi ->
     * jalankan Nazief-Adriani ({@link #stemCandidate}) -> fallback aturan konservatif.
     *
     * @param word token huruf kecil yang akan di-stem
     * @return kata dasar hasil stemming
     */
    public static String stem(String word) {
        // Kata pendek (<=3 huruf), angka murni, atau yang sudah ada di kamus langsung dikembalikan
        if (word == null || word.length() <= 3 || isNumeric(word) || ROOT_WORDS.contains(word)) {
            return word;
        }

        String original = word;

        // 0. Pengecualian morfologi umum (mis. ke-tahu-i menyalahi larangan ke-...-i standar)
        if ("diketahui".equals(original) || "ketahui".equals(original) || "mengetahui".equals(original)) {
            return "tahu";
        }
        if ("mengenaskan".equals(original)) {
            return "naas";
        }

        // 1. Coba hapus partikel (-lah, -kah, -tah, -pun) & kata ganti milik (-ku, -mu, -nya)
        String withoutInflection = stripInflectionalSuffixes(original);
        if (ROOT_WORDS.contains(withoutInflection)) {
            return withoutInflection;
        }

        // 2. Jalankan Nazief-Adriani pada kata tanpa akhiran infleksi, lalu fallback ke bentuk asli
        String result = stemCandidate(withoutInflection);
        if (result != null) {
            return result;
        }
        if (!withoutInflection.equals(original)) {
            result = stemCandidate(original);
            if (result != null) {
                return result;
            }
        }

        // 3. Fallback berbasis aturan konservatif bila kata belum terdaftar di ROOT_WORDS
        return fallbackRuleBasedStem(withoutInflection);
    }

    /**
     * Mencoba menemukan kata dasar di {@code ROOT_WORDS} dengan dua strategi:
     * (A) menghapus awalan terlebih dahulu (mis. "berjalan" -> "jalan"),
     * dan (B) menghapus akhiran derivasional lalu menghapus awalan (mis. "perayaan" -> "raya").
     *
     * @param word kandidat kata yang akan dicari akarnya
     * @return kata dasar jika ditemukan di {@code ROOT_WORDS}, atau {@code null} jika tidak cocok
     */
    private static String stemCandidate(String word) {
        if (ROOT_WORDS.contains(word)) {
            return word;
        }

        // A. Coba hapus awalan saja terlebih dahulu (mencegah kata seperti "berjalan" terpotong "-an")
        String prefixOnly = stripPrefixesWithDict(word, "");
        if (prefixOnly != null) {
            return prefixOnly;
        }

        // B. Coba hapus akhiran derivasional (-kan, -i, -an), cek kamus atau lanjut hapus awalan
        String[][] suffixVariants = extractDerivationalSuffixes(word);
        for (String[] variant : suffixVariants) {
            String stem = variant[0];
            String suffix = variant[1];
            if (stem.length() < 3) {
                continue;
            }
            if (ROOT_WORDS.contains(stem)) {
                return stem;
            }
            // Teruskan info suffix agar kombinasi awalan+akhiran terlarang dapat ditolak
            String prefixed = stripPrefixesWithDict(stem, suffix);
            if (prefixed != null) {
                return prefixed;
            }
        }

        return null;
    }

    /**
     * Menghapus akhiran infleksional: partikel (-lah, -kah, -tah, -pun)
     * serta kata ganti posesif (-ku, -mu, -nya).
     *
     * @param word kata input
     * @return kata setelah penghapusan akhiran infleksional (jika memenuhi syarat panjang)
     */
    private static String stripInflectionalSuffixes(String word) {
        String current = word;
        // Hapus partikel jika panjang kata > 5 dan sisa kata masih memiliki minimal 1 huruf vokal
        if (current.length() > 5 && (current.endsWith("lah") || current.endsWith("kah")
                || current.endsWith("tah") || current.endsWith("pun"))) {
            String candidate = current.substring(0, current.length() - 3);
            if (hasMinimumVowels(candidate, 1)) {
                current = candidate;
            }
        }
        if (ROOT_WORDS.contains(current)) {
            return current;
        }
        // Hapus kata ganti posesif -nya / -ku / -mu
        if (current.length() > 5 && current.endsWith("nya")) {
            String candidate = current.substring(0, current.length() - 3);
            if (hasMinimumVowels(candidate, 1)) {
                current = candidate;
            }
        } else if (current.length() > 4 && (current.endsWith("ku") || current.endsWith("mu"))) {
            // Hati-hati terhadap kata dasar berakhiran -ku/-mu seperti "saku", "tamu", "ilmu"
            String candidate = current.substring(0, current.length() - 2);
            if (ROOT_WORDS.contains(candidate)) {
                current = candidate;
            }
        }
        return current;
    }

    /**
     * Menghasilkan kandidat pemotongan akhiran derivasional (-kan, -an, -i).
     * Untuk akhiran "-kan", disediakan dua varian: potong "-kan" dan potong "-an"
     * (untuk kasus akar kata berakhiran huruf 'k' seperti "ledakan" -> "ledak" + "an").
     *
     * @param word kata input
     * @return array pasangan {sisaKata, namaSuffix}
     */
    private static String[][] extractDerivationalSuffixes(String word) {
        if (word.endsWith("kan") && word.length() > 5) {
            return new String[][] {
                { word.substring(0, word.length() - 3), "kan" },
                { word.substring(0, word.length() - 2), "an" }
            };
        }
        if (word.endsWith("an") && word.length() > 4) {
            return new String[][] {
                { word.substring(0, word.length() - 2), "an" }
            };
        }
        if (word.endsWith("i") && word.length() > 4) {
            return new String[][] {
                { word.substring(0, word.length() - 1), "i" }
            };
        }
        return new String[0][0];
    }

    /**
     * Titik masuk penghapusan awalan berbasis kamus kata dasar.
     */
    private static String stripPrefixesWithDict(String word, String suffix) {
        return stripPrefixesWithDictRecursive(word, suffix, 0);
    }

    /**
     * Menghapus awalan secara rekursif hingga kedalaman maksimal 3 lapis
     * (mis. "di-per-boleh-kan" atau "mem-per-tahan-kan").
     *
     * @param word sisa kata saat ini
     * @param suffix akhiran derivasional yang sebelumnya dipotong (atau "" jika tidak ada)
     * @param depth kedalaman rekursi awalan (0..2)
     * @return kata dasar jika ditemukan di {@code ROOT_WORDS}, atau {@code null}
     */
    private static String stripPrefixesWithDictRecursive(String word, String suffix, int depth) {
        if (depth >= 3 || word.length() < 4) {
            return null;
        }

        // Uji setiap kandidat pemotongan awalan beserta peluluhan konsonannya
        for (String[] candidate : generatePrefixCandidates(word, suffix)) {
            String stem = candidate[0];
            if (stem.length() < 3) {
                continue;
            }
            if (ROOT_WORDS.contains(stem)) {
                return stem;
            }
            // Coba hapus awalan lapis kedua/ketiga (mis. "di-per-", "mem-per-", "ber-ke-")
            String deeper = stripPrefixesWithDictRecursive(stem, suffix, depth + 1);
            if (deeper != null) {
                return deeper;
            }
        }

        return null;
    }

    /**
     * Menghasilkan kandidat pemotongan awalan (di-, ke-, se-, be-/ber-/bel-, te-/ter-,
     * meN-, peN-/per-/pel-) sekaligus memvalidasi aturan kombinasi awalan-akhiran terlarang
     * dan melakukan recoding huruf awal yang luluh (k, s, t, p).
     *
     * @param word kata yang akan dicek awalannya
     * @param suffix akhiran derivasional yang sudah dipotong sebelumnya
     * @return array pasangan {kandidatStem, tipePrefix}
     */
    private static String[][] generatePrefixCandidates(String word, String suffix) {
        // Daftar kandidat [stem, prefixType] sesuai aturan morfologi Nazief-Adriani
        java.util.List<String[]> list = new java.util.ArrayList<String[]>();

        // di- (tidak boleh dengan -an)
        if (word.startsWith("di") && !"an".equals(suffix)) {
            list.add(new String[] { word.substring(2), "di" });
        }
        // ke- (tidak boleh dengan -i atau -kan)
        if (word.startsWith("ke") && !"i".equals(suffix) && !"kan".equals(suffix)) {
            list.add(new String[] { word.substring(2), "ke" });
        }
        // se- (tidak boleh dengan -i atau -kan)
        if (word.startsWith("se") && !"i".equals(suffix) && !"kan".equals(suffix)) {
            list.add(new String[] { word.substring(2), "se" });
        }
        // be- / ber- / bel- (tidak boleh dengan -i)
        if (!"i".equals(suffix)) {
            if (word.startsWith("ber")) {
                list.add(new String[] { word.substring(3), "be" });
                list.add(new String[] { word.substring(2), "be" }); // untuk "ber-" + "r..." (mis. berekreasi -> rekreasi)
            } else if (word.startsWith("bel")) {
                list.add(new String[] { word.substring(3), "be" }); // belajar -> ajar
            } else if (word.startsWith("be")) {
                list.add(new String[] { word.substring(2), "be" }); // bekerja -> kerja
            }
        }
        // te- / ter- (tidak boleh dengan -an)
        if (!"an".equals(suffix)) {
            if (word.startsWith("ter")) {
                list.add(new String[] { word.substring(3), "te" });
                list.add(new String[] { word.substring(2), "te" }); // terasa -> rasa
            } else if (word.startsWith("te")) {
                list.add(new String[] { word.substring(2), "te" });
            }
        }
        // me- (tidak boleh dengan -an); mencakup alomorf meng-, meny-, men-, mem-, dan me-
        if (!"an".equals(suffix) && word.startsWith("me")) {
            if (word.startsWith("meng")) {
                list.add(new String[] { word.substring(4), "me" });
                list.add(new String[] { "k" + word.substring(4), "me" }); // recoding 'k' yang luluh (mengirim -> kirim)
            }
            if (word.startsWith("meny")) {
                list.add(new String[] { "s" + word.substring(4), "me" }); // recoding 's' yang luluh (menyambut -> sambut)
                list.add(new String[] { word.substring(2), "me" }); // menyalakan -> nyala, menyanyi -> nyanyi
            }
            if (word.startsWith("men")) {
                list.add(new String[] { word.substring(3), "me" });
                list.add(new String[] { "t" + word.substring(3), "me" }); // recoding 't' yang luluh (menari -> tari)
            }
            if (word.startsWith("mem")) {
                list.add(new String[] { word.substring(3), "me" });
                list.add(new String[] { "p" + word.substring(3), "me" }); // recoding 'p' yang luluh (memakai -> pakai)
                list.add(new String[] { word.substring(2), "me" }); // memasuki -> masuk, memiliki -> milik
            }
            list.add(new String[] { word.substring(2), "me" });
        }
        // pe- / per- / pel- (tidak boleh dengan -kan; peN-...-kan selalu peN- + akar berakhiran k + -an)
        if (word.startsWith("pe") && !"kan".equals(suffix)) {
            if (word.startsWith("peng")) {
                list.add(new String[] { word.substring(4), "pe" });
                list.add(new String[] { "k" + word.substring(4), "pe" }); // recoding 'k' (pengendara -> kendara)
            }
            if (word.startsWith("peny")) {
                list.add(new String[] { "s" + word.substring(4), "pe" }); // recoding 's' (penyambutan -> sambut)
                list.add(new String[] { word.substring(2), "pe" }); // penyanyi -> nyanyi
            }
            if (word.startsWith("pen")) {
                list.add(new String[] { word.substring(3), "pe" });
                list.add(new String[] { "t" + word.substring(3), "pe" }); // recoding 't' (penonton -> tonton)
            }
            if (word.startsWith("pem")) {
                list.add(new String[] { word.substring(3), "pe" });
                list.add(new String[] { "p" + word.substring(3), "pe" }); // recoding 'p' (pemadam -> padam)
                list.add(new String[] { word.substring(2), "pe" }); // pemilik -> milik
            }
            if (word.startsWith("per")) {
                list.add(new String[] { word.substring(3), "pe" });
            }
            if (word.startsWith("pel")) {
                list.add(new String[] { word.substring(3), "pe" }); // pelajar -> ajar
            }
            list.add(new String[] { word.substring(2), "pe" }); // pedagang -> dagang, pejalan -> jalan
        }

        return list.toArray(new String[0][0]);
    }

    /**
     * Fallback konservatif untuk kata yang tidak terdapat pada {@code ROOT_WORDS}
     * (mis. kata serapan atau kosakata baru saat query).
     * Hanya memotong imbuhan yang relatif tidak ambigu dan menjaga sisa kata tetap
     * memiliki minimal 3 karakter dan 2 huruf vokal.
     *
     * @param word kata yang belum cocok di kamus
     * @return hasil pemotongan konservatif atau kata asli jika tidak memenuhi syarat
     */
    private static String fallbackRuleBasedStem(String word) {
        String current = word;
        String suffix = "";

        if (current.endsWith("kan") && current.length() > 6) {
            String candidate = current.substring(0, current.length() - 3);
            if (hasMinimumVowels(candidate, 2)) {
                current = candidate;
                suffix = "kan";
            }
        }

        String candidate = null;
        if (current.startsWith("meng") && current.length() > 6 && !"an".equals(suffix)) {
            candidate = current.substring(4);
        } else if (current.startsWith("meny") && current.length() > 6 && !"an".equals(suffix)) {
            candidate = "s" + current.substring(4);
        } else if (current.startsWith("men") && current.length() > 5 && !"an".equals(suffix)) {
            candidate = current.substring(3);
        } else if (current.startsWith("mem") && current.length() > 5 && !"an".equals(suffix)) {
            candidate = current.substring(3);
        } else if (current.startsWith("ber") && current.length() > 5 && !"i".equals(suffix)) {
            candidate = current.substring(3);
        } else if (current.startsWith("ter") && current.length() > 5 && !"an".equals(suffix)) {
            candidate = current.substring(3);
        } else if (current.startsWith("di") && current.length() > 5 && !"an".equals(suffix)) {
            candidate = current.substring(2);
        }

        if (candidate != null && candidate.length() >= 3 && hasMinimumVowels(candidate, 2)) {
            return candidate;
        }
        return current;
    }

    /**
     * Memastikan kandidat kata dasar memiliki jumlah huruf vokal minimal tertentu
     * untuk mencegah pemotongan berlebih (over-stemming) menjadi fragmen tanpa suku kata.
     */
    private static boolean hasMinimumVowels(String word, int minVowels) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'i' || c == 'u' || c == 'e' || c == 'o') {
                count++;
                if (count >= minVowels) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Mengecek apakah seluruh karakter pada token berupa angka (digit).
     */
    private static boolean isNumeric(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Mengembalikan himpunan kamus kata dasar bawaan (read-only).
     *
     * @return Set kata dasar
     */
    public static Set<String> getRootWords() {
        return ROOT_WORDS;
    }
}
