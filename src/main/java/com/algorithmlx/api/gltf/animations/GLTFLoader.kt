package com.algorithmlx.api.gltf.animations

import java.io.InputStream

object GLTFLoader {
    @JvmStatic
    fun run(stream: InputStream): InputStream {
        var json = stream.bufferedReader().use { it.readText() }
        json = json.replace("pbrMetallicRoughness", "extras")

        return json.byteInputStream()
    }
}