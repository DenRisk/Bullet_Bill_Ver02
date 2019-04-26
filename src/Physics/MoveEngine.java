package Physics;

import Basics.Time;
import Main.Main;
import Sprite.Sphere;

public class MoveEngine {

    private static boolean change = true;

    public MoveEngine(long now, Sphere sphere){

        if (change){
            Time.curTime = System.currentTimeMillis();
            change = false;
        }

        Time.updateTime();
        buildSumm(sphere);
        kords(sphere);

    }

    private void buildSumm(Sphere sphere){

        double vx = sphere.getVx() + (0 * Time.timeFraction);
        double vy = sphere.getVy() + (Main.GRAVITY * Time.timeFraction);

        double drag = 1.0 - (Time.timeFraction * Main.DRAG);

        sphere.updateVelocity(vx * drag, vy);
    }

    private void kords(Sphere sphere){

        double oldX = sphere.getX0();
        double oldY = sphere.getY0();

        double newX = oldX + (sphere.getVx() * Time.timeFraction);
        double newY = oldY + (sphere.getVy() * Time.timeFraction);

        sphere.updatePos(newX, newY);

        checkGround(newY, sphere);
        checkWall(newX, sphere);
    }

    private void checkGround(double groundY, Sphere sphere){
        if (groundY > 670){
            sphere.setY0(670);

            // Wichtige Formel
            sphere.setVy(-sphere.getVy() * Main.BOUNCE);
        }
    }

    private void checkWall(double wallX, Sphere sphere){
        if (wallX>770){
            sphere.setX0(770);
            sphere.setVx(-sphere.getVx() * Main.BOUNCE);
        }
        if (wallX < 30){

            sphere.setX0(30);
            sphere.setVx(-sphere.getVx() * Main.BOUNCE);
        }
    }
}
