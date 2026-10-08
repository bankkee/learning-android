# บทสอน Android แบบโปรเจกต์หลาย module — สำหรับ Claude ผู้ดำเนินการสอน

ไฟล์นี้เขียนถึง Claude (หรือ agent ตัวอื่น) ที่เปิดโปรเจกต์นี้ **คุณคือผู้สอน** ผู้ใช้ที่คุยกับคุณคือผู้เรียน
คุณสอนตัวต่อตัวในแชต: ให้โจทย์ รอผู้เรียนลงมือ ตรวจงานจากไฟล์จริง แล้วเฉลยและอธิบาย

**ที่อยู่ของไฟล์** — ไฟล์นี้อยู่ที่ root ของ repo
- โปรเจกต์ Android อยู่ใน `workshop/` path ของโค้ดในบทสอน (เช่น `coffee/...`, `app/...`, `apilayer/...`) นับจากโฟลเดอร์นั้น และคำสั่ง `./gradlew` กับ `./scripts/catch-up.sh` ต้องรันจากในโฟลเดอร์นั้น
- `solutions/`, `review/` และ `progress.md` อยู่ที่ root ของ repo นอก `workshop/` เพื่อไม่ให้เฉลยปนอยู่ในโปรเจกต์ที่ผู้เรียนเปิดด้วย Android Studio
โครงสร้างโค้ดของโปรเจกต์อยู่ใน [agent.md](./agent.md) อ่านก่อนเริ่มสอนครั้งแรก

## ภาพรวม

| | |
|---|---|
| ผู้เรียน | เขียนโปรแกรมภาษาอื่นเป็น แต่ไม่เคยเขียน Android |
| จังหวะ | 6 session ไม่จับเวลา ผู้เรียนกำหนดจังหวะเอง ควรเว้นอย่างน้อย 1 วันระหว่าง session |
| สิ่งที่ทำ | แอปร้านกาแฟ สร้างต่อยอดกันทุก session บนโปรเจกต์เดียว |
| ผลลัพธ์ | ผู้เรียนรับงานเล็ก ๆ ในโปรเจกต์ Android หลาย module ได้: รู้ว่าต้องแตะไฟล์ไหน เพิ่ม API หนึ่งเส้นจนถึงหน้าจอได้ และเปิดหน้าจอข้าม module ได้ |

| Session | หัวข้อ | สิ่งที่แอปทำได้เมื่อจบ |
|---|---|---|
| 1 | แอป Android ประกอบด้วยอะไร | มีข้อความและปุ่ม กดแล้วข้อความเปลี่ยน |
| 2 | ViewModel, LiveData และ Koin | หมุนจอแล้วข้อมูลไม่หาย |
| 3 | โหลดข้อมูลผ่าน UseCase และ Repository | โหลดเมนูแนะนำจาก API มีสถานะกำลังโหลดและผิดพลาด |
| 4 | Intent, Router และ deep link | เปิดหน้ารายละเอียดได้ 3 ทาง: ในแอป, ข้าม module, จากลิงก์นอกแอป |
| 5 | รายการด้วย RecyclerView | แสดงเมนูทั้งหมดเป็นรายการ กดแล้วเปิดรายละเอียด |
| 6 | Feature flag, unit test และทำ feature เองทั้งเส้น | มีปุ่มแนะนำขนมที่เปิดปิดด้วย flag และมี test |

แต่ละ session ต่อยอดจาก session ก่อนหน้า Session 1–4 คือขั้นต่ำที่ทำให้อ่านโค้ดจริงออก

## เมื่อไรที่คุณเริ่มสอน

เริ่มเมื่อผู้ใช้บอกว่าอยากเรียน เช่น "เริ่มเรียน", "สอนต่อ", "เรียน session 3", "start lesson"
ถ้าผู้ใช้ขอให้แก้หรือปรับปรุงโปรเจกต์นี้ (ไม่ใช่เรียน) คุณไม่ได้อยู่ในบทผู้สอน ให้ทำงานตาม [agent.md](./agent.md)

ทุกครั้งที่เริ่ม ให้ทำ 3 อย่างนี้ก่อนพูดเรื่องเนื้อหา:

1. **อ่าน `progress.md`** ที่ root ของ repo (ถ้ามี) เพื่อรู้ว่าผู้เรียนอยู่ตรงไหนและเคยติดเรื่องอะไร
2. **ตรวจจากโค้ดจริง** ว่าเหลือ `TODO(Session N · ภารกิจ M)` อะไรบ้างใน `workshop/` session ที่มีเลขน้อยที่สุดที่ยังเหลือ TODO คือจุดที่ต้องทำต่อ
   ข้อยกเว้น: Session 6 · ภารกิจ 2 ไม่มี TODO ให้ดูว่ามีไฟล์ `workshop/coffee/src/test/.../CoffeeMenuViewModelTest.kt` หรือยัง
3. **บอกผู้เรียนสั้น ๆ** ว่าจะเริ่มที่ไหน แล้วถามว่าพร้อมไหม

ถ้าเป็นครั้งแรกของผู้เรียน (ไม่มี `progress.md` และ TODO ยังครบ) ให้พาทำสองหัวข้อนี้ก่อน ตามลำดับ: "เตรียมเครื่อง" แล้ว "แนะนำโครงสร้างโปรเจกต์"
**ห้ามให้โจทย์แรกก่อนแนะนำโครงสร้าง** ผู้เรียนต้องรู้ก่อนว่าของแต่ละอย่างในโปรเจกต์คืออะไรและอยู่ตรงไหน

ตอนเปิด session ถัด ๆ ไป ให้บอกทุกครั้งว่า session นี้จะแตะไฟล์ใดบ้าง อยู่ใน module ใด และ module นั้นมีหน้าที่อะไร โดยชี้กลับไปที่แผนผังในหัวข้อ "แนะนำโครงสร้างโปรเจกต์"

## วิธีดำเนินการสอน

### วงจรของหนึ่งภารกิจ

ทำทีละภารกิจ ห้ามให้โจทย์ล่วงหน้าหลายข้อ

1. **เล่า** — เล่าปัญหาที่ภารกิจนี้แก้ ด้วยคำของคุณเอง จากหัวข้อ "เล่า" หรือ "พูด" ในบทสอน สั้น ไม่เกิน 5–6 บรรทัด ยังไม่บอกวิธีทำ
2. **ให้โจทย์** — บอกโจทย์, ไฟล์ที่ต้องแก้ (เป็นลิงก์ที่กดได้) และวิธีเช็กว่าผ่าน
3. **หยุดและรอ** — จบข้อความตรงนั้น ผู้เรียนจะกลับมาด้วยหนึ่งในสี่แบบ:

| ผู้เรียนบอกว่า | คุณทำ |
|---|---|
| "เสร็จแล้ว" | ตรวจงาน (ดูหัวข้อถัดไป) |
| "ติด", "ใบ้หน่อย" หรือส่ง error มา | ใบ้เป็นขั้น (ดูหัวข้อถัดไป) |
| "เฉลยเลย" | เฉลยและอธิบาย |
| ถามเรื่องอื่น | ถ้าเกี่ยวกับภารกิจ ตอบ ถ้านอกเรื่อง (Compose, Hilt, Flow ซึ่งหลักสูตรนี้ไม่ได้ใช้) ตอบหนึ่งประโยค จดลง `progress.md` แล้วพากลับ |

4. **อธิบายหลังเฉลย** — ทำทุกครั้ง ไม่ว่าผู้เรียนจะทำถูกเองหรือดูเฉลย หัวข้อ "อธิบายหลังเฉลย" คือเนื้อหาจริงของบทเรียน: สิ่งที่เพิ่งเขียนคืออะไร และทำไมโปรเจกต์นี้ทำแบบนี้
5. **บันทึก** ลง `progress.md` แล้วถามว่าไปภารกิจถัดไปเลยไหม

**ไม่มีการจับเวลา** ผู้เรียนกำหนดจังหวะเอง ไม่ต้องบอกว่าภารกิจควรใช้เวลาเท่าไร และไม่เร่ง คุณเฉลยในสองกรณีเท่านั้น: ผู้เรียนขอ หรือให้คำใบ้ครบสามขั้นแล้วผู้เรียนยังไปต่อไม่ได้ ซึ่งกรณีหลังให้ถามก่อนว่าอยากดูเฉลยหรืออยากลองต่อ

### ตรวจงาน

อย่าเชื่อคำว่า "เสร็จแล้ว" โดยไม่ดู คุณอ่านไฟล์ของผู้เรียนได้ ให้ใช้ความสามารถนั้น

- **อ่านไฟล์ที่ภารกิจนั้นแตะ** แล้วเทียบกับ "เฉลย" ในบทสอนและไฟล์ใน `solutions/session-N/`
- **โค้ดที่ต่างจากเฉลยแต่ถูกต้องถือว่าผ่าน** บอกผู้เรียนว่าถูก แล้วชี้ว่าต่างจากแบบของโปรเจกต์นี้ตรงไหนและเพราะอะไรโปรเจกต์นี้ถึงเขียนอีกแบบ ถ้าความต่างจะทำให้ภารกิจถัดไปทำตามบทสอนไม่ได้ (เช่น ตั้งชื่อ id หรือชื่อ function ไม่ตรง) ให้ขอให้ผู้เรียนแก้ชื่อให้ตรง
- **ถ้าสงสัยว่า compile ไม่ผ่าน** รัน `./gradlew assembleSitDebug` แล้วอ่าน error ให้
- **สิ่งที่คุณมองไม่เห็น** คือหน้าจอ emulator ให้ถามผู้เรียนตามหัวข้อ "เช็กว่าผ่าน" ว่าเห็นอะไร ส่วน Logcat ให้ผู้เรียนวางข้อความมา หรือถ้ามีอุปกรณ์ต่ออยู่คุณอ่านเองได้ด้วย `adb logcat -d -s Lifecycle`
- **ถ้าผิด** บอกว่าผิดตรงไหนแบบเจาะจงหนึ่งจุด แล้วให้ผู้เรียนแก้เอง ไม่แก้ให้

### ใบ้เป็นขั้น

ให้ทีละขั้น แล้วรอผู้เรียนลองก่อนให้ขั้นถัดไป:

1. ชี้ไฟล์และบริเวณที่ต้องดู
2. ชี้บรรทัด หรือบอกว่าขาดอะไร
3. บอกชื่อ method หรือรูปแบบที่ต้องใช้
4. เฉลย

ก่อนใบ้ ให้ดูตาราง "จุดที่คนมักติด" ของภารกิจนั้น อาการของผู้เรียนมักตรงกับแถวใดแถวหนึ่ง

### สิ่งที่ห้ามทำ

- **ห้ามแก้ไฟล์ของผู้เรียนเอง** ยกเว้นผู้เรียนขอชัดเจน การเขียนให้คือการเอาบทเรียนไปจากผู้เรียน
- **ห้ามเฉลยก่อนผู้เรียนได้ลอง** และห้ามพูดถึงคำตอบของภารกิจถัดไป
- **ห้ามแนะนำให้เปิด `solutions/` หรือ `review/`** เว้นแต่ถึงขั้นเฉลย หรือบทสอนบอกให้คัดลอกไฟล์จากที่นั่น
- **ห้ามข้ามหัวข้อ "เช็กว่าผ่าน"** ให้ผู้เรียนรันแอปทุกครั้งที่จบภารกิจ การเห็นหน้าจอเปลี่ยนคือสิ่งที่ทำให้มือใหม่เชื่อมโค้ดกับผลลัพธ์ได้
- **ห้ามเทเนื้อหาทั้ง session ในข้อความเดียว** ข้อความของคุณควรสั้นพอที่ผู้เรียนอ่านจบแล้วลงมือได้ทันที

### แปลงบทสอนให้เข้ากับแชต

บทสอนของแต่ละ session เขียนเป็นลำดับเหตุการณ์ ให้แปลงดังนี้:

| ในบทสอนเขียนว่า | คุณทำ |
|---|---|
| "พูด", "เล่า" | เล่าด้วยคำของคุณเอง ไม่ต้องคัดลอกทุกคำ |
| "วาดบนกระดาน", แผนผัง | แสดงแผนผังในข้อความ |
| "ถามผู้เรียน", "ให้ผู้เรียนตอบ", "จับคู่" | ถามในแชตทีละคำถาม รอคำตอบ แล้วจึงเฉลย |
| "เปิดไฟล์ให้ดู" | ส่งลิงก์ไฟล์ให้ผู้เรียนเปิด และบอกว่าให้มองหาอะไร |
| "สาธิต" | บอกขั้นตอนให้ผู้เรียนทำเองบนเครื่อง แล้วถามว่าเห็นอะไร |

แผนผังที่ใช้ทวนตอนเปิดทุก session ให้ผู้เรียนเป็นคนเล่าว่าครั้งก่อนทำกล่องไหนไปแล้ว:

```
[ XML layout ]─[ Activity ]─[ ViewModel ]─[ UseCase ]─[ Repository ]─[ Api ]─[ server / mock ]
   session 1     session 1    session 2     session 3   ── มีให้แล้ว, เขียนเองใน session 6 ──

[ Activity ]──Intent──►[ Activity อื่น ]            session 4
[ Activity ]──────────►[ RecyclerView + Adapter ]   session 5
```

### จบ session

1. ถามคำถามในหัวข้อ "สรุป" ทีละข้อ รอคำตอบ แล้วเฉลย
2. ตอบคำถามที่จดค้างไว้ใน `progress.md`
3. อัปเดต `progress.md`
4. แนะนำให้ผู้เรียน commit งานของ session นี้ (ผู้เรียนเป็นคน commit เอง หรือขอให้คุณทำ)
5. บอกว่า session ถัดไปเรื่องอะไร และแนะนำให้เว้นอย่างน้อย 1 วัน ถ้าผู้เรียนอยากทำต่อทันทีก็ทำได้

### `progress.md`

เก็บไว้ที่ root ของ repo คุณเป็นคนสร้างและอัปเดต เพราะคุณจำบทสนทนาครั้งก่อนไม่ได้ ไฟล์นี้คือความจำของคุณ ใช้รูปแบบนี้:

```markdown
# ความคืบหน้า

- ผู้เรียน: <ชื่อที่ผู้เรียนให้เรียก>
- เตรียมเครื่อง: ผ่านแล้ว (2026-10-08)

## Session 1 (2026-10-08)
- ภารกิจ 1: ทำเอง ใช้คำใบ้ขั้น 2 — ติดเรื่อง constraint
- ภารกิจ 2: ทำเอง
- สรุป: ตอบได้ครบ

## คำถามค้าง
- Compose ต่างจาก XML อย่างไร (ตอบท้าย session 6)
```

### เมื่อผู้เรียนตามไม่ทัน

`solutions/session-N/` (ที่ root ของ repo) เก็บไฟล์ในสภาพ "จบ session N" และใช้ path เดียวกับใน `workshop/`

```
cd workshop && ./scripts/catch-up.sh 3
```

คำสั่งนี้ทับเฉลยตั้งแต่ session 1 ถึง 3 ทำให้โปรเจกต์พร้อมเริ่ม session 4
ใช้เมื่อผู้เรียนอยากข้าม session หรือโค้ดพังจนแก้ไม่คุ้ม **ต้องถามผู้เรียนก่อนรันทุกครั้ง** เพราะงานที่ผู้เรียนเขียนเองในไฟล์เดียวกันจะถูกทับ และแนะนำให้ commit ก่อน
ถ้าต้องการเฉลยของไฟล์เดียวระหว่างภารกิจ ให้แสดงเนื้อหาจาก `solutions/session-N/` ให้ผู้เรียนพิมพ์ตามหรือคัดลอกเอง

## เตรียมเครื่อง (ครั้งแรกของผู้เรียน)

พาผู้เรียนทำทีละข้อ ถามผลของแต่ละข้อก่อนไปข้อถัดไป บอกผู้เรียนล่วงหน้าว่าการดาวน์โหลดครั้งแรกใช้เวลา 30–40 นาที

1. ติดตั้ง Android Studio รุ่นล่าสุด
2. สร้าง emulator 1 ตัว: Tools › Device Manager › Create Device เลือก Pixel รุ่นใดก็ได้, API 34 ขึ้นไป แล้วเปิดให้ขึ้นหน้า home และเปิด Auto-rotate
3. เปิดโฟลเดอร์ `workshop/` (ไม่ใช่โฟลเดอร์นอกสุดของ repo) ใน Android Studio แล้วรอ Gradle sync จนเสร็จ
4. ตรวจที่ Build › Select Build Variant ว่า module `app` เลือก `sitDebug`
5. กด Run แล้วเห็นแอปมีปุ่ม "เข้าร้านกาแฟ" กดแล้วเห็นหน้าที่มีคำว่า "ร้านกาแฟ"

ถ้าข้อ 3 หรือ 5 ไม่ผ่าน ให้รัน `./gradlew assembleSitDebug` เองเพื่ออ่าน error
เมื่อผ่านครบ ถามชื่อที่ผู้เรียนอยากให้เรียก สร้าง `progress.md` แล้วไปต่อที่หัวข้อ "แนะนำโครงสร้างโปรเจกต์"

## แนะนำโครงสร้างโปรเจกต์ (ก่อน session 1)

ทำหลังเตรียมเครื่องผ่าน และก่อนให้โจทย์แรก เป้าหมายคือให้ผู้เรียนมองแถบซ้ายของ Android Studio แล้วไม่รู้สึกว่าเป็นกองไฟล์ที่ไม่รู้จัก
แนะนำจากภาพใหญ่ไปภาพเล็กเป็น 4 ชั้น ทีละชั้น หลังแต่ละชั้นให้ผู้เรียนเปิดดูของจริงแล้วถามหนึ่งคำถามก่อนไปชั้นถัดไป **ไม่ต้องให้จำ** บอกผู้เรียนว่ากลับมาดูตารางนี้ได้ตลอด

ให้ผู้เรียนสลับแถบซ้ายของ Android Studio เป็นมุมมอง **Project** (เมนูเลื่อนลงเหนือรายการไฟล์ ค่าเริ่มต้นคือ Android) เพื่อให้เห็นโฟลเดอร์ตรงกับที่อธิบาย

### ชั้นที่ 1 — ทั้ง repo

```
learning-android/
├── lessons/      บทเรียนพื้นฐานรายหัวข้อ
├── workshop/     โปรเจกต์ Android ที่จะเขียนโค้ดลงไป ← เปิดโฟลเดอร์นี้ด้วย Android Studio
├── solutions/    เฉลย
├── learning.md   บทสอนที่ Claude ใช้
└── README.md     วิธีเริ่มเรียน
```

**ถาม:** ตอนนี้ Android Studio เปิดอยู่ที่โฟลเดอร์ไหน (คำตอบ: `workshop/`)

### ชั้นที่ 2 — module ใน `workshop/`

โปรเจกต์แบ่งเป็น **module** คือโฟลเดอร์ที่ build แยกกันได้และมีหน้าที่ของตัวเอง

| Module | คืออะไร | เปรียบเทียบ | ผู้เรียนจะได้แตะไหม |
|---|---|---|---|
| `app` | จุดเริ่มของแอป: หน้าแรก และการประกอบทุก module เข้าด้วยกัน | ประตูหน้าของตึก | นิดหน่อย ใน session 4 และ 6 |
| `coffee` | **feature** ร้านกาแฟ: หน้าจอและ logic ของหน้าจอ | ห้องหนึ่งห้องในตึก | **มากที่สุด ทุก session** |
| `apilayer` | ทุกอย่างที่เกี่ยวกับการเรียก API ของทุก feature | แผนกติดต่อภายนอก | ตั้งแต่ session 3 |
| `core` | ของกลางที่ทุก feature ใช้ร่วมกัน เช่น หน้าจอแม่แบบ | ระบบน้ำไฟของตึก | อ่านเป็นหลัก แก้หนึ่งไฟล์ใน session 4 |
| `networks` | ชนิดข้อมูลพื้นฐานของการเรียกข้อมูล | มาตรฐานปลั๊กไฟ | อ่านอย่างเดียว |
| `buildSrc` | รายการเวอร์ชันของ library ทั้งหมด | สมุดจดรุ่นอุปกรณ์ | ไม่ต้องแตะ |

บอกผู้เรียนว่า: โปรเจกต์ขนาดใหญ่มี module แบบ `coffee` ได้หลายสิบตัว ตัวละหนึ่ง feature ส่วน `app`, `core`, `apilayer`, `networks` มีอย่างละตัวเหมือนที่เห็น

**ถาม:** ถ้าจะแก้หน้าตาของหน้าร้านกาแฟ น่าจะต้องเข้า module ไหน (คำตอบ: `coffee`)

### ชั้นที่ 3 — ข้างใน module หนึ่งตัว

ให้ผู้เรียนกางโฟลเดอร์ `coffee` ออกดู ทุก module มีรูปร่างเดียวกัน

```
coffee/
├── build.gradle                       module นี้ใช้ library อะไร และพึ่ง module ไหน
└── src/main/
    ├── AndroidManifest.xml            ประกาศต่อระบบว่า module นี้มีหน้าจออะไรบ้าง
    ├── java/com/learning/coffee/      โค้ด Kotlin (โฟลเดอร์ชื่อ java ตามธรรมเนียมเดิม)
    │   ├── ui/                        หน้าจอ แยกโฟลเดอร์ย่อยตามหน้า: menu/, detail/
    │   ├── di/                        การลงทะเบียนว่าของแต่ละอย่างสร้างอย่างไร (session 2)
    │   ├── route/                     ทางเข้าให้ module อื่นเปิดหน้าจอของ module นี้ (session 4)
    │   └── common/                    ของที่ใช้ร่วมกันภายใน module นี้
    └── res/                           resource: ทุกอย่างที่ไม่ใช่โค้ด
        ├── layout/                    หน้าตาของหน้าจอ เป็นไฟล์ XML
        └── values/strings.xml         ข้อความที่แสดงบนจอ
```

อธิบายสองคำที่จะเจอทุกวัน:

- **`java/` กับ `res/`** — พฤติกรรมอยู่ใน `java/` หน้าตาและข้อความอยู่ใน `res/` หน้าจอหนึ่งหน้าจึงมีไฟล์อย่างน้อยสองไฟล์ที่คู่กัน เช่น `ui/menu/CoffeeMenuActivity.kt` คู่กับ `res/layout/activity_coffee_menu.xml`
- **`com/learning/coffee`** — package คือที่อยู่ของ class ใช้ตอน import โฟลเดอร์ซ้อนกันตามชื่อ package

**ถาม:** ให้ผู้เรียนหาไฟล์ที่เป็น "หน้าตา" ของหน้ารายละเอียดเมนู (คำตอบ: `coffee/src/main/res/layout/activity_coffee_detail.xml`)

### ชั้นที่ 4 — ไฟล์ที่ root ของ `workshop/`

ไฟล์กลุ่มนี้คือระบบ build ชื่อ **Gradle** ซึ่งแปลงโค้ดทั้งหมดเป็นแอปที่ติดตั้งได้ ผู้เรียนไม่ต้องแก้ไฟล์กลุ่มนี้ แค่รู้ว่าคืออะไร

| ไฟล์ | คืออะไร |
|---|---|
| `settings.gradle` | รายชื่อ module ทั้งหมดของโปรเจกต์ |
| `build.gradle` (ตัวที่ root) | การตั้งค่าที่ทุก module ใช้ร่วมกัน |
| `gradlew`, `gradle/` | ตัวรัน Gradle ที่พกมากับโปรเจกต์ ทุกคนจึงใช้เวอร์ชันเดียวกัน |
| `gradle.properties` | ค่าตั้งของ Gradle |
| `local.properties` | ที่อยู่ของ Android SDK บนเครื่องนี้ แต่ละเครื่องไม่เหมือนกัน |
| `scripts/catch-up.sh` | ทับไฟล์ด้วยเฉลยเมื่อตามไม่ทัน |
| โฟลเดอร์ `build/`, `.gradle/`, `.idea/` | ของที่เครื่องสร้างขึ้นเอง ไม่ต้องเปิดดู |

**ถาม:** ไฟล์ไหนบอกว่าโปรเจกต์นี้มี module อะไรบ้าง ให้เปิดดูแล้วนับ (คำตอบ: `settings.gradle` มี 5 module ส่วน `buildSrc` ไม่ต้องประกาศเพราะ Gradle รู้จักชื่อนี้เอง)

### ปิดการแนะนำ

แสดงแผนผังนี้ แล้วบอกว่า workshop ทั้งหมดคือการไล่เติมจากซ้ายไปขวา และแต่ละกล่องอยู่ใน module ใด:

```
[ XML layout ]─[ Activity ]─[ ViewModel ]─[ UseCase ]─[ Repository ]─[ Api ]─[ server / mock ]
 └──────────── coffee ────────────────┘   └──────────── apilayer ───────────┘   └── app (mock) ──┘
```

จดลง `progress.md` ว่าแนะนำโครงสร้างแล้ว จากนั้นถามว่าจะเริ่ม session 1 เลยหรือพักก่อน

---

# Session 1 — แอป Android ประกอบด้วยอะไร

**เป้าหมาย:** ผู้เรียนบอกได้ว่าหน้าจอหนึ่งหน้าประกอบด้วยไฟล์อะไร แก้หน้าตาและพฤติกรรมของหน้าจอได้ และรู้ว่าหน้าจอ "เกิดและตาย" เมื่อไร

| ลำดับ | ช่วง |
|---|---|
| 1 | สำรวจโปรเจกต์ |
| 2 | ภารกิจ 1 — เพิ่ม View ใน layout |
| 3 | ภารกิจ 2 — ทำให้ปุ่มทำงาน |
| 4 | สังเกต lifecycle |
| 5 | สรุป |

## สำรวจโปรเจกต์

**พูด:**

- แอป Android คือชุดของ **หน้าจอ** แต่ละหน้าจอคือ class หนึ่งตัวที่เรียกว่า **Activity**
- หน้าตาของหน้าจอเขียนในไฟล์ **XML layout** ส่วนพฤติกรรมเขียนด้วย Kotlin
- ระบบ Android รู้จักหน้าจอของเราจากไฟล์ **AndroidManifest.xml** หน้าจอที่ไม่ได้ประกาศไว้จะเปิดไม่ได้
- โปรเจกต์แบ่งเป็น **module** แต่ละ module คือโฟลเดอร์ที่ build แยกกันได้

**ให้ผู้เรียนหาคำตอบเอง** ถามทีละข้อ แล้วเฉลยเมื่อผู้เรียนตอบ:

| คำถาม | คำตอบ |
|---|---|
| หน้าจอแรกที่เปิดตอนกดไอคอนแอปคือ class ไหน และรู้ได้อย่างไร | `MainActivity` ใน module `app` เพราะใน `app/src/main/AndroidManifest.xml` มี `intent-filter` ที่มี `MAIN` และ `LAUNCHER` |
| กดปุ่ม "เข้าร้านกาแฟ" แล้วไปหน้าจอไหน อยู่ module ใด | `CoffeeMenuActivity` ใน module `coffee` |
| คำว่า "ร้านกาแฟ" บนหน้าจอ เขียนไว้ที่ไฟล์ไหน | `coffee/src/main/res/values/strings.xml` ชื่อ `coffee_title` |
| เวอร์ชันของ library ทั้งหมดอยู่ที่ไหน | `buildSrc/src/main/java/Dependencies.kt` |

**อธิบายหลังเฉลย:** ข้อความไม่ได้พิมพ์ตรง ๆ ใน layout แต่อ้างถึงด้วยชื่อ (`@string/coffee_title`) เพื่อให้แปลภาษาและแก้คำได้ที่เดียว

## ภารกิจ 1 — เพิ่ม View ใน layout

**เล่า:** หน้าร้านกาแฟตอนนี้มีแต่หัวข้อ เราจะเพิ่มคำทักทาย ปุ่ม และที่แสดงผล

**โจทย์** — ใน `coffee/src/main/res/layout/activity_coffee_menu.xml` เพิ่ม View 3 ตัวต่อจาก `tvTitle` เรียงจากบนลงล่าง

| ชนิด | id | รายละเอียด |
|---|---|---|
| `TextView` | `tvGreeting` | อยู่ใต้ `tvTitle` |
| `Button` | `btnRecommend` | อยู่ใต้ `tvGreeting`, ข้อความ `@string/coffee_recommend_button` |
| `TextView` | `tvResult` | อยู่ใต้ `btnRecommend`, ตัวอักษรขนาด `24sp` |

ทุกตัวกว้างเต็มจอ ให้ดู `tvTitle` เป็นตัวอย่าง

**เช็กว่าผ่าน:** รันแอป เข้าหน้าร้านกาแฟ เห็นปุ่ม "แนะนำเมนูให้หน่อย" (ข้อความสองตัวยังว่าง จึงยังมองไม่เห็น)

**เฉลย** — ตัวอย่างหนึ่งตัว ไฟล์เต็มอยู่ที่ `solutions/session-1/`

```xml
<Button
    android:id="@+id/btnRecommend"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginTop="@dimen/default_24"
    android:text="@string/coffee_recommend_button"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tvGreeting" />
```

**อธิบายหลังเฉลย**

- **`android:id="@+id/..."`** — ตั้งชื่อให้ View เพื่อให้โค้ด Kotlin เรียกถึงได้ โปรเจกต์นี้ใช้คำนำหน้าบอกชนิด: `tv` = TextView, `btn` = Button
- **`app:layout_constraint...`** — บอกตำแหน่งโดยผูกขอบของ View กับสิ่งอื่น `Top_toBottomOf="@id/tvGreeting"` แปลว่า "ขอบบนของฉันอยู่ติดขอบล่างของ `tvGreeting`"
- **`0dp`** ในความกว้าง — แปลว่า "ยืดตาม constraint" เมื่อผูกทั้งซ้าย (`Start`) และขวา (`End`) กับ parent จึงกว้างเต็มจอ
- **`wrap_content`** — สูงเท่าที่เนื้อหาต้องการ

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| View ไปกองกันมุมบนซ้าย | ลืมใส่ constraint แนวตั้ง | ใส่ `layout_constraintTop_toBottomOf` |
| ใส่ `0dp` แล้วมองไม่เห็น | ผูก constraint ไม่ครบสองฝั่ง | ต้องมีทั้ง `Start` และ `End` |
| ขึ้นแดงที่ `app:` | ลบบรรทัด `xmlns:app` ที่หัวไฟล์ไปโดยไม่ตั้งใจ | คัดลอกหัวไฟล์จากเฉลย |

## ภารกิจ 2 — ทำให้ปุ่มทำงาน

**เล่า:** layout บอกแค่ว่ามีอะไรอยู่ตรงไหน การกำหนดว่าแสดงข้อความอะไรและกดแล้วเกิดอะไรขึ้นต้องเขียนใน Activity

**โจทย์** — ใน `CoffeeMenuActivity.kt` ที่ `setUpViews()`

1. ให้ `tvGreeting` แสดงข้อความ `R.string.coffee_greeting`
2. เมื่อกด `btnRecommend` ให้ `tvResult` แสดงคำว่า `ลาเต้`

ใบ้: View ทุกตัวเรียกได้ผ่าน `binding.<id>`

**เช็กว่าผ่าน:** เห็นคำทักทาย กดปุ่มแล้วคำว่า "ลาเต้" ปรากฏ

**เฉลย**

```kotlin
override fun setUpViews() {
    binding.tvGreeting.text = getString(R.string.coffee_greeting)
    binding.btnRecommend.setOnClickListener {
        binding.tvResult.text = "ลาเต้"
    }
}
```

**อธิบายหลังเฉลย**

- **ViewBinding** — ไฟล์ `activity_coffee_menu.xml` ถูกสร้างเป็น class `ActivityCoffeeMenuBinding` ให้อัตโนมัติ และทุก View ที่มี id กลายเป็น property ถ้าพิมพ์ id ผิด โค้ดจะ compile ไม่ผ่าน ซึ่งดีกว่าไปพังตอนรัน
- **`getViewBinding()`** — บรรทัดบนสุดของ class คือจุดที่บอกว่าหน้าจอนี้ใช้ layout ไหน
- **`setUpViews()`** — โปรเจกต์นี้ไม่ override `onCreate` ในแต่ละหน้าจอ base class เรียก `setUpViews()` ให้ ทุกหน้าจอจึงมีรูปร่างเหมือนกัน
- **`R.string.coffee_greeting`** — `R` คือ class ที่ระบบสร้างจากทุกไฟล์ใน `res/` ใช้อ้างถึง resource จากโค้ด

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| `binding.tvGreeting` ขึ้นแดง | ยังไม่ได้ build หลังแก้ XML หรือ id ใน XML สะกดไม่ตรง | Build › Make Project แล้วเทียบ id |
| `R` ขึ้นแดง | import `R` ผิด module | ใช้ `com.learning.coffee.R` |

## สังเกต lifecycle

ช่วงนี้ไม่เขียนโค้ด ทุกหน้าจอในโปรเจกต์เขียน log ไว้ให้แล้ว

**ให้ผู้เรียนทำ:** เปิดแถบ **Logcat** ใน Android Studio พิมพ์ `tag:Lifecycle` ในช่องกรอง แล้วทำตามตารางและจดว่าเห็น log อะไร

| ทำสิ่งนี้ | log ที่ควรเห็น (ของ `CoffeeMenuActivity`) |
|---|---|
| เปิดหน้าร้านกาแฟ | `onCreate` → `onStart` → `onResume` |
| กดปุ่มให้ "ลาเต้" ขึ้น แล้ว **หมุนจอ** | `onPause` → `onStop` → `onDestroy` → `onCreate` → `onStart` → `onResume` |
| กดปุ่ม Home ของเครื่อง | `onPause` → `onStop` |
| กลับเข้าแอป | `onStart` → `onResume` |
| กดปุ่ม Back | `onPause` → `onStop` → `onDestroy` |

**ถามผู้เรียน:** หลังหมุนจอ คำว่า "ลาเต้" ยังอยู่ไหม และทำไม

**อธิบาย:** หมุนจอแล้ว Android **ทำลาย Activity ตัวเดิมและสร้างตัวใหม่** ดูได้จาก `onDestroy` ตามด้วย `onCreate` ทุกอย่างที่เก็บไว้ใน Activity จึงหายไปด้วย
นี่คือเหตุผลหลักที่โปรเจกต์นี้ไม่เก็บข้อมูลไว้ใน Activity และเป็นโจทย์ของ session หน้า

## สรุป session 1

ให้ผู้เรียนตอบปากเปล่า:

1. หน้าจอหนึ่งหน้าประกอบด้วยไฟล์อะไรบ้าง → Activity (Kotlin), layout (XML), และบรรทัดประกาศใน Manifest
2. อยากเปลี่ยนข้อความบนปุ่มต้องแก้ที่ไหน → `strings.xml`
3. หมุนจอแล้วเกิดอะไรกับ Activity → ถูกทำลายแล้วสร้างใหม่

---

# Session 2 — ViewModel, LiveData และ Koin

**เป้าหมาย:** ผู้เรียนแยกหน้าที่ระหว่าง Activity กับ ViewModel ได้ และอ่านข้อความ error ของ Koin ออก

| ลำดับ | ช่วง |
|---|---|
| 1 | ทวน + เล่าปัญหา |
| 2 | ภารกิจ 1 — สร้าง LiveData ใน ViewModel |
| 3 | ภารกิจ 2 — ให้ Activity ใช้ ViewModel |
| 4 | ภารกิจ 3 — ลงทะเบียนใน Koin |
| 5 | สรุป |

## ทวน + เล่าปัญหา

ให้ทุกคนกดปุ่มแล้วหมุนจออีกครั้ง ข้อความหาย

**พูด:** เราต้องการที่เก็บข้อมูลที่ **อยู่รอดเมื่อ Activity ถูกสร้างใหม่** สิ่งนั้นคือ **ViewModel** โปรเจกต์นี้ใช้รูปแบบที่เรียกว่า MVVM:

- **Activity** บอก ViewModel ว่าผู้ใช้ทำอะไร และแสดงสิ่งที่ ViewModel บอก
- **ViewModel** ตัดสินใจและเก็บ state ของหน้าจอ โดยไม่รู้จัก View ใดเลย
- สองฝั่งคุยกันผ่าน **LiveData** ซึ่งเป็นกล่องเก็บค่าที่ "เฝ้าดูได้"

## ภารกิจ 1 — สร้าง LiveData ใน ViewModel

**โจทย์** — ใน `CoffeeMenuViewModel.kt`

1. สร้าง `MutableLiveData<String>` แบบ private ชื่อ `_recommendation`
2. เปิดให้ข้างนอกอ่านได้อย่างเดียวในชื่อ `recommendation` ชนิด `LiveData<String>`
3. เขียน `fun recommend()` ที่สุ่มหนึ่งค่าจาก `menus` แล้วใส่ลงใน `_recommendation`

ใบ้: สุ่มจาก list ด้วย `menus.random()` และตั้งค่า LiveData ด้วย `.value = ...`

**เช็กว่าผ่าน:** Build › Make Project ผ่าน (ยังไม่เห็นผลบนจอ)

**เฉลย**

```kotlin
class CoffeeMenuViewModel : ViewModel() {

    private val menus = listOf("ลาเต้", "อเมริกาโน่", "มัทฉะลาเต้", "โกโก้")

    private val _recommendation = MutableLiveData<String>()
    val recommendation: LiveData<String>
        get() = _recommendation

    fun recommend() {
        _recommendation.value = menus.random()
    }
}
```

**อธิบายหลังเฉลย**

- **คู่ `_recommendation` / `recommendation`** — ตัวที่แก้ค่าได้เป็น private ข้างนอกเห็นแค่ตัวอ่านอย่างเดียว เพื่อให้มีแต่ ViewModel ที่เปลี่ยน state ได้ ทุก ViewModel ในโปรเจกต์นี้เขียนคู่แบบนี้
- **ViewModel ไม่มี `binding`** — ถ้าเห็นใครพยายามตั้งข้อความของ View จากในนี้ แปลว่ากำลังทำผิดชั้น

## ภารกิจ 2 — ให้ Activity ใช้ ViewModel

**โจทย์** — ใน `CoffeeMenuActivity.kt`

1. ประกาศ property: `private val coffeeMenuViewModel: CoffeeMenuViewModel by viewModel()`
2. เปลี่ยนปุ่มให้เรียก `coffeeMenuViewModel.recommend()` แทนการตั้งข้อความเอง และเปลี่ยน `setOnClickListener` เป็น `singleClick`
3. ใน `observeViewModel()` ให้ observe `recommendation` แล้วนำค่าไปแสดงที่ `tvResult`

ใบ้: `observe(liveData) { ค่า -> ... }`

**เช็กว่าผ่าน:** Build ผ่าน แล้วรันแอปและเข้าหน้าร้านกาแฟ **แอปจะเด้ง** ซึ่งถูกต้องแล้ว ให้หยุดตรงนี้และอย่าเพิ่งแก้

**เฉลย**

```kotlin
private val coffeeMenuViewModel: CoffeeMenuViewModel by viewModel()

override fun setUpViews() {
    binding.tvGreeting.text = getString(R.string.coffee_greeting)
    binding.btnRecommend.singleClick {
        coffeeMenuViewModel.recommend()
    }
}

override fun observeViewModel() {
    observe(coffeeMenuViewModel.recommendation) {
        binding.tvResult.text = it
    }
}
```

**อธิบายหลังเฉลย**

- **`observe`** — "เมื่อค่าเปลี่ยน ให้ทำสิ่งนี้" และเมื่อ Activity ถูกสร้างใหม่ LiveData จะส่งค่าล่าสุดให้ observer ตัวใหม่ทันที
- **`singleClick`** — เหมือน `setOnClickListener` แต่กันการกดรัว โค้ดจริงใช้ตัวนี้เกือบทุกที่
- **`by viewModel()`** — เราไม่ได้สร้าง ViewModel เอง แต่ **ขอ** จากระบบที่ชื่อ Koin และตอนนี้ Koin ยังไม่รู้จัก ViewModel ตัวนี้ แอปจึงเด้ง

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| `viewModel` import ผิดตัว | Android Studio เสนอหลายตัว | ใช้ `org.koin.androidx.viewmodel.ext.android.viewModel` |
| `observe` import ผิดตัว | มี `observe` ของ AndroidX ด้วย | ใช้ `com.learning.core.util.observe` |
| `singleClick` ขึ้นแดง | ยังไม่ได้ import | `com.learning.core.extension.singleClick` |

## ภารกิจ 3 — ลงทะเบียนใน Koin

**เล่า (สำคัญ อย่ารีบผ่าน):** ให้ทุกคนเปิด Logcat หาข้อความสีแดง แล้วอ่านบรรทัดที่ขึ้นต้นด้วย `Caused by` จะพบข้อความลักษณะนี้:

```
NoBeanDefFoundException: No definition found for class:'com.learning.coffee.ui.menu.CoffeeMenuViewModel'
```

แปลว่า "มีคนขอ `CoffeeMenuViewModel` แต่ไม่มีใครบอกไว้ว่าต้องสร้างอย่างไร" ข้อความนี้จะเจออีกหลายครั้งในงานจริง

**โจทย์** — ใน `coffee/di/CoffeeModule.kt` ลงทะเบียน ViewModel ภายใน `module { }`

**เช็กว่าผ่าน:** แอปไม่เด้ง กดปุ่มหลายครั้งแล้วเมนูเปลี่ยน และ **หมุนจอแล้วเมนูล่าสุดยังอยู่**

**เฉลย**

```kotlin
private val coffeeModule = module {
    viewModel { CoffeeMenuViewModel() }
}
```

**อธิบายหลังเฉลย**

- **Koin** คือสมุดรายการที่บอกว่า "ถ้ามีคนขอของชนิดนี้ ให้สร้างแบบนี้" เรียกแนวคิดนี้ว่า Dependency Injection
- **ทำไมไม่ `CoffeeMenuViewModel()` เองใน Activity** — เพราะ Activity ถูกสร้างใหม่ตอนหมุนจอ ถ้าสร้างเองก็จะได้ ViewModel ตัวใหม่ทุกครั้ง การขอผ่าน `by viewModel()` ทำให้ได้ตัวเดิมกลับมา
- **`CoffeeModule` โหลดเมื่อไร** — เปิด `BaseCoffeeActivity` ให้ดู `getDi()` แล้วเปิด `BaseActivity.init()` ให้เห็นว่า module ของ feature ถูกโหลดตอนหน้าจอของ feature นั้นเปิด ไม่ใช่ตอนเปิดแอป

## สรุป session 2

1. ให้ผู้เรียนลากนิ้วตามเส้นทางบนกระดาน: กดปุ่ม → Activity เรียก `recommend()` → ViewModel ตั้งค่า LiveData → observer ใน Activity ทำงาน → ข้อความเปลี่ยน
2. ถาม: ถ้าต้องเพิ่มเงื่อนไข "ห้ามสุ่มได้เมนูเดิมซ้ำสองครั้งติด" ต้องแก้ไฟล์ไหน → ViewModel เท่านั้น
3. ถาม: เจอ `No definition found for class` ต้องไปดูที่ไหน → ไฟล์ `di/*Module.kt` ของ feature นั้น

**การบ้าน (ไม่บังคับ):** ทำข้อ 2 ให้ได้จริง

---

# Session 3 — โหลดข้อมูลผ่าน UseCase และ Repository

**เป้าหมาย:** ผู้เรียนไล่เส้นทางข้อมูลจากหน้าจอไปถึง API ได้ และรู้ว่าแต่ละชั้นทำหน้าที่อะไร

| ลำดับ | ช่วง |
|---|---|
| 1 | ทวน + อ่านโค้ดชั้นข้อมูลที่มีให้ |
| 2 | ภารกิจ 1 — เขียน UseCase และลงทะเบียน |
| 3 | ภารกิจ 2 — ให้ ViewModel เรียก UseCase |
| 4 | ภารกิจ 3 — ต่อเข้ากับ Koin และหน้าจอ |
| 5 | สรุป |

## ทวน + อ่านโค้ดชั้นข้อมูล

**พูด:** แอปจริงไม่ได้สุ่มจาก list ในเครื่อง แต่ไปถามข้อมูลจาก server ซึ่ง **ใช้เวลา** และ **ล้มเหลวได้** หน้าจอจึงมี 3 สถานะเสมอ: กำลังโหลด, สำเร็จ, ผิดพลาด

เปิดไฟล์ให้ดูตามลำดับจากนอกเข้าใน ดูแค่จุดที่ระบุ ไม่ต้องอ่านทั้งไฟล์:

| ไฟล์ (ใน module `apilayer`) | ชี้ให้เห็น |
|---|---|
| `repository/coffee/GetRecommendedCoffeeApi.kt` | `@POST("v1/coffee/recommended")` คือ path ของ API, `GetRecommendedCoffeeResponse` คือรูปร่างของข้อมูลที่ได้กลับมา, `@SerializedName("Name")` จับคู่ชื่อใน JSON กับชื่อตัวแปร, Request และ Response สืบทอด `FormData`, และ `API_CODE` คือรหัสฟอร์มของ API นี้ |
| `repository/coffee/GetRecommendedCoffeeRepository.kt` | `getBaseData(request, ApiHeader(...))` ห่อ request ลงซอง แล้ว `requestData(...)` ยิง API และแกะซองขากลับ คืน `Result` ที่เป็นได้สองทาง: `Success` หรือ `Error` |
| `app/src/main/assets/apiData/coffee/recommended_1.json` | JSON ที่ mock ใช้ตอบ เพราะเราไม่มี server จริง ให้สังเกตว่าข้อมูลจริง (`Name`, `Price`) อยู่ใน `Form` → `FormData` ไม่ได้อยู่ชั้นนอกสุด |
| `app/src/main/assets/apiData/coffee/recommended_error.json` | คำตอบตอนทำรายการไม่สำเร็จ: HTTP ยังสำเร็จ แต่ใน `FormData` มี `ErrorCode` และ `ErrorMessage` |

**พูดเรื่องซอง:** ทุก API ของโปรเจกต์นี้ส่งและรับข้อมูลในซองเดียวกัน คือ `Form` ที่มี `ApiHeader` (รหัสบอกว่าเป็นฟอร์มของ API ไหน) กับ `FormData` (ข้อมูลจริง)
class ของซองอยู่ที่ `networks/model/BaseModel.kt` ให้ผู้เรียนเปิดดู มีแค่ 4 class สั้น ๆ
ข้อดีสำหรับเรา: การห่อและแกะซองจบในชั้น Repository ทั้งหมด UseCase, ViewModel และหน้าจอเห็นแค่ข้อมูลข้างใน
ข้อที่ต้องจำ: server แจ้งว่า "ทำรายการไม่สำเร็จ" ได้สองทาง คือ HTTP error และ `ErrorCode` ในซอง ซึ่ง `requestData` แปลงทั้งสองทางเป็น `Result.Error` ให้แล้ว

**พูด:** ระหว่าง Repository กับ ViewModel ยังขาดอีกหนึ่งชั้นคือ **UseCase** ซึ่งเราจะเขียนกันเอง

## ภารกิจ 1 — เขียน UseCase และลงทะเบียน

**โจทย์**

1. สร้างไฟล์ใหม่ `apilayer/.../usecase/coffee/GetRecommendedCoffeeUseCase.kt`
   - class รับ `GetRecommendedCoffeeRepository` ทาง constructor
   - สืบทอด `UseCase<GetRecommendedCoffeeResponse, GetRecommendedCoffeeRequest>()`
   - override `run(params, useCache)` ให้คืนผลจาก `repository.getRecommendedCoffee(params)`
2. ใน `apilayer/di/ApiLayerModule.kt` ลงทะเบียนด้วย `single { GetRecommendedCoffeeUseCase(get()) }`

**เช็กว่าผ่าน:** Build ผ่าน

**เฉลย**

```kotlin
class GetRecommendedCoffeeUseCase(private val repository: GetRecommendedCoffeeRepository) :
    UseCase<GetRecommendedCoffeeResponse, GetRecommendedCoffeeRequest>() {
    override suspend fun run(
        params: GetRecommendedCoffeeRequest,
        useCache: Boolean
    ): Result<Failure, GetRecommendedCoffeeResponse?> {
        return repository.getRecommendedCoffee(params)
    }
}
```

**อธิบายหลังเฉลย**

- **UseCase คือ "งานหนึ่งอย่าง"** ตั้งชื่อเป็นกริยา ตัว class สั้นมาก เพราะงานหนักอยู่ใน class แม่
- เปิด `networks/usecase/UseCase.kt` ให้ดู: `run()` ถูกเรียกบน `Dispatchers.IO` (background thread) แล้วผลถูกส่งกลับมาบน main thread
- **ทำไมต้องย้าย thread** — Android มี main thread ตัวเดียวที่ใช้วาดหน้าจอ ถ้าเอางานช้าไปทำบนนั้นหน้าจอจะค้าง และระบบห้ามเรียก network บน main thread
- **`get()`** — บอก Koin ว่า "หาของชนิดที่ตรงกับ parameter ตัวนี้มาใส่ให้" ในที่นี้คือ Repository ที่ลงทะเบียนไว้บรรทัดบน

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| `Result` / `Failure` import ผิดตัว | Kotlin มี `Result` ของตัวเอง | ใช้ `com.learning.networks.model.Result` และ `...model.Failure` |
| ขึ้นแดงว่า return type ไม่ตรง | ลืมเครื่องหมาย `?` หลัง Response | ชนิดที่คืนต้องเป็น `Result<Failure, GetRecommendedCoffeeResponse?>` |

## ภารกิจ 2 — ให้ ViewModel เรียก UseCase

**เล่า:** แนะนำ `Response` ซึ่งเป็นชนิดข้อมูลที่บอกสถานะของหน้าจอ มี 3 ค่า: `Response.Loading`, `Response.Success(value)`, `Response.Error(failure)`

**โจทย์** — ใน `CoffeeMenuViewModel.kt` ลบของเดิม (`menus`, `recommendation`, `recommend()`) แล้ว

1. รับ `GetRecommendedCoffeeUseCase` ทาง constructor
2. สร้าง LiveData คู่ใหม่ชื่อ `_recommendedCoffeeResult` / `recommendedCoffeeResult` ชนิด `Response<GetRecommendedCoffeeResponse>`
3. เขียน `fun getRecommendedCoffee(params: GetRecommendedCoffeeRequest)`
   - ตั้งค่าเป็น `Response.Loading` ก่อน
   - เรียก `getRecommendedCoffeeUseCase(params) { res -> ... }`
   - ใน callback ใช้ `res.result({ กรณีผิดพลาด }, { กรณีสำเร็จ })` เพื่อตั้งค่าเป็น `Response.Error` หรือ `Response.Success`

**เช็กว่าผ่าน:** ไฟล์ ViewModel ไม่มีสีแดง (ไฟล์อื่นจะยังแดงอยู่ แก้ในภารกิจถัดไป)

**เฉลย**

```kotlin
class CoffeeMenuViewModel(
    private val getRecommendedCoffeeUseCase: GetRecommendedCoffeeUseCase
) : ViewModel() {

    private val _recommendedCoffeeResult = MutableLiveData<Response<GetRecommendedCoffeeResponse>>()
    val recommendedCoffeeResult: LiveData<Response<GetRecommendedCoffeeResponse>>
        get() = _recommendedCoffeeResult

    fun getRecommendedCoffee(params: GetRecommendedCoffeeRequest) {
        _recommendedCoffeeResult.value = Response.Loading
        getRecommendedCoffeeUseCase(params) { res ->
            res.result({
                _recommendedCoffeeResult.value = Response.Error(it)
            }, {
                it?.let {
                    _recommendedCoffeeResult.value = Response.Success(it)
                }
            })
        }
    }
}
```

**อธิบายหลังเฉลย**

- **`Result` กับ `Response` ต่างกัน** — `Result` คือผลของงาน (สำเร็จหรือพลาด) ส่วน `Response` คือสถานะของหน้าจอ (มี "กำลังโหลด" เพิ่ม) ViewModel เป็นคนแปลงจากอย่างแรกเป็นอย่างหลัง
- **ทำไมรวมเป็นค่าเดียว** — ถ้าแยกเป็นตัวแปร `isLoading`, `data`, `error` สามตัว มันขัดกันเองได้ เช่น กำลังโหลดและมี error พร้อมกัน
- **รูปแบบนี้ซ้ำทุก ViewModel** — ในโปรเจกต์ขนาดใหญ่จะเห็น function หน้าตาแบบนี้หลายสิบตัว ต่างกันแค่ชื่อ

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| `Response` import ผิดตัว | มี `Response` ของ Retrofit และ OkHttp | ใช้ `com.learning.networks.model.Response` |
| วงเล็บ `res.result` ไม่ลงตัว | มี lambda สองตัวอยู่ในวงเล็บเดียว | รูปแบบคือ `res.result({ ... }, { ... })` |

## ภารกิจ 3 — ต่อเข้ากับ Koin และหน้าจอ

**โจทย์**

1. ใน `coffee/di/CoffeeModule.kt` แก้ให้ส่ง dependency: `viewModel { CoffeeMenuViewModel(get()) }`
2. ใน `CoffeeMenuActivity.kt`
   - ปุ่มเรียก `coffeeMenuViewModel.getRecommendedCoffee(GetRecommendedCoffeeRequest())`
   - observe `recommendedCoffeeResult` แล้วใช้ `handleResponse(result, onSuccess = { ... })` แสดงชื่อและราคาที่ `tvResult` ด้วย `getString(R.string.coffee_result, ชื่อ, ราคา)`

**เช็กว่าผ่าน:** กดปุ่มแล้วเห็นวงโหลดประมาณ 1.5 วินาที ตามด้วย "ลาเต้ · 65 บาท" กดอีกครั้งได้ "มอคค่า · 75 บาท" **กดครั้งที่ 3 จะมี dialog แจ้งข้อผิดพลาด** ซึ่งตั้งใจให้เป็นแบบนั้น

**เฉลย**

```kotlin
override fun observeViewModel() {
    observe(coffeeMenuViewModel.recommendedCoffeeResult) { result ->
        handleResponse(result, onSuccess = {
            binding.tvResult.text = getString(R.string.coffee_result, it.name.orEmpty(), it.price ?: 0)
        })
    }
}
```

**อธิบายหลังเฉลย**

- **`handleResponse`** — แตก 3 สถานะให้: แสดงและซ่อนวงโหลด, แสดง dialog เมื่อผิดพลาด เราเขียนแค่กรณีสำเร็จ ทุกหน้าจอจึงจัดการ error เหมือนกันโดยไม่ต้องเขียนซ้ำ เปิด `core/extension/ResultHandleExtension.kt` ให้ดู
- **`.orEmpty()` และ `?: 0`** — field ของ Response เป็น nullable เพราะ server อาจไม่ส่งมา โค้ดจริงจึงมีการกันค่า null แบบนี้ทุกที่
- **ดู request จริง** — เปิด Logcat กรอง `tag:okhttp.OkHttpClient` จะเห็น request และ JSON ที่ตอบกลับทุกครั้ง นี่คือเครื่องมือแรกที่ใช้เมื่อหน้าจอแสดงข้อมูลไม่ถูก
- **ครั้งที่ 3 ที่ล้ม** — mock ตอบ HTTP 200 แต่ใน `FormData` มี `ErrorCode`, `requestData` เห็นว่าไม่ว่างจึงแปลงเป็น `Failure.ServerError` โดยใช้ `ErrorMessage` เป็นข้อความ, ViewModel แปลงเป็น `Response.Error`, `handleResponse` แสดง dialog ด้วยข้อความนั้น ให้ผู้เรียนไล่เส้นทางนี้เองทีละชั้น และเปิด `recommended_error.json` ดูประกอบ

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| แอปเด้งตอนเปิดหน้าจอ ข้อความมี `Could not create instance for [...CoffeeMenuViewModel]` | แก้ constructor ของ ViewModel แล้วลืมแก้ `CoffeeModule` | จำนวน `get()` ต้องเท่ากับจำนวน parameter |
| แอปเด้ง ข้อความมี `No definition found for class:'...GetRecommendedCoffeeUseCase'` | ลืมลงทะเบียน UseCase ในภารกิจ 1 | เพิ่มบรรทัด `single { ... }` ใน `ApiLayerModule` |
| วงโหลดไม่หาย | lambda ทางใดทางหนึ่งของ `result` ไม่ได้ตั้งค่า LiveData | ตรวจทั้งสองทาง |

## สรุป session 3

1. ให้ผู้เรียนหนึ่งคนไล่เส้นทางเต็มบนกระดาน ตั้งแต่กดปุ่มจนข้อความขึ้น โดยบอกชื่อไฟล์ของทุกชั้น
2. ถาม: API เปลี่ยนชื่อ field จาก `Name` เป็น `MenuName` ต้องแก้ที่ไหน → `@SerializedName` ใน `GetRecommendedCoffeeApi.kt` ที่เดียว
3. ถาม: อยากให้ตอน error แสดงข้อความในหน้าจอแทน dialog ทำอย่างไร → ส่ง `onError = { ...; true }` ให้ `handleResponse`

**การบ้าน (ไม่บังคับ):** ทำข้อ 3 ให้ได้จริง

---

# Session 4 — Intent, Router และ deep link

**เป้าหมาย:** ผู้เรียนเปิดหน้าจอได้ทั้ง 3 แบบ และรู้ว่าแต่ละแบบใช้เมื่อไร

| แบบ | ใช้เมื่อ | ภารกิจ |
|---|---|---|
| `newInstance()` + extra | หน้าจออยู่ใน module เดียวกัน | 1 |
| Router | หน้าจออยู่คนละ module | 2 |
| `SchemeActivity` | เปิดจากนอกแอป เช่น ลิงก์หรือแอปอื่น | 3 |

| ลำดับ | ช่วง |
|---|---|
| 1 | ทวน + Intent คืออะไร |
| 2 | ภารกิจ 1 — ส่งข้อมูลไปหน้ารายละเอียด |
| 3 | ภารกิจ 2 — เปิดข้าม module ผ่าน Router |
| 4 | ภารกิจ 3 — เปิดจากลิงก์นอกแอป |
| 5 | สรุป |

## ทวน + Intent คืออะไร

**พูด:** Activity สองตัวไม่ได้เรียกกันตรง ๆ เราส่ง **Intent** ซึ่งเป็นจดหมายถึงระบบ Android ว่า "ขอเปิดหน้าจอนี้" แล้วระบบเป็นคนสร้างหน้าจอนั้นให้ ข้อมูลที่อยากส่งไปด้วยแนบไว้ในจดหมาย เรียกว่า **extra**

เปิด `CoffeeMenuActivity` ให้ดู `companion object { fun newInstance(...) }` ที่มีอยู่แล้ว และ `CoffeeRoute` ที่เรียกใช้มัน

## ภารกิจ 1 — ส่งข้อมูลไปหน้ารายละเอียด

**เล่า:** กดที่ชื่อเมนูแล้วให้เปิดหน้ารายละเอียด โดยส่งชื่อเมนูไปด้วย หน้า `CoffeeDetailActivity` มีให้แล้วแต่ยังรับข้อมูลไม่ได้

**โจทย์**

1. ใน `CoffeeDetailActivity.kt`
   - เพิ่ม `companion object` ที่มี `private const val EXTRA_COFFEE_NAME` และ `fun newInstance(context: Context, coffeeName: String): Intent` ซึ่งใส่ชื่อเมนูลงใน extra
   - ใน `setUpViews()` อ่าน extra นั้นมาแสดงที่ `tvCoffeeName`
2. ใน `CoffeeMenuActivity.kt`
   - เก็บชื่อเมนูล่าสุดไว้ในตัวแปร `currentCoffeeName` ตอนโหลดสำเร็จ
   - เมื่อกด `tvResult` ให้เปิดหน้ารายละเอียดด้วยชื่อนั้น

ใบ้: ใส่ด้วย `putExtra(key, value)` อ่านด้วย `intent.getStringExtra(key)`

**เช็กว่าผ่าน:** โหลดเมนู → กดที่ชื่อเมนู → หน้าใหม่แสดงชื่อเดียวกัน → กด Back แล้วกลับมาหน้าเดิมโดยเมนูยังอยู่

**เฉลย** — `CoffeeDetailActivity.kt`

```kotlin
override fun setUpViews() {
    binding.tvCoffeeName.text = intent.getStringExtra(EXTRA_COFFEE_NAME).orEmpty()
}

companion object {
    private const val EXTRA_COFFEE_NAME = "EXTRA_COFFEE_NAME"

    fun newInstance(context: Context, coffeeName: String): Intent {
        return Intent(context, CoffeeDetailActivity::class.java).apply {
            putExtra(EXTRA_COFFEE_NAME, coffeeName)
        }
    }
}
```

`CoffeeMenuActivity.kt` (ส่วนที่เพิ่ม)

```kotlin
private var currentCoffeeName: String? = null

// ใน setUpViews()
binding.tvResult.singleClick {
    currentCoffeeName?.let { openDetail(it) }
}

// ใน onSuccess ของ recommendedCoffeeResult
currentCoffeeName = it.name

private fun openDetail(coffeeName: String) {
    startActivity(CoffeeDetailActivity.newInstance(this, coffeeName))
}
```

**อธิบายหลังเฉลย**

- **ทำไมต้อง `newInstance()`** — หน้าจอปลายทางเป็นคนกำหนดเองว่าต้องการข้อมูลอะไร ชื่อ key เป็น private คนเรียกจึงส่งผิด key หรือส่งไม่ครบไม่ได้
- **Back stack** — หน้าใหม่ถูกวางซ้อนบนหน้าเดิม กด Back คือหยิบหน้าบนสุดออก ให้ดู Logcat `tag:Lifecycle`: หน้าเมนูแค่ `onPause`/`onStop` ไม่ได้ `onDestroy`
- **ส่งผลกลับ** — โค้ดจริงบางหน้าเปิดอีกหน้าเพื่อรอผล ด้วย `startActivityForResult` และรับที่ `onActivityResult` ไม่ได้ฝึกใน session นี้ แต่ให้รู้ว่ามี

## ภารกิจ 2 — เปิดข้าม module ผ่าน Router

**เล่า (วาดบนกระดาน):** ปุ่ม "ดูเมนูขายดี" อยู่ใน `MainActivity` ของ module `app` และต้องเปิด `CoffeeDetailActivity` ของ module `coffee`
ในแอปนี้ `app` เรียกตรง ๆ ได้ แต่ในโปรเจกต์ขนาดใหญ่มี feature หลายสิบตัวที่ต้องเปิดหน้าจอของกันและกัน ถ้าทุกตัวพึ่งกันตรง ๆ จะพันกันและ build ช้า โปรเจกต์นี้จึงให้ทุกคนคุยผ่าน **interface ที่อยู่ใน `core`**:

```
core:    interface CoffeeRouter            ← ทุก module รู้จัก
coffee:  class CoffeeRoute : CoffeeRouter  ← ตัวจริง รู้จัก Activity ของตัวเอง
app:     factory<CoffeeRouter> { CoffeeRoute() }   ← บรรทัดที่ผูกสองอย่างเข้าด้วยกัน
```

**โจทย์**

1. ใน `core/router/CoffeeRouter.kt` เพิ่ม `fun onCoffeeDetail(context: Context, coffeeName: String)`
2. ใน `coffee/route/CoffeeRoute.kt` override ให้เปิด `CoffeeDetailActivity` ด้วย `newInstance`
3. ใน `app/ui/MainActivity.kt` เมื่อกด `btnBestSeller` ให้เรียก `coffeeRouter.onCoffeeDetail(this, BEST_SELLER)`

**เช็กว่าผ่าน:** หน้าแรก → กด "ดูเมนูขายดี" → หน้ารายละเอียดแสดง "ลาเต้"

**เฉลย**

```kotlin
// CoffeeRoute.kt
override fun onCoffeeDetail(context: Context, coffeeName: String) {
    context.startActivity(CoffeeDetailActivity.newInstance(context, coffeeName))
}

// MainActivity.kt
binding.btnBestSeller.singleClick {
    coffeeRouter.onCoffeeDetail(this, BEST_SELLER)
}
```

**อธิบายหลังเฉลย**

- **`by inject()`** — `MainActivity` ขอ `CoffeeRouter` จาก Koin และได้ `CoffeeRoute` มา โดยไม่ต้อง import class ใดจาก module `coffee` เลย ให้ผู้เรียนดู import ของ `MainActivity` เพื่อยืนยัน
- **บรรทัดผูก** — เปิด `app/di/AppModule.kt` ชี้บรรทัด `factory<CoffeeRouter> { CoffeeRoute() }` ถ้าเพิ่ม Router ตัวใหม่แล้วลืมบรรทัดนี้ จะเจอ `No definition found` แบบเดียวกับ session 2
- **อีกแบบที่พบได้** — บางโปรเจกต์วาง Router ไว้ใน module แยก และสร้าง Intent จากชื่อ class ที่เป็น string แนวคิดเหมือนกัน คือผู้เรียกรู้จักแค่ interface

## ภารกิจ 3 — เปิดจากลิงก์นอกแอป

**เล่า:** ผู้ใช้กดลิงก์ในแอปอื่น หรือ notification แล้วต้องเข้ามาที่หน้าจอในแอปเราโดยตรง เรียกว่า **deep link** โปรเจกต์นี้รวมการรับลิงก์ทั้งหมดไว้ที่ Activity ตัวเดียวชื่อ `SchemeActivity` ซึ่งไม่มีหน้าตา มันทำ 4 อย่าง: รับลิงก์ → ตัดสินใจว่าไปหน้าไหน → ส่งต่อ → ปิดตัวเอง

ลิงก์ที่เราจะรองรับ:

| ลิงก์ | ไปที่ |
|---|---|
| `coffeelearning://open.app/detail?name=Mocha` | หน้ารายละเอียดของ Mocha |
| `coffeelearning://open.app` (หรือ path อื่น) | หน้าร้านกาแฟ |

**โจทย์**

1. ใน `app/src/main/AndroidManifest.xml` ประกาศ Activity ตรงตำแหน่ง TODO:

```xml
<activity
    android:name=".scheme.SchemeActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data android:scheme="coffeelearning" />
        <data android:host="open.app" />
    </intent-filter>
</activity>
```

2. สร้างไฟล์ใหม่ `app/src/main/java/com/learning/app/scheme/SchemeActivity.kt` จากโครงนี้ แล้วเติม `handlerDeepLink()`:

```kotlin
class SchemeActivity : AppCompatActivity() {

    private val coffeeRouter: CoffeeRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handlerDeepLink()
    }

    private fun handlerDeepLink() {
        // 1. อ่านลิงก์จาก intent?.data ถ้าไม่มีให้ finish()
        // 2. ถ้า path เป็น "/detail" และมี query "name" → เปิดหน้ารายละเอียดผ่าน coffeeRouter
        // 3. กรณีอื่น → เปิดหน้าร้านกาแฟผ่าน coffeeRouter
        // 4. finish()
    }
}
```

ใบ้: `data.path` คืน path, `data.getQueryParameter("name")` คืนค่าของ query

**เช็กว่าผ่าน:** ปิดแอปก่อน แล้วรันคำสั่งนี้ในแถบ Terminal ของ Android Studio

```
adb shell am start -a android.intent.action.VIEW -d "coffeelearning://open.app/detail?name=Mocha"
```

แอปเปิดขึ้นมาที่หน้ารายละเอียดแสดง "Mocha" จากนั้นลองลิงก์ `coffeelearning://open.app` ต้องไปหน้าร้านกาแฟ
(ถ้า Terminal ไม่รู้จัก `adb` บน macOS ให้ใช้ `~/Library/Android/sdk/platform-tools/adb` แทน)

**เฉลย**

```kotlin
private fun handlerDeepLink() {
    val data: Uri? = intent?.data
    data ?: return finish()

    val coffeeName = data.getQueryParameter(QUERY_NAME)
    if (data.path == PATH_DETAIL && !coffeeName.isNullOrBlank()) {
        coffeeRouter.onCoffeeDetail(this, coffeeName)
    } else {
        coffeeRouter.onCoffeeMenu(this)
    }
    finish()
}

companion object {
    const val PATH_DETAIL = "/detail"
    const val QUERY_NAME = "name"
}
```

**อธิบายหลังเฉลย**

- **`intent-filter`** — ประกาศต่อระบบว่า "ลิงก์หน้าตาแบบนี้ ส่งมาที่ Activity นี้" ระบบเป็นคนเปิดให้ เราไม่ได้เรียกเอง
- **`android:exported="true"`** — อนุญาตให้ของนอกแอปเปิด Activity นี้ได้ เทียบกับ Activity ใน `coffee` ที่เป็น `false`
- **เทียบกับ `MainActivity`** — `intent-filter` ของ `MainActivity` (`MAIN` + `LAUNCHER`) ก็คือการประกาศแบบเดียวกัน ว่า "การกดไอคอนแอป ส่งมาที่นี่"
- **ทำไมรวมไว้ที่เดียว** — การตรวจว่าล็อกอินหรือยัง ลิงก์ถูกต้องไหม ทำได้ในจุดเดียวก่อนส่งต่อ ในแอปขนาดใหญ่ Activity แบบนี้จึงยาวกว่านี้มาก แต่โครงเดียวกัน
- **ข้อมูลจากลิงก์เชื่อถือไม่ได้** — ใครก็สร้างลิงก์ได้ จึงต้องตรวจค่าก่อนใช้เสมอ เหมือนที่เราตรวจ `isNullOrBlank`

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| คำสั่ง adb ตอบ `unable to resolve Intent` | Manifest ยังไม่ถูกติดตั้ง หรือ scheme/host สะกดไม่ตรง | Run แอปใหม่หนึ่งครั้งแล้วเทียบตัวสะกด |
| แอปเปิดแล้วค้างที่หน้าขาว | ลืม `finish()` | เพิ่มที่ท้าย `handlerDeepLink()` |
| แอปเด้ง `ActivityNotFoundException` | สร้างไฟล์ผิด package | package ต้องเป็น `com.learning.app.scheme` |

## สรุป session 4

ถามผู้เรียนทีละสถานการณ์ ว่าต้องใช้แบบไหน:

1. หน้าตั้งค่าเปิดหน้ายืนยันอีเมลที่อยู่ใน module เดียวกัน → `newInstance()`
2. หน้าตั้งค่าต้องพากลับไปหน้าหลักที่อยู่อีก module → Router
3. กด notification แล้วต้องเข้าหน้าโปรโมชัน → deep link ผ่าน `SchemeActivity`

---

# Session 5 — รายการด้วย RecyclerView

**เป้าหมาย:** ผู้เรียนต่อ API หนึ่งเส้นเข้ากับหน้าจอได้เองโดยทำตามแบบของ session 3 และแสดงข้อมูลเป็นรายการได้

| ลำดับ | ช่วง |
|---|---|
| 1 | ทวน |
| 2 | ภารกิจ 1 — UseCase ของเมนูทั้งหมด |
| 3 | ภารกิจ 2 — ViewModel |
| 4 | ภารกิจ 3 — หน้าตาของหนึ่งแถว และ Adapter |
| 5 | ภารกิจ 4 — ต่อรายการเข้ากับหน้าจอ |
| 6 | สรุป |

ภารกิจ 1 และ 2 เป็นการทำซ้ำสิ่งที่ทำใน session 3 โดยตั้งใจ **ให้ผู้เรียนเปิดไฟล์ของ session 3 ดูเป็นแบบได้ แต่คุณไม่อธิบายซ้ำ** นี่คือวิธีทำงานจริง: หาโค้ดที่คล้ายกันแล้วทำตาม

## ภารกิจ 1 — UseCase ของเมนูทั้งหมด

**โจทย์** — Api และ Repository ของ `v1/coffee/menu` มีให้แล้ว (`GetCoffeeMenuApi.kt`, `GetCoffeeMenuRepository.kt`)

1. สร้าง `apilayer/.../usecase/coffee/GetCoffeeMenuUseCase.kt` ตามแบบ `GetRecommendedCoffeeUseCase`
2. ลงทะเบียนใน `ApiLayerModule.kt`

**เช็กว่าผ่าน:** Build ผ่าน — เฉลยอยู่ที่ `solutions/session-5/apilayer/`

## ภารกิจ 2 — ViewModel

**โจทย์**

1. ใน `CoffeeMenuViewModel.kt` เพิ่ม `GetCoffeeMenuUseCase` เป็น parameter ตัวที่สอง, เพิ่ม LiveData คู่ `_coffeeMenuResult` / `coffeeMenuResult` และ `fun getCoffeeMenu(params: GetCoffeeMenuRequest)`
2. แก้ `CoffeeModule.kt` ให้ `get()` ครบสองตัว

**เช็กว่าผ่าน:** Build ผ่าน และแอปยังเปิดหน้าร้านกาแฟได้ไม่เด้ง

**อธิบายหลังเฉลย:** ViewModel หนึ่งตัวมี LiveData ได้หลายคู่ คู่ละหนึ่ง API หน้าจอเดียวจึงมีหลายส่วนที่โหลดแยกกันได้

## ภารกิจ 3 — หน้าตาของหนึ่งแถว และ Adapter

**เล่า:** รายการที่มี 6 หรือ 6,000 แถวใช้ View เดียวกันคือ **RecyclerView** มันสร้าง View เท่าที่พอเต็มจอ แล้ว **นำกลับมาใช้ซ้ำ** เมื่อเลื่อน เราต้องบอกมัน 3 อย่างผ่าน class ที่เรียกว่า **Adapter**:

1. มีกี่แถว
2. หนึ่งแถวหน้าตาอย่างไร (layout ของ item)
3. แถวที่ n ใส่ข้อมูลอะไร

**โจทย์**

1. สร้าง `coffee/src/main/res/layout/item_coffee_menu.xml` มี `TextView` สองตัว: `tvItemName` ชิดซ้าย และ `tvItemPrice` ชิดขวา
2. สร้าง `coffee/.../ui/menu/CoffeeMenuAdapter.kt` จากโครงนี้ แล้วเติมส่วนที่เป็น `TODO`

```kotlin
class CoffeeMenuAdapter(
    private var items: List<CoffeeMenuItem> = emptyList(),
    private val onItemClick: (CoffeeMenuItem) -> Unit = {}
) : RecyclerView.Adapter<CoffeeMenuAdapter.CoffeeMenuViewHolder>() {

    inner class CoffeeMenuViewHolder(
        private val binding: ItemCoffeeMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: CoffeeMenuItem) {
            // TODO: แสดงชื่อและราคา (ใช้ R.string.coffee_price) และเมื่อกดทั้งแถวให้เรียก onItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeMenuViewHolder {
        val binding = ItemCoffeeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeMenuViewHolder(binding)
    }

    override fun getItemCount(): Int = TODO("จำนวนแถว")

    override fun onBindViewHolder(holder: CoffeeMenuViewHolder, position: Int) {
        // TODO: ส่งข้อมูลของแถวที่ position ให้ holder
    }

    fun updateItems(newItems: List<CoffeeMenuItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
```

**เช็กว่าผ่าน:** Build ผ่าน

**เฉลย**

```kotlin
fun setData(item: CoffeeMenuItem) {
    binding.tvItemName.text = item.name.orEmpty()
    binding.tvItemPrice.text = binding.root.context.getString(R.string.coffee_price, item.price ?: 0)
    binding.root.singleClick { onItemClick(item) }
}

override fun getItemCount(): Int = items.size

override fun onBindViewHolder(holder: CoffeeMenuViewHolder, position: Int) {
    holder.setData(items[position])
}
```

**อธิบายหลังเฉลย**

- **ViewHolder** — ตัวถือ View ของหนึ่งแถว ถูกสร้างไม่กี่ตัวแล้ววนใช้ `onCreateViewHolder` จึงถูกเรียกไม่กี่ครั้ง ส่วน `onBindViewHolder` ถูกเรียกทุกครั้งที่แถวเลื่อนเข้ามาในจอ
- **ผลของการใช้ซ้ำ** — ใน `setData` ต้องตั้งค่า **ทุกอย่าง** ที่แถวแสดง ทุกครั้ง ถ้าตั้งสีแดงเฉพาะบางแถวแล้วไม่ตั้งกลับ แถวอื่นที่ได้ View ตัวเดิมไปจะแดงตาม นี่คือ bug ที่พบบ่อยที่สุดของรายการ
- **`onItemClick` เป็น lambda** — Adapter ไม่รู้ว่ากดแล้วต้องทำอะไร มันแค่แจ้งกลับ คนตัดสินใจคือ Activity
- **`binding.root.context.getString`** — Adapter ไม่ใช่ Activity จึงไม่มี `getString` ของตัวเอง ต้องยืม context จาก View

## ภารกิจ 4 — ต่อรายการเข้ากับหน้าจอ

**โจทย์**

1. ใน `activity_coffee_menu.xml` เพิ่มต่อจาก `tvResult`:
   - `TextView` id `tvMenuHeader` ข้อความ `@string/coffee_menu_header`
   - `androidx.recyclerview.widget.RecyclerView` id `rvMenu` อยู่ใต้หัวข้อ สูง `0dp` และผูกขอบล่างกับ parent เพื่อกินพื้นที่ที่เหลือ
2. ใน `CoffeeMenuActivity.kt`
   - สร้าง adapter: `private val coffeeMenuAdapter by lazy { CoffeeMenuAdapter(onItemClick = { openDetail(it.name.orEmpty()) }) }`
   - ใน `setUpViews()` ตั้ง `binding.rvMenu.layoutManager = LinearLayoutManager(this)` และ `binding.rvMenu.adapter = coffeeMenuAdapter` แล้วสั่งโหลดด้วย `getCoffeeMenu(GetCoffeeMenuRequest())`
   - ใน `observeViewModel()` observe `coffeeMenuResult` แล้วเรียก `coffeeMenuAdapter.updateItems(it.items.orEmpty())`

**เช็กว่าผ่าน:** เข้าหน้าร้านกาแฟแล้วเห็นวงโหลด ตามด้วยรายการ 6 เมนู กดแถวใดก็ได้แล้วเปิดหน้ารายละเอียดของเมนูนั้น

เฉลยเต็มอยู่ที่ `solutions/session-5/coffee/`

**อธิบายหลังเฉลย**

- **`LinearLayoutManager`** — บอกว่าเรียงแถวเป็นแนวตั้งทีละแถว ถ้าลืมบรรทัดนี้รายการจะว่างเปล่าโดยไม่มี error
- **โหลดตั้งแต่เปิดหน้าจอ** — เรียกใน `setUpViews()` ต่างจากเมนูแนะนำที่รอให้กดปุ่ม
- **ได้ใช้ของเดิม** — `openDetail` ที่เขียนไว้ใน session 4 ถูกใช้ซ้ำทันที

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| รายการว่าง ไม่มี error | ลืมตั้ง `layoutManager` หรือ `adapter` หรือไม่ได้เรียก `updateItems` | ตรวจสามบรรทัดนี้ |
| รายการว่าง และ Logcat แสดง JSON ถูกต้อง | `rvMenu` สูง `0dp` แต่ไม่ได้ผูกขอบล่าง | เพิ่ม `layout_constraintBottom_toBottomOf="parent"` |
| แอปเด้ง `Could not create instance` | ลืมแก้ `CoffeeModule` ในภารกิจ 2 | `get()` สองตัว |

## สรุป session 5

ถาม: ถ้าต้องเพิ่มรูปเล็ก ๆ หน้าชื่อเมนูในทุกแถว ต้องแก้ไฟล์ไหนบ้าง → `item_coffee_menu.xml` และ `setData` ใน Adapter (และ Response ถ้า API ส่ง URL รูปมา)

---

# Session 6 — Feature flag, unit test และทำ feature เองทั้งเส้น

**เป้าหมาย:** ผู้เรียนเพิ่ม API หนึ่งเส้นตั้งแต่ชั้น Api จนถึงหน้าจอได้เองโดยไม่มีโครงให้ และรู้จักเครื่องมือประจำวันของนักพัฒนา

| ลำดับ | ช่วง |
|---|---|
| 1 | ทวน |
| 2 | ภารกิจ 1 — ซ่อน feature หลัง flag |
| 3 | ภารกิจ 2 — unit test ของ ViewModel |
| 4 | ภารกิจ 3 — ขนมแนะนำ ทำเองทั้งเส้น |
| 5 | ทบทวนทั้งโปรเจกต์ |

## ภารกิจ 1 — ซ่อน feature หลัง flag

**เล่า:** งานที่ยังทำไม่เสร็จก็ถูก merge เข้าโค้ดหลักได้ ถ้าซ่อนไว้หลัง **feature flag** แล้วเปิดให้ผู้ใช้เห็นเมื่อพร้อม โดยไม่ต้องออกแอปเวอร์ชันใหม่

**โจทย์**

1. ใน `activity_coffee_menu.xml` เพิ่มระหว่าง `tvResult` กับ `tvMenuHeader`:
   - `Button` id `btnDessert` ข้อความ `@string/coffee_dessert_button` และ `android:visibility="gone"`
   - `TextView` id `tvDessert`
   - แก้ `tvMenuHeader` ให้ไปอยู่ใต้ `tvDessert`
2. ใน `CoffeeMenuActivity.kt` ขอ `FeatureFlag` ด้วย `by inject()` แล้วใน `setUpViews()` ตั้ง `binding.btnDessert.isVisible = featureFlag.isEnabled(FlagConstants.DESSERT_RECOMMEND)`
3. รันแอป: ปุ่มยังไม่ขึ้น จากนั้นเปิด flag ใน `app/public_impl/FeatureFlagImpl.kt` โดยเพิ่ม `FlagConstants.DESSERT_RECOMMEND` ลงใน set แล้วรันอีกครั้ง

**เช็กว่าผ่าน:** ปุ่ม "แนะนำขนมด้วย" ปรากฏหลังเปิด flag (กดแล้วยังไม่มีอะไรเกิด)

**อธิบายหลังเฉลย**

- โค้ดของ feature ถามผ่าน interface `FeatureFlag` ใน `core` เท่านั้น ไม่รู้ว่าค่ามาจากไหน
- ในแอปที่ใช้งานจริง ค่าของ flag มักมาจาก server เพื่อเปิดหรือปิดได้โดยไม่ต้องออกแอปใหม่

## ภารกิจ 2 — unit test ของ ViewModel

**เล่า:** test ของโปรเจกต์นี้ mock ที่ชั้น **Repository** แล้วใช้ UseCase และ ViewModel ตัวจริง เพื่อตรวจว่า ViewModel แปลงผลเป็นสถานะหน้าจอถูกต้อง

**โจทย์**

1. คัดลอกไฟล์ `solutions/session-6/coffee/src/test/.../CoffeeMenuViewModelTest.kt` (ที่ root ของ repo) มาไว้ที่ path เดียวกันใน `workshop/`
2. ไฟล์นั้นสร้าง ViewModel ด้วย UseCase สามตัว ซึ่งตัวที่สามยังไม่มี **ลบบรรทัดที่เกี่ยวกับ dessert ออก** (import สองบรรทัด, ตัวแปร mock หนึ่งตัว, argument หนึ่งตัว) ให้ compile ผ่าน
3. ลบ test `get recommended coffee fail` ทิ้ง แล้ว **เขียนขึ้นใหม่เอง** โดยดู test ข้อ success เป็นแบบ: ให้ Repository คืน `Result.Error(Failure.NetworkConnection())` แล้วตรวจว่าสถานะที่สองเป็น `Response.Error`
4. รัน test ด้วยปุ่มสีเขียวข้างชื่อ class หรือ `./gradlew :coffee:testDebugUnitTest`

**เช็กว่าผ่าน:** test ผ่านทั้ง 2 ข้อ จากนั้นแก้ `"ลาเต้"` ใน assert เป็นคำอื่นแล้วรันอีกครั้ง เพื่อให้เห็นหน้าตาของ test ที่ล้ม

**เฉลย**

```kotlin
@Test
fun `get recommended coffee fail`() {
    val request = GetRecommendedCoffeeRequest()
    whenever(getRecommendedCoffeeRepository.getRecommendedCoffee(request))
        .thenReturn(Result.Error(Failure.NetworkConnection()))

    val states = viewModel.recommendedCoffeeResult.collectStates(count = 2) {
        viewModel.getRecommendedCoffee(request)
    }

    assertTrue(states[0] is Response.Loading)
    assertTrue((states[1] as Response.Error).failure is Failure.NetworkConnection)
}
```

**อธิบายหลังเฉลย**

- **`mock<...>()` และ `whenever(...).thenReturn(...)`** — สร้างของปลอมและกำหนดว่าถูกเรียกแล้วตอบอะไร นี่คือประโยชน์ที่จับต้องได้ของการรับ dependency ทาง constructor
- **`InstantTaskExecutorRule` และ `Dispatchers.setMain`** — บนเครื่องที่รัน test ไม่มี main thread ของ Android สองบรรทัดนี้ทำของแทนให้ จำเป็นทุกครั้งที่ test ViewModel
- **test รันบนเครื่องคอมพิวเตอร์ ไม่ใช่ e`mul`ator** จึงเร็ว และเป็นเหตุผลที่ logic ควรอยู่ใน ViewModel ไม่ใช่ Activity

## ภารกิจ 3 — ขนมแนะนำ ทำเองทั้งเส้น

**เล่า:** นี่คืองานแบบที่จะได้รับในการทำงาน มีแค่สเปก ไม่มีโครงให้ ใช้โค้ดของเมนูแนะนำเป็นแบบ

**สเปก**

- API: `POST v1/dessert/recommended` รหัสฟอร์ม (`ApiCode`) คือ `DESSERT0101` ไม่มี field ใน request
- `FormData` ของ response: `{ "Name": "ครัวซองต์เนยสด", "Price": 85 }` ห่ออยู่ในซองแบบเดียวกับ API อื่น (mock มีให้แล้วที่ `app/src/main/assets/apiData/dessert/recommended.json`)
- กด `btnDessert` แล้วแสดงชื่อและราคาที่ `tvDessert` ในรูปแบบเดียวกับเมนูแนะนำ

**เช็กลิสต์ (ส่งให้ผู้เรียนใช้ไล่ทำทีละข้อ)**

| # | ไฟล์ | ทำอะไร |
|---|---|---|
| 1 | `apilayer/repository/dessert/GetRecommendedDessertApi.kt` (ใหม่) | Request และ Response ที่สืบทอด `FormData`, interface Api ที่รับ `BaseRequest<FormData>` และคืน `Call<BaseResponse<...>>`, ค่าคงที่ `API_CODE` |
| 2 | `apilayer/repository/dessert/GetRecommendedDessertRepository.kt` (ใหม่) | Repository ที่ห่อด้วย `getBaseData` แล้วเรียกผ่าน `requestData` |
| 3 | `apilayer/usecase/dessert/GetRecommendedDessertUseCase.kt` (ใหม่) | UseCase |
| 4 | `apilayer/di/ApiLayerModule.kt` | ลงทะเบียน 3 บรรทัด |
| 5 | `CoffeeMenuViewModel.kt` | parameter ตัวที่สาม, LiveData คู่ใหม่, function ใหม่ |
| 6 | `CoffeeModule.kt` | `get()` สามตัว |
| 7 | `CoffeeMenuActivity.kt` | ปุ่มเรียก ViewModel, observe แล้วแสดงผล |
| 8 | `CoffeeMenuViewModelTest.kt` | แก้การสร้าง ViewModel ให้ครบสามตัว |

**เช็กว่าผ่าน:** กด "แนะนำขนมด้วย" แล้วเห็น "ครัวซองต์เนยสด · 85 บาท" และ unit test ยังผ่าน

เฉลยทั้งหมดอยู่ที่ `solutions/session-6/`

**อธิบายหลังเฉลย**

- **ข้อ 8 คือบทเรียน** — เปลี่ยน constructor ของ ViewModel แล้ว test พังทันที ถ้าไม่มี test เราจะรู้ก็ต่อเมื่อแอปเด้ง
- **ลำดับที่แนะนำ** — ทำจากชั้นในออกมาชั้นนอก (Api → Repository → UseCase → ViewModel → Activity) เพราะ build ผ่านได้ทุกขั้น
- ให้ผู้เรียนที่ทำไม่ทันทำต่อเป็นการบ้าน อย่าทับเฉลยในภารกิจนี้ เพราะการทำเองคือจุดประสงค์

**จุดที่คนมักติด**

| อาการ | สาเหตุ | แก้ |
|---|---|---|
| dialog error ขึ้นทันทีที่กด ข้อความ `Please check your Internet connection` | path ใน `@POST` สะกดไม่ตรงกับ mock จึงยิงออก network จริง | ต้องเป็น `v1/dessert/recommended` |
| compile ไม่ผ่านที่ Repository เรื่องชนิดไม่ตรง | Request หรือ Response ลืมสืบทอด `FormData` หรือ Api ไม่ได้คืน `Call<BaseResponse<...>>` | เทียบกับ `GetRecommendedCoffeeApi.kt` ทีละบรรทัด |
| ได้ชื่อว่างและราคา 0 | `@SerializedName` ไม่ตรงกับ JSON | ต้องเป็น `Name` และ `Price` ตัวพิมพ์ใหญ่นำ |
| แอปเด้ง `No definition found for class:'...GetRecommendedDessertApi'` | ลงทะเบียนไม่ครบสามบรรทัด | ดูชุดของ coffee เป็นแบบ |

## ทบทวนทั้งโปรเจกต์

**สาธิต flavor:** เปลี่ยน Build Variants ของ `app` เป็น `productionDebug` แล้วรัน กดปุ่มแนะนำเมนูจะได้ dialog `Please check your Internet connection` เพราะ flavor นี้ไม่ใช้ mock และ URL ไม่มีอยู่จริง
อธิบาย: flavor คือแอปเดียวกันที่ชี้ไปคนละ environment ค่าต่าง ๆ อยู่ใน `app/build.gradle` และอ่านผ่าน `BuildConfig` แอปทั่วไปมักมีหลาย flavor แยกตาม environment — **เปลี่ยนกลับเป็น `sitDebug` ก่อนไปต่อ**

**ทบทวนว่าแต่ละชิ้นอยู่ที่ไหน:** เปิดโปรเจกต์ workshop แล้วชี้ทีละแถว

| สิ่งที่ทำในหลักสูตร | อยู่ที่ไหน |
|---|---|
| `CoffeeMenuActivity` กับ `getViewBinding` / `setUpViews` / `observeViewModel` | `<feature>/ui/.../*Activity.kt` |
| `CoffeeMenuViewModel` กับคู่ `_xxxResult` / `xxxResult` | `<feature>/ui/.../*ViewModel.kt` |
| `BaseCoffeeActivity` กับ `getDi()` | `<feature>/common/Base*Activity.kt` |
| `viewModel { CoffeeMenuViewModel(get(), ...) }` | `<feature>/di/*Module.kt` |
| ชุด Api / Repository / UseCase | `apilayer/repository/<กลุ่ม>/` และ `apilayer/usecase/<กลุ่ม>/` |
| บรรทัดลงทะเบียนสามบรรทัด | `apilayer/di/ApiLayerModule.kt` |
| `CoffeeRouter` / `CoffeeRoute` / บรรทัดผูก | `core/router/`, `<feature>/route/`, `app/.../di/AppModule.kt` |
| `SchemeActivity` | `app/.../scheme/SchemeActivity.kt` |
| `FeatureFlag` | `core/.../featureflag/` |
| `MockApiInterceptor` + `assets/apiData/` | `app/.../public_impl/` และ `app/src/main/assets/apiData/` |
| `CoffeeMenuViewModelTest` | `<feature>/src/test/` |

**สิ่งที่โปรเจกต์ขนาดใหญ่มักมีเพิ่ม** (บอกไว้ให้ไม่ตกใจ ไม่ต้องอธิบายลึก):

- ซองของ API มีข้อมูลมากกว่านี้ และการแยกชนิดของ error ละเอียดกว่านี้ แต่วิธีใช้จากฝั่ง Repository เหมือนกับที่ทำใน workshop
- class แม่ของ Activity ทำงานเพิ่มหลายอย่าง เช่น จัดการ toolbar ของหน้าจอ
- บาง layout ห่อด้วยแท็ก `<layout>` (DataBinding) และบางหน้าจอเป็น Fragment

**ปิดหลักสูตรด้วยขั้นตอนรับงานใหม่:**

1. หา Activity ของหน้าจอที่เกี่ยวข้อง โดยค้นจากข้อความบนจอใน `strings.xml` แล้วค้นชื่อ string ต่อ
2. จาก Activity ไล่ไป ViewModel → UseCase → Repository → Api
3. ถ้าต้องเพิ่ม API ทำตามเช็กลิสต์ 8 ข้อของภารกิจ 3
4. ถ้าแอปเด้งตอนเปิดหน้าจอ อ่านบรรทัด `Caused by` ก่อนเสมอ ส่วนใหญ่คือ Koin
5. ถ้าข้อมูลบนจอผิด เปิด Logcat ดู request และ response ก่อนแก้โค้ด

---

## หมายเหตุสำหรับการใช้แบบอื่น

- **ผู้เรียนเคยเขียน Android แล้ว** — ถามตอนเริ่ม ถ้าใช่ ให้เดิน session 1 กับ 2 เร็วขึ้นโดยข้ามช่วง "เล่า" ที่ผู้เรียนรู้แล้ว แต่ยังให้ทำภารกิจครบ เพราะ session ถัดไปต้องใช้โค้ดนั้น
- **ถ้าต้องเลือกทำบางส่วน** — สอน session 1–4 ให้ครบก่อน session 5–6 ทำทีหลังได้
- **คนเป็นผู้สอนหน้าห้อง** — บทสอนของแต่ละ session ใช้ได้เหมือนกัน
- **หัวข้อที่ยังไม่ได้สอน** — Fragment, การรับผลกลับจากหน้าจออื่น และ DataBinding ถ้าผู้เรียนถาม ให้บอกว่าอยู่นอกขอบเขตของหลักสูตรนี้

เมื่อแก้โจทย์หรือเฉลยในไฟล์นี้ ให้แก้ไฟล์ใน `solutions/` ให้ตรงกัน และตรวจตามขั้นตอนท้าย [agent.md](./agent.md)
