/*
 * BowEngine, modular and easy to use game engine
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.skythees.bowEngine.render;

import com.skythees.bowEngine.core.Transform;
import com.skythees.bowEngine.core.components.GameObject;
import com.skythees.bowEngine.core.lib.Reference;
import com.skythees.bowEngine.core.math.Vector3f;
import com.skythees.bowEngine.render.components.Camera;
import com.skythees.bowEngine.render.components.light.BaseLight;
import com.skythees.bowEngine.render.resources.MapStorage;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

/**
 * Created on 15.3.2015. at 2:47.
 */
public class RenderingEngine extends MapStorage
{
	private final ArrayList<BaseLight> lights = new ArrayList<>();
	private BaseLight activeLight;

	private Shader forwardAmbient;
	private Camera mainCamera;


	public RenderingEngine()
	{
		integerHashMap.put("diffuse", Reference.SamplerData.DIFFUSE);
		vector3fHashMap.put("ambient", new Vector3f(0.1f, 0.1f, 0.1f));

		forwardAmbient = new Shader("./resources/shaders/forward-ambient");

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_DEPTH_CLAMP);

		glEnable(GL_TEXTURE_2D);
	}

	public static String openGLVersion()
	{
		return glGetString(GL_VERSION);
	}

	public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType)
	{
		//TODO: Add a better support for custom shader uniform struct update.
		throw new IllegalArgumentException(uniformType + " is not a supported type in Rendering Engine!");
	}

	public void addLight(BaseLight light)
	{
		lights.add(light);
	}

	public void render(@NotNull GameObject object)
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		object.renderAll(forwardAmbient, this);
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);

		for (BaseLight light : lights)
		{
			activeLight = light;
			object.renderAll(light.getShader(), this);
		}

		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}

	public void renderGUI()
	{
		glDisable(GL_DEPTH_TEST);
		//TODO: Add GUI support
		glEnable(GL_DEPTH_TEST);
	}

	public BaseLight getActiveLight()
	{
		return this.activeLight;
	}

	public Camera getMainCamera()
	{
		return mainCamera;
	}

	public void addCamera(Camera camera)
	{
		this.mainCamera = camera;
	}

	public int getSamplerSlot(String samplerName)
	{
		return integerHashMap.get(samplerName);
	}
}
