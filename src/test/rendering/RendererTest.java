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
        double minX=2;
        double maxX=200;

        double minTX=0;
        double maxTX=1;

        double contactpointX =50;

        double widthRatio3D=maxX-minX;
        double widthRatioTM=maxTX-minTX;
        int matWidth=40;

        int width;

        if(minX<=0){
            //                     pixel start       calculations of upcomming pixels
            width= Math.abs((int) (minTX *matWidth +(((minX +contactpointX) *matWidth *widthRatioTM )/widthRatio3D)));
        }
        else{
            width= Math.abs((int) (minTX *matWidth +((contactpointX-minX) *matWidth *widthRatioTM) /widthRatio3D));
        }

        assertEquals(9, width);
    }

}