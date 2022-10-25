package main.maths;

public final class RefractionMath {

    public static double clampDouble(double min, double max, double value) {
        if (value < min) {
            value = min;
        }

        if (value > max) {
            value = max;
        }

        return value;
    }

    public static Vector3 refract(Vector3 incidence, Vector3 sNormal, double ior){
        double cosi = clampDouble(-1, 1, incidence.dot(sNormal)); 
        double etai = 1, etat = ior; 
        Vector3 n = sNormal;

        if (cosi < 0) { 
            cosi = cosi * -1; 
        } else { 
            double tempi = etai;
            etai = etat;
            etat = tempi;
            
            n = sNormal.multi(-1); 
        }

        double eta = etai / etat; 
        double k = 1 - eta * eta * (1 - cosi * cosi);
        
        if (k < 0){
            return new Vector3(0,0,0);
        }else{
            return incidence.multi(eta).add(sNormal.multi(eta * cosi - Math.sqrt(k)));
        }
    }

    public static double fresnel(Vector3 incidence, Vector3 sNormal, double ior) {

        double cosi = clampDouble(-1, 1, incidence.dot(sNormal));
        double etai = 1, etat = ior;

        if (cosi > 0) {
            double tempi = etai;
            etai = etat;
            etat = tempi;
        }

        double sint = etai / etat * Math.sqrt(Math.max(0.0, 1 - cosi * cosi));

        if (sint >= 1) {
            return 1.0;
        } else {
            double cost = Math.sqrt(Math.max(0.0, 1 - sint * sint));
            cosi = Math.abs(cosi);
            double rs = ((etat * cosi) - (etai * cost)) / ((etat * cosi) + (etai * cost));
            double rp = ((etai * cosi) - (etat * cost)) / ((etai * cosi) + (etat * cost));
            return (rs * rs + rp * rp) / 2;
        }

    }

}
