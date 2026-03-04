<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/42882e2a-2add-4622-84fd-2b0070ac4586" />
```md
# Dokumentasi Testing API - Praktikum 1 (User Management)

Dokumen ini berisi panduan lengkap untuk melakukan pengujian (testing) API Manajemen User. API ini dibangun menggunakan Spring Boot dan mematuhi prinsip-prinsip arsitektur RESTful.

## Base URL
Semua *endpoint* dalam dokumentasi ini menggunakan *base URL* berikut:
`http://localhost:8080/api/users`

*(Catatan: Sesuaikan port `8080` jika Anda mengonfigurasi port server yang berbeda di `application.properties`)*

---

## 1. Menambahkan User Baru (Create)

Menyimpan data pengguna baru ke dalam *database*. Sistem akan menghasilkan ID secara otomatis (UUID).

* **Endpoint:** `/api/users`
* **Method:** `POST`
* **Content-Type:** `application/json`

**Request Body:**
```json
{
  "name": "Budi Santoso",
  "age": 25
}

```

**Responses:**

* **201 Created (Success)**
```json
{
  "status": "success",
  "data": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Budi Santoso",
    "age": 25
  }
}

```


* **400 Bad Request**
Terjadi jika *request body* kosong, format JSON salah, atau gagal melewati validasi.

---

## 2. Mengambil Semua Data User (Read All)

Mengembalikan daftar semua pengguna yang ada di dalam sistem.

* **Endpoint:** `/api/users`
* **Method:** `GET`
* **Content-Type:** `application/json` (Response)

**Request Body:** `Kosong`

**Responses:**

* **200 OK (Success)**
```json
{
  "status": "success",
  "data": [
    {
      "id": "123e4567-e89b-12d3-a456-426614174000",
      "name": "Budi Santoso",
      "age": 25
    },
    {
      "id": "987f6543-a21c-45d6-b789-123456789012",
      "name": "Siti Aminah",
      "age": 22
    }
  ]
}

```


*(Akan mengembalikan array `data` kosong `[]` jika belum ada user).*

---

## 3. Mengambil Data User Berdasarkan ID (Read One)

Mencari dan mengembalikan data satu pengguna secara spesifik berdasarkan ID.

* **Endpoint:** `/api/users/{id}`
* **Method:** `GET`
* **Content-Type:** `application/json` (Response)

**Path Variable:**

* `id`: ID unik pengguna (UUID format string).

**Request Body:** `Kosong`

**Responses:**

* **200 OK (Success)**
```json
{
  "status": "success",
  "data": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Budi Santoso",
    "age": 25
  }
}

```


* **500 Internal Server Error (Not Found - *Catatan: Error handling default Spring Boot*)**
Terjadi jika ID tidak ditemukan di *database* karena pelemparan `RuntimeException("user not found")` di layer *Service*.

---

## 4. Memperbarui Data User (Update)

Memperbarui informasi `name` dan `age` dari pengguna yang sudah ada berdasarkan ID.

* **Endpoint:** `/api/users/{id}`
* **Method:** `PUT`
* **Content-Type:** `application/json`

**Path Variable:**

* `id`: ID unik pengguna yang ingin diubah.

**Request Body:**

```json
{
  "name": "Budi Santoso Update",
  "age": 26
}

```

**Responses:**

* **200 OK (Success)**
```json
{
  "status": "success",
  "data": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Budi Santoso Update",
    "age": 26
  }
}

```


* **500 Internal Server Error (Not Found)**
Terjadi jika ID yang ingin di-*update* tidak ditemukan.

---

## 5. Menghapus Data User (Delete)

Menghapus data pengguna dari *database* secara permanen.

* **Endpoint:** `/api/users/{id}`
* **Method:** `DELETE`
* **Content-Type:** `application/json` (Response)

**Path Variable:**

* `id`: ID unik pengguna yang ingin dihapus.

**Request Body:** `Kosong`

**Responses:**

* **200 OK (Success)**
```json
{
  "status": "success delete user with id 123e4567-e89b-12d3-a456-426614174000"
}

```


* **500 Internal Server Error (Not Found)**
Terjadi jika ID yang ingin dihapus tidak ditemukan di *database*.

```
   



