package com.learning.playground

import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

// ตัวช่วยของ test ทุกบท: อ่านไฟล์ของ module app ตรง ๆ เพราะแบบฝึกหลายข้อแก้ XML ไม่ได้แก้ Kotlin

const val ANDROID_NS = "http://schemas.android.com/apk/res/android"
const val APP_NS = "http://schemas.android.com/apk/res-auto"
const val TOOLS_NS = "http://schemas.android.com/tools"

/** หาไฟล์จาก path ที่นับจาก app/src/main/ ไม่ว่า test จะถูกรันจากโฟลเดอร์ app หรือ root ของโปรเจกต์ */
fun mainFile(path: String): File {
    return listOf(File("src/main/$path"), File("app/src/main/$path")).firstOrNull { it.exists() }
        ?: error("หาไฟล์ app/src/main/$path ไม่เจอ")
}

fun parseXml(path: String): Element = parseXml(mainFile(path))

fun parseXml(file: File): Element {
    val factory = DocumentBuilderFactory.newInstance().apply { isNamespaceAware = true }
    return factory.newDocumentBuilder().parse(file).documentElement
}

fun parseLayout(name: String): Element = parseXml("res/layout/$name.xml")

/** ลูกหลานทุกชั้นที่มี tag นี้ */
fun Element.children(tag: String): List<Element> {
    val nodes = getElementsByTagName(tag)
    return (0 until nodes.length).map { nodes.item(it) as Element }
}

/** ตัวเองและลูกหลานทุกชั้น */
fun Element.all(): List<Element> = listOf(this) + children("*")

/** ลูกชั้นแรกเท่านั้น */
fun Element.directChildren(): List<Element> {
    return (0 until childNodes.length).map { childNodes.item(it) }.filterIsInstance<Element>()
}

fun Element.parent(): Element = parentNode as Element

fun Element.android(attribute: String): String = getAttributeNS(ANDROID_NS, attribute)

fun Element.app(attribute: String): String = getAttributeNS(APP_NS, attribute)

fun Element.tools(attribute: String): String = getAttributeNS(TOOLS_NS, attribute)

/** ค่าของ attribute ทุกตัวของ element นี้ */
fun Element.attributeValues(): List<String> = (0 until attributes.length).map { attributes.item(it).nodeValue }

/** "@+id/tvName" และ "@id/tvName" กลายเป็น "tvName" */
fun String.idName(): String = substringAfter("/")

/** หา View จาก id หรือหยุดพร้อมข้อความถ้าไม่มี */
fun Element.byId(id: String): Element {
    return all().firstOrNull { it.android("id").idName() == id && it.android("id").isNotEmpty() }
        ?: error("ไม่มี View ที่ id เป็น $id")
}

fun Element.hasId(id: String): Boolean = all().any { it.android("id").isNotEmpty() && it.android("id").idName() == id }

/** ค่าของ <string name="..."> ใน strings.xml หรือ null ถ้าไม่มี */
fun stringResource(name: String): String? {
    return parseXml("res/values/strings.xml").children("string")
        .firstOrNull { it.getAttribute("name") == name }
        ?.textContent
}

/** <style name="..."> จากไฟล์ใดก็ได้ใน res/values/ หรือ null ถ้าไม่มี */
fun styleResource(name: String): Element? {
    return mainFile("res/values").listFiles().orEmpty()
        .filter { it.extension == "xml" }
        .flatMap { parseXml(it).children("style") }
        .firstOrNull { it.getAttribute("name") == name }
}
