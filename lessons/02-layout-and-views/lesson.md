# บทที่ 02 — Layout และ View ทุกประเภท

บทสอนนี้เขียนถึง Claude ผู้ดำเนินการสอน วิธีสอนบทเรียนรายหัวข้ออยู่ใน [../README.md](../README.md) อ่านก่อนสอนครั้งแรก

| | |
|---|---|
| ทำไมต้องเรียน | ทุกหน้าจอเริ่มจาก layout และงานแรกของคนใหม่มักเป็นการแก้หน้าตา |
| ผู้เรียน | ผ่านบทที่ 01 แล้ว: เปิดโปรเจกต์ รันแอป และหาไฟล์ใน `res/` ได้ |
| จังหวะ | ไม่จับเวลา ไม่มีจุดพักที่กำหนด ผู้เรียนหยุดตรงไหนก็ได้ ให้จดลง `progress.md` |
| ต้องมีในเครื่อง | Android Studio และ emulator หนึ่งตัว |
| เรียนจบแล้ว | เลือก layout ที่เหมาะกับหน้าจอและบอกเหตุผลได้ วาง View ให้ได้ตำแหน่งที่ต้องการ และเรียก View จากโค้ดผ่าน ViewBinding ได้ |
| บทถัดไป | [03 — Activity และ Life cycle](../03-lifecycle.md) (ยังเป็นโครง) |

บทนี้ไม่ได้ไล่ View ทีละตัว ผู้เรียนประกอบหน้าจอของร้านกาแฟทีละส่วน แบบฝึกเกือบทั้งหมดอยู่ในรูป **"หน้าจอนี้ยังไม่ถูก ทำให้เหมือนภาพเป้าหมาย"**
ชื่อ attribute ที่ต้องใช้อยู่ในหัวข้อ "อธิบาย" ของแต่ละขั้น ให้บอกผู้เรียนก่อนเสมอ แบบฝึกวัดการเลือกใช้ ไม่ได้วัดการจำชื่อ

## โค้ดของบทนี้

อยู่ในโปรเจกต์ [../playground/](../playground/) package `com.learning.playground.lesson02`

```
lessons/playground/app/src/main/
├── java/com/learning/playground/lesson02/
│   ├── Lesson02Activity.kt        สารบัญของบท เขียนไว้แล้ว
│   ├── LayoutDemoActivity.kt      แสดง layout หนึ่งไฟล์เต็มจอ เขียนไว้แล้ว
│   ├── MenuListActivity.kt, MenuAdapter.kt, CoffeeItem.kt    ขั้นที่ 7 เขียนไว้แล้ว
│   ├── QuantityStepperView.kt     custom view ของขั้นที่ 9 เขียนไว้แล้ว
│   ├── OrderSummary.kt            เขียนไว้แล้ว
│   └── OrderFormActivity.kt       ขั้นที่ 9 ผู้เรียนเขียนต่อ
└── res/
    ├── layout/
    │   ├── lesson02_step1_card.xml             ขั้นที่ 1
    │   ├── lesson02_step2_weight_example.xml   ขั้นที่ 2 ตัวอย่าง
    │   ├── lesson02_step2_row_linear.xml       ขั้นที่ 2
    │   ├── lesson02_step3_detail.xml           ขั้นที่ 3
    │   ├── lesson02_step4_row_constraint.xml   ขั้นที่ 4
    │   ├── lesson02_step5_panel.xml            ขั้นที่ 5
    │   ├── activity_order_form.xml             ขั้นที่ 6 และ 9
    │   ├── activity_menu_list.xml, item_menu.xml    ขั้นที่ 7
    │   ├── lesson02_step8_reuse.xml, lesson02_header.xml    ขั้นที่ 8
    │   └── view_quantity_stepper.xml           layout ของ custom view
    └── values/
        ├── strings_lesson02.xml, styles_lesson02.xml, attrs_lesson02.xml
        └── colors.xml, dimens.xml              ใช้ร่วมกันทั้งโปรเจกต์
```

ภาพเป้าหมายของแบบฝึกอยู่ที่ [targets/](targets/) ชื่อ `step1.png` ถึง `step9.png` ถ่ายจากเฉลยที่รันบน emulator ส่งลิงก์ให้ผู้เรียนเปิดดูได้ ภาพไม่ใช่เฉลย

เฉลยอยู่ที่ `solutions/lesson-02/` ใช้ path เดียวกับ `lessons/playground/`

**ตรวจแบบฝึก** — รันจากในโฟลเดอร์ `lessons/playground/` โดยเปลี่ยนเลขตามขั้น:

```
./gradlew :app:testDebugUnitTest --tests "com.learning.playground.lesson02.Step1Test"
./gradlew assembleDebug        # ตรวจว่า build ผ่าน
```

test อ่านไฟล์ XML ของผู้เรียนตรง ๆ และตรวจเฉพาะสิ่งที่ถูกหรือผิดชัดเจน เช่น ใช้ `0dp` ตรงที่ควรใช้
**test ไม่ได้ตรวจหน้าตา** layout มีคำตอบถูกได้หลายแบบ หลัง test ผ่านให้อ่าน XML ของผู้เรียนเอง และให้ผู้เรียนเทียบกับภาพเป้าหมาย
ถ้าหน้าตาตรงแต่ test ไม่ผ่านเพราะผู้เรียนใช้วิธีอื่น ให้ถือว่าถูก แล้วชวนดูวิธีในเฉลย

**ผู้เรียนดูผลได้สองทาง**

- **Layout Editor** เปิดไฟล์ XML แล้วกดปุ่ม Split ที่มุมขวาบน จะเห็นโค้ดกับภาพคู่กัน แก้แล้วภาพเปลี่ยนทันที ใช้ทางนี้เป็นหลัก
- **รันแอป** กด Run แล้วเข้า "บทที่ 02" จากหน้าสารบัญ แต่ละปุ่มเปิดไฟล์ของขั้นนั้น ใช้เมื่อต้องลองกด พิมพ์ หรือเลื่อน

**คำเตือนของ IDE** — Layout Editor ระบายสีเตือนหลายเรื่องที่บทนี้ไม่ได้สอน ถ้าผู้เรียนถาม ให้เอาเมาส์ชี้เพื่ออ่านข้อความด้วยกัน แล้วอธิบายตามที่ข้อความบอก ถ้าไม่รู้ให้บอกว่าไม่รู้ อย่าเดา

## ลำดับ

| ขั้น | เรื่อง | ไฟล์ที่ผู้เรียนแก้ |
|---|---|---|
| 1 | View กับ ViewGroup, ขนาด, หน่วย, `margin` กับ `padding` | `lesson02_step1_card.xml` |
| 2 | `LinearLayout` | `lesson02_step2_row_linear.xml` |
| 3 | `FrameLayout` และ `ScrollView` | `lesson02_step3_detail.xml` |
| 4 | `ConstraintLayout` พื้นฐาน | `lesson02_step4_row_constraint.xml` |
| 5 | chain, guideline, barrier, group | `lesson02_step5_panel.xml` |
| 6 | View พื้นฐานและ `visibility` | `activity_order_form.xml` |
| 7 | `RecyclerView` ระดับ layout | `activity_menu_list.xml`, `item_menu.xml` |
| 8 | การนำกลับมาใช้ | `lesson02_step8_reuse.xml`, `styles_lesson02.xml` |
| 9 | ViewBinding และ custom view | `activity_order_form.xml`, `OrderFormActivity.kt` |
| ปิด | อ่าน layout ของ workshop | |

ขั้นที่ 4 สำคัญที่สุด เพราะ workshop ใช้ `ConstraintLayout` เกือบทุกหน้า อย่าไปขั้นถัดไปจนกว่าผู้เรียนจะอธิบายได้ว่าทำไม View ที่ไม่มี constraint จึงไปกองที่มุมซ้ายบน

---

## ขั้นที่ 1 — View กับ ViewGroup, ขนาด, หน่วย, margin กับ padding

**อธิบาย**

- ทุกอย่างบนจอคือ **View** เช่น `TextView`, `Button` ส่วน **ViewGroup** คือ View ที่มีลูกได้และเป็นคนจัดตำแหน่งของลูก เช่น `LinearLayout` หน้าจอหนึ่งหน้าจึงเป็นต้นไม้ของ View
- View ทุกตัวต้องบอกขนาดสองค่า `layout_width` และ `layout_height`:

| ค่า | ความหมาย |
|---|---|
| `wrap_content` | ใหญ่เท่าที่เนื้อหาข้างในต้องการ |
| `match_parent` | ใหญ่เท่าพื้นที่ที่แม่ให้ |
| ตัวเลข เช่น `56dp` | ขนาดตายตัว |

- หน่วย: ใช้ `dp` กับขนาดและระยะ ใช้ `sp` กับขนาดตัวอักษร `sp` ขยายตามที่ผู้ใช้ตั้งค่าขนาดตัวอักษรของเครื่อง ส่วน `px` ไม่ใช้ เพราะจอแต่ละเครื่องมีความละเอียดไม่เท่ากัน
- ระยะห่างมีสองแบบ:

| | อยู่ที่ไหน | ผล |
|---|---|---|
| `padding` | ด้านใน ระหว่างขอบของ View กับเนื้อหา | พื้นหลังของ View คลุมถึง |
| `layout_margin` | ด้านนอก ระหว่าง View กับสิ่งรอบตัว | พื้นหลังของ View ไม่คลุม |

**ให้ผู้เรียนลอง** — เปิด `lesson02_step1_card.xml` แล้วกด Split การ์ดสีขาวคือ `cardMenu`

1. เปลี่ยน `layout_height` ของ `cardMenu` เป็น `match_parent` ถามว่าเกิดอะไร แล้วเปลี่ยนกลับ
2. ก่อนทำ ให้ทำนาย: ใส่ `android:padding="32dp"` ที่ `cardMenu` กับใส่ `android:layout_margin="32dp"` พื้นที่สีขาวจะต่างกันอย่างไร (คำตอบ: `padding` ทำให้พื้นขาวใหญ่ขึ้น ส่วน `margin` ทำให้พื้นขาวขยับเข้ามาแต่ขนาดเท่าเดิม) ลองทั้งสองแบบแล้วลบออก

**แบบฝึก** ทำให้เหมือน [targets/step1.png](targets/step1.png)

- 1.1 ให้การ์ดกว้างเต็มจอ ห่างจากขอบจอ 16dp ทุกด้าน และตัวอักษรห่างจากขอบการ์ด 16dp ทุกด้าน
- 1.2 ชื่อเมนูใช้หน่วยของขนาดตัวอักษรผิด แก้ให้ถูก

**เฉลย**

```xml
<LinearLayout
    android:id="@+id/cardMenu"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="16dp"
    android:padding="16dp"
    ...>

    <TextView
        android:id="@+id/tvName"
        android:textSize="20sp"
        ... />
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ใส่ `padding="16dp"` ที่ layout ตัวนอกสุดแทน `layout_margin` ที่การ์ด | ภาพเหมือนกันในกรณีนี้ และ test ไม่ผ่าน ให้ถือว่าเข้าใจถูก แล้วถามว่าถ้ามีการ์ดสองใบซ้อนกัน สองวิธีจะต่างกันตรงไหน |
| เขียน `android:margin` | ไม่มี attribute นี้ ต้องเป็น `android:layout_margin` ทุกอย่างที่ขึ้นต้นด้วย `layout_` คือคำขอที่ View ส่งให้แม่ |
| ถามว่า `20dp` กับ `20sp` ต่างกันตรงไหน เพราะภาพเท่ากัน | ต่างเมื่อผู้ใช้ตั้งขนาดตัวอักษรของเครื่องให้ใหญ่ขึ้น `sp` ขยายตาม `dp` ไม่ขยาย |

**จะเจอที่ไหน** — ทุกไฟล์ layout ของ workshop workshop เก็บระยะไว้เป็นชื่อ เช่น `@dimen/default_16` แทนการเขียน `16dp` ตรง ๆ ซึ่งเป็นเรื่องของขั้นที่ 8

---

## ขั้นที่ 2 — LinearLayout

**อธิบาย**

- `LinearLayout` เรียงลูกต่อกันเป็นเส้นเดียว `android:orientation` เลือกว่าเป็นแนวตั้ง (`vertical`) หรือแนวนอน (`horizontal`)
- `android:layout_weight` แบ่งพื้นที่ที่เหลือให้ลูกตามสัดส่วน วิธีใช้ที่พบบ่อย: ตั้งขนาดด้านที่จะแบ่งเป็น `0dp` แล้วใส่ `layout_weight="1"` แปลว่า "เอาพื้นที่ที่เหลือทั้งหมด"
- สองคำที่คล้ายกัน:

| | ใส่ที่ | จัดอะไร |
|---|---|---|
| `android:gravity` | ตัวแม่ | จัดตำแหน่งของลูกทุกตัวข้างใน |
| `android:layout_gravity` | ตัวลูก | จัดตำแหน่งของตัวเองในแม่ |

- ถ้าต้องการทั้งแนวนอนและแนวตั้งในก้อนเดียว ต้องซ้อน `LinearLayout` อีกชั้น

**ให้ผู้เรียนลอง** — เปิด `lesson02_step2_weight_example.xml` มีสามกล่องที่ `layout_weight="1"` เท่ากัน ก่อนแก้ให้ทำนาย: ถ้าเปลี่ยนกล่องกลางเป็น `layout_weight="2"` กล่องกลางจะกว้างเป็นกี่ส่วนของจอ (คำตอบ: ครึ่งจอ เพราะได้ 2 จาก 4 ส่วน) แล้วลองดู

**แบบฝึก** 2.1 ใน `lesson02_step2_row_linear.xml` ทุกอย่างเรียงลงมาเป็นแนวตั้ง ทำให้เป็นแถวเมนูแบบ [targets/step2.png](targets/step2.png): รูปอยู่ซ้าย ชื่อกับคำอธิบายซ้อนกันอยู่กลางและกินพื้นที่ที่เหลือ ราคาอยู่ขวาสุด ทุกอย่างอยู่กึ่งกลางในแนวตั้ง

คำใบ้ขั้นแรกถ้าขอ: ต้องมี `LinearLayout` สองชั้น ชั้นนอกแนวนอน ชั้นในแนวตั้งห่อชื่อกับคำอธิบาย

**เฉลย**

```xml
<LinearLayout
    android:id="@+id/rowMenu"
    android:gravity="center_vertical"
    android:orientation="horizontal"
    ...>

    <ImageView android:id="@+id/ivCoffee" ... />

    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_weight="1"
        android:orientation="vertical">

        <TextView android:id="@+id/tvName" ... />
        <TextView android:id="@+id/tvDescription" ... />
    </LinearLayout>

    <TextView android:id="@+id/tvPrice" ... />
</LinearLayout>
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ราคาหายไปจากจอ | ก้อนกลางกว้าง `match_parent` จึงกินที่ทั้งหมด ต้องเป็น `0dp` คู่กับ `layout_weight="1"` |
| ใส่ `layout_weight` แล้วไม่เกิดอะไร | ขนาดยังเป็น `wrap_content` weight แบ่งเฉพาะพื้นที่ที่ **เหลือ** |
| ใช้ `layout_gravity="center_vertical"` ที่ลูกทุกตัวแทน `gravity` ที่แม่ | ถูกต้อง ผลเหมือนกัน |
| เปลี่ยนเป็น `horizontal` แล้วเห็นแค่รูป | ยังไม่ได้ห่อชื่อกับคำอธิบาย ทั้งสี่ชิ้นจึงเรียงแนวนอนและล้นจอ |

**จะเจอที่ไหน** — `LinearLayout` เหมาะกับของที่เรียงเส้นเดียว เช่น ปุ่มที่เรียงลงมา พอต้องซ้อนหลายชั้นจะอ่านยากและวาดช้า นั่นคือเหตุผลของขั้นที่ 4

---

## ขั้นที่ 3 — FrameLayout และ ScrollView

**อธิบาย**

- `FrameLayout` วางลูกทุกตัว **ซ้อนกัน** ที่จุดเดียว ลูกที่เขียนทีหลังอยู่ข้างบน ใช้ `android:layout_gravity` ที่ลูกเพื่อเลือกมุม เช่น `top|end` หรือ `center` เหมาะกับการวางของทับกัน เช่น ป้ายบนรูป
- `start` กับ `end` คือซ้ายกับขวาสำหรับภาษาที่อ่านจากซ้ายไปขวา และสลับให้เองในภาษาที่อ่านจากขวาไปซ้าย ใช้คู่นี้แทน `left` กับ `right`
- `ScrollView` ทำให้เนื้อหาที่ยาวเกินจอเลื่อนได้ มีกฎข้อเดียว: **มีลูกได้ตัวเดียว** ถ้ามีหลายชิ้นต้องห่อด้วย layout อีกชั้น และลูกตัวนั้นควรสูง `wrap_content`
- `NestedScrollView` ทำงานเหมือนกัน ใช้เมื่อมีของที่เลื่อนได้ซ้อนอยู่ข้างใน หรือใช้ร่วมกับ `CoordinatorLayout`

**ให้ผู้เรียนลอง** — รันแอป เข้าบทที่ 02 แล้วกด "ขั้นที่ 3" ถามว่าปุ่ม "สั่งแก้วนี้" อยู่ที่ไหน (ไม่เห็น เพราะเนื้อหายาวเกินจอและเลื่อนไม่ได้) และป้าย "ขายดี" อยู่ตรงไหน (ใต้รูป)

**แบบฝึก** ทำให้เหมือน [targets/step3.png](targets/step3.png)

- 3.1 ย้ายป้าย `tvBadge` ไปซ้อนบนรูปที่มุมขวาบน ห่างจากขอบรูป 8dp
- 3.2 ทำให้ทั้งหน้าเลื่อนได้ จนกดปุ่ม "สั่งแก้วนี้" ได้

**เฉลย**

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <FrameLayout android:id="@+id/frameImage" ...>
            <ImageView android:id="@+id/ivCoffee" android:layout_gravity="center" ... />
            <TextView
                android:id="@+id/tvBadge"
                android:layout_gravity="top|end"
                android:layout_margin="8dp"
                ... />
        </FrameLayout>
        ...
    </LinearLayout>
</ScrollView>
```

`xmlns:android` ต้องย้ายไปอยู่ที่ tag นอกสุดตัวใหม่ IDE จะเตือนและเสนอแก้ให้

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ใส่ `android:gravity="top\|end"` ที่ป้าย | นั่นจัดตัวอักษรข้างในป้าย ต้องเป็น `layout_gravity` |
| build ไม่ผ่านหลังห่อด้วย `ScrollView` | มีลูกมากกว่าหนึ่งตัว หรือ `xmlns:android` ยังอยู่ที่ tag เดิม |
| ห่อแล้วยังเลื่อนไม่ได้ | ลูกของ `ScrollView` สูง `match_parent` ให้เปลี่ยนเป็น `wrap_content` |
| ป้ายถูกรูปบัง | เขียนป้ายไว้ก่อนรูป ลูกที่เขียนทีหลังอยู่ข้างบน |

**จะเจอที่ไหน** — หน้าฟอร์มและหน้ารายละเอียดเกือบทั้งหมดมี `ScrollView` หรือ `NestedScrollView` เป็นชั้นนอกสุด เพราะจอเล็กและแป้นพิมพ์กินพื้นที่

---

## ขั้นที่ 4 — ConstraintLayout พื้นฐาน

**อธิบาย** — ขั้นนี้สำคัญที่สุดของบท

- `ConstraintLayout` ไม่ได้เรียงลูกให้ ลูกแต่ละตัวต้อง **บอกเองว่าขอบของตัวเองผูกกับอะไร** เรียกว่า constraint
- รูปของ constraint: `app:layout_constraint[ขอบของฉัน]_to[ขอบของเป้า]Of="เป้า"` เช่น

| เขียน | อ่านว่า |
|---|---|
| `app:layout_constraintStart_toStartOf="parent"` | ขอบซ้ายของฉันชิดขอบซ้ายของแม่ |
| `app:layout_constraintStart_toEndOf="@id/ivCoffee"` | ขอบซ้ายของฉันต่อจากขอบขวาของรูป |
| `app:layout_constraintTop_toBottomOf="@id/tvName"` | ขอบบนของฉันอยู่ใต้ชื่อ |

- View ต้องมี constraint อย่างน้อยหนึ่งตัวในแนวนอนและหนึ่งตัวในแนวตั้ง **ถ้าไม่มี มันจะไปอยู่ที่มุมซ้ายบน** และ IDE ขีดเส้นแดงเตือน
- ผูกสองด้านตรงข้ามกัน เช่น ทั้ง `Top` และ `Bottom` กับ `parent` View จะอยู่กึ่งกลาง
- ขนาด `0dp` ใน `ConstraintLayout` แปลว่า "ยืดให้เต็มระหว่าง constraint สองด้าน" ไม่ใช้ `match_parent` กับลูกของ `ConstraintLayout`
- `margin` ทำงานได้เฉพาะด้านที่มี constraint
- attribute ที่ขึ้นต้นด้วย `tools:` เช่น `tools:text` แสดงเฉพาะใน Layout Editor ไม่ไปอยู่ในแอปจริง ใช้ใส่ข้อมูลตัวอย่างให้เห็นภาพ

**ให้ผู้เรียนลอง** — เปิด `lesson02_step4_row_constraint.xml` รูปกับราคามี constraint ครบแล้ว ถามสองข้อ:

1. ชื่อกับคำอธิบายอยู่ตรงไหนในภาพ และทำไม (ซ้อนกันที่มุมซ้ายบนทับรูป เพราะไม่มี constraint)
2. อ่าน constraint สามบรรทัดของ `tvPrice` แล้วบอกเป็นคำพูดว่าราคาอยู่ตรงไหน (ชิดขวาของแม่ กึ่งกลางแนวตั้ง)

**แบบฝึก** 4.1 เพิ่ม constraint ให้ `tvName` และ `tvDescription` จนได้แถวเมนูหน้าตาเดียวกับขั้นที่ 2 ตาม [targets/step4.png](targets/step4.png) โดย **ห้ามเพิ่ม layout ซ้อน**

- ชื่ออยู่ระหว่างรูปกับราคา ห่างจากรูป 16dp และยืดเต็มช่องว่าง
- คำอธิบายอยู่ใต้ชื่อ ขอบซ้ายและขวาตรงกับชื่อ

**เฉลย**

```xml
<TextView
    android:id="@+id/tvName"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginStart="16dp"
    android:layout_marginEnd="8dp"
    app:layout_constraintEnd_toStartOf="@id/tvPrice"
    app:layout_constraintStart_toEndOf="@id/ivCoffee"
    app:layout_constraintTop_toTopOf="parent"
    ... />

<TextView
    android:id="@+id/tvDescription"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    app:layout_constraintEnd_toEndOf="@id/tvName"
    app:layout_constraintStart_toStartOf="@id/tvName"
    app:layout_constraintTop_toBottomOf="@id/tvName"
    ... />
```

**หลังทำเสร็จ ให้เทียบกับขั้นที่ 2** — เปิดสองไฟล์คู่กันแล้วถาม: แบบไหนมี layout ซ้อนกี่ชั้น และถ้าต้องเพิ่มป้าย "ใหม่" ที่มุมขวาบนของรูป แบบไหนแก้ง่ายกว่า

| | `LinearLayout` (ขั้นที่ 2) | `ConstraintLayout` (ขั้นที่ 4) |
|---|---|---|
| จำนวนชั้น | สองชั้น | ชั้นเดียว |
| อ่านโค้ด | เห็นลำดับจากบนลงล่างทันที | ต้องไล่ว่าใครผูกกับใคร |
| เพิ่มของที่ไม่ได้อยู่ในแถว | ต้องห่อเพิ่ม | เพิ่ม View แล้วผูก constraint |
| เหมาะกับ | ของที่เรียงเส้นเดียว | หน้าจอที่มีหลายส่วนสัมพันธ์กัน |

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ชื่อยาวแล้วทับราคา | `layout_width` ยังเป็น `wrap_content` ต้องเป็น `0dp` และมี constraint ทั้ง `Start` และ `End` |
| ใส่ `layout_marginStart` แล้วไม่ขยับ | ด้านนั้นยังไม่มี constraint |
| เขียน `@+id/ivCoffee` ใน constraint | ใช้ได้ `@+id/` แปลว่า "สร้างถ้ายังไม่มี" ส่วน `@id/` แปลว่า "อ้างถึงที่มีอยู่" แบบหลังอ่านชัดกว่า |
| `app:` เป็นสีแดง | tag นอกสุดยังไม่มี `xmlns:app` ไฟล์นี้มีให้แล้ว ถ้าเจอในไฟล์อื่นให้กด Option+Enter |
| ใช้ `layout_constraintLeft_toRightOf` | ทำงานได้ แต่ให้ใช้ `Start`/`End` ตามที่อธิบายในขั้นที่ 3 |

**จะเจอที่ไหน** — layout ของ workshop เกือบทุกไฟล์มี `ConstraintLayout` เป็นชั้นนอกสุด และแถวของรายการเมนูใน workshop มีโครงเดียวกับแบบฝึกนี้: ชื่อกว้าง `0dp` ผูกระหว่างขอบซ้ายกับราคา

---

## ขั้นที่ 5 — chain, guideline, barrier, group

เครื่องมือสี่ตัวของ `ConstraintLayout` ที่ใช้แทนการซ้อน layout ผู้เรียนทำแบบฝึกกับ chain และ barrier ส่วน guideline และ group ให้ลองแก้ค่าดู

**อธิบาย**

| ตัว | ใช้ทำอะไร | เขียนอย่างไร |
|---|---|---|
| chain | กระจาย View หลายตัวให้เต็มช่วง | ผูก View ต่อกันเป็นสาย **ทั้งสองทิศ**: A ผูกขวากับ B และ B ผูกซ้ายกับ A ต่อไปจนสุด |
| guideline | เส้นอ้างอิงที่มองไม่เห็น เช่น กึ่งกลางจอ | `<Guideline>` กับ `app:layout_constraintGuide_percent="0.5"` แล้วให้ View อื่นผูกกับมัน |
| barrier | เส้นที่ขยับตาม View ที่ **ยื่นออกมามากที่สุด** ในกลุ่ม | `<Barrier>` กับ `app:barrierDirection="end"` และ `app:constraint_referenced_ids="a,b"` |
| group | ซ่อนหรือแสดง View หลายตัวพร้อมกัน | `<Group>` กับ `app:constraint_referenced_ids="a,b"` แล้วตั้ง `android:visibility` ที่ group |

- chain ต่างจากการผูกทางเดียวตรงที่ทุกตัวรู้จักเพื่อนทั้งสองข้าง ระบบจึงแบ่งช่องว่างให้เท่ากันได้
- ใน `constraint_referenced_ids` เขียนชื่อ id เฉย ๆ คั่นด้วยจุลภาค ไม่มี `@id/`

**ให้ผู้เรียนลอง** — เปิด `lesson02_step5_panel.xml`

1. หา `guideHalf` แล้วเปลี่ยน `layout_constraintGuide_percent` จาก `0.5` เป็น `0.3` ถามว่ากล่อง "ร้อน" กับ "เย็น" เปลี่ยนอย่างไร แล้วเปลี่ยนกลับ
2. หา `groupDelivery` ซึ่งตั้ง `visibility="gone"` ไว้ เปลี่ยนเป็น `visible` ถามว่าอะไรโผล่ขึ้นมา (ป้ายและค่าของที่อยู่ สองตัวพร้อมกัน) แล้วเปลี่ยนกลับ

**แบบฝึก** ทำให้เหมือน [targets/step5.png](targets/step5.png)

- 5.1 ปุ่ม S, M, L กองซ้อนกันที่มุมซ้าย ทำให้กระจายเท่ากันเต็มความกว้างด้วย chain
- 5.2 ค่าของ "เบอร์โทรศัพท์" ทับป้ายของตัวเอง เพราะค่าทั้งสองบรรทัดผูกกับป้าย "ชื่อ" ซึ่งสั้นกว่า เพิ่ม barrier ชื่อ `barrierLabels` แล้วให้ค่าทั้งสองเริ่มหลัง barrier

**เฉลย**

```xml
<Button
    android:id="@+id/btnSizeS"
    app:layout_constraintEnd_toStartOf="@id/btnSizeM"
    app:layout_constraintStart_toStartOf="parent"
    ... />

<Button
    android:id="@+id/btnSizeM"
    app:layout_constraintEnd_toStartOf="@id/btnSizeL"
    app:layout_constraintStart_toEndOf="@id/btnSizeS"
    ... />

<Button
    android:id="@+id/btnSizeL"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toEndOf="@id/btnSizeM"
    ... />

<androidx.constraintlayout.widget.Barrier
    android:id="@+id/barrierLabels"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:barrierDirection="end"
    app:constraint_referenced_ids="tvLabelName,tvLabelPhone" />

<TextView
    android:id="@+id/tvValueName"
    app:layout_constraintStart_toEndOf="@id/barrierLabels"
    ... />

<TextView
    android:id="@+id/tvValuePhone"
    app:layout_constraintStart_toEndOf="@id/barrierLabels"
    ... />
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ปุ่มเรียงต่อกันแต่ชิดซ้าย ไม่กระจาย | ผูกทางเดียว เช่น M ผูกกับ S แต่ S ไม่ได้ผูกกลับไปหา M |
| ปุ่มสุดท้ายยังกองอยู่ | ลืมผูก `End` ของปุ่มสุดท้ายกับ `parent` |
| barrier ไม่มีผล | เขียน `@id/` ใน `constraint_referenced_ids` หรือค่ายังผูกกับป้ายตัวเดิม |
| แก้ข้อ 5.2 ด้วยการผูกค่ากับป้าย "เบอร์โทรศัพท์" | ภาพถูกในตอนนี้ แต่ถ้าเปลี่ยนภาษาแล้วป้าย "ชื่อ" ยาวกว่า จะทับอีก barrier แก้ได้ทุกกรณี |
| ถามว่า `barrierDirection="end"` คืออะไร | barrier อยู่ที่ขอบ `end` ของ View ที่ยื่นไปทางนั้นมากที่สุด |

**จะเจอที่ไหน** — group ใช้บ่อยกับการซ่อนทั้งก้อนตอนโหลดข้อมูล ส่วน barrier เจอในฟอร์มที่ป้ายยาวไม่เท่ากัน

---

## ขั้นที่ 6 — View พื้นฐานและ visibility

**อธิบาย** — View ที่ใช้รับข้อมูลจากผู้ใช้ เปิด `activity_order_form.xml` แล้วไล่ดูไปพร้อมกัน

| View | ใช้ทำอะไร | attribute ที่ควรรู้ |
|---|---|---|
| `TextView` | แสดงข้อความ | `text`, `textSize`, `textColor`, `maxLines`, `ellipsize` |
| `ImageView` | แสดงรูป | `src`, `scaleType`, `contentDescription` (ข้อความสำหรับโปรแกรมอ่านหน้าจอ) |
| `TextInputLayout` + `TextInputEditText` | ช่องกรอกแบบมีป้ายลอย คู่นี้ใช้แทน `EditText` เปล่า | `hint` ใส่ที่ตัวนอก, `inputType` ใส่ที่ตัวใน |
| `Button` | ปุ่ม | `text`, `enabled` |
| `RadioGroup` + `RadioButton` | เลือกได้หนึ่งจากหลายตัว `RadioGroup` คือสิ่งที่ทำให้เลือกได้ทีละตัว | `checkedButton`, `orientation` |
| `CheckBox` | ติ๊กได้อิสระ | `checked` |
| `SwitchMaterial` | เปิดหรือปิด | `checked` |
| `ProgressBar` | วงหมุนตอนรอ | `visibility` |

- `android:inputType` บอกชนิดของข้อมูล ระบบใช้เลือกแป้นพิมพ์ที่เหมาะ เช่น `phone` ได้แป้นตัวเลข
- `RadioGroup` เป็นลูกของ `LinearLayout` จึงมี `orientation` เหมือนกัน
- `android:visibility` มีสามค่า:

| ค่า | เห็นไหม | กินพื้นที่ไหม |
|---|---|---|
| `visible` | เห็น | กิน |
| `invisible` | ไม่เห็น | **กิน** |
| `gone` | ไม่เห็น | ไม่กิน |

- `tools:visibility="visible"` ทำให้ยังเห็นใน Layout Editor แม้ตั้ง `gone` ไว้

**ให้ผู้เรียนลอง** — รันแอป เข้า "ขั้นที่ 6 และ 9" แล้วแตะช่อง "เบอร์โทรศัพท์" ถามว่าแป้นพิมพ์ที่ขึ้นมาเป็นแบบใด (ตัวอักษร) จากนั้นถามว่าเห็นอะไรแปลกระหว่างสวิตช์ "จัดส่งถึงบ้าน" กับปุ่ม "สั่งเลย" (ช่องว่างใหญ่ และวงหมุนที่หมุนตลอด)

**แบบฝึก** ทำให้เหมือน [targets/step6.png](targets/step6.png)

- 6.1 ให้ช่อง "เบอร์โทรศัพท์" ขึ้นแป้นพิมพ์ตัวเลขสำหรับโทรศัพท์
- 6.2 ให้ตัวเลือกขนาดแก้วเรียงเป็นแนวนอน และเลือก M ไว้ตั้งแต่เปิดหน้าจอ
- 6.3 ช่อง "ที่อยู่จัดส่ง" และวงหมุนยังไม่ควรเห็นและไม่ควรกินพื้นที่

**เฉลย**

```xml
<com.google.android.material.textfield.TextInputEditText
    android:id="@+id/etPhone"
    android:inputType="phone"
    ... />

<RadioGroup
    android:id="@+id/rgSize"
    android:checkedButton="@id/rbSizeM"
    android:orientation="horizontal"
    ...>

<com.google.android.material.textfield.TextInputLayout
    android:id="@+id/tilAddress"
    android:visibility="gone"
    ...>

<ProgressBar
    android:id="@+id/progressOrder"
    android:visibility="gone"
    ... />
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| ใส่ `inputType` ที่ `TextInputLayout` | ต้องใส่ที่ `TextInputEditText` ตัวใน ตัวนอกเป็นแค่กรอบและป้าย |
| ใส่ `android:checked="true"` ที่ `rbSizeM` แทน `checkedButton` | ทำงานได้และภาพถูก test ไม่ผ่าน ให้ถือว่าถูก แล้วบอกว่า `checkedButton` ที่ group ทำให้เห็นค่าเริ่มต้นในที่เดียว |
| ตั้ง `invisible` แล้วช่องว่างยังอยู่ | `invisible` ยังกินพื้นที่ ต้องเป็น `gone` |
| ตั้ง `gone` แล้วหาช่องที่อยู่ใน Layout Editor ไม่เจอ | เพิ่ม `tools:visibility="visible"` ได้ |

**จะเจอที่ไหน** — workshop ซ่อนและแสดงวงหมุนตอนเรียก API ด้วยการสลับ `visibility` ระหว่าง `visible` กับ `gone` จากโค้ด ซึ่งผู้เรียนจะทำเองในขั้นที่ 9

---

## ขั้นที่ 7 — RecyclerView ระดับ layout

ขั้นนี้สอน `RecyclerView` ในฐานะ View ตัวหนึ่งที่วางลงในหน้าจอ การเขียน Adapter อยู่ในบทที่ 11 ถ้าผู้เรียนถามถึง `MenuAdapter.kt` ให้บอกว่าเปิดดูได้ แต่ยังไม่ต้องเข้าใจ

**อธิบาย**

- `RecyclerView` แสดงรายการที่มีจำนวนไม่แน่นอน และสร้าง View เฉพาะแถวที่อยู่บนจอ จึงเลื่อนรายการเป็นพันแถวได้ลื่น
- ใช้ layout สองไฟล์เสมอ:

| ไฟล์ | คืออะไร |
|---|---|
| layout ของหน้าจอ (`activity_menu_list.xml`) | มี `RecyclerView` หนึ่งตัว บอกว่ารายการอยู่ตรงไหนของจอ |
| layout ของหนึ่งแถว (`item_menu.xml`) | หน้าตาของรายการหนึ่งชิ้น ถูกใช้ซ้ำทุกแถว |

- **LayoutManager** กำหนดว่าแถวเรียงกันอย่างไร ตั้งใน XML ได้ด้วย `app:layoutManager`:

| ค่า | ผล |
|---|---|
| `androidx.recyclerview.widget.LinearLayoutManager` | เรียงเส้นเดียว แนวตั้งเป็นค่าเริ่มต้น เปลี่ยนด้วย `android:orientation` |
| `androidx.recyclerview.widget.GridLayoutManager` | ตาราง กำหนดจำนวนคอลัมน์ด้วย `app:spanCount` |

- Layout Editor ไม่รู้ว่ารายการจะมีข้อมูลอะไร จึงแสดง `RecyclerView` เป็นรายการเปล่า ใส่ `tools:listitem="@layout/item_menu"` เพื่อให้เห็นตัวอย่างด้วย layout ของแถวจริง
- ส่วนสูงของ layout หนึ่งแถวต้องเป็น `wrap_content` ถ้าเป็น `match_parent` แถวเดียวจะสูงเต็มจอ

**ให้ผู้เรียนลอง** — รันแอป เข้า "ขั้นที่ 7" ถามว่าเห็นเมนูกี่รายการบนจอ และต้องทำอย่างไรจึงเห็นรายการถัดไป (เห็นรายการเดียว ต้องเลื่อนทีละหนึ่งหน้าจอ) จากนั้นเปิด `activity_menu_list.xml` ใน Layout Editor ถามว่าเห็นอะไร

**แบบฝึก** ทำให้เหมือน [targets/step7.png](targets/step7.png)

- 7.1 ทำให้ Layout Editor แสดงตัวอย่างรายการด้วย layout ของแถวจริง
- 7.2 แก้ให้หนึ่งแถวสูงเท่าเนื้อหาของตัวเอง
- 7.3 เปลี่ยนรายการเป็นตารางสองคอลัมน์ โดยแก้ที่ XML อย่างเดียว

**เฉลย**

```xml
<!-- activity_menu_list.xml -->
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/rvMenu"
    app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
    app:spanCount="2"
    tools:listitem="@layout/item_menu"
    ... />

<!-- item_menu.xml: tag นอกสุด -->
android:layout_height="wrap_content"
```

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| แก้ส่วนสูงที่ `RecyclerView` แทนที่ `item_menu.xml` | `RecyclerView` ต้องเต็มจอ สิ่งที่สูงเกินคือแถว |
| ใส่ `tools:listitem` แล้วแอปจริงไม่เปลี่ยน | ถูกแล้ว `tools:` มีผลเฉพาะใน Layout Editor |
| พิมพ์ชื่อ LayoutManager ผิดแล้วแอปปิดตัวตอนเปิดหน้านี้ | ชื่อ class ใน XML ไม่ถูกตรวจตอน build ให้อ่าน `Caused by` ใน Logcat แบบบทที่ 01 |
| ใส่ `spanCount` แล้วยังเป็นคอลัมน์เดียว | LayoutManager ยังเป็น `LinearLayoutManager` |

**จะเจอที่ไหน** — workshop session 5 ผู้เรียนจะเขียน Adapter ของรายการเมนูเอง layout ของแถวคือ `item_coffee_menu.xml` และบทที่ 11 สอนกลไกของ Adapter กับ ViewHolder

---

## ขั้นที่ 8 — การนำกลับมาใช้

**อธิบาย** — ค่าเดียวกันที่เขียนซ้ำหลายที่ ต้องแก้ทุกที่เมื่อเปลี่ยน Android มีที่เก็บกลางสำหรับแต่ละชนิด

| เก็บอะไร | ที่ไหน | อ้างถึงด้วย |
|---|---|---|
| สี | `res/values/colors.xml` | `@color/appBrown` |
| ระยะและขนาด | `res/values/dimens.xml` | `@dimen/default_16` |
| ชุดของ attribute ที่ใช้ด้วยกันบ่อย | `<style>` ใน `res/values/` | `style="@style/ชื่อ"` (ไม่มี `android:` นำหน้า) |
| ก้อนของ layout | ไฟล์ layout แยก | `<include layout="@layout/ชื่อไฟล์" />` |

- **style** คือชุดของ attribute ที่ตั้งชื่อไว้ View ที่ใช้ style จะได้ทุก attribute ในชุดนั้น และยังเขียนทับเฉพาะตัวได้
- **theme** คือ style ที่ใช้กับทั้งแอปหรือทั้งหน้าจอ อยู่ใน `themes.xml` และถูกเลือกใน Manifest (ที่เห็นในบทที่ 01) กำหนดสีหลักและหน้าตาเริ่มต้นของ View ทุกชนิด
- `<include>` วาง layout อีกไฟล์ลงตรงนั้นทั้งก้อน
- `<merge>` ใช้เป็น tag นอกสุดของไฟล์ที่จะถูก include เพื่อไม่ให้เกิด layout ซ้อนเกินมาหนึ่งชั้น ตัวอย่างอยู่ใน `view_quantity_stepper.xml`

**ให้ผู้เรียนลอง** — เปิด `lesson02_step8_reuse.xml` ถามว่าสี `#6F4E37` ปรากฏกี่ครั้ง (สามครั้ง) และถ้าร้านเปลี่ยนสีต้องแก้กี่ที่ จากนั้นเปิด `styles_lesson02.xml` ดู style `MenuTitle` ที่ทำไว้เป็นตัวอย่าง

**แบบฝึก** หน้าตาต้องเหมือนเดิมทุกจุดตาม [targets/step8.png](targets/step8.png) สิ่งที่เปลี่ยนคือโค้ด

- 8.1 ก้อนหัวของหน้าจอ (รูปกับชื่อร้าน) มีอยู่แล้วในไฟล์ `lesson02_header.xml` แทนก้อนที่เขียนซ้ำด้วย `<include>`
- 8.2 แทนสีและระยะที่เขียนเป็นค่าตรง ๆ ด้วยชื่อจาก `colors.xml` และ `dimens.xml`
- 8.3 ข้อความสามบรรทัดมี attribute ชุดเดียวกัน สร้าง style ชื่อ `ShopInfoLabel` ใน `styles_lesson02.xml` แล้วให้ทั้งสามบรรทัดใช้

**เฉลย**

```xml
<!-- styles_lesson02.xml -->
<style name="ShopInfoLabel">
    <item name="android:layout_width">match_parent</item>
    <item name="android:layout_height">wrap_content</item>
    <item name="android:paddingTop">@dimen/default_16</item>
    <item name="android:textColor">@color/appBrown</item>
    <item name="android:textSize">16sp</item>
</style>

<!-- lesson02_step8_reuse.xml -->
<LinearLayout
    android:background="@color/appCream"
    android:padding="@dimen/default_16"
    ...>

    <include layout="@layout/lesson02_header" />

    <TextView
        android:id="@+id/tvLabelOpen"
        style="@style/ShopInfoLabel"
        android:text="@string/reuse_open" />
    ...
```

`layout_width` และ `layout_height` จะอยู่ใน style หรือคงไว้ที่ View ก็ได้ ทั้งสองแบบผ่าน test

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| เขียน `android:style="..."` | `style` ไม่มี `android:` นำหน้า |
| ใน `<item>` เขียน `name="textSize"` | ต้องมี `android:` นำหน้า: `name="android:textSize"` |
| เขียน `<include android:layout="...">` | `layout` ของ `<include>` ไม่มี `android:` นำหน้าเช่นกัน |
| ย้ายสีไปไว้ใน style แต่ยังเป็น `#6F4E37` | test ไม่ผ่าน ค่าใน style ก็ควรอ้างถึง `@color/` |
| ถามว่า `16sp` ต้องย้ายไป `dimens.xml` ด้วยไหม | ย้ายได้ บทนี้ไม่บังคับ ทีมส่วนใหญ่เก็บขนาดตัวอักษรไว้ใน style |

**จะเจอที่ไหน**

- `@dimen/default_16`, `@color/appBrown` เป็นชื่อเดียวกับที่ workshop ใช้ อยู่ใน module `core`
- ปุ่มขอบเส้นใน workshop ใช้ style ที่ library เตรียมให้: `style="@style/Widget.MaterialComponents.Button.OutlinedButton"`

---

## ขั้นที่ 9 — ViewBinding และ custom view

**อธิบาย**

- **ViewBinding** สร้าง class ให้หนึ่งตัวต่อหนึ่งไฟล์ layout ชื่อ class มาจากชื่อไฟล์: `activity_order_form.xml` กลายเป็น `ActivityOrderFormBinding`
- View ที่มี `android:id` กลายเป็น property ชื่อเดียวกัน: `@+id/btnSubmit` กลายเป็น `binding.btnSubmit` View ที่ไม่มี id เรียกจากโค้ดไม่ได้
- ชนิดของ property ตรงกับ tag ใน XML จึงเรียก function ของ View ชนิดนั้นได้เลย และถ้าพิมพ์ชื่อผิดจะ compile ไม่ผ่าน
- สามบรรทัดที่ทุก Activity มี:

```kotlin
binding = ActivityOrderFormBinding.inflate(layoutInflater)   // สร้าง View จาก XML
setContentView(binding.root)                                 // แสดงบนจอ
binding.btnSubmit.setOnClickListener { ... }                 // ใช้งาน
```

- สิ่งที่ทำกับ View จากโค้ดบ่อย:

| ต้องการ | เขียน |
|---|---|
| ตั้งข้อความ | `binding.tvSummary.text = "..."` |
| อ่านข้อความที่ผู้ใช้พิมพ์ | `binding.etName.text.toString()` |
| อ่านว่าติ๊กอยู่ไหม | `binding.cbExtraShot.isChecked` |
| ซ่อนหรือแสดง | `binding.tilAddress.isVisible = true` (`false` คือ `gone`) |
| รู้เมื่อสวิตช์หรือ checkbox เปลี่ยน | `setOnCheckedChangeListener { _, isChecked -> ... }` |

- **custom view** คือ View ที่ทีมเขียนเอง ใช้ใน XML ได้เหมือน View ทั่วไป ต่างกันที่ tag ต้องเป็นชื่อเต็มรวม package และ attribute ของมันขึ้นต้นด้วย `app:`

**ให้ผู้เรียนลอง**

1. เปิด `OrderFormActivity.kt` พิมพ์ `binding.` แล้วดูรายการที่ IDE เสนอ ถามว่าชื่อเหล่านี้มาจากไหน
2. รันแอป เข้า "ขั้นที่ 6 และ 9" ติ๊ก "เพิ่มช็อต" แล้วดู Logcat ด้วย `tag:OrderForm` จากนั้นให้หาบรรทัดในโค้ดที่ทำให้เกิด log นี้ ใช้เป็นแบบของแบบฝึก 9.2
3. เปิด Tools › Layout Inspector ขณะแอปรันอยู่ คลิก View บนภาพแล้วดูว่ามันอยู่ตรงไหนของต้นไม้ ใช้เครื่องมือนี้เมื่อ View ไม่อยู่ในที่ที่คิด

**แบบฝึก** ทำให้ฟอร์มทำงานตาม [targets/step9.png](targets/step9.png)

- 9.1 `QuantityStepperView` คือ custom view สำหรับเลือกจำนวน วางลงใน `activity_order_form.xml` ใต้ข้อความ "จำนวนแก้ว" ให้ id เป็น `stepperCups` และจำกัดไม่เกิน 5 แก้วด้วย `app:maxQuantity`
- 9.2 เมื่อเปิดสวิตช์ "จัดส่งถึงบ้าน" ให้ช่อง "ที่อยู่จัดส่ง" ปรากฏ และหายไปเมื่อปิด
- 9.3 เมื่อกด "สั่งเลย" ให้แสดงสรุปใน `tvSummary` โดยเรียก `orderSummary(...)` ที่เขียนไว้แล้วใน `OrderSummary.kt` ส่วนขนาดแก้วใช้ `selectedSize()` ที่อยู่ท้ายไฟล์

**เฉลย**

```xml
<com.learning.playground.lesson02.QuantityStepperView
    android:id="@+id/stepperCups"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:maxQuantity="5" />
```

```kotlin
binding.switchDelivery.setOnCheckedChangeListener { _, isChecked ->
    binding.tilAddress.isVisible = isChecked
}

binding.btnSubmit.setOnClickListener {
    binding.tvSummary.text = orderSummary(
        name = binding.etName.text.toString(),
        size = selectedSize(),
        extraShot = binding.cbExtraShot.isChecked,
        cups = binding.stepperCups.quantity
    )
}
```

test ของขั้นนี้ตรวจจากข้อความในไฟล์ จึงรู้แค่ว่าเรียกใช้ View ครบ ให้ผู้เรียนลองกดในแอปจริงและเล่าผล: กรอกชื่อ เลือก L ติ๊กเพิ่มช็อต กดบวกจน 3 แก้ว แล้วกดสั่ง ต้องได้ `สมชาย สั่งขนาด L เพิ่มช็อต 3 แก้ว` (ชื่อตามที่กรอก)

**จุดที่มักสับสน**

| อาการ | สาเหตุ |
|---|---|
| `binding.stepperCups` เป็นสีแดง | ยังไม่ได้ทำข้อ 9.1 หรือ id ไม่ตรง หรือยังไม่ได้ build ใหม่หลังแก้ XML |
| เขียน tag ว่า `<QuantityStepperView>` แล้วแอปปิดตัว | custom view ต้องใช้ชื่อเต็มรวม package |
| เขียน `binding.tilAddress.visibility = isChecked` | `visibility` รับค่า `View.VISIBLE` หรือ `View.GONE` ไม่ใช่ `Boolean` ใช้ `isVisible` แทน หรือเขียน `if` |
| `isVisible` เป็นสีแดง | ต้อง import `androidx.core.view.isVisible` กด Option+Enter |
| เขียน `binding.etName.text` โดยไม่มี `.toString()` | ชนิดไม่ตรงกับ `String` ที่ `orderSummary` รับ |
| ใช้ `View.VISIBLE`/`View.GONE` กับ `if` | ถูกต้อง |

**จะเจอที่ไหน**

- ใน workshop บรรทัด `inflate` กับ `setContentView` ย้ายไปอยู่ใน class แม่ Activity แต่ละตัวเหลือแค่ `override fun getViewBinding() = ActivityXxxBinding.inflate(layoutInflater)` แล้วใช้ `binding.xxx` ได้เลย
- workshop ใช้ `singleClick { }` แทน `setOnClickListener { }` เพื่อกันการกดรัว แนวคิดเดียวกัน
- โปรเจกต์ขนาดใหญ่มักมี custom view ของตัวเองจำนวนมาก เช่น ปุ่มและช่องกรอกที่มีหน้าตาของแบรนด์ วิธีใช้คือวาง tag ชื่อเต็มแล้วตั้งค่าผ่าน `app:` แบบข้อ 9.1

---

## ปิดบท — อ่าน layout ของ workshop

แสดง layout นี้ให้ผู้เรียน (ย่อจาก `workshop/app/src/main/res/layout/activity_main.xml`) แล้วถามทีละข้อ รอคำตอบก่อนเฉลย

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/default_16">

    <TextView
        android:id="@+id/tvAppTitle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="@string/main_title"
        android:textColor="@color/appBrownDark"
        android:textSize="28sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btnEnterCoffee"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="@dimen/default_24"
        android:text="@string/main_enter_coffee"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tvAppTitle" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

| ถาม | คำตอบ |
|---|---|
| ปุ่มอยู่ตรงไหนของจอ และกว้างเท่าไร | ใต้ชื่อแอป ห่าง 24dp กว้างเต็มจอหักขอบ 16dp เพราะ `0dp` ยืดระหว่าง `Start` กับ `End` ของแม่ |
| ทำไม `layout_width` เป็น `0dp` ไม่ใช่ `match_parent` | ลูกของ `ConstraintLayout` ใช้ `0dp` คู่กับ constraint สองด้าน |
| ใน Kotlin เรียกปุ่มนี้ว่าอะไร และ class binding ของไฟล์นี้ชื่ออะไร | `binding.btnEnterCoffee` และ `ActivityMainBinding` |
| `@dimen/default_24` คืออะไร ทำไมไม่เขียน `24dp` | ชื่อของระยะที่เก็บไว้ที่เดียว เปลี่ยนที่เดียวมีผลทั้งแอป |
| ถ้าลบบรรทัด `app:layout_constraintTop_toBottomOf` ของปุ่ม จะเกิดอะไร | ปุ่มไม่มี constraint แนวตั้ง จึงขึ้นไปอยู่บนสุดทับชื่อแอป |

ถ้าตอบได้ 4 จาก 5 ข้อถือว่าผ่านบทนี้

**layout สองตัวที่ต้องอ่านออกแต่ไม่ต้องเขียน** — เล่าสั้น ๆ หลังคำถาม

- `RelativeLayout` คือรุ่นก่อนของ `ConstraintLayout` พบในโค้ดเก่า ใช้ `android:layout_below="@id/x"`, `android:layout_toEndOf="@id/x"`, `android:layout_alignParentEnd="true"` แนวคิดเดียวกับ constraint คือบอกตำแหน่งเทียบกับตัวอื่น ถ้าเจอให้อ่านแบบเดียวกัน งานใหม่ใช้ `ConstraintLayout`
- `CoordinatorLayout` ใช้เมื่อ View ต้องขยับตามกัน เช่น แถบบนที่หดเมื่อเลื่อนรายการ มักเห็นคู่กับ `AppBarLayout` และ `NestedScrollView` รู้ว่ามีไว้ทำอะไรก็พอ

**บันทึก** ลง `progress.md`: ขั้นที่ผ่าน, ขั้นที่ใช้คำใบ้หรือดูเฉลย, และเรื่องที่ยังไม่มั่นใจ
บทที่ 03 ยังเป็นโครง ถ้าผู้เรียนขอเรียนต่อ ให้บอกสถานะตามจริง
