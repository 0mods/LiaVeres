import net.minecraftforge.gradle.userdev.UserDevExtension
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.spongepowered.asm.gradle.plugins.MixinExtension

buildscript {
    dependencies {
        classpath("org.spongepowered:mixingradle:0.7-SNAPSHOT")
    }
}

plugins {
    java
    eclipse
    idea
    `maven-publish`
    id("net.neoforged.gradle") version "6.+"
    kotlin("jvm") version "1.9.0-Beta"
}

apply(plugin = "org.spongepowered.mixin")

val mc_version: String by project
val forge_version: String by project
val coroutines_version: String by project
val serialization_version: String by project
val version_mod: String by project
val type_version: String by project
val lv_version: String = "${mc_version}-${type_version}${version_mod}"

val uuid = project.findProperty("uuid_mc") as String
val accessToken = project.findProperty("access_token_mc") as String
val clientId = project.findProperty("client_id_mc") as String
val xuid = project.findProperty("xuid_mc") as String

val shadow: Configuration by configurations.creating

val main = sourceSets["main"]

group = "com.algorithmlx"
version = lv_version

evaluationDependsOnChildren()

jarJar.enable()

configurations {
    runtimeElements {
        setExtendsFrom(emptySet())

        artifacts.clear()
        outgoing.artifact(tasks.jarJar)
    }
    minecraftLibrary {
        extendsFrom(shadow)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

configure<UserDevExtension> {
    mappings("official", mc_version)

    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    runs {
        create("client") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "liaveres")
            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")
            args("-mixin.config=liaveres.mixins.json", "--uuid $uuid", "--accessToken $accessToken", "--clientId $clientId", "--xuid $xuid", "--userType msa")

            mods {
                create("liaveres") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("server") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "liaveres")
            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")
            arg("-mixin.config=liaveres.mixins.json")

            mods {
                create("liaveres") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("gameTestServer") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "liaveres")
            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")
            arg("-mixin.config=liaveres.mixins.json")

            mods {
                create("liaveres") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("data") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            args("--mod", "liaveres", "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))

            mods {
                create("liaveres") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }
    }
}

sourceSets {
    named("main") {
        resources.srcDir("src/generated/resources")
    }
}

configure<MixinExtension> {
    add(main, "liaveres.refmap.json")
}

repositories {
    maven("https://nexus.twelveiterations.com/repository/maven-public/")
    maven("https://maven.theillusivec4.top/")

    mavenCentral()
}

dependencies {
    minecraft("net.neoforged:forge:$mc_version-$forge_version")

    implementation(fg.deobf("top.theillusivec4.curios:curios-forge:5.2.0-beta.3+1.20.1"))

    shadow("org.jetbrains.kotlin:kotlin-reflect:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlin:kotlin-stdlib:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlin:kotlin-stdlib-common:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${coroutines_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${coroutines_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-core:${serialization_version}")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-json:${serialization_version}")

    implementation("org.projectlombok:lombok:1.18.28")

    implementation(kotlin("stdlib"))

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks {
    withType<Jar> {
        from(main.output)
        manifest {
            attributes(
                mapOf(
                    "Specification-Title" to "liaveres",
                    "Specification-Vendor" to "AlgorithmLX",
                    "Specification-Version" to "1",
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to version,
                    "Implementation-Timestamp" to ZonedDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")),
                    "MixinConfigs" to "liaveres.mixins.json"
                )
            )
        }
        finalizedBy("reobfJar")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}
kotlin {
    jvmToolchain(17)
}