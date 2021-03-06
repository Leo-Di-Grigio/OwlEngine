package com.owlengine.lua;

import java.io.File;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;

import com.owlengine.scenes.SceneMng;

public final class LuaEngine {
	
	private static LuaLib luaLib;
	private static SceneMng scenes;
	
	public LuaEngine(final SceneMng scenes, final LuaLib luaLib) {
		LuaEngine.scenes = scenes;
		LuaEngine.luaLib = luaLib;
	}
	
	public static LuaScript load(final String path, final String method){
		if(path != null){
			File file = new File(path);
			
			if(file.exists()){
				try {
		    		LuaValue globals = luaLib.getGlobals(scenes);
					globals.get("dofile").call(LuaValue.valueOf(path));
					return new LuaScript(globals, method);
		    	}
		    	catch (LuaError e){  
		    		e.printStackTrace();
		    	}
			}
		}
		
		return null;
	}
}