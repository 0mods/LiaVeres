package com.algorithmlx.liaveres.api.config

import com.algorithmlx.liaveres.common.LiaVeres
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import net.minecraftforge.fml.loading.FMLPaths
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.lang.RuntimeException
import java.lang.reflect.Field
import java.lang.reflect.Modifier

class KConfigBuilder(private val clazz: Class<*>, private val configName: String) {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    private fun setup() {
        val configDir = FMLPaths.GAMEDIR.get().resolve("liaveres").toFile()
        if (!configDir.exists()) configDir.mkdirs()

        val configFiles: Array<File>? = configDir.listFiles()

        if (configFiles != null) {
            val configs = HashMap<String, JsonObject>()
            configFiles.forEach {
                val name = it.name.substring(0, it.name.length - (".json".length))
                try {
                    val fileContents = FileUtils.readFileToString(it, Charsets.UTF_8)
                    val jsonObject = gson.fromJson(fileContents, JsonObject::class.java)
                    configs[name] = jsonObject
                } catch (e: IOException) {
                    LiaVeres.LOGGER.error("Failed to load config file ${it.name}", e)
                    e.printStackTrace()
                }
            }
            readFromJson(configs)
        }

        this.toJson().entries.forEach {
            val configFile = File(configDir, it.key + ".json")
            val jsonStr = gson.toJson(it.value)
            try {
                FileUtils.writeStringToFile(configFile, jsonStr, Charsets.UTF_8)
            } catch (e: IOException) {
                throw RuntimeException("Failed to save config file: ${configFile.absolutePath}")
            }
        }
    }

    private fun getConfigFields(): HashMap<Field, Config> {
        val fieldMap = HashMap<Field, Config>()
        for (it in clazz.declaredFields) {
            if (!it.isAnnotationPresent(Config::class.java)) continue
            if (!Modifier.isStatic(it.modifiers)) throw UnsupportedOperationException("Field \"${it.name}\" is not static")
            val annotation = it.getAnnotation(Config::class.java)
            fieldMap[it] = annotation
        }

        return fieldMap
    }

    private fun toJson(): HashMap<String, JsonObject> {
        val fieldMap = this.getConfigFields()
        val configs = HashMap<String, JsonObject>()

        fieldMap.entries.forEach {
            val field = it.key
            val annotation = it.value

            val config = configs.computeIfAbsent(this.configName) { _-> JsonObject() }

            val categoryObject: JsonObject

            if (config.has(annotation.branch)) {
                categoryObject = config.getAsJsonObject(annotation.branch)
            } else {
                categoryObject = JsonObject()
                config.add(annotation.branch, categoryObject)
            }

            val key: String = annotation.name.ifEmpty { field.name }

            if (categoryObject.has(key))
                throw UnsupportedOperationException("Some bad news.. Duplicate key found: $key")

            val fieldObject = JsonObject()

            fieldObject.addProperty("comment", annotation.comment)

            val value: Any
            try {
                value = field.get(null)
            } catch (e: IllegalAccessException) {
                throw RuntimeException(e)
            }

            val element = gson.toJsonTree(value)
            fieldObject.add("value", element)

            categoryObject.add(key, fieldObject)
        }

        return configs
    }

    fun readFromJson(configs: HashMap<String, JsonObject>) {
        val fieldMap = this.getConfigFields()

        for (entry in fieldMap.entries) {
            val field = entry.key
            val annotation = entry.value

            val config = configs[this.configName] ?: continue

            val categoryObject = config.getAsJsonObject(annotation.branch) ?: continue

            val key = field.name
            if (!categoryObject.has(key)) continue

            val fieldObject = categoryObject.get(key).asJsonObject
            if (!fieldObject.has("value")) continue

            val jsonValue = fieldObject.get("value")
            val fieldType = field.type

            val fieldValue: Any = gson.fromJson(jsonValue, fieldType)
            try {
                field.set(null, fieldValue)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Failed to set field value", e)
            }
        }
    }

    companion object {
        fun build(configClass: Class<*>, configFileName: String) {
            KConfigBuilder(configClass, configFileName).setup()
        }
    }
}