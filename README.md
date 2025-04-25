# API Test Otomasyon Projesi

Bu repo, çeşitli web servisleri için gerçekleştirilmiş API test otomasyon örneklerini içermektedir. Proje, **Rest Assured** kütüphanesi kullanılarak geliştirilmiş olup, test senaryoları **TestNG** framework'ü ile yönetilmektedir.

Test sonuçlarının detaylı biçimde raporlanması için **Extent Reports** entegre edilmiş, bu raporlar PDF formatına dönüştürülerek saklanmıştır. Sürekli entegrasyon/teslimat (CI/CD) süreçleri için **GitHub Actions** yapılandırılmış, testlerin daha hızlı ve verimli çalışması için **paralel test özelliği** uygulanmıştır.

---

## İçerik

Bu proje içerisinde aşağıdaki API'ler için test senaryoları yer almaktadır:

- **jsonplaceholder.typicode.com**: Basit GET operasyonlarını ve kullanıcı verilerini test eden örnekler
- **reqres.in**: Kullanıcı bilgilerini getirme, oluşturma, güncelleme ve silme (CRUD) işlemleri
- **restcountries.com**: Türkçe konuşulan ülkelerin bölge ve başkent bilgileri doğrulama
- **fakerestapi.azurewebsites.net**: Aktivite verilerinin sayısını ve başlıklarını test etme

Her bir test senaryosu, ilgili API endpoint'lerine yapılan isteklerin yanıtlarını belirli kriterlere göre doğrulamaktadır:
- HTTP durum kodları
- İçerik tipi (Content-Type)
- JSON body içerikleri
- Yanıt yapısının bütünlüğü

---

## Kullanılan Teknolojiler

- **Java**: Projenin geliştirme dili
- **Rest Assured**: API istekleri ve yanıtlarını test etmek için kullanılan Java kütüphanesi
- **TestNG**: Test organizasyonu ve paralel çalıştırma için kullanılan güçlü framework
- **Extent Reports**: Test çıktılarının HTML rapor olarak görselleştirilmesini sağlar
- **openhtmltopdf + jsoup**: HTML raporların PDF formatına dönüştürülmesinde kullanılır
- **GitHub Actions**: CI/CD sürecinde testleri otomatikleştirir ve raporları yükler

