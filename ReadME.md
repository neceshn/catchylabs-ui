# Selenium BDD Otomasyon Projesi

Bu proje, Java, Selenium WebDriver ve Cucumber (BDD) kullanılarak oluşturulmuş otomasyon test senaryolarını içermektedir. Projede; kullanıcı login, hesap detayları, para transferi, hesap güncelleme gibi senaryolar yer almakta ve her biri için detaylı step definition’lar yazılmıştır.

## İçerik

- [Özellikler](#özellikler)
- [Teknolojiler](#teknolojiler)
- [Kurulum](#kurulum)
- [Çalıştırma](#çalıştırma)
    - [Parametre Bazlı Çalıştırma](#parametre-bazlı-çalıştırma)
- [Proje Yapısı](#proje-yapısı)
- [Katkıda Bulunma](#katkıda-bulunma)
- [Lisans](#lisans)

## Özellikler

- **BDD Yaklaşımı:** Senaryolar Gherkin dilinde yazılmıştır.
- **Mobil Emülasyon:** ChromeDriver mobil emülasyon modunda çalıştırılarak farklı cihazlarda test yapılabilmektedir.(IPhone X) ile uyumlu şekilde çalışmaktadır. Parametreye bağlanabilir.
- **Dinamik Test Adımları:** Bazı senaryolarda dinamik **DOM** erişimi uygulanmaktadır.
- **Çeşitli Senaryolar:** Login, para transferi (popup’lar), hesap oluşturma ve hesap güncelleme gibi senaryolar yer almaktadır.
- **Parametre Bazlı Çalıştırma:** Maven komut satırından test çalıştırılırken farklı tarayıcılar (Chrome, Firefox) veya mobil emülasyon gibi seçenekler parametre olarak gönderilebilmektedir.
  - **mvn clean test -Dbrowser=mobile** | **mvn clean test -Dbrowser=chrome** | **mvn clean test -Dbrowser=firefox**


## Teknolojiler

- **Java 21**
- **Selenium WebDriver**
- **Cucumber (BDD)**
- **JUnit**
- **Maven**
- **ChromeDriver**
- **ExtentReport**

## Kurulum

1. **Java Kurulumu:**  
   Bilgisayarınızda **Java JDK 21** yüklü olmalıdır.

2. **Maven Kurulumu:**  
   Intellij MVN template ile proje oluşturulmuştur. Local maven için gereklilikleri sağlamalısınız.

3. **ChromeDriver:**  
   WebDriverManager ile kullanılmıştır.

4. **Proje Bağımlılıkları:**  
   Proje klasöründe terminal veya komut satırında aşağıdaki komutu çalıştırarak gerekli bağımlılıkları indirebilirsiniz:
   ```bash
   mvn clean install
   mvn clean test -Dbrowser=mobile 
   mvn clean test -Dbrowser=chrome
   mvn clean test -Dbrowser=firefox
   ```