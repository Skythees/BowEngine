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

package com.skythees.bowEngine.core.lib;

/**
 * Created on 03.03.15.
 */
@SuppressWarnings({"ALL", "unused"})
public class Reference
{
	public class Info
	{
		@SuppressWarnings("unused")
		public static final String ENGINE_NAME    = "Bow Engine";
		@SuppressWarnings("unused")
		public static final String ENGINE_ID      = "bow_engine";
		@SuppressWarnings("unused")
		public static final String ENGINE_VERSION = "0.0.1";
		@SuppressWarnings("unused")
		public static final String AUTHOR         = "Skythees";
	}

	public class FileParsing
	{
		@SuppressWarnings("unused")
		public static final String OPENGL_ATTRIBUTE = "attribute";
		@SuppressWarnings("unused")
		public static final String OPENGL_STRUCTURE = "struct";
		@SuppressWarnings("unused")
		public static final String OPENGL_UNIFORM   = "uniform";

		@SuppressWarnings("unused")
		public static final String INCLUDE_DIRECTIVE = "#include";
	}

	public class Extensions
	{
		@SuppressWarnings("unused")
		public static final String FRAGMENT_SHADER_EXTENSION = ".fs";
		@SuppressWarnings("unused")
		public static final String VERTEX_SHADER_EXTENSION   = ".vs";
		@SuppressWarnings("unused")
		public static final String GEOMETRY_SHADER_EXTENSION = ".gs";

		@SuppressWarnings("unused")
		public static final String OPENGL_HEADER = ".glh";
	}
}
