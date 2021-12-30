public class Main {
//    MAX_ITER=1000; RADIUS=2.;
//    int divergenceIndex(Complex z0) {
//        int ite = 0; Complex zn = z0;
//// sortie de boucle si divergence
//        while (ite < MAX_ITER-1 && |zn| <= RADIUS)
//        zn = f(zn); ite++;
//        return ite;
//    }

    public static void main(String[] args) {
        JuliaSetGenerator juliaSetGenerator = new JuliaSetGenerator();
        juliaSetGenerator.generate();
    }
}
