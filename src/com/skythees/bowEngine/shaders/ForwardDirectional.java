package com.skythees.bowEngine.shaders;

import com.skythees.bowEngine.core.components.BaseLight;
import com.skythees.bowEngine.core.components.DirectionalLight;
import com.skythees.bowEngine.core.math.vector.Matrix4f;
import com.skythees.bowEngine.render.Material;
import com.skythees.bowEngine.render.Shader;
import com.skythees.bowEngine.render.Transform;

/**
 * Created on 15.3.2015. at 19:42.
 */
public class ForwardDirectional extends Shader {
    private static final ForwardDirectional instance = new ForwardDirectional();

    private ForwardDirectional() {
        super();

        addVertexShader(loadInternalShader("forward-directional.vsh"));
        addFragmentShader(loadInternalShader("forward-directional.fsh"));

        setAttributeLocation("position", 0);
        setAttributeLocation("texturePos", 1);
        setAttributeLocation("normal", 2);

        compileShader();

        addUniform("model");
        addUniform("MVP");

        addUniform("specularIntensity");
        addUniform("specularExponent");
        addUniform("eyePos");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    @SuppressWarnings("UnusedDeclaration")
    public static ForwardDirectional getInstance() {
        return instance;
    }

    @Override
    public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);
        material.getTexture().bind();

        setUniform("model", worldMatrix);
        setUniform("MVP", projectedMatrix);

        setUniform("specularIntensity", material.getSpecularIntensity());
        setUniform("specularExponent", material.getSpecularExponent());

        setUniform("eyePos", getRenderingEngine().getMainCamera().getPos());
        setUniform("directionalLight", getRenderingEngine().getActiveDirectionalLight());
    }

    public void setUniform(String uniformName, DirectionalLight directionalLight) {
        setUniform(uniformName + ".base", directionalLight.getBase());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }

    public void setUniform(String uniformName, BaseLight baseLight) {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniform(uniformName + ".intensity", baseLight.getIntensity());
    }
}
