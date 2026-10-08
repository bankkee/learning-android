# บทที่ 03 — Activity และ Life cycle

> **สถานะ: โครงของบทเรียน** ยังไม่มีบทสอนเต็มและยังไม่มีโค้ดตัวอย่าง ใช้สอนไม่ได้จนกว่าจะเขียนเพิ่ม

- **ทำไมต้องเรียน:** bug ที่มือใหม่เจอบ่อยที่สุดมาจากการไม่รู้ว่าหน้าจอถูกสร้างและทำลายเมื่อไร
- **ต้องรู้ก่อน:** บทที่ 02
- **เรียนจบแล้วไปที่:** workshop Session 1 — แอป Android ประกอบด้วยอะไร (ดู [learning.md](../learning.md))

## เรียนจบแล้วทำอะไรได้

- บอกลำดับ callback ของ Activity ได้ในสถานการณ์หลัก
- อธิบายได้ว่าทำไมหมุนจอแล้วข้อมูลหาย
- รู้ว่างานแต่ละอย่างควรอยู่ใน callback ใด

## เนื้อหา

- Activity คืออะไร และ `onCreate` ทำอะไร
- การกดและ listener
- ลำดับ `onCreate` → `onStart` → `onResume` → `onPause` → `onStop` → `onDestroy`
- configuration change: หมุนจอ, เปลี่ยนภาษา, โหมดมืด
- process ถูกระบบปิดขณะอยู่เบื้องหลัง และ `onSaveInstanceState`
- lifecycle ของ Fragment และ `viewLifecycleOwner` (ปูพื้นให้บทที่ 11)
- lifecycle-aware component: ทำไม LiveData ถึงไม่ทำให้แอปพังเมื่อหน้าจอปิดไปแล้ว
- memory leak จากการถือ Activity หรือ View ไว้นานเกินอายุ

## ตัวอย่างที่จะใช้สอน

หน้าจอที่เขียน log ทุก callback และมีตัวนับที่หายเมื่อหมุนจอ
