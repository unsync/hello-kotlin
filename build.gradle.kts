import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
	const val kotlin = "1.4.20"
	const val junit = "5.7.0"
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.4.20")
    }
}

plugins {
	kotlin("jvm").version("1.4.20")
	kotlin("plugin.allopen").version("1.4.20")
	id("com.github.ben-manes.versions").version("0.36.0")
	id("io.gitlab.arturbosch.detekt").version("1.14.2")
	id("org.jlleitschuh.gradle.ktlint").version("9.4.1")
}

group = "kotlin.hello"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly: Configuration by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")

    implementation("org.apache.commons:commons-lang3:3.11")

	testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
