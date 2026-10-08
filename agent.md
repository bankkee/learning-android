# Coffee Learning — โครงสร้างและ convention ของโปรเจกต์

ไฟล์นี้สรุปโครงสร้างของโปรเจกต์สอน Android ตัวนี้ ให้คน (หรือ agent) ที่เข้ามาแก้โค้ดเข้าใจได้เร็ว
บทสอนอยู่ใน [learning.md](./learning.md) ซึ่งเขียนถึง Claude ในฐานะผู้ดำเนินการสอน: Claude ให้โจทย์ในแชต ผู้เรียนเขียนโค้ดเอง แล้ว Claude ตรวจจากไฟล์จริง

## โปรเจกต์นี้คืออะไร

แอปร้านกาแฟขนาดเล็กสำหรับสอนคนที่ไม่เคยเขียน Android ให้ทำงานกับโปรเจกต์ Android หลาย module ได้
workshop มี 6 session ไม่จับเวลา ผู้เรียนเติมโค้ดตรง `TODO(Session N · ภารกิจ M)` ทีละขั้น

โครงสร้างเป็นแบบที่พบในแอปขนาดใหญ่: หลาย module, MVVM, Koin และ Retrofit
ข้อมูลทั้งหมดเป็นของสมมุติ ห้ามเพิ่ม URL, token หรือชื่อ API ของระบบจริงใด ๆ เข้ามา

## Module

โปรเจกต์ Android (starter) อยู่ใน `workshop/` ซึ่งเป็น root ของโปรเจกต์ Gradle และเป็นโฟลเดอร์ที่ผู้เรียนเปิดด้วย Android Studio
เอกสารและเฉลยอยู่นอกโฟลเดอร์นั้น เพื่อไม่ให้เฉลยปนอยู่ในผลค้นหาและรายการไฟล์ของผู้เรียน
path ของโค้ดในไฟล์นี้ (เช่น `coffee/...`) และคำสั่ง `./gradlew`, `./scripts/...` นับจาก `workshop/`

```
learning-android/            root ของ repo
├── CLAUDE.md      ไฟล์ที่ Claude Code อ่านอัตโนมัติ: บอกว่าเมื่อไรสอนตาม learning.md เมื่อไรทำงานตามไฟล์นี้
├── README.md      วิธีเริ่มเรียน สำหรับผู้เรียน
├── learning.md    บทสอนของ workshop ที่ Claude ใช้ดำเนินการสอน
├── lessons/       บทเรียนพื้นฐานรายหัวข้อ บทที่ 00–02 พร้อมสอน ที่เหลือเป็นโครง โค้ดของบทที่ 01–12 อยู่ใน lessons/playground/ (ดู lessons/README.md)
├── agent.md       ไฟล์นี้
├── progress.md    ความคืบหน้าของผู้เรียน Claude สร้างและอัปเดตระหว่างสอน (ยังไม่มีจนกว่าจะเริ่มเรียน)
├── solutions/     เฉลย: session-N/ ของ workshop และ lesson-NN/ ของบทเรียนใน lessons/ ไม่ถูก build
├── review/        ชุดสำหรับส่งให้คนอื่น review: โปรเจกต์ฉบับเฉลยครบ + REVIEW.md
└── workshop/      โปรเจกต์ Android ที่ผู้เรียนเขียนโค้ดลงไป

workshop/
├── buildSrc/      เวอร์ชัน library ทั้งหมดอยู่ใน Dependencies.kt
├── app/           จุดเริ่มแอป: Application, AppModule, MainActivity, SchemeActivity, mock API, feature flag
├── core/          ของกลางที่ทุก feature ใช้: base Activity, interface Router, extension, interface FeatureFlag
├── networks/      ชนิดข้อมูลกลางของการเรียกข้อมูล: Response, Result, Failure, UseCase, NetworkDataSource
├── apilayer/      Api + Repository + UseCase ของทุก feature และ Koin module ของชั้นนี้
├── coffee/        feature module ตัวเดียวของโปรเจกต์ (ผู้เรียนทำงานในนี้เป็นหลัก)
└── scripts/       catch-up.sh ทับไฟล์ในโฟลเดอร์นี้ด้วยเฉลยจาก ../solutions/
```

`review/coffee-learning-solution/` คือ starter ที่ทับด้วย `solutions/session-1` ถึง `session-6` แล้วเติม comment `ผู้เรียนเขียน: Session N · ภารกิจ M`
กำกับทุกจุดที่ผู้เรียนเป็นคนเขียน เป็นสำเนาที่สร้างขึ้น ไม่ใช่ต้นทาง เมื่อแก้ starter หรือ `solutions/` ต้องสร้างชุดนี้ใหม่

ทิศทางการพึ่งพา (ลูกศรชี้ไปยัง module ที่ถูกใช้):

```
app ──► coffee ──► apilayer ──► core ──► networks
 │         └──────────────────► core
 └────► core, networks, apilayer
```

กฎ: feature module ห้ามพึ่ง feature module อื่นโดยตรง ถ้าต้องเปิดหน้าจอของอีก feature ให้ผ่าน Router

## Package

```
app/src/main/java/com/learning/app/
├── CoffeeApp.kt                 Application: เริ่ม Koin ด้วย appModule
├── di/AppModule.kt              ผูก Router, FeatureFlag, OkHttp, Retrofit
├── ui/MainActivity.kt           หน้าแรก (launcher)
├── scheme/SchemeActivity.kt     รับ deep link แล้วส่งต่อ (ผู้เรียนสร้างใน session 4)
└── public_impl/                 ตัวจริงของ interface ใน core: FeatureFlagImpl, MockApiInterceptor
app/src/main/assets/apiData/     JSON ที่ mock API ใช้ตอบ

coffee/src/main/java/com/learning/coffee/
├── di/CoffeeModule.kt           Koin module ของ feature (object : ModuleInject)
├── route/CoffeeRoute.kt         ตัวจริงของ CoffeeRouter
├── common/BaseCoffeeActivity    base ของทุก Activity ใน feature, คืน getDi()
└── ui/
    ├── menu/    CoffeeMenuActivity, CoffeeMenuViewModel, CoffeeMenuAdapter
    └── detail/  CoffeeDetailActivity

apilayer/src/main/java/com/learning/apilayer/
├── di/ApiLayerModule.kt
├── repository/<กลุ่ม>/    XxxApi.kt (Request, Response, interface Retrofit) + XxxRepository.kt
└── usecase/<กลุ่ม>/       XxxUseCase.kt
```

## เส้นทางของข้อมูล

```
Activity ──► ViewModel ──► UseCase ──► Repository ──► Api (Retrofit) ──► server หรือ mock
   ▲             │                          │
   └─ observe LiveData<Response<T>>  ◄── Result<Failure, T>
```

| ชั้น | อยู่ที่ไหน | หน้าที่ | ห้ามทำ |
|---|---|---|---|
| Activity | feature `ui/` | แสดงผล, รับการกด, observe LiveData | คำนวณ, เรียก UseCase/Repository ตรง ๆ |
| ViewModel | feature `ui/` | ถือ state ของหน้าจอเป็น LiveData, สั่ง UseCase | แตะ View หรือ `binding` |
| UseCase | `apilayer/usecase/` | งานหนึ่งอย่าง: รัน `run()` บน `Dispatchers.IO` แล้วเรียก callback บน main thread | ถือ state ของหน้าจอ |
| Repository | `apilayer/repository/` | สืบทอด `NetworkDataSource`, ห่อ request ด้วย `getBaseData()` แล้วเรียก Api ผ่าน `requestData()` ซึ่งแกะซองและแปลงทุกผลลัพธ์เป็น `Result` | รู้จัก ViewModel หรือ UI |
| Api | `apilayer/repository/` | interface ของ Retrofit คืน `Call<Response>` | มี logic |

ชนิดข้อมูลสองตัวที่ต้องแยกให้ออก:

- `Result<Failure, T>` — ผลจาก Repository/UseCase มีสองทาง: `Error(failure)` หรือ `Success(success)`
- `Response<T>` — state ที่หน้าจอ observe มีสามทาง: `Loading`, `Success(value)`, `Error(failure)`

ViewModel คือจุดที่แปลง `Result` เป็น `Response`

## ลำดับการทำงานของหน้าจอ

ทุก Activity สืบทอด `NewBaseActivity<VBinding>` ซึ่งเรียกตามลำดับนี้ใน `onCreate`:

1. `init()` — สร้าง `binding` จาก `getViewBinding()`, `setContentView`, แล้วโหลด Koin module ของ feature จาก `getDi()`
2. `observeViewModel()` — ผูก observer กับ LiveData
3. `setUpViews()` — ตั้งค่า View และ listener, สั่งโหลดข้อมูลครั้งแรก

Activity จึง override แค่ `getViewBinding()`, `observeViewModel()`, `setUpViews()` ไม่ต้อง override `onCreate`

`BaseActivity` เขียน log ทุก lifecycle callback ด้วย tag `Lifecycle` ใช้ในภารกิจของ session 1

## Convention

**ตั้งชื่อ**
- `<Feature><Purpose>Activity`, `<Feature><Purpose>ViewModel` เช่น `CoffeeMenuActivity`, `CoffeeMenuViewModel`
- ชุด API หนึ่งเส้น: `GetXxxApi`, `GetXxxRequest`, `GetXxxResponse`, `GetXxxRepository`, `GetXxxUseCase`
- field ของ Response เป็น nullable และใช้ `@SerializedName("PascalCase")`
- Request และ Response สืบทอด `FormData`, Api รับ `BaseRequest<FormData>` และคืน `Call<BaseResponse<XxxResponse>>`, รหัสฟอร์มอยู่ใน `companion object` ของ Api ชื่อ `API_CODE`, และ Repository รับ parameter เป็น `FormData`
- id ใน layout เป็น camelCase มีคำนำหน้าชนิด View: `tvGreeting`, `btnRecommend`, `rvMenu`

**LiveData ใน ViewModel** — ตัวที่แก้ได้เป็น private มีขีดล่าง, ตัวที่เปิดให้ข้างนอกอ่านได้อย่างเดียว

```kotlin
private val _recommendedCoffeeResult = MutableLiveData<Response<GetRecommendedCoffeeResponse>>()
val recommendedCoffeeResult: LiveData<Response<GetRecommendedCoffeeResponse>>
    get() = _recommendedCoffeeResult
```

**Activity** — observe ด้วย `observe(liveData) { }` และแตกกรณีด้วย `handleResponse` จาก `core`

```kotlin
observe(coffeeMenuViewModel.recommendedCoffeeResult) { result ->
    handleResponse(result, onSuccess = { binding.tvResult.text = it.name.orEmpty() })
}
```

`handleResponse` แสดง/ซ่อน loading และแสดง dialog ตอน error ให้เอง ส่ง `onError = { ...; true }` เมื่ออยากจัดการ error เอง
(คืน `true` = จัดการแล้ว ไม่ต้องแสดง dialog มาตรฐาน)

**การกด** — ใช้ `singleClick { }` จาก `core` แทน `setOnClickListener` เพื่อกันการกดรัว

**เปิดหน้าจอ**
- ทุก Activity มี `companion object { fun newInstance(context, ...): Intent }` ใส่ข้อมูลที่ส่งต่อใน Intent extra โดยชื่อ key เป็น `private const`
- ใน module เดียวกัน: `startActivity(XxxActivity.newInstance(this, ...))`
- ข้าม module: ผ่าน Router ซึ่งมี 3 ส่วน คือ interface `CoffeeRouter` ใน `core/router/`, ตัวจริง `CoffeeRoute` ใน `coffee/route/`, และบรรทัดผูกใน `app/di/AppModule.kt`
- จากนอกแอป: `SchemeActivity` รับลิงก์ `coffeelearning://open.app/...` ตาม `intent-filter` ใน Manifest, อ่าน `intent.data`, ส่งต่อผ่าน Router แล้ว `finish()`

**DI (Koin)**
- ViewModel ลงทะเบียนใน `coffee/di/CoffeeModule.kt` ด้วย `viewModel { CoffeeMenuViewModel(get(), ...) }` จำนวน `get()` ต้องเท่ากับจำนวน parameter ของ constructor
- ชุด API ลงทะเบียนใน `apilayer/di/ApiLayerModule.kt` สามบรรทัดต่อหนึ่งเส้น: `factory` สำหรับ Api และ Repository, `single` สำหรับ UseCase
- Activity ขอ ViewModel ด้วย `by viewModel()` และขอ dependency อื่นด้วย `by inject()`
- `appModule` โหลดตอนเปิดแอป ส่วน Koin module ของ feature โหลดเมื่อ Activity ของ feature นั้นเริ่มทำงาน ผ่าน `getDi()` → `injectFeature()`

**Feature flag** — ถามผ่าน `FeatureFlag.isEnabled(FlagConstants.XXX)` ชื่อ flag อยู่ใน `core/featureflag/FlagConstants.kt` และเปิด/ปิดที่ `app/public_impl/FeatureFlagImpl.kt`

**UI** — XML layout + ViewBinding ไม่ใช้ Jetpack Compose ข้อความที่แสดงผลอยู่ใน `res/values/strings.xml` ของ module ที่ใช้
(`android.nonTransitiveRClass=true` ทำให้ `R` ของแต่ละ module มีเฉพาะ resource ของตัวเอง ถ้าต้องอ้าง resource ของ `core` จาก Kotlin ให้ import `com.learning.core.R`)

**รายการ** — `RecyclerView.Adapter` ที่มี `inner class XxxViewHolder(binding)` พร้อม `setData(item)` และ `updateItems(newItems)` รับ callback การกดผ่าน constructor

**Unit test** — อยู่ใน `<module>/src/test/` mock ที่ชั้น Repository แล้วสร้าง UseCase และ ViewModel ตัวจริง ใช้ `InstantTaskExecutorRule` และ `Dispatchers.setMain`

## Mock API

flavor `sit` ไม่ยิง server จริง `MockApiInterceptor` ดัก request ตาม path แล้วตอบด้วย JSON ใน `app/src/main/assets/apiData/` พร้อมหน่วง 1.5 วินาที

| Path | คำตอบ |
|---|---|
| `v1/coffee/recommended` | วน 3 แบบ: ลาเต้ → มอคค่า → error ทางธุรกิจ (HTTP 200 ที่มี `ErrorCode`) ตั้งใจ เพื่อให้เห็นสถานะ Error |
| `v1/coffee/menu` | รายการ 6 เมนู |
| `v1/dessert/recommended` | ครัวซองต์เนยสด (ใช้ใน session 6) |

เพิ่ม API ใหม่: วางไฟล์ JSON ใน `assets/apiData/` โดยห่อข้อมูลในซอง `{ "Form": [ { "Header": { "ApiCode": "..." }, "FormData": { ... } } ] }` แล้วเพิ่ม path ใน `MOCKS` ของ `MockApiInterceptor`
request และ response ทุกครั้งดูได้ใน Logcat ด้วย tag `okhttp.OkHttpClient`

## Build

| รายการ | ค่า |
|---|---|
| Gradle wrapper | 8.7 |
| Android Gradle Plugin | 8.6.0 |
| Kotlin | 1.9.22 |
| compileSdk / targetSdk | 36 |
| minSdk | 29 |
| Java / Kotlin target | 17 |
| Koin | 2.2.3 |
| Retrofit / OkHttp | 2.9.0 / 4.12.0 |
| Coroutines | 1.7.3 |
| AndroidX Lifecycle | 2.4.0 |
| Mockito / mockito-kotlin | 4.2.0 / 2.1.0 |

ค่าเหล่านี้อยู่ใน `buildSrc/src/main/java/Dependencies.kt` ไฟล์ Gradle เป็น Groovy DSL (`build.gradle`)

Product flavor (dimension `version`):

| Flavor | applicationId | `BuildConfig.USE_MOCK_API` | ใช้เมื่อ |
|---|---|---|---|
| `sit` (ค่าเริ่มต้น) | `com.learning.coffeeapp.sit` | `true` | ตลอดหลักสูตร |
| `production` | `com.learning.coffeeapp` | `false` | สาธิต network error เพราะ URL ไม่มีอยู่จริง |

```
./gradlew assembleSitDebug               # build
./gradlew :coffee:testDebugUnitTest      # unit test (มีไฟล์ test หลังจบ session 6)
./scripts/catch-up.sh 3                  # ทับเฉลย session 1–3
```

ต้องมีไฟล์ `local.properties` ที่ root ชี้ `sdk.dir` ไปยัง Android SDK ของเครื่อง (Android Studio สร้างให้อัตโนมัติ, ไม่อยู่ใน git)

สถานะการตรวจล่าสุด (2026-10-08, JDK 21): starter build ผ่าน, เฉลยทั้ง 6 session build ผ่านเมื่อทับตามลำดับ, unit test ของ session 6 ผ่าน
ยังไม่ได้รันบน emulator หรือเครื่องจริง

## สิ่งที่ตั้งใจทำให้ง่าย

| เรื่อง | โปรเจกต์นี้ | เหตุผล |
|---|---|---|
| จำนวน feature module | 1 | ให้ผู้เรียนเห็นทั้งระบบได้ในครั้งเดียว |
| ซองของ request/response | หัวซองมีแค่ `ApiCode` | ให้เห็นแนวคิดของซองโดยไม่มีรายละเอียดของ backend |
| `NetworkDataSource` | `requestData()` ตรวจแค่ `ErrorCode`, HTTP 5xx และ network error | ให้อ่านจบได้ในหน้าเดียว |
| `BaseActivity` | มีแค่ loading, dialog error และ log lifecycle | ไม่เกี่ยวกับสิ่งที่สอน |
| Router | อยู่ใน `<feature>/route/` ทั้งหมด | แบบที่ง่ายที่สุด |
| Layout | ViewBinding อย่างเดียว | ลดแนวคิดที่ต้องสอน |
| Feature flag | set ในโค้ด | ไม่ต้องมี server |
| Unit test JVM | `coffee/build.gradle` ตั้ง `-Dnet.bytebuddy.experimental=true` | Mockito 4.2.0 ยังไม่รู้จัก JDK ที่ใหม่กว่า 18 |

## ก่อนแก้โปรเจกต์นี้

1. โค้ดใน module หลักคือ **starter** มี `TODO(Session N · ภารกิจ M)` ที่ตั้งใจเว้นไว้ให้ผู้เรียน ห้ามเติมให้
2. `solutions/session-N/` เก็บเฉพาะไฟล์ที่เปลี่ยนใน session นั้น ในสภาพ "จบ session N" และใช้ path เดียวกับโปรเจกต์ ไฟล์เฉลยยังคง `TODO` ของ session ถัดไปไว้
3. เมื่อแก้ไฟล์ที่ผู้เรียนต้องแตะ ให้แก้ทุกเวอร์ชันของไฟล์นั้นใน `solutions/` และโค้ดใน `learning.md` ให้ตรงกัน
4. ตรวจหลังแก้ทุกครั้ง: starter ต้อง build ผ่าน, แล้วทับเฉลยทีละ session และ build ทุกรอบ, จบด้วยรัน unit test
5. คง library และ convention ชุดเดิมไว้ให้สม่ำเสมอทั้งหลักสูตร อย่าเปลี่ยนเป็นของใหม่เฉพาะบางจุด
