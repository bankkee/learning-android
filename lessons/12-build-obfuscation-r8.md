# บทที่ 12 — Build, obfuscation, R8

> **สถานะ: โครงของบทเรียน** ยังไม่มีบทสอนเต็มและยังไม่มีโค้ดตัวอย่าง ใช้สอนไม่ได้จนกว่าจะเขียนเพิ่ม

- **ที่มาของหัวข้อ:** senior: "build, obfuscation, R8"
- **ทำไมต้องเรียน:** แอปที่ทำงานได้ใน debug แต่พังใน release เป็นปัญหาที่หาสาเหตุยากถ้าไม่รู้ว่ามีขั้นตอนนี้
- **ต้องรู้ก่อน:** บทที่ 07
- **เรียนจบแล้วไปที่:** workshop Session 6 — Feature flag, unit test และทำ feature เองทั้งเส้น (ดู [learning.md](../learning.md))

## เรียนจบแล้วทำอะไรได้

- อธิบายได้ว่าจากโค้ดจนเป็นไฟล์ติดตั้งผ่านขั้นตอนใดบ้าง
- สร้าง release build ที่เปิด R8 และแก้ปัญหาที่เกิดจากมันได้

## เนื้อหา

- Gradle: project กับ module, `build.gradle`, plugin, dependency (`implementation` กับ `api`)
- การรวมเวอร์ชันไว้ที่เดียว (`buildSrc`)
- build type: `debug` กับ `release`
- product flavor และ build variant, `BuildConfig`, `resValue`
- APK กับ AAB, การ sign และ keystore
- R8 ทำสามอย่าง: ตัดโค้ดที่ไม่ใช้, เปลี่ยนชื่อ (obfuscation), ปรับให้เร็วขึ้น
- `minifyEnabled`, `shrinkResources`, ไฟล์ `proguard-rules.pro`
- keep rule: `-keep`, `-keepclassmembers`, และ `consumer-rules.pro` ของ library module
- อะไรพังเมื่อเปิด R8: reflection, Gson กับชื่อ field, ชื่อ class ที่อ้างเป็นข้อความ
- ไฟล์ `mapping.txt` และการแปลง stack trace กลับ (retrace)
- เครื่องมือ: APK Analyzer
- **ต้องยืนยันกับโปรเจกต์จริง**: การตั้งค่า R8 และขั้นตอน release ที่ทีมใช้

## ตัวอย่างที่จะใช้สอน

เปิด minify ใน release build ของแอปจากบทที่ 07 ให้เห็นว่า JSON แปลงไม่ได้ แล้วแก้
