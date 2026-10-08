# บทที่ 01 — Android Studio และส่วนประกอบของแอป

บทสอนนี้เขียนถึง Claude ผู้ดำเนินการสอน วิธีสอนบทเรียนรายหัวข้ออยู่ใน [../README.md](../README.md) อ่านก่อนสอนครั้งแรก

| | |
|---|---|
| ทำไมต้องเรียน | มือใหม่ติดที่เครื่องมือก่อนติดที่แนวคิด: หาไฟล์ไม่เจอ รันไม่เป็น อ่าน error ไม่ออก |
| ผู้เรียน | ผ่านบทที่ 00 แล้ว ยังไม่เคยเปิดโปรเจกต์ Android |
| จังหวะ | ไม่จับเวลา ผู้เรียนกำหนดจังหวะเอง จุดพักที่แนะนำคือหลังขั้นที่ 4 |
| ต้องมีในเครื่อง | Android Studio และ emulator หนึ่งตัว (หรือเครื่องจริงที่ต่อสายไว้) |
| เรียนจบแล้ว | รันแอปได้ บอกได้ว่าไฟล์แต่ละชนิดมีไว้ทำอะไร และหาสาเหตุเบื้องต้นจาก error ของ build กับ Logcat ได้ |
| บทถัดไป | [02 — Layout และ View ทุกประเภท](../02-layout-and-views/lesson.md) |

บทนี้สอนเครื่องมือ ไม่ได้สอนการเขียนโค้ด แบบฝึกส่วนใหญ่จึงเป็นการ **หาและแก้ของที่มีอยู่แล้ว** โค้ด Kotlin ที่ต้องเขียนมีไม่เกินข้อละหนึ่งถึงสองบรรทัด

## โค้ดของบทนี้

โค้ดของบทที่ 01–12 อยู่ในโปรเจกต์ Android เดียวกันคือ [../playground/](../playground/) แต่ละบทมี package ของตัวเอง

```
lessons/playground/
├── settings.gradle, build.gradle          ไฟล์ Gradle ระดับโปรเจกต์
└── app/
    ├── build.gradle                       ไฟล์ Gradle ของ module app
    └── src/
        ├── main/
        │   ├── AndroidManifest.xml
        │   ├── java/com/learning/playground/
        │   │   ├── HomeActivity.kt        หน้าแรกของแอป: สารบัญของทุกบท
        │   │   ├── PlaygroundApp.kt       ของกลาง ไม่ต้องอ่าน
        │   │   └── lesson01/
        │   │       ├── CoffeeShopActivity.kt  หน้าจอของบทนี้
        │   │       ├── Pricing.kt             คำนวณราคา (ขั้นที่ 7)
        │   │       └── Promotion.kt           โปรโมชัน (ขั้นที่ 6)
        │   └── res/
        │       ├── layout/activity_home.xml, activity_coffee_shop.xml
        │       └── values/strings.xml, themes.xml, colors.xml, dimens.xml
        └── test/java/com/learning/playground/lesson01/
            └── Step3Test.kt ... Step7Test.kt    test ที่ใช้ตรวจแบบฝึก
```

โปรเจกต์นี้มีโค้ดของบทอื่นอยู่ด้วย (package `lesson02` และไฟล์ที่ขึ้นต้นด้วย `lesson02_`) บทนี้ไม่ใช้ ถ้าผู้เรียนถาม ให้บอกว่าเป็นของบทถัดไป

เฉลยอยู่ที่ `solutions/lesson-01/` (root ของ repo) ใช้ path เดียวกับ `lessons/playground/`

**ตรวจแบบฝึก** — รันจากในโฟลเดอร์ `lessons/playground/` โดยเปลี่ยนเลขตามขั้น (ขั้นที่ 1 และ 2 ไม่มี test):

```
./gradlew :app:testDebugUnitTest --tests "com.learning.playground.lesson01.Step3Test"
./gradlew assembleDebug        # ตรวจว่า build ผ่าน
```

test ของขั้นที่ 3 และ 4 อ่านไฟล์ XML ของโปรเจกต์ตรง ๆ เพราะแบบฝึกแก้ที่ XML

**สิ่งที่คุณมองไม่เห็น** — หน้าจอ emulator, แถบ Logcat และแถบ Build ของ Android Studio อยู่ในเครื่องของผู้เรียน ให้ผู้เรียนเล่าสิ่งที่เห็นหรือวางข้อความมาในแชต อย่าเดาแทน

## เตรียมก่อนเริ่ม (ครั้งแรก)

1. อ่าน `progress.md` ถ้ามี เพื่อรู้ภาษาที่ผู้เรียนถนัดและเรื่องที่ยังไม่มั่นใจจากบทที่ 00
2. ให้ผู้เรียนเปิดโฟลเดอร์ `lessons/playground` ด้วย Android Studio (File › Open) แล้วรอ sync จนแถบสถานะด้านล่างหยุดทำงาน ครั้งแรกใช้เวลาหลายนาทีเพราะต้องดาวน์โหลด library
3. ถามว่ามี emulator แล้วหรือยัง ถ้ายังไม่มี ขั้นที่ 1 จะพาสร้าง

ถ้า sync ไม่ผ่าน ให้รัน `./gradlew assembleDebug` ในโฟลเดอร์นั้นเพื่ออ่าน error เอง

## ลำดับ

| ขั้น | เรื่อง | ไฟล์หลัก |
|---|---|---|
| 1 | เปิดโปรเจกต์และรันบน emulator | |
| 2 | มุมมอง Android กับ Project และโครงของ module | |
| 3 | `AndroidManifest.xml` | `AndroidManifest.xml` |
| 4 | resource และ class `R` | `strings.xml`, `activity_coffee_shop.xml`, `CoffeeShopActivity.kt` |
| 5 | Gradle: sync, build, run, Build Variant | `app/build.gradle` |
| 6 | Logcat และ stack trace | `Promotion.kt` |
| 7 | debugger และทางลัด | `Pricing.kt` |
| ปิด | อ่านโปรเจกต์ workshop | `workshop/` |

ขั้นที่ 6 สำคัญที่สุด อย่าไปขั้นถัดไปจนกว่าผู้เรียนจะหาบรรทัดต้นเหตุจาก stack trace ได้เอง

แอปนี้มีของพังที่ตั้งใจวางไว้สองจุด: ปุ่ม "ดูโปรโมชัน" ทำให้แอปปิดตัว (ขั้นที่ 6) และยอดรวมผิดเมื่อสั่งครบ 3 แก้ว (ขั้นที่ 7)
ถ้าผู้เรียนเจอก่อนถึงขั้นนั้น ให้บอกว่าตั้งใจ และเก็บไว้ก่อน

---

## ขั้นที่ 1 — เปิดโปรเจกต์และรันบน emulator

**อธิบาย**

- emulator คือโทรศัพท์จำลองที่รันบนเครื่องคอมพิวเตอร์ สร้างได้จาก Device Manager (แถบด้านขวา หรือ View › Tool Windows › Device Manager) กด `+` › Create Virtual Device เลือกรุ่นใดก็ได้ และ system image ตั้งแต่ API 29 ขึ้นไป
- ปุ่ม Run (สามเหลี่ยมสีเขียวบนแถบเครื่องมือ) ทำสามอย่างต่อกัน: build โค้ดเป็นไฟล์ `.apk`, ติดตั้งลง emulator, แล้วเปิดแอป
- ช่องข้างปุ่ม Run สองช่องบอกว่าจะรัน **อะไร** (`app`) บน **เครื่องไหน**

**ให้ผู้เรียนลอง** — กด Run แอปจะเปิดที่หน้าสารบัญ ให้กดปุ่ม "บทที่ 01 — หน้าร้านกาแฟ" แล้วเล่าว่าเห็นอะไรบนจอ ที่ควรเห็น:

```
ร้านกาแฟ Playground
สาขาทดสอบ
สั่งครบ 3 แก้ว ลดแก้วละ 10 บาท
สั่งไปแล้ว 0 แก้ว
รวม 0 บาท
[สั่งกาแฟ]
[ดูโปรโมชัน]
                เวอร์ชัน 1.0.0
```

**แบบฝึก** 1.1 กดปุ่ม "สั่งกาแฟ" 2 ครั้ง แล้วบอกข้อความสองบรรทัดที่เปลี่ยน

**เฉลย** — `สั่งไปแล้ว 2 แก้ว` และ `รวม 120 บาท`

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ปุ่ม Run เป็นสีเทา | sync ยังไม่เสร็จหรือไม่ผ่าน ดูแถบ Build ด้านล่าง |
| ช่องเครื่องเขียนว่า No Devices | ยังไม่ได้สร้างหรือยังไม่ได้เปิด emulator |
| emulator เปิดแล้วแต่แอปไม่ขึ้น | emulator ยังบูตไม่เสร็จ รอจนเห็นหน้า home แล้วกด Run อีกครั้ง |
| กด "ดูโปรโมชัน" แล้วแอปปิดตัว | ตั้งใจ เป็นแบบฝึกของขั้นที่ 6 |

**จะเจอที่ไหน** — ทุกครั้งที่แก้โค้ดใน workshop จะกด Run แบบเดียวกันนี้เพื่อดูผล

---

## ขั้นที่ 2 — มุมมอง Android กับ Project และโครงของ module

**อธิบาย**

- แถบ Project ด้านซ้ายมี dropdown ที่มุมบน เลือกได้ว่าจะดูไฟล์แบบใด สองแบบที่ใช้บ่อย:

| มุมมอง | แสดงอะไร | ใช้เมื่อ |
|---|---|---|
| Android | จัดกลุ่มตามหน้าที่: `manifests`, `kotlin+java`, `res`, `Gradle Scripts` ซ่อนไฟล์ที่ไม่ค่อยได้แตะ | เขียนโค้ดประจำวัน |
| Project | โฟลเดอร์จริงตามที่อยู่ในดิสก์ | หาไฟล์ที่มุมมอง Android ซ่อน หรือเมื่อมีคนบอก path มา |

- **module** คือหน่วยของโค้ดที่ build แยกกันได้ โปรเจกต์นี้มี module เดียวคือ `app` ทุก module มีโครงเดียวกัน:

| ที่อยู่ | คืออะไร |
|---|---|
| `build.gradle` | บอกว่า module นี้ build อย่างไรและใช้ library อะไร |
| `src/main/AndroidManifest.xml` | บอกระบบ Android ว่าแอปมีอะไรบ้าง |
| `src/main/java/` | โค้ด Kotlin (ชื่อโฟลเดอร์เป็น `java` ด้วยเหตุผลทางประวัติศาสตร์) |
| `src/main/res/` | resource: หน้าตาของหน้าจอ ข้อความ สี รูป |
| `src/test/` | unit test |
| `build/` | ผลของการ build สร้างขึ้นเอง ลบได้ ไม่ต้องแก้ |

**ให้ผู้เรียนลอง** — สลับ dropdown ระหว่าง Android กับ Project แล้วหาไฟล์ `activity_coffee_shop.xml` ให้เจอในทั้งสองมุมมอง

**แบบฝึก** 2.1 ตอบคำถามสี่ข้อ ถามทีละข้อและรอคำตอบ

| ถาม | คำตอบ |
|---|---|
| path จริงของ `activity_coffee_shop.xml` นับจากโฟลเดอร์ `playground` | `app/src/main/res/layout/activity_coffee_shop.xml` |
| ไฟล์ test ของบทนี้อยู่ที่โฟลเดอร์ใด | `app/src/test/java/com/learning/playground/lesson01/` |
| ยกตัวอย่างโฟลเดอร์ที่เห็นในมุมมอง Project แต่ไม่เห็นในมุมมอง Android | `app/build/`, `.gradle/` หรือ `gradle/wrapper/` |
| มุมมอง Android แสดง `build.gradle` สองไฟล์ใต้ Gradle Scripts ต่างกันอย่างไร | ตัวที่กำกับว่า Project อยู่ที่ root ใช้ร่วมกันทั้งโปรเจกต์ ตัวที่กำกับว่า Module :app อยู่ใน `app/` |

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| หาโฟลเดอร์ `kotlin+java` ในดิสก์ไม่เจอ | เป็นชื่อกลุ่มของมุมมอง Android โฟลเดอร์จริงคือ `src/main/java` |
| เห็น package เดียวกันสองสามครั้ง มีวงเล็บ `(test)` | มุมมอง Android แยก `src/main` กับ `src/test` เป็นคนละรายการ |

**จะเจอที่ไหน** — workshop มี 5 module (`app`, `core`, `networks`, `apilayer`, `coffee`) แต่ละตัวมีโครงตามตารางข้างบนเหมือนกันทุกตัว

---

## ขั้นที่ 3 — AndroidManifest.xml

**อธิบาย**

- ระบบ Android ไม่ได้อ่านโค้ด Kotlin เพื่อดูว่าแอปมีอะไร มันอ่าน `AndroidManifest.xml` ทุกหน้าจอ (Activity) ต้องประกาศที่นี่ ไม่อย่างนั้นเปิดไม่ได้
- อ่านไฟล์ด้วยกันทีละส่วน:

| ส่วน | ความหมาย |
|---|---|
| `<application android:label android:theme>` | ชื่อแอปใต้ icon และหน้าตาพื้นฐานของทุกหน้าจอ (`android:name=".PlaygroundApp"` เป็นของกลางของ playground ข้ามได้) |
| `<activity android:name=".HomeActivity">` | ประกาศหนึ่งหน้าจอ จุดนำหน้าแปลว่าต่อจาก `namespace` ใน `app/build.gradle` ชื่อเต็มจึงเป็น `com.learning.playground.HomeActivity` |
| `<activity android:name=".lesson01.CoffeeShopActivity">` | หน้าจอของบทนี้ อยู่ใน package ย่อย `lesson01` |
| `android:exported` | `true` คือยอมให้สิ่งที่อยู่นอกแอปเปิดหน้าจอนี้ได้ หน้าแรกต้องเป็น `true` เพราะคนเปิดคือ launcher ของเครื่อง หน้าจออื่นเป็น `false` เปิดได้จากในแอปเท่านั้น |
| `<intent-filter>` ที่มี `MAIN` และ `LAUNCHER` | บอกว่าหน้าจอนี้คือหน้าแรก และให้แสดง icon ในรายการแอป มีอยู่ที่ `HomeActivity` ตัวเดียว |

- สิ่งที่แอปต้องขออนุญาตก็ประกาศที่นี่ด้วย `<uses-permission>` แอปนี้ยังไม่มี

**ให้ผู้เรียนลอง** — ให้ทำนายก่อน แล้วลบ `<intent-filter>` ทั้งก้อนของ `HomeActivity` และกด Run ถามว่าเกิดอะไร (Android Studio ไม่ยอมรัน และแจ้งข้อความประมาณว่า `Default Activity not found`) จากนั้นกด undo ให้กลับมาเหมือนเดิม
ถามต่อ: build ผ่านไหม (ผ่าน โค้ดไม่ผิด แต่ไม่มีหน้าจอใดถูกระบุว่าเป็นหน้าแรก)

**แบบฝึก** 3.1 ทำให้หน้าจอของบทนี้ (`CoffeeShopActivity`) เป็นแนวตั้งเสมอ แม้หมุนเครื่อง โดยแก้ที่ Manifest อย่างเดียว ไม่แตะ Kotlin
คำใบ้ขั้นแรกถ้าขอ: attribute ชื่อ `android:screenOrientation` ให้พิมพ์ `android:scr` ใน tag `<activity>` แล้วดูรายการที่ IDE เสนอ

**เฉลย**

```xml
<activity
    android:name=".lesson01.CoffeeShopActivity"
    android:exported="false"
    android:screenOrientation="portrait" />
```

ให้ผู้เรียนรัน เข้าหน้าของบทที่ 01 แล้วกดปุ่มหมุนเครื่องบนแถบของ emulator หน้าจอต้องไม่หมุนตาม ส่วนหน้าสารบัญยังหมุนได้ เพราะ attribute นี้เป็นของแต่ละ Activity

**Android Studio จะขีดฆ่า attribute นี้** ผู้เรียนมักถาม ให้ใช้เป็นโอกาสสอนวิธีอ่านคำเตือนของ IDE: เอาเมาส์ชี้ที่คำที่ถูกขีดแล้วอ่านข้อความด้วยกัน
ขีดฆ่าแปลว่า "ยังใช้ได้ แต่ไม่แนะนำ" ไม่ใช่ error ตั้งแต่ `targetSdk 36` ระบบไม่สนใจการล็อกทิศบนจอใหญ่ เช่น แท็บเล็ตและเครื่องพับได้ ส่วนบนโทรศัพท์ยังล็อกได้ตามปกติ ข้อความที่ IDE แสดงอาจต่างจากนี้ ให้ยึดตามที่ผู้เรียนเห็น

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ใส่ attribute หลังเครื่องหมาย `/>` | attribute ต้องอยู่ใน tag ก่อน `/>` |
| ใส่ที่ `HomeActivity` | หน้าสารบัญล็อกแนวตั้งแทน test ไม่ผ่าน ให้ถามว่าหน้าจอของบทนี้ชื่ออะไร |
| ใส่ค่า `landscape` | attribute ถูก ค่าผิดทิศ `landscape` คือแนวนอน ให้ผู้เรียนรันดูก่อนบอก |
| test ข้อ `still the launcher` ไม่ผ่าน | ยังไม่ได้ใส่ `<intent-filter>` ของ `HomeActivity` กลับหลังการทดลอง |
| ใส่ที่ `<application>` | ไม่มีผล attribute นี้เป็นของแต่ละ Activity |

**จะเจอที่ไหน**

- `workshop/app/src/main/AndroidManifest.xml` มี `<uses-permission android:name="android.permission.INTERNET" />` เพราะแอปเรียก API
- workshop session 4 ผู้เรียนจะประกาศ `SchemeActivity` พร้อม `<intent-filter>` อีกแบบหนึ่ง สำหรับรับลิงก์จากนอกแอป

---

## ขั้นที่ 4 — resource และ class R

**อธิบาย**

- ของที่ไม่ใช่โค้ดอยู่ใน `res/` แยกโฟลเดอร์ตามชนิด:

| โฟลเดอร์ | เก็บอะไร |
|---|---|
| `res/layout/` | หน้าตาของหน้าจอ หนึ่งไฟล์ต่อหนึ่งหน้าจอ |
| `res/values/strings.xml` | ข้อความที่แสดงให้ผู้ใช้เห็น |
| `res/values/themes.xml` | หน้าตาพื้นฐานของแอป |
| `res/drawable/` | รูปและรูปทรง (โปรเจกต์นี้ยังไม่มี) |

- ตอน build ระบบสร้าง class ชื่อ `R` ให้ มีเลขประจำตัวของ resource ทุกชิ้น โค้ด Kotlin อ้างถึง resource ผ่าน `R` เช่น `R.string.shop_name` ส่วนใน XML เขียน `@string/shop_name`
- `R.string.shop_name` เป็น **ตัวเลข** ไม่ใช่ข้อความ ต้องใช้ `getString(R.string.shop_name)` เพื่อได้ข้อความ
- ข้อความที่มีช่องให้เติมค่าเขียน `%1$d` (ตัวเลข) หรือ `%1$s` (ข้อความ) แล้วส่งค่าตามหลัง: `getString(R.string.shop_total_price, 120)`
- เหตุผลที่ไม่เขียนข้อความลงในโค้ดตรง ๆ: แก้คำได้ในที่เดียว และแปลเป็นภาษาอื่นได้โดยไม่แตะโค้ด
- `id` ใน layout เช่น `@+id/btnOrder` กลายเป็น `binding.btnOrder` ใน Kotlin และไฟล์ `activity_coffee_shop.xml` กลายเป็น class `ActivityCoffeeShopBinding` รายละเอียดอยู่ในบทที่ 02

**ให้ผู้เรียนลอง**

1. เปิด `activity_coffee_shop.xml` (ถ้าเห็นเป็นภาพ ให้กดปุ่ม Code ที่มุมขวาบน) กด Cmd ค้างแล้วคลิก `@string/shop_name` (Windows/Linux ใช้ Ctrl) ถามว่าไปโผล่ที่ไหน
2. เปิด `CoffeeShopActivity.kt` ทำแบบเดียวกันกับ `R.string.shop_total_price`
3. กลับไปที่ layout ถามว่าปุ่ม `btnOrder` ต่างจากปุ่ม `btnPromotion` อย่างไร (ข้อความเขียนตรง ๆ และ IDE ระบายสีเตือน)

**แบบฝึก**

- 4.1 ย้ายข้อความของปุ่ม `btnOrder` ไปไว้ใน `strings.xml` ชื่อ `shop_order` แล้วให้ layout อ้างถึง
- 4.2 ใน `CoffeeShopActivity.kt` ย้ายข้อความ `"สั่งไปแล้ว $count แก้ว"` ไปไว้ใน `strings.xml` ชื่อ `shop_order_count` แล้วใช้ `getString` โดยดูบรรทัด `tvTotal` ที่อยู่ถัดลงมาเป็นแบบ

**เฉลย**

```xml
<!-- strings.xml -->
<string name="shop_order">สั่งกาแฟ</string>
<string name="shop_order_count">สั่งไปแล้ว %1$d แก้ว</string>

<!-- activity_coffee_shop.xml -->
android:text="@string/shop_order"
```

```kotlin
binding.tvOrderCount.text = getString(R.string.shop_order_count, count)
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| เขียน `binding.tvOrderCount.text = R.string.shop_order_count` แล้ว compile ไม่ผ่าน | `R.string.xxx` เป็นตัวเลข ต้องครอบด้วย `getString(...)` |
| `R.string.shop_order_count` เป็นสีแดงทั้งที่เพิ่มใน `strings.xml` แล้ว | พิมพ์ชื่อไม่ตรงกัน หรือ IDE import `android.R` มาแทน ต้องเป็น `com.learning.playground.R` |
| จอแสดง `สั่งไปแล้ว %1$d แก้ว` | ลืมส่ง `count` เป็นตัวที่สองของ `getString` |
| ใช้ `%d` แทน `%1$d` | ใช้ได้และผ่าน test เลข `1$` บอกลำดับของค่า จำเป็นเมื่อมีมากกว่าหนึ่งช่อง |

**จะเจอที่ไหน**

- ข้อความทุกคำใน workshop อยู่ใน `res/values/strings.xml` ของ module ที่ใช้
- workshop ตั้ง `android.nonTransitiveRClass=true` ทำให้ `R` ของแต่ละ module มีเฉพาะ resource ของตัวเอง อาการ "`R.xxx` สีแดง" ในข้อ 4.2 จึงเจออีกเมื่ออ้าง resource ข้าม module
- ชื่อ id มีคำนำหน้าตามชนิด: `tvXxx`, `btnXxx`, `rvXxx`

**จุดพัก** — ถ้าแบ่งสอนสองครั้ง ให้หยุดตรงนี้และจดลง `progress.md`

---

## ขั้นที่ 5 — Gradle: sync, build, run, Build Variant

**อธิบาย**

- Gradle คือเครื่องมือที่เปลี่ยนโค้ดกับ resource เป็นแอป ทำงานตามไฟล์ `build.gradle`
- สามคำที่ต้องแยกให้ออก:

| คำ | ทำอะไร | เมื่อไร |
|---|---|---|
| sync | Android Studio อ่านไฟล์ Gradle ใหม่ เพื่อรู้ว่าโปรเจกต์มี module และ library อะไร | หลังแก้ไฟล์ `.gradle` ทุกครั้ง (กด Sync Now บนแถบที่ขึ้นมา หรือปุ่มรูปช้าง) |
| build | compile โค้ดและรวม resource เป็น `.apk` | Build › Make Project หรือเป็นส่วนหนึ่งของ Run |
| run | build แล้วติดตั้งและเปิดแอป | ปุ่ม Run |

- อ่าน `app/build.gradle` ด้วยกัน:

| บรรทัด | ความหมาย |
|---|---|
| `namespace` | package ของ class ที่ระบบสร้างให้ (`R`, `BuildConfig`) และฐานของชื่อใน Manifest |
| `applicationId` | ชื่อประจำตัวของแอปบนเครื่อง สองแอปที่ id ต่างกันติดตั้งคู่กันได้ |
| `minSdk` / `targetSdk` | Android รุ่นต่ำสุดที่ติดตั้งได้ และรุ่นที่แอปถูกทดสอบด้วย |
| `versionName` | เวอร์ชันที่ผู้ใช้เห็น |
| `buildTypes` | แอปเดียวกันที่ build คนละแบบ: `debug` สำหรับพัฒนา `release` สำหรับปล่อยจริง |
| `buildConfigField` | ค่าที่ Gradle ส่งเข้าไปในโค้ด อ่านได้จาก `BuildConfig.BRANCH_NAME` |
| `dependencies` | library ที่ใช้ |

- **Build Variant** คือแบบที่จะ build ตอนกด Run เลือกได้ที่ Build › Select Build Variant

**ให้ผู้เรียนลอง**

1. **อ่าน error ของ build** — ใน `CoffeeShopActivity.kt` แก้ `binding.btnOrder` เป็น `binding.btnOrde` แล้วสั่ง Build › Make Project ให้อ่านแถบ Build ด้านล่างแล้วบอกสามอย่าง: ไฟล์ไหน บรรทัดไหน ข้อความว่าอะไร (บรรทัดที่ขึ้นต้นด้วย `e:` ลงท้ายว่า `Unresolved reference: btnOrde`) ให้คลิกที่ error เพื่อกระโดดไปยังบรรทัดนั้น แล้วแก้กลับ
2. **สลับ Build Variant** — ก่อนทำให้ทำนาย: ถ้าเปลี่ยนเป็น `release` แล้วรัน บรรทัดใดบนจอจะเปลี่ยน (คำตอบ: `สาขาทดสอบ` เป็น `สาขาจริง` เพราะ `buildConfigField` ของสอง build type ต่างกัน) ลองแล้ว **สลับกลับเป็น `debug`** ก่อนไปต่อ เพราะ debugger ในขั้นที่ 7 ใช้กับ `release` ไม่ได้

**แบบฝึก**

- 5.1 เปลี่ยนเวอร์ชันของแอปเป็น `1.1.0` แล้วดูบรรทัดล่างสุดของจอ
- 5.2 ทำให้แอปแบบ `debug` มี `applicationId` ลงท้ายด้วย `.debug` เพื่อให้ติดตั้งคู่กับแบบ `release` ได้ คำใบ้ขั้นแรกถ้าขอ: ใช้ `applicationIdSuffix` ในก้อน `debug`

**เฉลย**

```groovy
defaultConfig {
    ...
    versionName "1.1.0"
}

buildTypes {
    debug {
        applicationIdSuffix ".debug"
        buildConfigField "String", "BRANCH_NAME", "\"สาขาทดสอบ\""
    }
```

หลังข้อ 5.2 emulator จะมี icon ชื่อ Playground สองตัว ตัวเก่าคือ `com.learning.playground` ตัวใหม่คือ `com.learning.playground.debug` ใช้เป็นตัวอย่างว่า `applicationId` คือสิ่งที่เครื่องใช้แยกแอป ไม่ใช่ชื่อที่แสดง

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| แก้ `versionName` แล้วจอยังแสดงเลขเดิม | ยังไม่ได้ sync หรือยังไม่ได้ Run ใหม่ |
| ใส่ `applicationIdSuffix` ใน `defaultConfig` | test ผ่าน แต่ `release` จะได้ `.debug` ไปด้วย ให้ย้ายไปไว้ในก้อน `debug` |
| สับสน `namespace` กับ `applicationId` | ค่าเริ่มต้นมักเหมือนกัน แต่หน้าที่ต่างกัน: `namespace` ใช้ในโค้ด ส่วน `applicationId` ใช้บนเครื่อง ข้อ 5.2 เปลี่ยนแค่ตัวหลัง จึงไม่ต้องแก้ import |
| error ของ build ยาวหลายหน้า | อ่านเฉพาะบรรทัดที่ขึ้นต้นด้วย `e:` ก่อน ที่เหลือเป็นผลตามมา |

**จะเจอที่ไหน**

- workshop มี product flavor `sit` และ `production` ซ้อนกับ build type อีกชั้น Build Variant จึงเป็น `sitDebug`, `productionRelease` และ `sit` ใช้ `applicationIdSuffix ".sit"` แบบเดียวกับข้อ 5.2
- workshop ใช้ `buildConfigField` กำหนด `BASE_URL` และ `USE_MOCK_API` ต่างกันในแต่ละ flavor
- เวอร์ชันของ library ใน workshop ไม่ได้เขียนใน `build.gradle` ตรง ๆ แต่รวมไว้ที่ `buildSrc/src/main/java/Dependencies.kt`

---

## ขั้นที่ 6 — Logcat และ stack trace

**อธิบาย**

- Logcat (View › Tool Windows › Logcat) คือบันทึกของทุกอย่างที่เกิดบนเครื่อง ทั้งจากแอปของเราและจากระบบ จึงต้องกรอง
- โค้ดเขียน log ด้วย `Log.d(TAG, "ข้อความ")` โดย `TAG` คือป้ายสำหรับกรอง ระดับมี `d` (debug), `i` (info), `w` (warning), `e` (error)
- ตัวกรองที่ใช้บ่อย พิมพ์ในช่องด้านบนของ Logcat:

| พิมพ์ | ได้ |
|---|---|
| `package:mine` | เฉพาะ log ของแอปที่กำลังรัน |
| `tag:CoffeeShop` | เฉพาะ log ที่มี tag นี้ |
| `level:error` | เฉพาะระดับ error ขึ้นไป |

- เมื่อแอปปิดตัวเอง Logcat จะมีก้อนสีแดงขึ้นต้นด้วย `FATAL EXCEPTION` เรียกว่า stack trace วิธีอ่าน:
  1. บรรทัดแรกบอกชนิดของ error และข้อความ
  2. ถ้ามีบรรทัด `Caused by:` ให้ข้ามไปอ่านตรงนั้น ตัวล่างสุดคือ **ต้นเหตุจริง** ตัวบนเป็นแค่ห่อ
  3. ใต้ `Caused by:` ไล่ลงมาหาบรรทัดแรกที่เป็นโค้ดของเรา (ขึ้นต้นด้วย `com.learning` และเป็นลิงก์สีน้ำเงิน) นั่นคือจุดที่ต้องไปดู

**ให้ผู้เรียนลอง** — พิมพ์ `tag:CoffeeShop` ในช่องกรอง กดปุ่ม "สั่งกาแฟ" สองสามครั้ง ถามว่าเห็นอะไร และข้อความนั้นมาจากบรรทัดไหนของ `CoffeeShopActivity.kt`

**แบบฝึก** 6.1 กดปุ่ม "ดูโปรโมชัน" แอปจะปิดตัว ให้หาสาเหตุจาก Logcat แล้วแก้ให้ปุ่มแสดงโปรโมชันได้
เงื่อนไข: ห้ามแก้ข้อมูล `todayPromotion` เพราะแทนข้อมูลที่ server ส่งมา

ก่อนให้แก้ ให้ผู้เรียนเปลี่ยนตัวกรองเป็น `package:mine level:error` แล้วตอบสามข้อนี้จาก stack trace ถ้าตอบไม่ได้ให้กลับไปที่วิธีอ่านสามข้อ อย่าบอกคำตอบ:

| ถาม | คำตอบ |
|---|---|
| บรรทัดแรกบอกว่า error อะไร | `java.lang.IllegalStateException: โหลดโปรโมชันไม่ได้` |
| `Caused by:` บอกว่าอะไร | `java.lang.NumberFormatException: For input string: "55 บาท"` |
| โค้ดของเราบรรทัดแรกใต้ `Caused by:` อยู่ที่ไหน | function `parsePrice` ในไฟล์ `Promotion.kt` |

ให้ผู้เรียนอธิบายด้วยคำของตัวเองว่าเกิดอะไร: `toInt()` แปลง `"55 บาท"` เป็นตัวเลขไม่ได้เพราะมีหน่วยติดมา

**เฉลย**

```kotlin
fun parsePrice(priceText: String): Int {
    return priceText.filter { it.isDigit() }.toInt()
}
```

คำตอบอื่นที่ถูกและผ่าน test เช่น `priceText.substringBefore(" ").toInt()` หรือ `priceText.removeSuffix(" บาท").toInt()`

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| อ่านแค่บรรทัดแรกแล้วไปดู `loadPromotion` | นั่นคือห่อ ข้อความ "โหลดโปรโมชันไม่ได้" ไม่ได้บอกสาเหตุ ให้อ่าน `Caused by:` |
| Logcat ว่างหรือไม่เห็นก้อนสีแดง | ตัวกรองยังเป็น `tag:CoffeeShop` ซึ่งตัด error ของระบบออก ให้เปลี่ยนเป็น `package:mine` |
| แก้ `todayPromotion` เป็น `"55"` | แอปไม่พังแล้ว แต่ผิดเงื่อนไข และ test ข้อ `reads price with unit` ไม่ผ่าน |
| ลบ `try`/`catch` ออก | ไม่ได้แก้ต้นเหตุ แอปยังพังด้วย error ตัวใน |
| ครอบ `catch` แล้วคืน `0` | แอปไม่พัง แต่แสดงราคาผิด test ไม่ผ่าน ใช้เป็นตัวอย่างว่าการกลบ error ไม่ใช่การแก้ |
| หา stack trace ไม่เจอเพราะ log ไหลเร็ว | กดปุ่มถังขยะล้าง Logcat ก่อน แล้วกดปุ่มในแอปอีกครั้ง |

**จะเจอที่ไหน**

- workshop session 1: `BaseActivity` เขียน log ทุก lifecycle callback ด้วย tag `Lifecycle` ผู้เรียนจะกรองด้วย `tag:Lifecycle`
- workshop session 3: request และ response ของ API ดูได้ด้วย `tag:okhttp.OkHttpClient`
- error จาก library เช่น Koin มักห่อหลายชั้น มี `Caused by:` สองสามตัว ใช้วิธีอ่านเดียวกัน: ตัวล่างสุดก่อน

---

## ขั้นที่ 7 — debugger และทางลัด

**อธิบาย**

- log บอกได้เฉพาะสิ่งที่เราเขียนให้มันบอก ส่วน debugger หยุดแอปไว้กลางทางแล้วให้ดูค่าของตัวแปรทุกตัวในตอนนั้น
- **breakpoint** คือจุดที่ให้หยุด ตั้งด้วยการคลิกที่ขอบซ้ายข้างเลขบรรทัดให้ขึ้นจุดสีแดง
- ต้องรันด้วยปุ่ม **Debug** (รูปแมลง ข้างปุ่ม Run) ถ้ารันด้วย Run ธรรมดา breakpoint ไม่ทำงาน
- เมื่อหยุดแล้ว:

| ปุ่ม | ทำอะไร | macOS | Windows/Linux |
|---|---|---|---|
| Step Over | ทำบรรทัดนี้แล้วหยุดที่บรรทัดถัดไป | F8 | F8 |
| Step Into | เข้าไปใน function ที่บรรทัดนี้เรียก | F7 | F7 |
| Resume | ปล่อยให้ทำงานต่อจนเจอ breakpoint ถัดไป | Cmd+Option+R | F9 |

- ทางลัดที่ใช้ทุกวัน:

| ทำอะไร | macOS | Windows/Linux |
|---|---|---|
| ค้นทุกอย่าง (ไฟล์, class, คำสั่ง) | Shift สองครั้ง | Shift สองครั้ง |
| ค้นข้อความทั้งโปรเจกต์ | Cmd+Shift+F | Ctrl+Shift+F |
| ไปที่ definition | Cmd+B หรือ Cmd+คลิก | Ctrl+B หรือ Ctrl+คลิก |
| กลับไปจุดก่อนหน้า | Cmd+[ | Ctrl+Alt+ลูกศรซ้าย |
| ให้ IDE เสนอวิธีแก้ | Option+Enter | Alt+Enter |
| ไฟล์ที่เพิ่งเปิด | Cmd+E | Ctrl+E |

**ให้ผู้เรียนลอง** — ตั้ง breakpoint ที่บรรทัด `count++` ใน `CoffeeShopActivity.kt` กด Debug แล้วกดปุ่ม "สั่งกาแฟ" ในแอป ถามว่า `count` ในแถบ Variables เป็นเท่าไร กด Step Over หนึ่งครั้งแล้วถามอีกครั้ง จากนั้นกด Resume

**แบบฝึก**

- 7.1 หน้าจอเขียนว่า "สั่งครบ 3 แก้ว ลดแก้วละ 10 บาท" ให้ผู้เรียนสั่ง 3 แก้วแล้วบอกยอดรวมที่เห็น (180 บาท) และยอดที่ควรเป็น (150 บาท) จากนั้นหาสาเหตุด้วย debugger แล้วแก้
  วิธีที่แนะนำ: ไปที่ `totalPrice` ด้วย Cmd+B จากใน Activity ตั้ง breakpoint ที่บรรทัดแรกของ function สั่งให้ครบ 3 แก้ว แล้วกด Step Over ทีละบรรทัด สังเกตว่าบรรทัดใดถูกข้าม
- 7.2 ตอบสามข้อโดยใช้ทางลัด ห้ามไล่เปิดไฟล์ด้วยมือ

| ถาม | คำตอบ | ทางลัดที่ใช้ |
|---|---|---|
| ข้อความ `สาขาทดสอบ` ถูกกำหนดที่ไฟล์ใด | `app/build.gradle` | ค้นข้อความทั้งโปรเจกต์ |
| Cmd+คลิกที่ `ActivityCoffeeShopBinding` แล้วไปที่ไหน | `activity_coffee_shop.xml` | ไปที่ definition |
| เปิด `Step7Test` โดยไม่ใช้แถบ Project | พิมพ์ชื่อในช่องค้นทุกอย่าง | Shift สองครั้ง |

**เฉลย** ข้อ 7.1 — เมื่อ `cups` เป็น 3 เงื่อนไข `cups > DISCOUNT_MIN_CUPS` เป็นเท็จ บรรทัดลดราคาจึงถูกข้าม

```kotlin
if (cups >= DISCOUNT_MIN_CUPS) {
    pricePerCup -= DISCOUNT_PER_CUP
}
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| แอปไม่หยุดที่ breakpoint | รันด้วย Run ไม่ใช่ Debug หรือ Build Variant ยังเป็น `release` จากขั้นที่ 5 |
| แอปค้าง กดอะไรไม่ได้ | กำลังหยุดอยู่ที่ breakpoint ให้กด Resume |
| หาเจอจากการอ่านโค้ดโดยไม่ใช้ debugger | ถูกต้อง ให้ชม แล้วให้ลองไล่ด้วย debugger อีกรอบเพื่อฝึกเครื่องมือ |
| แก้ `DISCOUNT_MIN_CUPS` เป็น `2` | ผ่าน test แต่ชื่อค่าคงที่ไม่ตรงกับความหมายแล้ว ชวนคิดว่าคนอ่านคนถัดไปจะเข้าใจว่าอย่างไร |

**จะเจอที่ไหน**

- workshop session 3: ตั้ง breakpoint ใน ViewModel เพื่อดูว่า `Result` ที่ได้จาก UseCase เป็น `Success` หรือ `Error`
- ค้นข้อความทั้งโปรเจกต์ด้วย `TODO(Session` คือวิธีหาภารกิจของแต่ละ session ใน workshop

---

## ปิดบท — อ่านโปรเจกต์ workshop

ให้ผู้เรียนเปิดโฟลเดอร์ `workshop/` ด้วย Android Studio (File › Open เลือกเปิดในหน้าต่างใหม่) แล้วรอ sync
ถามทีละข้อ รอคำตอบก่อนเฉลย ผู้เรียนใช้เครื่องมือใดในบทนี้ก็ได้ ยังไม่ต้องเข้าใจโค้ดข้างใน

| ถาม | คำตอบ |
|---|---|
| workshop มีกี่ module รู้ได้จากไฟล์ใด | 5 ตัว จาก `settings.gradle`: `app`, `core`, `networks`, `apilayer`, `coffee` |
| Activity ใดเป็นหน้าแรก ชื่อเต็มคืออะไร | `com.learning.app.ui.MainActivity` ดูจาก `<intent-filter>` ใน `app/src/main/AndroidManifest.xml` และ `namespace` ใน `app/build.gradle` |
| แอปขอ permission อะไร | `android.permission.INTERNET` |
| Build Variant มีอะไรบ้าง ทำไมมากกว่า playground | `sitDebug`, `sitRelease`, `productionDebug`, `productionRelease` เพราะมี flavor สองตัวคูณกับ build type สองตัว |
| ชื่อแอป `app_name` กำหนดไว้ที่ใด | ไม่ได้อยู่ใน `strings.xml` อยู่ที่ `resValue` ของแต่ละ flavor ใน `app/build.gradle` (หาได้ด้วยการค้นข้อความทั้งโปรเจกต์) |

ถ้าตอบได้ 4 จาก 5 ข้อถือว่าผ่านบทนี้ ข้อสุดท้ายตั้งใจให้ยาก: สิ่งที่หาในที่ที่คาดไว้ไม่เจอ ให้ค้นทั้งโปรเจกต์

**บันทึก** ลง `progress.md`: ขั้นที่ผ่าน, ขั้นที่ใช้คำใบ้หรือดูเฉลย, และเรื่องที่ยังไม่มั่นใจ
