package main.maths;

// Maths used for calculating refraction
// This class uses Snell's Law and Fresnel Equations
// For more info about his visit:
// https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-to-shading/reflection-refraction-fresnel
public final class RefractionMath {

    // Check if value is below min or above max
    // Set to min or max respectively if it's the case
    public static double clampDouble(double min, double max, double value) {
        if (value < min) {
            value = min;
        }

        if (value > max) {
            value = max;
        }

        return value;
    }

    // Calculate refraction direction according to Snell's law
    public static Vector3 refract(Vector3 incidence, Vector3 sNormal, double ior) {

        // Cos of the dot product of incidence direction and surface normal
        double cosi = clampDouble(-1, 1, incidence.dot(sNormal));

        // Put ior of origin and target in variables
        double etai = 1, etat = ior;

        // Check if if the cos is below 0
        // Reverse it if it is

        // Otherwise ray is going out of object
        // So swap the iors around
        if (cosi < 0) {
            cosi = cosi * -1;
        } else {
            double tempi = etai;
            etai = etat;
            etat = tempi;
        }

        // Calculate the ior for the current calculation
        double eta = etai / etat;

        // Apply Snell's law
        double k = 1 - eta * eta * (1 - cosi * cosi);

        // If result is 0 return black
        // Otherwise return new direction
        if (k < 0) {
            return new Vector3(0, 0, 0);
        } else {
            return incidence.multi(eta).add(sNormal.multi(eta * cosi - Math.sqrt(k)));
        }
    }

    // Calculate fresnel equations
    public static double fresnel(Vector3 incidence, Vector3 sNormal, double ior) {

        // Cos of the dot product of incidence direction and surface normal
        double cosi = clampDouble(-1, 1, incidence.dot(sNormal));

        // Put ior of origin and target in variables
        double etai = 1, etat = ior;

        // Check if Cos is above 0, Swap iors if it is
        if (cosi > 0) {
            double tempi = etai;
            etai = etat;
            etat = tempi;
        }

        // Calculate sin of target
        double sint = etai / etat * Math.sqrt(Math.max(0.0, 1 - cosi * cosi));

        // If equal to or above 1 return 1
        // Otherwise use the fresnel equations and return the result
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
