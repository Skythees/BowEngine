/*
 * Software developed by Skythees
 * Copyright (C) 2015 Skythees
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.skythees.bowEngine.core.util.input;

import com.skythees.bowEngine.core.math.Vector2f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

@SuppressWarnings("UnusedDeclaration")
public class InputHelper {
    @SuppressWarnings("WeakerAccess")
    public static final int NUM_KEY_CODES = 256;
    @SuppressWarnings("WeakerAccess")
    public static final int NUM_MOUSE_BUTTONS = 5;

    @SuppressWarnings("CanBeFinal")
    private static boolean[] lastKeys = new boolean[NUM_KEY_CODES];
    @SuppressWarnings("CanBeFinal")
    private static boolean[] lastMouse = new boolean[NUM_MOUSE_BUTTONS];

    @SuppressWarnings("UnusedDeclaration")
    public static void update() {
        for (int i = 0; i < NUM_KEY_CODES; i++)
            lastKeys[i] = getKey(i);

        for (int i = 0; i < NUM_MOUSE_BUTTONS; i++)
            lastMouse[i] = getMouse(i);
    }

    public static boolean getKey(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static boolean getKeyDown(int keyCode) {
        return getKey(keyCode) && !lastKeys[keyCode];
    }

    @SuppressWarnings("UnusedDeclaration")
    public static boolean getKeyUp(int keyCode) {
        return !getKey(keyCode) && lastKeys[keyCode];
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean getMouse(int mouseButton) {
        return Mouse.isButtonDown(mouseButton);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static boolean getMouseDown(int mouseButton) {
        return getMouse(mouseButton) && !lastMouse[mouseButton];
    }

    @SuppressWarnings("UnusedDeclaration")
    public static boolean getMouseUp(int mouseButton) {
        return !getMouse(mouseButton) && lastMouse[mouseButton];
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Vector2f getMousePosition() {
        return new Vector2f(Mouse.getX(), Mouse.getY());
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void setMousePosition(Vector2f pos) {
        Mouse.setCursorPosition((int) pos.getX(), (int) pos.getY());
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void setCursor(boolean enabled) {
        Mouse.setGrabbed(!enabled);
    }
}
