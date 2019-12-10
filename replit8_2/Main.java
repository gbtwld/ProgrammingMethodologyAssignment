package replit8_2;

abstract class Converter {
    abstract protected double convert(double src); // 추상 메소드
    abstract protected String getSrcString(); // 추상 메소드
    abstract protected String getDestString(); // 추상 메소드
    protected double ratio; // 비율
    public double run(double val) {
        System.out.println("convert " + val + " " + getSrcString()+" to "+getDestString());
        double res = convert(val);
        System.out.println(res + " " + getDestString());
        return res;
    }
}

class Won2Dollar extends Converter {
    // fill here!
    Won2Dollar(double src) {
        ratio = 1 / src;
    }


    @Override
    protected double convert(double src) {
        return src * ratio;
    }

    @Override
    protected String getSrcString() {
        return "won";
    }

    @Override
    protected String getDestString() {
        return "dollar";
    }
}

class Km2Mile extends Converter {
    // fill here!
    Km2Mile(double src) {
        ratio = 1 / src;
    }

    @Override
    protected double convert(double src) {
        return src * ratio;
    }

    @Override
    protected String getSrcString() {
        return "km";
    }

    @Override
    protected String getDestString() {
        return "mile";
    }
}

class Main {
    public static void main(String[] args) {
        Won2Dollar toDollar = new Won2Dollar(1200); // 1달러는 1200원
        toDollar.run(2400);
        Km2Mile toMile = new Km2Mile(1.6); // 1마일은 1.6km
        toMile.run(3.2);
    }
}
