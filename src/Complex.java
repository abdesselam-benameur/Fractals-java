public class Complex {
    private double re;
    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public double argument() {
        return Math.atan2(im, re);
    }

    public double module() {
        return Math.sqrt(re * re + im * im);
    }

    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex sub(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex square(Complex c) {
        return new Complex(re * c.re, im * c.im);
    }

    public Complex pow(int n) {
        if (n == 0) {
            return new Complex(1, 0);
        } else if (n == 1) {
            return this;
        } else {
            double rn = Math.pow(module(), n);
            double n_theta = n * argument();
            return new Complex(rn * Math.cos(n_theta), rn * Math.sin(n_theta));
        }
    }

    public String toString() {
        if (im == 0) {
            return re + "";
        } else if (re == 0) {
            return im + "i";
        } else if (im < 0) {
            return re + " - " + (-im) + "i";
        } else {
            return re + " + " + im + "i";
        }
    }
}
