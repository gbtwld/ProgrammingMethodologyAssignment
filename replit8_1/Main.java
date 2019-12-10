package replit8_1;

class Pen { // 모든 펜의 공통 속성
    private int amount; // 남은 량
    public int getAmount() { return amount; }
    public void setAmount (int amount) { this.amount = amount; }
}
class SharpPencil extends Pen{ // 샤프펜슬
    // fill here!
    public int width;
}

class Ballpen extends Pen { // 볼펜
    // fill here!
    private String color;
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
class FountainPen extends Ballpen{ // 만년필
    // fill here!
    public void refill (int n) {
        setAmount(n);
    }
}

class Main {
    public static void main(String[] args) {
        Pen fp = new FountainPen();
        fp.setAmount(1);
        System.out.println(fp.getAmount()); // 1
    }
}
