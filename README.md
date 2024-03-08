# enoca
# Enoca Java Challenge Projesi

## Proje Amaçları

- Müşterilerin çevrimiçi alışveriş yaptıkları bir platformun temel işlevselliğini içerir.
- Müşterilerin hesap oluşturmasına, ürünleri görüntülemesine, sepete eklemesine ve sipariş vermesine olanak tanır.
- Temel CRUD (Create, Read, Update, Delete) işlemlerini içeren bir RESTful API sunar.

## Teknolojiler

- Java 17
- Spring Boot 3.2.3 Maven
- Spring Data JPA
- MySQL veritabanı (yerel geliştirme için)
- Docker (MySQL ayağa kaldırmak için)
## Proje Yapısı

- **entity**: Veritabanı tablolarını temsil eden JPA entity sınıfları.
- **dto**: Data Transfer Object (DTO) sınıfları.
- **repository**: Spring Data JPA repository arabirimleri.
- **service**: İş mantığı işlemlerinin gerçekleştirildiği servis sınıfları.
- **controller**: RESTful API endpoint'lerini yöneten controller sınıfları.
- **exception**: Özel istisna (exception) sınıfları.


## Kurulum

1. Projeyi bilgisayarınıza klonlayın: `git clone https://github.com/AydinSinan/enoca.git`
2. Proje dizinine gidin: `cd enoca-java-challenge`
3. Maven bağımlılıklarını yükleyin: `mvn install`
4. Uygulamayı başlatın: `mvn spring-boot:run`
5. Tarayıcınızda Swagger UI'ı görüntülemek için: `http://localhost:8080/swagger-ui.html`

## API Endpoint'leri

- Müşteriler:
  - `GET /api/customers`: Tüm müşterileri al
  - `GET /api/customers/{id}`: Belirli bir müşteriyi al
  - `POST /api/customers`: Yeni bir müşteri oluştur
  - `PUT /api/customers/{id}`: Var olan bir müşteriyi güncelle
  - `DELETE /api/customers/{id}`: Bir müşteriyi sil

- Ürünler:
  - `GET /api/products`: Tüm ürünleri al
  - `GET /api/products/{id}`: Belirli bir ürünü al
  - `POST /api/products`: Yeni bir ürün oluştur
  - `PUT /api/products/{id}`: Var olan bir ürünü güncelle
  - `DELETE /api/products/{id}`: Bir ürünü sil

- Sepet:
  - `POST /api/carts/create?customerId=?`: Belirli bir müşteri için sepet oluştur
  - `GET /api/carts/{customerId}`: Belirli bir müşterinin sepetini al
  - `PUT /api/carts/{customerId}`: Belirli bir müşterinin sepetini güncelle
  - `POST /api/carts/{customerId}/addProduct`: Belirli bir müşterinin sepetine ürün ekle
  - `DELETE /api/carts/{customerId}/removeProduct`: Belirli bir müşterinin sepetinden ürün çıkar
  - `POST /api/carts/{customerId}/placeOrder`: Belirli bir müşterinin siparişi ver

- Sipariş
  - `GET /api/orders/{orderId}`: Belirli bir siparişi görüntüle
  - `POST /api/orders/place`: Yeni bir sipariş oluştur
  





