# ImageVideoMultipleUpload

Versi pertama dari library yang kuinginkan untuk dapat posting lebih dari satu file pada sekali request android

## Bagaimana memulai?

Entahlah, aku belum testing untuk user umum

### Cara install

Yang kau butuhkan adalah setting pada build.gradle

```
maven { url 'https://jitpack.io' }
```



# Guyon
YANG PALING PENTING ADALAH: PRELOADGILA.class
## Cara menggunakan
Mendapatkan instance dari ViewGroup paling atas. Master of ViewGroup yang memiliki panjang dan lebar MATCH_PARENT

Contoh:
```
LinearLayout llFrame=findViewById(R.id.mainLayout);
```
Lalu tinggal memanggil class PreloadGila.java dengan argumen berupa:
1. Context
2. Instance dari parent ViewGroup
3. Gambar drawable(opsional. Jika tidak, maka di isi 0)

```
PreloadGila preloadGila=new PreloadGila(this,llFrame,R.drawable.doraemon);
```

### Selesai.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

