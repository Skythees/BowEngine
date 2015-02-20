package com.skythees.bowEngine.render;

import com.skythees.bowEngine.managers.Input;
import com.skythees.bowEngine.managers.Time;
import com.skythees.bowEngine.math.vector.Vector3f;
import org.lwjgl.input.Keyboard;

public class Camera {
    private static final Vector3f yAxis = new Vector3f(0, 1, 0);

    public Vector3f pos;
    private Vector3f forward;
    private Vector3f up;

    public Camera() {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
    }

    public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
        this.pos = pos;
        this.forward = forward;
        this.up = up;

        up.normalized();
        forward.normalized();
    }

    public void input() {
        float moveAmount = (float) (10 * Time.getDelta());
        float rotationAmount = (float) (100 * Time.getDelta());

        if (Input.getKey(Keyboard.KEY_W)) {
            move(getForward(), Input.getKey(Keyboard.KEY_LCONTROL) ? moveAmount * 5 : moveAmount);
        }
        if (Input.getKey(Keyboard.KEY_A)) {
            move(getLeft(), -(Input.getKey(Keyboard.KEY_LCONTROL) ? moveAmount * 5 : moveAmount));
        }
        if (Input.getKey(Keyboard.KEY_S)) {
            move(getForward(), -(Input.getKey(Keyboard.KEY_LCONTROL) ? moveAmount * 5 : moveAmount));
        }
        if (Input.getKey(Keyboard.KEY_D)) {
            move(getRight(), -(Input.getKey(Keyboard.KEY_LCONTROL) ? moveAmount * 5 : moveAmount));
        }

        if (Input.getKey(Keyboard.KEY_UP)) {
            rotateX(-rotationAmount);
        }
        if (Input.getKey(Keyboard.KEY_LEFT)) {
            rotateY(-rotationAmount);
        }
        if (Input.getKey(Keyboard.KEY_DOWN)) {
            rotateX(rotationAmount);
        }
        if (Input.getKey(Keyboard.KEY_RIGHT)) {
            rotateY(rotationAmount);
        }
    }

    public void move(Vector3f dir, float amount) {
        pos = pos.add(dir.mul(amount));
    }

    public void rotateY(float angle) {
        Vector3f hAxis = yAxis.cross(forward).normalized();
        forward = forward.rotated(angle, yAxis).normalized();
        up = forward.cross(hAxis).normalized();
    }

    public void rotateX(float angle) {
        Vector3f hAxis = yAxis.cross(forward).normalized();
        forward = forward.rotated(angle, hAxis).normalized();
        up = forward.cross(hAxis).normalized();
    }

    public Vector3f getLeft() {
        Vector3f left = up.cross(forward);
        left.normalized();
        return left;
    }

    public Vector3f getRight() {
        Vector3f right = forward.cross(up);
        right.normalized();
        return right;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }
}