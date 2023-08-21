import net.minecraftforge.gradle.userdev.UserDevExtension
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.spongepowered.asm.gradle.plugins.MixinExtension

buildscript {
    dependencies {
        classpath("org.spongepowered:mixingradle:0.7-SNAPSHOT")
    }
}

plugins {
    java
    idea
    `maven-publish`
    id("net.minecraftforge.gradle") version "6.+"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
}

apply(plugin = "org.spongepowered.mixin")

val mcVersion: String by project
val forgeVersion: String by project
val coroutinesVersion: String by project
val serializationVersion: String by project
val versionMod: String by project
val typeVersion: String by project
val jeiVersion: String by project
val lvVersion: String = "${mcVersion}-${typeVersion}${versionMod}"

val uuid = project.findProperty("uuid_mc") as String
val accessToken = project.findProperty("access_token_mc") as String
val clientId = project.findProperty("client_id_mc") as String
val xuid = project.findProperty("xuid_mc") as String

val shadow: Configuration by configurations.creating

val main: SourceSet = sourceSets["main"]

group = "com.algorithmlx"
version = lvVersion

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
    mappings("official", mcVersion)

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
//    maven("https://maven.theillusivec4.top/")
//    maven("https://repsy.io/mvn/algorithmlx/algomaven/")
//    maven("https://dvs1.progwml6.com/files/maven/")
//    maven("https://modmaven.dev/")
//    maven("https://maven.theillusivec4.top/")
//    maven("https://maven.blamejared.com/")

    mavenCentral()
}

dependencies {
    minecraft("net.minecraftforge:forge:$mcVersion-$forgeVersion")

//    implementation(fg.deobf("mezz.jei:jei-${mcVersion}-common-api:${jeiVersion}"))
//    implementation(fg.deobf("mezz.jei:jei-${mcVersion}-forge-api:${jeiVersion}"))
//    implementation(fg.deobf("mezz.jei:jei-${mcVersion}-forge:${jeiVersion}"))
//    implementation(fg.deobf("top.theillusivec4.curios:curios-forge:5.2.0-beta.3+1.20.1"))

    implementation(kotlin("stdlib"))

    shadow("org.jetbrains.kotlin:kotlin-reflect:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlin:kotlin-stdlib:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlin:kotlin-stdlib-common:${kotlin.coreLibrariesVersion}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${coroutinesVersion}")
    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${coroutinesVersion}")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-core:${serializationVersion}")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-json:${serializationVersion}")

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
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