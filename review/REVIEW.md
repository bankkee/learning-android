# ขอ review: หลักสูตรสอน Android แบบโปรเจกต์หลาย module

ชุดนี้คือเฉลยฉบับสมบูรณ์ของหลักสูตรสอน Android สำหรับคนที่ไม่เคยเขียน Android มาก่อน (workshop 6 session)
ผู้สอนคือ Claude: ผู้เรียนเปิดโปรเจกต์ด้วย Claude Code แล้ว Claude ให้โจทย์ ตรวจงาน และอธิบายตามบทใน `learning.md`
เป้าหมายของหลักสูตรคือให้ผู้เรียนรับงานเล็ก ๆ ในโปรเจกต์ Android หลาย module ได้ จึงอยากให้คนที่มีประสบการณ์ช่วยดูว่าสิ่งที่สอน **ถูกต้องและใช้ได้กับงานจริง**

## ในชุดนี้มีอะไร

| ไฟล์ / โฟลเดอร์ | คืออะไร |
|---|---|
| `coffee-learning-solution/` | โปรเจกต์ Android ในสภาพที่ผู้เรียนทำครบทุก session แล้ว build และรันได้ |
| `learning.md` | บทสอนที่ Claude ใช้ดำเนินการสอนผู้เรียนตัวต่อตัวในแชต: โจทย์ เฉลย และคำอธิบายของทุกภารกิจ |
| `agent.md` | โครงสร้างและ convention ของโปรเจกต์ รวมถึงตารางสิ่งที่ตั้งใจทำให้ง่าย |

ข้อมูลทั้งหมดในโปรเจกต์เป็นของสมมุติ

## วิธีรัน

1. เปิดโฟลเดอร์ `coffee-learning-solution/` ด้วย Android Studio แล้วรอ Gradle sync
2. เลือก Build Variant `sitDebug` (ค่าเริ่มต้น) แล้วกด Run
3. ไม่ต้องมี server: flavor `sit` ตอบ API ด้วย JSON ใน `app/src/main/assets/apiData/`

```
./gradlew assembleSitDebug
./gradlew :coffee:testDebugUnitTest
adb shell am start -a android.intent.action.VIEW -d "coffeelearning://open.app/detail?name=Mocha"
```

## วิธีดูว่าส่วนไหนผู้เรียนเป็นคนเขียน

ค้นคำว่า `ผู้เรียน` ทั้งโปรเจกต์ จะพบ comment 3 รูปแบบ:

| รูปแบบ | ความหมาย |
|---|---|
| `// >>> ผู้เรียนเขียน: Session N · ภารกิจ M` ... `// <<< จบส่วนที่ผู้เรียนเขียน` | ก้อนโค้ดที่ผู้เรียนเขียน |
| `// ผู้เรียนเขียน: Session N · ภารกิจ M` ท้ายบรรทัดหรือเหนือ declaration | บรรทัดหรือ declaration เดียว |
| `// ผู้เรียนสร้างไฟล์นี้เองทั้งไฟล์: ...` ที่หัวไฟล์ | ไฟล์ที่ไม่มีใน starter |

โค้ดที่ไม่มี comment กำกับคือสิ่งที่ starter เตรียมไว้ให้ ผู้เรียนไม่ได้แตะ
comment เหล่านี้มีเฉพาะในชุด review นี้ ไม่อยู่ในโค้ดที่ผู้เรียนได้รับ

| Session | หัวข้อ | ไฟล์ที่ผู้เรียนแตะ |
|---|---|---|
| 1 | Activity, layout, ViewBinding, lifecycle | `activity_coffee_menu.xml`, `CoffeeMenuActivity` |
| 2 | ViewModel, LiveData, Koin | `CoffeeMenuViewModel`, `CoffeeMenuActivity`, `CoffeeModule` |
| 3 | UseCase, Repository, `Response` | `GetRecommendedCoffeeUseCase` (ใหม่), `ApiLayerModule`, `CoffeeMenuViewModel`, `CoffeeModule`, `CoffeeMenuActivity` |
| 4 | Intent, Router, deep link | `CoffeeDetailActivity`, `CoffeeRouter`, `CoffeeRoute`, `MainActivity`, `SchemeActivity` (ใหม่), `AndroidManifest.xml` ของ `app` |
| 5 | RecyclerView | `GetCoffeeMenuUseCase` (ใหม่), `CoffeeMenuAdapter` (ใหม่), `item_coffee_menu.xml` (ใหม่) และไฟล์ชุดเดียวกับ session 3 |
| 6 | Feature flag, unit test, API ใหม่ทั้งเส้น | `FeatureFlagImpl`, `CoffeeMenuViewModelTest` (ใหม่), ชุด dessert ใน `apilayer` 3 ไฟล์ (ใหม่) และไฟล์ชุดเดียวกับ session 3 |

โค้ดบางส่วนถูกเขียนแล้วแทนที่ใน session ถัดไป จึงไม่เหลือในฉบับสุดท้าย เช่น ViewModel ของ session 2 ที่สุ่มจาก list ในเครื่อง ดูได้ใน `learning.md`

## สิ่งที่อยากให้ช่วยดู

เรียงตามความสำคัญ

1. **มีอะไรที่สอนผิด หรือขัดกับแนวปฏิบัติที่ดีไหม** โดยเฉพาะคำอธิบายในหัวข้อ "อธิบายหลังเฉลย" ของ `learning.md` ซึ่ง Claude จะอธิบายให้ผู้เรียนตามนั้น
2. **จุดที่ตั้งใจทำให้ง่าย ยอมรับได้ไหม** รายการเต็มอยู่ท้าย `agent.md` ข้อที่กังวลที่สุดคือ:
   - ซอง `BaseRequest<FormData>` / `BaseResponse` มีแล้วแต่ย่อ: หัวซองมีแค่ `ApiCode` และ `requestData` ตรวจแค่ `ErrorCode`, HTTP 5xx และ network error
   - Router ทั้งหมดอยู่ใน `<feature>/route/`
3. **`CoffeeModule` ใช้รูปแบบ `loadModule` / `unloadModule` แบบ `by lazy`** ควรสอนแบบนี้ต่อไป หรือมีวิธีที่ดีกว่าสำหรับโค้ดใหม่
4. **วิธีเขียน unit test** (`CoffeeMenuViewModelTest`): mock ที่ Repository, ใช้ UseCase กับ ViewModel ตัวจริง แล้วรอค่าจาก LiveData ด้วย `CountDownLatch` อีกแบบคือ test ที่เรียก UseCase ตรง ๆ แบบไหนควรเป็นแบบอย่าง
5. **หัวข้อที่ยังไม่ได้สอน ควรย้ายเข้ามาไหม**: Fragment, `startActivityForResult`, DataBinding (`<layout>`)

## สิ่งที่รู้อยู่แล้ว

- **ยังไม่ได้รันบน emulator หรือเครื่องจริง** ตรวจถึงระดับ build ผ่านและ unit test ผ่าน (JDK 21) พฤติกรรมบนจอ เช่น ระยะขอบกับ status bar และ deep link ยังไม่ได้ยืนยันด้วยตา
- library บางตัวไม่ใช่เวอร์ชันล่าสุด (Koin 2.2.3, Lifecycle 2.4.0, Material 1.2.1) และใช้ของที่ไม่ใช่แนวทางล่าสุด เช่น LiveData และ `notifyDataSetChanged()` โดยตั้งใจ เพราะยังพบมากในโค้ดที่ใช้งานอยู่
- `coffee/build.gradle` ตั้ง `-Dnet.bytebuddy.experimental=true` ให้ unit test เพราะ Mockito 4.2.0 ยังไม่รู้จัก JDK ที่ใหม่กว่า 18
- เมนูแนะนำถูกตั้งให้ล้มทุกครั้งที่ 3 (mock ตอบ HTTP 200 ที่มี `ErrorCode`) เพื่อให้ผู้เรียนเห็นสถานะ Error
