# บทที่ 00 — Kotlin เท่าที่ต้องใช้

บทสอนนี้เขียนถึง Claude ผู้ดำเนินการสอน วิธีสอนบทเรียนรายหัวข้ออยู่ใน [../README.md](../README.md) อ่านก่อนสอนครั้งแรก

| | |
|---|---|
| ที่มาของหัวข้อ | เพิ่มเข้ามา (ไม่อยู่ในรายการของ senior) |
| ผู้เรียน | เขียนโปรแกรมภาษาอื่นเป็น ยังไม่เคยเขียน Kotlin |
| จังหวะ | ไม่จับเวลา ผู้เรียนกำหนดจังหวะเอง แบ่งทำหลายครั้งได้ จุดพักที่แนะนำคือหลังขั้นที่ 4 |
| ต้องมีในเครื่อง | Android Studio (ไม่ต้องมี emulator) |
| เรียนจบแล้ว | อ่านโค้ด Kotlin ใน workshop ได้โดยไม่ติดที่ไวยากรณ์ และเขียน class, function, lambda แบบง่ายได้เอง |
| บทถัดไป | [01 — Android Studio และส่วนประกอบของแอป](../01-android-studio-and-app-anatomy/lesson.md) |

บทนี้ไม่ได้สอน Kotlin ทั้งภาษา สอนเฉพาะไวยากรณ์ที่ผู้เรียนจะเจอในทุกไฟล์ของโปรเจกต์ Android
ทุกขั้นจึงปิดด้วยหัวข้อ "จะเจอที่ไหน" ให้บอกผู้เรียนเสมอ เพื่อให้รู้ว่ากำลังเรียนสิ่งนี้ไปทำอะไร

## โค้ดของบทนี้

โฟลเดอร์นี้เป็นโปรเจกต์ Kotlin ธรรมดา ไม่มี Android

```
lessons/00-kotlin/
├── src/main/kotlin/lesson00/Step1Values.kt ... Step8Reading.kt   ตัวอย่างและแบบฝึก ขั้นละหนึ่งไฟล์
└── src/test/kotlin/lesson00/Step1Test.kt ... Step8Test.kt        test ที่ใช้ตรวจแบบฝึก
```

แต่ละไฟล์มีสองส่วน:

- **`fun main()`** ตัวอย่างที่รันได้ ผู้เรียนกดลูกศรสีเขียวหน้า `fun main` เพื่อดูผล
- **แบบฝึก** function ที่ข้างในเป็น `TODO("แบบฝึก N.M")` ผู้เรียนแทนที่บรรทัดนั้นด้วยโค้ดจริง

เฉลยอยู่ที่ `solutions/lesson-00/` (root ของ repo) ใช้ path เดียวกับโฟลเดอร์นี้

**ตรวจแบบฝึก** — รันจากในโฟลเดอร์ `lessons/00-kotlin/` โดยเปลี่ยนเลขตามขั้น:

```
./gradlew test --tests "lesson00.Step1Test"
```

คุณรันคำสั่งนี้เองได้เมื่อผู้เรียนบอกว่าเสร็จ ผู้เรียนรันจาก Android Studio ได้ด้วยการเปิดไฟล์ test แล้วกดลูกศรสีเขียวหน้าชื่อ class

## เตรียมก่อนเริ่ม (ครั้งแรก)

1. ให้ผู้เรียนเปิดโฟลเดอร์ `lessons/00-kotlin` ด้วย Android Studio (File › Open) แล้วรอ sync
2. ให้เปิด `Step1Values.kt` แล้วกดลูกศรสีเขียวหน้า `fun main` ต้องเห็นข้อความ `ร้านกาแฟ ขายไปแล้ว 2 แก้ว รวม 130 บาท` ในแถบ Run
3. ถามว่าผู้เรียนถนัดภาษาอะไร แล้วจดลง `progress.md` ตลอดบทให้เทียบกับภาษานั้นเมื่อช่วยให้เข้าใจเร็วขึ้น เช่น lambda กับ arrow function ของ JavaScript หรือ `?.` กับ optional chaining

ถ้าข้อ 2 ไม่ผ่าน ให้รัน `./gradlew compileKotlin` ในโฟลเดอร์นี้เพื่ออ่าน error

## แนะนำโครงสร้าง (ก่อนขั้นที่ 1)

ทำหลังเตรียมเครื่องผ่าน และก่อนเริ่มขั้นที่ 1 ให้ผู้เรียนสลับแถบซ้ายของ Android Studio เป็นมุมมอง **Project** แล้วพาดูทีละส่วน

```
00-kotlin/
├── src/
│   ├── main/kotlin/lesson00/     โค้ดของบทเรียน ← ทำงานในนี้
│   │   ├── Step1Values.kt        หนึ่งไฟล์ต่อหนึ่งขั้น มีตัวอย่างและแบบฝึกอยู่ในไฟล์เดียวกัน
│   │   └── ... ถึง Step8Reading.kt
│   └── test/kotlin/lesson00/     ตัวตรวจคำตอบ ← เปิดดูได้ ไม่ต้องแก้
│       └── Step1Test.kt ... Step8Test.kt
├── build.gradle.kts              บอกว่าโปรเจกต์นี้ใช้ Kotlin เวอร์ชันใดและ library อะไร
├── settings.gradle.kts           ชื่อของโปรเจกต์
├── gradlew, gradle/              ตัวรันระบบ build ที่พกมากับโปรเจกต์
└── build/, .gradle/              ของที่เครื่องสร้างขึ้นเอง ไม่ต้องเปิดดู
```

สิ่งที่ต้องอธิบาย:

| สิ่งที่เห็น | คืออะไร |
|---|---|
| `src/main` กับ `src/test` | `main` คือโค้ดจริง `test` คือโค้ดที่ใช้ตรวจโค้ดจริง โปรเจกต์ Android ทุกตัวแบ่งแบบนี้ |
| `lesson00` | package คือที่อยู่ของโค้ด บรรทัดแรกของทุกไฟล์เขียนว่า `package lesson00` |
| ไฟล์ `.kt` | ไฟล์โค้ด Kotlin |
| `fun main()` ในแต่ละไฟล์ | จุดเริ่มรันของตัวอย่าง มีลูกศรสีเขียวอยู่ข้างหน้า |
| `TODO("แบบฝึก 1.1")` | ช่องว่างที่ผู้เรียนต้องแทนด้วยโค้ดจริง ถ้ารันโดยยังไม่แทน โปรแกรมจะหยุดพร้อมข้อความนี้ |
| ไฟล์ Gradle | ระบบ build ผู้เรียนไม่ต้องแก้ในบทนี้ จะเจออีกครั้งในโปรเจกต์ Android |

**ให้ผู้เรียนทำ:** เปิด `Step1Values.kt` คู่กับ `Step1Test.kt` แล้วถามว่า test ข้อ `1_1 menuLabel` คาดหวังให้ `menuLabel("ลาเต้", 65)` คืนค่าอะไร (คำตอบ: `"ลาเต้ · 65 บาท"`) เพื่อให้เห็นว่า test คือคำอธิบายโจทย์ที่แม่นที่สุด
จากนั้นให้กดรัน `Step1Test` หนึ่งครั้งให้เห็นว่าตอนนี้ล้มทั้งสองข้อ และข้อความ error คือ `แบบฝึก 1.1` นี่คือสภาพเริ่มต้นที่ถูกต้อง

## ลำดับ

| ขั้น | เรื่อง | ไฟล์ |
|---|---|---|
| 1 | ตัวแปร ชนิดข้อมูล ข้อความ | `Step1Values.kt` |
| 2 | ค่าที่อาจไม่มี (null) | `Step2Null.kt` |
| 3 | function | `Step3Functions.kt` |
| 4 | lambda | `Step4Lambda.kt` |
| 5 | class และ data class | `Step5Classes.kt` |
| 6 | object และ companion object | `Step6Objects.kt` |
| 7 | sealed class กับ when | `Step7Sealed.kt` |
| 8 | let, apply, by lazy, generic | `Step8Reading.kt` |
| ปิด | อ่านโค้ดจริง | |

ขั้นที่ 2 และ 4 สำคัญที่สุด อย่าไปขั้นถัดไปจนกว่าผู้เรียนจะอธิบายแนวคิดกลับมาด้วยคำของตัวเองได้

---

## ขั้นที่ 1 — ตัวแปร ชนิดข้อมูล ข้อความ

**อธิบาย**

- `val` กำหนดค่าได้ครั้งเดียว ส่วน `var` เปลี่ยนค่าได้ ใช้ `val` เป็นหลัก เปลี่ยนเป็น `var` เมื่อจำเป็นเท่านั้น
- Kotlin เดาชนิดให้จากค่าที่ใส่ จะเขียนกำกับเองก็ได้ในรูป `ชื่อ: ชนิด`
- ใส่ค่าลงในข้อความด้วย `$ชื่อ` และใช้ `${...}` เมื่อเป็นนิพจน์

**ให้ผู้เรียนลอง** — รัน `main` แล้วเอา comment ของบรรทัด `shopName = "ร้านชา"` ออก ถามว่า IDE บอกอะไร (คำตอบ: `Val cannot be reassigned`) แล้วให้ใส่ comment กลับ

**แบบฝึก** 1.1 `menuLabel` และ 1.2 `totalPrice`

**เฉลย**

```kotlin
fun menuLabel(name: String, price: Int): String {
    return "$name · $price บาท"
}

fun totalPrice(price: Int, cups: Int): Int {
    return price * cups
}
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| test 1.1 ไม่ผ่านทั้งที่ข้อความดูเหมือนกัน | จุดกลาง `·` หรือช่องว่างไม่ตรง ให้คัดลอกจากคำอธิบายเหนือ function |
| เขียน `name + " · " + price` | ถูกต้องและผ่าน test แต่ให้ลองเขียนแบบ `$` ด้วย เพราะโค้ดจริงใช้แบบนั้น |

**จะเจอที่ไหน** — ทุกบรรทัด เช่น `private val coffeeMenuViewModel` และ `private var currentCoffeeName`

---

## ขั้นที่ 2 — ค่าที่อาจไม่มี (null)

**อธิบาย** — นี่คือสิ่งที่ทำให้ Kotlin ต่างจากภาษาส่วนใหญ่

- ชนิดปกติ เช่น `String` **เป็น null ไม่ได้** ถ้าอยากให้เป็นได้ต้องเขียน `String?`
- compiler ไม่ยอมให้เรียกอะไรบน `String?` ตรง ๆ จนกว่าจะจัดการกรณี null ก่อน ปัญหา null จึงถูกจับตั้งแต่ตอนเขียน ไม่ใช่ตอนแอปรันอยู่ในมือผู้ใช้
- เครื่องมือสามตัว:

| เขียน | ความหมาย |
|---|---|
| `a?.length` | ถ้า `a` เป็น null ผลทั้งก้อนเป็น null ไม่พัง |
| `a ?: "ค่าอื่น"` | ถ้า `a` เป็น null ให้ใช้ค่าทางขวาแทน |
| `a!!` | "มั่นใจว่าไม่ใช่ null" ถ้าผิดแอปพังทันที ควรเลี่ยง |

**ให้ผู้เรียนลอง** — รัน `main` จากนั้นเอา comment ของ `println(description.length)` ออก ถามว่า IDE บอกอะไร แล้วให้ใส่ comment กลับ
ถามต่อ: `description?.length ?: 0` ได้ค่าอะไรและชนิดอะไร (คำตอบ: `0` ชนิด `Int` ไม่ใช่ `Int?` เพราะ `?:` ปิดกรณี null แล้ว)

**แบบฝึก** 2.1 `displayName` และ 2.2 `nameLength`

**เฉลย**

```kotlin
fun displayName(name: String?): String {
    return name ?: "ไม่ระบุชื่อ"
}

fun nameLength(name: String?): Int {
    return name?.length ?: 0
}
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| เขียน `if (name == null) ... else ...` | ถูกต้องและผ่าน test ให้ชมแล้วชวนเขียนแบบ `?:` ด้วย เพราะโค้ดจริงใช้แบบสั้น |
| ใช้ `name!!.length` | ผ่าน test ข้อที่มีค่า แต่พังข้อที่เป็น null ใช้เป็นตัวอย่างว่าทำไมควรเลี่ยง `!!` |
| งงว่า `?.` กับ `?:` ต่างกันอย่างไร | `?.` คือ "เรียกต่อถ้ามีค่า" ส่วน `?:` คือ "ค่าสำรองถ้าไม่มี" |

**จะเจอที่ไหน** — ข้อมูลจาก API ทุก field เป็น nullable เพราะ server อาจไม่ส่งมา จึงเห็น `it.name.orEmpty()` และ `it.price ?: 0` ทั่วโปรเจกต์ (`orEmpty()` คือทางลัดของ `?: ""`)

---

## ขั้นที่ 3 — function

**อธิบาย**

- รูปเต็ม: `fun ชื่อ(parameter: ชนิด): ชนิดที่คืน { return ... }`
- ถ้ามีนิพจน์เดียว เขียนสั้นได้ด้วย `=`
- parameter มีค่าเริ่มต้นได้ คนเรียกจึงละได้
- ตอนเรียกระบุชื่อ parameter ได้ ทำให้อ่านง่ายและสลับลำดับได้
- function ที่ไม่คืนค่า ชนิดที่คืนคือ `Unit` และไม่ต้องเขียน

**ให้ผู้เรียนลอง** — รัน `main` แล้วถามว่าบรรทัด `greet("สมชาย")` ได้คำว่า "สวัสดี" มาจากไหน

**แบบฝึก** 3.1 `priceAfterDiscount` มีสองส่วน: เขียนเนื้อ function และแก้บรรทัดประกาศให้ `discountPercent` มีค่าเริ่มต้นเป็น `0`

**เฉลย**

```kotlin
fun priceAfterDiscount(price: Int, discountPercent: Int = 0): Int {
    return price - (price * discountPercent / 100)
}
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| test ข้อ `discountPercent has default value` ไม่ผ่าน | ทำแค่เนื้อ function ยังไม่ได้เพิ่ม `= 0` ที่บรรทัดประกาศ |
| ได้ผลลัพธ์เป็น 0 | เขียน `discountPercent / 100 * price` การหารจำนวนเต็มปัดทิ้งก่อนคูณ ให้คูณก่อนหาร |

**จะเจอที่ไหน** — `fun handleResponse(response, onLoading = { ... }, onError = { false }, onSuccess)` มีค่าเริ่มต้นสองตัว คนเรียกจึงส่งแค่ `onSuccess` และรูปแบบ `handleResponse(result, onSuccess = { ... })` คือการระบุชื่อ parameter

---

## ขั้นที่ 4 — lambda

**อธิบาย** — ขั้นนี้สำคัญที่สุดของบท Android ทั้งหมดทำงานด้วยแนวคิดนี้

- lambda คือก้อนโค้ดที่เขียนไว้ในปีกกา **ยังไม่ถูกรัน** จนกว่าจะมีคนเรียก
- ส่งต่อเป็นค่าได้เหมือนตัวเลขหรือข้อความ ประโยชน์คือบอกคนอื่นว่า "เมื่อเกิดเหตุการณ์นี้ ให้ทำสิ่งนี้"
- ชนิดของ lambda เขียนเป็น `(สิ่งที่รับ) -> สิ่งที่คืน` เช่น `() -> Unit` คือไม่รับอะไรและไม่คืนอะไร
- ถ้า lambda เป็น parameter ตัวสุดท้าย เขียนไว้นอกวงเล็บได้ และถ้าเป็นตัวเดียวก็ละวงเล็บไปได้เลย
- ถ้า lambda รับค่าตัวเดียว ไม่ต้องตั้งชื่อ ใช้ `it` แทนได้

**ให้ผู้เรียนลอง** — ก่อนรัน `main` ให้ผู้เรียน **ทำนาย** ว่าสามบรรทัดสุดท้ายพิมพ์อะไรก่อนหลัง:

```kotlin
button.setOnClickListener { println("ปุ่มถูกกด") }
println("ยังไม่มีอะไรเกิดขึ้น จนกว่าจะมีคนกด")
button.click()
```

แล้วรันดู ถ้าทำนายผิด (คิดว่า "ปุ่มถูกกด" ขึ้นก่อน) ให้หยุดอธิบายตรงนี้จนเข้าใจ: `setOnClickListener` แค่ **เก็บ** โค้ดไว้ ไม่ได้รัน
เปิด class `FakeButton` ที่หัวไฟล์ให้ดูประกอบว่ามันเก็บ lambda ไว้ในตัวแปร แล้วเรียกตอน `click()`

**แบบฝึก** 4.1 `affordablePrices`, 4.2 `handleResult`, 4.3 `countClicks`

**เฉลย**

```kotlin
fun affordablePrices(prices: List<Int>, max: Int): List<Int> {
    return prices.filter { it <= max }
}

fun handleResult(success: Boolean, onSuccess: () -> Unit, onError: () -> Unit) {
    if (success) {
        onSuccess()
    } else {
        onError()
    }
}

fun countClicks(button: FakeButton, times: Int): Int {
    var count = 0
    button.setOnClickListener { count++ }
    repeat(times) { button.click() }
    return count
}
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ข้อ 4.2 เขียน `onSuccess` เฉย ๆ ไม่มีวงเล็บ | นั่นคือการเอ่ยถึง lambda ไม่ใช่การเรียก ต้องมี `()` |
| ข้อ 4.3 ได้ 0 เสมอ | ตั้ง listener แล้วแต่ลืมกดปุ่ม หรือกดก่อนตั้ง listener |
| ข้อ 4.3 ใช้ `for` แทน `repeat` | ถูกต้อง |
| ไม่เข้าใจว่า `it` มาจากไหน | ให้เขียนแบบตั้งชื่อก่อน `{ price -> price <= max }` แล้วค่อยย่อ |

**จะเจอที่ไหน**

- `binding.btnRecommend.setOnClickListener { ... }` การกดปุ่มทุกปุ่ม
- `observe(viewModel.result) { ... }` "เมื่อข้อมูลเปลี่ยน ให้ทำสิ่งนี้"
- `handleResponse(result, onSuccess = { ... })` คือแบบฝึก 4.2 ในรูปจริง
- `res.result({ กรณีผิดพลาด }, { กรณีสำเร็จ })` ใน ViewModel

**จุดพัก** — ถ้าแบ่งสอนสองครั้ง ให้หยุดตรงนี้และจดลง `progress.md`

---

## ขั้นที่ 5 — class และ data class

**อธิบาย**

- ของในวงเล็บหลังชื่อ class คือ constructor ใส่ `val` หรือ `var` ข้างหน้าเพื่อให้เป็น property ไปพร้อมกัน
- สร้าง object ไม่ต้องใช้ `new`
- `data class` สำหรับ class ที่มีไว้เก็บข้อมูล ได้ `toString`, `equals` และ `copy` มาให้เอง
- `private set` ทำให้ข้างนอกอ่านได้แต่แก้ไม่ได้

**ให้ผู้เรียนลอง** — รัน `main` แล้วเทียบสองบรรทัดที่พิมพ์ `latte` กับ `somchai` ถามว่าต่างกันอย่างไรและเพราะอะไร

**แบบฝึก** 5.1 `Counter.increment`, 5.2 `MenuItem.label`, 5.3 `raisePrice`

**เฉลย**

```kotlin
fun increment() {
    count++
}

fun label(): String {
    return "$name · $price บาท"
}

fun raisePrice(item: MenuItem, amount: Int): MenuItem {
    return item.copy(price = item.price + amount)
}
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ข้อ 5.3 เขียน `item.price = item.price + amount` | `price` เป็น `val` แก้ไม่ได้ ต้องสร้างตัวใหม่ด้วย `copy` |
| ข้อ 5.3 เขียน `MenuItem(item.name, item.price + amount)` | ถูกต้องและผ่าน test ชวนลองแบบ `copy` ซึ่งไม่ต้องไล่ทุก field เมื่อ class ใหญ่ขึ้น |

**จะเจอที่ไหน**

- `data class GetRecommendedCoffeeResponse(val name: String?, val price: Int?)` รูปร่างของข้อมูลจาก API ทุกตัว
- `class CoffeeMenuViewModel(private val useCase: ...)` การรับของเข้าทาง constructor
- คู่ `_recommendation` (private, แก้ได้) กับ `recommendation` (เปิดให้อ่าน) ใน ViewModel ใช้แนวคิดเดียวกับ `private set`

---

## ขั้นที่ 6 — object และ companion object

**อธิบาย**

- `object` คือของที่มีตัวเดียวทั้งโปรแกรม ไม่ต้องสร้าง เรียกผ่านชื่อ เหมาะกับค่าคงที่และของกลาง
- `companion object` คือของที่เป็นของ class ไม่ใช่ของ object ตัวใดตัวหนึ่ง เรียกผ่านชื่อ class (ใกล้เคียงกับ `static` ในภาษาอื่น)
- `const val` คือค่าคงที่ที่รู้ตั้งแต่ตอน compile

**ให้ผู้เรียนลอง** — เปิด class `FakeIntent` อ่านด้วยกัน: มันคือ "จดหมาย" ที่บอกปลายทางและแนบข้อมูลได้ แล้วดู `MenuScreen.newInstance()` ซึ่งเป็นตัวอย่างที่ทำเสร็จแล้ว

**แบบฝึก** 6.1 `openingText`, 6.2 `DetailScreen.newInstance`, 6.3 `DetailScreen.readCoffeeName`

**เฉลย**

```kotlin
fun openingText(): String {
    return "$SHOP_NAME เปิดแล้ว"
}

fun newInstance(coffeeName: String): FakeIntent {
    return FakeIntent("DetailScreen").apply {
        putExtra(EXTRA_COFFEE_NAME, coffeeName)
    }
}

fun readCoffeeName(intent: FakeIntent): String {
    return intent.getStringExtra(EXTRA_COFFEE_NAME) ?: ""
}
```

ผู้เรียนยังไม่ได้เรียน `apply` (อยู่ในขั้นที่ 8) คำตอบแบบนี้จึงถูกต้องและเป็นแบบที่คาดว่าจะได้:

```kotlin
val intent = FakeIntent("DetailScreen")
intent.putExtra(EXTRA_COFFEE_NAME, coffeeName)
return intent
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ข้อ 6.2 พิมพ์ `"EXTRA_COFFEE_NAME"` เป็นข้อความเอง | ผ่าน test แต่ให้ใช้ค่าคงที่ ถ้าพิมพ์ผิดตัวเดียวฝั่งอ่านจะหาไม่เจอโดยไม่มี error |
| ข้อ 6.3 compile ไม่ผ่านเรื่องชนิด | `getStringExtra` คืน `String?` ต้องปิดกรณี null ด้วย `?:` ทวนขั้นที่ 2 |

**จะเจอที่ไหน** — ทุก Activity มี `companion object { fun newInstance(context, ...): Intent }` และ key ของ extra เป็น `private const val` แบบฝึก 6.2 กับ 6.3 คือสิ่งที่จะเขียนจริงใน workshop session 4 ต่างกันแค่ใช้ `Intent` ตัวจริง
ส่วน `object CoffeeModule` และ `object FlagConstants` คือ `object` แบบในข้อ 6.1

---

## ขั้นที่ 7 — sealed class กับ when

**อธิบาย**

- บางค่าเป็นได้แค่ไม่กี่แบบ และแต่ละแบบพกข้อมูลไม่เหมือนกัน เช่น ผลของการโหลด: กำลังโหลด (ไม่มีข้อมูล), สำเร็จ (มีผลลัพธ์), ผิดพลาด (มีข้อความ)
- `sealed class` ประกาศชุดของแบบที่เป็นไปได้ไว้ครบในที่เดียว
- `when` เลือกทำตามแบบ และ compiler บังคับให้เขียนครบทุกแบบ ถ้าวันหนึ่งมีคนเพิ่มแบบใหม่ ทุกจุดที่ลืมจัดการจะ compile ไม่ผ่าน
- ใน `is LoadState.Success ->` ใช้ `state.menuName` ได้เลย เพราะ Kotlin รู้แล้วว่าเป็นแบบนั้น

**ให้ผู้เรียนลอง** — รัน `main` จากนั้นให้ลบบรรทัด `is LoadState.Error -> "!"` ใน function `icon` แล้วดูว่า IDE บอกอะไร จากนั้นใส่กลับ

**แบบฝึก** 7.1 `describe` และ 7.2 `isFinished`

**เฉลย**

```kotlin
fun describe(state: LoadState): String {
    return when (state) {
        is LoadState.Loading -> "กำลังโหลด"
        is LoadState.Success -> "ได้เมนู ${state.menuName}"
        is LoadState.Error -> "ผิดพลาด: ${state.message}"
    }
}

fun isFinished(state: LoadState): Boolean {
    return state !is LoadState.Loading
}
```

ข้อ 7.2 เขียนด้วย `when` สามกรณีก็ถูกต้อง

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| เขียน `$state.menuName` แล้วได้ข้อความแปลก ๆ | `$` จับแค่ชื่อแรก ต้องใช้ `${state.menuName}` ทวนขั้นที่ 1 |
| ใส่ `else ->` เพื่อให้ compile ผ่าน | ใช้ได้ แต่เสียประโยชน์ของ sealed class เพราะ compiler จะไม่เตือนเมื่อมีแบบใหม่ |

**จะเจอที่ไหน** — `Response` ที่ทุกหน้าจอใช้คือ sealed class ที่มี `Loading`, `Success(value)`, `Error(failure)` หน้าตาเดียวกับ `LoadState` ในขั้นนี้ และ `handleResponse` ข้างในคือ `when` สามกรณีแบบข้อ 7.1

---

## ขั้นที่ 8 — let, apply, by lazy, generic

เป้าหมายของขั้นนี้คือ **อ่านออก** ไม่ต้องเขียนคล่อง

**อธิบาย**

| เขียน | อ่านว่า |
|---|---|
| `x?.let { ... }` | ถ้า `x` ไม่ใช่ null ให้ทำสิ่งนี้กับมัน โดยเรียกมันว่า `it` |
| `Thing().apply { ... }` | สร้างของแล้วตั้งค่าต่อทันที ในปีกกา `this` คือของชิ้นนั้น |
| `val x by lazy { ... }` | ยังไม่สร้างจนกว่าจะถูกใช้ครั้งแรก แล้วเก็บไว้ใช้ต่อ |
| `Box<String>` | `Box` ที่ข้างในเป็น `String` ส่วน `<T>` ในคำประกาศคือ "ชนิดที่คนใช้เป็นคนกำหนด" |

**ให้ผู้เรียนลอง** — ก่อนรัน `main` ให้ทำนายว่าข้อความ `(กำลังเตรียมเมนู... เกิดครั้งเดียว)` ถูกพิมพ์กี่ครั้ง และอยู่ก่อนหรือหลัง `สร้างร้านแล้ว` (คำตอบ: ครั้งเดียว และอยู่หลัง)

**แบบฝึก** 8.1 `shout`, 8.2 `buildOrder`, 8.3 `unwrapOr`

**เฉลย**

```kotlin
fun shout(text: String?): String {
    return text?.let { it.uppercase() + "!" } ?: ""
}

fun buildOrder(menu: String, cups: Int): Order {
    return Order().apply {
        this.menu = menu
        this.cups = cups
    }
}

fun <T> unwrapOr(box: Box<T>?, default: T): T {
    return box?.value ?: default
}
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ข้อ 8.2 เขียน `menu = menu` แล้ว IDE เตือน | ชื่อ parameter ซ้ำกับ property ต้องเขียน `this.menu = menu` |
| ข้อ 8.1 เขียนด้วย `if (text == null)` | ถูกต้อง เป้าหมายคืออ่าน `?.let` ออก ไม่ใช่ต้องใช้ทุกครั้ง |

**จะเจอที่ไหน**

- `currentCoffeeName?.let { openDetail(it) }` "ถ้ามีชื่อเมนูแล้ว ให้เปิดหน้ารายละเอียด"
- `Intent(context, X::class.java).apply { putExtra(...) }`
- `private val viewModel: CoffeeMenuViewModel by viewModel()` ใช้ `by` แบบเดียวกับ `by lazy`: ได้ของมาเมื่อถูกใช้ครั้งแรก
- `LiveData<Response<GetRecommendedCoffeeResponse>>` generic ซ้อนสามชั้น อ่านจากนอกเข้าใน: กล่องที่เฝ้าดูได้ → ของสถานะการโหลด → ของข้อมูลเมนูแนะนำ

---

## ปิดบท — อ่านโค้ดจริง

แสดงโค้ดนี้ให้ผู้เรียน (มาจากเฉลยของ workshop) แล้วถามทีละข้อ รอคำตอบก่อนเฉลย

```kotlin
class CoffeeDetailActivity : BaseCoffeeActivity<ActivityCoffeeDetailBinding>() {

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
}
```

| ถาม | คำตอบ |
|---|---|
| `newInstance` เรียกอย่างไร ต้องสร้าง `CoffeeDetailActivity` ก่อนไหม | `CoffeeDetailActivity.newInstance(...)` ไม่ต้องสร้าง เพราะอยู่ใน `companion object` |
| `.apply { putExtra(...) }` ทำอะไร และ `putExtra` เป็นของใคร | สร้าง `Intent` แล้วใส่ข้อมูลต่อทันที `putExtra` เป็นของ `Intent` ตัวที่เพิ่งสร้าง |
| ทำไมต้องมี `.orEmpty()` | `getStringExtra` คืน `String?` ถ้าไม่มีค่าให้ใช้ข้อความว่างแทน |
| `<ActivityCoffeeDetailBinding>` หลัง `BaseCoffeeActivity` คืออะไร | generic: บอก class แม่ว่าหน้าจอนี้ใช้ binding ชนิดใด |
| ทำไม `EXTRA_COFFEE_NAME` เป็น `private` | ให้มีแต่ class นี้ที่รู้ key คนอื่นต้องส่งข้อมูลผ่าน `newInstance` เท่านั้น |

ถ้าตอบได้ 4 จาก 5 ข้อถือว่าผ่านบทนี้ ส่วนที่ผู้เรียนยังไม่รู้ (`Activity`, `Intent`, `binding`) ให้บอกว่าเป็นเรื่องของบทถัด ๆ ไป ไม่ใช่เรื่องของ Kotlin

**บันทึก** ลง `progress.md`: ขั้นที่ผ่าน, ขั้นที่ใช้คำใบ้หรือดูเฉลย, และเรื่องที่ยังไม่มั่นใจ เพื่อทวนตอนเปิดบทถัดไป
