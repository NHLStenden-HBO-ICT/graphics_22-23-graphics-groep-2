package test.rendering;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RendererTest {


    @Test
    void renderFrame() {
    }

    @Test
    void getscene() {
    }

    @Test
    void setscene() {
    }

    @Test
    void widthpixelTexture(){

        //folowing numbers are theoretical
        double minX=-51.86090037556417;
        double maxX=162.38721616503693;

        double minTX=0;
        double maxTX=1;

        double contactpointX =50;

        double widthRatio3D=maxX-minX;
        double widthRatioTM=maxTX-minTX;
        int matWidth=40;

        int width;

        if(minX<=0){
            //                     pixel start       calculations of upcomming pixels
            width= Math.abs((int) (minTX *matWidth +(((Math.abs(minX) +contactpointX) *matWidth *widthRatioTM )/widthRatio3D)));
        }
        else{
            width= Math.abs((int) (minTX *matWidth +((contactpointX-Math.abs(minX)) *matWidth *widthRatioTM) /widthRatio3D));
        }

        assertEquals(19, width);
    }

}