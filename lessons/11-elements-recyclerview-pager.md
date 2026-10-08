# บทที่ 11 — Element: RecyclerView และ Pager

> **สถานะ: โครงของบทเรียน** ยังไม่มีบทสอนเต็มและยังไม่มีโค้ดตัวอย่าง ใช้สอนไม่ได้จนกว่าจะเขียนเพิ่ม

- **ทำไมต้องเรียน:** หน้าจอส่วนใหญ่ของแอปจริงเป็นรายการ หรือเป็นแท็บที่ปัดได้
- **ต้องรู้ก่อน:** บทที่ 04, บทที่ 08
- **เรียนจบแล้วไปที่:** workshop Session 5 — รายการด้วย RecyclerView (ดู [learning.md](../learning.md))

## เรียนจบแล้วทำอะไรได้

- แสดงข้อมูลเป็นรายการและจัดการการกดแต่ละแถวได้
- สร้างหน้าจอแบบแท็บที่ปัดได้

## เนื้อหา

- **RecyclerView**: ทำไมต้องนำ View กลับมาใช้ซ้ำ
- Adapter, ViewHolder, layout ของ item, `LayoutManager` (แนวตั้ง, แนวนอน, ตาราง)
- การอัปเดตข้อมูล: `notifyDataSetChanged` กับ `DiffUtil` / `ListAdapter`
- การกดแถว: ส่ง callback ผ่าน constructor
- item หลายชนิดในรายการเดียว (`getItemViewType`)
- bug จากการใช้ View ซ้ำ: ต้องตั้งค่าทุกอย่างทุกครั้ง
- สถานะรายการว่าง, เส้นคั่น, ระยะห่างระหว่างแถว
- **Fragment** เท่าที่ pager ต้องใช้: สร้าง, ส่ง argument, lifecycle ของ view
- **Pager**: `ViewPager2`, `FragmentStateAdapter`, `TabLayout` + `TabLayoutMediator`
- ViewModel ร่วมกันระหว่าง Activity กับ Fragment
- **ยังต้องตัดสินใจ**: จะสอน `ViewPager` รุ่นใด

## ตัวอย่างที่จะใช้สอน

รายการเมนูจาก list ในเครื่อง แล้วขยายเป็นหน้าจอสองแท็บที่แต่ละแท็บเป็นรายการ
