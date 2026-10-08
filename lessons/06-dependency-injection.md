# บทที่ 06 — Dependency injection

> **สถานะ: โครงของบทเรียน** ยังไม่มีบทสอนเต็มและยังไม่มีโค้ดตัวอย่าง ใช้สอนไม่ได้จนกว่าจะเขียนเพิ่ม

- **ทำไมต้องเรียน:** แอปเด้งด้วย error ของ Koin เป็นสิ่งที่คนใหม่เจอในสัปดาห์แรก
- **ต้องรู้ก่อน:** บทที่ 04
- **เรียนจบแล้วไปที่:** workshop Session 2 — ViewModel, LiveData และ Koin (ดู [learning.md](../learning.md))

## เรียนจบแล้วทำอะไรได้

- อธิบายได้ว่า DI แก้ปัญหาอะไร
- ลงทะเบียนและขอ dependency ด้วย Koin ได้
- อ่าน error ของ Koin แล้วรู้ว่าต้องแก้ที่ใด

## เนื้อหา

- dependency คืออะไร และปัญหาของการสร้างของเองข้างใน class
- ส่งเข้าทาง constructor ด้วยมือก่อน แล้วจึงเห็นว่าทำไมต้องมีเครื่องมือ
- Koin: `module { }`, `single`, `factory`, `viewModel`, `get()`
- การขอของ: `by viewModel()`, `by inject()`
- ผูก interface กับตัวจริง: `single<Interface> { Impl() }`
- `startKoin` และการโหลด module ทีหลังด้วย `loadKoinModules`
- error ที่เจอบ่อย: `No definition found`, `Could not create instance`
- DI กับการเขียน test: ส่งของปลอมเข้าไปแทน

## ตัวอย่างที่จะใช้สอน

ViewModel จากบทที่ 04 ที่รับ dependency หนึ่งตัว เริ่มจากส่งด้วยมือแล้วเปลี่ยนเป็น Koin
