#!/bin/sh
# ทับไฟล์ในโปรเจกต์ด้วยเฉลยตั้งแต่ session 1 ถึง session ที่ระบุ
# ใช้เมื่อทำไม่ทันหรือขาดเรียน:  ./scripts/catch-up.sh 3   → โปรเจกต์อยู่ในสภาพ "จบ session 3"
# เฉลยอยู่ที่ ../solutions/ (นอกโฟลเดอร์ workshop)
# คำเตือน: งานที่เขียนเองในไฟล์เดียวกันจะถูกทับ
set -e

LAST="$1"
case "$LAST" in
    1|2|3|4|5|6) ;;
    *) echo "วิธีใช้: ./scripts/catch-up.sh <เลข session 1-6>"; exit 1 ;;
esac

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
SOLUTIONS="$ROOT/../solutions"
if [ ! -d "$SOLUTIONS" ]; then
    echo "ไม่พบโฟลเดอร์เฉลยที่ $SOLUTIONS"
    exit 1
fi

N=1
while [ "$N" -le "$LAST" ]; do
    cp -R "$SOLUTIONS/session-$N/." "$ROOT/"
    echo "ทับเฉลย session $N แล้ว"
    N=$((N + 1))
done
