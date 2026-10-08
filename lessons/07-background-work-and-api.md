# บทที่ 07 — งานเบื้องหลังและการเรียก API

> **สถานะ: โครงของบทเรียน** ยังไม่มีบทสอนเต็มและยังไม่มีโค้ดตัวอย่าง ใช้สอนไม่ได้จนกว่าจะเขียนเพิ่ม

- **ทำไมต้องเรียน:** งานส่วนใหญ่ของแอปคือต่อ API เข้ากับหน้าจอ ถ้าไม่มีบทนี้ MVVM จะเป็นแนวคิดลอย ๆ
- **ต้องรู้ก่อน:** บทที่ 05, บทที่ 06
- **เรียนจบแล้วไปที่:** workshop Session 3 — โหลดข้อมูลผ่าน UseCase และ Repository (ดู [learning.md](../learning.md))

## เรียนจบแล้วทำอะไรได้

- อธิบายได้ว่าทำไมห้ามทำงานช้าบน main thread
- ไล่เส้นทางของข้อมูลจาก API จนถึงหน้าจอได้ทุกชั้น

## เนื้อหา

- main thread และอาการหน้าจอค้าง
- coroutine ระดับใช้งาน: `launch`, `Dispatchers.IO`, `Dispatchers.Main`, `suspend`
- HTTP และ JSON พอสังเขป
- Retrofit: interface, `@GET` / `@POST`, `@Body`, `Call<T>`
- Gson และ `@SerializedName`, ทำไม field ควรเป็น nullable
- OkHttp interceptor: log และการ mock คำตอบ
- การแปลง exception และ HTTP error เป็นค่าผลลัพธ์ (`Result`)
- ชั้น Repository และ UseCase: แต่ละชั้นรับผิดชอบอะไร
- permission `INTERNET` และการดู request ใน Logcat

## ตัวอย่างที่จะใช้สอน

หน้าจอที่เรียก API หนึ่งเส้นผ่าน mock interceptor โดยเขียนทุกชั้นในไฟล์เดียวก่อน แล้วจึงแยกชั้น
