package Physics;

import Main.Main;
import Sprite.Sphere;
import Sprite.Vec;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Vector;

public class Circle_Circle_Collision {


    public static double getDistance(double aS_x, double aS_y, double cS_x, double cS_y) {
        double xDistance = cS_x - aS_x;
        double yDistance = cS_y - aS_y;
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public static void cicleDetection(Sphere aS, Sphere cS) {

        double distance = getDistance(aS.getCenterX(), aS.getCenterY(), cS.getCenterX(), cS.getCenterY());
        if ( distance < aS.getRadius() + cS.getRadius()) {
            cS.setFill(Color.RED);
            collideCircle(aS, cS, distance);

        }
        else {
            cS.setFill(Color.BLACK);
        }
    }

    public static void collideCircle (Sphere aS, Sphere cS, double distance) {


        double relX = aS.getX0() - cS.getX0();
        double relY = aS.getY0() - cS.getY0();

        double collisionAngle = Math.atan2(relY, relX);

        Vec aSVec = aS.getVelocityVector();
        Vec cSVec = cS.getVelocityVector();

        aSVec.rotateCoordinates(collisionAngle);
        cSVec.rotateCoordinates(collisionAngle);

        double swap = aSVec.getX();
        aSVec.setX(cSVec.getX());
        cSVec.setX(swap);

        aSVec.restoreCoordinates();
        cSVec.restoreCoordinates();

        aS.updateVelocity(aS.getVx() * 0.9, aS.getVy() * 0.9);
        cS.updateVelocity(cS.getVx() * 0.9, cS.getVy() * 0.9);

        double minDist = aS.getR() + cS.getR();
        double overlap = minDist - distance;
        double toMove = overlap / 2;
        double newX = aS.getX0() + (toMove * Math.cos(collisionAngle));
        double newY = aS.getY0() + (toMove * Math.sin(collisionAngle));
        aS.updatePos(newX, newY);

        newX = cS.getX0() - (toMove * Math.cos(collisionAngle));
        newY = cS.getY0() - (toMove * Math.sin(collisionAngle));
        cS.updatePos(newX, newY);
    }

}
