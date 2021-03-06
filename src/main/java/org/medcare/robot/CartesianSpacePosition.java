package org.medcare.robot;

public class CartesianSpacePosition {

    private double[][] rotationMatrix = new double[3][3];
    private double[] positionVector = new double[3];

    public CartesianSpacePosition(double[][] rotationMatrix, double[] positionVector) {
        this.rotationMatrix = rotationMatrix;
        this.positionVector = positionVector;
    }

    public CartesianSpacePosition() {

    }

    public void setFrameTransformation(double[][] rotationMatrix, double[] positionVector) {
        this.rotationMatrix = rotationMatrix;
        this.positionVector = positionVector;
    }

    public double[][] getRotationMatrix() {
        return rotationMatrix;
    }

    public double[] getPositionVector() {
        return positionVector;
    }


}
